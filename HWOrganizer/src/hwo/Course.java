// Course
// Author: Michael Peirce
// Owner: Jenna Netland
// Concrete Course class: Courses hold default values and assignments

package hwo;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Calendar;

public class Course implements ICourse, Serializable
{
	private String name; // Name of the course
	private FileInfo assignmentLoc; // Location of assignment (page number, url...)
	private FileInfo turninLoc; // Location where assignment should be turned in (building, website...)
	private ArrayList<FileInfo> resources; // Location of additional resources for assignment (handouts, forums...)
	private String assignmentType; // Type of assignment (reading, P-set, studying)
	private Calendar dueTime; // Time at which the assignment is due (only concerned with hours / minutes)
	private ArrayList<RepeatAssignment> assignments; // A list of assignments for this course
	private ISemester semester; // The semester when this course occurs
	// Can be from semester
	private Calendar startDate;
	private Calendar endDate;
	
	//-------------------------------------------------
	// Constructors
	//-------------------------------------------------
	
	// Partial Constructor
	private Course (ISemester semester, String name)
	{
		this.assignments = new ArrayList<RepeatAssignment>();
		
		if (semester != null)
		{
			this.semester = semester;
			this.semester.addCourse(this);
		}
		else
			throw new IllegalArgumentException("Tried to use a null semester to create a course.");
		
		this.name = name;
		this.assignmentLoc = null;
		this.turninLoc = null;
		this.resources = null;
		this.assignmentType = "";
	}
	
	// Complete Constructor
	public Course (ISemester semester, String name, FileInfo assignmentLoc, FileInfo turninLoc, ArrayList<FileInfo> resources, String assignmentType, Calendar dueTime)
	{
		this.assignments = new ArrayList<RepeatAssignment>();
		this.name = name;
		this.assignmentLoc = assignmentLoc;
		this.turninLoc = turninLoc;
		this.resources = resources;
		this.assignmentType = assignmentType;
		this.dueTime = dueTime;
		if (semester != null)
		{
			this.semester = semester;
			this.semester.addCourse(this);
		}
		else
			throw new IllegalArgumentException("Tried to use a null semester to create a course.");
		
	}
	
	// Shallow Copy Constructor
	public Course (Course orig)
	{
		this.changeTo(orig);
	}
	
	//-------------------------------------------------
	// Getter Methods
	//-------------------------------------------------
	public String getName() {return name;}
	public FileInfo getAssignmentLoc() {return assignmentLoc;}
	public FileInfo getTurninLoc() {return turninLoc;}
	public ArrayList<FileInfo> getResources() {return resources;}
	public String getAssignmentType() {return assignmentType;}
	public Calendar getDueTime() {return dueTime;}
	public ArrayList<RepeatAssignment> getAssignments() {return assignments;}
	public ISemester getSemester() {return semester;}
	// Can refer to semester
	public Calendar getStartDate() 
	{
		if (this.startDate == null)
			return this.semester.getStartDate();
		return this.startDate;
	}
	public Calendar getEndDate() 
	{
		if (this.endDate == null)
			return this.semester.getEndDate();
		return this.endDate;
	}
	
	//-------------------------------------------------
	// Setter methods
	//-------------------------------------------------
	public void setName(String name) {this.name = name;}
	public void setAssignmentLoc(FileInfo location) {this.assignmentLoc = location;}
	public void setTurninLoc(FileInfo location) {this.turninLoc = location;}
	public void setResources(ArrayList<FileInfo> resources) {this.resources = resources;}
	public void setAssignmentType(String type) {this.assignmentType = type;}
	public void setDueTime(Calendar time) {this.dueTime = time;}
	public void addAssignment(RepeatAssignment assignment) 
	{
		if (assignment != null)
			this.assignments.add(assignment);
		else
			throw new IllegalArgumentException("Tried to add a null assignment to a course.");
	}
	public void removeAssignment(RepeatAssignment assignment)
	{
		this.assignments.remove(assignment);
	}
	public void setSemester(ISemester semester) 
	{
		if (semester != null)
		{
			this.semester.removeCourse(this);
			this.semester = semester;
			this.semester.addCourse(this);
		}
		else
			throw new IllegalArgumentException("Tried to assign a course to a null semester.");
	}
	// Overrides value from semester
	public void setStartDate(Calendar date) 
	{
		if (date.after(this.getEndDate()))
			throw new IllegalArgumentException("Tried to change a course's start date to be later than its end date.");
		else
			this.startDate = date;
	}
	public void setEndDate(Calendar date) 
	{
		if (date.before(this.getStartDate()))
			throw new IllegalArgumentException("Tried to change a course's end date to be earlier than its start date.");
		else
			this.endDate = date;
	}
	
	//-------------------------------------------------
	// Class methods
	//-------------------------------------------------
	public void removeSelf()
	{
		this.semester.removeCourse(this);
	}
	public String validateAssignment(int frequency, boolean[] validDays, Calendar startDate, Calendar endDate)
	{
		if (frequency < 1)
			return "Tried to set an assignment's frequency to a negative value.";
		if (validDays.length != 7)
			return "Tried to use a validDays array of a length that wasn't 7.";
		if (startDate.after(endDate))
			return "Tried to set an assignment's start date to be later than its end date.";
		
		return null; // null indicates no errors were found
	}
	public void changeTo(Course from)
	{
		this.name = from.name;
		this.assignmentLoc = from.assignmentLoc;
		this.turninLoc = from.turninLoc;
		this.resources = from.resources;
		this.assignmentType = from.assignmentType;
		this.dueTime = from.dueTime;
		this.assignments = from.assignments;
		this.semester = from.semester;
		this.startDate = from.startDate;
		this.endDate = from.endDate;
	}
} 
