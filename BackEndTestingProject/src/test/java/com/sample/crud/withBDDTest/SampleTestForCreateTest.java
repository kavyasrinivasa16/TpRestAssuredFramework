package com.sample.crud.withBDDTest;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;

public class SampleTestForCreateTest {

	@Test
	public void postDataToServer() {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("createdBy", "Kavya");
		jsonObj.put("status", "created");
		jsonObj.put("teamSize", 10);
		jsonObj.put("projectName", "JUPITER");

		given().contentType(ContentType.JSON).body(jsonObj.toJSONString()).when()
				.post("http://49.249.28.218:8091/addProject").then().assertThat().statusCode(201).log().all();

	}

}
