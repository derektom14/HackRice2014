package hwo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class SemesterSelection extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SemesterSelection dialog = new SemesterSelection();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SemesterSelection() {
		setTitle("Semester");
		setBounds(100, 100, 536, 80);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][]", "[][]"));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblStartDate = new JLabel("Start Date");
				buttonPane.add(lblStartDate);
			}
			{
				JSpinner spinner = new JSpinner();
				buttonPane.add(spinner);
				spinner.setModel(new SpinnerDateModel(new Date(1390629600000L), null, null, Calendar.DAY_OF_YEAR));
			}
			{
				JLabel lblEndDate = new JLabel("End Date");
				buttonPane.add(lblEndDate);
			}
			{
				JSpinner spinner = new JSpinner();
				buttonPane.add(spinner);
				spinner.setModel(new SpinnerDateModel(new Date(1400994000000L), null, null, Calendar.DAY_OF_YEAR));
			}
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
