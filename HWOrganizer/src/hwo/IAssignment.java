// IAssignment
// Author: Jenna Netland
// Owner: Jenna Netland
// Interface for SingleAssignment and RepeatAssignment objects: Assignments hold the details of homework assignments

package hwo;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;

import javax.xml.datatype.Duration;

public interface IAssignment extends Serializable
{
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public String getName();
	public String getNotes();
	public Duration getCompletionTime();
	public ICourse getCourse();
	// Can be from course
	public FileInfo getAssignmentLoc();
	public FileInfo getTurninLoc();
	public ArrayList<FileInfo> getResources();
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
	public void setPriority(int priority);
	public void setCourse(ICourse course);
	// Can override value from course
	public void setAssignmentLoc(FileInfo location);
	public void setTurninLoc(FileInfo location);
	public void setResources(ArrayList<FileInfo> resources);
	public void setAssignmentType(String type);
	public void setDueTime(Calendar time);
	// Can override value from course / semester
	public void setStartDate(Calendar date);
	public void setEndDate(Calendar date);
	

}
