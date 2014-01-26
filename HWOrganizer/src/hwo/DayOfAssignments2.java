package hwo;

import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JButton;

public class DayOfAssignments2 extends JPanel {

	private JList<SingleAssignment> list;
	private DefaultListModel<SingleAssignment> model;
	private Calendar day;
	
	/**
	 * Create the panel.
	 */
	public DayOfAssignments2(Calendar curDay) {
		this.day = curDay;
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		JLabel lblNameOfDay = new JLabel("Name of Day");
		panel_1.add(lblNameOfDay);
		
		model = new DefaultListModel<SingleAssignment>();
		
		JPanel panel = new JPanel();
		add(panel);
		list = new AssignmentList(model);
		panel.add(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Item 1", "Item 2", "a", "b", "c", "d", "e", "f", "g"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(null);
		panel.add(lblNewLabel);

	}

	public void addAssignment(SingleAssignment assignment){
		model.addElement(assignment);
	}
}