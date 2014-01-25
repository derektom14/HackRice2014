// ISemester
// Author: Michael Pierce
// Owner: Jenna Netland
// An interface for semesters - semesters

package hwo;
import java.util.Calendar;
import java.util.ArrayList;

public interface ISemester {
	//Accessor methods
	public Calendar getStartDate();
	public Calendar getEndDate();
	//Setter methods
	public void setStartDate(Calendar startDate);
	public void setEndDate(Calendar endDate);
	public void addCourse(ICourse newCourse);
	public ArrayList<ICourse> getCourses();
}
