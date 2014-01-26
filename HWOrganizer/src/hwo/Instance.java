package hwo;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

import java.io.IOException;

public class Instance {
	private Semester[] semesters;
	private String name;
	private Settings settings;
	private int currentSemester;
	
	Instance () {
		semesters = new Semester[]{new Semester(null, null)};
		name = "Name";
		settings = null;
		currentSemester = 0;
	}
	
	public void save(String fileName) {
		try{
			FileOutputStream saveFile = new FileOutputStream(fileName);
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
	
	public void open(String fileName) {
		try{
			FileInputStream saveFile = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(saveFile);
			name = (String) in.readObject();
			semesters = new Semester[(int) in.readObject()];
			for (int i = 0; i < semesters.length; i++)
				semesters[i] = (Semester) in.readObject();
			in.close();
			saveFile.close();
		} catch(IOException e) {
			e.printStackTrace();
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
