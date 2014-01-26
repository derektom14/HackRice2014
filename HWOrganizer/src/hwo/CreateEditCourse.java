package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class CreateEditCourse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCourseName;
	private JTextField tfAssignmentLoc;
	private JTextField tfTurnInLoc;
	private JTextField tfResources;
	private ICourse course;
	private ISemester semester;
	private boolean complete = false;

	public static ICourse createNewCourse(ISemester semester, Frame parent){
		CreateEditCourse dialog = new CreateEditCourse(parent, semester, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
		System.out.println("new course: " + dialog.course.getName() + dialog.course);
		return dialog.course;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateEditCourse dialog = new CreateEditCourse(null, new Instance().getCurSemester(), null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			System.out.println(dialog.course);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateEditCourse(Frame parent, ISemester semester, Course course) {
		super(parent, true);
		this.course = course;
		this.semester = semester;
		setTitle("Create New Course");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[424px]", "[27px][27px][27px][27px]"));
		{
			JLabel lblCourseName = new JLabel("Course Name:");
			contentPanel.add(lblCourseName, "flowx,cell 0 0,alignx left,growy");
		}
		{
			JLabel lblAssignmentLocation = new JLabel("Default Assignment Location:");
			contentPanel.add(lblAssignmentLocation, "flowx,cell 0 1,alignx left,growy");
		}
		{
			JLabel lblTurninLocation = new JLabel("Default Turn-In Location:");
			contentPanel.add(lblTurninLocation, "flowx,cell 0 2,alignx left,growy");
		}
		{
			JLabel lblResources = new JLabel("Default Resources:");
			contentPanel.add(lblResources, "flowx,cell 0 3,alignx left,growy");
		}
		{
			tfCourseName = new JTextField();
			contentPanel.add(tfCourseName, "cell 0 0,grow");
			tfCourseName.setColumns(10);
		}
		{
			tfAssignmentLoc = new JTextField();
			contentPanel.add(tfAssignmentLoc, "cell 0 1,grow");
			tfAssignmentLoc.setColumns(10);
		}
		{
			tfTurnInLoc = new JTextField();
			contentPanel.add(tfTurnInLoc, "cell 0 2,grow");
			tfTurnInLoc.setColumns(10);
		}
		{
			tfResources = new JTextField();
			contentPanel.add(tfResources, "cell 0 3,grow");
			tfResources.setColumns(10);
		}
		{
			JButton btnBrowse = new JButton("Browse...");
			btnBrowse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Util.browseForFile(tfResources, CreateEditCourse.this);
				}
			});
			contentPanel.add(btnBrowse, "cell 0 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						storeCourse();
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

	
	
	private void storeCourse(){
		if (course == null){
			course = new Course(semester, tfCourseName.getText(), tfAssignmentLoc.getText(), tfTurnInLoc.getText(), tfResources.getText(),
				"", Calendar.getInstance());
		}
		else {
			course.setName(tfCourseName.getText());
			course.setAssignmentLoc(tfAssignmentLoc.getText());
			course.setResources(tfResources.getText());
			course.setTurninLoc(tfTurnInLoc.getText());
		}
	}
}
