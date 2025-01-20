package com.sample.crud.withoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SampleTestForReadTest {

	@Test
	public void getDataFromServer() {
		Response resp = RestAssured.get("http://49.249.28.218:8091/projects");
		resp.then().assertThat().statusCode(200);
		resp.then().log().all();
	}

}
