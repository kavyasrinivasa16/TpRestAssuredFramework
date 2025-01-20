package TypesOfParameter;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ParamParameterTest {

	@Test
	public void sampleTest() {
		given()
		.param("teamSize", 10)
		.log().all()
		.when()
		.get("http://49.249.28.218:8091/project")
		.then()
		.log().all();
	}

}
