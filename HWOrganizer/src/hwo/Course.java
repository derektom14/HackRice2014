package hwo;
import java.sql.Time; 
import java.util.ArrayList;
import java.util.Calendar;

public class Course implements ICourse
{
	private String name;
	private String assignmentLoc;
	private String turninLoc;
	private String resources;
	private String assignmentType;
	private ArrayList<RepeatAssignment> assignments;
	private ISemester semester;
	private Time dueTime;
	private Calendar startDate;
	private Calendar endDate;
	
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
	public Course (ISemester semester, String name, String assignmentLoc, String turninLoc, String resources, String assignmentType, Time dueTime)
	{
		this.semester = semester;
		this.name = name;
		this.assignmentLoc = assignmentLoc;
		this.turninLoc = turninLoc;
		this.resources = resources;
		this.assignmentType = assignmentType;
		this.dueTime = dueTime;
	}
	
	//Accessor methods
	public String getName() {return name;}
	public String getAssignmentLoc() {return assignmentLoc;}
	public String getTurninLoc() {return turninLoc;}
	public String getResources() {return resources;}
	public String getAssignmentType() {return assignmentType;}
	public ArrayList<RepeatAssignment> getAssignments() {return assignments;}
	public ISemester getSemester() {return semester;}
	public Time getDueTime() {return dueTime;}
	// Return semester value if this value is null
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
	
	//Setter methods
	public void setName(String name) {this.name = name;}
	public void setAssignmentLoc(String location) {this.assignmentLoc = location;}
	public void setTurninLoc(String location) {this.turninLoc = location;}
	public void setResources(String resources) {this.resources = resources;}
	public void setAssignmentType(String type) {this.assignmentType = type;}
	public void setSemester(ISemester semester) {this.semester = semester;}
	public void setDueTime(Time time) {this.dueTime = time;}
	public void setStartDate(Calendar date) {this.startDate = date;}
	public void setEndDate(Calendar date) {this.endDate = date;}
	public void addAssignment(RepeatAssignment assignment) {this.assignments.add(assignment);}
} 
