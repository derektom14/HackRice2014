package hwo;

public class AddRepeatAssignment extends Change{
	private RepeatAssignment rAssignment;
	private int index;
	
	AddRepeatAssignment(RepeatAssignment rAssignment, int index) {
		this.rAssignment = rAssignment;
		this.index = index;
	}
	
	public Change act () {
		rAssignment.getCourse().addAssignment(rAssignment);
		return new DeleteRepeatAssignment(rAssignment);
	}
	
	public String toString() {
		return "Deleted the repeated assignment " + rAssignment.getName();
	}
}
