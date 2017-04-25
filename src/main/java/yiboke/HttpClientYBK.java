package yiboke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.common.collect.Range;
import com.httpframe.httpframe.HttpclientUtil;
import com.twitter.conversions.string;
import com.twitter.conversions.thread;

public class HttpClientYBK {

	/*
	 * http://tracker.otvcloud.com/collection.gif?
	 * appid=800000&msg=PAGE|直播频道页|未知|1487904755230|1487904756396|1487904757822|
	 * 0|93120|Denny|118|五星体育直播回看&topic=PAGE
	 */
	
	
	public static void access()
	{
	
		// String url="http://hadooptest05.chinacloudapp.cn/t.gif?";
		for (int i = 0; i <= 100; i++) {
			StringBuilder msg = new StringBuilder();
			msg.append("Android").append("|");
			msg.append(getUUID()).append("|");
			msg.append("微信朋友圈").append("|");
			msg.append("qundao1").append("|");
			msg.append("127.0.0.1").append("|");
			msg.append("四川").append("|");
			msg.append("电信");

			StringBuilder url_YBK = new StringBuilder();
			url_YBK.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			url_YBK.append("appid=900006&msg=");
			url_YBK.append(URLEncoder.encode(msg.toString()));
			url_YBK.append("&topic=USER_ACCESS");
			System.out.println(url_YBK.toString());

			String str = HttpclientUtil.httpGetRequest(url_YBK.toString());

		}
		
	}
	
	
	/*
	 * 用户访问呢数appid=800000&msg=PAGE|Android|用户id|微信朋友圈|渠道名|IP地址|地域信息|运营商信息|访问时间&
	 * topic=PAGE
	 */
	// app_install//APP_START
	
	public static void app_start(){
		

		
		for (int i = 0; i <= 100; i++) {
			StringBuilder msg_1 = new StringBuilder();
			msg_1.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			msg_1.append("appid=900006&msg=");
			msg_1.append(URLEncoder.encode("Android|"));
			msg_1.append(URLEncoder.encode(getUUID()+"|"));
			msg_1.append(URLEncoder.encode("qudao1"));
			msg_1.append("&topic=APP_START");
			System.out.println(msg_1.toString());
			String str = HttpclientUtil.httpGetRequest(msg_1.toString());
           System.out.println(str);
		}

	}
	
	
	
	public static void app_install() {

		
		for (int i = 0; i <= 100; i++) {
			StringBuilder msg_1 = new StringBuilder();
			msg_1.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			msg_1.append("appid=900006&msg=");
			msg_1.append(URLEncoder.encode("Android|"));
			msg_1.append(URLEncoder.encode("qudao1"));
			msg_1.append("&topic=APP_INSTALL");
			System.out.println(msg_1.toString());
			String str = HttpclientUtil.httpGetRequest(msg_1.toString());
           System.out.println(str);
		}

	}
	//USER_REGIST
	public static void USER_REGIST()
	{
		
		// String url="http://hadooptest05.chinacloudapp.cn/t.gif?";
		for (int i = 0; i <= 100; i++) {

			
			StringBuilder msg = new StringBuilder();
			msg.append("Android|");
			msg.append(getUUID()).append("|");
			msg.append("0").append("|");
			msg.append("22").append("|");
			msg.append("成都").append("|");
			msg.append("boke").append("|");
			msg.append("qudao1").append("|");
			msg.append("新浪");

			
			StringBuilder url_YBK = new StringBuilder();
			url_YBK.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			url_YBK.append("appid=900006&msg=");
			url_YBK.append(URLEncoder.encode(msg.toString()));
			url_YBK.append("&topic=USER_REGIST");
			System.out.println(url_YBK.toString());

			String str = HttpclientUtil.httpGetRequest(url_YBK.toString());
System.out.println(str);
		}
		
	}
	
	
	
	/*
	 * USER_USETIME
	 */
	@SuppressWarnings("deprecation")
	public static void user_UseTime()
	{
		
		

		
		for (int i = 0; i <= 100; i++) {
			StringBuilder msg_1 = new StringBuilder();
			msg_1.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			msg_1.append("appid=900006&msg=");
			msg_1.append(URLEncoder.encode("Android|"+getUUID()+"|"+"10|"+"qudao1"));
			msg_1.append("&topic=USER_USETIME");
			String str = HttpclientUtil.httpGetRequest(msg_1.toString());
			System.out.println(str);
		}

		
	}
	
	
	/*
	 * USER_COMMENTS
	 */
	public static void USER_COMMENTS()
	{
		
		
		
		

		// String url="http://hadooptest05.chinacloudapp.cn/t.gif?";
		for (int i = 0; i <= 10; i++) {
			StringBuilder msg = new StringBuilder();
			msg.append("Android").append("|");
			msg.append("滚播").append("|");
			msg.append(3333).append("|");
			msg.append(222).append("|");
			msg.append(getUUID()).append("|");
			msg.append("好评好评！").append("|");
			msg.append("qundao1");
			StringBuilder url_YBK = new StringBuilder();
			url_YBK.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			url_YBK.append("appid=900006&msg=");
			url_YBK.append(URLEncoder.encode(msg.toString()));
			url_YBK.append("&topic=USER_COMMENTS");
			System.out.println(url_YBK.toString());

			String str = HttpclientUtil.httpGetRequest(url_YBK.toString());
			
		}

	}
		
		
	
	/*
	 * USER_SHARE	 USER_RESHARE
	 */
	public static void USER_SHARE(String zhuti)
	{
		StringBuilder msg = new StringBuilder();
		msg.append("Android").append("|");
		msg.append("滚播").append("|");
		msg.append(3333).append("|");
		msg.append(222).append("|");
		msg.append(getUUID()).append("|");
		msg.append("微信").append("|");
		msg.append("qundao1");
		

		// String url="http://hadooptest05.chinacloudapp.cn/t.gif?";
		for (int i = 0; i <= 100; i++) {

			StringBuilder url_YBK = new StringBuilder();
			url_YBK.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			url_YBK.append("appid=900006&msg=");
			url_YBK.append(URLEncoder.encode(msg.toString()));
			url_YBK.append("&topic="+zhuti);
			System.out.println(url_YBK.toString());

			String str = HttpclientUtil.httpGetRequest(url_YBK.toString());

		}
		
		
	}

	/*
	 * USER_SUBSCRIBE
	 */
	
	public static void USER_SUBSCRIBE()
	{
		StringBuilder msg = new StringBuilder();
		msg.append("Android").append("|");
		msg.append("滚播").append("|");
		msg.append(3333).append("|");
		msg.append(222).append("|");
		msg.append(getUUID()).append("|");
		msg.append("qundao1");
		

		// String url="http://hadooptest05.chinacloudapp.cn/t.gif?";
		for (int i = 0; i <= 100; i++) {

			StringBuilder url_YBK = new StringBuilder();
			url_YBK.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			url_YBK.append("appid=900006&msg=");
			url_YBK.append(URLEncoder.encode(msg.toString()));
			url_YBK.append("&topic=USER_SUBSCRIBE");
			System.out.println(url_YBK.toString());

			String str = HttpclientUtil.httpGetRequest(url_YBK.toString());

		}
		
		
		
	}
	
	
	
	/*
	 * USER_WATCHTIME user_watchtime
	 */
	public static void USER_WATCHTIME()
	{
		
		StringBuilder msg = new StringBuilder();
		msg.append("Android").append("|");
		msg.append("滚播").append("|");
		msg.append(3333).append("|");
		msg.append(222).append("|");
		msg.append(getUUID()).append("|");
		msg.append(10).append("|");
		msg.append(600).append("|");
		msg.append("qundao1");
		

		// String url="http://hadooptest05.chinacloudapp.cn/t.gif?";
		for (int i = 0; i <= 100; i++) {

			StringBuilder url_YBK = new StringBuilder();
			url_YBK.append("http://hadooptest05.chinacloudapp.cn/collection.gif?");
			url_YBK.append("appid=900006&msg=");
			url_YBK.append(URLEncoder.encode(msg.toString()));
			url_YBK.append("&topic=USER_WATCHTIME");
			System.out.println(url_YBK.toString());

			String str = HttpclientUtil.httpGetRequest(url_YBK.toString());

		}
		
	}
	
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");

	}
	
	
	public static int UV(String str)
	{
		
		BufferedReader reader = null;
		List<String> list=null;
		try {
			
			 list = new ArrayList<String>();
			
			reader = new BufferedReader(new FileReader(new File(str)));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				boolean b = true;
				for (String s : list) {
					if(s.equals(line)) {
						b = false;
						break;
					}
				}
				if(b) {
					list.add(line);
				}
			}
			
			System.out.println(list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list.size();
	}

	public static void main(String[] args) {
		
		
		access();
		app_install();;
		app_start();
		USER_REGIST();
		user_UseTime();
		USER_SHARE("USER_SHARE");
		USER_SHARE("USER_RESHARE");
		USER_COMMENTS();
		USER_SUBSCRIBE();
		USER_WATCHTIME();
		
		
		

	}

}
