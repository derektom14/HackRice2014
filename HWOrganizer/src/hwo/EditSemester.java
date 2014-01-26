package hwo;

public class EditSemester extends Change{
	private Semester currentVersion;
	private Semester changeToVersion;
	
	EditSemester(Semester currentVersion, Semester changeToVersion) {
		this.currentVersion = currentVersion;
		this.changeToVersion = changeToVersion;
	}
	
	public Change act () {
		Semester oldVersion = new Semester(currentVersion);
		currentVersion.changeTo(changeToVersion);
		return new EditSemester(currentVersion, oldVersion);
	}
	
	public String toString() {
		return "Deleted a semester";
	}
}
