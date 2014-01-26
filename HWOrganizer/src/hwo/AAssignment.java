// AAssignment
// Author: Jenna Netland
// Owner: Jenna Netland
// Abstract Class for SingleAssignment and RepeatAssignment objects: Assignments hold the details of homework assignments

package hwo;

import java.util.Calendar;
import java.time.Duration;

public abstract class AAssignment implements IAssignment
{
	private String name; // Name of the assignment
	private String notes; // Notes regarding assignment
	private Duration completionTime; // Estimated amount of time to complete assignment
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
	public void setName(String s) {this.name = s;}
	public void setNotes(String s) {this.notes = s;}
	public void setCompletionTime(Duration d) {this.completionTime = d;} 
	public void setCourse(ICourse c) {this.course = c;}
	// Overrides value from course
	public void setAssignmentLoc(String s) {this.assignmentLoc = s;}
	public void setTurninLoc(String s) {this.turninLoc = s;}
	public void setResources(String s) {this.resources = s;}
	public void setAssignmentType(String s) {this.assignmentType = s;}
	public void setDueTime(Calendar t) {this.dueTime = t;}
	// Overrides value from course / semester
	public void setStartDate(Calendar d) {this.startDate = d;}
	public void setEndDate(Calendar d) {this.endDate = d;}
}
