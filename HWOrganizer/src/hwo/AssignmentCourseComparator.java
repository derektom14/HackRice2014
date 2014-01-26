// Author: Jenna

package hwo;

public class AssignmentCourseComparator extends AssignmentComparator
{
	public int compare(SingleAssignment a, SingleAssignment b) {
		return a.getCourse().getName().compareTo(b.getCourse().getName());
	}
}
