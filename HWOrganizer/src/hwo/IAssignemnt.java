// IAssignment
// Author: Jenna
// Interface for SingleAssignment and RepetedAssignemnt objects

package hwo;
import java.util.Date;
import java.time.Duration;

public interface IAssignemnt 
{
	// Get Methods
	public Date getDueDate();
	public int getProgress();
	public int getPriority();
	public boolean isCompleted();
	public Class getClass();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignemntType();
	public String getHWName();
	public String getNotes();
	public Duration getCompletionTime();
	public Date getStartDate();
	public Date getEndDate();
	
	// Set Methods
	public void setDueDate(Date d);
	public void setProgress(int p);
	public void setPriority(int p);
	public void setCompletion(boolean c);
	public void setClass(Class c);
	public void setAssignmentLoc(String s);
	public void setTurninLoc(String s);
	public void setResources(String s);
	public void setAssignemntType(String s);
	public void setHWName(String s);
	public void setNotes(String s);
	public void setCompletionTime(Duration d);
	public void setStartDate(Date d);
	public void setEndDate(Date d);
	

}
