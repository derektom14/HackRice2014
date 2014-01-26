// Author: Michael

package hwo;

public class AssignmentNameComparator extends AssignmentComparator {

	public int compare(SingleAssignment a, SingleAssignment b) {
		return a.getName().compareTo(b.getName());
	}
}
