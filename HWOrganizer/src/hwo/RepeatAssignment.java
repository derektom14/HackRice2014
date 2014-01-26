// RepeatAssignment 
// Author: Michael Peirce
// Owner: Jenna Netland
// Concrete RepeatAssignment Class: RepeatAssignments hold linked SingleAssignments and some shared information

package hwo;

import java.util.ArrayList;
import java.util.Calendar;
import java.time.Duration;
import java.util.GregorianCalendar;

public class RepeatAssignment extends AAssignment
{
	private int frequency; // in weeks - how often an assignment should occur
	private boolean[] validDays;
	private ArrayList<SingleAssignment> assignments;
	
	//-------------------------------------------------
	// Constructors
	//-------------------------------------------------
	
	// Partial Constructor (*does not create SingleAssignments)
	public RepeatAssignment (Course course)
	{
		this.frequency = 1;
		this.validDays = new boolean[7];
		this.assignments = new ArrayList<SingleAssignment>();
		
		super.setName(null);
		super.setNotes(null);
		super.setCompletionTime(null);
		
		// Add this to course
		super.setCourse(course);
		super.getCourse().addAssignment(this);
		
		// Signals to refer to the parent course
		super.setAssignmentLoc(null);
		super.setTurninLoc(null);
		super.setResources(null);
		super.setAssignmentType(null);
		super.setDueTime(null);
		// Signals to refer to the parent course / semester
		super.setStartDate(null);
		super.setEndDate(null);
		
		super.getCourse().addAssignment(this);
	}
	
	// Full Constructor
	public RepeatAssignment (Course course, int frequency, boolean[] validDays, String name, String notes, Duration completionTime,
			String assignmentLoc, String turninLoc, String resources, String assignmentType, Calendar dueTime, Calendar startDate, Calendar endDate)
	{
		this.frequency = frequency;
		this.validDays = validDays;
		this.assignments = new ArrayList<SingleAssignment>();
		
		super.setName(name);
		super.setNotes(notes);
		super.setCompletionTime(completionTime);
		
		// Add this to course
		super.setCourse(course);
		super.getCourse().addAssignment(this);
		
		// Can refer to course
		super.setAssignmentLoc(assignmentLoc);
		super.setTurninLoc(turninLoc);
		super.setResources(resources);
		super.setAssignmentType(assignmentType);
		super.setDueTime(dueTime);
		// Can refer to course / semester
		super.setStartDate(startDate);
		super.setEndDate(endDate);
		
		// Populates single assignments
		createSingleAssignments();
	}
	
	// Extension of constructor to create assignments -- Michael
	public void createSingleAssignments()
	{
		
		GregorianCalendar g = new GregorianCalendar(getStartDate().get(Calendar.YEAR),
				getStartDate().get(Calendar.MONTH), getStartDate().get(Calendar.DAY_OF_MONTH));
		do {
			if (validDays[g.get(Calendar.DAY_OF_WEEK) - 1])
				this.assignments.add(new SingleAssignment(g, this));
		} while (g.before(getEndDate()));
	}
	
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public int getFrequency(){return this.frequency;}
	public boolean[] getValidDays(){return this.validDays;}
	public ArrayList<SingleAssignment> getAssignments(){return this.assignments;}
	// Can refer to course
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
	public Calendar getDueTime()
	{
		if (super.getDueTime() == null)
			return getCourse().getDueTime();
		return super.getDueTime();
	}
	// Can refer to course / semester
	public Calendar getStartDate()
	{
		if (super.getStartDate() == null)
			return getCourse().getStartDate();
		return super.getStartDate();
	}
}
