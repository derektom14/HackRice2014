// RepeatAssignment 
// Author: Michael (Jenna - commenting and constructor) 
// Repeat Assignment information - Contains SingleAssignments
// 	Unique Fields: assignments, frequency 
package hwo;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class RepeatAssignment extends AAssignment
{
	private ArrayList<SingleAssignment> assignments;
	private boolean[] frequency;
	
	// Partial Constructor
	public RepeatAssignment (Course course, Date startDate, Date endDate)
	{
		this.assignments = new ArrayList<SingleAssignment>();
		this.frequency = new boolean[7];
		super.setCourse(course);
		super.setStartDate(startDate);
		super.setEndDate(endDate);
		
		super.setHWName(null);
		super.setNotes(null);
		super.setCompletionTime(null);
		super.setAssignmentLoc(null);
		super.setTurninLoc(null);
		super.setResources(null);
		super.setAssignmentType(null);
		super.setDueTime(null);
		
		createSingleAssignments();
	}
	
	// Full Constructor
	public RepeatAssignment (boolean [] frequency, Course course, Date startDate, Date endDate, Time dueTime, String hwName, String addNotes, Duration estTime, String assignmentLoc, String turninLoc, String resources, String typeOfAssignment)
	{
		this.assignments = new ArrayList<SingleAssignment>();
		if (frequency.length == 7)
			this.frequency = frequency;
		super.setCourse(course);
		super.setStartDate(startDate);
		super.setEndDate(endDate);
		super.setHWName(hwName);
		super.setNotes(addNotes);
		super.setCompletionTime(estTime);
		super.setAssignmentLoc(assignmentLoc);
		super.setTurninLoc(turninLoc);
		super.setResources(resources);
		super.setAssignmentType(typeOfAssignment);
		super.setDueTime(dueTime);
		
		createSingleAssignments();
	}
	
	// Extension of constructor to populate this.assignments
	public void createSingleAssignments()
	{
		Calendar c = Calendar.getInstance();
		
		// Loop through all dates in range
		// For each date
			// Find what day of the week it is
		c.setTime(date);
		int dayOfWeek - c.get(Calendar.DAY_OF_WEEK);
			// if the frequency incudes that day of the week
			if frequency[dayOfWeek]
				// add it to the dueDates list
					add date to dueDates
		// For each dueDate in the dueDates list
		for (dueDate : dueDates)
			// create a new SingleAssignment with that due date
			this.assignments.append(new SingleAssignment(dueDate, this));
	}
	
	//Accessor methods
	public boolean[] getFrequency(){return this.frequency;}
	public ArrayList<SingleAssignment> getAssignments(){return this.assignments;}
	// Return course value if this value is null
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
	public Time getDueTime()
	{
		if (super.getDueTime() == null)
			return getCourse().getDueTime();
		return super.getDueTime();
	}
}
