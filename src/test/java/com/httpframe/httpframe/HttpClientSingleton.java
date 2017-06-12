package com.httpframe.httpframe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.util.internal.SystemPropertyUtil;

public class HttpClientSingleton {
	
	

	/** 日志文件处理 **/
	private static Logger LOG = LoggerFactory.getLogger(HttpclientUtil.class);
	private static CookieStore cookieStore = null;
	private static HttpClientContext context = null;
	private static CloseableHttpClient httpClient = null;
	private static CloseableHttpResponse response = null;
	private static String EMPTY_STR = "";
	private static String encode = "UTF-8";
	private static RequestConfig requestConfig;
	/**
	 * 初始化链接
	 * 
	 * @return
	 */
	
	private static CloseableHttpClient getHttpClient() {
		
		
		
	
		if(cookieStore==null){
			return HttpClients.createDefault();
		}
		else
		{
			
			System.out.println(cookieStore.getCookies());
			return HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			
		}
	
		
	}

	
	
/*
	private static CloseableHttpClient getHttpClientHttps() {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(cm)
				.setDefaultRequestConfig(requestConfig).build();

		return httpClient;
	}

	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {

			SSLContext sslContext = new org.apache.http.ssl.SSLContextBuilder()
					.loadTrustMaterial(null, new TrustStrategy() {
						
						public boolean isTrusted(X509Certificate[] chain,
								String authType)
								throws java.security.cert.CertificateException {
							// TODO Auto-generated method stub
							return false;
						}
					}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext,
					new X509HostnameVerifier() {

						
						public boolean verify(String arg0, SSLSession arg1) {
							return true;
						}

						
						public void verify(String host, SSLSocket ssl)
								throws IOException {
						}

						
						public void verify(String host, X509Certificate cert)
								throws SSLException {
						}

						
						public void verify(String host, String[] cns,
								String[] subjectAlts) throws SSLException {
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sslsf;
	}
*/
	private static String readZipPath(InputStream inputStream, String path)
			throws Exception {
		File file = new File(path);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		byte[] data = new byte[2048];
		int n = -1;
		while ((n = inputStream.read(data)) != -1) {
			fileOutputStream.write(data, 0, n);
			fileOutputStream.flush();
		}
		fileOutputStream.close();
		return path;
	}

	/**
	 * 无参数get请求获取结果
	 * 
	 * @param url
	 *            访问url
	 * @return
	 */
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
	/*
	 * public static String httpPostRequest(String url, Map<String, Object>
	 * headers, Map<String, Object> params,List<String> keys) throws
	 * UnsupportedEncodingException{ HttpPost httpPost = new HttpPost(url);
	 * 
	 * for (Map.Entry<String, Object> param: headers.entrySet()) {
	 * httpPost.addHeader(param.getKey(), param.getValue()+""); }
	 * 
	 * ArrayList<NameValuePair> pairs = covertParams2NVPS(params,keys);
	 * httpPost.setEntity(new UrlEncodedFormEntity(pairs, encode));
	 * 
	 * return getResult(httpPost); }
	 *//**
	 * map 转请求参数对象
	 * 
	 * @param params
	 * @return

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

	/*
	 * 设置回话保持
	 */
	public static void setCookieStore() {
		System.out.println("----setCookieStore");
		cookieStore = new BasicCookieStore();
		// JSESSIONID
		String setCookie = response.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID1= setCookie.substring("route=".length(), setCookie.length());
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.length());
		//String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
		System.out.println("JSESSIONID:" + JSESSIONID);
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID1);
		
		cookie.setVersion(0);
		cookie.setDomain(".ehuu.com");
		cookie.setPath("/");
		
		
		/* cookie = new BasicClientCookie("route", JSESSIONID);
		
		cookie.setVersion(0);
		cookie.setDomain("api.ehuu.com");
		cookie.setPath("/");*/
		// cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
		// cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
		// cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
		// cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
		cookieStore.addCookie(cookie);
		
	}
	
	
	/*
	 * 
	 */
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
	
	
	/**
	 * http 请求结果处理
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		URI uri = request.getURI();
		String strUrl = uri.toString();
		if (strUrl.startsWith("http://")) {
			httpClient = getHttpClient();
			// httpClient=HttpClients.createDefault();
		} else {
			//httpClient = getHttpClientHttps();
		}
		
		try {
			
			response = httpClient.execute(request);
			//setCookieStore(response);
			//setContext();
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, "utf-8");
				return result;
			} else {
				return status+"";
			}
//			if (0 == epargs.length) {
//				if (entity != null) {
//					String result = EntityUtils.toString(entity, "utf-8");
//					return status;
//				}
//			} else {
//				InputStream inputStream = entity.getContent();
//				return readZipPath(inputStream, epargs[0]);
//			}
		} catch (ClientProtocolException e) {
			LOG.error("httpclient Protocol异常,error:", e);
		} catch (Exception e) {
			LOG.error("httpclient 读取数据异常,error:", e);
			e.printStackTrace();
		} finally {
			if (null != response)
				try {
					response.close();
				} catch (Exception e) {
					LOG.error("CloseableHttpResponse 关闭异常,error:", e);
				}
		}
		return EMPTY_STR;
	}
}

/*class TrustAnyTrustManager implements X509TrustManager {
	
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}*/
	

