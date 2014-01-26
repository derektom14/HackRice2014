package hwo;

public class AddSingleAssignment extends Change{
	private SingleAssignment sAssignment;
	private int index;
	
	AddSingleAssignment(SingleAssignment sAssignment, int index) {
		this.sAssignment = sAssignment;
		this.index = index;
	}
	
	public Change act () {
		sAssignment.getParentAssignment().addAssignment(sAssignment);
		return new DeleteSingleAssignment(sAssignment, index);
	}
	
	public String toString() {
		return "Deleted the single assignment " + sAssignment.getName() + " due on " + sAssignment.getDueDate();
	}
}
