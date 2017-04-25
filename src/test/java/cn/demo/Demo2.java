package cn.demo;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.testng.annotations.Test;

import com.httpframe.httpframe.HttpclientUtil;

public class Demo2 {
	
	private static String url;
	private static HttpContext localcontext=new BasicHttpContext();
	
	  static HttpClientContext context = HttpClientContext.adapt(localcontext);
	//@Test
	public void jingweiduTest()
	{
		url="http://api.ehuu.com/site-api/get-nearby-vendor?xpoint=100&ypoint=30&type=MEDICINE";
		String respose=HttpclientUtil.httpGetRequest(url);
		System.out.println(respose);
		
		
		
		/*url="http://api.ehuu.com/site-api/get-app-indexweb?shopCode=503";
		String respose=HttpclientUtil.httpGetRequest(url);
		System.out.println(respose);*/
		
	}
	
	//@Test
	public void dianpuTest()
	{
		
		
		
	}

}
