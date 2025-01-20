package com.ninza.hrm.api.employeeTest;

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
import com.ninza.hrm.api.pojoClass.EmployeePOJO;
import com.ninza.hrm.api.pojoClass.ProjectPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeTest {

	@Test
	public void addEmployeeTest() throws SQLException {

		// create an object to pojo class
		Random random = new Random();
		int ranNum = random.nextInt(5000);

		String projectName = "TEK_" + ranNum;
		String userName = "user" + ranNum;

		// Api-1 ==> add a project inside the server
		ProjectPOJO pObj = new ProjectPOJO(projectName, "created", "Kavya", 10);
		given()
		.contentType(ContentType.JSON)
		.body(pObj)
		.when()
		.post("http://49.249.28.218:8091/addProject")
		.then()
		.log().all();

		// API-2 ===> add employee to same Project
		EmployeePOJO empObj = new EmployeePOJO("SoftwareEngg", "16/06/1996", "kavya@gmail.com", userName, 18,
				"9876543210", projectName, "ROLE_EMPLOYEE", "TEK_" + ranNum);

		given()
		.contentType(ContentType.JSON)
		.body(empObj).when().post("http://49.249.28.218:8091/employees")
		.then()
		.assertThat().statusCode(201)
		.and()
		.time(Matchers.lessThan(3000L))
		.log().all();

		// Verify Emp Namein DB
		boolean flag = false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root@%", "root");
		ResultSet resultset = conn.createStatement().executeQuery("select * from employee");
		while (resultset.next()) {
			if (resultset.getString(5).equals(userName)) {
				flag = true;
				break;
			}
		}
		conn.close();
		Assert.assertTrue(flag, "Employee in DB is not verified");
	}

	@Test
	public void addEmployeeWithoutEmailTest() {

		// create an object to pojo class
		Random random = new Random();
		int ranNum = random.nextInt(5000);

		String projectName = "TEK_" + ranNum;
		String userName = "user" + ranNum;

		// Api-1 ==> add a project inside the server
		ProjectPOJO pObj = new ProjectPOJO(projectName, "created", "Kavya", 10);
		given()
		.contentType(ContentType.JSON)
		.body(pObj)
		.when()
		.post("http://49.249.28.218:8091/addProject")
		.then()
		.log().all();

		// API-2 ===> add employee to same Project
		EmployeePOJO empObj = new EmployeePOJO("SoftwareEngg", "16/06/1996", "", userName, 18,
				"9876543210", projectName, "ROLE_EMPLOYEE", "TEK_" + ranNum);

		given()
		.contentType(ContentType.JSON)
		.body(empObj)
		.when()
		.post("http://49.249.28.218:8091/employees")
		.then()
		.assertThat().statusCode(500)
		.and()
		.log().all();

	
		
	}
}
