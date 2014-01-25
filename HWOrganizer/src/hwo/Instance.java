package hwo;
import java.io.*;
import java.util.ArrayList;

import java.io.IOException;

public class Instance {
	private Semester[] semesters;
	private String name;
	
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
}