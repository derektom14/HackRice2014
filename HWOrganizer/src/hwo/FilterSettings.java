package hwo;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JCheckBox cbStart;
	private JCheckBox cbEnd;
	private final Settings settings;
	private JComboBox<String> courseBox;
	private Map<String, ICourse> courseMap;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FilterSettings dialog = new FilterSettings(new Instance(), null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeSettings(Instance instance, Frame parent){
		FilterSettings dialog = new FilterSettings(instance, parent);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
	}
	
	/**
	 * Create the dialog.
	 */
	public FilterSettings(Instance instance, Frame parent) {
		super(parent, true);
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
			String[] courseNames = new String[courseMap.size() + 1];
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
			cbStart = new JCheckBox("");
			contentPanel.add(cbStart, "cell 1 1");
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
			cbEnd = new JCheckBox("");
			contentPanel.add(cbEnd, "cell 1 1");
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
						
						Calendar newStart = (cbStart.isSelected() ? Calendar.getInstance() : null);
						if (newStart != null)
							newStart.setTime((Date)beginSpinner.getValue());
						settings.setStartDate(newStart);
						
						Calendar newEnd = (cbEnd.isSelected() ? Calendar.getInstance() : null);
						if (newEnd != null)
							newEnd.setTime((Date)endSpinner.getValue());
						settings.setEndDate(newEnd);
						
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
