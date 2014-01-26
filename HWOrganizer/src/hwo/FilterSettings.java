package hwo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Choice;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class FilterSettings extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FilterSettings dialog = new FilterSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FilterSettings() {
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
			JComboBox comboBox = new JComboBox();
			contentPanel.add(comboBox, "cell 1 0,growx");
		}
		{
			JLabel lblEndingBetween = new JLabel("Ending between");
			contentPanel.add(lblEndingBetween, "cell 0 1");
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1390629600000L), null, null, Calendar.DAY_OF_YEAR));
			contentPanel.add(spinner, "flowx,cell 1 1");
		}
		{
			JLabel lblAnd = new JLabel("and");
			contentPanel.add(lblAnd, "cell 1 1");
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1390629600000L), null, null, Calendar.DAY_OF_YEAR));
			contentPanel.add(spinner, "cell 1 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
