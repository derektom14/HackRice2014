package hwo;

import java.awt.Component;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

// Derek

// displays list of assignments

public class AssignmentList extends JList<SingleAssignment>{
	
	public AssignmentList(ListModel<SingleAssignment> model){
		setModel(model);
	}
	
}

class AssignmentRenderer extends JLabel implements ListCellRenderer<SingleAssignment>{

	@Override
	public Component getListCellRendererComponent(
			JList<? extends SingleAssignment> list, SingleAssignment value,
			int index, boolean isSelected, boolean cellHasFocus) {
		return new SingleAssignmentPanel(value);
	}
	
}