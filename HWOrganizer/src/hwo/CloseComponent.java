package hwo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseComponent implements ActionListener{
	
	private Component c;

	public CloseComponent(Component c){
		this.c = c;
		
	}
	
	public void actionPerformed(ActionEvent e){
		c.setVisible(false);
	}
}
