package practice_serialization;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run3_jacksonDeserialization {

	public static void main(String[] args) throws Throwable, DatabindException, IOException {
		
		ObjectMapper objMap = new ObjectMapper();
		Project pObj = objMap.readValue(new File("./project.json"), Project.class);
		System.out.println(pObj.getProjectName());
		System.out.println(pObj.getCreatedBy());
		System.out.println(pObj.getTeamsize());
		System.out.println(pObj.getStatus());
	}

}
