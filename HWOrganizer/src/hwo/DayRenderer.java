package hwo;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class DayRenderer implements ListCellRenderer<DayOfAssignments> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends DayOfAssignments> list, DayOfAssignments day,
			int index, boolean isSelected, boolean hasFocus) {
		return day;
	}

	
	
}
