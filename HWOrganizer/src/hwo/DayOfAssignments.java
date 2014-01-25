package hwo;

import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;

public class DayOfAssignments extends JPanel {

	private JList<SingleAssignment> list;
	private DefaultListModel<SingleAssignment> model;
	private Date date;
	
	/**
	 * Create the panel.
	 */
	public DayOfAssignments(Date date) {
		this.date = date;
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
