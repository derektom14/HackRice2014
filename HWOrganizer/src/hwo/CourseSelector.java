package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private ICourse chosenCourse;
	private final Map<String, ICourse> courses;
	
	public static ICourse selectCourse(Map<String, ICourse> courses, String action){
		CourseSelector dialog = new CourseSelector(courses, action);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
		return dialog.chosenCourse;
	}

	/**
	 * Create the dialog.
	 */
	public CourseSelector(final Map<String, ICourse> courses, String action) {
		this.courses = courses;
		setBounds(100, 100, 450, 122);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		{
			String[] courseNames = new String[courses.size()];
			int k = 0;
			for (String cName : courses.keySet())
				courseNames[k++] = cName;
			comboBox = new JComboBox(courseNames);
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(action);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						chosenCourse = courses.get(comboBox.getSelectedItem());
						setVisible(false);
					} 
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new CloseComponent(this));
				buttonPane.add(cancelButton);
			}
		}
	}
}
