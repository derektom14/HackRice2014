// RepeatAssignment 
// Author: Michael Peirce
// Owner: Jenna Netland
// Concrete RepeatAssignment Class: RepeatAssignments hold linked SingleAssignments and some shared information

package hwo;

import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.datatype.Duration;
import java.util.GregorianCalendar;

public class RepeatAssignment extends AAssignment
{
	private int frequency; // How often an assignment should occur (in weeks)
	private boolean[] validDays; // 7-entry boolean array representing repeated days (Sunday - Saturday)
	private ArrayList<SingleAssignment> assignments; // Holds SingleAssignments created by this RepeatAssignment
	
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
		if (course != null)
		{
			super.setCourse(course);
			super.getCourse().addAssignment(this);
		}
		else
			throw new IllegalArgumentException("Tried to set an assignment's course to null.");
		
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
	public RepeatAssignment (ICourse course, int frequency, boolean[] validDays, String name, String notes, Duration completionTime,
			String assignmentLoc, String turninLoc, String resources, String assignmentType, Calendar dueTime, Calendar startDate, Calendar endDate,
			boolean repeating, int priority)
	{
		if (frequency >= 1)
			this.frequency = frequency;
		else
			throw new IllegalArgumentException("Tried to set an assignment's frequency to a negative value.");
		if (validDays.length == 7)
			this.validDays = validDays;
		else
			throw new IllegalArgumentException("Tried to use a validDays array of a length that wasn't 7.");
		if (startDate.after(endDate))
			throw new IllegalArgumentException("Tried to set an assignment's start date to be later than its end date.");
		this.assignments = new ArrayList<SingleAssignment>();
		
		super.setName(name);
		super.setNotes(notes);
		super.setCompletionTime(completionTime);
		
		// Add this to course
		if (course != null)
		{
			super.setCourse(course);
			super.getCourse().addAssignment(this);
		}
		else
			throw new IllegalArgumentException("Tried to set an assignment's course to null.");
		
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
		if (repeating)
			createSingleAssignments();
		else
			createSingleAssignment();
	}
	
	private void createSingleAssignment() {
		GregorianCalendar g = new GregorianCalendar(getStartDate().get(Calendar.YEAR),
				getStartDate().get(Calendar.MONTH), getStartDate().get(Calendar.DAY_OF_MONTH));
		this.assignments.add(new SingleAssignment(this, g));
	}

	// Creates SingleAssignments - used by full constructor
	public void createSingleAssignments()
	{
		// Convert Calendar to a changeable format
		GregorianCalendar g = new GregorianCalendar(getStartDate().get(Calendar.YEAR),
				getStartDate().get(Calendar.MONTH), getStartDate().get(Calendar.DAY_OF_MONTH));
		
		// Go through all dates from start to end, adding SingleAssignments when a valid date is reached
		int weekCounter = 0;
		do {
			// Check if valid
			if (validDays[g.get(Calendar.DAY_OF_WEEK) - 1]) // Day of week goes from 1-7, array goes from 0-6
			{
				int numWeeks = (weekCounter % 7) + 1;
				if ((numWeeks + 1) % this.frequency == 0){
					this.assignments.add(new SingleAssignment(this, new GregorianCalendar(g.get(Calendar.YEAR), g.get(Calendar.MONTH), g.get(Calendar.DAY_OF_MONTH))));
				}
			}
			// Increment day and week counter
			g.add(Calendar.DAY_OF_MONTH, 1);
			weekCounter += 1;
		} while (g.before(getEndDate()));
	}
	
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public int getFrequency() {return this.frequency;}
	public boolean[] getValidDays() {return this.validDays;}
	public ArrayList<SingleAssignment> getAssignments() {return this.assignments;}
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
	
	//-------------------------------------------------
	// Setter Methods
	//-------------------------------------------------
	public void setCourse(ICourse course)
	{
		if (course != null)
			super.setCourse(course);
		else
			throw new IllegalArgumentException("Tried to set a repeated assignment's course to null.");
	}
	public void setPriority(int priority) 
	{
		if (priority < 1 || priority > 5)
			throw new IllegalArgumentException("Tried to set an assignment's priority to a value outside the range 1-5.");
		else
			super.setPriority(priority);
	}
	
	//-------------------------------------------------
	// Class Methods
	//-------------------------------------------------
	public void removeAssignment(SingleAssignment assignment)
	{
		this.assignments.remove(assignment);
		if (this.assignments.size() == 0)
			this.getCourse().removeAssignment(this);
	}
}



