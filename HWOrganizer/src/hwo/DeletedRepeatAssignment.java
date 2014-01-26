package hwo;

public class DeletedRepeatAssignment extends Change{
	private RepeatAssignment rAssignment;
	
	DeletedRepeatAssignment(RepeatAssignment rAssignment) {
		this.rAssignment = rAssignment;
	}
	
	public void act (Instance instance) {
		rAssignment.getCourse().addAssignment(rAssignment);
	}
	
	public String toString() {
		return "Deleted the repeated assignment " + rAssignment.getName();
	}
}
