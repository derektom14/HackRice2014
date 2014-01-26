// ICourse
// Author: Michael Peirce
// Owner: Jenna Netland
// Interface for Courses: Courses hold default values and assignments

package hwo;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;

public interface ICourse extends Serializable
{	
	//-------------------------------------------------
	// Getter methods
	//-------------------------------------------------
	public String getName();
	public FileInfo getAssignmentLoc();
	public FileInfo getTurninLoc();
	public ArrayList<FileInfo> getResources();
	public String getAssignmentType();
	public Calendar getDueTime();
	public ArrayList<RepeatAssignment> getAssignments();
	public ISemester getSemester();
	// Can be from semester
	public Calendar getStartDate();
	public Calendar getEndDate();
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setName(String name);
	public void setAssignmentLoc(FileInfo location);
	public void setTurninLoc(FileInfo location);
	public void setResources(ArrayList<FileInfo> resources);
	public void setAssignmentType(String type);
	public void setDueTime(Calendar time);
	public void addAssignment(RepeatAssignment assignment);
	public void removeAssignment(RepeatAssignment assignment);
	public void setSemester(ISemester semester);
	// Can override value from semester
	public void setStartDate(Calendar date);
	public void setEndDate(Calendar date);
	
	//-------------------------------------------------
	// Class methods
	//-------------------------------------------------
	public void removeSelf();
	public String validateAssignment(int frequency, boolean[] validDays, Calendar startDate, Calendar endDate);
}
