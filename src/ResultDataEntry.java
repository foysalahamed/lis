import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ResultDataEntry extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldExamID;
	private Connection connection;
	JComboBox comboBoxExamName;
	JComboBox comboBoxCourseID;
	JComboBox comboBoxExamID;
	JComboBox comboBoxExamNameDB ;
	JComboBox comboBoxTeacherId;
	 JDateChooser dateChooserExamDate ;
	JComboBox comboBoxYear;
	JRadioButton rdbtnById;
	private JTable table;
	 DefaultTableModel model;
	 private JTextField textFieldSearchByStuID;
	 private JTextField textFieldCourseName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultDataEntry frame = new ResultDataEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResultDataEntry() {
		
try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1011, 738);
		setTitle("London Intelligence School");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(119, 148, 200, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblYear = new JLabel("Exam ID");
		lblYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblYear.setBounds(13, 50, 46, 14);
		panel.add(lblYear);
		
		JLabel lblStudentId = new JLabel("Exam Name");
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblStudentId.setBounds(8, 31, 59, 14);
		panel.add(lblStudentId);
		
		comboBoxExamName = new JComboBox();
		comboBoxExamName.setModel(new DefaultComboBoxModel(new String[] {"---Select---", "Class Test", "First Terminal", "Second Terminal", "Midterm", "Quiz", "Final"}));
		comboBoxExamName.setBounds(66, 28, 109, 20);
		panel.add(comboBoxExamName);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="Insert into LIS_EXAM_SETUP values(?,?)";
								
				try {
					PreparedStatement prStatement= connection.prepareStatement(query);
					
					
					prStatement.setString(1,textFieldExamID.getText() );
					prStatement.setString(2,(String)comboBoxExamName.getSelectedItem() );
					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Saved data");
					comboBoxExamName.setSelectedIndex(0);
					textFieldExamID.setText("");
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Duplicate ID");
				}
				
			}
		});
		button.setBounds(18, 90, 73, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Clear");
		button_1.setBounds(101, 90, 73, 23);
		panel.add(button_1);
		
		JLabel lblExamSetup = new JLabel("Exam Setup");
		lblExamSetup.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblExamSetup.setBounds(77, 11, 73, 14);
		panel.add(lblExamSetup);
		
		textFieldExamID = new JTextField();
		textFieldExamID.setBounds(67, 49, 108, 20);
		panel.add(textFieldExamID);
		textFieldExamID.setColumns(10);
		
		JLabel lblLondonIntelligenceSchool = new JLabel("London Intelligence School");
		lblLondonIntelligenceSchool.setForeground(new Color(210, 105, 30));
		lblLondonIntelligenceSchool.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblLondonIntelligenceSchool.setBounds(307, 44, 278, 40);
		contentPane.add(lblLondonIntelligenceSchool);
		
		JLabel lblLogo = new JLabel("");
		Image logo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		lblLogo.setIcon(new ImageIcon(logo));
		lblLogo.setBounds(250, 29, 50, 71);
		contentPane.add(lblLogo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(346, 148, 505, 532);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(26, 11, 431, 116);
		panel_1.add(panel_2);
		
		JLabel lblTeachersId = new JLabel("Teachers ID");
		lblTeachersId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTeachersId.setBounds(10, 41, 59, 14);
		panel_2.add(lblTeachersId);
		
		JLabel lblResultSetup = new JLabel("Result Setup");
		lblResultSetup.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblResultSetup.setBounds(74, 11, 68, 14);
		panel_2.add(lblResultSetup);
		
		JLabel lblSection = new JLabel("CourseID");
		lblSection.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSection.setBounds(188, 41, 54, 14);
		panel_2.add(lblSection);
		
		 comboBoxCourseID = new JComboBox();
		 comboBoxCourseID.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		comboBoxCourseID.addItem("garbeg");
				
		 		String cName=comboBoxCourseID.getSelectedItem().toString();
		 		comboBoxCourseID.removeItem("garbeg");
				String query="select course_name from lis_course where course_id='"+cName+"'";
				//comboBoxExamID.removeAllItems();
				try{
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet rs=prst.executeQuery();
					if(rs.next()){
						textFieldCourseName.setText(rs.getString("COURSE_NAME"));
					}
					
				}catch(Exception e){
					
				}
		 	}
		 });
		
		comboBoxCourseID.setBounds(252, 38, 109, 20);
		panel_2.add(comboBoxCourseID);
		
		JLabel label_4 = new JLabel("Exam Name");
		label_4.setBounds(10, 64, 59, 14);
		panel_2.add(label_4);
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		 comboBoxExamNameDB = new JComboBox();
		 comboBoxExamNameDB.setBounds(75, 64, 109, 20);
		 panel_2.add(comboBoxExamNameDB);
		 comboBoxExamNameDB.addActionListener(new ActionListener() {
		 	 public void actionPerformed(ActionEvent arg0) {
		 		
		 		String eName=comboBoxExamNameDB.getSelectedItem().toString();
				String query="select exam_id from lis_exam_setup where exam_name='"+eName+"'";
				comboBoxExamID.removeAllItems();
				try{
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet rs=prst.executeQuery();
					while(rs.next()){
						comboBoxExamID.addItem(rs.getString("EXAM_ID"));
					}
					
				}catch(Exception e){
					
				}
		 		
		 	}
		 });
		 comboBoxExamNameDB.setModel(new DefaultComboBoxModel(new String[] {"--Select--", "Class Test", "First Terminal", "Second Terminal", "Midterm", "Quiz", "Final"}));
		 
		 JLabel label = new JLabel("Date of Exam");
		 label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		 label.setBounds(194, 90, 68, 14);
		 panel_2.add(label);
		 
		  dateChooserExamDate = new JDateChooser();
		 dateChooserExamDate.setBounds(266, 87, 95, 20);
		 panel_2.add(dateChooserExamDate);
		 
		  comboBoxTeacherId = new JComboBox();
		  comboBoxTeacherId.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		  comboBoxTeacherId.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		String empid=comboBoxTeacherId.getSelectedItem().toString();
		  		String query="select course_id from lis_course where teacher_id='"+empid+"'";
		  		comboBoxCourseID.removeAllItems();
				try{
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet rs=prst.executeQuery();
					while(rs.next()){
				  		
						comboBoxCourseID.addItem(rs.getString("COURSE_ID"));
						
					}
					rs.close();
					prst.close();
					
				}catch(Exception e2){
					e2.printStackTrace();
				}
		  		
		  	}
		  });
		 comboBoxTeacherId.setBounds(74, 38, 109, 20);
		 panel_2.add(comboBoxTeacherId);
		 
		 JLabel lblExamid = new JLabel("ExamID");
		 lblExamid.setBounds(10, 90, 59, 14);
		 panel_2.add(lblExamid);
		 lblExamid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		 
		 
		  comboBoxExamID = new JComboBox();
		  comboBoxExamID.setModel(new DefaultComboBoxModel(new String[] {"---Select---"}));
		  comboBoxExamID.setBounds(75, 87, 109, 20);
		  panel_2.add(comboBoxExamID);
		  
		  textFieldCourseName = new JTextField();
		  textFieldCourseName.setEditable(false);
		  textFieldCourseName.setBounds(252, 8, 86, 20);
		  panel_2.add(textFieldCourseName);
		  textFieldCourseName.setColumns(10);
		  comboBoxExamID.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		
		  		comboBoxExamID.addItem("garbeg");
		    		String st_id=comboBoxExamID.getSelectedItem().toString();
		    		comboBoxExamID.removeItem("garbeg");
				
				try {
					String q="select firstname,lastname from lis_student_info where stu_id='"+st_id+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					if(result.next()){
					}	
					else{
					}
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
		  		
		  	}
		  });
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaption);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(26, 141, 431, 301);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		 comboBoxYear = new JComboBox();
		 comboBoxYear.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		String yr=comboBoxYear.getSelectedItem().toString();
		 		getStudentsFromDB(yr);
		 	}
		 });
		 comboBoxYear.setBounds(54, 11, 109, 20);
		 panel_3.add(comboBoxYear);
		 comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"----Select-----", "Nursery", "Play", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}));
		 
		 JLabel lblYear_1 = new JLabel("Year");
		 lblYear_1.setBounds(22, 14, 59, 14);
		 panel_3.add(lblYear_1);
		 lblYear_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(10, 42, 399, 187);
		 panel_3.add(scrollPane);
		 
		 table = new JTable();
		// scrollPane.setViewportView(table);
		 model = new DefaultTableModel(){

	            private static final long serialVersionUID = 1L;

	           
	            @Override
	            public Class<?> getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return String.class;
	                    case 1:
	                        return String.class;
	                    case 2:
	                        return String.class;
	                    case 3:
	                        return String.class;
	                    case 4:
	                        return String.class;
	                   
	                    default:
	                        return String.class;
	                }
	            }
	        };
	        table.setModel(model);
	        table.setPreferredScrollableViewportSize(table.getPreferredSize());
	        scrollPane.setViewportView(table);
	        
	        JButton btnResultSave = new JButton("Result Save");
	        btnResultSave.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        		for(int r=0;r<table.getRowCount();r++){
		        		
	        			String query="insert into LIS_RESULT_SETUP values(?,?,?,?,?,?,?,?,?)";
		        			try {
								PreparedStatement prst=connection.prepareStatement(query);
								prst.setString(1,comboBoxCourseID.getSelectedItem().toString() );
//								prst.setString(2,textFieldCourseName.getText() );

								prst.setString(2, table.getValueAt(r, 0).toString());//stu id
								prst.setString(3,comboBoxYear.getSelectedItem().toString() );
								prst.setString(4, comboBoxExamID.getSelectedItem().toString() );		
								prst.setString(5,((JTextField)dateChooserExamDate.getDateEditor().getUiComponent()).getText() );
								prst.setString(6,grdeCalculate(Double.parseDouble(table.getValueAt(r, 4).toString()) ));

								prst.setDouble(7, (double)getGPA(Double.parseDouble(table.getValueAt(r, 4).toString())));
								prst.setDouble(8, Double.parseDouble(table.getValueAt(r, 4).toString()));

								prst.setString(9, table.getValueAt(r, 5).toString());			        			
			        			prst.executeQuery();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
	        			
	        		}
        			JOptionPane.showMessageDialog(null, "Successfully Inerted");
        			clearAllField();

	        	}
	        });
	        btnResultSave.setBounds(193, 269, 120, 23);
	        panel_3.add(btnResultSave);
	        
	         rdbtnById = new JRadioButton("By ID");
	         rdbtnById.addActionListener(new ActionListener() {
	         	public void actionPerformed(ActionEvent arg0) {
	         		if(rdbtnById.isSelected()){
	        			textFieldSearchByStuID.setEnabled(true);
	        			comboBoxYear.setEnabled(false);
	        		}
	         		else{
	         			textFieldSearchByStuID.setEnabled(false);
	        			comboBoxYear.setEnabled(true);
	         		}
	         	}
	         });
	        rdbtnById.setBackground(SystemColor.inactiveCaption);
	        rdbtnById.setBounds(169, 10, 67, 23);
	        panel_3.add(rdbtnById);
	        
	        textFieldSearchByStuID = new JTextField();
	        textFieldSearchByStuID.addKeyListener(new KeyAdapter() {
	        	@Override
	        	public void keyReleased(KeyEvent arg0) { 
	        		String stid=textFieldSearchByStuID.getText();
	        		getStudentsFromDB(stid);
	        	}
	        });
	       
	        textFieldSearchByStuID.setEnabled(false);
	        textFieldSearchByStuID.setBounds(242, 11, 86, 20);
	        panel_3.add(textFieldSearchByStuID);
	        textFieldSearchByStuID.setColumns(10);
	        //getContentPane().add(scrollPane);
	        model.addColumn("STUDENT ID");
		    model.addColumn("ROLL NO");
		    model.addColumn("FIRST NAME");
		    model.addColumn("YEAR");
		    model.addColumn("MARKS");
		    model.addColumn("REMARKS");
		getTeacherId();
		getExamId();
	}
	protected void clearAllField() {
		// TODO Auto-generated method stub
		comboBoxTeacherId.setSelectedIndex(0);
		//comboBoxCourseID.setSelectedItem("---Select---");
		comboBoxExamNameDB.setSelectedIndex(0);
		//comboBoxExamID.setSelectedItem("---Select---");
		dateChooserExamDate.setCalendar(null);
		if(rdbtnById.isSelected()){
			rdbtnById.setSelected(false);
			textFieldSearchByStuID.setText("");
			textFieldSearchByStuID.setEnabled(false);
			comboBoxYear.setEnabled(true);

		}else{
			comboBoxYear.setSelectedIndex(0);

		}
		emptymodel();
	}

	protected double getGPA(double marks) {
		// TODO Auto-generated method stub
		double gpa=0.0;
		
		if(marks>=80){
			return gpa=5.0;
		}
		else if(marks<=79&&marks>=70){
			return gpa=4.50;
		}
		else if(marks<=69&&marks>=60){
			return gpa=4.0;
		}
		else if(marks<=59&&marks>=50){
			return gpa=3.5;
		}
		else if(marks<=49&&marks>=40){
			return gpa=3.0;
		}
		else if(marks<=39&&marks>=33){
			return gpa=2.50;
		}
		else if(marks<33){
			return gpa=0;
		}

		
		return gpa;
	}

	private void getExamId() {
		// TODO Auto-generated method stub
		
	}

	private void getTeacherId() {
		// TODO Auto-generated method stub
		String query="select emp_id from lis_employee_info where dept_id='"+"Teacher"+"'";
		try{
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			while(rs.next()){
				comboBoxTeacherId.addItem(rs.getString("EMP_ID"));
			}
			
		}catch(Exception e){
			
		}
		
	}

	protected String grdeCalculate(double d) {
		// TODO Auto-generated method stub
		String temp=null;
		if(d>=80){
			return temp="A+";
		}
		else if(d<=79&&d>=70){
			return temp="A";
		}
		else if(d<=69&&d>=60){
			return temp="A-";
		}
		else if(d<=59&&d>=50){
			return temp="B+";
		}
		else if(d<=49&&d>=40){
			return temp="B";
		}
		else if(d<=39&&d>=33){
			return temp="B-";
		}
		else if(d<33){
			return temp="F";
		}
		return null;
	}

	
	
	protected void getStudentsFromDB(String yr) {
		emptymodel();
		String q=null;
		
		try {
			if(rdbtnById.isSelected()){
				 q="select stu_id,student_roll,firstname,year from LIS_STUDENT_INFO where stu_id='"+yr+"' and status='"+"active"+"'";

			}
			else{
				 q="select stu_id,student_roll,firstname,year from LIS_STUDENT_INFO where year='"+yr+"' and status='"+"active"+"'";
			}
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet rs=prstatement.executeQuery();
			int c=0;
			  while (rs.next()) {
				  
			    	model.addRow(new Object[0]);
			    	model.setValueAt(rs.getString("STU_ID"), c, 0);
			    	model.setValueAt(rs.getString("STUDENT_ROLL"), c, 1);
			    	
			    	model.setValueAt(rs.getString("FIRSTNAME"), c, 2);
			    	model.setValueAt(rs.getString("YEAR"), c, 3);

			    	model.setValueAt(" ", c, 4);
			    	model.setValueAt(" ", c, 5);

			    	c++;

		
			  }
			
			
		
	} catch (SQLException e) {
		//JOptionPane.showMessageDialog(null, e);
		e.printStackTrace();
	}
			
	}

	private void emptymodel() {
		// TODO Auto-generated method stub
		for(int i=model.getRowCount()-1;i>-1;i--){
			model.removeRow(i);
		}
		
	}
}
