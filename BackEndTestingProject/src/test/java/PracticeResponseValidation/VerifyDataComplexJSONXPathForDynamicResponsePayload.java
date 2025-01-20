package PracticeResponseValidation;

import static io.restassured.RestAssured.*;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

public class VerifyDataComplexJSONXPathForDynamicResponsePayload {

	@Test
	public void SampleTest() {
		Response resp = given().get("http://49.249.28.218:8091/projects-paginated");
		// resp.then().log().all();

		List<String> list = JsonPath.read(resp.asString(), ".content[*].projectName");
		for (String data : list) {
			System.out.println(data);
		}
		
		List<String> list1 = JsonPath.read(resp.asString(), ".content[*].[?(@.projectName=='Airtel_2105')].projectId");
		String actProjectID = list1.get(0);
		
		Assert.assertEquals(actProjectID, "NH_PROJ_851");
	}

}
