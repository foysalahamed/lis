import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;


public class AttendanceCheck extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldStuID;
	private JTable table;
	Connection connection=null;
	JDateChooser dateChooserFrom, dateChooserTo;
	JDateChooser dateChooserFrm,dateChooserTO;
	private JTextField textFieldEmpId;
	private JTable tableEmpAttn;
	private JTextField textFieldEmpAttnTmSt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceCheck frame = new AttendanceCheck();
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
	public AttendanceCheck() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		setBounds(100, 100, 1265, 665);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(56, 112, 582, 292);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldStuID = new JTextField();
		textFieldStuID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String stuid=textFieldStuID.getText();
				getStudentAttendance(stuid);
			}
		});
		textFieldStuID.setBounds(104, 65, 86, 20);
		panel.add(textFieldStuID);
		textFieldStuID.setColumns(10);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(41, 68, 84, 14);
		panel.add(lblStudentId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 544, 178);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		 dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(104, 34, 95, 20);
		panel.add(dateChooserFrom);
		
		 dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(229, 34, 95, 20);
		panel.add(dateChooserTo);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(209, 37, 28, 14);
		panel.add(lblTo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(48, 37, 46, 14);
		panel.add(lblFrom);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(new Color(222, 184, 135));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuid=textFieldStuID.getText();
				String dFrom=((JTextField)dateChooserFrom.getDateEditor().getUiComponent()).getText();
				String dto=((JTextField)dateChooserTo.getDateEditor().getUiComponent()).getText();
				String query="select * from LIS_STUDENTS_ATTENDANCE where stu_id='"+stuid+"' and attn_date between '"+dFrom+"' and '"+dto+"' order by attn_date asc";
				String rpPath="C:\\Users\\creativepc\\Desktop\\LIS\\reportAttendance.jrxml";
				report( dFrom, dto,query,rpPath);
			}
		});
		btnPrint.setBounds(229, 64, 89, 23);
		panel.add(btnPrint);
		
		JLabel label = new JLabel("London Intelligence School");
		label.setForeground(new Color(205, 133, 63));
		label.setFont(new Font("Times New Roman", Font.BOLD, 28));
		label.setBounds(471, 24, 338, 33);
		contentPane.add(label);
		
		JLabel label_logo = new JLabel("");
		label_logo.setBounds(419, 7, 50, 68);
		contentPane.add(label_logo);
		Image imglogo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		label_logo.setIcon(new ImageIcon(imglogo));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(648, 112, 582, 292);
		contentPane.add(panel_1);
		
		textFieldEmpId = new JTextField();
		textFieldEmpId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String empid=textFieldEmpId.getText();
				getEmpAttendance(empid);
				
			}
		});
		textFieldEmpId.setColumns(10);
		textFieldEmpId.setBounds(105, 61, 95, 20);
		panel_1.add(textFieldEmpId);
		
		JLabel lblEmployeeId = new JLabel("Emp ID");
		lblEmployeeId.setBounds(41, 64, 46, 14);
		panel_1.add(lblEmployeeId);
		
		 dateChooserFrm = new JDateChooser();
		dateChooserFrm.setBounds(104, 30, 95, 20);
		panel_1.add(dateChooserFrm);
		
		 dateChooserTO = new JDateChooser();
		dateChooserTO.setBounds(229, 30, 95, 20);
		panel_1.add(dateChooserTO);
		
		JLabel label_3 = new JLabel("To");
		label_3.setBounds(209, 33, 28, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("From");
		label_4.setBounds(48, 33, 46, 14);
		panel_1.add(label_4);
		
		JButton button = new JButton("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empid=textFieldEmpId.getText();
				String dFrom=((JTextField)dateChooserFrm.getDateEditor().getUiComponent()).getText();
				String dto=((JTextField)dateChooserTO.getDateEditor().getUiComponent()).getText();
				String query;
				if(allemp==true){
					query="select * from LIS_SESSIONS where LOGIN_TIME between '"+dFrom+"' and '"+dto+"' order by LOGIN_TIME asc";
					allemp=false;
				}
				else
				{
					query="select * from LIS_SESSIONS where employeeid='"+empid+"' and LOGIN_TIME between '"+dFrom+"' and '"+dto+"' order by LOGIN_TIME asc";
				}
				 
				 
				 
					
				String rpPath="C:\\Users\\creativepc\\Desktop\\LIS\\empAttendanceReport.jrxml";
				report( dFrom, dto,query,rpPath);
			}
		});
		button.setBackground(new Color(222, 184, 135));
		button.setBounds(265, 60, 89, 23);
		panel_1.add(button);
		
		JButton btnAll = new JButton("All");
		btnAll.setBackground(new Color(222, 184, 135));
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEmpAttendance("all");
			}
		});
		btnAll.setBounds(334, 27, 89, 23);
		panel_1.add(btnAll);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 93, 562, 179);
		panel_1.add(scrollPane_1);
		
		tableEmpAttn = new JTable();
		scrollPane_1.setViewportView(tableEmpAttn);
		
		JLabel lblStudentsAttendance = new JLabel("Students Attendance");
		lblStudentsAttendance.setForeground(new Color(255, 228, 181));
		lblStudentsAttendance.setBounds(71, 83, 212, 28);
		contentPane.add(lblStudentsAttendance);
		lblStudentsAttendance.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblEmployeeAttendance = new JLabel("Employee Attendance");
		lblEmployeeAttendance.setForeground(new Color(255, 228, 181));
		lblEmployeeAttendance.setBounds(662, 84, 212, 28);
		contentPane.add(lblEmployeeAttendance);
		lblEmployeeAttendance.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblStAttn = new JLabel("");
		lblStAttn.setBounds(55, 86, 579, 25);
		contentPane.add(lblStAttn);
		Image imgst=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		lblStAttn.setIcon(new ImageIcon(imgst));
		
		JLabel lblEmpAttn = new JLabel("");
		lblEmpAttn.setBackground(Color.YELLOW);
		lblEmpAttn.setBounds(651, 85, 579, 25);
		contentPane.add(lblEmpAttn);
		Image imgemp=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		lblEmpAttn.setIcon(new ImageIcon(imgemp));
		
		JLabel lblEmployeeAttendanceTime = new JLabel("Employee Attendance Time Setup");
		lblEmployeeAttendanceTime.setForeground(new Color(255, 228, 181));
		lblEmployeeAttendanceTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEmployeeAttendanceTime.setBounds(662, 415, 323, 28);
		contentPane.add(lblEmployeeAttendanceTime);
		
		JLabel labelEmpTmSt = new JLabel("");
		labelEmpTmSt.setBackground(Color.YELLOW);
		labelEmpTmSt.setBounds(651, 416, 579, 25);
		contentPane.add(labelEmpTmSt);
		Image imgets=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelEmpTmSt.setIcon(new ImageIcon(imgets));
		
		JLabel lblNewLabel = new JLabel("Attendance Time Setup");
		lblNewLabel.setBounds(682, 454, 132, 14);
		contentPane.add(lblNewLabel);
		
		textFieldEmpAttnTmSt = new JTextField();
		textFieldEmpAttnTmSt.setBounds(827, 451, 86, 20);
		contentPane.add(textFieldEmpAttnTmSt);
		textFieldEmpAttnTmSt.setColumns(10);
		
		JButton btnSave = new JButton("Show");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String q="select * from LIS_ATTN_TIME_SETTINGS";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet rs=prstatement.executeQuery();
					if(rs.next()){
						textFieldEmpAttnTmSt.setText(rs.getString("LASTTIME"));
					}
					rs.close();
					prstatement.close();
				
			} catch (SQLException e) {
				//JOptionPane.showMessageDialog(null, e);
				e.printStackTrace();
			}
			}
		});
		btnSave.setBackground(new Color(143, 188, 143));
		btnSave.setBounds(737, 479, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q="update LIS_ATTN_TIME_SETTINGS set lasttime='"+textFieldEmpAttnTmSt.getText()+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					prstatement.executeQuery();
					prstatement.close();
					textFieldEmpAttnTmSt.setText(null);
				
			} catch (SQLException e1) {
				//JOptionPane.showMessageDialog(null, e);
				e1.printStackTrace();
			}
			}
		});
		btnEdit.setBackground(new Color(143, 188, 143));
		btnEdit.setBounds(828, 479, 89, 23);
		contentPane.add(btnEdit);
		
		JLabel lblExm = new JLabel("* Exm: 00:00");
		lblExm.setForeground(new Color(255, 0, 0));
		lblExm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExm.setBounds(923, 454, 132, 14);
		contentPane.add(lblExm);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Time Out");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(1104, 448, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnAbsence = new JButton("Absence");
		btnAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAbsence.setBackground(new Color(143, 188, 143));
		btnAbsence.setBounds(1112, 479, 89, 23);
		contentPane.add(btnAbsence);
		
	}
	private void report(String dFrom, String dto,String query,String reportPath){
		//making report
		
		
		try{
			JasperDesign jd=JRXmlLoader.load( reportPath);

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

	protected void getStudentAttendance(String stuid) {
		// TODO Auto-generated method stub
		
		
		String dFrom=((JTextField)dateChooserFrom.getDateEditor().getUiComponent()).getText();
		String dto=((JTextField)dateChooserTo.getDateEditor().getUiComponent()).getText();

		try {
			String q="select * from LIS_STUDENTS_ATTENDANCE where stu_id='"+stuid+"' and attn_date between '"+dFrom+"' and '"+dto+"' order by attn_date asc";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet rs=prstatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		
	} catch (SQLException e) {
		//JOptionPane.showMessageDialog(null, e);
		e.printStackTrace();
	}
		
	}
	boolean allemp=false;
	protected void getEmpAttendance(String empid) {
		// TODO Auto-generated method stub
		
		String q;
		
		String dFrom=((JTextField)dateChooserFrm.getDateEditor().getUiComponent()).getText();
		String dto=((JTextField)dateChooserTO.getDateEditor().getUiComponent()).getText();
		if(empid.equals("all")){
			 q="select * from lis_sessions where LOGIN_TIME between '"+dFrom+"' and '"+dto+"' order by LOGIN_TIME desc";
			 allemp=true;
		}
		else{
			 q="select * from lis_sessions where employeeid='"+empid+"' and LOGIN_TIME between '"+dFrom+"' and '"+dto+"' order by LOGIN_TIME desc";

		}

		try {
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet rs=prstatement.executeQuery();
			tableEmpAttn.setModel(DbUtils.resultSetToTableModel(rs));
			
		
	} catch (SQLException e) {
		//JOptionPane.showMessageDialog(null, e);
		e.printStackTrace();
	}
		
	}
}
