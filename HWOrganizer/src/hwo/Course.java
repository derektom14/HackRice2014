package hwo;

public class Course implements ICourse{
	private String name;
	private String assignmentLocation;
	private String turnInLocation;
	private String resources;
	private String assignmentType;
	private ISemester semester;
	
	//Accessor methods
	public String getName() {return name;}
	public String getAssignmentLocation() {return assignmentLocation;}
	public String getTurnInLocation() {return turnInLocation;}
	public String getResources() {return resources;}
	public String getAssignmentType() {return assignmentType;}
	public ISemester getSemester() {return semester;}
	
	//Setter methods
	public void setName(String name) {this.name = name;}
	public void setAssignmentLocation(String location) {this.assignmentLocation = location;}
	public void setTurnInLocation(String location) {this.turnInLocation = location;}
	public void setResources(String resources) {this.resources = resources;}
	public void setAssignmentType(String type) {this.assignmentType = type;}
	public void setSemester(ISemester semester) {this.semester = semester;}
	
}
