package hwo;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Derek

// Displays main GUI

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<DayOfAssignments> dayListModel;

	private Instance instance = new Instance();
	
	Vector<SingleAssignment> assignments = getSingleAssignments();
	private JList<DayOfAssignments> dayList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	private Vector<SingleAssignment> getSingleAssignments() {
		// TODO Auto-generated method stub
		return new Vector<SingleAssignment>();
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 387);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New Assignment");
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
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAsText = new JMenuItem("Save as Text");
		mnFile.add(mntmSaveAsText);
		
		JMenu mnFilter = new JMenu("Filter");
		mnFile.add(mnFilter);
		
		JMenu mnOrder = new JMenu("Order");
		mnFile.add(mnOrder);
		
		JMenu mnNewMenu = new JMenu("Edit\r\n");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnNewMenu.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Re-do");
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
		mnHelp.add(mntmOpenHelpFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[111px][201px]", "[303px]"));
		
		JPanel sideBar = new JPanel();
		contentPane.add(sideBar, "cell 0 0,alignx left,growy");
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		
		JButton btnNewAssignment = new JButton("New Assignment");
		btnNewAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewAssignment();
			}
		});
		sideBar.add(btnNewAssignment);
		
		JButton btnNewCourse = new JButton("New Course");
		btnNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewCourse();
			}
		});
		sideBar.add(btnNewCourse);
		
		JButton btnFilterSearch = new JButton("Filter / Search");
		sideBar.add(btnFilterSearch);
		
		JPanel assignmentList = new JPanel();
		contentPane.add(assignmentList, "cell 1 0,grow");
		
		dayList = new JList<DayOfAssignments>();
		assignmentList.add(dayList);
		fillListOfDays();
	}

	private void addNewAssignment(){
		System.out.println(instance);
		System.out.println(instance.getCurSemester());
		System.out.println(instance.getCurSemester().getCourses());
		RepeatAssignment assignment = CreateEditAssignment.createNewAssignment(instance.getCurSemester().getCourses(), this);
	}
	
	private void addNewCourse(){
		Course course = CreateEditCourse.createNewCourse(instance.getCurSemester(), this);
		instance.getCurSemester().addCourse(course);
	}
	
	private boolean sameDay(Calendar a, Calendar b){
		return a.get(Calendar.YEAR) == b.get(Calendar.YEAR) && a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR);  
	}
	
	private void fillListOfDays() {
		if (!assignments.isEmpty()){
			Collections.sort(assignments, new AssignmentDateComparator());
			int index = 0;
			while(index < assignments.size()){
				Calendar curDay = assignments.get(0).getDueDate();
				DayOfAssignments day = new DayOfAssignments(curDay);
				do {
					day.addAssignment(assignments.get(index));
					index++;
				} while (index < assignments.size() && sameDay(assignments.get(index).getDueDate(), curDay));
				dayListModel.addElement(day);
			}
		}
	}

}

