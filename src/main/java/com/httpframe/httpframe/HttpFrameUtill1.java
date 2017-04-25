package com.httpframe.httpframe;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

public class HttpFrameUtill1 {
	
	
	
	
	
	public static String httpGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	/**
	 * 存在header的请求
	 * 
	 * @param url
	 *            访问的url
	 * @param headers
	 *            头信息
	 * @param params
	 *            参数信息
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url, Map<String, Object> headers)
			throws URISyntaxException {
		HttpGet httpGet = new HttpGet(url);
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), param.getValue() + "");
		}
		return getResult(httpGet);
	}

	/**
	 * 无参数的post请求
	 * 
	 * @param url
	 * @return
	 */
	public static String httpPostRequest(String url) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}

	/**
	 * 有参数的post请求
	 * 
	 * @param url
	 *            请求的url
	 * @param params
	 *            参数值
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, encode));
		return getResult(httpPost);
	}

	/**
	 * 有参数的post请求
	 * 
	 * @param url
	 *            请求的url
	 * @param params
	 *            参数值
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, String json)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(json, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		return getResult(httpPost);
	}

	/**
	 * 带头信息的post 请求
	 * 
	 * @param url
	 *            请求url
	 * @param headers
	 *            头信息请求
	 * @param params
	 *            请求参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url,
			Map<String, Object> headers, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpPost.addHeader(param.getKey(), param.getValue() + "");
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, encode));

		return getResult(httpPost);
	}
	
	
	/**
	 * map 转请求参数对象
	 * 
	 * @param params
	 * @return
	 */
	private static ArrayList<NameValuePair> covertParams2NVPS(
			Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()
					+ ""));
		}
		return pairs;
	}
	
	
	private static String getRe1sult()
	{
		
		
		
		return null;
		
		
	}
	

}
