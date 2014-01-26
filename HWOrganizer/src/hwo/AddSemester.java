package hwo;

public class AddSemester extends Change{
	private Semester semester;
	private int semesterIndex;
	private Instance instance;
	
	AddSemester(Semester semester, int semesterIndex, Instance instance) {
		this.semester = semester;
		this.semesterIndex = semesterIndex;
		this.instance = instance;
	}
	
	public Change act () {
		instance.addSemester(semester, semesterIndex);
		return new DeleteSemester(semesterIndex, instance);
	}
	
	public String toString() {
		return "Deleted a semester";
	}
}
