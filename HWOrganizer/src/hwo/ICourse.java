// Author: Michael

package hwo;

public interface ICourse {
	
	//Accessor methods
	public String getName();
	public String getAssignmentLocation();
	public String getTurnInLocation();
	public String getResources();
	public String getAssignmentType();
	public ISemester getSemester();
	
	//Setter methods
	public void setName(String name);
	public void setAssignmentLocation(String location);
	public void setTurnInLocation(String location);
	public void setResources(String resources);
	public void setAssignmentType(String type);
	public void setSemester(ISemester semester);
}
