package com.ninza.hrm.api.employeeTest;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoClass.EmployeePOJO;
import com.ninza.hrm.api.pojoClass.ProjectPOJO;
import com.ninza.hrm.contants.endpoints.IEndPoint;
import io.restassured.http.ContentType;


public class EmployeeTest2 extends BaseAPIClass{

	
	@Test
	public void addEmployeeTest() throws Throwable {

		// create an object to pojo class
		String BASEURI = fLib.getDataFromPropertiesFile("BASE_URI");
		String projectName = "TEK_" + jLib.getRandomNumber();
		String userName = "user" + jLib.getRandomNumber();

		// Api-1 ==> add a project inside the server
		ProjectPOJO pObj = new ProjectPOJO(projectName, "created", "Kavya", 10);
		given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj)
		.then()
		.spec(specRespObj)
		.log().all();

		// API-2 ===> add employee to same Project
		EmployeePOJO empObj = new EmployeePOJO("SoftwareEngg", "16/06/1996", "kavya@gmail.com", userName, 18,
				"9876543210", projectName, "ROLE_EMPLOYEE", "TEK_" + jLib.getRandomNumber());

		given()
		.spec(specReqObj)
		.body(empObj).when().post(IEndPoint.ADDEmp)
		.then()
		.assertThat().statusCode(201)
		.and()
		.time(Matchers.lessThan(3000L))
		.spec(specRespObj)
		.log().all();

		// Verify Emp Name in DB
		
		boolean flag = dbLib.executeQueryVerifyAndGetData("select* from employee", 5, userName);
		Assert.assertTrue(flag,"Project in DB is not verified");
		Assert.assertTrue(flag,"Employee in DB is not verified");
	
	}

	@Test
	public void addEmployeeWithoutEmailTest() throws Throwable {

		// create an object to pojo class
		

		String BASEURI = fLib.getDataFromPropertiesFile("BASE_URI");
		String projectName = "TEK_" + jLib.getRandomNumber();
		String userName = "user" + jLib.getRandomNumber();

		// Api-1 ==> add a project inside the server
		ProjectPOJO pObj = new ProjectPOJO(projectName, "created", "Kavya", 10);
		given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndPoint.ADDProj)
		.then()
		.spec(specRespObj)
		.log().all();

		// API-2 ===> add employee to same Project
		EmployeePOJO empObj = new EmployeePOJO("SoftwareEngg", "16/06/1996", "", userName, 18,
				"9876543210", projectName, "ROLE_EMPLOYEE", "TEK_" + jLib.getRandomNumber());

		given()
		.spec(specReqObj)
		.body(empObj)
		.when()
		.post(IEndPoint.ADDEmp)
		.then()
		.assertThat().statusCode(500)
		.and()
		.spec(specRespObj)
		.log().all();	
	}
	
}
