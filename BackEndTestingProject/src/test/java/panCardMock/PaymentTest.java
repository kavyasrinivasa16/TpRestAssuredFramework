package panCardMock;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PaymentTest {
	
	@Test
	public void sampleTest()
	{
	
		JSONObject obj = new JSONObject();
		obj.put("creditcard", "1234567891234");
		obj.put("cvv", "123");
		obj.put("cardName", "Deepak");
		
		given()
		//.contentType(ContentType.JSON)
		.body(obj)
		.when()
		.post("http://localhost:8889/credit-card")
		.then().log().all();
	}

}
