// IAssignment
// Author: Jenna Netland
// Owner: Jenna Netland
// Interface for SingleAssignment and RepeatAssignment objects: Assignments hold the details of homework assignments

package hwo;

import java.util.Calendar;
import java.time.Duration;

public interface IAssignment 
{
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public String getNotes();
	public Duration getCompletionTime();
	public Course getCourse();
	// Can be from course
	public String getName();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignmentType();
	public Calendar getDueTime();
	// Can be from semester
	public Calendar getStartDate();
	public Calendar getEndDate();
	
	//-------------------------------------------------
	// Setter Methods
	//-------------------------------------------------
	public void setNotes(String s);
	public void setCompletionTime(Duration d);
	public void setCourse(Course c);
	// Can override value from course
	public void setName(String s);
	public void setAssignmentLoc(String s);
	public void setTurninLoc(String s);
	public void setResources(String s);
	public void setAssignmentType(String s);
	public void setDueTime(Calendar t);
	// Can override value from semester
	public void setStartDate(Calendar d);
	public void setEndDate(Calendar d);
	

}
