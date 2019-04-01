package com.eternity.microservice.demo1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoSomeThing {
	
	/** 操作类型（INSERT、UPDATE、SELECT、DELETE） */
	public String optType();
	
	/** 描述 */
	public String describe();
	
	/** 模块 */
	public String module();
}
