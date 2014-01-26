package hwo;

public class DeletedSemester extends Change{
	private Semester semester;
	private int semesterIndex;
	
	DeletedSemester(Semester semester) {
		this.semester = semester;
	}
	
	public void act (Instance instance) {
		instance.addSemester(semester, semesterIndex);
	}
	
	public String toString() {
		return "Deleted a semester";
	}
}
