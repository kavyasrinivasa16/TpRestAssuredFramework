package RequestChaining;

import static io.restassured.RestAssured.given;
import java.util.Random;
import org.testng.annotations.Test;

import POJOClassUtility.EmployeePOJO;
import POJOClassUtility.ProjectPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Scenario_2_AddEmployToProject {
	@Test
	public void sampleTest() {
		// create an object to pojo class
		Random random = new Random();
		int ranNum = random.nextInt(5000);

		// Api-1 ==> add a project inside the server
		ProjectPOJO pObj = new ProjectPOJO("TEK_" + ranNum, "created", "Kavya", 10);
		Response resp = given().contentType(ContentType.JSON).body(pObj).when()
				.post("http://49.249.28.218:8091/addProject");
		resp.then().assertThat().statusCode(201).log().all();

		// capture projectName from the response
		String projectName = resp.jsonPath().get("projectName");
		System.out.println(projectName);

		// API-2 ===> add employee to same Project
		EmployeePOJO empObj = new EmployeePOJO("SoftwareEngg", "16/06/1996", "kavya@gmail.com", "TEK_" + ranNum, 18,
				"9876543210", projectName, "ROLE_EMPLOYEE", "TEK_" + ranNum);

		given().contentType(ContentType.JSON).body(empObj).when().post("http://49.249.28.218:8091/employees").
		then().assertThat().statusCode(201).log().all();
	}

}
