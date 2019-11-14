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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class Administrator extends JFrame {
	private Connection connection=null;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator frame = new Administrator();
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
	public Administrator() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		setBounds(100, 100, 1159, 879);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistration = new JButton("Student Regi.");
		btnRegistration.setBackground(new Color(255, 204, 153));
		btnRegistration.setFont(btnRegistration.getFont().deriveFont(btnRegistration.getFont().getStyle() & ~Font.BOLD));
		btnRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentRegistration regi=new StudentRegistration();
				regi.setVisible(true);
			}
		});
		btnRegistration.setBounds(83, 161, 117, 23);
		contentPane.add(btnRegistration);
		
		JButton btnFeeSetup = new JButton("Fee Setup");
		btnFeeSetup.setBackground(new Color(255, 204, 153));
		btnFeeSetup.setForeground(new Color(51, 0, 0));
		btnFeeSetup.setFont(btnFeeSetup.getFont().deriveFont(btnFeeSetup.getFont().getStyle() & ~Font.BOLD));
		btnFeeSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeeStructureSetup fee=new FeeStructureSetup();
				fee.setVisible(true);
			}
		});
		btnFeeSetup.setBounds(83, 185, 117, 23);
		contentPane.add(btnFeeSetup);
		
		JButton btnDueF = new JButton("Due Fee");
		btnDueF.setBackground(new Color(255, 204, 153));
		btnDueF.setForeground(new Color(51, 0, 0));
		btnDueF.setFont(btnDueF.getFont().deriveFont(btnDueF.getFont().getStyle() & ~Font.BOLD));
		btnDueF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DueFee duefee=new DueFee();
				duefee.setVisible(true);
			}
		});
		btnDueF.setBounds(82, 209, 117, 23);
		contentPane.add(btnDueF);
		
		JButton btnResultTranscript = new JButton("Result Transcript");
		btnResultTranscript.setBackground(new Color(255, 204, 153));
		btnResultTranscript.setForeground(new Color(51, 0, 0));
		btnResultTranscript.setFont(btnResultTranscript.getFont().deriveFont(btnResultTranscript.getFont().getStyle() & ~Font.BOLD));
		btnResultTranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultTranscript resultTranscript=new ResultTranscript();
				resultTranscript.setVisible(true);
			}
		});
		btnResultTranscript.setBounds(83, 256, 117, 23);
		contentPane.add(btnResultTranscript);
		
		JButton btnCertificate = new JButton("Certificate");
		btnCertificate.setBackground(new Color(255, 204, 153));
		btnCertificate.setForeground(new Color(51, 0, 0));
		btnCertificate.setFont(btnResultTranscript.getFont().deriveFont(btnResultTranscript.getFont().getStyle() & ~Font.BOLD));
		btnCertificate.setBounds(83, 280, 117, 23);
		contentPane.add(btnCertificate);
		
		JButton btnScholarship = new JButton("Scholarship");
		btnScholarship.setBackground(new Color(255, 204, 153));
		btnScholarship.setForeground(new Color(51, 0, 0));
		btnScholarship.setFont(btnResultTranscript.getFont().deriveFont(btnResultTranscript.getFont().getStyle() & ~Font.BOLD));
		btnScholarship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Scholarship().setVisible(true);
			}
		});
		btnScholarship.setBounds(83, 304, 117, 23);
		contentPane.add(btnScholarship);
		
		JButton btnTc = new JButton("TC");
		btnTc.setBackground(new Color(255, 204, 153));
		btnTc.setForeground(new Color(51, 0, 0));
		btnTc.setFont(btnTc.getFont().deriveFont(btnTc.getFont().getStyle() & ~Font.BOLD));
		btnTc.setBounds(83, 329, 117, 23);
		contentPane.add(btnTc);
		
		JButton btnStudentAttendaces = new JButton("Stu Attndance");
		btnStudentAttendaces.setBackground(new Color(255, 204, 153));
		btnStudentAttendaces.setFont(btnStudentAttendaces.getFont().deriveFont(btnStudentAttendaces.getFont().getStyle() & ~Font.BOLD));
		btnStudentAttendaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StudentsAttendance().setVisible(true);
			}
		});
		btnStudentAttendaces.setBounds(392, 304, 117, 23);
		contentPane.add(btnStudentAttendaces);
		
		JButton btnEmployeeAttendance = new JButton("Grading Setup");
		btnEmployeeAttendance.setBackground(new Color(255, 204, 153));
		btnEmployeeAttendance.setForeground(new Color(51, 0, 0));
		btnEmployeeAttendance.setFont(btnEmployeeAttendance.getFont().deriveFont(btnEmployeeAttendance.getFont().getStyle() & ~Font.BOLD));
		btnEmployeeAttendance.setBounds(82, 233, 117, 23);
		contentPane.add(btnEmployeeAttendance);
		btnEmployeeAttendance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ResultDataEntry().setVisible(true);
				
			}
		});
		
		JButton btnEmployeeReg = new JButton("Employee Reg");
		btnEmployeeReg.setBackground(new Color(255, 204, 153));
		btnEmployeeReg.setFont(btnTc.getFont().deriveFont(btnTc.getFont().getStyle() & ~Font.BOLD));
		btnEmployeeReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeInfo empInfo=new EmployeeInfo();
				empInfo.setVisible(true);
			}
		});
		btnEmployeeReg.setBounds(392, 136, 117, 23);
		contentPane.add(btnEmployeeReg);
		
		
		JButton btnExpenditure = new JButton("Expenditure Setup");
		btnExpenditure.setBackground(new Color(255, 204, 153));
		btnExpenditure.setFont(btnTc.getFont().deriveFont(btnTc.getFont().getStyle() & ~Font.BOLD));
		btnExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Expenditure expend=new Expenditure();
				expend.setVisible(true);
			}
		});
		btnExpenditure.setBounds(392, 161, 117, 23);
		contentPane.add(btnExpenditure);
		
		JLabel labelBackground = new JLabel("");
		//Image bacImage=new ImageIcon(this.getClass().getResource("/dd.gif")).getImage();
		
		JButton btnTransport = new JButton("Trnas Fee Setup");
		btnTransport.setBackground(new Color(255, 204, 153));
		btnTransport.setFont(btnTransport.getFont().deriveFont(btnTransport.getFont().getStyle() & ~Font.BOLD));
		btnTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransportFareSetup tsetup=new TransportFareSetup();
				tsetup.setVisible(true);
			}
		});
		btnTransport.setBounds(392, 185, 117, 23);
		contentPane.add(btnTransport);
		
		JButton btnProductPriceSetup = new JButton("Price Setup");
		btnProductPriceSetup.setBackground(new Color(255, 204, 153));
		btnProductPriceSetup.setFont(btnProductPriceSetup.getFont().deriveFont(btnProductPriceSetup.getFont().getStyle() & ~Font.BOLD));
		btnProductPriceSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PriceSetup psetup=new PriceSetup();
				psetup.setVisible(true);
			}
		});
		btnProductPriceSetup.setBounds(392, 209, 117, 23);
		contentPane.add(btnProductPriceSetup);
		
		JButton btnReport = new JButton("Report");
		btnReport.setBackground(new Color(255, 204, 153));
		btnReport.setFont(btnReport.getFont().deriveFont(btnReport.getFont().getStyle() & ~Font.BOLD));
		btnReport.setBounds(392, 233, 117, 23);
		contentPane.add(btnReport);
		
		JButton btnFineSetup = new JButton("Fine Setup");
		btnFineSetup.setBackground(new Color(255, 204, 153));
		btnFineSetup.setFont(btnFineSetup.getFont().deriveFont(btnFineSetup.getFont().getStyle() & ~Font.BOLD));
		btnFineSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FineSetup fne=new FineSetup();
				fne.setVisible(true);
			}
		});
		btnFineSetup.setBounds(392, 256, 117, 23);
		contentPane.add(btnFineSetup);
		
		JButton btnCheckExpenditure = new JButton("Check Expenditure");
		btnCheckExpenditure.setBackground(new Color(255, 204, 153));
		btnCheckExpenditure.setFont(btnCheckExpenditure.getFont().deriveFont(btnCheckExpenditure.getFont().getStyle() & ~Font.BOLD));
		btnCheckExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckExpenditure expn=new CheckExpenditure();
				expn.setVisible(true);
			}
		});
		btnCheckExpenditure.setBounds(392, 280, 117, 23);
		contentPane.add(btnCheckExpenditure);
	//	labelBackground.setIcon(new ImageIcon(bacImage));
	//	labelBackground.setBounds(230, 121, 378, 286);
	//	contentPane.add(labelBackground);
		
		JLabel label = new JLabel("London Intelligence School");
		label.setForeground(new Color(205, 133, 63));
		label.setFont(new Font("Times New Roman", Font.BOLD, 28));
		label.setBounds(284, 47, 338, 33);
		contentPane.add(label);
		
		JLabel lbl = new JLabel("");
		lbl.setBounds(230, 31, 50, 68);
		contentPane.add(lbl);
		Image imglogo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		lbl.setIcon(new ImageIcon(imglogo));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(143, 188, 143));
		menuBar.setBounds(0, 0, 1590, 21);
		contentPane.add(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		
		menuBar.add(mnHome);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Home().setVisible(true);
			}
		});
		mnHome.add(mntmHome);
		
		JMenu mnResult = new JMenu("Result");
		menuBar.add(mnResult);
		
		JMenuItem mntmResultSetup = new JMenuItem("Result Setup");
		mntmResultSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ResultDataEntry().setVisible(true);
				
			}
		});
		mnResult.add(mntmResultSetup);
		
		JMenuItem mntmTranscript = new JMenuItem("Transcript");
		mnResult.add(mntmTranscript);
		
		JMenu mnFees = new JMenu("Fees");
		menuBar.add(mnFees);
		
		JMenuItem mntmFeeSetup_1 = new JMenuItem("Fee Setup");
		mnFees.add(mntmFeeSetup_1);
		
		JMenuItem mntmDueFee = new JMenuItem("Due Fee");
		mnFees.add(mntmDueFee);
		
		JMenu mnFine = new JMenu("Fine");
		menuBar.add(mnFine);
		
		JMenuItem mntmFineSetup = new JMenuItem("Fine Setup");
		mnFine.add(mntmFineSetup);
		
		JMenu mnScholarship = new JMenu("Scholarship");
		menuBar.add(mnScholarship);
		
		JMenuItem mntmScholarshipSetup = new JMenuItem("Scholarship setup");
		mnScholarship.add(mntmScholarshipSetup);
		
		JMenu mnTc = new JMenu("TC");
		menuBar.add(mnTc);
		
		JMenu mnExpenditure = new JMenu("Expenditure");
		menuBar.add(mnExpenditure);
		
		JMenuItem mntmExpenditureSetup = new JMenuItem("Expenditure Setup");
		mnExpenditure.add(mntmExpenditureSetup);
		
		JMenuItem mntmCheckExpenditure = new JMenuItem("Check Expenditure");
		mnExpenditure.add(mntmCheckExpenditure);
		
		JMenu mnProductPrice = new JMenu("Product Price");
		menuBar.add(mnProductPrice);
		
		JMenuItem mntmPriceSetup = new JMenuItem("Price Setup");
		mnProductPrice.add(mntmPriceSetup);
		
		JMenu mnAttendence = new JMenu("Attendence");
		menuBar.add(mnAttendence);
		
		JMenuItem mntmStudentAttendence = new JMenuItem("Student Attendance");
		mnAttendence.add(mntmStudentAttendence);
		
		JMenuItem mntmEmployeeAttendance = new JMenuItem("Employee Attendance");
		mnAttendence.add(mntmEmployeeAttendance);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenu mnLeaves = new JMenu("Leaves");
		menuBar.add(mnLeaves);
		
		JMenuItem mntmStudentsLeave = new JMenuItem("Students Leave");
		mnLeaves.add(mntmStudentsLeave);
		
		JMenuItem mntmEmployeeLeave = new JMenuItem("");
		mnLeaves.add(mntmEmployeeLeave);
		
		JButton btnStudentsLeaving = new JButton("Transection");
		btnStudentsLeaving.setBackground(new Color(255, 204, 153));
		btnStudentsLeaving.setFont(btnStudentsLeaving.getFont().deriveFont(btnStudentsLeaving.getFont().getStyle() & ~Font.BOLD));
		btnStudentsLeaving.setBounds(83, 136, 117, 23);
		contentPane.add(btnStudentsLeaving);
		btnStudentsLeaving.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Transection().setVisible(true);
				
			}
		});
		
		JButton btnLeavingApplication = new JButton("Approval");
		btnLeavingApplication.setBackground(new Color(255, 204, 153));
		btnLeavingApplication.setFont(btnLeavingApplication.getFont().deriveFont(btnLeavingApplication.getFont().getStyle() & ~Font.BOLD));
		btnLeavingApplication.setBounds(246, 233, 117, 23);
		contentPane.add(btnLeavingApplication);
		btnLeavingApplication.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PermissionForLeaves().setVisible(true);
				
			}
		});
		
		JButton buttonCourseSetup = new JButton("Course Setup");
		buttonCourseSetup.setBackground(new Color(255, 204, 153));
		buttonCourseSetup.setFont(buttonCourseSetup.getFont().deriveFont(buttonCourseSetup.getFont().getStyle() & ~Font.BOLD));
		buttonCourseSetup.setBounds(246, 211, 117, 23);
		contentPane.add(buttonCourseSetup);
		
		JButton btnAttnChk = new JButton("Attn Chk");
		btnAttnChk.setBackground(new Color(255, 204, 153));
		btnAttnChk.setFont(btnAttnChk.getFont().deriveFont(btnAttnChk.getFont().getStyle() & ~Font.BOLD));
		btnAttnChk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				new AttendanceCheck().setVisible(true);
			}
		});
		btnAttnChk.setBounds(392, 329, 117, 23);
		contentPane.add(btnAttnChk);
		
		JLabel lblVpLogo = new JLabel("");
		lblVpLogo.setBounds(841, 631, 201, 68);
		contentPane.add(lblVpLogo);
		Image imgvplogo=new ImageIcon(this.getClass().getResource("/VillageParkLogo.png")).getImage();
		lblVpLogo.setIcon(new ImageIcon(imgvplogo));
		
		Image im=new ImageIcon(this.getClass().getResource("/bac1.png")).getImage();
		
		JLabel lblbac1 = new JLabel("");
		lblbac1.setBounds(0, 388, 1496, 311);
		contentPane.add(lblbac1);
		lblbac1.setIcon(new ImageIcon(im));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//PrintWriter pw=new PrintWriter("C:\\login.txt");
					PrintWriter pw=new PrintWriter("C:\\LIS\\logfile\\login.txt");
					pw.println("");
					PrintWriter pwuser=new PrintWriter("C:\\LIS\\logfile\\user.txt");
					pwuser.println("");
					pw.close();
					pwuser.close();
					sessionLogout();
					dispose();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
		btnLogout.setBounds(913, 47, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnPayroll = new JButton("Payroll");
		btnPayroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PayrollSetup().setVisible(true);
			}
		});
		btnPayroll.setFont(btnPayroll.getFont().deriveFont(btnPayroll.getFont().getStyle() & ~Font.BOLD));
		btnPayroll.setBackground(new Color(255, 204, 153));
		btnPayroll.setBounds(246, 256, 117, 23);
		contentPane.add(btnPayroll);
		
		JButton btnEmpAttendance = new JButton("Emp Attendance");
		btnEmpAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EmpAttendance().setVisible(true);
			}
		});
		btnEmpAttendance.setFont(btnEmpAttendance.getFont().deriveFont(btnEmpAttendance.getFont().getStyle() & ~Font.BOLD));
		btnEmpAttendance.setBackground(new Color(255, 204, 153));
		btnEmpAttendance.setBounds(246, 280, 117, 23);
		contentPane.add(btnEmpAttendance);
		
		JLabel lblkids = new JLabel("");
		lblkids.setBounds(565, 81, 477, 296);
		Image imgvplog=new ImageIcon(this.getClass().getResource("/kids.png")).getImage();
		lblkids.setIcon(new ImageIcon(imgvplog));
		contentPane.add(lblkids);
		
		JButton btnAccounts = new JButton("Accounts");
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This is Dumy demonestration thats why we havent put Accounts here\nThank you");
			}
		});
		btnAccounts.setFont(btnAccounts.getFont().deriveFont(btnAccounts.getFont().getStyle() & ~Font.BOLD));
		btnAccounts.setBackground(new Color(255, 204, 153));
		btnAccounts.setBounds(246, 189, 117, 23);
		contentPane.add(btnAccounts);
		buttonCourseSetup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new CourseSetup().setVisible(true);
				
			}
		});
		
		
	}
private void sessionLogout(){
		
		Employee empid=new Employee();
		String empId=empid.getEmployeeID();
		//String empId="njj";
		DateFormat logTime=new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		DateFormat dat=new SimpleDateFormat("dd-MMM-yy");

		Calendar canl=Calendar.getInstance();
		String logoutTime=(String)logTime.format(canl.getTime());
		String loginDate=(String)dat.format(canl.getTime());

		System.out.println("time:"+logoutTime+" date:"+loginDate);
		System.out.println("employeeid logout:"+empId);
		String query="update lis_sessions set logout_time=to_date('"+logoutTime+"','dd-mm-yy hh24:mi:ss') where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"' and regular_time=0";
		try {
			 PreparedStatement prst=connection.prepareStatement(query);
			 prst.executeQuery();
			 prst.close();
			JOptionPane.showMessageDialog(null, "logout successfully");

		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
			
		}
	}
}
