package hwo;

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
         String s = assignment.getName() + ' ' + assignment.getTimeString();
         setText(s);
         if (assignment.isCompleted())
        	 setIcon(completeIcon);
         addMouseListener(new MouseAdapter(){
        	 public void mouseClicked(MouseEvent e){
        		 System.out.println("Got click");
        		 assignmentInfo.setAssignment(assignment);
        	 }
         });
         addMouseMotionListener(new MouseMotionAdapter(){
        	 public void mouseMoved(MouseEvent e){
        		 System.out.println("Got motion");
        		 assignmentInfo.setAssignment(assignment);
        	 }
         });
         return this;
     }
}