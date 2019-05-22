 package com.troila.lw.log;

import com.troila.lw.HelloClient;
import com.troila.lw.interceptor.MyInterceptor;

import feign.Feign;
import feign.Logger;

public class LogApp {
	
	/**
	 * 日誌級別有四種
	 * NONE：	默認值，不記錄日誌
	 * BASIC：	記錄請求方式、URL、響應狀態代碼和執行時間
	 * HEADERS:	除了BASIC之外，還會記錄請求頭和響應頭
	 * FULL：	在HEADERS基礎上，還會記錄請求和響應的元數據
	 */
	public static void main(String[] args) {
		HelloClient client = Feign.builder()
				//這裡設置日誌級別
				.logLevel(Logger.Level.FULL)
				.logger(new Logger.JavaLogger().appendToFile("log/http.log"))
				.target(HelloClient.class, "http://localhost:8080");
		String result = client.hello();
		System.out.println(result+"&&&");
	}

}
