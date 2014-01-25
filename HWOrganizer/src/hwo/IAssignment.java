// IAssignment
// Author: Jenna
// Interface for SingleAssignment and RepeatAssignment objects

package hwo;
import java.util.Calendar;
import java.sql.Time;
import java.time.Duration;

public interface IAssignment 
{
	// Get Methods
	public Course getCourse();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignmentType();
	public String getHWName();
	public String getNotes();
	public Duration getCompletionTime();
	public Calendar getStartDate();
	public Calendar getEndDate();
	public Time getDueTime();
	
	// Set Methods
	public void setCourse(Course c);
	public void setAssignmentLoc(String s);
	public void setTurninLoc(String s);
	public void setResources(String s);
	public void setAssignmentType(String s);
	public void setHWName(String s);
	public void setNotes(String s);
	public void setCompletionTime(Duration d);
	// -d must be earlier than the current start date
	public void setStartDate(Calendar d);
	// -d must be later than the current end date
	public void setEndDate(Calendar d);
	public void setDueTime(Time t);
	

}
