package Encryption;

import java.util.Base64;
import org.testng.annotations.Test;

public class EncodeAndDecodeTest {

	@Test
	public void sampleTest() {
		String EncodeData = new String(Base64.getEncoder().encode("rmgyantra:rmgy@9999".getBytes()));
		System.out.println(EncodeData);
		String DecodeData = new String(Base64.getDecoder().decode(EncodeData.getBytes()));
		System.out.println(DecodeData);
	}

}
