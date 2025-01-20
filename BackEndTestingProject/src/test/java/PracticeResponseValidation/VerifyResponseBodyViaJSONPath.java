package PracticeResponseValidation;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class VerifyResponseBodyViaJSONPath {

	@Test
	public void sampleTest() {

		Response resp = given().get("http://49.249.28.218:8091/projects-paginated");
		resp.then().log().all();

		resp.then().assertThat().body("numberOfElements", Matchers.greaterThanOrEqualTo(20));
		resp.then().assertThat().body("pageable.sort.unsorted", Matchers.equalTo(true));
		resp.then().assertThat().body("content[0].projectId", Matchers.equalTo("NH_PROJ_786"));

		int data = resp.jsonPath().get("numberOfElements");
		System.out.println(data);

		boolean data1 = resp.jsonPath().get("pagable.sort.unsorted");
		System.out.println(data1);

		String data2 = resp.jsonPath().get("content[0].projectId");
		System.out.println(data2);

	}

}
