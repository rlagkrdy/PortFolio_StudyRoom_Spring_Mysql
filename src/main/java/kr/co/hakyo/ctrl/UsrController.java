package kr.co.hakyo.ctrl;

import java.util.HashMap;
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

import kr.co.hakyo.service.UsrService;

@Controller
@RequestMapping("/usr")
public class UsrController {

	@Autowired
	private UsrService usrService;


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
