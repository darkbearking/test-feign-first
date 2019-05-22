package com.troila.lw;
import feign.Feign;

public class MyFeignClientTest {

	public static void main(String[] args) {
		HelloClient client = Feign.builder()
				.client(new MyFeignClient())
				.target(HelloClient.class,"http://localhost:8080");
		String result = client.hello();
		System.out.println(result);
	}
}
