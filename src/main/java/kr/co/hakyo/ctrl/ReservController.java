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

import kr.co.hakyo.service.ReservService;

@Controller
@RequestMapping("/reserv")
public class ReservController {

	@Autowired
	private ReservService reservService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> dataMap) {
		try {
			return reservService.list(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{RESERV_KEY}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> info(@PathVariable("RESERV_KEY") Long RESERV_KEY) {
		
		try {
			Map<String, Object> info = reservService.info(RESERV_KEY);
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
			return reservService.insert(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{RESERV_KEY}", method = RequestMethod.PUT)
	@ResponseBody
	public int update(@PathVariable("RESERV_KEY") Long RESERV_KEY, @RequestBody Map<String, Object> dataMap) {
		try {
			dataMap.put("RESERV_KEY", RESERV_KEY);
			return reservService.update(dataMap);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(value = "/{RESERV_KEY}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable("RESERV_KEY") Long RESERV_KEY) {
		try {
			int del = reservService.delete(RESERV_KEY);
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
