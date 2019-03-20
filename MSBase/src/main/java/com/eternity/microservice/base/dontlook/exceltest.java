package com.eternity.microservice.base.dontlook;

import java.util.HashMap;
import java.util.Map;


public class exceltest {
	
	public static final Map<String, Object> keyMap = new HashMap<>();
	
	
	public static void main(String[] args) {
		System.out.println(keyMap);
		keyMap.put("11", 11);
		keyMap.put("22", 22);
		keyMap.put("33", 33);
		System.out.println(keyMap);
		modifyMap();
		System.out.println(keyMap);
	}
	
	public static void modifyMap(){
		
		keyMap.put("44", 44);
	}
}
