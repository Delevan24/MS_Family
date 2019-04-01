package com.eternity.microservice.demo1.annotation;

import org.springframework.stereotype.Component;

@Component
public class AnnoTest {
	
	@DoSomeThing(describe = "sd" , module = "sd", optType = "sdsa")
	public void printLine(String name) {
		System.out.println(name);
		
	}
}
