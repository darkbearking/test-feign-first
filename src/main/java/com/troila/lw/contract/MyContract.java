package com.troila.lw.contract;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;

import feign.Contract.BaseContract;
import feign.MethodMetadata;

public class MyContract extends BaseContract {

	//處理類級別註解
	@Override
	protected void processAnnotationOnClass(MethodMetadata arg0, Class<?> arg1) {
		// TODO Auto-generated method stub

	}

	//處理方法級別註解
	//同時因為我們定義的MyUrl註解僅僅是一個方法級的註解（@Target(ElementType.METHOD)），故只對當前方法進行完善即可
	@Override
	protected void processAnnotationOnMethod(MethodMetadata data, Annotation annotation, Method method) {
		//如果Annotation是MyUrl類型的，我們才做處理，否則忽略
		if(MyUrl.class.isInstance(annotation)) {
			MyUrl myurl = method.getAnnotation(MyUrl.class);
			String url = myurl.url();
			String httpMethod = myurl.method();
			//data.template()保存http請求的信息，包括請求頭、url等
			data.template().method(httpMethod);
			//下面的寫法是取代原有的請求路徑。相當於url的重寫了。
			//data.template().url(url);
			//下面的寫法是追加到原有的請求路徑下。因為我們在feign客戶端一般寫的是服務的通用地址，然後追加具體功能的地址
			data.template().append(url);
		}
	}

	//處理參數級別註解
	@Override
	protected boolean processAnnotationsOnParameter(MethodMetadata arg0, Annotation[] arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
