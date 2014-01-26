package hwo;

public class DeletedCourse extends Change{
	private Course course;
	
	DeletedCourse(Course course) {
		this.course = course;
	}
	
	public void act (Instance instance) {
		course.getSemester().addCourse(course);
	}
	
	public String toString() {
		return "Deleted the course " + course.getName();
	}
}
