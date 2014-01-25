// AAssignment
// Author: Jenna
// Abstract Class for SingleAssignment and RepetedAssignment objects

package hwo;
import java.time.Duration;
import java.util.Date;

public abstract class AAssignment implements IAssignment
{
	private Date dueDate;
	private int progress;
	private int priority;
	private boolean completed;
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
	private boolean[] frequency;
	
	// Get Methods
	public Date getDueDate(){return this.dueDate;}
	public int getProgress() {return this.progress;}
	public int getPriority(){return this.priority;}
	public boolean isCompleted(){return this.completed;}
	public Course getCourse(){return this.course;}
	public String getAssignmentLoc(){return this.assignmentLoc;}
	public String getTurninLoc(){return this.turninLoc;}
	public String getResources(){return this.resources;}
	public String getAssignemntType(){return this.assignmentType;}
	public String getHWName(){return this.name;}
	public String getNotes(){return this.notes;}
	public Duration getCompletionTime(){return this.completionTime;}
	public Date getStartDate(){return this.startDate;}
	public Date getEndDate(){return this.endDate;}
	public boolean[] getFrequency(){return this.frequency;}
	
	// Set Methods
	public void setDueDate(Date d){this.dueDate = d;}
	public void setProgress(int p){this.progress = p;}
	public void setPriority(int p){this.priority = p;}
	public void setCompletion(boolean c){this.completed = c;}
	public void setCourse(Course c){this.course = c;}
	public void setAssignmentLoc(String s){this.assignmentLoc = s;}
	public void setTurninLoc(String s){this.turninLoc = s;}
	public void setResources(String s){this.resources = s;}
	public void setAssignemntType(String s){this.assignmentType = s;}
	public void setHWName(String s){this.name = s;}
	public void setNotes(String s){this.notes = s;}
	public void setCompletionTime(Duration d){this.completionTime = d;} 
	public void setStartDate(Date d){this.startDate = d;}
	public void setEndDate(Date d){this.endDate = d;}
	public void setFrequency(boolean[] b){this.frequency = b;}
	
}
