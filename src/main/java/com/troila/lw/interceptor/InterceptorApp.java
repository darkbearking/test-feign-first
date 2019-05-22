package com.troila.lw.interceptor;

import com.troila.lw.HelloClient;

import feign.Feign;

public class InterceptorApp {

	public static void main(String[] args) {
		HelloClient client = Feign.builder()
				//設置了請求攔截器
				.requestInterceptor(new MyInterceptor())
				.target(HelloClient.class, "http://localhost:8080");
		String result = client.hello();
		System.out.println(result+"&&&");
	}
}
