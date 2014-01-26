package hwo;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
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

	/**
	 * Create the panel.
	 */
	public AssignmentDisplay() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[][]", "[][][][][][]"));
		
		lblAssignmentName = new JLabel("Assignment Name");
		add(lblAssignmentName, "cell 0 0");
		
		JLabel lblDue = new JLabel("Due:");
		add(lblDue, "cell 0 1");
		
		lblDueVal = new JLabel("");
		add(lblDueVal, "cell 1 1");
		
		JLabel lblPriority = new JLabel("Priority:");
		add(lblPriority, "cell 0 2");
		
		lblPriorityVal = new JLabel("");
		add(lblPriorityVal, "cell 1 2,alignx left,aligny bottom");
		
		JLabel lblInformation = new JLabel("Location:");
		add(lblInformation, "cell 0 3");
		
		lblInfoVal = new JLabel("");
		add(lblInfoVal, "cell 1 3");
		
		lblTurnin = new JLabel("Turn-in:");
		add(lblTurnin, "cell 0 4");
		
		lblTurninVal = new JLabel("");
		add(lblTurninVal, "cell 1 4,alignx right,aligny bottom");
		
		lblResources = new JLabel("Resources:");
		add(lblResources, "cell 0 5");
		
		lblResourcesVal = new JLabel("");
		add(lblResourcesVal, "cell 1 5");

	}

	public void setAssignment(SingleAssignment assignment) {
		lblAssignmentName.setText(assignment.getName());
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
