package cn.com.ws.helloword.service;

import org.springframework.stereotype.Component;

@Component
public class SayHello {
	
	public String say() {
		return "hello";
	}
}
