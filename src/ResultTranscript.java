import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ResultTranscript extends JFrame {

	private JPanel contentPane;
	private JTable tableResult;
	
	JComboBox comboBoxTerms,comboBoxStuID,comboBoxSection,comboBoxYear;
	JComboBox comboBoxExamID;
	JLabel labelStudentName;
	Connection connection;
	private JTextField textFieldStudentId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultTranscript frame = new ResultTranscript();
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
	public ResultTranscript() {
		
		try {
			connection=DbConnection.dbconnection();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
		}
		
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(56, 128, 562, 417);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("Year");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label.setBounds(10, 41, 46, 14);
		panel_1.add(label);
		
		 comboBoxYear = new JComboBox();
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"---Select---", "Nursery", "Play", "One", "Two", "Three", "Four", "Five", "Six"}));
		comboBoxYear.setBounds(74, 38, 109, 20);
		panel_1.add(comboBoxYear);
		
		JLabel label_1 = new JLabel("Student ID");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(8, 63, 59, 14);
		panel_1.add(label_1);
		
		 comboBoxStuID = new JComboBox();
		 comboBoxStuID.setModel(new DefaultComboBoxModel(new String[] {"---Select---"}));
		comboBoxStuID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxStuID.addItem("garbeg");
		   		String st_id=comboBoxStuID.getSelectedItem().toString();
		   		comboBoxStuID.removeItem("garbeg");
				
				try {
					String q="select firstname,lastname from lis_student_info where stu_id='"+st_id+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					if(result.next()){
						labelStudentName.setText(result.getString("FIRSTNAME")+ " "+result.getString("LASTNAME"));
					}	
					else{
						labelStudentName.setText("");
					}
					result.close();
					prstatement.close();
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
		   
			}
		});
		comboBoxStuID.setBounds(74, 60, 109, 20);
		panel_1.add(comboBoxStuID);
		
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
		
		
		JLabel label_2 = new JLabel("Student Name");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_2.setBounds(6, 81, 77, 14);
		panel_1.add(label_2);
		
		JLabel lblTranscript = new JLabel("Transcript");
		lblTranscript.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTranscript.setBounds(185, 11, 122, 19);
		panel_1.add(lblTranscript);
		
		JLabel label_4 = new JLabel("Section");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(190, 41, 46, 14);
		panel_1.add(label_4);
		
		 comboBoxSection = new JComboBox();
		 comboBoxSection.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		String yr=comboBoxYear.getSelectedItem().toString();
		   		String sec=comboBoxSection.getSelectedItem().toString();
		   		comboBoxStuID.removeAllItems();
		   		System.out.print("year "+ yr + "and section " +sec );
				
				try {
					//String q="select stu_id from lis_student_info where year='"+yr+"' and section='"+sec+"'";
					String q="select stu_id from lis_student_info where year='"+yr+"'and section='"+sec+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					while(result.next()){
						comboBoxStuID.addItem(result.getString("STU_ID"));
						
					}	
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			
		 		
		 	}
		 });
		comboBoxSection.setModel(new DefaultComboBoxModel(new String[] {"---Select--- ", "A", "B", "C", "D"}));
		comboBoxSection.setBounds(252, 38, 109, 20);
		panel_1.add(comboBoxSection);
		
		JLabel label_5 = new JLabel("Section");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_5.setBounds(78, 81, 46, 14);
		panel_1.add(label_5);
		
		 labelStudentName = new JLabel("");
		labelStudentName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		labelStudentName.setBounds(138, 81, 219, 14);
		panel_1.add(labelStudentName);
		
		JButton buttonPrint = new JButton("Print");
		buttonPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				report();
			}
		});
		buttonPrint.setBounds(206, 107, 89, 23);
		panel_1.add(buttonPrint);
		
		comboBoxTerms = new JComboBox();
		comboBoxTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String exam_name=comboBoxTerms.getSelectedItem().toString();
		   		comboBoxExamID.removeAllItems();
				
				try {
					String q="select * from lis_exam_setup where exam_name ='"+exam_name+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					while(result.next()){
						comboBoxExamID.addItem(result.getString("EXAM_ID"));

					}	
						
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		comboBoxTerms.setModel(new DefaultComboBoxModel(new String[] {"---Select---", "Class Test", "First Terminal", "Second Terminal", "Midterm", "Quiz", "Final"}));
		comboBoxTerms.setBounds(252, 60, 109, 20);
		panel_1.add(comboBoxTerms);
		
		JLabel lblTerms = new JLabel("Terms");
		lblTerms.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTerms.setBounds(193, 63, 46, 14);
		panel_1.add(lblTerms);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 137, 430, 160);
		panel_1.add(scrollPane);
		
		tableResult = new JTable();
		scrollPane.setViewportView(tableResult);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String e_id=comboBoxExamID.getSelectedItem().toString();
		   		String s_id=comboBoxStuID.getSelectedItem().toString();
		   		String yr=comboBoxYear.getSelectedItem().toString();
				
				try {
					String query="select si.firstname,si.lastname,si.year,si.section,si.student_roll,spi.fathers_name,"
							+ "spi.mothers_name,sr.course_id,cd.course_name,sr.marks,sr.grade,sr.gpa,sr.remarks,ex.exam_name "
							+ "from lis_student_info si join lis_parents_info spi on si.stu_id=spi.stu_id "
							+ "join lis_result_setup sr on si.stu_id=sr.stu_id "
							+ "join lis_course cd on cd.course_id=sr.course_id "
							+ "join lis_exam_setup ex on ex.exam_id=sr.exam_id "
							+ "where sr.exam_id='"+e_id+"' and sr.stu_id='"+s_id+"' and sr.year='"+yr+"'";
					//String q="select stu_id from lis_student_info where year='"+yr+"' and section='"+sec+"'";
					//String q="select * from lis_result_setup where exam_id='"+e_id+"' and stu_id='"+s_id+"' and year='"+yr+"'";
					PreparedStatement prstatement=connection.prepareStatement(query);
					ResultSet result=prstatement.executeQuery();
					tableResult.setModel(DbUtils.resultSetToTableModel(result));
						
					//printtable();
					
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			
		 		
			}
		});
		btnOk.setBounds(106, 107, 89, 23);
		panel_1.add(btnOk);
		
		 comboBoxExamID = new JComboBox();
		 comboBoxExamID.setModel(new DefaultComboBoxModel(new String[] {"---Select---"}));
		comboBoxExamID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						}
		});
		comboBoxExamID.setBounds(443, 60, 109, 20);
		panel_1.add(comboBoxExamID);
		
		JLabel lblExamId = new JLabel("Exam ID");
		lblExamId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblExamId.setBounds(385, 63, 46, 14);
		panel_1.add(lblExamId);
		
		textFieldStudentId = new JTextField();
		textFieldStudentId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//		   		String s_id=comboBoxStuID.getSelectedItem().toString();
//		   		String yr=comboBoxYear.getSelectedItem().toString();
		   		String s_id=textFieldStudentId.getText();
				String e_id=comboBoxExamID.getSelectedItem().toString();


				try {
					String query="select si.firstname,si.lastname,si.year,si.section,si.student_roll,spi.fathers_name,"
							+ "spi.mothers_name,sr.course_id,cd.course_name,sr.marks,sr.grade,sr.gpa,sr.remarks,ex.exam_name "
							+ "from lis_student_info si join lis_parents_info spi on si.stu_id=spi.stu_id "
							+ "join lis_result_setup sr on si.stu_id=sr.stu_id "
							+ "join lis_course cd on cd.course_id=sr.course_id "
							+ "join lis_exam_setup ex on ex.exam_id=sr.exam_id "
							+ "where sr.exam_id='"+e_id+"' and sr.stu_id='"+s_id+"'";
					//String q="select stu_id from lis_student_info where year='"+yr+"' and section='"+sec+"'";
					//String q="select * from lis_result_setup where exam_id='"+e_id+"' and stu_id='"+s_id+"' and year='"+yr+"'";
					PreparedStatement prstatement=connection.prepareStatement(query);
					ResultSet result=prstatement.executeQuery();
					tableResult.setModel(DbUtils.resultSetToTableModel(result));
						
					//printtable();
					
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			
				
			}
		});
		textFieldStudentId.setBounds(374, 12, 86, 20);
		panel_1.add(textFieldStudentId);
		textFieldStudentId.setColumns(10);
		
		JLabel label_3 = new JLabel("Student ID");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(302, 15, 59, 14);
		panel_1.add(label_3);
	}

	protected void printtable() {
		// TODO Auto-generated method stub
		MessageFormat header=new MessageFormat("London Intelligence School "+"\n Savar,Dhaka");
		MessageFormat footer=new MessageFormat("Thank you for being with us");
		try {
			tableResult.print(JTable.PrintMode.NORMAL,header,footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
	private void report(){
		//making report
		
		String e_id=comboBoxExamID.getSelectedItem().toString();
   		String s_id=comboBoxStuID.getSelectedItem().toString();
   		String yr=comboBoxYear.getSelectedItem().toString();
		
		
			String query="select si.firstname,si.lastname,si.year,si.section,si.student_roll,spi.fathers_name,"
					+ "spi.mothers_name,sr.course_id,cd.course_name,sr.marks,sr.grade,sr.gpa,sr.remarks,ex.exam_name "
					+ "from lis_student_info si join lis_parents_info spi on si.stu_id=spi.stu_id "
					+ "join lis_result_setup sr on si.stu_id=sr.stu_id join lis_course cd "
					+ "on cd.course_id=sr.course_id join lis_exam_setup ex on ex.exam_id=sr.exam_id "
					+ "where sr.exam_id='"+e_id+"' and sr.stu_id='"+s_id+"' and sr.year='"+yr+"'";
			
		
		
		try{
			
			JasperDesign jd=JRXmlLoader.load("C:\\LIS\\report\\ResultTranscript.jrxml");

			JRDesignQuery newQuery=new JRDesignQuery();
			newQuery.setText(query);
			jd.setQuery(newQuery);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr,null,connection);
			//JasperViewer.viewReport(jp);
			
			
			JRViewer test = new JRViewer(jp);
	        
	        JFrame frame = new JFrame("Report");
	        frame.getContentPane().add(test); //new JRViewer(jasperPrint)
	        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.pack();
	        frame.setVisible(true);   

		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
	}
}
