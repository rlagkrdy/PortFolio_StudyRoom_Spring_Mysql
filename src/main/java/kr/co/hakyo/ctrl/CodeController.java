package kr.co.hakyo.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hakyo.service.CodeService;

@Controller
@RequestMapping("/code")
public class CodeController {
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> list(@RequestParam Map<String, Object> dataMap){
		try {
			return codeService.list(dataMap);
		} catch(Exception e) {
			return null;
		}
	}
}
