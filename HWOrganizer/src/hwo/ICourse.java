// Author: Michael

package hwo;
import java.sql.Time;
import java.util.ArrayList;

public interface ICourse {
	
	//Accessor methods
	public String getName();
	public String getAssignmentLoc();
	public String getTurninLoc();
	public String getResources();
	public String getAssignmentType();
	public ISemester getSemester();
	public Time getDueTime();
	public ArrayList<RepeatAssignment> getAssignments();
	
	//Setter methods
	public void setName(String name);
	public void setAssignmentLoc(String location);
	public void setTurninLoc(String location);
	public void setResources(String resources);
	public void setAssignmentType(String type);
	public void setSemester(ISemester semester);
	public void setDueTime(Time t);
}
