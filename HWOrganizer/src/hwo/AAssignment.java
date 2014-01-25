// AAssignment
// Author: Jenna
// Abstract Class for SingleAssignment and RepeatAssignment objects

package hwo;
import java.time.Duration;
import java.sql.Time;
import java.util.Date;

public abstract class AAssignment implements IAssignment
{
	private Course course;
	private String assignmentLoc;
	private String turninLoc;
	private String resources;
	private String assignmentType;
	private String name;
	private String notes;
	private Duration completionTime;
	private Date startDate;
	private Date endDate;
	private Time dueTime;
	
	// Get Methods  
	public Course getCourse(){return this.course;}
	public String getAssignmentLoc(){return this.assignmentLoc;}
	public String getTurninLoc(){return this.turninLoc;}
	public String getResources(){return this.resources;}
	public String getAssignmentType(){return this.assignmentType;}
	public String getHWName(){return this.name;}
	public String getNotes(){return this.notes;}
	public Duration getCompletionTime(){return this.completionTime;}
	public Date getStartDate(){return this.startDate;}
	public Date getEndDate(){return this.endDate;}
	public Time getDueTime(){return this.dueTime;}
	
	// Set Methods
	public void setCourse(Course c){this.course = c;}
	public void setAssignmentLoc(String s){this.assignmentLoc = s;}
	public void setTurninLoc(String s){this.turninLoc = s;}
	public void setResources(String s){this.resources = s;}
	public void setAssignmentType(String s){this.assignmentType = s;}
	public void setHWName(String s){this.name = s;}
	public void setNotes(String s){this.notes = s;}
	public void setCompletionTime(Duration d){this.completionTime = d;} 
	public void setStartDate(Date d){this.startDate = d;}
	public void setEndDate(Date d){this.endDate = d;}
	public void setDueTime(Time t){this.dueTime = t;}
}
