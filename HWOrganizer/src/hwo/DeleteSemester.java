package hwo;

public class DeleteSemester extends Change{
	private int semesterIndex;
	private Instance instance;
	
	DeleteSemester(int semesterIndex, Instance instance) {
		this.semesterIndex = semesterIndex;
	}
	
	public Change act () {
		return new AddSemester(instance.deleteSemester(semesterIndex), semesterIndex, instance);
	}
	
	public String toString() {
		return "Added a semester";
	}
}
