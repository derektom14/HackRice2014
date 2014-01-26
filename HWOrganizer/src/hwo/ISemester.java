// ISemester
// Author: Michael Peirce
// Owner: Jenna Netland
// An interface for Semesters: Semesters are start and end dates used by Courses

package hwo;

import java.util.HashMap;
import java.util.Calendar;
import java.io.Serializable;

public interface ISemester extends Serializable 
{
	//-------------------------------------------------
	// Getter methods
	//-------------------------------------------------
	public Calendar getStartDate();
	public Calendar getEndDate();
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setStartDate(Calendar startDate);
	public void setEndDate(Calendar endDate);
	public String addCourse(ICourse newCourse);
	public void removeCourse(ICourse course);
	public HashMap<String, ICourse> getCourses();
}
