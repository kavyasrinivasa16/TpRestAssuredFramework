package practice.PostRequestTypes;

import static io.restassured.RestAssured.*;
import java.util.Random;
import org.testng.annotations.Test;
import POJOClassUtility.ProjectPOJO;
import io.restassured.http.ContentType;

public class PostReq_via_POJOClassTest {

	@Test
	public void postDataToServer() {

		// create an object to pojo class
		Random random = new Random();
		int ranNum = random.nextInt(5000);

		ProjectPOJO pObj = new ProjectPOJO("IBM" + ranNum, "created", "Kavya", 10);

		given().contentType(ContentType.JSON).body(pObj).when().post("http://49.249.28.218:8091/addProject").then()
				.assertThat().statusCode(201).log().all();

	}

}
