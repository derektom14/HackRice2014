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
	private ICourse course;
	private ISemester semester;
	private boolean complete = false;
	private FileBrowser locPanel;
	private FileBrowser turninPanel;
	private MultiFileBrowser resourcesPanel;

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
		setBounds(100, 100, 653, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[]", "[27px][27px][27px][]"));
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
			locPanel = new FileBrowser(null, null);
			contentPanel.add(locPanel, "cell 0 1");
		}
		{
			turninPanel = new FileBrowser(null, null);
			contentPanel.add(turninPanel, "cell 0 2");
		}
		{
			resourcesPanel = new MultiFileBrowser(null, null);
			contentPanel.add(resourcesPanel, "cell 0 3");
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
			course = new Course(semester, tfCourseName.getText(), locPanel.getInfo(), turninPanel.getInfo(), resourcesPanel.getFileInfos(),
				"", Calendar.getInstance());
		}
		else {
			course.setName(tfCourseName.getText());
			course.setAssignmentLoc(locPanel.getInfo());
			course.setTurninLoc(turninPanel.getInfo());
			course.setResources(resourcesPanel.getFileInfos());
		}
	}
}
