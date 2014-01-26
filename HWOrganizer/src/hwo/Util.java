package hwo;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import java.awt.Desktop;
import java.net.URI;

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
	
	public static boolean isURL(String text) {
		try {
			URL u = new URL(text);
			u.toURI();
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
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
}
