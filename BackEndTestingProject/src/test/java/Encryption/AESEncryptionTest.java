package Encryption;

import org.testng.annotations.Test;

public class AESEncryptionTest {
	@Test
	public void sampleTest() throws Exception {
		String privateKey = "AcO3tEam@j!tu_#1";
		String data = "Rohith";

		EncryptAndDecryptUtility ed = new EncryptAndDecryptUtility();
		// System.out.println(ed.encrypt(data, privateKey));

		System.out.println(ed.decrypt("JtERKPW2sdon2AZq0qDLtg==", privateKey));
	}
	
	@Test
	public void sampleTes1t() throws Exception {
		String privateKey = "AcO3tEam@j!tu_#1";
		String data = "{\"name\" : \"kavya\", \"id\" : \"tp_o1\"}";

		EncryptAndDecryptUtility ed = new EncryptAndDecryptUtility();
		//System.out.println(ed.encrypt(data, privateKey));

		System.out.println(ed.decrypt("AkBvTpH4c9RaoZHyt8+nja4RS54AU3qKV4cFdNWvcXNG8AIyVKJg+ucKVkMbpxMS", privateKey));
	}
	
	

}
