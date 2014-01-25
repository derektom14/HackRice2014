// Author: Michael

package hwo;
import java.util.ArrayList;
import java.util.Calendar;

public interface ICourse {
	
	//Accessor methods
	public String getName();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignmentType();
	public ISemester getSemester();
	public Calendar getDueTime();
	public ArrayList<RepeatAssignment> getAssignments();
	public Calendar getStartDate();
	public Calendar getEndDate();
	
	//Setter methods
	public void setName(String name);
	public void setAssignmentLoc(String location);
	public void setTurninLoc(String location);
	public void setResources(String resources);
	public void setAssignmentType(String type);
	public void setSemester(ISemester semester);
	public void setDueTime(Calendar t);
	public void setStartDate(Calendar date);
	public void setEndDate(Calendar date);
}
