package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.datatype.Duration;

import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;

public class CreateEditAssignment extends JDialog {
	
	private AAssignment assignment;

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfAssignmentLoc;
	private JTextField tfTurnInLoc;
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
	private JComboBox<Course> cbCourse;
	private JSpinner spnDue;
	private JPanel panel;
	private JTextField tfFileLoc;
	private JButton btnBrowse;
	private static Course[] courses;
	private JLabel lblStart;
	private JSpinner spnStart;

	public static RepeatAssignment createNewAssignment(Course[] courses, Frame parent){
		if (courses.length == 0)
			throw new IllegalArgumentException("Cannot create assignment with no courses");
		CreateEditAssignment dialog = new CreateEditAssignment(parent, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
		return (RepeatAssignment) dialog.assignment;
	}
	
	public static void editAssignment(Course[] courses, Frame parent, SingleAssignment assignment){
		CreateEditAssignment dialog = new CreateEditAssignment(parent, assignment);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
	}
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		Course course = new Course(null, title);
//		System.out.println(createNewAssignment(new Course[]{course}, null));
//	}

	/**
	 * Create the dialog.
	 */
	public CreateEditAssignment(Frame parent, SingleAssignment assignment) {
		super(parent, true);
		setTitle("Create New Assignment");
		this.assignment = assignment;
		setBounds(100, 100, 575, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("hidemode 3", "[][grow][grow][grow]", "[][][][][][][][][]"));
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
			JLabel lblCourse = new JLabel("Class:");
			contentPanel.add(lblCourse, "cell 0 1,alignx trailing");
		}
		{
			cbCourse = new JComboBox();
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
			spnDue.setModel(new SpinnerDateModel(new Date(), new Date(), null, Calendar.MINUTE));
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
			tfAssignmentLoc = new JTextField();
			contentPanel.add(tfAssignmentLoc, "cell 1 5,growx");
			tfAssignmentLoc.setColumns(10);
		}
		{
			JLabel lblTurninLocation = new JLabel("Turn-In Location:");
			contentPanel.add(lblTurninLocation, "cell 0 6,alignx trailing");
		}
		{
			tfTurnInLoc = new JTextField();
			contentPanel.add(tfTurnInLoc, "cell 1 6,growx");
			tfTurnInLoc.setColumns(10);
		}
		{
			JLabel lblFileLocation = new JLabel("Resources:");
			contentPanel.add(lblFileLocation, "cell 0 7,alignx trailing");
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, "cell 1 7,grow");
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				tfFileLoc = new JTextField();
				panel.add(tfFileLoc);
				tfFileLoc.setColumns(10);
			}
			{
				btnBrowse = new JButton("Browse...");
				btnBrowse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						browseForFile();
					}
				});
				panel.add(btnBrowse);
			}
		}
		{
			JLabel lblProgress = new JLabel("Progress:");
			lblProgress.setHorizontalAlignment(SwingConstants.TRAILING);
			contentPanel.add(lblProgress, "cell 0 8,alignx trailing");
		}
		{
			progress = new JSlider();
			progress.setValue(0);
			contentPanel.add(progress, "cell 1 8");
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
			tfName.setText(assignment.getHWName());
			tfAssignmentLoc.setText(assignment.getAssignmentLoc());
			tfTurnInLoc.setText(assignment.getTurninLoc());
			tfFileLoc.setText(assignment.getResources());
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
	}
	
	private void browseForFile(){
		JFileChooser chooser = new JFileChooser();
		File file = chooser.getSelectedFile();
		tfFileLoc.setText(file.getAbsolutePath());
	}
	
	private Course getCourse(String cName){
		for (Course c : courses)
			if (c.getName().equals(cName))
				return c;
		throw new IllegalStateException("Chose nonexistent course");
	}
	
	private void storeAssignment(){
		Course course = getCourse((String)cbCourse.getSelectedItem());
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
		
		String hwName = tfName.getText();
		String assignmentLoc = tfAssignmentLoc.getText();
		String turninLoc = tfTurnInLoc.getText();
		String resources = tfFileLoc.getText();
		
		if (assignment == null){
			assignment = new RepeatAssignment(course, frequency, validDays, hwName, "", new Duration(), assignmentLoc, turninLoc, resources, "", dueCal, startCal, dueCal);
		}
		else {
			assignment.setAssignmentLoc(assignmentLoc);
			assignment.setCourse(course);
			assignment.setStartDate(startCal);
			assignment.setDueTime(dueCal);
			assignment.setEndDate(dueCal);
			assignment.setName(hwName);
			assignment.setAssignmentLoc(assignmentLoc);
			assignment.setName(hwName);
			assignment.setResources(resources);
			assignment.setTurninLoc(turninLoc);
		}
	}

}
