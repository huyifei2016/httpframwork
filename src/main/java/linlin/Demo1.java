package linlin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import io.netty.handler.codec.http.HttpContent;
import scala.annotation.meta.beanGetter;

public class Demo1 {
	private static HttpContext localcontext=new BasicHttpContext();
	
	 static CookieStore cookieStore = null;
	  static HttpClientContext context = HttpClientContext.adapt(localcontext);
	 //static CloseableHttpClient client=HttpClients.createDefault();
	 static  String loginUrl = "http://api.ehuu.com/site-api/userlogin";
	 static String testUrl = "http://api.ehuu.com/site-api/myorders/getMyOrdersList?orderStatus=&pageNo=1&pageSize=1";
	
	
	
	public static void a()
	{
		
		
		

	    HttpPost httpPost = new HttpPost(loginUrl);
	    CloseableHttpClient client=HttpClients.createDefault();
	    JSONObject jsonObject=new JSONObject();
	    jsonObject.put("loginName", "18244281624");
	    jsonObject.put("password", "hyf123");
	    StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");  
	        entity.setContentEncoding("UTF-8");    
	        entity.setContentType("application/json");    
	        httpPost.setEntity(entity);
	   
	    
	    try {
	      // 执行post请求
	      HttpResponse httpResponse = client.execute(httpPost,context);
	      HttpEntity entity1=httpResponse.getEntity();
	      String result=EntityUtils.toString(entity1, "utf-8");
	      System.out.println(result);
	     /* if (location != null && location.startsWith(loginErrorUrl)) {
	        System.out.println("----loginError");
	      }*/
	   
	      // 执行get请求
	    


	      // cookie store
	     // setCookieStore(httpResponse);
	      // context
	     // setContext();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		
		
	}
	
	public static void b()
	{
		
	/*	CloseableHttpClient client = HttpClients.custom()
	            .setDefaultCookieStore(cookieStore).build();
	    */
		
	    
		CloseableHttpClient client=HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(testUrl);
	    System.out.println("request line:" + httpGet.getRequestLine());
	    try {
	      // 执行get请求
	      HttpResponse httpResponse = client.execute(httpGet,context);
	      HttpEntity entity1=httpResponse.getEntity();
	      String result=EntityUtils.toString(entity1, "utf-8");
	      System.out.println(result);
	     /* System.out.println("context cookies:"
	          + context.getCookieStore().getCookies());*/
	      
	      
	      
	     //printResponse(httpResponse);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        // 关闭流并释放资源
	        client.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		a();
		b();
		
		
		
	}
	
	
	
	 public static void printResponse(HttpResponse httpResponse)  
	            throws ParseException, IOException {  
	        // 获取响应消息实体  
	        HttpEntity entity = httpResponse.getEntity();  
	        // 响应状态  
	        System.out.println("status:" + httpResponse.getStatusLine());  
	        System.out.println("headers:");  
	        HeaderIterator iterator = httpResponse.headerIterator();  
	        while (iterator.hasNext()) {  
	            System.out.println("\t" + iterator.next());  
	        }  
	        // 判断响应实体是否为空  
	        if (entity != null) {  
	            String responseString = EntityUtils.toString(entity);  
	            System.out.println("response length:" + responseString.length());  
	            System.out.println("response content:"  
	                    + responseString.replace("\r\n", ""));  
	        }  
	    }  
	  
	    public static void setContext() {  
	        System.out.println("----setContext");  
	        context = HttpClientContext.create();  
	        Registry<CookieSpecProvider> registry = RegistryBuilder  
	                .<CookieSpecProvider> create()  
	                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())  
	                .register(CookieSpecs.BROWSER_COMPATIBILITY,  
	                        new BrowserCompatSpecFactory()).build();  
	        context.setCookieSpecRegistry(registry);  
	        context.setCookieStore(cookieStore);  
	    }  
	  
	    public static void setCookieStore(HttpResponse httpResponse) {  
	        System.out.println("----setCookieStore");  
	        cookieStore = new BasicCookieStore();  
	        // JSESSIONID  
	        String setCookie = httpResponse.getFirstHeader("Set-Cookie")  
	                .getValue();  
	        String JSESSIONID = setCookie.substring("JSESSIONID=".length(),  
	                setCookie.indexOf(";"));  
	        System.out.println("JSESSIONID:" + JSESSIONID);  
	        // 新建一个Cookie  
	        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",  
	                JSESSIONID);  
	        cookie.setVersion(0);  
	        cookie.setDomain("127.0.0.1");  
	        cookie.setPath("/CwlProClient");  
	        // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");  
	        // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");  
	        // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");  
	        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");  
	        cookieStore.addCookie(cookie);  
	    }  
	  
	    public static List<NameValuePair> getParam(Map parameterMap) {  
	        List<NameValuePair> param = new ArrayList<NameValuePair>();  
	        Iterator it = parameterMap.entrySet().iterator();  
	        while (it.hasNext()) {  
	            Entry parmEntry = (Entry) it.next();  
	            param.add(new BasicNameValuePair((String) parmEntry.getKey(),  
	                    (String) parmEntry.getValue()));  
	        }  
	        return param;  
	    } 
	

}
