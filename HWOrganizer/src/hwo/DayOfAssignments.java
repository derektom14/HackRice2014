package hwo;

import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class DayOfAssignments extends JPanel {

	private JList<SingleAssignment> list;
	private DefaultListModel<SingleAssignment> model;
	private Calendar day;
	
	/**
	 * Create the panel.
	 */
	public DayOfAssignments(Calendar curDay) {
		this.day = curDay;
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JLabel lblNameOfDay = new JLabel("Name of Day");
		add(lblNameOfDay);
		
		model = new DefaultListModel<SingleAssignment>();
		list = new AssignmentList(model);
		add(list);

	}

	public void addAssignment(SingleAssignment assignment){
		model.addElement(assignment);
	}
}