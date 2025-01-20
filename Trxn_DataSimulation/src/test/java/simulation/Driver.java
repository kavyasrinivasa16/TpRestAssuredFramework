package simulation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class Driver {

	public static String path;
	public static void main(String[] args) throws Throwable {

		String TRANSACTION_ID_VAR = "";
		String TRANSACTION_DATE_VAR = "";
		String TRANSACTION_AMOUNT_VAR = "";
		String PAYER_PSP_VAR = "";
		String PAYEE_PSP_VAR = "";

		path = args[0];
		// System.out.println(args[0]);

		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.getRowcount("trxn");
		for (int i = 1; i <= rowCount; i++)
		{
			TRANSACTION_ID_VAR = "IDFC001002"+new Random().nextInt(10000);
			TRANSACTION_DATE_VAR = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			TRANSACTION_AMOUNT_VAR = eLib.getDataFromExcel("trxn", i, 2);
			PAYER_PSP_VAR = eLib.getDataFromExcel("trxn", i, 3);
			PAYEE_PSP_VAR = eLib.getDataFromExcel("trxn", i, 4);
			
			eLib.setDataIntoExcel("trxn", i, 0, TRANSACTION_ID_VAR);
			eLib.setDataIntoExcel("trxn", i, 1, TRANSACTION_DATE_VAR);
			
			System.out.println(TRANSACTION_ID_VAR);
			System.out.println(TRANSACTION_DATE_VAR);
			System.out.println(TRANSACTION_AMOUNT_VAR);
			System.out.println(PAYER_PSP_VAR);
			System.out.println(PAYEE_PSP_VAR);
			
			System.out.println("==================================");
			
			String reqBody = "{\"Transaction_ID\":\""+TRANSACTION_ID_VAR+"\",\"Transaction_Mode\":\"ONLINE\",\"Transaction_Date\":\""+TRANSACTION_DATE_VAR+"\",\"Transaction_Time\":\"11:01:28:930 \",\r\n"
					+ "\"Transaction_Amount\":\""+TRANSACTION_AMOUNT_VAR+"\",\"Transaction_Type\":\"Transfer\",\"Description\":\"Payer to Payee transaction via online mode\",\"Currency\":\"INR\",\r\n"
					+ "\"Location\":\"Mumbai Maharastra\",\"Authorization_Code\":\"C123\",\"Merchant_Information\":\"Merchant Dharavi Mumbai\",\"Batch_Number\":\"06545678\",\r\n"
					+ "\"Recurring_Indicator\":\"yes\",\"Tax_Information\":\"GS34567S\",\"Risk_Assessment_Score\":\"199\",\"Promotion_Coupon_Code\":\"CH123\",\"Exchange_Rate\":\"67\",\r\n"
					+ "\"Transaction_Code\":\"TR12\",\"Notes\":\"This is a merchant transaction.\",\"Reference_Number\":\"REF991\",\"Device_Information\":\"xiomi Note11\",\"MCC\":\"M123\",\r\n"
					+ "\"CVM\":\"OTP\",\"Regulatory_Compliance_Information\":\"KYC\",\"Payer_Details\":{\"Payer_PSP\":\""+PAYER_PSP_VAR+"\",\"Payer_Name\":\"Payer\",\"Bank_Account\":\"HDFC\",\r\n"
					+ "\"Account_Type\":\"Savings\",\"IFSC\":\"HDF01\",\"Mobile_Number\":\"9887776676\",\"Address\":\"payer_address@123\",\"Ip_Address\":\"1235@fghj\",\"Mail_Id\":\"mohoan@gmail.com\",\r\n"
					+ "\"Balance\":\"690000.90\"},\"Payee_Details\":{\"Payee_PSP\":\""+PAYEE_PSP_VAR+"\",\"Payee_Name\":\"Payee\",\"Bank_Account\":\"ICICI\",\"Account_Type\":\"Savings\",\"IFSC\":\"ICIC01\",\r\n"
					+ "\"Mobile_Number\":\"9886662222\",\"Address\":\"payee_address@123\",\"Mail_Id\":\"deepak.h@gmail.com\"},\"Transaction_Status\":\"Completed\",\"isUPITransaction\":true,\r\n"
					+ "\"Sender_Source\":\"Remitter\",\"Recipient_Destination\":\"Benificiary\"}";
			given().contentType(ContentType.JSON)
			.body(reqBody)
			.when().post("http://49.249.29.5:8091/add-transaction")
			.then().log().all();
		}

	}

}
