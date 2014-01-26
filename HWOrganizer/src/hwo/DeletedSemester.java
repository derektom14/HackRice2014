package hwo;

public class DeletedSemester extends Change{
	private Semester semester;
	private int semesterIndex;
	
	DeletedSemester(Semester semester, int semesterIndex) {
		this.semester = semester;
		this.semesterIndex = semesterIndex;
	}
	
	public void act (Instance instance) {
		instance.addSemester(semester, semesterIndex);
	}
	
	public String toString() {
		return "Deleted a semester";
	}
}
