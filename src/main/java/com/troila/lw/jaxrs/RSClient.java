package com.troila.lw.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import feign.Feign;
import feign.RequestLine;
import feign.jaxrs.JAXRSContract;

public interface RSClient {

	/**
	 * get表示以http的get方式發送請求，path表示請求路徑
	 * 但是其含義的表述，需要通過Feign的.contract(new JAXRSContract())方法來進行翻譯。
	 * 否則這兩個註解是無效的
	 */
	@GET
	@Path("/hello")
	//上面的兩個註解，相當於feign的@RequestLine("GET /hello")註解和註解中的參數的效果了。
	public String hello();
	//上面寫法的效果，等同於下面這兩行的效果
//	@RequestLine("GET /hello")
//	public String hello();
}
