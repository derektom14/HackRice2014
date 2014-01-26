package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CreateEditCourse extends JDialog {
	private JTextField tfCourseName;
	private ICourse course;
	private ISemester semester;
	private boolean complete = false;
	private FileBrowser locPanel;
	private FileBrowser turninPanel;
	private FileBrowser[] resourcePanels = new FileBrowser[3];

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
		setBounds(100, 100, 589, 313);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel contentPanel = new JPanel();
			getContentPane().add(contentPanel, BorderLayout.NORTH);
			contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][grow][grow][grow]"));
			{
				JLabel lblCourseName = new JLabel("Course Name:");
				contentPanel.add(lblCourseName, "cell 0 0");
			}
			{
				tfCourseName = new JTextField();
				contentPanel.add(tfCourseName, "cell 1 0");
				tfCourseName.setColumns(10);
			}
			{
				JLabel lblAssignmentLocation = new JLabel("Default Assignment Location:");
				contentPanel.add(lblAssignmentLocation, "cell 0 1");
			}
			{
				locPanel = new FileBrowser(null, null);
				contentPanel.add(locPanel, "cell 1 1");
			}
			{
				JLabel lblTurninLocation = new JLabel("Default Turn-In Location:");
				contentPanel.add(lblTurninLocation, "cell 0 2");
			}
			{
				turninPanel = new FileBrowser(null, null);
				contentPanel.add(turninPanel, "cell 1 2");
			}
			{
				JLabel lblResources = new JLabel("Default Resources:");
				contentPanel.add(lblResources, "cell 0 3");
			}
			{
				FileBrowser rPanel1 = new FileBrowser(null, null);
				resourcePanels[0] = rPanel1;
				contentPanel.add(rPanel1, "cell 1 3,grow");
			}
			{
				FileBrowser rPanel2 = new FileBrowser(null, null);
				resourcePanels[1] = rPanel2;
				contentPanel.add(rPanel2, "cell 1 4,grow");
			}
			{
				FileBrowser rPanel3 = new FileBrowser(null, null);
				resourcePanels[2] = rPanel3;
				contentPanel.add(rPanel3, "cell 1 5,grow");
			}
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
		ArrayList<FileInfo> resources = new ArrayList<FileInfo>();
		for (int k = 0; k < 3; k++)
			resources.add(resourcePanels[k].getInfo());
		if (course == null){
			course = new Course(semester, tfCourseName.getText(), locPanel.getInfo(), turninPanel.getInfo(), resources,
				"", Calendar.getInstance());
		}
		else {
			course.setName(tfCourseName.getText());
			course.setAssignmentLoc(locPanel.getInfo());
			course.setTurninLoc(turninPanel.getInfo());
			course.setResources(resources);
		}
	}
}
