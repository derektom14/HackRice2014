// Author: Michael

package hwo;

public interface IClass {
	public String getName();
	public String getAssignmentLocation();
	public String getTurnInLocation();
	public String getResources();
	public String getAssignmentType();
	public ISemester getSemester();
	public void setName(String name);
	public void setAssignmentLocation(String location);
	public void setTurnInLocation(String location);
	public void setResources(String resources);
	public void setAssignmentType(String type);
	public void setSemester(ISemester semester);
}
