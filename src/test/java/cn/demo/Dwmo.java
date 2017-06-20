package cn.demo;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.ParseException;
import org.apache.http.client.protocol.HttpClientContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.httpframe.httpframe.HttpclientUtil;

import cn.utils.OperationUtils;
import cn.utils.UrlProperties;

public class Dwmo {
	
static	HttpClientContext content=new HttpClientContext();
	
	@BeforeClass
	public void beforeclass()
	{

		String	url=UrlProperties.getValue("URL")+ "/site-api/userlogin";
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
			result= HttpclientUtil.httpPostRequest(url, jsonString,content);
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
	@Test
	public void test1()
	{
		String url1=UrlProperties.getValue("URL")+"/site-api/getrebatedetail?pageNo=1&pageSize=10";
		String string=HttpclientUtil.httpGetRequest(url1, content);
		System.out.println(string);
		
		
	}
	
	
	public static void main(String[] args) {
		
		
		
		String	url=UrlProperties.getValue("URL")+ "/site-api/userlogin";
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
			result= HttpclientUtil.httpPostRequest(url, jsonString,content);
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
		String url1=UrlProperties.getValue("URL")+"/site-api/getrebatedetail?pageNo=1&pageSize=10";
		String string=HttpclientUtil.httpGetRequest(url1, content);
		System.out.println(string);
		
		
		
	}

}
