package com.troila.lw.jaxrs;

import feign.Feign;
import feign.jaxrs.JAXRSContract;

public class RsApp {

	public static void main(String[] args) {
		RSClient client = Feign.builder()
				//contract是翻譯器，就是通過jaxrs規則，告訴feign @get、@path這些註解是干什麼用的
				.contract(new JAXRSContract())
				.target(RSClient.class, "http://localhost:8080");
		String result = client.hello();
		System.out.println(result+"&&&");
	}

}
