package hwo;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class AssignmentListCellRenderer extends JLabel implements ListCellRenderer<SingleAssignment> {
     final static ImageIcon completeIcon = new ImageIcon("resources/check.png");
     final static Color color = new Color(240, 240, 240);
	private AssignmentDisplay assignmentInfo;

     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

     public AssignmentListCellRenderer(AssignmentDisplay assignmentInfo) {
		this.assignmentInfo = assignmentInfo;
	}

	public Component getListCellRendererComponent(
       JList<? extends SingleAssignment> list,           // the list
       final SingleAssignment assignment,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // does the cell have focus
     {
         String s = assignment.getName() + " - " + assignment.getCourse().getName() + " - " + assignment.getTimeString();
         setText(s);
         setBackground(color);
         if (assignment.isCompleted())
        	 setIcon(completeIcon);
         
         return this;
     }
}