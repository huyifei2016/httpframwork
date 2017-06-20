package cn.linlin.shop;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.httpframe.httpframe.HttpclientUtil;
import com.httpframe.httpframe.JsonUtil;
import cn.utils.JdbcUtil;
import cn.utils.OperationUtils;

public class ShopController {
	
	
	
	private static String url;
	private static String phone="18244281300";
	private static String customid="";
	private static CloseableHttpClient client=HttpClients.createDefault();
	private static HttpClientContext context=new HttpClientContext();
	private static CookieStore cookiestore=new BasicCookieStore();
	@BeforeClass
	public void beforeClass()
	{
		//JdbcUtil.deleteUser(phone);
		//OperationUtils.register(phone);
		customid=JdbcUtil.gainCustomid(phone);
		
		
		
	}
	
	
	
	
	//http://192.168.10.21/shop/create-newshop
	//单体店铺
	
	/*
	 * {
  "address": "string",
  "areaId": 0,
  "categoryId": 0,
  "cityName": "string",
  "closeTime": "string",
  "customerId": 0,
  "customerName": "string",
  "dbGroups": [
    {
      "groupCode": "string",
      "vectorCode": "string"
    }
  ],
  "description": "string",
  "logoPath": "string",
  "openTime": "string",
  "parentCategoryId": 0,
  "phoneNum": "string",
  "shopId": 0,
  "shopName": "string",
  "streetId": 0,
  "tag": "string",
  "vendorType": "string",
  "xpoint": "string",
  "ypoint": "string"
}

	 */
	
	
	
	@Test
	public void newshopTest()
	{
		
		
		url="http://api.ehuu.com/site-api/shop/create-newshop";
		JSONObject jsonObject=new JSONObject();
		HttpPost httpPost=new HttpPost(url);
		jsonObject.put("address", "北京市");
		jsonObject.put("areaId", "221");
		jsonObject.put("categoryId", "5");
		jsonObject.put("cityName", "北京");
		jsonObject.put("closeTime", "");
		jsonObject.put("customerId", customid);
		jsonObject.put("customerName", "name");
		jsonObject.put("description", "1111");
		jsonObject.put("shopId", "111");
		jsonObject.put("shopName", "测试单体店铺");
		jsonObject.put("parentCategoryId", "0");
		StringEntity stringEntity=new StringEntity(jsonObject.toString(),"utf-8");
		stringEntity.setContentType("application/json");  
		httpPost.setEntity(stringEntity);
		//map.put("", "");
		String regex="SUCCEED";
		try {
			
			HttpResponse response=client.execute(httpPost);
			HttpEntity entity=response.getEntity();
			String result=EntityUtils.toString(entity, "utf-8");
			System.out.println(result);
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher=pattern.matcher(result);
			assertEquals(true, matcher.find());
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterClass()
	{
		
		
	}
}
