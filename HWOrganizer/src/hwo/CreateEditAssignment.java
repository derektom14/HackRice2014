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
import java.util.HashMap;
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
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;

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
	private MultiFileBrowser resourcesPanel;
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

	public static RepeatAssignment createNewAssignment(HashMap<String, ICourse> courseMap, Frame parent){
		if (courseMap.size() == 0)
			throw new IllegalArgumentException("Cannot create assignment with no courses");
		CreateEditAssignment dialog = new CreateEditAssignment(parent, courseMap, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
		System.out.println("new assignment: " + dialog.assignment);
		return (RepeatAssignment) dialog.assignment;
	}
	
	public static void editAssignment(SingleAssignment assignment, Frame parent, Instance instance){
		CreateEditAssignment dialog = new CreateEditAssignment(parent, assignment.getCourse().getSemester().getCourses(), assignment);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CreateEditAssignment(null, new HashMap<String, ICourse>(), null).setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public CreateEditAssignment(Frame parent, HashMap<String, ICourse> courseMap, SingleAssignment assignment) {
		super(parent, true);
		setTitle(assignment == null ? "Create New Assignment" : "Edit Assignment");
		this.assignment = assignment;
		this.courseMap = courseMap;
		System.out.println(courseMap);
		setBounds(100, 100, 575, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("hidemode 3", "[][grow][grow][grow]", "[][][][][][center][][grow][][][grow]"));
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
			cbCourse = new JComboBox();//<String>(courseNames);
			cbCourse.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					switchCourse(getCourse((String)cbCourse.getSelectedItem()));
				}
			});
			contentPanel.add(cbCourse, "cell 1 1,growx");
		}
		{
			lblStart = new JLabel("Start:");
			contentPanel.add(lblStart, "cell 0 2,alignx trailing");
		}
		{
			spnStart = new JSpinner();
			spnStart.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			contentPanel.add(spnStart, "cell 1 2");
		}
		{
			JLabel lblDueDate = new JLabel("Due:");
			contentPanel.add(lblDueDate, "cell 0 3,alignx trailing");
		}
		{
			spnDue = new JSpinner();
			spnDue.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
			contentPanel.add(spnDue, "cell 1 3");
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
			resourcesPanel = new MultiFileBrowser(null, null);
			contentPanel.add(resourcesPanel, "cell 1 7,grow");
			resourcesPanel.setLayout(new BoxLayout(resourcesPanel, BoxLayout.X_AXIS));
		}
		{
			lblProgress = new JLabel("Progress:");
			lblProgress.setHorizontalAlignment(SwingConstants.TRAILING);
			contentPanel.add(lblProgress, "cell 0 8,alignx trailing");
		}
		{
			panel_1 = new JPanel();
			contentPanel.add(panel_1, "cell 1 8,grow");
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
			contentPanel.add(lblPriority, "cell 0 9,alignx trailing");
		}
		{
			spnPriority = new JSpinner();
			spnPriority.setModel(new SpinnerNumberModel(2, 1, 5, 1));
			contentPanel.add(spnPriority, "cell 1 9");
		}
		{
			lblNotes = new JLabel("Notes:");
			contentPanel.add(lblNotes, "cell 0 10,alignx trailing");
		}
		{
			textNotes = new JTextArea();
			contentPanel.add(textNotes, "cell 1 10,grow");
		}
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
			resourcesPanel.setInfos(assignment.getResources());
			spnPriority.setValue(assignment.getPriority());
			
			
			cbRepeating.setVisible(false);
			cbSu.setVisible(false);
			cbM.setVisible(false);
			cbT.setVisible(false);
			cbW.setVisible(false);
			cbTh.setVisible(false);
			cbF.setVisible(false);
			cbS.setVisible(false);
			tfWeekFreq.setVisible(false);
		}
		else {
			progress.setVisible(false);
			lblProgress.setVisible(false);
			btnComplete.setVisible(false);
		}
	}
	
	private void switchCourse(ICourse course){
		if (course != null){
			if (!resourcePanelModified)
				resourcesPanel.setInfos(course.getResources());
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
		Date dueDate = (Date)spnDue.getValue();
		Calendar dueCal = new GregorianCalendar();
		dueCal.setTime(dueDate);
		Date startDate = (Date)spnStart.getValue();
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(startDate);
		int frequency = tfWeekFreq.getSelectedIndex() + 1;
		int prog = progress.getValue();
		int priority = (Integer)spnPriority.getValue();
		
		String hwName = tfName.getText();
		FileInfo assignmentLoc = locPanel.getInfo();
		FileInfo turninLoc = turninPanel.getInfo();
		ArrayList<FileInfo> resources = resourcesPanel.getFileInfos();
		
		if (assignment == null){
			assignment = new RepeatAssignment(course, frequency, validDays, hwName, "", null, assignmentLoc, turninLoc, resources, "", dueCal, startCal, dueCal, cbRepeating.isSelected(), priority);
		}
		else {
			assignment.setCourse(course);
			assignment.setStartDate(startCal);
			assignment.setDueTime(dueCal);
			assignment.setEndDate(dueCal);
			assignment.setName(hwName);
			assignment.setAssignmentLoc(assignmentLoc);
			assignment.setName(hwName);
			assignment.setResources(resources);
			assignment.setTurninLoc(turninLoc);
			assignment.setPriority((Integer)spnPriority.getValue());
		}
	}

}
