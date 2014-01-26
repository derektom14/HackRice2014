package hwo;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.io.IOException;

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
			if (settings.getCourse() == null || settings.getCourse() == c) {
				for (RepeatAssignment r : c.getAssignments()) {
					for (SingleAssignment s : r.getAssignments()) {
						if (settings.getStartDate() == null || s.getDueDate().after(settings.getStartDate())
							&& settings.getEndDate() == null || s.getDueDate().before(settings.getEndDate()))
								results.add(s);
					}
				}
			}
		}
		return null;
	}
}
