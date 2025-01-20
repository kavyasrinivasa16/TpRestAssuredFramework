package practice.PostRequestTypes;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostReq_via_HashmapTest {
	
	@Test
	public void postDataToServer()
	{
		JSONObject jsonObj = new JSONObject();
		HashMap<String,Object> map = new HashMap();
		map.put("createdBy", "Kavya");
		map.put("status", "created");
		map.put("teamSize", 10);
		map.put("projectName", "Mars");

		given()
			.contentType(ContentType.JSON).body(map)
		.when()
			.post("http://49.249.28.218:8091/addProject")
		.then()
			.assertThat().statusCode(201)
		.log().all();

	}

}
