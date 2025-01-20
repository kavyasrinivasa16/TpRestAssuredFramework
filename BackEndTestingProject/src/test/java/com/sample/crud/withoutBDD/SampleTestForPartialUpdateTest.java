package com.sample.crud.withoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleTestForPartialUpdateTest {

	@Test
	public void patchDataToServer() {

		JSONObject jsonObj = new JSONObject();

//		jsonObj.put("createdBy", "Kavya");
//		jsonObj.put("status", "created");
//		jsonObj.put("teamSize", 10);
		jsonObj.put("projectName", "WORK1");

		RequestSpecification req = RestAssured.given();
		req.contentType(ContentType.JSON);
		req.body(jsonObj.toJSONString());

		Response resp = req.patch("http://49.249.28.218:8091/project/NH_PROJ_330");
		resp.then().log().all();
		resp.then().assertThat().statusCode(200);

	}

}
