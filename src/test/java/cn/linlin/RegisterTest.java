package cn.linlin;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.httpframe.httpframe.HttpclientUtil;

import cn.utils.JdbcUtil;
import cn.utils.UrlProperties;




public class RegisterTest {
	
	
	
    
	
	private  static String register_url;
	@BeforeClass
	public void BeforeClass()
	{
		
	}
	@AfterClass
	public void AfterClass()
	{
		
		JdbcUtil.deleteUser("18244281100");
          
    }  
		
		
	
	
	 
	@Test
	public void registerTest()
	{
		register_url=UrlProperties.getValue("URL")+"/site-api/registuser";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("areaId", 0);
		map.put("comfirmPwd", "hyf1");
		map.put("dbSgCode", "hyf1");
		map.put("identityCode", 0);
		map.put("lastLoginAddress", "192.168.10.1");
		map.put("logo", "000");
		map.put("password", "hyf1");
		map.put("phone", "18244281100");
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
			assertEquals(true, matcher.find());
			//System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log(e.toString());
		}
	}

}
