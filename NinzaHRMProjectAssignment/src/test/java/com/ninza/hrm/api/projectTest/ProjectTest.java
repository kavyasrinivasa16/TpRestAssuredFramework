package com.ninza.hrm.api.projectTest;

import static io.restassured.RestAssured.given;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.mysql.jdbc.Driver;
import com.ninza.hrm.api.pojoClass.ProjectPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectTest {

	
	ProjectPOJO pObj;
	
	@Test
	public void addSingleProjectWithCreatedTest() throws SQLException
	{
		Random random = new Random();
		int ranNum = random.nextInt(5000);
		
		String projectName = "IBM" + ranNum;
		String expSucMsg = "Successfully Added";

		pObj = new ProjectPOJO(projectName, "created", "Kavya", 10);

		 Response resp = given()
		.contentType(ContentType.JSON)
		.body(pObj)
		.when()
		.post("http://49.249.28.218:8091/addProject");
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThan(3000L))
		.assertThat().contentType(ContentType.JSON)
		.log().all();
		
		String actMsg = resp.jsonPath().get("msg");
		Assert.assertEquals(expSucMsg, actMsg);
		
		//verify the projectName in DB layer
		boolean flag = false;
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn =DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root@%", "root");
		ResultSet resultset= conn.createStatement().executeQuery("select * from project");
		while(resultset.next()) {
		if(resultset.getString(4).equals(projectName)) {
		flag = true;
		break;
		}
		}
		conn.close();
		Assert.assertTrue(flag,"Project in DB is not verified");
		}
	
	
	@Test(dependsOnMethods = "addSingleProjectWithCreatedTest")
	public void createDuplicateProjectTest()
	{
		given()
		.contentType(ContentType.JSON)
		.body(pObj)
		.when()
		.post("http://49.249.28.218:8091/addProject")
		.then()
		.assertThat()
		.statusCode(409)
		.log().all();
	}
}
	

