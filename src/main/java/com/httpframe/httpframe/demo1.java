package com.httpframe.httpframe;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.execchain.MainClientExec;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class demo1 {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		List params=new ArrayList();
		String url="http://ubuntu12-zxw.chinacloudapp.cn/otvcloud_ybk/php/getShareDetail.php";
		String urls="http://ssl.test.90iktv.com/jmake/api_version";
		HttpClient client=new DefaultHttpClient();
		HttpPost request=new HttpPost(url);
		params.add(new BasicNameValuePair("userId","10086"));
		params.add(new BasicNameValuePair("id","66913"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");
		request.setEntity(entity);
		HttpResponse response=client.execute(request);
		HttpEntity entity1=response.getEntity();
		System.out.println(entity1.getContentType());
		System.out.println(EntityUtils.toString(entity1));
		
		
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("userId","10086");
		map.put("id","66913");
		
		
		
		HttpclientUtil.httpGetRequest(url);
		String string=HttpclientUtil.httpPostRequest(url, map);	
		System.out.println(string);

		 String e1=HttpclientUtil.httpGetRequest(urls);

	  
		System.out.println(e1);
	}

}
