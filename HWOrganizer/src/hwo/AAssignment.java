// AAssignment
// Author: Jenna Netland
// Owner: Jenna Netland
// Abstract Class for SingleAssignment and RepeatAssignment objects: Assignments hold the details of homework assignments

package hwo;

import java.util.Calendar;
import javax.xml.datatype.Duration;

public abstract class AAssignment implements IAssignment
{
	private String name; // Name of the assignment
	private String notes; // Notes regarding assignment
	private Duration completionTime; // Estimated amount of time to complete assignment
	private int priority; // The priority of the assignment (1 (low) <= priority <= 5 (high), default = 2)
	private ICourse course; // The course which assigned this assignment
	// Can refer to course
	private String assignmentLoc;
	private String turninLoc;
	private String resources;
	private String assignmentType;
	private Calendar dueTime;
	// Can refer to course / semester
	private Calendar startDate;
	private Calendar endDate;
	
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public String getName() {return this.name;}
	public String getNotes() {return this.notes;}
	public Duration getCompletionTime() {return this.completionTime;}
	public int getPriority() {return this.priority;}
	public ICourse getCourse() {return this.course;}
	// Can refer to course
	public String getAssignmentLoc() {return this.assignmentLoc;}
	public String getTurninLoc() {return this.turninLoc;}
	public String getResources() {return this.resources;}
	public String getAssignmentType() {return this.assignmentType;}
	public Calendar getDueTime() {return this.dueTime;}
	// Can refer to course / semester
	public Calendar getStartDate() {return this.startDate;}
	public Calendar getEndDate() {return this.endDate;}
	
	//-------------------------------------------------
	// Setter Methods
	//-------------------------------------------------
	public void setName(String name) {this.name = name;}
	public void setNotes(String notes) {this.notes = notes;}
	public void setCompletionTime(Duration time) {this.completionTime = time;} 
	public void setPriority(int priority) {this.priority = priority;}
	public void setCourse(ICourse course) {this.course = course;}
	// Overrides value from course
	public void setAssignmentLoc(String location) {this.assignmentLoc = location;}
	public void setTurninLoc(String location) {this.turninLoc = location;}
	public void setResources(String resources) {this.resources = resources;}
	public void setAssignmentType(String type) {this.assignmentType = type;}
	public void setDueTime(Calendar time) {this.dueTime = time;}
	// Overrides value from course / semester
	public void setStartDate(Calendar date) 
	{
		if (date.after(this.getEndDate()))
			throw new IllegalArgumentException("Tried to change an assignment's start date to be later than its end date.");
		else
			this.startDate = date;
	}
	public void setEndDate(Calendar date) 
	{
		if (date.before(this.getStartDate()))
			throw new IllegalArgumentException("Tried to change an assignment's end date to be earlier than its start date.");
		else
			this.endDate = date;
	}
	
	//-------------------------------------------------
	// Class methods
	//-------------------------------------------------
	public void changeTo(AAssignment from)
	{
		this.name = from.name;
		this.notes = from.notes;
		this.completionTime = from.completionTime;
		this.priority = from.priority;
		this.course = from.course;
		this.assignmentLoc = from.assignmentLoc;
		this.turninLoc = from.turninLoc;
		this.resources = from.resources;
		this.assignmentType = from.assignmentType;
		this.dueTime = from.dueTime;
		this.startDate = from.startDate;
		this.endDate = from.endDate;
	}
}
