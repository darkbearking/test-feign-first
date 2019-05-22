package com.troila.lw.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyInterceptor implements RequestInterceptor {

	public void apply(RequestTemplate template) {
		//對於HelloClient類中，諸多方法都有一個“@Header”註解，
		//當我們不想做這種逐個設置的時候，可以在這種攔截器裡面多批量處理
		//這個攔截器的效果，就是自動為請求添加一個json類型的請求頭。再也不必為每個請求各自添加相同的東西了。
		
		//當然，除了可以設置請求頭類型，還可以設置業務級的過濾，比如賬號、密碼等內容。
		template.header("Content-Type","application/json");
		System.out.println("這是自定義請求攔截器");
	}

}
