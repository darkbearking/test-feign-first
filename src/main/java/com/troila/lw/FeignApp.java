package com.troila.lw;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class FeignApp {
	
	public static void main(String[] args) {
		//請求的前置路徑在這裡統一定義
		//如果返回的是簡單對象，比如字符串，這裡是不需要解碼器的。
		HelloClient client = Feign.builder().target(HelloClient.class , "http://localhost:8080");
		String json = client.hello();
		System.out.println(json);
		
		//因為返回的內容是json，但是我們需要以Person格式（對象形式）顯示，因此需要做編解碼，將json解析為對象
		HelloClient client1 = Feign.builder().
				decoder(new GsonDecoder()).target(HelloClient.class , "http://localhost:8080");
		Person p = client1.getPerson(1);
		System.out.println(p.getClass().toString() + "==" + p.getId() + "--" + p.getName() + "--" + p.getMessage());
		
		//當你調用post類型請求的時候，就需要傳遞參數給服務端，那麼就需要encode
		Person person = new Person();
		person.setId(22);
		person.setName("dark");
		person.setMessage("is ok?");
		
		//同樣的道理，因為在發送請求到服務端的過程中是以json傳參的，那麼需要把對象變為json，就需要進行編碼
		HelloClient client2 = Feign.builder().
				encoder(new GsonEncoder()).target(HelloClient.class , "http://localhost:8080");
		try {
			json = client2.createPerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(json+" ####");
		
		//測試調用xml格式的請求
		//下面有問題，沒有調通，暫時擱置
/*		JAXBContextFactory factory = new JAXBContextFactory.Builder().build();
		XmlResult xmlResult = null;
		HelloClient client3 = Feign.builder()
				.encoder(new JAXBEncoder(factory))
				.decoder(new JAXBDecoder(factory))
				.target(HelloClient.class , "http://localhost:8080");
		try {
			xmlResult = client3.createXMLPerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(xmlResult.getMessage());*/
	}
}
