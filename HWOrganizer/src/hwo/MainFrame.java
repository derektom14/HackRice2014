package hwo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

// Derek

// Displays main GUI

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<DayOfAssignments> dayListModel;

	private Instance instance = new Instance();
	
	private JList<DayOfAssignments> dayList;
	
	private AssignmentDisplay assignmentInfo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("BLAH");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private ArrayList<SingleAssignment> getSingleAssignments() {
		return instance.filter();
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 476);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New Assignment");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewAssignment();
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmNewCourse = new JMenuItem("New Course");
		mntmNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewCourse();
			}
		});
		mnFile.add(mntmNewCourse);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save();
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAsText = new JMenuItem("Save as Text");
		mntmSaveAsText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnFile.add(mntmSaveAsText);
		
		JMenu mnFilter = new JMenu("Filter");
		mnFile.add(mnFilter);
		
		JMenu mnOrder = new JMenu("Order");
		mnFile.add(mnOrder);
		
		JMenu mnNewMenu = new JMenu("Edit\r\n");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Re-do");
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnNewMenu.add(mntmRedo);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmShowButtons = new JMenuItem("Show buttons");
		mnOptions.add(mntmShowButtons);
		
		JMenuItem mntmCompletedAssignmentOptions = new JMenuItem("Completed assignment options...");
		mnOptions.add(mntmCompletedAssignmentOptions);
		
		JMenuItem mntmShowMoreOptions = new JMenuItem("Show more options");
		mnOptions.add(mntmShowMoreOptions);
		
		JMenuItem mntmClearDays = new JMenuItem("Clear days...");
		mnOptions.add(mntmClearDays);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmOpenHelpFile = new JMenuItem("Open Help File");

		mntmOpenHelpFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Util.openFile("Homework Organizer Help.pdf");
			}
		});
		mnHelp.add(mntmOpenHelpFile);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel sideBar = new JPanel();
		contentPane.add(sideBar, BorderLayout.WEST);
		
		JButton btnNewAssignment = new JButton("New Assignment");
		btnNewAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewAssignment();
			}
		});
		sideBar.setLayout(new GridLayout(0, 1, 0, 0));
		sideBar.add(btnNewAssignment);
		
		JButton btnNewCourse = new JButton("New Course");
		btnNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewCourse();
			}
		});
		sideBar.add(btnNewCourse);
		
		JButton btnFilterSearch = new JButton("Filter / Search");
		btnFilterSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				filterAssignments();
			}
		});
		sideBar.add(btnFilterSearch);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reorder();
			}
		});
		sideBar.add(btnOrder);
		
		JPanel assignmentList = new JPanel();
		contentPane.add(assignmentList);
		dayListModel = new DefaultListModel<DayOfAssignments>();
		assignmentList.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(192, 69, 2, 2);
		assignmentList.add(scrollPane);
		
		dayList = new JList<DayOfAssignments>();
		dayList.setBounds(10, 0, 629, 395);
		assignmentList.add(dayList);
		dayList.setMinimumSize(new Dimension(700, 0));
		dayList.setModel(dayListModel);
		
		assignmentInfo = new AssignmentDisplay(instance, this);
		assignmentInfo.setMinimumSize(new Dimension(500, 173));
		contentPane.add(assignmentInfo, BorderLayout.EAST);
		//assignmentInfo.setVisible(false);
		
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("Click in main frame");
			}
		});
		
		fillListOfDays();
	}

	private void addNewAssignment(){
		RepeatAssignment newAssignment = 
				CreateEditAssignment.createNewAssignment(instance.getCurSemester(), this);
		if (newAssignment != null)
			instance.addChange(new DeleteRepeatAssignment(newAssignment));
		fillListOfDays();
	}
	
	private void addNewCourse(){
		ICourse newCourse = CreateEditCourse.createNewCourse(instance.getCurSemester(), this);
		if (newCourse != null)
			instance.addChange(new DeleteCourse(newCourse));
	}
	
	private boolean sameDay(Calendar c, int year, int day){
		return c.get(Calendar.YEAR) == year && c.get(Calendar.DAY_OF_YEAR) == day;  
	}
	
	private void fillListOfDays() {
		ArrayList<SingleAssignment> assignments = instance.filter();
		dayListModel.clear();
		if (!assignments.isEmpty()){
			Collections.sort(assignments, new AssignmentDateComparator());
			int index = 0;
			while(index < assignments.size()){
				Calendar curDate = assignments.get(index).getDueDate();
				int year = curDate.get(Calendar.YEAR);
				int day = curDate.get(Calendar.DAY_OF_YEAR);
				System.out.println("Assignment info: " + assignmentInfo);
				DayOfAssignments dayOfAssignments = new DayOfAssignments(curDate, assignmentInfo);
				do {
					dayOfAssignments.addAssignment(assignments.get(index));
					index++;
				} while (index < assignments.size() && sameDay(assignments.get(index).getDueDate(), year, day));
				dayListModel.addElement(dayOfAssignments);
			}
		}
		dayList.setCellRenderer(new DayRenderer());
		dayList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int index = dayList.locationToIndex(e.getPoint());
				Point origin = dayList.indexToLocation(index);
				DayOfAssignments day = dayListModel.elementAt(index);
				day.mouseClicked(new Point(e.getX() - origin.x, e.getY() - origin.y));
			}
		});
		dayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void save(){
		instance.save();
	}
	
	private void filterAssignments(){
		FilterSettings.changeSettings(instance, this);
		fillListOfDays();
	}
	
	private void reorder(){
		
	}

}

