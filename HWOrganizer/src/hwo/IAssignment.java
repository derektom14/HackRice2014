// IAssignment
// Author: Jenna
// Interface for SingleAssignment and RepetedAssignment objects

package hwo;
import java.util.Date;
import java.time.Duration;

public interface IAssignment 
{
	// Get Methods
	public Date getDueDate();
	public int getProgress();
	public int getPriority();
	public boolean isCompleted();
	public Course getCourse();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignemntType();
	public String getHWName();
	public String getNotes();
	public Duration getCompletionTime();
	public Date getStartDate();
	public Date getEndDate();
	public boolean[] getFrequency();
	
	// Set Methods
	public void setDueDate(Date d);
	// -p is from 0 to 100
	public void setProgress(int p);
	// -p is from 1 to 5
	public void setPriority(int p);
	public void setCompletion(boolean c);
	public void setCourse(Course c);
	public void setAssignmentLoc(String s);
	public void setTurninLoc(String s);
	public void setResources(String s);
	public void setAssignemntType(String s);
	public void setHWName(String s);
	public void setNotes(String s);
	public void setCompletionTime(Duration d);
	// -d must be earlier than the current start date
	public void setStartDate(Date d);
	// -d must be later than the current end date
	public void setEndDate(Date d);
	// - b must have a length of 7
	public boolean[] setFrequency(boolean[] b);
	

}
