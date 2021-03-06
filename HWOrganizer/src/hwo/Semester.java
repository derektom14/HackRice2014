// Semester
// Author: Michael Peirce
// Owner: Jenna Netland
// Concrete Semester class: Semesters are start and end dates used by Courses

package hwo;

import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Calendar;

public class Semester implements ISemester
{
	private Calendar startDate; // The day the semester starts (ignores hours and below)
	private Calendar endDate; // The day the semester ends (ignores hours and below)
	private HashMap<String, ICourse> courses = new HashMap<String, ICourse>(); // A list of the courses in this semester
	
	//-------------------------------------------------
	// Constructor
	//-------------------------------------------------
	
	// Normal Constructor
	public Semester(Calendar startDate, Calendar endDate)
	{
		if (startDate.after(endDate))
			throw new IllegalArgumentException("Tried to create a semester whose start date was not befor its end date.");
		else
		{
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
	// Shallow Copy Constructor
	public Semester(Semester orig)
	{
		this.changeTo(orig);
	}
	
	//-------------------------------------------------
	// Getter methods
	//-------------------------------------------------
	public Calendar getStartDate(){return startDate;}
	public Calendar getEndDate() {return endDate;}
	public HashMap<String, ICourse> getCourses() {return courses;}
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setStartDate(Calendar startDate) 
	{
		if (startDate.after(this.endDate))
			throw new IllegalArgumentException("Tried to change a semester's start date to be later than its end date.");
		else
			this.startDate = startDate;
	}
	public void setEndDate(Calendar endDate) 
	{
		if (endDate.before(this.startDate))
			throw new IllegalArgumentException("Tried to change a semester's end date to be earlier than its start date.");
		else
			this.endDate = endDate;
	}
	public void addCourse(ICourse course) 
	{
		if (course != null) {
			courses.put(course.getName(), course);
		}
		else
			throw new IllegalArgumentException("Tried to add a null course to a semester.");
	}
	public void removeCourse(ICourse course)
	{
		this.courses.remove(course.getName());
	}
	public String validateCourse(ICourse course) {
		if (courses.containsKey(course.getName()))
			return "There is already a course with the name " + course.getName();
		return null;
	}
	
	//-------------------------------------------------
	// Class methods
	//-------------------------------------------------
	public void changeTo(Semester from)
	{
		this.startDate = from.startDate;
		this.endDate = from.endDate;
		this.courses = from.courses;
	}
}
