package hwo;

public class DeleteSingleAssignment extends Change{
	private SingleAssignment sAssignment;
	private int index;
	
	DeleteSingleAssignment(SingleAssignment sAssignment, int index) {
		this.sAssignment = sAssignment;
		this.index = index;
	}
	
	public Change act () {
		sAssignment.getParentAssignment().removeAssignment(sAssignment);
		return new AddSingleAssignment(sAssignment, index);
	}
	
	public String toString() {
		return "Added the single assignment " + sAssignment.getName() + " due on " + sAssignment.getDueDate();
	}
}
