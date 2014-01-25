// AAssignment
// Author: Jenna
// Abstract Class for SingleAssignment and RepetedAssignment objects

package hwo;
import java.time.Duration;
import java.util.Date;

public class SingleAssignment extends AAssignment
{
	private Date dueDate;
	private int progress;
	private int priority;
	private boolean completed;
	private RepeatAssignment repeat;
	
	public SingleAssignment(Date dueDate, RepeatAssignment parent)
	{
		this.dueDate = dueDate;
		this.repeat = parent;
	}
	
	public Date getDueDate(){return this.dueDate;}
	public int getProgress() {return this.progress;}
	public int getPriority(){return this.priority;}
	public boolean isCompleted(){return this.completed;}
	
	public void setDueDate(Date d){this.dueDate = d;}
	// -p is from 0 to 100
	public void setProgress(int p){this.progress = p;}
	// -p is from 1 to 5
	public void setPriority(int p){this.priority = p;}
	public void setCompletion(boolean c){this.completed = c;}
}
