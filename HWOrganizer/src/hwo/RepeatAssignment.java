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
			throw new IllegalArgumentException("Cannot create an assignment without a course");
		
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
			FileInfo assignmentLoc, FileInfo turninLoc, ArrayList<FileInfo> resources, String assignmentType, Calendar dueTime, Calendar startDate, Calendar endDate,
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
			throw new IllegalArgumentException("End date cannot be earlier than start date.");
		this.assignments = new ArrayList<SingleAssignment>();
		
		super.setName(name);
		super.setNotes(notes);
		super.setCompletionTime(completionTime);
		super.setPriority(priority);
		
		// Add this to course
		if (course != null)
		{
			super.setCourse(course);
			super.getCourse().addAssignment(this);
		}
		else
			throw new IllegalArgumentException("Cannot have an assignment without a course");
		
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
	
	// Shallow Copy Constructor
	public RepeatAssignment(RepeatAssignment orig)
	{
		this.changeTo(orig);
	}
	
	// Creates one SingleAssignment
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
				int numWeeks = (weekCounter / 7) + 1;
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
	public FileInfo getAssignmentLoc()
	{
		if (super.getAssignmentLoc() == null)
			return getCourse().getAssignmentLoc();
		return super.getAssignmentLoc();
	}
	public FileInfo getTurninLoc()
	{
		if (super.getTurninLoc() == null)
			return getCourse().getTurninLoc();
		return super.getTurninLoc();
	}
	public ArrayList<FileInfo> getResources()
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
		{
			super.getCourse().removeAssignment(this);
			super.setCourse(course);
			super.getCourse().addAssignment(this);
		}
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
	public void addAssignment(SingleAssignment assignment) {this.assignments.add(assignment);}
	
	//-------------------------------------------------
	// Class Methods
	//-------------------------------------------------
	public RepeatAssignment removeAssignment(SingleAssignment assignment)
	{
		this.assignments.remove(assignment);
		/*
		if (this.assignments.size() == 0)
		{
			this.getCourse().removeAssignment(this);
			return this;
		}*/
		return null;
	}
	public void changeTo(RepeatAssignment from)
	{
		super.changeTo(from);
		this.frequency = from.frequency;
		this.validDays = from.validDays;
		this.assignments = from.assignments;
	}
}



