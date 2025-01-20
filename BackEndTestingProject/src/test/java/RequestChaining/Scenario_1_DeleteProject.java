package RequestChaining;

import static io.restassured.RestAssured.*;
import java.util.Random;
import org.testng.annotations.Test;
import POJOClassUtility.ProjectPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Scenario_1_DeleteProject {

	@Test
	public void postDataToServer() {

		// create an object to pojo class
		Random random = new Random();
		int ranNum = random.nextInt(5000);

		// Api-1 ==> add a project inside the server
		ProjectPOJO pObj = new ProjectPOJO("Airtel" + ranNum, "created", "Kavya", 10);

		Response resp = given().contentType(ContentType.JSON).body(pObj).when()
				.post("http://49.249.28.218:8091/addProject");
		resp.then().assertThat().statusCode(201).log().all();

		// capture projectName from the response
		String projectID = resp.jsonPath().get("projectId");
		System.out.println(projectID);

		// API-2 ==> delete Project
		given().delete("http://49.249.28.218:8091/project/" + projectID).then().log().all();

	}

}
