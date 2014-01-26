package hwo;

public class DeleteRepeatAssignment extends Change{
	private RepeatAssignment rAssignment;
	private int index;
	
	DeleteRepeatAssignment(RepeatAssignment rAssignment, int index) {
		this.rAssignment = rAssignment;
		this.index = index;
	}
	
	public Change act () {
		rAssignment.getCourse().removeAssignment(rAssignment);
		rAssignment.getCourse().addAssignment(rAssignment);
		return new AddRepeatAssignment(rAssignment, index);
	}
	
	public String toString() {
		return "Deleted the repeated assignment " + rAssignment.getName();
	}
}
