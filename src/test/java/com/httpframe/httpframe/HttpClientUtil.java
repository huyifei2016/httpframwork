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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.handler.codec.http.HttpContent;
import com.httpframe.httpframe.JsonUtil;
public class HttpClientUtil {

	
	/** 日志文件处理 **/
	private static Logger LOG = LoggerFactory.getLogger(HttpclientUtil.class);
	/** 连接池配置 **/
	private static PoolingHttpClientConnectionManager cm;
	private static String EMPTY_STR = "";
	private static String encode = "UTF-8";
	private static RequestConfig requestConfig;
	private static CloseableHttpClient httpClient = null;
	private static List<String> JSESSIONID=new ArrayList<String>();

	/**
	 * 初始化链接
	 * 
	 * @return
	 */
	
	static {
		try {
			System.setProperty("javax.Net.ssl.trustStore",
					"E:/ok/maven.1479175127877/cms-all/cms-base/cms-base-common/jssecacerts");
			cm = new PoolingHttpClientConnectionManager();

			//String maxTotal = HttpframeProperties
			//		.getPropertiesValue("httpclient.MaxTotal");
			String maxTotal=HttpframeProperties.getPropertiesValue("httpclient.MaxTotal");
			if (maxTotal != null) {
				cm.setMaxTotal(Integer.parseInt(maxTotal));
			}
			String defaultMaxPerRoute = HttpframeProperties
					.getPropertiesValue("httpclient.DefaultMaxPerRoute");
			if (defaultMaxPerRoute != null) {
				cm.setDefaultMaxPerRoute(Integer.parseInt(defaultMaxPerRoute));
			}
			encode = HttpframeProperties
					.getPropertiesValue("httpclient.encode");
			RequestConfig.Builder configBuilder = RequestConfig.custom();
			// 设置连接超时
			configBuilder.setConnectTimeout(Integer.parseInt(maxTotal));
			// 设置读取超时
			configBuilder.setSocketTimeout(20 * 600);
			// 设置从连接池获取连接实例的超时
			configBuilder.setConnectionRequestTimeout(20 * 600);
			// 在提交请求之前 测试连接是否可用
			configBuilder.setStaleConnectionCheckEnabled(true);
			requestConfig = configBuilder.build();

		} catch (Exception e) {
			LOG.error("httpclient池创建失败,error:", e);
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		
		
			return HttpClients.custom().setConnectionManager(cm).build();
	
		
	}

	

	

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
	public static String httpGetRequest(String url,HttpClientContext content) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet, content);
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
	public static String httpGetRequest(String url, Map<String, Object> headers,HttpClientContext context)
			throws URISyntaxException {
		HttpGet httpGet = new HttpGet(url);
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), param.getValue() + "");
		}
		return getResult(httpGet,context);
	}

	/**
	 * 无参数的post请求
	 * 
	 * @param url
	 * @return
	 */
	public static String httpPostRequest(String url,HttpClientContext context) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost,context);
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
	public static String httpPostRequest(String url, Map<String, Object> params,HttpClientContext context)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, encode));
		return getResult(httpPost,context);
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
	public static String httpPostRequest(String url, String json,HttpClientContext context)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(json, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		return getResult(httpPost,context);
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
			Map<String, Object> headers, Map<String, Object> params,HttpClientContext context)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpPost.addHeader(param.getKey(), param.getValue() + "");
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, encode));

		return getResult(httpPost,context);
	}

	
	private static ArrayList<NameValuePair> covertParams2NVPS(
			Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()
					+ ""));
		}
		return pairs;
	}

	
	
	
	/**
	 * http 请求结果处理
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request,HttpClientContext context) {
		URI uri = request.getURI();
		String strUrl = uri.toString();
		HttpResponse	response;
		String result="";
		List<String> li=new ArrayList<String>();
			
		if (strUrl.startsWith("http://")) {
			httpClient = getHttpClient();
			
		} else {
			httpClient = getHttpClientHttps();
		}
		
		try {
			response= httpClient.execute(request,context);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {

				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "utf-8");
				return result;
			} else {
				
				return null;
			}

		} catch (ClientProtocolException e) {
			LOG.error("httpclient Protocol异常,error:", e);
		} catch (Exception e) {
			LOG.error("httpclient 读取数据异常,error:", e);
			e.printStackTrace();
		} finally {
		
		}
		return result;
	}


class TrustAnyTrustManager implements X509TrustManager {
	
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}
}
}