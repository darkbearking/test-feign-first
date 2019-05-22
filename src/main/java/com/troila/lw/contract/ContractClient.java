package com.troila.lw.contract;

public interface ContractClient {

	@MyUrl(url = "/hello" ,method = "GET")
	public String hello();
}
