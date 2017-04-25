package cn.demo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.httpframe.httpframe.HttpclientUtil;

public class StatelessConnection {

	private static HttpClientContext context = null;
	private static CookieStore cookieStore = null;
	private static String url;

	

	@Test(priority=0)
	public void loginTest() {

		url = "http://api.ehuu.com/site-api/userlogin";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", "18244281624");
		map.put("password", "hyf123");

		String jsonString = JSON.toJSONString(map);
		try {
			String string = HttpclientUtil.httpPostRequest(url, jsonString);
			// Assert.assertEquals("201", string);
			System.out.println(string);
			

			url="http://api.ehuu.com/site-api/myorders/getMyOrdersList?orderStatus=&pageNo=1&pageSize=1";
			String string1=HttpclientUtil.httpGetRequest(url);
			System.out.println(string1);
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log(e.toString());

		}
		
		
		
		
		

	}
	
	@Test(priority=1)
	public void yuyueTest()
	{
		
		
		
		
	}

}
