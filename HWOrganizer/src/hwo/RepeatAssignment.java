// RepeatAssignment
// Author: Michael (Jenna - commenting and constructor) 
// Repeat Assignment information - Contains SingleAssignments
// 	Unique Fields: assignments, frequency
package hwo;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class RepeatAssignment extends AAssignment
{
	private ArrayList<SingleAssignment> assignments;
	private boolean[] frequency;
	
	// Constructor
	public RepeatAssignment ()
	{
		
	}
	
	//Accessor methods
	public String getAssignmentLoc()
	{
		if (super.getAssignmentLoc() == null)
			return getCourse().getAssignmentLoc();
		return super.getAssignmentLoc();
	}
	public String getTurninLoc()
	{
		if (super.getTurninLoc() == null)
			return getCourse().getTurninLoc();
		return super.getTurninLoc();
	}
	public String getResources()
	{
		if (super.getResources() == null)
			return getCourse().getResources();
		return super.getResources();
	}
	public String getAssignemntType()
	{
		if (super.getAssignmentType() == null)
			return getCourse().getAssignmentType();
		return super.getAssignmentType();
	}
	public boolean[] getFrequency(){return this.frequency;}
	public ArrayList<SingleAssignment> getAssignments(){return this.assignments;}
}
