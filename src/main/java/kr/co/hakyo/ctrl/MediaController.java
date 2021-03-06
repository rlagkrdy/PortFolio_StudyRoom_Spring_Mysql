package kr.co.hakyo.ctrl;

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
import kr.co.hakyo.utils.ImageUtil;

@Controller
@RequestMapping("/media")
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@Autowired
	private ImageUtil iu;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> dataMap) {
		try {
			return mediaService.list(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{media_KEY}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> info(@PathVariable("media_KEY") Long media_KEY) {
		
		try {
			Map<String, Object> info = mediaService.info(media_KEY);
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

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@RequestParam Map<String, Object> dataMap, MultipartHttpServletRequest request) {
		try {
			Map<String, Object> exist = new HashMap<String, Object>();
			exist.put("media_KEY", dataMap.get("media_KEY"));
			List<?> existList = mediaService.list(exist);
			
			System.err.println(dataMap);
			Iterator<String> fileName = request.getFileNames();
			Map<String, Object> mediaMap = null;
			while(fileName.hasNext()) {
				String fileNames = fileName.next();
				mediaMap = iu.imageUpload(request.getFile(fileNames), (long) 1, "USR_PROFILE");
				System.err.println("mediaMap : " +mediaMap);
			}
			dataMap.put("default", mediaMap.get("MEDIA_URL"));
			return dataMap;
//			if(existList.size() > 0) {
//				return 0;
//			} else {
//				return mediaService.insert(dataMap);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{media_KEY}", method = RequestMethod.PUT)
	@ResponseBody
	public int update(@PathVariable("media_KEY") Long media_KEY, @RequestBody Map<String, Object> dataMap) {
		try {
			dataMap.put("media_KEY", media_KEY);
			return mediaService.update(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{media_KEY}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable("media_KEY") Long media_KEY) {
		try {
			int del = mediaService.delete(media_KEY);
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
