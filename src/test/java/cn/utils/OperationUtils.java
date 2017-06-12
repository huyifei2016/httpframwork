package cn.utils;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.crypto.ec.ECNewPublicKeyTransform;
import org.testng.Reporter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.httpframe.httpframe.HttpclientUtil;



public class OperationUtils {
	private static HttpContext localcontext=new BasicHttpContext();
	private static HttpClientContext context = HttpClientContext.adapt(localcontext);
	private static String url="";
	private static Properties properties=null;
	static{
		properties=new Properties();
		try {
			properties.load(OperationUtils.class.getResourceAsStream("../../url.properties"));
			url=properties.getProperty("URL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	//登录
	
	public static boolean login1(String phone)
	{
	
		String url_login=url+"/site-api/userlogin";
		String regex="SUCCEED";
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url_login);
		Matcher matcher=null;
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("loginName", phone);
		jsonObject.put("password", "hyf1");
		StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");  
	        entity.setContentEncoding("UTF-8");    
	        entity.setContentType("application/json");    
	        httpPost.setEntity(entity);
		String result;
		
		try { 
			
			HttpResponse response=client.execute(httpPost);
			HttpEntity entity2=response.getEntity();
			result=EntityUtils.toString(entity2, "utf-8");
			System.out.println(result);
			Pattern pattern=Pattern.compile(regex);
			matcher=pattern.matcher(result);
			if(!matcher.find())
			{
				System.out.println("fail!!!");
				
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log(e.toString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return matcher.find();
		
	}
	
	//注册
	public static boolean register(String phone)
	{
		String register_url=url+"/site-api/registuser";
		boolean flog=true;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("areaId", 0);
		map.put("comfirmPwd", "hyf123456");
		map.put("dbSgCode", "hyf123456");
		map.put("identityCode", 0);
		map.put("lastLoginAddress", "192.168.10.1");
		map.put("logo", "000");
		map.put("password", "hyf123456");
		map.put("phone", phone);
		map.put("provinceCity", "chengdu");
		map.put("rebatType", "0");
		map.put("registerChannel", "0");
		map.put("sex", "0");
		map.put("userName","yifeihu");
		map.put("userType", "0");
		map.put("vectorCode","0");
		map.put("xPoint", 0);
		map.put("yPoint", 0);
		String jsonString = JSON.toJSONString(map);
		String regex="SUCCEED";
		try {
			String result=HttpclientUtil.httpPostRequest(register_url, jsonString,null);
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher=pattern.matcher(result);
			if(!matcher.find())
			{
				
				flog=false;
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return flog;
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("dd");
	}
	
}
