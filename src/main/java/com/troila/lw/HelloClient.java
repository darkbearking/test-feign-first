package com.troila.lw;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 欲用feign，先定義接口
 * @author liwei
 *
 */
public interface HelloClient {

	//這裡不需要寫請求全地址，只寫功能地址即可，前置地址統一定義
	@RequestLine("GET /hello")
	public String hello();
	
	//使用param參數需要提供一個參數，類似controller的寫法。
	@RequestLine("GET /person/{id}")
	public Person getPerson(@Param("id") Integer id);
	
	//使用param參數需要提供一個參數，類似controller的寫法。
	@RequestLine("POST /person/create")
	//下面這個註解的作用，是告訴接收方，我發送的請求體是一個json字符串
	@Headers("Content-Type: application/json")
	public String createPerson(Person person);
	
	//測試xml格式的調用
	@RequestLine("POST /person/createXML")
	@Headers("Content-Type: application/xml")
	public XmlResult createXMLPerson(Person person);
}
