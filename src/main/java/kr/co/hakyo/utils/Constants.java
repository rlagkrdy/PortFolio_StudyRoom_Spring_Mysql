package kr.co.hakyo.utils;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	public static String URL = "http://localhost:8080/";
//	public static String directoryPath = "C:/Users/김학요/Desktop/PortFolio_StudyRoom_Spring_Mysql/src/main/webapp/resources/assets/FILE";
//	public static String Url = "http://localhost:8080/assets/FILE/";
	
//	public static String URL = "http://221.149.240.50:8080/";
	public static String directoryPath = "/home/orangepi/tomcat8/webapps/ROOT/resources/assets/FILE";
	public static String Url = URL + "assets/FILE/";  
	
	
	public static ArrayList<String> CD01 = new ArrayList<String>(); // SNS
	public static ArrayList<String> CD02 = new ArrayList<String>(); // 판매제품
	public static ArrayList<String> CD03 = new ArrayList<String>(); // 예약상태
}
