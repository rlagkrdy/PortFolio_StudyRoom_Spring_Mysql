package kr.co.hakyo.secu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.err.println("에러 진입??? : " + auth.getMessage());
		System.err.println(req.getHeader("referer"));
		
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.getWriter()
		.write("{\"isSucess\" : \"ok\"}");
	}
 
}