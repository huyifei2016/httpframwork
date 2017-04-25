package cn.linlin;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.httpframe.httpframe.HttpclientUtil;

public class ActionController {
	private static String url;

	@BeforeTest
	public void beforeTest() {
		url = "";

	}

	/**
	 * { "code": "string", "data": {}, "message": "string" }
	 */

	@Test
	public void createPivilege() {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", "");
		map.put("data", "");
		map.put("message", "");
		String jsonString = JSON.toJSONString(map);
		try {
			String string = HttpclientUtil.httpPostRequest(url, map);
			Assert.assertEquals("201", string);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log(e.toString());

		}

	}

	@Test
	public void deletePrivilege() {
	}

	@Test
	public void findPrivilege() {
	}

	@Test
	public void getPrivilege() {
	}

	@Test
	public void updatePrivilege() {
	}

}
