package restassuied;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.bouncycastle.crypto.engines.Grainv1Engine;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class Shop {
	
	
	
	
	@BeforeClass
	public void BeforeClass()
	{
		
		
		
	}
	
	@Test
	public void ShopidTest()
	{

		get("http://192.168.10.21/site-api/find-all-category?shopCode=24").then()
		.body("message", equalTo("SUCCEED"));
		
		
	}
	@Test
	public void find_all_category_Test()
	{
	String result=get("http://192.168.10.21/site-api/find-all-category?shopCode=24").asString();
	get("http://192.168.10.21/site-api/find-all-category?shopCode=24").then()
		.body("message", equalTo("SUCCEED"));
	System.out.println(result);
	}
	@Test
	public void get_nearby_vendor()
	{
		String result=get("http://192.168.10.21/site-api/get-nearby-vendor?xpoint=104.629457&ypoint=30.130075&type=CLINIC").asString();
		get("http://192.168.10.21/site-api/get-nearby-vendor?xpoint=104.629457&ypoint=30.130075&type=CLINIC").then()
			.body("message", equalTo("SUCCEED"));
		System.out.println(result);
	}
	@Test
	public void find_shopplan_products_Test()
	{
		String result=get("http://192.168.10.21/site-api/find-shopplan-products").asString();
		get("http://192.168.10.21/site-api/find-shopplan-products").then()
			.body("message", equalTo("SUCCEED"));
		System.out.println(result);
		
	}
	
	public void find_product_review_Test()
	{
		String result=get("URL=http://192.168.10.21/site-api/find-product-review?barcode=33637&pageNo=1&pageSize=4&shopCode=24").asString();
		get("URL=http://192.168.10.21/site-api/find-product-review?barcode=33637&pageNo=1&pageSize=4&shopCode=24").then()
			.body("message", equalTo("SUCCEED"));
		System.out.println(result);
	}
	
	public void find_all_product_tag_Test()
	{
		
		String result=get("URL=http://192.168.10.21/site-api/find-product-review?barcode=33637&pageNo=1&pageSize=4&shopCode=24").asString();
		get("URL=http://192.168.10.21/site-api/find-product-review?barcode=33637&pageNo=1&pageSize=4&shopCode=24").then()
			.body("message", equalTo("SUCCEED"));
		System.out.println(result);
		
		
	}
	
	
	
	@Test
	public void get_app_indexweb_Test()
	{
		String result=get("http://192.168.10.21/site-api/get-app-indexweb?shopCode=24").asString();
		get("http://192.168.10.21/site-api/get-app-indexweb?shopCode=24").then()
			.body("message", equalTo("SUCCEED"));
		System.out.println(result);
	}
	
	@Test
	public void find_all_product_Test()
	{
	
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isRebate", "0");
		map.put("pageNo", "1");
		map.put("pageSize", "4");
		map.put("priceSort", "0");
		map.put("saleSort", "0");
		map.put("shopCode", "24");
		map.put("subject", "");
		String result=given().contentType(ContentType.JSON).body(map).when().
				post("http://192.168.10.21/site-api/find-all-product").asString();
		
		
		given().contentType(ContentType.JSON).body(map)
		.when().post("http://192.168.10.21/site-api/find-all-product")
		.then().body("message", equalTo("SUCCEED"));
		System.out.println(result);
	}
	
	
	
	
	
	
	
	
}
