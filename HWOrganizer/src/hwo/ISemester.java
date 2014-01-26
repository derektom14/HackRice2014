// ISemester
// Author: Michael Pierce
// Owner: Jenna Netland
// An interface for Semesters: Semesters are start and end dates used by Courses

package hwo;

import java.util.ArrayList;
import java.util.Calendar;

public interface ISemester 
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
	public void addCourse(ICourse newCourse);
	public ArrayList<ICourse> getCourses();
}
