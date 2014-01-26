package hwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MultiFileBrowser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5317277056727615694L;
	/**
	 * Create the panel.
	 */
	
	private ArrayList<FileBrowser> files = new ArrayList<FileBrowser>();
	
	public MultiFileBrowser(String name, FileInfo info) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addNewBrowser(name, info);
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addNewBrowser(null, null);
			}
		});
		add(button);
	}
	
	public void addNewBrowser(String name, FileInfo info){
		FileBrowser file = new FileBrowser(name, null);
		files.add(file);
		add(file);
		
	}

	public ArrayList<FileInfo> getFileInfos() {
		ArrayList<FileInfo> info = new ArrayList<FileInfo>();
		for (FileBrowser file : files){
			info.add(file.getInfo());
		}
		return info;
	}
}
