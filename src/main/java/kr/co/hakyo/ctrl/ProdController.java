package kr.co.hakyo.ctrl;

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

import kr.co.hakyo.service.ProdService;

@Controller
@RequestMapping("/prod")
public class ProdController {

	@Autowired
	private ProdService prodService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> dataMap) {
		try {
			return prodService.list(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{PROD_KEY}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> info(@PathVariable("PROD_KEY") Long PROD_KEY) {
		
		try {
			Map<String, Object> info = prodService.info(PROD_KEY);
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
			return prodService.insert(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{PROD_KEY}", method = RequestMethod.PUT)
	@ResponseBody
	public int update(@PathVariable("PROD_KEY") Long PROD_KEY, @RequestBody Map<String, Object> dataMap) {
		try {
			dataMap.put("PROD_KEY", PROD_KEY);
			return prodService.update(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{PROD_KEY}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable("PROD_KEY") Long PROD_KEY) {
		try {
			int del = prodService.delete(PROD_KEY);
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
