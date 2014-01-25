// Author: Michael

package hwo;
import java.util.Date;
import java.util.ArrayList;

public interface ISemester {
	public Date getStartDate();
	public Date getEndDate();
	public void setStartDate(Date startDate);
	public void setEndDate(Date endDate);
	public void addClass(IClass newClass);
	public ArrayList<IClass> getClasses();
	
}
