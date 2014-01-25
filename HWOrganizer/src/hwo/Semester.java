//Author: Michael

package hwo;
import java.util.Date;
import java.util.ArrayList;

public class Semester implements ISemester{
	private Date startDate;
	private Date endDate;
	private ArrayList<IClass> classes;
	
	public Date getStartDate(){return startDate;}
	public Date getEndDate() {return endDate;}
	public void setStartDate(Date startDate){this.startDate = startDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	public void addClass(IClass newClass) {classes.add(newClass);}
	public ArrayList<IClass> getClasses() {return classes;}
	
	
}
