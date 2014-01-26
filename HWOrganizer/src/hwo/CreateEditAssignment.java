package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner.DateEditor;

import net.miginfocom.swing.MigLayout;

public class CreateEditAssignment extends JDialog {
	
	private IAssignment assignment;
	private Map<String, ICourse> courseMap;

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JCheckBox cbRepeating;
	private JCheckBox cbSu;
	private JCheckBox cbM;
	private JCheckBox cbT;
	private JCheckBox cbW;
	private JCheckBox cbTh;
	private JCheckBox cbF;
	private JCheckBox cbS;
	private JComboBox<String> tfWeekFreq;
	private JSlider progress;
	private JComboBox<String> cbCourse;
	private JSpinner spnDue;
	private FileBrowser[] resourcePanels = new FileBrowser[3];
	private JLabel lblStart;
	private JSpinner spnStart;
	private JPanel panel_1;
	private JButton btnComplete;
	private JLabel lblProgress;
	private JLabel lblPriority;
	private JSpinner spnPriority;
	private FileBrowser locPanel;
	private FileBrowser turninPanel;
	
	private boolean resourcePanelModified = false;
	private boolean locPanelModified = false;
	private boolean turninPanelModified = false;
	private JLabel lblNotes;
	private JTextArea textNotes;
	private JLabel lblNewLabel;
	private JSpinner dueTimeSpinner;

	public static RepeatAssignment createNewAssignment(ISemester semester, Frame parent){
		if (semester.getCourses().size() == 0)
			throw new IllegalArgumentException("Cannot create assignment with no courses");
		CreateEditAssignment dialog = new CreateEditAssignment(parent, semester, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
		System.out.println("new assignment: " + dialog.assignment);
		return (RepeatAssignment) dialog.assignment;
	}
	
	public static void editAssignment(SingleAssignment assignment, Frame parent, Instance instance){
		CreateEditAssignment dialog = new CreateEditAssignment(parent, assignment.getCourse().getSemester(), assignment);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public CreateEditAssignment(Frame parent, ISemester semester, SingleAssignment assignment) {
		super(parent, true);
		setTitle(assignment == null ? "Create New Assignment" : "Edit Assignment");
		this.assignment = assignment;
		this.courseMap = semester.getCourses();
		System.out.println(courseMap);
		setBounds(100, 100, 575, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("hidemode 3", "[][grow][][grow][grow]", "[][][][][][center][][][grow][grow][][][grow]"));
		{
			JLabel lblName = new JLabel("Name:");
			contentPanel.add(lblName, "cell 0 0,alignx trailing");
		}
		{
			tfName = new JTextField();
			contentPanel.add(tfName, "cell 1 0,growx");
			tfName.setColumns(10);
		}
		{
			JLabel lblCourse = new JLabel("Course:");
			contentPanel.add(lblCourse, "cell 0 1,alignx trailing");
		}
		{
			System.out.println(courseMap);
			String[] courseNames = new String[courseMap.size() + 1];
			courseNames[0] = "";
			int k = 1;
			for (String cName : courseMap.keySet())
				courseNames[k++] = cName;
			System.out.println(Arrays.toString(courseNames));
			cbCourse = new JComboBox(courseNames);
			cbCourse.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					switchCourse(getCourse((String)cbCourse.getSelectedItem()));
				}
			});
			contentPanel.add(cbCourse, "flowx,cell 1 1,growx");
		}
		{
			lblNewLabel = new JLabel("Due Time:");
			contentPanel.add(lblNewLabel, "cell 0 2,alignx trailing");
		}
		{
			dueTimeSpinner = new JSpinner();
			Date curDueDate;
			if (assignment == null){
				Calendar dueDate = new GregorianCalendar();
				dueDate.set(Calendar.HOUR, 23);
				dueDate.set(Calendar.MINUTE, 59);
				dueDate.set(Calendar.SECOND, 0);
				curDueDate = dueDate.getTime();
			} else {
				Calendar dueDate = assignment.getDueTime();
				System.out.println(dueDate.get(Calendar.HOUR_OF_DAY) + ", " + assignment.getTimeString());
				curDueDate = dueDate.getTime();
			}
			dueTimeSpinner.setModel(new SpinnerDateModel(curDueDate, null, null, Calendar.DAY_OF_YEAR));
			dueTimeSpinner.setEditor(new DateEditor(dueTimeSpinner, "hh:mm a"));
			contentPanel.add(dueTimeSpinner, "cell 1 2");
		}
		{
			lblStart = new JLabel("Start:");
			contentPanel.add(lblStart, "cell 0 3,alignx trailing");
		}
		{
			spnStart = new JSpinner();
			spnStart.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			spnStart.setEditor(new DateEditor(spnStart, "MM/dd/yy"));
			contentPanel.add(spnStart, "flowx,cell 1 3");
		}
		{
		}
		{
			cbRepeating = new JCheckBox("Repeating");
			contentPanel.add(cbRepeating, "cell 0 4,alignx trailing");
		}
		{
			cbSu = new JCheckBox("Su");
			contentPanel.add(cbSu, "flowx,cell 1 4");
		}
		{
			cbM = new JCheckBox("M");
			contentPanel.add(cbM, "cell 1 4");
		}
		{
			cbT = new JCheckBox("T");
			cbT.setToolTipText("");
			contentPanel.add(cbT, "cell 1 4,alignx center");
		}
		{
			cbW = new JCheckBox("W");
			contentPanel.add(cbW, "cell 1 4");
		}
		{
			cbTh = new JCheckBox("Th");
			contentPanel.add(cbTh, "cell 1 4");
		}
		{
			cbF = new JCheckBox("F");
			contentPanel.add(cbF, "cell 1 4");
		}
		{
			cbS = new JCheckBox("S");
			contentPanel.add(cbS, "cell 1 4");
		}
		{
			tfWeekFreq = new JComboBox();
			tfWeekFreq.setModel(new DefaultComboBoxModel(new String[] {"Every week", "Every other week", "Every third week", "Every fourth week"}));
			contentPanel.add(tfWeekFreq, "cell 1 4");
		}
		{
			JLabel lblAssignmentLocation = new JLabel("Assignment Location:");
			contentPanel.add(lblAssignmentLocation, "cell 0 5,alignx trailing");
		}
		{
			locPanel = new FileBrowser(null, null);
			contentPanel.add(locPanel, "cell 1 5,grow");
		}
		{
			JLabel lblTurninLocation = new JLabel("Turn-In Location:");
			contentPanel.add(lblTurninLocation, "cell 0 6,alignx trailing");
		}
		{
			turninPanel = new FileBrowser(null, null);
			contentPanel.add(turninPanel, "cell 1 6,grow");
		}
		{
			JLabel lblFileLocation = new JLabel("Resources:");
			contentPanel.add(lblFileLocation, "cell 0 7,alignx trailing");
		}
		{
			FileBrowser rPanel1 = new FileBrowser(null, null);
			resourcePanels[0] = rPanel1;
			contentPanel.add(rPanel1, "cell 1 7,grow");
		}
		{
			FileBrowser rPanel2 = new FileBrowser(null, null);
			resourcePanels[1] = rPanel2;
			contentPanel.add(rPanel2, "cell 1 8,grow");
		}
		{
			FileBrowser rPanel3 = new FileBrowser(null, null);
			resourcePanels[2] = rPanel3;
			contentPanel.add(rPanel3, "cell 1 9,grow");
		}
		{
			lblProgress = new JLabel("Progress:");
			lblProgress.setHorizontalAlignment(SwingConstants.TRAILING);
			contentPanel.add(lblProgress, "cell 0 10,alignx trailing");
		}
		{
			panel_1 = new JPanel();
			contentPanel.add(panel_1, "cell 1 10,grow");
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
			{
				progress = new JSlider();
				panel_1.add(progress);
				progress.setValue(0);
			}
			{
				btnComplete = new JButton("Complete!");
				btnComplete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						progress.setValue(100);
					}
				});
				panel_1.add(btnComplete);
			}
		}
		{
			lblPriority = new JLabel("Priority:");
			contentPanel.add(lblPriority, "cell 0 11,alignx trailing");
		}
		{
			spnPriority = new JSpinner();
			spnPriority.setModel(new SpinnerNumberModel(2, 1, 5, 1));
			contentPanel.add(spnPriority, "cell 1 11");
		}
		{
			lblNotes = new JLabel("Notes:");
			contentPanel.add(lblNotes, "cell 0 12,alignx trailing");
		}
		{
			textNotes = new JTextArea();
			contentPanel.add(textNotes, "cell 1 12,grow");
		}
		{
			JLabel lblDueDate = new JLabel("End");
			contentPanel.add(lblDueDate, "cell 1 3,alignx trailing");
		}
		spnDue = new JSpinner();
		spnDue.setModel(new SpinnerDateModel(semester.getEndDate().getTime(), null, null, Calendar.DAY_OF_YEAR));
		spnDue.setEditor(new DateEditor(spnDue, "MM/dd/yy"));
		contentPanel.add(spnDue, "cell 1 3");
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						storeAssignment();
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			if (assignment != null)
			{
				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						deleteAssignment();
						setVisible(false);
					}
				});
				buttonPane.add(deleteButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (assignment != null){
			tfName.setText(assignment.getName());
			locPanel.setInfo(assignment.getAssignmentLoc());
			turninPanel.setInfo(assignment.getTurninLoc());
			ArrayList<FileInfo> resources = assignment.getResources();
			for (int k = 0; k < 3; k++)
				resourcePanels[k].setInfo(resources.get(k));
			spnPriority.setValue(assignment.getPriority());
			System.out.println("Selected Course: " + assignment.getCourse().getName());
			cbCourse.setSelectedItem(assignment.getCourse().getName());
			progress.setValue(((SingleAssignment)assignment).getProgress());
			System.out.println("Got progress  " + ((SingleAssignment)assignment).getProgress());
			lblStart.setVisible(false);
			spnStart.setVisible(false);
			cbRepeating.setVisible(false);
			cbSu.setVisible(false);
			cbM.setVisible(false);
			cbT.setVisible(false);
			cbW.setVisible(false);
			cbTh.setVisible(false);
			cbF.setVisible(false);
			cbS.setVisible(false);
			tfWeekFreq.setVisible(false);
			System.out.println("HERE");
		}
		else {
			progress.setVisible(false);
			lblProgress.setVisible(false);
			btnComplete.setVisible(false);
		}
	}
	
	private void switchCourse(ICourse course){
		if (course != null){
			if (!resourcePanelModified){
				ArrayList<FileInfo> resources = course.getResources();
				for (int k = 0; k < resources.size(); k++)
					resourcePanels[k].setInfo(resources.get(k));
			}
			if (!locPanelModified)
				locPanel.setInfo(course.getAssignmentLoc());
			if (!turninPanelModified)
				turninPanel.setInfo(course.getTurninLoc());
		}
	}
	
	private ICourse getCourse(String cName){
		if (cName == null || cName.length() == 0)
			return null;
		if (courseMap.containsKey(cName))
			return courseMap.get(cName);
		throw new IllegalStateException("Chose nonexistent course");
	}
	
	private void storeAssignment(){
		System.out.println("Storing assignment");
		ICourse course = getCourse((String)cbCourse.getSelectedItem());
		boolean[] validDays = {
				cbSu.isSelected(),
				cbM.isSelected(),
				cbT.isSelected(),
				cbW.isSelected(),
				cbTh.isSelected(),
				cbF.isSelected(),
				cbS.isSelected()
		};
		
		Date endDate = (Date)spnDue.getValue();
		Calendar endCal = new GregorianCalendar();
		endCal.setTime(endDate);
		
		Date startDate = (Date)spnStart.getValue();
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(startDate);
		
		Date dueTime = (Date)dueTimeSpinner.getValue();
		Calendar dueCal = new GregorianCalendar();
		dueCal.setTime(dueTime);
		
		int frequency = tfWeekFreq.getSelectedIndex() + 1;
		int prog = progress.getValue();
		int priority = (Integer)spnPriority.getValue();
		
		String hwName = tfName.getText();
		FileInfo assignmentLoc = locPanel.getInfo();
		FileInfo turninLoc = turninPanel.getInfo();
		ArrayList<FileInfo> resources = new ArrayList<FileInfo>();
		for (int k = 0; k < 3; k++)
			resources.add(resourcePanels[k].getInfo());
		
		if (assignment == null){
			System.out.println("Creating new assignment");
			assignment = new RepeatAssignment(course, frequency, validDays, hwName, "", null, assignmentLoc, turninLoc, resources, "", dueCal, startCal, endCal, cbRepeating.isSelected(), priority);
		}
		else {
			System.out.println("Storing into previous assignment");
			assignment.setCourse(course);
			assignment.setDueTime(dueCal);
			assignment.setEndDate(endCal);
			assignment.setName(hwName);
			assignment.setAssignmentLoc(assignmentLoc);
			assignment.setName(hwName);
			assignment.setResources(resources);
			assignment.setTurninLoc(turninLoc);
			assignment.setPriority((Integer)spnPriority.getValue());
			((SingleAssignment)assignment).setProgress(prog);
			System.out.println("Set progress " + prog);
		}
	}
	
	private void deleteAssignment(){
		((SingleAssignment)assignment).removeSelf();
	}

}
