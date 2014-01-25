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

import net.miginfocom.swing.MigLayout;

public class CreateEditAssignment extends JDialog {

	
	private boolean complete;
	private AAssignment assignment;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfAssignmentLoc;
	private JTextField tfTurnInLoc;
	private JTextField tfResources;
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
	private JSpinner spinner;
	private JPanel panel;
	private JTextField tfFileLoc;
	private JButton btnBrowse;
	private static Course[] courses;
	private JLabel lblStart;
	private JSpinner spinner_1;

	public static RepeatAssignment createNewAssignment(Semester semester, Course[] courses, Frame parent){
		CreateEditAssignment.courses = courses;
		RepeatAssignment assignment = new RepeatAssignment();
		CreateEditAssignment dialog = new CreateEditAssignment(assignment, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		if (dialog.complete)
			return assignment;
		else
			return null;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Course course = new Course();
			CreateEditAssignment dialog = new CreateEditAssignment(course, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			System.out.println(course);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateEditAssignment(AAssignment assignment, Frame parent) {
		super(parent, true);
		this.assignment = assignment;
		setBounds(100, 100, 575, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][grow][grow]", "[][][][][][][][][grow][]"));
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
			spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerDateModel(new Date(1390629600000L), null, null, Calendar.DAY_OF_YEAR));
			contentPanel.add(spinner_1, "cell 1 2");
		}
		{
			JLabel lblDueDate = new JLabel("Due:");
			contentPanel.add(lblDueDate, "cell 0 3,alignx trailing");
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1390629600000L), new Date(1390629600000L), null, Calendar.MINUTE));
			contentPanel.add(spinner, "cell 1 3");
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
			JLabel lblResources = new JLabel("Resources:");
			contentPanel.add(lblResources, "cell 0 7,alignx trailing");
		}
		{
			tfResources = new JTextField();
			contentPanel.add(tfResources, "cell 1 7,growx");
			tfResources.setColumns(10);
		}
		{
			JLabel lblFileLocation = new JLabel("File Location:");
			contentPanel.add(lblFileLocation, "cell 0 8,alignx trailing");
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, "cell 1 8,grow");
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
			contentPanel.add(lblProgress, "cell 0 9,alignx trailing");
		}
		{
			progress = new JSlider();
			progress.setValue(0);
			contentPanel.add(progress, "cell 1 9");
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
						complete = true;
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
	}
	
	private void browseForFile(){
		JFileChooser chooser = new JFileChooser();
		File file = chooser.getSelectedFile();
		tfFileLoc.setText(file.getAbsolutePath());
	}
	
	private void getCourse(String cName){
		
	}
	
	private void storeAssignment(){
		assignment.setHWName(tfName.getText());
		assignment.setAssignmentLoc(tfAssignmentLoc.getText());
		assignment.setCourse(getCourse(cbCourse.getSelectedItem()));
		assignment.setDueTime(new Time(tfDueTime.getText()));
		assignment.setEndDate(new Time(tfDueDate.getText()));
	}

}
