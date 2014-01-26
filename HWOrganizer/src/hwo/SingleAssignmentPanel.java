package hwo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

// Panel that displays a single assignment

public class SingleAssignmentPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SingleAssignmentPanel(SingleAssignment assignment) {
		
		JLabel lblclassHwName = new JLabel("(class) HW Name - Due Time");
		lblclassHwName.setText(assignment.getCourse().toString() + " " + assignment.getName().toString() +
				" " + assignment.getTimeString());
		add(lblclassHwName);
		
		JCheckBox isCompleted = new JCheckBox("");
		add(isCompleted);

	}

}
