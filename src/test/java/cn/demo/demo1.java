package cn.demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.testng.TestNG;
import com.httpframe.httpframe.HttpclientUtil;
import com.twitter.conversions.thread;


public class demo1 {
private static String  url;
private String loginUrl = "http://api.ehuu.com/site-api/userlogin";
private String testUrl = "http://api.ehuu.com/site-api/myorders/getMyOrdersList?orderStatus=&pageNo=1&pageSize=1";

private static HttpContext localcontext=new BasicHttpContext();

private static HttpClientContext context = HttpClientContext.adapt(localcontext);
@BeforeClass
public void BeforeClass()
{
	

}
	
	/*
	 * 
	 * {
  "areaId": "string",
  "chainType": 0,
  "cookieExpireTime": 0,
  "cookieId": "string",
  "customerId": 0,
  "dbSgCode": "string",
  "imToken": "string",
  "isRealname": true,
  "isShopEmployee": true,
  "isVendor": true,
  "lastLoginAddress": "string",
  "logo": "string",
  "nickName": "string",
  "openRebate": true,
  "password": "string",
  "phone": "string",
  "productManagement": true,
  "registerChannel": "string",
  "sessionId": "string",
  "status": "string",
  "userType": "string",
  "xPoint": 0,
  "yPoint": 0
}
	 * 
	 */
@Test	
public void fuction1(){
	url="http://api.ehuu.com/site-api/userlogin";
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("loginName", "18244281100");
	map.put("password", "hyf1");
	String jsonString = JSON.toJSONString(map);
	try {
		String string = HttpclientUtil.httpPostRequest(url, jsonString,context);
		//System.out.println(context.getCookieStore().getCookies().toString());
		String regex="SUCCEED";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(string);
		assertEquals(true, matcher.find());
		//Assert.assertEquals("201", string);
		System.out.println(string);
	//	String string1=HttpclientUtil.httpGetRequest(url1);
		//System.out.println(string1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Reporter.log(e.toString());

	}
	
	
	
}
///add-autorealnameauth

//@Test(dependsOnMethods = { "fuction1" }) 
@Test
public  void renzheng() {
	
	
	String string=HttpclientUtil.httpGetRequest(testUrl,context);
	System.out.println(string);
	
	
	
	 //client= 

	    //HttpGet httpGet = new HttpGet(loginUrl);
	
	
	/*HttpGet httpGet = new HttpGet(testUrl);
    HttpResponse httpResponse1;
	try {
		httpResponse1 = client.execute(httpGet);
	    HttpEntity entity2=httpResponse1.getEntity();
	    String result1=EntityUtils.toString(entity2, "utf-8");
	    System.out.println(result1);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

	
	
	
	
	
	// TODO Auto-generated method stub
	
	/*
	 * {
  "backendImg": "string",
  "bankCard": "string",
  "cellphone": "string",
  "customerId": 0,
  "frondImg": "string",
  "holdersImg": "string",
  "idCard": "string",
  "name": "string",
  "status": 0,
  "type": 0
}
	 *
	 
	url="http://api-dev.ehuu.com/site-api/add-mwrealnameauth";
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("backendImg", "");
	//map.put("bankCard", "");
	//map.put("cellphone", "");
	map.put("customerId", "4374712");
	map.put("frondImg", "");
	map.put("holdersImg", "");
	map.put("name", "zhangsan");
	map.put("idCard", "51012549874521");
	//map.put("type", "");
	//String jsonString = JSON.toJSONString(map);
	try {
		String string = HttpclientUtil.httpPostRequest(url, map);
		//Assert.assertEquals("201", string);

	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Reporter.log(e.toString());

	}*/
	

}
/*
 * {
  "{
  "advertId": 0,
  "amount": 0,
  "customerId": "string",
  "groupId": "string",
  "number": 0,
  "password": "string",
  "redPackName": "string",
  "type": 0
}
}
 */

public void LoginTest()
{
	url="http://api-dev.ehuu.com/site-api/redpack/create-redpack";
	//url="http://api.ehuu.com/site-api/find-all-product";
	HashMap<String, Object> map = new HashMap<String, Object>();
	

	map.put("advertId", "");
	map.put("amount", "10");
	map.put("customerId", "4374762");
	map.put("groupId", "123");
	map.put("number", "10");
	map.put("password", "B19A003EF8D8E10F1FEB610B3E8259BB");
	map.put("redPackName", "hyf1");
	map.put("type", "0");

	

	String jsonString = JSON.toJSONString(map);


}

/*
 * 
 * {
  "advertId": 0,
  "amount": 0,
  "customerId": "string",
  "groupId": "string",
  "number": 0,
  "password": "string",
  "redPackName": "string",
  "type": 0
}
 * 
 * 
 */

public  void chuangjian()
{
	



}




//@Test
public void regiester()
{
	
	url="http://api.ehuu.com/site-api/registuser";
	/*
	 * {
  "areaId": 0,
  "comfirmPwd": "string",
  "dbSgCode": "string",
  "identityCode": "string",
  "lastLoginAddress": "string",
  "logo": "string",
  "password": "string",
  "phone": "string",
  "provinceCity": "string",
  "rebatType": "string",
  "registerChannel": "string",
  "sex": "string",
  "userName": "string",
  "userType": "string",
  "vectorCode": "string",
  "xPoint": 0,
  "yPoint": 0
}
	 */
	
	
for(int i=100;i<=100;i++)
{
	/*try {
		Thread.sleep(10000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("areaId", 0);
	map.put("comfirmPwd", "hyf1");
	map.put("dbSgCode", "hyf1");
	map.put("identityCode", 0);
	map.put("lastLoginAddress", "192.168.10.1");
	map.put("logo", "000");
	map.put("password", "hyf1");
	map.put("phone", "18244281"+i);
	map.put("provinceCity", "chengdu");
	map.put("rebatType", "0");
	map.put("registerChannel", "0");
	map.put("sex", "0");
	map.put("userName","yifeihu"+i);
	map.put("userType", "0");
	map.put("vectorCode","0");
	map.put("xPoint", 0);
	map.put("yPoint", 0);
	
	
}

}

	
	
	
}
