package panCardMock;

import org.mockito.Mockito;

class PANCard {
	public String isValid(String panCard) {
		if (panCard.matches("[A-Z]{5}[0-9]{4}[A-Z]") == true)

		{
			return "is Valid pan";
		} else {
			return "is Invalid pan";
		}
	}

	public static PANCard getMockObject() {
		PANCard mockObj = Mockito.mock(PANCard.class);
		Mockito.when(mockObj.isValid("ABCDE1234A")).thenReturn("valid pan card");
		Mockito.when(mockObj.isValid("ABCDE1234B")).thenReturn("valid pan card");
		Mockito.when(mockObj.isValid("ABCDE1234C")).thenReturn("Invalid pan card");

		return mockObj;
	}
}

public class MockingTest {

	public static void main(String[] args) {
		PANCard obj = PANCard.getMockObject();
		System.out.println(obj.isValid("ABCDE1234C"));
	}
}
