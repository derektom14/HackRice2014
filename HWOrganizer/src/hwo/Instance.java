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
import java.util.Stack;

public class Instance {
	private ArrayList<Semester> semesters;
	private String name;
	private Settings settings = new Settings(null, null, null);
	private int currentSemester;
	private Stack<Change> undoStack = new Stack<Change>();
	private Stack<Change> redoStack = new Stack<Change>();
	
	Instance () {
		open();
	}
	
	public Semester getCurSemester() {return semesters.get(currentSemester);}
	
	public void addSemester(Semester semester, int index)
	{
		if (index <= semesters.size())
			semesters.add(semester);
		else
			throw new IllegalArgumentException("Tried to add a semester past the length of the ArrayList.");
	}

	public Semester deleteSemester(int index)
	{
		if (index < semesters.size()) {
			Semester semester = semesters.get(index);
			semesters.remove(index);
			return semester;
		}
		else
			throw new IllegalArgumentException("Tried to remove a semester past the length of the ArrayList.");
	}
	
	public void save() {
		try{
			File f = new File(".hworganizer.ser");
			f.createNewFile();
			FileOutputStream saveFile = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(saveFile);
			out.writeObject(name);
			out.writeObject(semesters.size());
			for (int i = 0; i < semesters.size(); i++)
				out.writeObject(semesters.get(i));
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
			semesters = new ArrayList<Semester>();
			int size = (int) in.readObject();
			for (int i = 0; i < size; i++)
				semesters.add((Semester) in.readObject());
			in.close();
			saveFile.close();
		} catch(IOException e) {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar future = (GregorianCalendar) now.clone();
			future.add(Calendar.MONTH, 4);
			semesters = new ArrayList<Semester>();
			semesters.add(new Semester(now, future));
			name = "Name";
			currentSemester = 0;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SingleAssignment> filter()
	{
		ArrayList<SingleAssignment> results = new ArrayList<SingleAssignment>();
		for (ICourse c : semesters.get(currentSemester).getCourses().values()) {
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
	
	public void deleteAssignments() {
		ArrayList<SingleAssignment> toDelete = filter();
		for (SingleAssignment a : toDelete)
			a.removeSelf();
	}

	public Settings getSettings() {
		return settings;
	}
	
	public void undo() {
		if (!undoStack.empty()) {
			Change change = undoStack.pop();
			redoStack.push(change.act());
		}
	}
	
	public void redo() {
		if (!redoStack.empty()) {
			Change change = redoStack.pop();
			undoStack.push(change.act());
		}
	}
	
	public void addChange(Change change) {
		undoStack.push(change);
		redoStack.clear();
	}
	
	public ArrayList<String> getUndoList(){
		ArrayList<String> textList = new ArrayList<String>();
		for (Change change : undoStack) {
			textList.add(change.toString());
		}
		return textList;
	}
	
	public ArrayList<String> getRedoList(){
		ArrayList<String> textList = new ArrayList<String>();
		for (Change change : redoStack) {
			textList.add(change.toString());
		}
		return textList;
	}
}