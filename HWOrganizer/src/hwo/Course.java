package hwo;

import java.util.ArrayList;
import java.util.Date;

public class Course implements ICourse{
	private String name;
	private String assignmentLoc;
	private String turninLoc;
	private String resources;
	private String assignmentType;
	private ArrayList<RepeatAssignment> assignments;
	private ISemester semester;
	
	//Accessor methods
	public String getName() {return name;}
	public String getAssignmentLoc() {return assignmentLoc;}
	public String getTurninLoc() {return turninLoc;}
	public String getResources() {return resources;}
	public String getAssignmentType() {return assignmentType;}
	public ArrayList<RepeatAssignment> getAssignments() {return assignments;}
	public ISemester getSemester() {return semester;}
	
	//Setter methods
	public void setName(String name) {this.name = name;}
	public void setAssignmentLoc(String location) {this.assignmentLoc = location;}
	public void setTurninLoc(String location) {this.turninLoc = location;}
	public void setResources(String resources) {this.resources = resources;}
	public void setAssignmentType(String type) {this.assignmentType = type;}
	public void setSemester(ISemester semester) {this.semester = semester;}
}
