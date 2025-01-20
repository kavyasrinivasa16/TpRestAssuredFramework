package com.sample.crud.withBDDTest;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class SampleTestForPartialUpdateTest {

	@Test
	public void patchDataToServer() {

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("projectName", "WORK1");

		given().contentType(ContentType.JSON).body(jsonObj.toJSONString()).when()
				.patch("http://49.249.28.218:8091/project/NH_PROJ_358").then().assertThat().statusCode(200).log().all();
	}

}
