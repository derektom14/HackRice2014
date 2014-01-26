package hwo;

public class AddCourse extends Change{
	private Course course;
	
	AddCourse(Course course) {
		this.course = course;
	}
	
	public Change act () {
		course.getSemester().addCourse(course);
		return new DeleteCourse(course);
	}
	
	public String toString() {
		return "Deleted the course " + course.getName();
	}
}
