package restassuied;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

public class Demo1 {
	
	@Test
	public void test1()
	{
		
		
		get("http://192.168.10.21/site-api/find-all-category?shopCode=24").then()
		.body("message", equalTo("SUCCEED"));
		
	}
	

}
