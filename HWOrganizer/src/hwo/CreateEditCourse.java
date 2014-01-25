package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private Course course;
	private boolean complete = false;

	public static Course createNewCourse(Frame parent){
		Course course = new Course();
		CreateEditCourse dialog = new CreateEditCourse(course, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		if (dialog.complete)
			return course;
		else
			return null;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Course course = new Course();
			CreateEditCourse dialog = new CreateEditCourse(course, null);
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
	public CreateEditCourse(Course course, Frame parent) {
		super(parent, true);
		this.course = course;
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
			JLabel lblAssignmentLocation = new JLabel("Assignment Location:");
			contentPanel.add(lblAssignmentLocation, "flowx,cell 0 1,alignx left,growy");
		}
		{
			JLabel lblTurninLocation = new JLabel("Turn-In Location:");
			contentPanel.add(lblTurninLocation, "flowx,cell 0 2,alignx left,growy");
		}
		{
			JLabel lblResources = new JLabel("Resources:");
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
		course.setName(tfCourseName.getText());
		course.setAssignmentLoc(tfAssignmentLoc.getText());
		course.setResources(tfResources.getText());
		course.setTurninLoc(tfTurnInLoc.getText());
	}
}
