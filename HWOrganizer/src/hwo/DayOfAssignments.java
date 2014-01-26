package hwo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.BoxLayout;
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
	 * @param assignmentInfo 
	 */
	public DayOfAssignments(Calendar curDay, AssignmentDisplay assignmentInfo) {
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
		list.setModel(model);
		list.setCellRenderer(new AssignmentListCellRenderer(assignmentInfo));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(null);
		panel.add(lblNewLabel);
		
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("CLICK");
			}
		});

	}

	public void addAssignment(SingleAssignment assignment){
		model.addElement(assignment);
	}
}