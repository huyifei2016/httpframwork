package com.httpframe.httpframe;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

public class ResultString {

	public static String url_1 = "http://hadooptest05.chinacloudapp.cn/t.gif?_=1471414863144";
	// public static String cmd="lvpl";
	public static String cmd = "1";
	// public static String cmd="";
	public static String vd = "1";
	public static String appid = "900000";
	public static String von = "%E5%BC%83%E5%AD%90%E7%9A%84%E8%89%BA%E6%9C%AF2";
	public static String n = "%E5%BC%83%E5%AD%90%E7%9A%84%E8%89%BA%E6%9C%AF2";
	public static String ch = "%E5%9B%B4%E6%A3%8B%E5%A4%A9%E5%85%83";
	public static String wch = "%E5%9B%B4%E6%A3%8B%E5%A4%A9%E5%85%83";
	public static String tg = "";
	public static String cdn = "";
	public static String s = "";
	public static String sn = "0";
	public static String pu = "%252FC%253A%252FUsers%252Fcuidalian%252FDesktop%252FBigDataCollector_JS-master%252Ftest.html";
	public static String vurl = "http%253A%252F%252Fceshi-219.chinacloudapp.cn%253A8082%252FLoveMore.mp";
	public static String t = "%25E8%258E%25B7%25E5%258F%2596%25E8%25A7%2586%25E9%25A2%2591%25E4%25BF%25A1%25E6%2581%25AF";
	public static String bs = "Firefox";
	public static String os = "os";
	public static String pf = "10.0.0";
	public static String at = "other";
	public static String dr = "0";
	public static String lt = "0.097";
	public static String st = "100";
	public static String sd = "10";
	public static String pt = "10";
	public static String rpt = "10";
	public static String stay = "0.104";
	public static String tpt = "0.104";
	public static String uid = "8d1bb21bc6ce85b7591b2f214a9f3771";
	public static String ver = "1.0.0";

	public static String uid_collect() {

		Random random = new Random();

		return StringMD5.string2MD5(String.valueOf(random.nextInt()));

	}
	
	
	
	public static String log_string_jslb()
	{
	
		
		
		
		
		
	}

	public static String log_string_xin(String[] ar, String sn, String nu,String vid,String von,String uidd ,String url_1) {
		Random random=new Random();
		//String uidd=StringMD5.string2MD5(String.valueOf(new Date().getTime())  );
		String appid = ar[0];
		String ip = ar[2];
		String isp = ar[3];
		String aString = ar[1];
		String url1 = "http://hadooptest05.chinacloudapp.cn/t.gif?_=1478141027296&appid=";
		String url2 = "&cmd=0&vid=";
		String uln1="&von=";
		String nln2="&ch=1&wch=1&tg=1&cdn=1&s=61c6b6b45fcf02b0b0b5fa724f144f90&sn=";
		String url3 = "&ref=&pu=localhost%252Ftest.html&vurl=";
		String url4="&t=%25E8%258E%25B7%25E5%258F%2596%25E8%25A7%2586%25E9%25A2%2591%25E4%25BF%25A1%25E6%2581%25AF&bs=Firefox&os=10.0.0&pf=Windows&at=other&dr=245&st=1&sd=10&lt=1.883&stay=8.183&pt=10&rpt=10&";
		String url5 = "&tpt=8.183&ver=1.0.9&";
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(url1);
		sBuffer.append(appid);
		sBuffer.append(url2);
		sBuffer.append(vid);
		sBuffer.append(uln1);
		sBuffer.append(URLEncoder.encode(von)).append("&");
		sBuffer.append("n=");
		sBuffer.append(URLEncoder.encode(von));
		sBuffer.append(nln2);
		sBuffer.append(sn);
		sBuffer.append(url3);
		sBuffer.append(url_1);
		sBuffer.append(url4);
		sBuffer.append("uid=").append(uidd);
		sBuffer.append(url5);
		sBuffer.append("ip=").append(ip).append("&");
		sBuffer.append("a=").append(aString).append("&");
		sBuffer.append("isp=").append(isp).append("&");
		sBuffer.append("nu=").append(nu);
		return sBuffer.toString();
	}

	public static String log_String() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(url_1).append("&");
		sBuffer.append("appid=").append(appid).append("&");
		sBuffer.append("cmd=").append(cmd).append("&");
		sBuffer.append("s=").append(s).append("&");
		sBuffer.append("vb=").append(vd).append("&");
		sBuffer.append("von=").append(von).append("&");
		sBuffer.append("n=").append(n).append("&");
		sBuffer.append("ch=").append(ch).append("&");
		sBuffer.append("wch=").append(wch).append("&");
		sBuffer.append("tg=").append(tg).append("&");
		sBuffer.append("cdn=").append(cdn).append("&");
		sBuffer.append("sn=").append(sn).append("&");
		sBuffer.append("pu=").append(pu).append("&");
		sBuffer.append("vurl=").append(vurl).append("&");
		sBuffer.append("t=").append(t).append("&");
		sBuffer.append("bs=").append(bs).append("&");
		sBuffer.append("os=").append(os).append("&");
		sBuffer.append("pf=").append(pf).append("&");
		sBuffer.append("at=").append(at).append("&");
		sBuffer.append("dr=").append(dr).append("&");
		sBuffer.append("lt=").append(lt).append("&");
		sBuffer.append("st=").append(st).append("&");
		sBuffer.append("sd=").append(sd).append("&");
		sBuffer.append("pt=").append(pt).append("&");
		sBuffer.append("rpt=").append(rpt).append("&");
		sBuffer.append("stay=").append(stay).append("&");
		sBuffer.append("tpt=").append(tpt).append("&");
		sBuffer.append("uid=").append(uid_collect()).append("&");
		sBuffer.append("ver=").append(ver).append("&");
		 //sBuffer.append("ip=").append().append("&");
		// sBuffer.append("a=").append(aString).append("&");
		// sBuffer.append("isp=").append(isp).append("&");
		// sBuffer.append("nu=").append(nu);
		return sBuffer.toString();
	}
};
