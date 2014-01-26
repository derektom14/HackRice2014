package hwo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FileBrowser extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -292388213637019289L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 * @param info 
	 */
	public FileBrowser(String name, FileInfo info) {
		
		if (name != null){
			JLabel lblName = new JLabel("Name:");
			add(lblName);
		}
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSource = new JLabel("Source:");
		add(lblSource);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse...");
		add(btnBrowse);

		if (info != null){
			setInfo(info);
		}
		
	}

	public FileInfo getInfo() {
		return new FileInfo(textField_1.getText(), textField.getText());
	}

	public void setInfo(FileInfo info) {
		textField_1.setText(info.getName());
		textField.setText(info.getLoc());
	}

}
