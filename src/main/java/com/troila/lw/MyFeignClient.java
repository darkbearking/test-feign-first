package com.troila.lw;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;

//feign的底層其實是HttpUrlConnection來連接http服務的。
public class MyFeignClient implements Client{

	/**
	 * 事實上Feign已經內置了關於http的請求了。這個例子的目的僅僅是用於測試了解流程。
	 * 而且在真正使用時，並不需要我們真正寫這樣的一個客戶端來使用。
	 * 
	 * 模擬實現feign的客戶端的功能。
	 * 將Feign的請求轉為http的請求，然後再將http的響應轉為feign的響應
	 * 說白了，這個方法就是請求轉請求，然後響應轉響應的過程。
	 * 
	 * 當前的例子中，並沒有對請求頭做處理，因此是一個簡化的客戶端，僅能轉化String類型的請求，而不能轉化其它類型。
	 * 換言之，一個請求可以接受與發送什麼類型的參數和返回值，主要看請求頭。
	 */
	public Response execute(Request request, Options options) throws IOException {
		System.out.println("this is my client");
		
		CloseableHttpClient httpClient = null;
		HttpRequestBase httpRequest = null;
		HttpResponse httpResponse = null;
		Response response = null;
		byte[] body = null;
		
		try {
			//創建一個默認的客戶端
			httpClient = HttpClients.createDefault();
			//獲取調用的http方法
			final String method = request.method();
			httpRequest = new HttpRequestBase() {
				
				@Override
				public String getMethod() {
					return method;
				}
			};
			//設置請求地址
			httpRequest.setURI(new URI(request.url()));
			//執行請求 獲取響應
			httpResponse = httpClient.execute(httpRequest);
			//獲取響應的主體內容
			body = EntityUtils.toByteArray(httpResponse.getEntity());
			//將HttpClient的響應對象轉換為Feign的Response
			response = Response.builder()
					.body(body)
					.headers(new HashMap<String, Collection<String>>())
					.status(httpResponse.getStatusLine().getStatusCode())
					.build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
