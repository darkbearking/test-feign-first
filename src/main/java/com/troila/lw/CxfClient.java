package com.troila.lw;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class CxfClient {

	public static void main(String[] args) {
		//創建WebClient，并訪問某個Web服務
		WebClient client = WebClient.create("http://localhost:8080/person/1");
		
		//獲取響應
		Response response = client.get();
		
		//獲取響應內容
		InputStream is = (InputStream)response.getEntity();
		String content = null;
		try {
			content = IOUtils.readStringFromStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(content);
	}

}
