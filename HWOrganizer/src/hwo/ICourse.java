// ICourse
// Author: Michael Peirce
// Owner: Jenna Netland
// Interface for Courses: Courses hold default values and assignments

package hwo;

import java.util.ArrayList;
import java.util.Calendar;

public interface ICourse 
{	
	//-------------------------------------------------
	// Getter methods
	//-------------------------------------------------
	public String getName();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
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
	public void setAssignmentLoc(String location);
	public void setTurninLoc(String location);
	public void setResources(String resources);
	public void setAssignmentType(String type);
	public void setDueTime(Calendar time);
	public void addAssignment(RepeatAssignment assignment);
	public void setSemester(ISemester semester);
	// Can override value from semester
	public void setStartDate(Calendar date);
	public void setEndDate(Calendar date);
}
