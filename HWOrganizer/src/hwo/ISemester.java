// Author: Michael

package hwo;
import java.util.Date;
import java.util.ArrayList;

public interface ISemester {
	//Accessor methods
	public Date getStartDate();
	public Date getEndDate();
	//Setter methods
	public void setStartDate(Date startDate);
	public void setEndDate(Date endDate);
	public void addCourse(ICourse newCourse);
	public ArrayList<ICourse> getCourses();
}
