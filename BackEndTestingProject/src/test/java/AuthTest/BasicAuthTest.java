package AuthTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class BasicAuthTest {

	@Test
	public void sampleTest()
	{
		given()
		.auth().basic("rmgyantra", "rmgy@9999")
		.log().all()
		.when()
		.get("http://49.249.29.5:8091/login") //ninza pay will support for basic auth
		.then()
		.log().all();
	}
}
