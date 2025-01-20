package DDT;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class AddMultipleProjectTest {
	
	@Test(dataProvider = "getData")
	public void sampleTest(String pName, String status)
	{
		String reqBody = "{\r\n"
				+ "  \"createdBy\": \"Kavya\",\r\n"
				+ "  \"projectName\": \""+pName+"\",\r\n"
				+ "  \"status\": \""+status+"\",\r\n"
				+ "  \"teamSize\": 0\r\n"
				+ "}";
		
		given()
		.contentType(ContentType.JSON)
		.body(reqBody)
		.when()
		.post("http://49.249.28.218:8091/addProject")
		.then()
		.log().all();
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		ExcelUtility eLib = new ExcelUtility();
		int count = eLib.getRowcount("Project");
		
		Object[][] objarr = new Object[count][2];
		
		for(int i=0; i<count; i++)
		{
			objarr[i][0] = eLib.getDataFromExcel("Project", i+1, 0);
			objarr[i][1] = eLib.getDataFromExcel("Project", i+1, 1);
			
		}
		return objarr;
		
	}

}
