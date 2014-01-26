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
	public String getName();
	public String getNotes();
	public Duration getCompletionTime();
	public ICourse getCourse();
	// Can be from course
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignmentType();
	public Calendar getDueTime();
	// Can be from course / semester
	public Calendar getStartDate();
	public Calendar getEndDate();
	
	//-------------------------------------------------
	// Setter Methods
	//-------------------------------------------------
	public void setName(String name);
	public void setNotes(String notes);
	public void setCompletionTime(Duration time);
	public void setCourse(ICourse course);
	// Can override value from course
	public void setAssignmentLoc(String location);
	public void setTurninLoc(String location);
	public void setResources(String resources);
	public void setAssignmentType(String type);
	public void setDueTime(Calendar time);
	// Can override value from course / semester
	public void setStartDate(Calendar date);
	public void setEndDate(Calendar date);
	

}
