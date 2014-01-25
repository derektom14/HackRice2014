// Author: Michael

package hwo;

public class AssignmentNameComparator extends AssignmentComparator {

	public int compare(SingleAssignment a, SingleAssignment b) {
		return a.getHWName().compareTo(b.getHWName());
	}
}
