package kr.co.hakyo.ctrl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.hakyo.service.MediaService;
import kr.co.hakyo.service.UsrService;
import kr.co.hakyo.utils.ImageUtil;

@Controller
@RequestMapping("/usr")
public class UsrController {

	@Autowired
	private UsrService usrService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ImageUtil iu;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> dataMap) {
		try {
			return usrService.list(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{USR_KEY}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> info(@PathVariable("USR_KEY") Long USR_KEY) {
		try {
			Map<String, Object> info = usrService.info(USR_KEY);
			if(info != null) {
				return info;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/imageInsert", method = RequestMethod.POST)
	@ResponseBody
	public int imageTest(@RequestParam Map<String, Object> dataMap, MultipartHttpServletRequest request) {
		
		try {
			Iterator<String> fileName = request.getFileNames();
			while(fileName.hasNext()) {
				String fileNames = fileName.next();
				System.err.println(fileNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO 파일업로드 기능 정의
		return 0;
	}
	
	@RequestMapping(value = "/images", method = RequestMethod.POST)
	@ResponseBody
	public int imageInsert(@RequestParam Map<String, Object> dataMap, MultipartHttpServletRequest request) {
		Map<String, Object> exist = new HashMap<String, Object>();
		exist.put("USR_ID", dataMap.get("USR_ID"));
		
		try {
			List<?> existList = usrService.list(exist);
			if(existList.size() > 0) {
				return 0;
			} else {
				usrService.insert(dataMap);
				Long USR_KEY = Long.parseLong(dataMap.get("USR_KEY").toString());
				Iterator<String> fileName = request.getFileNames();
				while(fileName.hasNext()) {
					String fileNames = fileName.next();
					Map<String, Object> mediaMap = iu.imageUpload(request.getFile(fileNames), USR_KEY, "USR_PROFILE");
					mediaService.insert(mediaMap);
				}
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO 파일업로드 기능 정의
		return 0;
	}
	
	@RequestMapping(value = "/images/{USR_KEY}", method = RequestMethod.POST)
	@ResponseBody
	public int imageUpdate(@PathVariable("USR_KEY") Long USR_KEY, @RequestParam Map<String, Object> dataMap, MultipartHttpServletRequest request) {
		try {
			dataMap.put("USR_KEY", USR_KEY);
			usrService.update(dataMap);

			Map<String, Object> exist = new HashMap<String, Object>();
			exist.put("MEDIA_REF_KEY", USR_KEY);
			exist.put("MEDIA_REF_CATE", "USR_PROFILE");
			
			mediaService.list(exist).forEach(item -> {
				System.err.println("JAVA forEach문 진입!!" + item);
				iu.deleteFile(new File(item.get("MEDIA_ORI_URL").toString()));
				Long MEDIA_KEY = Long.parseLong(item.get("MEDIA_KEY").toString());
				try {
					mediaService.delete(MEDIA_KEY);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			Iterator<String> fileName = request.getFileNames();
			while(fileName.hasNext()) {
				String fileNames = fileName.next();
				Map<String, Object> mediaMap = iu.imageUpload(request.getFile(fileNames), USR_KEY, "USR_PROFILE");
				mediaService.insert(mediaMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO 파일업로드 기능 정의
		return 0;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public int create(@RequestParam Map<String, Object> dataMap) {
		try {
			Map<String, Object> exist = new HashMap<String, Object>();
			exist.put("USR_ID", dataMap.get("USR_ID"));
			List<?> existList = usrService.list(exist);
			if(existList.size() > 0) {
				return 0;
			} else {
				return usrService.insert(dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{USR_KEY}", method = RequestMethod.PUT)
	@ResponseBody
	public int update(@PathVariable("USR_KEY") Long USR_KEY, @RequestBody Map<String, Object> dataMap) {
		try {System.err.println("진입!!");
			dataMap.put("USR_KEY", USR_KEY);
			return usrService.update(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{USR_KEY}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable("USR_KEY") Long USR_KEY) {
		try {
			int del = usrService.delete(USR_KEY);
			if(del == 1) {
				return 1;
			} else {
				return del;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
}
