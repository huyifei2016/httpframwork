package cn.demo;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.httpframe.httpframe.HttpclientUtil;
import com.twitter.conversions.string;

public class Relevance {

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
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log(e.toString());

		}

	}
	
	@Test(priority=1)
	public void jiaruyuyue()
	{
		
		url="http://api.ehuu.com/site-api/shoppingcart/add-shopping-cart?session=&barcode=501-31232131231&quantity=1&shopCode=501";
		try {
			
			String string=HttpclientUtil.httpGetRequest(url);
			System.out.println(string);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		
	}
	
	

}
