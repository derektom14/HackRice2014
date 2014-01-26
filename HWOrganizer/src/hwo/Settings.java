// Author: Michael
package hwo;

import java.util.Calendar;

public class Settings {
	private ICourse course;
	private Calendar startDate;
	private Calendar endDate;
	
	Settings (ICourse course, Calendar startDate, Calendar endDate) {
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// Accessor methods
	public ICourse getCourse(){return course;}
	public Calendar getStartDate(){return startDate;}
	public Calendar getEndDate(){return endDate;}
	
	// Setter methods
	public void setCourse(ICourse course){this.course = course;}
	public void setStartDate(Calendar startDate){this.startDate = startDate;}
	public void setEndDate(Calendar endDate){this.endDate = endDate;}
}
