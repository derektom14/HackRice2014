package hwo;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class Util {

	public static void browseForFile(JTextField field, Component parent){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		System.out.println(chooser);
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			System.out.println(file);
			field.setText(file.getAbsolutePath());
		}
	}
}
