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

import kr.co.hakyo.service.DefService;

@Controller
@RequestMapping("/def")
public class DefController {

	@Autowired
	private DefService defService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> dataMap) {
		try {
			return defService.list(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{def_KEY}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> info(@PathVariable("def_KEY") Long def_KEY) {
		
		try {
			Map<String, Object> info = defService.info(def_KEY);
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
			exist.put("def_KEY", dataMap.get("def_KEY"));
			List<?> existList = defService.list(exist);
			if(existList.size() > 0) {
				return 0;
			} else {
				return defService.insert(dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{def_KEY}", method = RequestMethod.PUT)
	@ResponseBody
	public int update(@PathVariable("def_KEY") Long def_KEY, @RequestBody Map<String, Object> dataMap) {
		try {
			dataMap.put("def_KEY", def_KEY);
			return defService.update(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{def_KEY}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable("def_KEY") Long def_KEY) {
		try {
			int del = defService.delete(def_KEY);
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
