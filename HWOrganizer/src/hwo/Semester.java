// Semester
// Author: Michael Peirce
// Owner: Jenna Netland
// Concrete Semester class: Semesters are start and end dates used by Courses

package hwo;

import java.util.ArrayList;
import java.util.Calendar;

public class Semester implements ISemester, java.io.Serializable
{
	private Calendar startDate; // The day the semester starts (ignores hours and below)
	private Calendar endDate; // The day the semester ends (ignores hours and below)
	private ArrayList<ICourse> courses; // A list of the courses in this semester
	
	//-------------------------------------------------
	// Constructor
	//-------------------------------------------------
	public Semester(Calendar startDate, Calendar endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	//-------------------------------------------------
	// Getter methods
	//-------------------------------------------------
	public Calendar getStartDate(){return startDate;}
	public Calendar getEndDate() {return endDate;}
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setStartDate(Calendar startDate){this.startDate = startDate;}
	public void setEndDate(Calendar endDate) {this.endDate = endDate;}
	public void addCourse(ICourse course) {courses.add(course);}
	public ArrayList<ICourse> getCourses() {return courses;}
}
