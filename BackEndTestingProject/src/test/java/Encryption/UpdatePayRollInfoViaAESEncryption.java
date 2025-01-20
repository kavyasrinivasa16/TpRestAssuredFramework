package Encryption;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UpdatePayRollInfoViaAESEncryption {

	@Test
	public void sampleTest() throws Exception {

		EncryptAndDecryptUtility ed = new EncryptAndDecryptUtility();

		String jBody = "{ \"employee\": { \"empId\":\"NH_00252\", \"designation\": \"SoftwareEngg\", "
				+ "\"dob\": \"16/06/1996\", \"email\": \"kavya@gmail.com\", \"empName\": \"tek_3002\", "
				+ "\"experience\": 18.0, \"mobileNo\": \"9876543210\", \"project\": \"TEK_3002\", "
				+ "\"role\": \"ROLE_EMPLOYEE\", \"username\": “TEK_3002” }, \"basicPlusVda\": 5000, "
				+ "\"hra\": 5000, \"insurance\": 2000, \"lta\": 2000, \"lwf\": 200, \"netPay\": 300, "
				+ "\"payroll_id\": 176, \"pf\": 100, \"pt\": 200, \"stat_bonus\": 300, \"status\": “Active” }";

		String jReqBody = ed.encrypt(jBody, "Ac03tEam@j!tu_#1");
		System.out.println(jReqBody);

		Response resp = given().body(jReqBody).when().put("http://49.249.28.218:8091/payroll");
		resp.then().log().all();

		String respBody = ed.decrypt(resp.getBody().asString(), "Ac03tEam@j!tu_#1");
		System.out.println(respBody);
	}
}
