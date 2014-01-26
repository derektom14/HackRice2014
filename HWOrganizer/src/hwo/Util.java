package hwo;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.validator.routines.UrlValidator;

public class Util {

	static UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
	
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
	
	public static boolean isURL(String text) {
		return urlValidator.isValid(text);
	}
	
	public static boolean isFile(String text) {
		File file = new File(text);
		return file.exists();
	}
	
	public static int testText(String text) {
		if (isURL(text))
			return 1;
		if (isFile(text))
			return 2;
		return 0;
	}
	
	public static void openURL(String text) {
		try {
			URI uri = new URI(text);
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
		            desktop.browse(uri);
		    }
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openFile(String text) {
		try {
			Desktop.getDesktop().open(new File(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void linkify(JLabel label) {
		String text = label.getText();
		boolean isURL = isURL(text);
		boolean isFile = isFile(text);
		if(isURL || isFile){
			label.setText("<html><a href=\"" + text + "\">" + (isURL ? "Link" : "File") + "</a></html>");
			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			if (isURL)
				label.addMouseListener(new OpenURL(text));
			else
				label.addMouseListener(new OpenFile(text));
		}
	}
}

class OpenURL extends MouseAdapter{
	private String url;

	public OpenURL(String url){
		this.url = url;
	}
	
	public void mouseClicked(MouseEvent e){
		Util.openURL(url);
	}
}

class OpenFile extends MouseAdapter{
	private String fileLoc;
	
	public OpenFile(String fileLoc){
		this.fileLoc = fileLoc;
	}
	
	public void mouseClicked(MouseEvent e){
		Util.openFile(fileLoc);
	}
}