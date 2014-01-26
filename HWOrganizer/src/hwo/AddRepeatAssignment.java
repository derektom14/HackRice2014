package hwo;

public class AddRepeatAssignment extends Change{
	private RepeatAssignment rAssignment;
	
	AddRepeatAssignment(RepeatAssignment rAssignment) {
		this.rAssignment = rAssignment;
	}
	
	public void act (Instance instance) {
		rAssignment.getCourse().addAssignment(rAssignment);
	}
	
	public String toString() {
		return "Deleted the repeated assignment " + rAssignment.getName();
	}
	
	public Change getReverse() {
		return new DeleteRepeatAssignment(rAssignment);
	}
}
