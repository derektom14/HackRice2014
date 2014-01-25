// Author: Michael
package hwo;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class RepeatAssignment extends AAssignment{
	private ArrayList<SingleAssignment> assignments;
	private boolean[] frequency;
	
	//Accessor methods
	public String getAssignmentLoc(){
		if (super.getAssignmentLoc() == null)
			return getCourse().getAssignmentLoc();
		return super.getAssignmentLoc();
	}
	public String getTurninLoc(){
		if (super.getTurninLoc() == null)
			return getCourse().getTurninLoc();
		return super.getTurninLoc();
	}
	public String getResources(){
		if (super.getResources() == null)
			return getCourse().getResources();
		return super.getResources();
	}
	public String getAssignemntType(){
		if (super.getAssignmentType() == null)
			return getCourse().getAssignmentType();
		return super.getAssignmentType();
	}
	public boolean[] getFrequency(){return frequency;}
}
