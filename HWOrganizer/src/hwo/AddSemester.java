package hwo;

public class AddSemester extends Change{
	private Semester semester;
	private int semesterIndex;
	
	AddSemester(Semester semester, int semesterIndex) {
		this.semester = semester;
		this.semesterIndex = semesterIndex;
	}
	
	public void act (Instance instance) {
		instance.addSemester(semester, semesterIndex);
	}
	
	public String toString() {
		return "Deleted a semester";
	}
	
	public Change getReverse() {
		return new DeleteSemester();
	}
}
