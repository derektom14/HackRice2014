package hwo;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class AssignmentDisplay extends JPanel {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E, M d");
	
	private JLabel lblAssignmentName;
	private JLabel lblDueVal;
	private JLabel lblPriorityVal;
	private JLabel lblInfoVal;
	private JLabel lblTurnin;
	private JLabel lblTurninVal;
	private JLabel lblResources;
	private JLabel lblResourcesVal;
	private JButton btnEdit;

	private SingleAssignment assignment;
	private JLabel lblCourse;
	private JLabel lblCourseVal;

	/**
	 * Create the panel.
	 */
	public AssignmentDisplay() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[][]", "[][][][][][][][]"));
		
		lblAssignmentName = new JLabel("Assignment Name");
		add(lblAssignmentName, "cell 0 0");
		
		lblCourse = new JLabel("Course:");
		add(lblCourse, "cell 0 1");
		
		lblCourseVal = new JLabel("");
		add(lblCourseVal, "cell 1 1");
		
		JLabel lblDue = new JLabel("Due:");
		add(lblDue, "cell 0 2");
		
		lblDueVal = new JLabel("");
		add(lblDueVal, "cell 1 2");
		
		JLabel lblPriority = new JLabel("Priority:");
		add(lblPriority, "cell 0 3");
		
		lblPriorityVal = new JLabel("");
		add(lblPriorityVal, "cell 1 3,alignx left,aligny bottom");
		
		JLabel lblInformation = new JLabel("Location:");
		add(lblInformation, "cell 0 4");
		
		lblInfoVal = new JLabel("");
		add(lblInfoVal, "cell 1 4");
		
		lblTurnin = new JLabel("Turn-in:");
		add(lblTurnin, "cell 0 5");
		
		lblTurninVal = new JLabel("");
		add(lblTurninVal, "cell 1 5,alignx right,aligny bottom");
		
		lblResources = new JLabel("Resources:");
		add(lblResources, "cell 0 6");
		
		lblResourcesVal = new JLabel("");
		add(lblResourcesVal, "cell 1 6");
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateEditAssignment.editAssignment(assignment, (Frame)SwingUtilities.windowForComponent(AssignmentDisplay.this));
			}
		});
		add(btnEdit, "cell 0 7");

	}

	public void setAssignment(SingleAssignment assignment) {
		this.assignment = assignment;
		lblAssignmentName.setText(assignment.getName());
		lblCourseVal.setText(assignment.getCourse().getName());
		lblDueVal.setText(dateFormat.format(assignment.getDueTime().getTime()));
		lblPriorityVal.setText(String.valueOf(assignment.getPriority()));
		lblInfoVal.setText(assignment.getAssignmentLoc());
		lblTurninVal.setText(assignment.getTurninLoc());
		lblResourcesVal.setText(assignment.getResources());
		Util.linkify(lblInfoVal);
		Util.linkify(lblTurninVal);
		Util.linkify(lblResourcesVal);
		setVisible(true);
	}

}
