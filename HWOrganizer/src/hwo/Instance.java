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
	private ArrayList<ISemester> semesters;
	private String name;
	private Settings settings = new Settings(null, null, null);
	private int currentSemester;
	private Stack<Change> undoStack = new Stack<Change>();
	private Stack<Change> redoStack = new Stack<Change>();
	private int order; // 0: due date, 1: class, 2: name
	
	Instance () {
		open();
	}
	
	public ISemester getCurSemester() {return semesters.get(currentSemester);}
	
	public void addSemester(ISemester semester, int index)
	{
		if (index <= semesters.size())
			semesters.add(semester);
		else
			throw new IllegalArgumentException("Tried to add a semester past the length of the ArrayList.");
	}

	public ISemester deleteSemester(int index)
	{
		if (index < semesters.size()) {
			ISemester semester = semesters.get(index);
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
			semesters = new ArrayList<ISemester>();
			int size = (int) in.readObject();
			for (int i = 0; i < size; i++)
				semesters.add((Semester) in.readObject());
			in.close();
			saveFile.close();
		} catch(IOException e) {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar future = (GregorianCalendar) now.clone();
			future.add(Calendar.MONTH, 4);
			semesters = new ArrayList<ISemester>();
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
						if ((settings.getStartDate() == null || s.getDueDate().after(settings.getStartDate()))
							&& (settings.getEndDate() == null || s.getDueDate().before(settings.getEndDate())))
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
		System.out.println("undo");
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
	
	public void setOrder(String s) {
		if (s.equals("Due Date"))
			order = 0;
		else if (s.equals("Class"))
			order = 1;
		else if (s.equals("Name"))
			order = 2;
		else
			throw new IllegalArgumentException("Invalid Order Input");
	}
	
	public int getOrder(){
		return order;
	}
}