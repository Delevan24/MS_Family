package com.eternity.microservice.base.dontlook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class exceltest {
	
	public static final Map<String, Object> keyMap = new HashMap<>();
	
	
	public static void main(String[] args) {
		/*System.out.println(keyMap);
		keyMap.put("11", 11);
		keyMap.put("22", 22);
		keyMap.put("33", 33);
		System.out.println(keyMap);
		modifyMap();
		System.out.println(keyMap);*/
		new HashMap<>();
		new ArrayList<Integer>();
		new Hashtable<>();
		new ConcurrentHashMap<>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
	}
	
	public static void modifyMap(){
		
		keyMap.put("44", 44);
	}
}
