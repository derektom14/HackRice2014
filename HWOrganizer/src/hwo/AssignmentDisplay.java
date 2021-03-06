package hwo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class AssignmentDisplay extends JPanel {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E, M/d"),  dateFormat2 = new SimpleDateFormat("h:mm a");
	
	private JLabel lblAssignmentName;
	private JLabel lblDueVal;
	private JLabel lblPriorityVal;
	private JLabel lblInfoVal;
	private JLabel lblTurnin;
	private JLabel lblTurninVal;
	private JLabel lblResources;
	private JLabel lblRVal1;
	private JButton btnEdit;

	private SingleAssignment assignment;
	private JLabel lblCourse;
	private JLabel lblCourseVal;
	private JLabel lblRVal2;
	private JLabel lblRVal3;
	
	private JLabel[] resourceVals = new JLabel[3];

	private MainFrame mainFrame;

	private Instance instance;
	private JProgressBar progressBar;
	private JLabel lblTime;
	private JLabel lblTimeVal;

	/**
	 * Create the panel.
	 * @param mainFrame 
	 */
	public AssignmentDisplay(final Instance instance, MainFrame mainFrame) {
		this.instance = instance;
		this.mainFrame = mainFrame;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[][]", "[][][][][][][][][][][][]"));
		
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
		
		lblTime = new JLabel("Time:");
		add(lblTime, "cell 0 3");
		
		lblTimeVal = new JLabel("");
		add(lblTimeVal, "cell 1 3,alignx left,aligny bottom");
		
//		JLabel lblPriority = new JLabel("Priority:");
//		add(lblPriority, "cell 0 3");
//		
//		lblPriorityVal = new JLabel("");
//		add(lblPriorityVal, "cell 1 3,alignx left,aligny bottom");
		
		JLabel lblInformation = new JLabel("Location:");
		add(lblInformation, "cell 0 5");
		
		lblInfoVal = new JLabel("");
		add(lblInfoVal, "cell 1 5");
		
		lblTurnin = new JLabel("Turn-in:");
		add(lblTurnin, "cell 0 6");
		
		lblTurninVal = new JLabel("");
		add(lblTurninVal, "cell 1 6,alignx right,aligny bottom");
		
		lblResources = new JLabel("Resources:");
		add(lblResources, "cell 0 7");
		
		lblRVal1 = new JLabel("");
		resourceVals[0] = lblRVal1;
		add(lblRVal1, "cell 1 7");
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edit();
			}
		});
		
		lblRVal2 = new JLabel("");
		resourceVals[1] = lblRVal2;
		add(lblRVal2, "cell 1 8");
		
		lblRVal3 = new JLabel("");
		resourceVals[2] = lblRVal3;
		add(lblRVal3, "cell 1 9");
		
		progressBar = new JProgressBar();
		add(progressBar, "cell 0 10 2 1");
		add(btnEdit, "cell 0 11");

	}

	public void setAssignment(SingleAssignment assignment) {
		this.assignment = assignment;
		lblAssignmentName.setText(assignment.getName());
		lblCourseVal.setText(assignment.getCourse().getName());
		lblDueVal.setText(dateFormat.format(assignment.getDueDate().getTime()));
		lblTimeVal.setText(dateFormat2.format(assignment.getDueTime().getTime()));
		//lblPriorityVal.setText(String.valueOf(assignment.getPriority()));
		progressBar.setValue(assignment.getProgress());
		delinkify(lblInfoVal);
		delinkify(lblTurninVal);
		linkify(lblInfoVal, assignment.getAssignmentLoc());
		linkify(lblTurninVal, assignment.getTurninLoc());
		
		ArrayList<FileInfo> resources = assignment.getResources();
		for (int k = 0; k < resources.size(); k++) {
			delinkify(resourceVals[k]);
			linkify(resourceVals[k], resources.get(k));
		}
		setVisible(true);
	}
	
	public void clear(){
		this.assignment = null;
		lblAssignmentName.setText("");
		lblCourseVal.setText("");
		lblDueVal.setText("");
		//lblPriorityVal.setText("");
		progressBar.setValue(0);
		delinkify(lblInfoVal);
		delinkify(lblTurninVal);
		for(int k = 0; k < 3; k++)
			delinkify(resourceVals[k]);
		repaint();
	}

	private void linkify(JLabel label, FileInfo info) {
		String loc = info.getLoc();
		String name = info.getName();
		boolean isFile = Util.isFile(loc);
		boolean isURL = Util.isURL(loc);
		if (isFile || isURL){
			if (name.isEmpty())
				if (isFile)
					name = "File";
				else
					name = "Link";
			label.setText("<html><a href=\"" + loc + "\">" + name + "</a></html>");
			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			if (isURL)
				label.addMouseListener(new OpenURL(loc));
			else
				label.addMouseListener(new OpenFile(loc));
		}
		else{
			label.setText(name);
		}
	}
	
	private void delinkify(JLabel label){
		label.setText("");
		label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		for (MouseListener listener : label.getMouseListeners())
			label.removeMouseListener(listener);
	}
	
	public void edit(){
		CreateEditAssignment.editAssignment(assignment, (Frame)SwingUtilities.windowForComponent(AssignmentDisplay.this), instance);
		if (assignment.getParentAssignment().getAssignments().contains(assignment))
			setAssignment(assignment);
		else{
			setVisible(false);
			clear();
		}
		mainFrame.repaint();
		mainFrame.fillListOfDays();
	}

}
