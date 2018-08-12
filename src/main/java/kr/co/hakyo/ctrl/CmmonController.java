package kr.co.hakyo.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginAction")
public class CmmonController {
	
	@RequestMapping("**")
	public void login(){
		System.err.println("로그인 진입");
	}

}