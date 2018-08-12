package kr.co.hakyo.secu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.err.println("로그인 성공 진입!!!");
		
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter()
		.write("{\"isSucess\" : \"ok\"}");
	}
 
}