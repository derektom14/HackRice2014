package hwo;

public class EditSingleAssignment extends Change{
	private SingleAssignment currentVersion;
	private SingleAssignment changeToVersion;
	
	EditSingleAssignment(SingleAssignment currentVersion, SingleAssignment changeToVersion) {
		this.currentVersion = currentVersion;
		this.changeToVersion = changeToVersion;
	}
	
	public Change act () {
		SingleAssignment oldVersion = new SingleAssignment(currentVersion);
		currentVersion.changeTo(changeToVersion);
		return new EditSingleAssignment(currentVersion, oldVersion);
	}
	
	public String toString() {
		return "Edited " + currentVersion.getName();
	}
}
