// SingleAssignment
// Author: Jenna Netland
// Owner: Jenna Netland
// Concrete SingleAssignment Class: SingletAssignments hold specific information for assignments due a certain day

package hwo;

import java.util.Calendar;
import java.time.Duration;

public class SingleAssignment extends AAssignment
{
	private Calendar dueDate; // The date this assignment is due (ignores hours and below)
	private int progress; // The current progress on the assignment as a percentage (0 <= progress <= 100)
	private int priority; // The priority of the assignment (1 (low) <= priority <= 5 (high))
	private RepeatAssignment parent; // The RepeatedAssignment that created this assignment
	
	//-------------------------------------------------
	// Constructors
	//-------------------------------------------------
	public SingleAssignment(RepeatAssignment parent, Calendar dueDate)
	{
		this.dueDate = dueDate;
		this.progress = 0;
		this.priority = 2;
		
		this.parent = parent;
		
		// Signals to refer to the parent RepeatAssignment
		super.setName(null);
		super.setNotes(null);
		super.setCompletionTime(null);
		super.setCourse(null);
		// Signals to refer to the parent RepeatAssignment / course
		super.setAssignmentLoc(null);
		super.setTurninLoc(null);
		super.setResources(null);
		super.setAssignmentType(null);
		super.setDueTime(null);
	}
	
	//-------------------------------------------------
	// Getter methods
	//-------------------------------------------------
	public Calendar getDueDate() {return this.dueDate;}
	public int getProgress()  {return this.progress;}
	public int getPriority() {return this.priority;}
	public boolean isCompleted() {return this.priority == 100;}
	public RepeatAssignment getParentAssignment() {return this.parent;}
	// Overriden for SingleAssignments
	public Calendar getStartDate() {return this.dueDate;}
	public Calendar getEndDate() {return this.dueDate;}
	// Can refer to parent RepeatAssignment
	public Course getCourse()
	{
		if (super.getCourse() == null)
			return this.parent.getCourse();
		return super.getCourse();
	}
	public String getName()
	{
		if (super.getName() == null)
			return this.parent.getName();
		return super.getName();
	}
	public String getNotes()
	{
		if (super.getNotes() == null)
			return this.parent.getNotes();
		return super.getNotes();
	}
	public Duration getCompletionTime()
	{
		if (super.getCompletionTime() == null)
			return this.parent.getCompletionTime();
		return super.getCompletionTime();
	}
	// Can refer to parent RepeatAssignment / course
	public String getAssignmentLoc()
	{
		if (super.getAssignmentLoc() == null)
			return this.parent.getAssignmentLoc();
		return super.getAssignmentLoc();
	}
	public String getTurninLoc()
	{
		if (super.getTurninLoc() == null)
			return this.parent.getTurninLoc();
		return super.getTurninLoc();
	}
	public String getResources()
	{
		if (super.getResources() == null)
			return this.parent.getResources();
		return super.getResources();
	}
	public String getAssignmentType()
	{
		if(super.getAssignmentType() == null)
			return this.parent.getAssignmentType();
		return super.getAssignmentType();
	}
	public Calendar getDueTime()
	{
		if (super.getDueTime() == null)
			return this.parent.getDueTime();
		return super.getDueTime();
	}
	// Alternate Getter to getDueTime
	public String getTimeString()
	{
		// Isolate parts of time
		Calendar time = this.getDueTime();
		int hours = time.get(Calendar.HOUR_OF_DAY);
		int minutes = time.get(Calendar.MINUTE);
		String am_pm = "am";
		// Convert to 12-hour clock
		if (hours == 0)
			hours = 12;
		if (hours > 12)
		{
			hours -= 12;
			am_pm = "pm";
		}
		// Returns in the form hours:minutes am_pm
		return hours + ":" + minutes + " " + am_pm;
	}
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setDueDate(Calendar d) {this.dueDate = d;}
	public void setProgress(int p) {this.progress = p;}
	public void setPriority(int p) {this.priority = p;}
	public void setCompletion(boolean c) {this.progress = 100;}
}
