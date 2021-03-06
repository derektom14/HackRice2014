package hwo;

public class DeleteCourse extends Change{
	private ICourse course;
	
	DeleteCourse(ICourse course) {
		this.course = course;
	}
	
	public Change act () {
		course.getSemester().removeCourse(course);
		return new AddCourse(course);
	}
	
	public String toString() {
		return "Added the course " + course.getName();
	}
}
