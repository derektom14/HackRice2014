// Course
// Author: Michael Peirce
// Owner: Jenna Netland
// Concrete Course class: Courses hold default values and assignments

package hwo;

import java.util.ArrayList;
import java.util.Calendar;

public class Course implements ICourse
{
	private String name;
	private String assignmentLoc;
	private String turninLoc;
	private String resources;
	private String assignmentType;
	private Calendar dueTime;
	private ArrayList<RepeatAssignment> assignments;
	private ISemester semester;
	// Can be from semester
	private Calendar startDate;
	private Calendar endDate;
	
	//-------------------------------------------------
	// Constructors
	//-------------------------------------------------
	
	// Partial Constructor
	public Course (ISemester semester, String name)
	{
		this.assignments = new ArrayList<RepeatAssignment>();
		
		this.semester = semester;
		this.name = name;
		this.assignmentLoc = "";
		this.turninLoc = "";
		this.resources = "";
		this.assignmentType = "";
		
		this.semester.addCourse(this);
	}
	
	// Complete Constructor
	public Course (ISemester semester, String name, String assignmentLoc, String turninLoc, String resources, String assignmentType, Calendar dueTime)
	{
		this.semester = semester;
		this.name = name;
		this.assignmentLoc = assignmentLoc;
		this.turninLoc = turninLoc;
		this.resources = resources;
		this.assignmentType = assignmentType;
		this.dueTime = dueTime;
	}
	
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public String getName() {return name;}
	public String getAssignmentLoc() {return assignmentLoc;}
	public String getTurninLoc() {return turninLoc;}
	public String getResources() {return resources;}
	public String getAssignmentType() {return assignmentType;}
	public Calendar getDueTime() {return dueTime;}
	public ArrayList<RepeatAssignment> getAssignments() {return assignments;}
	public ISemester getSemester() {return semester;}
	// Can refer to semester
	public Calendar getStartDate() 
	{
		if (this.startDate == null)
			return this.semester.getStartDate();
		return this.startDate;
	}
	public Calendar getEndDate() 
	{
		if (this.endDate == null)
			return this.semester.getEndDate();
		return this.endDate;
	}
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setName(String name) {this.name = name;}
	public void setAssignmentLoc(String location) {this.assignmentLoc = location;}
	public void setTurninLoc(String location) {this.turninLoc = location;}
	public void setResources(String resources) {this.resources = resources;}
	public void setAssignmentType(String type) {this.assignmentType = type;}
	public void setDueTime(Calendar time) {this.dueTime = time;}
	public void addAssignment(RepeatAssignment assignment) {this.assignments.add(assignment);}
	public void setSemester(ISemester semester) {this.semester = semester;}
	// Overrides value from semester
	public void setStartDate(Calendar date) {this.startDate = date;}
	public void setEndDate(Calendar date) {this.endDate = date;}
} 
