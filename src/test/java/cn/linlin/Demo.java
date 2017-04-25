package cn.linlin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.httpframe.httpframe.HttpclientUtil;
import com.twitter.conversions.string;

public class Demo {
	
	
	
	 public static Map<String, Object>   convertJsonStrToMap(String jsonStr){
	        
	        Map<String, Object> map = JSON.parseObject(
	                jsonStr,new TypeReference<Map<String, Object>>(){} );
	        
	        return map;
	    }
	
	/*public static void main(String[] args) {
		
		
		demo_url="http://localhost:3000/posts/1";
		String ressult=HttpclientUtil.httpGetRequest(demo_url);
		Map<String, Object> map=convertJsonStrToMap(ressult);
		for(Map.Entry<String, Object> en:map.entrySet())
		{
			
			System.out.println("key:"+en.getKey()+"   value:"+en.getValue());
			
		}
	}*/
	/*
	 * post1
	 */
	public static String demo_url="";
	
	@BeforeClass
	public void BeforeTest()
	{
		demo_url="http://localhost:3000/posts/1";
		
	}
	
	@Test(groups="functest")
	public void test1()
	{
	
		
	String result=HttpclientUtil.httpGetRequest(demo_url);
	HashMap<String, Object> mm=(HashMap<String, Object>) convertJsonStrToMap(result);
	//assertEquals("json-server", mm.get("title"), "success!!");
	//System.out.println("eeeeeeee");
	fail("fail!!!!");
		
		
		
		
		
		
	}

	public static void json2Map(){ 
		//Map -> JSON 
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("a","ww"); 
		map.put("b","ee"); 
		map.put("c","rr"); 
		String json = JSON.toJSONString(map,true); 
		System.out.println(json); 
		//JSON -> Map 
		Map<String,String> map1 = (Map<String,String>)JSON.parse(json); 
		for (String key : map1.keySet()) { 
			System.out.println(key+":"+map1.get(key)); 
		} 
	} 

}
