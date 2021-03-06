package hwo;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
	private AssignmentDisplay assignmentInfo;
	
	/**
	 * Create the panel.
	 * @param assignmentInfo 
	 */
	public DayOfAssignments(Calendar curDay, AssignmentDisplay assignmentInfo) {
		this.day = curDay;
		this.assignmentInfo = assignmentInfo;
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		SimpleDateFormat format = new SimpleDateFormat("E, M/dd");
		
		JLabel lblNameOfDay = new JLabel(format.format(curDay.getTime()));
		panel_1.add(lblNameOfDay);
		
		model = new DefaultListModel<SingleAssignment>();
		
		JPanel panel = new JPanel();
		add(panel);
		list = new AssignmentList(model);
		panel.add(list);
		list.setModel(model);
		list.setCellRenderer(new AssignmentListCellRenderer(assignmentInfo));

	}

	public void addAssignment(SingleAssignment assignment){
		model.addElement(assignment);
	}

	public void mouseClicked(Point point) {
		int index = list.locationToIndex(point);
		System.out.println(assignmentInfo + ", " + model);
		assignmentInfo.setAssignment(model.get(index));
	}
}