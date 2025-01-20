package practice;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SampleTestUsingMtdChainingTest {
	
	@Test
	public void getRequestTest()
	{
		get("https://reqres.in/api/users?page=2").prettyPrint();
	}

}
