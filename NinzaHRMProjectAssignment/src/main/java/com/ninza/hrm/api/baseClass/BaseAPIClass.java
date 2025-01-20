package com.ninza.hrm.api.baseClass;

import com.ninza.hrm.api.genericUtility.DataBaseUtilities;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;
import static io.restassured.RestAssured.*;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class BaseAPIClass {

	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public DataBaseUtilities dbLib = new DataBaseUtilities();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;
	
	@BeforeSuite
	public void configBS() throws Throwable
	{
		dbLib.getDBConnection();
		System.out.println("=========connect to db=======");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
		builder.setBaseUri(fLib.getDataFromPropertiesFile("BASE_URI"));
		specReqObj = builder.build();
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectContentType(ContentType.JSON);
		specRespObj = resBuilder.build();
		
	}
	
	@AfterSuite
	public void configAS() throws Throwable
	{
		dbLib.closeDbConnection();
		System.out.println("==========disconnect to db===========");
	}
}
