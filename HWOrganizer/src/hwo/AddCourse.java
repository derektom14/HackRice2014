package hwo;

public class AddCourse extends Change{
	private Course course;
	
	AddCourse(Course course) {
		this.course = course;
	}
	
	public void act (Instance instance) {
		course.getSemester().addCourse(course);
	}
	
	public String toString() {
		return "Deleted the course " + course.getName();
	}
	
	public Change getReverse() {
		return new DeleteCourse(course);
	}
}
