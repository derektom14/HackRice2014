// AAssignment
// Author: Jenna
// Single Assignment information

package hwo;
import java.time.Duration;
import java.util.Date;

public class SingleAssignment extends AAssignment
{
	private Date dueDate;
	private int progress;
	private int priority;
	private boolean completed;
	private RepeatAssignment parent;
	
	// Constructor
	public SingleAssignment(Date dueDate, RepeatAssignment parent)
	{
		this.dueDate = dueDate;
		this.progress = 0;
		this.priority = 2;
		this.completed = false;
		this.parent = parent;
		
		super.setCourse(null);
		super.setTurninLoc(null);
		super.setResources(null);
		super.setAssignmentType(null);
		super.setHWName(null);
		super.setNotes(null);
		super.setCompletionTime(null);
		
	}
	
	// Get methods
	public Date getDueDate(){return this.dueDate;}
	public int getProgress() {return this.progress;}
	public int getPriority(){return this.priority;}
	public boolean isCompleted(){return this.completed;}
	public RepeatAssignment getParentAssignment(){return this.parent;}
	// Possible Parent Get Methods
	public Course getCourse()
	{
		if (super.getCourse() == null)
			return this.parent.getCourse();
		return super.getCourse();
	}
	public String getAssignmentLoc()
	{
		if (super.getAssignmentLoc() == null)
			return this.parent.getAssignmentLoc();
		return super.getAssignmentLoc();
	}
	public String getTurninLoc(){return this.turninLoc;}
	public String getResources(){return this.resources;}
	public String getAssignemntType(){return this.assignmentType;}
	public String getHWName(){return this.name;}
	public String getNotes(){return this.notes;}
	public Duration getCompletionTime(){return this.completionTime;}
	// Overridden
	public Date getStartDate(){return this.dueDate;}
	public Date getEndDate(){return this.dueDate;}
	
	
	// Set methods
	public void setDueDate(Date d){this.dueDate = d;}
	// -p is from 0 to 100
	public void setProgress(int p){this.progress = p;}
	// -p is from 1 to 5
	public void setPriority(int p){this.priority = p;}
	public void setCompletion(boolean c){this.completed = c;}
}
