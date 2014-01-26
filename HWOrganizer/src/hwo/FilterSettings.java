package hwo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class FilterSettings extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JSpinner beginSpinner;
	private JSpinner endSpinner;
	private final Settings settings;
	private JComboBox<String> courseBox;
	private Map<String, ICourse> courseMap;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FilterSettings dialog = new FilterSettings();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FilterSettings(Instance instance) {
		this.settings = instance.getSettings();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		{
			JLabel lblCourse = new JLabel("Course");
			contentPanel.add(lblCourse, "cell 0 0,alignx trailing");
		}
		{
			courseMap = instance.getCurSemester().getCourses();
			String[] courseNames = new String[courseMap.size()];
			courseNames[0] = "";
			int k = 1;
			for (String cName : courseMap.keySet())
				courseNames[k++] = cName;
			courseBox = new JComboBox<String>(courseNames);
			contentPanel.add(courseBox, "cell 1 0,growx");
		}
		{
			JLabel lblEndingBetween = new JLabel("Ending between");
			contentPanel.add(lblEndingBetween, "cell 0 1");
		}
		{
			beginSpinner = new JSpinner();
			beginSpinner.setModel(new SpinnerDateModel(new Date(1390629600000L), null, null, Calendar.DAY_OF_YEAR));
			contentPanel.add(beginSpinner, "flowx,cell 1 1");
		}
		{
			JLabel lblAnd = new JLabel("and");
			contentPanel.add(lblAnd, "cell 1 1");
		}
		{
			endSpinner = new JSpinner();
			endSpinner.setModel(new SpinnerDateModel(new Date(1390629600000L), null, null, Calendar.DAY_OF_YEAR));
			contentPanel.add(endSpinner, "cell 1 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String key = (String)courseBox.getSelectedItem();
						if (key == null)
							settings.setCourse(null);
						settings.setCourse(courseMap.get(key));
						
						settings.getStartDate().setTime((Date)beginSpinner.getValue());
						settings.getEndDate().setTime((Date)endSpinner.getValue());
						setVisible(false);
					}
				});
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
