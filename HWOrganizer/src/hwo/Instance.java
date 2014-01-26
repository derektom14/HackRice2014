package hwo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Instance {
	private Semester[] semesters;
	private String name;
	private Settings settings = new Settings(null, null, null);
	private int currentSemester;
	
	Instance () {
		open();
	}
	
	public Semester getCurSemester() {return semesters[currentSemester];}
	
	public void save() {
		try{
			File f = new File(".hworganizer.ser");
			f.createNewFile();
			FileOutputStream saveFile = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(saveFile);
			out.writeObject(name);
			out.writeObject(semesters.length);
			for (int i = 0; i < semesters.length; i++)
				out.writeObject(semesters[i]);
			out.close();
			saveFile.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void open() {
		try{
			FileInputStream saveFile = new FileInputStream(".hworganizer.ser");
			ObjectInputStream in = new ObjectInputStream(saveFile);
			name = (String) in.readObject();
			semesters = new Semester[(int) in.readObject()];
			for (int i = 0; i < semesters.length; i++)
				semesters[i] = (Semester) in.readObject();
			in.close();
			saveFile.close();
		} catch(IOException e) {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar future = (GregorianCalendar) now.clone();
			future.add(Calendar.MONTH, 4);
			semesters = new Semester[]{new Semester(now, future)};
			name = "Name";
			currentSemester = 0;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SingleAssignment> filter()
	{
		ArrayList<SingleAssignment> results = new ArrayList<SingleAssignment>();
		for (ICourse c : semesters[currentSemester].getCourses()) {
			System.out.println("Course: " + c);
			if (settings.getCourse() == null || settings.getCourse() == c) {
				System.out.println("Accepted");
				for (RepeatAssignment r : c.getAssignments()) {
					System.out.println("Repeating: " + r);
					for (SingleAssignment s : r.getAssignments()) {
						System.out.println("Single: " + s);
						if (settings.getStartDate() == null || s.getDueDate().after(settings.getStartDate())
							&& settings.getEndDate() == null || s.getDueDate().before(settings.getEndDate()))
								results.add(s);
					}
				}
			}
		}
		return results;
	}

	public Settings getSettings() {
		return settings;
	}
}
