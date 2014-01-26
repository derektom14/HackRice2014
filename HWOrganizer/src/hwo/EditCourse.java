package hwo;

public class EditCourse extends Change{
	private Course currentVersion;
	private Course changeToVersion;
	
	EditCourse(Course currentVersion, Course changeToVersion) {
		this.currentVersion = currentVersion;
		this.changeToVersion = changeToVersion;
	}
	
	public Change act () {
		Course oldVersion = new Course(currentVersion);
		currentVersion.changeTo(changeToVersion);
		return new EditCourse(currentVersion, oldVersion);
	}
	
	public String toString() {
		return "Deleted the course " + currentVersion.getName();
	}
}
