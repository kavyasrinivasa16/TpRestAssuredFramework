package practice_serialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Project1 {

	String projectName;
	String projectStatus;
	int teamsize;
	List<String> teammember;
	ProjectManager ProjectManager;

	public Project1(String projectName, String projectStatus, int teamsize, List<String> teammember,
			ProjectManager projectManager) {
		super();
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.teamsize = teamsize;
		this.teammember = teammember;
		ProjectManager = projectManager;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setTeamsize(int teamsize) {
		this.teamsize = teamsize;
	}

	public int getTeamsize() {
		return teamsize;
	}

	public void setTeammember(List<String> teammember) {
		this.teammember = teammember;
	}

	public List<String> getTeammember() {
		return teammember;
	}

	public void setProjectManager(ProjectManager ProjectManager) {
		this.ProjectManager = ProjectManager;
	}

	public ProjectManager getProjectManager() {
		return ProjectManager;
	}

}

class ProjectManager {

	String name;
	String empid;

	public ProjectManager(String name, String empid) {
		super();
		this.name = name;
		this.empid = empid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpid() {
		return empid;
	}

}

public class Run1_POJO {

	public static void main(String[] args) throws Throwable, DatabindException, IOException {
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("jhon");
		lst.add("stev");
		lst.add("david");

		ProjectManager pM = new ProjectManager("deepak", "tp01");

		Project1 projectObj = new Project1("Kavya", "created", 10, lst, pM);
		
		ObjectMapper objMap = new ObjectMapper();
		objMap.writeValue(new File("./Project1.json"), projectObj);
	}

}
