package hwo;

public class DeleteRepeatAssignment extends Change{
	private RepeatAssignment rAssignment;
	
	DeleteRepeatAssignment(RepeatAssignment rAssignment) {
		this.rAssignment = rAssignment;
	}
	
	public Change act () {
		rAssignment.getCourse().removeAssignment(rAssignment);
		rAssignment.getCourse().addAssignment(rAssignment);
		return new AddRepeatAssignment(rAssignment, rAssignment.getCourse().getAssignments().indexOf(rAssignment));
	}
	
	public String toString() {
		return "Deleted the repeated assignment " + rAssignment.getName();
	}
}
