package cn.linlin;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.httpframe.httpframe.HttpclientUtil;

import cn.utils.JdbcUtil;
import cn.utils.OperationUtils;
import cn.utils.UrlProperties;

public class LoginTest {
	private static  String url;
	private static String phone="18244281200";
	
	@BeforeClass
	public void beforeClass()
	{
		OperationUtils.register(phone);
		
	}
	@AfterClass
	public void afterClass()
	{
		
	JdbcUtil.deleteUser(phone);
		
	}
	/*
	 * 
	 */
	@Test
	public void loginTestBuyer()
	{
		url=UrlProperties.getValue("URL")+ "/site-api/userlogin";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", "18244281200");
		map.put("password", "hyf123456");
		map.put("xPoint", "104.056000");
		map.put("yPoint", "30.550700");
		map.put("lastLoginAddress", "");
		map.put("rememberMe", "true");
		String regex="SUCCEED";
		String jsonString = JSON.toJSONString(map);
		String result;
		try {
			result= HttpclientUtil.httpPostRequest(url, jsonString,null);
			System.out.println(result);
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher=pattern.matcher(result);
			assertEquals(true, matcher.find());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log(e.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	

}
