//Author: Michael

package hwo;
import java.util.Date;
import java.util.ArrayList;

public class Semester implements ISemester, java.io.Serializable{
	private Date startDate;
	private Date endDate;
	private ArrayList<ICourse> courses;
	
	//Accessor methods
	public Date getStartDate(){return startDate;}
	public Date getEndDate() {return endDate;}
	
	//Setter methods
	public void setStartDate(Date startDate){this.startDate = startDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	public void addClass(ICourse course) {courses.add(course);}
	public ArrayList<ICourse> getClasses() {return courses;}
	
	
}
