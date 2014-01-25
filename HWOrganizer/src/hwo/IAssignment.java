// IAssignment
// Author: Jenna
// Interface for SingleAssignment and RepeatAssignment objects

package hwo;
import java.util.Date;
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
	public Date getStartDate();
	public Date getEndDate();
	
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
	public void setStartDate(Date d);
	// -d must be later than the current end date
	public void setEndDate(Date d);
	

}
