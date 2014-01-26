package hwo;

public class EditRepeatAssignment extends Change{
	private RepeatAssignment currentVersion;
	private RepeatAssignment changeToVersion;
	
	EditRepeatAssignment(RepeatAssignment currentVersion, RepeatAssignment changeToVersion) {
		this.currentVersion = currentVersion;
		this.changeToVersion = changeToVersion;
	}
	
	public Change act () {
		RepeatAssignment oldVersion = new RepeatAssignment(currentVersion);
		currentVersion.changeTo(changeToVersion);
		return new EditRepeatAssignment(currentVersion, oldVersion);
	}
	
	public String toString() {
		return "Edited " + currentVersion.getName();
	}
}
