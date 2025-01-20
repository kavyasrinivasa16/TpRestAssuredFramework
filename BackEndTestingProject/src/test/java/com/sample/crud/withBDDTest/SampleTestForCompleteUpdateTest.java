package com.sample.crud.withBDDTest;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class SampleTestForCompleteUpdateTest {

	@Test
	public void putDataToServer() {

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("createdBy", "Kushii");
		jsonObj.put("status", "created");
		jsonObj.put("teamSize", 10);
		jsonObj.put("projectName", "MOON");

		given().contentType(ContentType.JSON).body(jsonObj.toJSONString()).when()
				.put("http://49.249.28.218:8091/project/NH_PROJ_358").then().assertThat().statusCode(200).log().all();

	}

}
