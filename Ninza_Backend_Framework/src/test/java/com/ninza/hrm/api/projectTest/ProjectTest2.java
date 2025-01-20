package com.ninza.hrm.api.projectTest;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoClass.ProjectPOJO;
import com.ninza.hrm.contants.endpoints.IEndPoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectTest2 extends BaseAPIClass{

	ProjectPOJO pObj;
	
	@Test
	public void addSingleProjectWithCreatedTest() throws Throwable
	{
		
		String BASEURI = fLib.getDataFromPropertiesFile("BASE_URI");
		String projectName = "IBM" + jLib.getRandomNumber();
		String expSucMsg = "Successfully Added";

		pObj = new ProjectPOJO(projectName, "created", "Kavya", 10);

		 Response resp = given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj);
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThan(3000L))
		.spec(specRespObj)
		.log().all();
		
		String actMsg = resp.jsonPath().get("msg");
		Assert.assertEquals(expSucMsg, actMsg);
		
		//verify the projectName in DB layer
		
		boolean flag = dbLib.executeQueryVerifyAndGetData("select* from project", 4, projectName);
		Assert.assertTrue(flag,"Project in DB is not verified");	
		}
	
	@Test(dependsOnMethods = "addSingleProjectWithCreatedTest")
	public void createDuplicateProjectTest() throws Throwable
	{
		String BASEURI = fLib.getDataFromPropertiesFile("BASE_URI");
		given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj)
		.then()
		.assertThat()
		.statusCode(409)
		.spec(specRespObj)
		.log().all();
	}	
	
	
}
	

