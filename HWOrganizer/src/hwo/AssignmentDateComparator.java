// Michael

package hwo;

public class AssignmentDateComparator extends AssignmentComparator {

	public int compare(SingleAssignment a, SingleAssignment b) {
		if (a.getDueDate().after(b.getDueDate()))
			return -1;
		if (a.getDueDate().before(b.getDueDate()))
			return 1;
		return 0;
	}
}
