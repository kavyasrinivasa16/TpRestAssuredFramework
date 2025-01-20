package practice_serialization;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder(
		value = { "createdBy",
				  "projectName",
				  "teamsize",
				  "status"
		}
		)
@JsonIgnoreProperties(
		value = { 
				"teamsize"
				},allowSetters = true
		)
class Project // POJO class [Plain Old Java Object]
{
	private String projectName;
	@JsonProperty(value = "created By")
	private String createdBy;
	private int teamsize;
	private String status;

	private Project() {
	}

	public Project(String projectName, String createdBy, int teamsize, String status) {
		super();
		this.projectName = projectName;
		this.createdBy = createdBy;
		this.teamsize = teamsize;
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getTeamsize() {
		return teamsize;
	}

	public void setTeamsize(int teamsize) {
		this.teamsize = teamsize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

public class Run1_jacksonSerializationTest {
	public static void main(String[] args) throws Throwable, DatabindException, IOException {
		Project pObj = new Project("Shop@Door", "Kavya", 10, "Created");

		ObjectMapper objMap = new ObjectMapper();
		objMap.writeValue(new File("./project.json"), pObj);
		System.out.println("======END=====");
	}
}
