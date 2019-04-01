package com.eternity.microservice.demo1;

import com.eternity.microservice.demo1.annotation.AnnoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {
	
	@Resource
	public AnnoTest annoTest;
	
	@Test
	public void test1(){
		annoTest.printLine("==================adadf==================");
	}
	
}
