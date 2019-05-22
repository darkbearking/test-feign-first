package com.troila.lw.contract;

import com.troila.lw.jaxrs.RSClient;

import feign.Feign;
import feign.jaxrs.JAXRSContract;

public class ContractApp {

	public static void main(String[] args) {
		ContractClient client = Feign.builder()
				//contract是翻譯器，就是通過自定義的MyContract類中的規則，告訴feign @MyUrl註解是干什麼用的
				.contract(new MyContract())
				.target(ContractClient.class, "http://localhost:8080");
		String result = client.hello();
		System.out.println(result+"###");
	}

}
