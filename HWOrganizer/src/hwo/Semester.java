//Author: Michael

package hwo;
import java.util.Calendar;
import java.util.ArrayList;

public class Semester implements ISemester, java.io.Serializable{
	private Calendar startDate;
	private Calendar endDate;
	private ArrayList<ICourse> courses;
	
	// Constructor
	public Semester(Calendar startDate, Calendar endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	//Accessor methods
	public Calendar getStartDate(){return startDate;}
	public Calendar getEndDate() {return endDate;}
	
	//Setter methods
	public void setStartDate(Calendar startDate){this.startDate = startDate;}
	public void setEndDate(Calendar endDate) {this.endDate = endDate;}
	public void addCourse(ICourse course) {courses.add(course);}
	public ArrayList<ICourse> getCourses() {return courses;}
}
