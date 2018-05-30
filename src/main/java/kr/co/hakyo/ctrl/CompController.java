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

import kr.co.hakyo.service.CompService;

@Controller
@RequestMapping("/comp")
public class CompController {

	@Autowired
	private CompService compService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> dataMap) {
		try {
			return compService.list(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{COMP_KEY}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> info(@PathVariable("COMP_KEY") Long COMP_KEY) {
		
		try {
			Map<String, Object> info = compService.info(COMP_KEY);
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
			exist.put("COMP_KEY", dataMap.get("COMP_KEY"));
			List<?> existList = compService.list(exist);
			if(existList.size() > 0) {
				return 0;
			} else {
				return compService.insert(dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{COMP_KEY}", method = RequestMethod.PUT)
	@ResponseBody
	public int update(@PathVariable("COMP_KEY") Long COMP_KEY, @RequestBody Map<String, Object> dataMap) {
		try {
			dataMap.put("COMP_KEY", COMP_KEY);
			return compService.update(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{COMP_KEY}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable("COMP_KEY") Long COMP_KEY) {
		try {
			int del = compService.delete(COMP_KEY);
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
