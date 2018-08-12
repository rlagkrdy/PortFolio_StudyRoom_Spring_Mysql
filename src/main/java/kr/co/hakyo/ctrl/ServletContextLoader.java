package kr.co.hakyo.ctrl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import kr.co.hakyo.service.impl.CompServiceImpl;
import kr.co.hakyo.utils.Constants;

public class ServletContextLoader implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	getCode();
		    }
		}).start();
	}
	
	private static void getCode() {
		Gson gson = new Gson();
		System.err.println("@@@@@@@@ START GET CODE @@@@@@@@");
		String codeUrl = Constants.URL+"code/";
		
		ArrayList<Map<String, Object>> codeList = getMapRestTemplateList(codeUrl);
		for(int i=0; i<codeList.size(); i++) {
			System.err.println("["+i+"]"+codeList.get(i));
			Map<String, Object> obj = codeList.get(i);
			try {
				Class constantsClass = Class.forName("kr.co.hakyo.utils.Constants");
				Field[] constantsFields = constantsClass.getFields();
				for(Field fd : constantsFields) {
					if(fd.getType().equals(ArrayList.class)) {
						Object instVal = fd.getType().newInstance();
						ArrayList<String> arr = (ArrayList<String>) fd.get(instVal);
						if(obj.get("CODE_CATE").equals(fd.getName())) {
							arr.add(gson.toJson(obj));
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
			
		System.err.println("@@@@@@@@ END GET CODE @@@@@@@@");
	}
	
	public static ArrayList<Map<String, Object>> getMapRestTemplateList(String url) {
		RestTemplate restTemplate = new RestTemplate();
		
		String json = restTemplate.getForObject(url, String.class); 
		
		ObjectMapper om = new ObjectMapper();
		ArrayList<Map<String, Object>> res = null;
		
		try {
			res = om.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return res;
	}
}
