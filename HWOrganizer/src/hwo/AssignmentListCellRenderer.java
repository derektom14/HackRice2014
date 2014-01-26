package hwo;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class AssignmentListCellRenderer extends JLabel implements ListCellRenderer<SingleAssignment> {
     final static ImageIcon completeIcon = new ImageIcon("complete.png");
     final static ImageIcon incompleteIcon = new ImageIcon("incomplete.png");

     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

     public Component getListCellRendererComponent(
       JList<? extends SingleAssignment> list,           // the list
       SingleAssignment assignment,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // does the cell have focus
     {
         String s = assignment.toString();
         setText(s);
         setIcon(assignment.isCompleted() ? completeIcon : incompleteIcon);
         return this;
     }
}