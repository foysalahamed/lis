import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JTextArea;


public class PayrollSetup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTable tableEmployeeList;
	private JTable tableIncrement;
	Connection connection=null;
	private JTextField textFieldEmpId;
	String allwonceName;
	String deductionName;
	JTextArea textAreaMessages;
	JDateChooser dateChooserFrom,dateChooserTo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollSetup frame = new PayrollSetup();
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
	public PayrollSetup() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		setBounds(100, 100, 1311, 798);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));
		
		JLabel labeltitle = new JLabel("London Intelligence School");
		labeltitle.setForeground(new Color(210, 105, 30));
		labeltitle.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		labeltitle.setBounds(317, 26, 278, 40);
		contentPane.add(labeltitle);
		
		JLabel labelttl = new JLabel("");
		Image logo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		labelttl.setIcon(new ImageIcon(logo));
		
		labelttl.setBounds(260, 11, 50, 71);
		contentPane.add(labelttl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(293, 91, 900, 608);
		contentPane.add(tabbedPane);
		Image imgalw=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		Image imgded=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		Image imgovr=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		Image imgattnsta=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		
		JPanel panelEmpLoan = new JPanel();
		tabbedPane.addTab("Employee Loan ", null, panelEmpLoan, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 228, 225));
		panelEmpLoan.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 492, 391);
		panelEmpLoan.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Basic Salary");
		lblNewLabel.setBounds(282, 57, 86, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(368, 54, 109, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblEmployeeId = new JLabel("Emp ID");
		lblEmployeeId.setBounds(29, 59, 68, 14);
		panel.add(lblEmployeeId);
		
		JLabel lblHouseRent = new JLabel("House Rent");
		lblHouseRent.setBounds(282, 76, 68, 14);
		panel.add(lblHouseRent);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(368, 73, 109, 20);
		panel.add(textField_1);
		
		JLabel lblMedical = new JLabel("Medical");
		lblMedical.setBounds(282, 96, 68, 14);
		panel.add(lblMedical);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(368, 93, 109, 20);
		panel.add(textField_2);
		
		JLabel lblLunch = new JLabel("Lunch");
		lblLunch.setBounds(282, 116, 68, 14);
		panel.add(lblLunch);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(368, 113, 109, 20);
		panel.add(textField_3);
		
		JLabel lblBonus = new JLabel("Bonus");
		lblBonus.setBounds(282, 156, 68, 14);
		panel.add(lblBonus);
		
		JLabel lblTada = new JLabel("TA/DA");
		lblTada.setBounds(282, 176, 68, 14);
		panel.add(lblTada);
		
		JLabel lblTransport = new JLabel("Transport");
		lblTransport.setBounds(282, 136, 68, 14);
		panel.add(lblTransport);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(368, 133, 109, 20);
		panel.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(368, 173, 109, 20);
		panel.add(textField_6);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(368, 153, 109, 20);
		panel.add(comboBox_1);
		
		JLabel lblHours_1 = new JLabel("Hours");
		lblHours_1.setBounds(31, 154, 68, 14);
		panel.add(lblHours_1);
		
		JLabel lblDays_1 = new JLabel("Days");
		lblDays_1.setBounds(31, 134, 68, 14);
		panel.add(lblDays_1);
		
		JLabel lblHours = new JLabel("Designation");
		lblHours.setBounds(29, 97, 68, 14);
		panel.add(lblHours);
		
		JLabel lblDays = new JLabel("Name");
		lblDays.setBounds(29, 77, 68, 14);
		panel.add(lblDays);
		
		JLabel label_4 = new JLabel("----------");
		label_4.setBounds(112, 154, 114, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("----------");
		label_5.setBounds(112, 134, 114, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Transport");
		label_6.setBounds(110, 97, 114, 14);
		panel.add(label_6);
		
		JLabel labelEmpName = new JLabel("");
		labelEmpName.setBounds(110, 77, 114, 14);
		panel.add(labelEmpName);
		
		JLabel lblPaymentType = new JLabel("Payment Type");
		lblPaymentType.setBounds(28, 114, 86, 14);
		panel.add(lblPaymentType);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "Monthly", "Hourly", "Contract"}));
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(110, 113, 109, 20);
		panel.add(comboBox_2);
		
		JLabel lblOverTime = new JLabel("Rate/Hrs");
		lblOverTime.setBounds(29, 176, 61, 14);
		panel.add(lblOverTime);
		
		JLabel lblOverTime_1 = new JLabel("Over Time");
		lblOverTime_1.setBounds(31, 195, 68, 14);
		panel.add(lblOverTime_1);
		
		JLabel label_2 = new JLabel("----------");
		label_2.setBounds(112, 195, 114, 14);
		panel.add(label_2);
		
		JLabel label_8 = new JLabel("Rate/Hrs");
		label_8.setBounds(29, 217, 61, 14);
		panel.add(label_8);
		
		JLabel lblPayTo = new JLabel("Pay To");
		lblPayTo.setBounds(29, 236, 86, 14);
		panel.add(lblPayTo);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Select", "Bank", "Cash in Hand"}));
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(109, 235, 109, 20);
		panel.add(comboBox_3);
		
		textField_15 = new JTextField();
		textField_15.setBounds(110, 173, 109, 20);
		panel.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(110, 214, 109, 20);
		panel.add(textField_16);
		textField_16.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(29, 261, 453, 101);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblBankName = new JLabel("Bank Name");
		lblBankName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBankName.setBounds(22, 32, 95, 14);
		panel_3.add(lblBankName);
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBounds(161, 29, 86, 20);
		panel_3.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(161, 50, 86, 20);
		panel_3.add(textField_18);
		
		JLabel lblAccountNo = new JLabel("Account No");
		lblAccountNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNo.setBounds(20, 53, 136, 14);
		panel_3.add(lblAccountNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAddress.setBounds(20, 74, 95, 14);
		panel_3.add(lblAddress);
		
		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBounds(161, 71, 86, 20);
		panel_3.add(textField_19);
		
		JLabel lblBankDetails = new JLabel("Bank Details");
		lblBankDetails.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBankDetails.setBounds(159, 4, 95, 14);
		panel_3.add(lblBankDetails);
		
		JLabel lblNewLabel_1 = new JLabel("Salary");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 228, 181));
		lblNewLabel_1.setBounds(20, 13, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblSalr = new JLabel("");
		lblSalr.setBounds(10, 11, 472, 20);
		panel.add(lblSalr);
		Image imgsal=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		lblSalr.setIcon(new ImageIcon(imgsal));
		
		textFieldEmpId = new JTextField();
		textFieldEmpId.setEditable(false);
		textFieldEmpId.setColumns(10);
		textFieldEmpId.setBounds(107, 54, 109, 20);
		panel.add(textFieldEmpId);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(512, 303, 330, 99);
		panelEmpLoan.add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Net Pay:  TK");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(39, 71, 95, 14);
		panel_2.add(label);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(150, 68, 86, 20);
		panel_2.add(textField_12);
		
		JLabel label_1 = new JLabel("Total Deduction:  TK");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 50, 136, 14);
		panel_2.add(label_1);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(150, 47, 86, 20);
		panel_2.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBounds(150, 26, 86, 20);
		panel_2.add(textField_14);
		
		JLabel label_3 = new JLabel("Total Pay:  TK");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(45, 29, 95, 14);
		panel_2.add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(512, 11, 373, 285);
		panelEmpLoan.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setLayout(null);
		
		JLabel lblOthers_1 = new JLabel("Others");
		lblOthers_1.setBounds(31, 154, 68, 14);
		panel_1.add(lblOthers_1);
		
		JLabel lblOthers = new JLabel("Provident Fund");
		lblOthers.setBounds(31, 134, 79, 14);
		panel_1.add(lblOthers);
		
		JLabel lblInsurance = new JLabel("Insurance");
		lblInsurance.setBounds(31, 114, 68, 14);
		panel_1.add(lblInsurance);
		
		JLabel lblLoan = new JLabel("Loan");
		lblLoan.setBounds(31, 94, 68, 14);
		panel_1.add(lblLoan);
		
		JLabel lblProvidentFund = new JLabel("Tax ");
		lblProvidentFund.setBounds(31, 75, 79, 14);
		panel_1.add(lblProvidentFund);
		
		JLabel lblTax = new JLabel("Tax ID");
		lblTax.setBounds(31, 53, 68, 14);
		panel_1.add(lblTax);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(115, 74, 109, 20);
		panel_1.add(textField_5);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(115, 93, 109, 20);
		panel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(115, 113, 109, 20);
		panel_1.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(115, 133, 109, 20);
		panel_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(115, 153, 109, 20);
		panel_1.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(115, 54, 109, 20);
		panel_1.add(textField_11);
		
		JLabel lblDeduction = new JLabel("Deduction");
		lblDeduction.setForeground(new Color(255, 228, 181));
		lblDeduction.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDeduction.setBounds(20, 13, 310, 14);
		panel_1.add(lblDeduction);
		
		JLabel labelDed = new JLabel("");
		labelDed.setBounds(10, 11, 353, 20);
		panel_1.add(labelDed);
		labelDed.setIcon(new ImageIcon(imgded));
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(203, 417, 89, 23);
		panelEmpLoan.add(btnNewButton);
		Image imgbtn=new ImageIcon(this.getClass().getResource("/btnbac.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(imgbtn));
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(308, 417, 89, 23);
		panelEmpLoan.add(btnNewButton_1);
		
		JPanel panelPaySlip = new JPanel();
		tabbedPane.addTab("Pay Slip", null, panelPaySlip, null);
		panelPaySlip.setBackground(Color.WHITE);
		panelPaySlip.setLayout(null);
		
		
		JLabel lblOverTime_2 = new JLabel("Over Time");
		lblOverTime_2.setBounds(65, 56, 134, 14);
		lblOverTime_2.setForeground(new Color(255, 228, 181));
		lblOverTime_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panelPaySlip.add(lblOverTime_2);
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(55, 54, 345, 20);
		panelPaySlip.add(label_9);
		label_9.setIcon(new ImageIcon(imgovr));
		
		JLabel lblAttendanceStatus = new JLabel("Attendance Status");
		lblAttendanceStatus.setBounds(437, 56, 134, 14);
		lblAttendanceStatus.setForeground(new Color(255, 228, 181));
		lblAttendanceStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panelPaySlip.add(lblAttendanceStatus);
		
		JLabel labelAttnStatus = new JLabel("");
		labelAttnStatus.setBounds(427, 54, 384, 20);
		panelPaySlip.add(labelAttnStatus);
		labelAttnStatus.setIcon(new ImageIcon(imgattnsta));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(55, 222, 756, 178);
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBackground(Color.WHITE);
		panelPaySlip.add(panel_4);
		
		JLabel label_10 = new JLabel("Net Pay:  TK");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(39, 71, 95, 14);
		panel_4.add(label_10);
		
		textFieldNetSalary = new JTextField();
		textFieldNetSalary.setEditable(false);
		textFieldNetSalary.setColumns(10);
		textFieldNetSalary.setBounds(150, 68, 86, 20);
		panel_4.add(textFieldNetSalary);
		
		JLabel label_11 = new JLabel("Total Deduction:  TK");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_11.setBounds(10, 50, 136, 14);
		panel_4.add(label_11);
		
		textFieldTotalDeduction = new JTextField();
		textFieldTotalDeduction.setEditable(false);
		textFieldTotalDeduction.setColumns(10);
		textFieldTotalDeduction.setBounds(150, 47, 86, 20);
		panel_4.add(textFieldTotalDeduction);
		
		textFieldTotalPay = new JTextField();
		textFieldTotalPay.setEditable(false);
		textFieldTotalPay.setColumns(10);
		textFieldTotalPay.setBounds(150, 26, 86, 20);
		panel_4.add(textFieldTotalPay);
		
		JLabel label_12 = new JLabel("Total Pay:  TK");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(45, 29, 95, 14);
		panel_4.add(label_12);
		
		JLabel lblBankAc = new JLabel("Bank A/C #");
		lblBankAc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBankAc.setBounds(534, 29, 77, 14);
		panel_4.add(lblBankAc);
		
		JLabel lblBank = new JLabel("Bank ");
		lblBank.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBank.setBounds(347, 67, 86, 14);
		panel_4.add(lblBank);
		
		JLabel lblEmployeeName = new JLabel("Name");
		lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployeeName.setBounds(347, 29, 95, 14);
		panel_4.add(lblEmployeeName);
		
		textFieldEmpName = new JTextField();
		textFieldEmpName.setEditable(false);
		textFieldEmpName.setColumns(10);
		textFieldEmpName.setBounds(439, 23, 86, 20);
		panel_4.add(textFieldEmpName);
		
		textFieldBank = new JTextField();
		textFieldBank.setEditable(false);
		textFieldBank.setColumns(10);
		textFieldBank.setBounds(439, 64, 86, 20);
		panel_4.add(textFieldBank);
		
		textFieldAccounNo = new JTextField();
		textFieldAccounNo.setEditable(false);
		textFieldAccounNo.setColumns(10);
		textFieldAccounNo.setBounds(622, 23, 124, 20);
		panel_4.add(textFieldAccounNo);
		
		 textAreaMessages = new JTextArea();
		textAreaMessages.setBackground(Color.LIGHT_GRAY);
		textAreaMessages.setBounds(150, 111, 483, 45);
		panel_4.add(textAreaMessages);
		
		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessages.setBounds(39, 116, 95, 14);
		panel_4.add(lblMessages);
		
		JLabel label_7 = new JLabel("Adrress");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(347, 50, 95, 14);
		panel_4.add(label_7);
		
		textFieldEmpAddress = new JTextField();
		textFieldEmpAddress.setEditable(false);
		textFieldEmpAddress.setColumns(10);
		textFieldEmpAddress.setBounds(439, 44, 86, 20);
		panel_4.add(textFieldEmpAddress);
		
		JLabel lblTin = new JLabel("TIN #");
		lblTin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTin.setBounds(534, 48, 67, 14);
		panel_4.add(lblTin);
		
		textFieldTinNo = new JTextField();
		textFieldTinNo.setEditable(false);
		textFieldTinNo.setColumns(10);
		textFieldTinNo.setBounds(622, 44, 124, 20);
		panel_4.add(textFieldTinNo);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setBounds(64, 205, 279, 14);
		lblSummary.setForeground(new Color(255, 228, 181));
		lblSummary.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panelPaySlip.add(lblSummary);
		
		JLabel labelSummary = new JLabel("");
		labelSummary.setBounds(54, 203, 757, 20);
		panelPaySlip.add(labelSummary);
		Image imgsum=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelSummary.setIcon(new ImageIcon(imgsum));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(427, 74, 384, 121);
		panelPaySlip.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblLeave = new JLabel("Presents");
		lblLeave.setBounds(118, 49, 59, 14);
		panel_5.add(lblLeave);
		
		JLabel lblNewLabel_2 = new JLabel("Leave");
		lblNewLabel_2.setBounds(118, 71, 46, 14);
		panel_5.add(lblNewLabel_2);
		
		JLabel lblLate = new JLabel("Late");
		lblLate.setBounds(118, 94, 46, 14);
		panel_5.add(lblLate);
		
		textFieldEmpPresents = new JTextField();
		textFieldEmpPresents.setEditable(false);
		textFieldEmpPresents.setBounds(176, 46, 86, 20);
		panel_5.add(textFieldEmpPresents);
		textFieldEmpPresents.setColumns(10);
		
		textFieldEmpLeave = new JTextField();
		textFieldEmpLeave.setEditable(false);
		textFieldEmpLeave.setColumns(10);
		textFieldEmpLeave.setBounds(176, 68, 86, 20);
		panel_5.add(textFieldEmpLeave);
		
		textFieldEmplate = new JTextField();
		textFieldEmplate.setEditable(false);
		textFieldEmplate.setColumns(10);
		textFieldEmplate.setBounds(176, 90, 86, 20);
		panel_5.add(textFieldEmplate);
		
		JLabel lblSalaryMonth = new JLabel("Salary  Duration");
		lblSalaryMonth.setBounds(55, 13, 98, 14);
		panelPaySlip.add(lblSalaryMonth);
		
		 dateChooserFrom = new JDateChooser();
		 dateChooserFrom.setBounds(163, 13, 95, 20);
		 panelPaySlip.add(dateChooserFrom);
		 
		  dateChooserTo = new JDateChooser();
		  dateChooserTo.setBounds(298, 13, 95, 20);
		  panelPaySlip.add(dateChooserTo);
		  
		  JButton btnPrint = new JButton("Print");
		  btnPrint.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		String query="select p.pay_slip_id,p.emp_id,p.payed_month,p.emp_name,p.emp_address,p.regular_hour,"
		  				+ "p.over_time,p.ot_rate,p.ot_taka,p.total_salary,p.total_deduct,p.net_salary,p.taxid,"
		  				+ "p.messages from lis_emp_pay_slip p where p.emp_id='"+empId+"' and p.payed_month='"+"Dec-17"+"'";
		  		//String query="select p.pay_slip_id,p.emp_id,p.payed_month,p.emp_name,p.emp_address,p.regular_hour,p.over_time,p.ot_rate,p.ot_taka,p.total_salary,p.total_deduct,p.net_salary,p.taxid,p.messages,a.allownce_name,a.new_amount,d.deduction_name,d.amount from lis_emp_pay_slip p join lis_emp_allownces a on p.emp_id=a.emp_id join lis_emp_deduction d on p.emp_id=d.emp_id where p.emp_id='"+empId+"' and p.payed_month='"+"Dec-16"+"'";
		  		String reportPath="C:\\LIS\\report\\lis_paySlip.jrxml";
		  		report(query,reportPath);
		  	}
		  });
		  btnPrint.setBounds(422, 411, 89, 23);
		  panelPaySlip.add(btnPrint);
		  
		  JButton btnSave = new JButton("Save");
		  btnSave.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		DateFormat dat=new SimpleDateFormat("MMyy");
				Calendar canl=Calendar.getInstance();
				String m_y=(String)dat.format(canl.getTime());
				String paySlipID=empId+m_y;
				
				DateFormat payM=new SimpleDateFormat("MMM-yy");
				Calendar cln=Calendar.getInstance();
				String paymentM=(String)payM.format(cln.getTime());
				System.out.println("Pay Slip: "+paySlipID);
				String netSalry=textFieldNetSalary.getText();
				int nSal=(int)Double.parseDouble(netSalry);
				String salarySpell=NumberSpellOut.convert(nSal);
				System.out.println("Salry Spell out:"+salarySpell);

		  		int option = JOptionPane.showConfirmDialog(null, "Do you want save it", "Increment", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String query="insert into LIS_EMP_PAY_SLIP values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					try{
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1,paySlipID);
					prstatement.setString(2,empId );
					prstatement.setString(3,paymentM);
					prstatement.setString(4, textFieldEmpName.getText());
					prstatement.setString(5, textFieldEmpAddress.getText());
					prstatement.setDouble(6, Double.parseDouble(textFieldTtlBasicHour.getText()));
					prstatement.setDouble(7, Double.parseDouble(textFieldOverTime.getText()));
					prstatement.setDouble(8, Double.parseDouble(textFieldOtRateTK.getText()));
					prstatement.setDouble(9, Double.parseDouble(textFieldOverTimeTK.getText()));
					prstatement.setDouble(10, Double.parseDouble(textFieldTotalPay.getText()));
					prstatement.setDouble(11, Double.parseDouble(textFieldTotalDeduction.getText()));
					prstatement.setDouble(12, Double.parseDouble(textFieldNetSalary.getText()));
					prstatement.setString(13, textFieldTinNo.getText());
					prstatement.setString(14, textAreaMessages.getText());
					prstatement.setString(15, salarySpell);


					prstatement.executeQuery();
					prstatement.close();
					JOptionPane.showMessageDialog(null, "Suceesully Inserted");
					
				}
					catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e);
					}


				}	   

		  		
		  	}
		  });
		  btnSave.setBounds(332, 411, 89, 23);
		  panelPaySlip.add(btnSave);
		  
		  JPanel panel_6 = new JPanel();
		  panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		  panel_6.setBackground(Color.WHITE);
		  panel_6.setBounds(55, 74, 344, 120);
		  panelPaySlip.add(panel_6);
		  panel_6.setLayout(null);
		  
		  JLabel lblTotalHour = new JLabel("Total Basic Hour");
		  lblTotalHour.setBounds(10, 22, 110, 14);
		  panel_6.add(lblTotalHour);
		  
		  textFieldTtlBasicHour = new JTextField();
		  textFieldTtlBasicHour.setFont(new Font("Tahoma", Font.BOLD, 12));
		  textFieldTtlBasicHour.setEditable(false);
		  textFieldTtlBasicHour.setColumns(10);
		  textFieldTtlBasicHour.setBounds(130, 19, 86, 20);
		  panel_6.add(textFieldTtlBasicHour);
		  
		  JLabel lblOverTime_3 = new JLabel("Over Time");
		  lblOverTime_3.setBounds(12, 43, 98, 14);
		  panel_6.add(lblOverTime_3);
		  
		  textFieldOverTime = new JTextField();
		  textFieldOverTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		  textFieldOverTime.setEditable(false);
		  textFieldOverTime.setColumns(10);
		  textFieldOverTime.setBounds(130, 41, 86, 20);
		  panel_6.add(textFieldOverTime);
		  
		  JLabel lblAmount = new JLabel("Rate OT/Hr");
		  lblAmount.setBounds(13, 65, 98, 14);
		  panel_6.add(lblAmount);
		  
		  textFieldOtRateTK = new JTextField();
		  textFieldOtRateTK.setFont(new Font("Tahoma", Font.BOLD, 12));
		  textFieldOtRateTK.setEditable(false);
		  textFieldOtRateTK.setColumns(10);
		  textFieldOtRateTK.setBounds(130, 63, 86, 20);
		  panel_6.add(textFieldOtRateTK);
		  
		  JLabel lblTotalAmount = new JLabel("Total Amount TK");
		  lblTotalAmount.setBounds(11, 87, 98, 14);
		  panel_6.add(lblTotalAmount);
		  
		  textFieldOverTimeTK = new JTextField();
		  textFieldOverTimeTK.setFont(new Font("Tahoma", Font.BOLD, 12));
		  textFieldOverTimeTK.setEditable(false);
		  textFieldOverTimeTK.setColumns(10);
		  textFieldOverTimeTK.setBounds(130, 85, 86, 20);
		  panel_6.add(textFieldOverTimeTK);
		
		JPanel panelIncrement = new JPanel();
		panelIncrement.setBackground(Color.WHITE);
		tabbedPane.addTab("Increment", null, panelIncrement, null);
		tabbedPane.setBackgroundAt(2, new Color(245, 222, 179));
		panelIncrement.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 65, 407, 166);
		panelIncrement.add(scrollPane_1);
		
		tableIncrement = new JTable();
		scrollPane_1.setViewportView(tableIncrement);
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField description = new JTextField();
				JTextField amount = new JTextField();
				JTextField remarks= new JTextField();
				Object[] message = {
				    "Increment ID (inc+empID+123...)", description,
				    "Amount", amount,
				    "Remarks", remarks
				};
				
				DateFormat dat=new SimpleDateFormat("dd-MMM-yy");
				Calendar canl=Calendar.getInstance();
				String increDate=(String)dat.format(canl.getTime());

				int option = JOptionPane.showConfirmDialog(null, message, "Increment", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String query="insert into LIS_EMP_INCREMENT values(?,?,?,?,?)";
					try{
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1,empId );
					prstatement.setString(2,description.getText() );
					prstatement.setString(3,increDate );
					prstatement.setInt(4, Integer.parseInt(amount.getText()));
					prstatement.setString(5,remarks.getText() );

					prstatement.executeQuery();
					prstatement.close();
					//JOptionPane.showMessageDialog(null, "Suceesully Inserted");
					refreshIncrementTable();
					updateBasicSalary(amount.getText());
					
				}
					catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e);
					}


				}	   
				
				
			}
		});
		btnNewButton_2.setBounds(166, 237, 89, 23);
		panelIncrement.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int roid=tableIncrement.getSelectedRow();
				String increment=tableIncrement.getModel().getValueAt(roid,1).toString();
					
				int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
				if(opt==JOptionPane.OK_OPTION){
					String query="delete from LIS_EMP_INCREMENT where increment_id='"+increment+"' and emp_id='"+empId+"'";
					try {
						PreparedStatement prst=connection.prepareStatement(query);
						prst.executeQuery();
						prst.close();
						refreshIncrementTable();
						
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
			
		});
		btnNewButton_3.setBounds(258, 237, 89, 23);
		btnNewButton_3.setForeground(Color.RED);
		panelIncrement.add(btnNewButton_3);
		
		JLabel lblIncrement = new JLabel("Increment");
		lblIncrement.setForeground(new Color(255, 228, 181));
		lblIncrement.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIncrement.setBounds(51, 47, 82, 14);
		panelIncrement.add(lblIncrement);
		
		JLabel labelIncrement = new JLabel("");
		labelIncrement.setBounds(41, 45, 407, 20);
		panelIncrement.add(labelIncrement);
		Image imsal=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelIncrement.setIcon(new ImageIcon(imsal));
		
		JPanel panelAchademicHis = new JPanel();
		panelAchademicHis.setBackground(Color.WHITE);
		tabbedPane.addTab("Education & Experiances", null, panelAchademicHis, null);
		panelAchademicHis.setLayout(null);
		
		JLabel lblEducation = new JLabel("Education");
		lblEducation.setForeground(new Color(255, 228, 181));
		lblEducation.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEducation.setBounds(60, 44, 134, 14);
		panelAchademicHis.add(lblEducation);
		
		JLabel labelAchademic = new JLabel("");
		labelAchademic.setBounds(50, 42, 592, 20);
		panelAchademicHis.add(labelAchademic);
		Image imgacha=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelAchademic.setIcon(new ImageIcon(imgacha));
		
		
		JButton button_6 = new JButton("Add");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField txtTitle = new JTextField();
				JTextField txtInsti = new JTextField();
				JTextField txtBoard = new JTextField();
				JTextField txtpassingyr = new JTextField();
				JTextField txtgrade = new JTextField();
				
				/*JComboBox comboBoxgr = new JComboBox();
				comboBoxgr.setModel(new DefaultComboBoxModel(new String[] {"Select", "5", "Cash in Hand"}));
				comboBoxgr.setBackground(Color.WHITE);*/
				//comboBoxgr.setBounds(109, 235, 109, 20);
				
				
				Object[] message = {
				    "Title", txtTitle,
				    "Institute", txtInsti,				 
				    "Passing Yer", txtpassingyr,
				    "Board", txtBoard,
				    "Grade Point", txtgrade
				    
				};

				int option = JOptionPane.showConfirmDialog(null, message, "Education Details", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String query="insert into LIS_EMP_EDUCATIONS values(?,?,?,?,?,?)";
					try{
				PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1,empId );
					prstatement.setString(2,txtTitle.getText() );
					prstatement.setString(3, txtInsti.getText());
					prstatement.setString(4,txtpassingyr.getText() );
					prstatement.setString(5,txtBoard.getText() );
					prstatement.setString(6,txtgrade.getText() );
					
					prstatement.executeQuery();
					prstatement.close();
					refreshEducationTable();
					
				}
					catch(Exception e){
						e.printStackTrace();
					}


				   
				}

			}
		});
		button_6.setBounds(239, 184, 89, 23);
		panelAchademicHis.add(button_6);
		
		JButton button_7 = new JButton("Del");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowId=tableEducation.getSelectedRow();
				String ttl=tableEducation.getModel().getValueAt(rowId, 1).toString();
				
				int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
				if(opt==JOptionPane.OK_OPTION){
					String query="delete from LIS_EMP_EDUCATIONS where title='"+ttl+"' and emp_id='"+empId+"'";
					try {
						PreparedStatement prst=connection.prepareStatement(query);
						prst.executeQuery();
						prst.close();
						refreshEducationTable();
						
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		button_7.setForeground(Color.RED);
		button_7.setBounds(331, 184, 89, 23);
		panelAchademicHis.add(button_7);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(50, 62, 595, 120);
		panelAchademicHis.add(scrollPane_6);
		
		tableEducation = new JTable();
		scrollPane_6.setViewportView(tableEducation);
		
		JButton button_8 = new JButton("Del");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowId=tableExperiances.getSelectedRow();
				String desig=tableExperiances.getModel().getValueAt(rowId, 1).toString();
				
				int rowId2=tableExperiances.getSelectedRow();
				String compny=tableExperiances.getModel().getValueAt(rowId2, 2).toString();
					
				int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
				if(opt==JOptionPane.OK_OPTION){
					String query="delete from LIS_EMP_EXPERIANCES where designation='"+desig+"'and company='"+compny+"' and emp_id='"+empId+"'";
					try {
						PreparedStatement prst=connection.prepareStatement(query);
						prst.executeQuery();
						prst.close();
						refreshExperiancesTable();
						
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		button_8.setForeground(Color.RED);
		button_8.setBounds(332, 353, 89, 23);
		panelAchademicHis.add(button_8);
		
		
		JLabel lblExperiances = new JLabel("Experiances");
		lblExperiances.setForeground(new Color(255, 228, 181));
		lblExperiances.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblExperiances.setBounds(61, 213, 134, 14);
		panelAchademicHis.add(lblExperiances);
		
		JLabel labelExperiances = new JLabel("");
		labelExperiances.setBounds(51, 211, 592, 20);
		panelAchademicHis.add(labelExperiances);
		Image imgexp=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelExperiances.setIcon(new ImageIcon(imgexp));
		
		JButton button_9 = new JButton("Add");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField txtDesig = new JTextField();
				JTextField txtCompany = new JTextField();
				JTextField txtSalary = new JTextField();
				JDateChooser dateChooserStartingDate=new JDateChooser();
				JDateChooser dateChooserEndingDate=new JDateChooser();
				JTextField txtRes = new JTextField();

				/*JComboBox comboBoxgr = new JComboBox();
				comboBoxgr.setModel(new DefaultComboBoxModel(new String[] {"Select", "5", "Cash in Hand"}));
				comboBoxgr.setBackground(Color.WHITE);*/
				//comboBoxgr.setBounds(109, 235, 109, 20);
				
				
				Object[] message = {
				    "Designation", txtDesig,
				    "Company", txtCompany,				 
				    "Salary", txtSalary,
				    "Starting Date", dateChooserStartingDate,
				    "Ending Date", dateChooserEndingDate,
				    "Resposibilites",txtRes
				};
				//txtBoard.setText("Foysal");
				int option = JOptionPane.showConfirmDialog(null, message, "Experiance Details", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) {
					String query="insert into LIS_EMP_EXPERIANCES values(?,?,?,?,?,?,?)";
					try{
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1,empId );
					prstatement.setString(2,txtDesig.getText() );
					prstatement.setString(3, txtCompany.getText());
					prstatement.setDouble(4,Double.parseDouble(txtSalary.getText())) ;
					prstatement.setString(5,((JTextField)dateChooserStartingDate.getDateEditor().getUiComponent()).getText() );
					prstatement.setString(6,((JTextField)dateChooserEndingDate.getDateEditor().getUiComponent()).getText() );
					prstatement.setString(7,txtRes.getText() );

					prstatement.executeQuery();
					prstatement.close();
					refreshExperiancesTable();
					
				}
					catch(Exception e){
						e.printStackTrace();
					}


				   
				}


			}
		});
		button_9.setBounds(240, 353, 89, 23);
		panelAchademicHis.add(button_9);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(51, 231, 602, 104);
		panelAchademicHis.add(scrollPane_7);
		
		tableExperiances = new JTable();
		scrollPane_7.setViewportView(tableExperiances);
		tabbedPane.setBackgroundAt(3, new Color(238, 232, 170));
		Image imgdedu=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		
		JPanel panelIssued = new JPanel();
		panelIssued.setBackground(Color.WHITE);
		tabbedPane.addTab("Issued Assets", null, panelIssued, null);
		panelIssued.setLayout(null);
		
		JLabel lblIssuedAssetsTo = new JLabel("Issued Assets to Employee");
		lblIssuedAssetsTo.setForeground(new Color(255, 228, 181));
		lblIssuedAssetsTo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIssuedAssetsTo.setBounds(53, 30, 181, 14);
		panelIssued.add(lblIssuedAssetsTo);
		
		JLabel labelIssuedAsst = new JLabel("");
		labelIssuedAsst.setBounds(43, 28, 592, 20);
		panelIssued.add(labelIssuedAsst);
		Image imgissued=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelIssuedAsst.setIcon(new ImageIcon(imgissued));
		JButton button_10 = new JButton("Add");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField txtEquipId = new JTextField();
				JTextField txtEquipName = new JTextField();
				JTextField txtQuantity = new JTextField();
				JDateChooser dateChooserIssuedDate=new JDateChooser();
				JTextField txtRemarks = new JTextField();

				/*JComboBox comboBoxgr = new JComboBox();
				comboBoxgr.setModel(new DefaultComboBoxModel(new String[] {"Select", "5", "Cash in Hand"}));
				comboBoxgr.setBackground(Color.WHITE);*/
				//comboBoxgr.setBounds(109, 235, 109, 20);
				
				
				Object[] message = {
					"Issue Date", dateChooserIssuedDate,
				    "Equipment ID", txtEquipId,				   
				    "Equipment Name", txtEquipName,	
				    "Quantity", txtQuantity,
				    "Remarks",txtRemarks
				};
				//txtBoard.setText("Foysal");
				int option = JOptionPane.showConfirmDialog(null, message, "Issued Assets to Employee", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) {
					String query="insert into LIS_EMP_ISSUED_ASSETS values(?,?,?,?,?,?,?,?)";
					try{
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1,((JTextField)dateChooserIssuedDate.getDateEditor().getUiComponent()).getText() );
					prstatement.setString(2,empId );
					prstatement.setString(3,txtEquipId.getText() );
					prstatement.setString(4, txtEquipName.getText());
					prstatement.setDouble(5,Double.parseDouble(txtQuantity.getText())) ;
					prstatement.setString(6,"" );
					prstatement.setString(7,"" );
					prstatement.setString(8,txtRemarks.getText() );

					prstatement.executeQuery();
					prstatement.close();
					refreshIssuedAssetsTable();
					
				}
					catch(Exception e){
						e.printStackTrace();
					}


				   
				}



			}
		});
		button_10.setBounds(232, 170, 89, 23);
		panelIssued.add(button_10);
		
		JButton button_11 = new JButton("Del");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int roid=tableIssuedAssets.getSelectedRow();
				String equipId=tableIssuedAssets.getModel().getValueAt(roid, 2).toString();
				int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
				if(opt==JOptionPane.OK_OPTION){
				String query="delete from LIS_EMP_ISSUED_ASSETS where equipment_id='"+equipId+"' and emp_id='"+empId+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.executeQuery();
					prst.close();
					refreshIssuedAssetsTable();
					
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e1);
				}
				}
			}
		});
		button_11.setForeground(Color.RED);
		button_11.setBounds(324, 170, 89, 23);
		panelIssued.add(button_11);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(43, 47, 594, 110);
		panelIssued.add(scrollPane_8);
		
		tableIssuedAssets = new JTable();
		scrollPane_8.setViewportView(tableIssuedAssets);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int roid=tableIssuedAssets.getSelectedRow();
				String equipId=tableIssuedAssets.getModel().getValueAt(roid, 2).toString();
				
				JDateChooser dateChooserReturnDate=new JDateChooser();
				JTextField txtReturnStatus = new JTextField();
				Object[] message = {
					"Return Date", dateChooserReturnDate,
				    "Return Status",txtReturnStatus
				};
				//txtBoard.setText("Foysal");
				int option = JOptionPane.showConfirmDialog(null, message, "Equipment Return", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) {
					String rdate=((JTextField)dateChooserReturnDate.getDateEditor().getUiComponent()).getText();
					String query="update LIS_EMP_ISSUED_ASSETS set return_date='"+rdate+"',return_status='"+txtReturnStatus.getText()+"' where emp_id='"+empId+"' and equipment_id='"+equipId+"'";
					try{
					PreparedStatement prstatement=connection.prepareStatement(query);
					
					prstatement.executeQuery();
					prstatement.close();
					refreshIssuedAssetsTable();
					
				}
					catch(Exception e){
						e.printStackTrace();
					}


				   
				}

			}
		});
		btnEdit.setForeground(new Color(0, 100, 0));
		btnEdit.setBounds(415, 170, 89, 23);
		panelIssued.add(btnEdit);
		tabbedPane.setBackgroundAt(4, new Color(255, 228, 225));
		//Image imgsum=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		
		JPanel panelSettings = new JPanel();
		panelSettings.setBackground(Color.WHITE);
		tabbedPane.addTab(" Variable Settings", null, panelSettings, null);
		panelSettings.setLayout(null);
		
		JLabel lblOverTimeSettings = new JLabel("Over Time Settings");
		lblOverTimeSettings.setForeground(new Color(255, 228, 181));
		lblOverTimeSettings.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOverTimeSettings.setBounds(55, 233, 132, 14);
		panelSettings.add(lblOverTimeSettings);
		
		JLabel labelOvrTimeSettings = new JLabel("");
		labelOvrTimeSettings.setBounds(45, 231, 328, 20);
		panelSettings.add(labelOvrTimeSettings);
		Image imgovrtmst=new ImageIcon(this.getClass().getResource("/lblbac2.png")).getImage();
		labelOvrTimeSettings.setIcon(new ImageIcon(imgovrtmst));
		
		JLabel label_13 = new JLabel("Allownces");
		label_13.setForeground(new Color(255, 228, 181));
		label_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_13.setBounds(55, 28, 134, 14);
		panelSettings.add(label_13);
		
		JLabel labelVariableAllownces = new JLabel("");
		labelVariableAllownces.setBounds(45, 26, 366, 20);
		panelSettings.add(labelVariableAllownces);
		Image imgvarallownce=new ImageIcon(this.getClass().getResource("lblbac2.png")).getImage();
		labelVariableAllownces.setIcon(new ImageIcon(imgvarallownce));
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				allownceAdd();
			}
		});
		button.setBounds(126, 178, 89, 23);
		panelSettings.add(button);
		
		JButton button_1 = new JButton("Del");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteAllownces();
			}
		});
		button_1.setForeground(Color.RED);
		button_1.setBounds(218, 178, 89, 23);
		panelSettings.add(button_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(45, 48, 367, 119);
		panelSettings.add(scrollPane_2);
		
		tablevarAllownces = new JTable();
		tablevarAllownces.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowId=tablevarAllownces.getSelectedRow();
				 allwonceName=tablevarAllownces.getModel().getValueAt(rowId, 1).toString();
				
			}
		});
		tablevarAllownces.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_UP||event.getKeyCode()==KeyEvent.VK_DOWN){
					int rowId=tablevarAllownces.getSelectedRow();
					 allwonceName=tablevarAllownces.getModel().getValueAt(rowId, 1).toString();
					
				}
			}
		});
		scrollPane_2.setViewportView(tablevarAllownces);
		
		JLabel lblDeduction_2 = new JLabel("Deductions");
		lblDeduction_2.setForeground(new Color(255, 228, 181));
		lblDeduction_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDeduction_2.setBounds(468, 28, 134, 14);
		panelSettings.add(lblDeduction_2);
		
		JLabel labelVarDeduction = new JLabel("");
		labelVarDeduction.setBounds(457, 28, 366, 20);
		panelSettings.add(labelVarDeduction);
		Image varDeduc=new ImageIcon(this.getClass().getResource("/lblbac2.png")).getImage();
		labelVarDeduction.setIcon(new ImageIcon(varDeduc));
		JButton button_4 = new JButton("Add");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDeductionItem();
			}
		});
		button_4.setBounds(538, 180, 89, 23);
		panelSettings.add(button_4);
		
		JButton button_5 = new JButton("Del");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteDeduction();
			}
		});
		button_5.setForeground(Color.RED);
		button_5.setBounds(630, 180, 89, 23);
		panelSettings.add(button_5);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(455, 49, 350, 118);
		panelSettings.add(scrollPane_5);
		
		tableVariableDeduct = new JTable();
		tableVariableDeduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowId=tableVariableDeduct.getSelectedRow();
				 deductionName=tableVariableDeduct.getModel().getValueAt(rowId, 1).toString();
				
			}
		});
		tableVariableDeduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_UP||event.getKeyCode()==KeyEvent.VK_DOWN){
					int rowId=tableVariableDeduct.getSelectedRow();
					 deductionName=tableVariableDeduct.getModel().getValueAt(rowId, 1).toString();
					
				}
			}
		});
		scrollPane_5.setViewportView(tableVariableDeduct);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(44, 253, 366, 101);
		panelSettings.add(scrollPane_3);
		
		tableOverTimeRate = new JTable();
		scrollPane_3.setViewportView(tableOverTimeRate);
		
		JButton button_2 = new JButton("Add");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				overTimeRateAdd();
			}
		});
		button_2.setBounds(136, 356, 89, 23);
		panelSettings.add(button_2);
		
		JButton button_3 = new JButton("Del");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteOvertimeRate();
			}
		});
		button_3.setForeground(Color.RED);
		button_3.setBounds(228, 356, 89, 23);
		panelSettings.add(button_3);
		tabbedPane.setBackgroundAt(5, new Color(255, 218, 185));
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 112, 271, 587);
		contentPane.add(scrollPane);
		
		tableEmployeeList = new JTable();
		tableEmployeeList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_DOWN||event.getKeyCode()==KeyEvent.VK_UP){
					getDataFromEmpTable();
					getAllowncesData();
					getDeductionData();
					getAttnDetails();
					netPayment();
					getBankDetails();
					refreshIncrementTable();
					refreshEducationTable();
					refreshExperiancesTable();
					refreshIssuedAssetsTable();
					refreshWorkingHour();
					refreshOverTimeRateTable();
				}
			}
		});
		tableEmployeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getDataFromEmpTable();
				getAllowncesData();
				getDeductionData();
				getAttnDetails();
				netPayment();
				getBankDetails();
				refreshIncrementTable();
				refreshEducationTable();
				refreshExperiancesTable();
				refreshIssuedAssetsTable();
				refreshWorkingHour();
				refreshOverTimeRateTable();
	

			}
		});
		scrollPane.setViewportView(tableEmployeeList);
		
		JLabel lblEmployeeDetails = new JLabel("Employee Details");
		lblEmployeeDetails.setForeground(new Color(255, 228, 181));
		lblEmployeeDetails.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEmployeeDetails.setBounds(20, 95, 132, 14);
		contentPane.add(lblEmployeeDetails);
		
		JLabel labelEmployeeDetails = new JLabel("");
		labelEmployeeDetails.setBounds(10, 93, 271, 20);
		contentPane.add(labelEmployeeDetails);
		Image imged=new ImageIcon(this.getClass().getResource("/lblbac2.png")).getImage();
		labelEmployeeDetails.setIcon(new ImageIcon(imged));
		refreshEmployeelistTable();
	}


	protected void report(String query, String reportPath) {
		
			
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

	protected void deleteOvertimeRate() {
		int roid=tableOverTimeRate.getSelectedRow();
		String grd=tableOverTimeRate.getModel().getValueAt(roid, 0).toString();
		int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
		if(opt==JOptionPane.OK_OPTION){
		String query="delete from LIS_EMP_OVER_TIME_RATE where emp_grade='"+grd+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			prst.executeQuery();
			prst.close();
			refreshOverTimeRateTable();

		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e1);
		}
		}			
	}

	protected void overTimeRateAdd() {
		JTextField allownceName = new JTextField();
		JTextField rate = new JTextField();
		JComboBox comboBoxGrade = new JComboBox();
		comboBoxGrade.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B","C", "D","E", "F",}));
		comboBoxGrade.setBackground(Color.WHITE);
		//comboBoxgr.setBounds(109, 235, 109, 20);
		

		Object[] message = {
		    "Grade Scale", comboBoxGrade,
		    "Amount", rate
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Over Time Rate", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String query="insert into LIS_EMP_OVER_TIME_RATE values(?,?)";
			try{
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.setString(1,comboBoxGrade.getSelectedItem().toString() );
			prstatement.setDouble(2, Double.parseDouble(rate.getText()));
			prstatement.executeQuery();
			prstatement.close();
			//JOptionPane.showMessageDialog(null, "Suceesully Inserted");
			refreshOverTimeRateTable();
			
		}
			catch(Exception e){
				e.printStackTrace();
			}


		   
		}

		
	}

	private void refreshOverTimeRateTable() {
		String query="select * from LIS_EMP_OVER_TIME_RATE ";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			tableOverTimeRate.setModel(DbUtils.resultSetToTableModel(rs));
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
				
	}

	protected void deleteDeduction() {
		int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
		if(opt==JOptionPane.OK_OPTION){
		String query="delete from LIS_EMP_DEDUCTION where deduction_name='"+deductionName+"' and emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			prst.executeQuery();
			prst.close();
			refreshDeductTable();
			netPayment();

		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e1);
		}
		}		
	}

	protected void addDeductionItem() {
		JTextField txtDeductName = new JTextField();
		JTextField txtAmount = new JTextField();
		Object[] message = {
		    "Deduction Name", txtDeductName,
		    "Amount", txtAmount
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Deduction", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String query="insert into LIS_EMP_DEDUCTION values(?,?,?)";
			try{
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.setString(1,empId );
			prstatement.setString(2,txtDeductName.getText() );
			prstatement.setInt(3, Integer.parseInt(txtAmount.getText()));
			prstatement.executeQuery();
			prstatement.close();
			refreshDeductTable();
			netPayment();

		}
			catch(Exception e){
				e.printStackTrace();
			}

		}

		
	}

	protected void deleteAllownces() {
		int opt=JOptionPane.showConfirmDialog(null, "Do you want to Delete items?", "Worning", JOptionPane.OK_CANCEL_OPTION);
		if(opt==JOptionPane.OK_OPTION){
			String query="delete from LIS_EMP_ALLOWNCES where allownce_name='"+allwonceName+"' and emp_id='"+empId+"'";
			try {
				PreparedStatement prst=connection.prepareStatement(query);
				prst.executeQuery();
				prst.close();
				refreshAllowncesTable();
				netPayment();

				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
				
	}

	protected void allownceAdd() {
		JTextField allownceName = new JTextField();
		JTextField amount = new JTextField();
		Object[] message = {
		    "Allownce Name", allownceName,
		    "Amount", amount
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Allownces", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String query="insert into LIS_EMP_ALLOWNCES values(?,?,?,?)";
			try{
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.setString(1,empId );
			prstatement.setString(2,allownceName.getText() );
			prstatement.setInt(3, Integer.parseInt(amount.getText()));
			prstatement.setInt(4, Integer.parseInt(amount.getText()));
			prstatement.executeQuery();
			prstatement.close();
			//JOptionPane.showMessageDialog(null, "Suceesully Inserted");
			refreshAllowncesTable();
			netPayment();
			
		}
			catch(Exception e){
				e.printStackTrace();
			}


		   
		}

		
	}

	protected void refreshWorkingHour() {
		String dfrom=((JTextField)dateChooserFrom.getDateEditor().getUiComponent()).getText();
		String dto=((JTextField)dateChooserTo.getDateEditor().getUiComponent()).getText();

		String query="select round(sum(regular_time)) as basicHour,round(sum(over_time)) as OT from lis_sessions where employeeid='"+empId+"' and login_time between '"+dfrom+"' and '"+dto+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			while(rs.next()){
				textFieldTtlBasicHour.setText(rs.getString("basicHour"));
				textFieldOverTime.setText(rs.getString("OT"));
			}
			prst.close();
			rs.close();
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		String otRate=null;
		String query2="select info.grade_scale,rte.rate from lis_employee_info info join lis_emp_over_time_rate rte on info.grade_scale=rte.emp_grade where emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query2);
			ResultSet rs=prst.executeQuery();
			while(rs.next()){
				textFieldOtRateTK.setText(rs.getString("RATE"));
				otRate=rs.getString("RATE");
				
			}
			prst.close();
			rs.close();
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		double otrate=Double.parseDouble(otRate);
		String ot_wh=textFieldOverTime.getText();
		double _ot_wh=Double.parseDouble(ot_wh);
		double ot_amnt=_ot_wh*otrate;
		String _ot_amnt=String.valueOf(ot_amnt);
		textFieldOverTimeTK.setText(_ot_amnt); 
	}

	protected void updateBasicSalary(String increment) {

		String query="update lis_emp_allownces set new_amount=new_amount+'"+increment+"' where emp_id='"+empId+"' and allownce_name='"+"Salary"+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			prst.executeQuery();			
			prst.close();			
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	protected void refreshIssuedAssetsTable() {
	
		String query=" select * from LIS_EMP_ISSUED_ASSETS where emp_id='"+empId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		tableIssuedAssets.setModel(DbUtils.resultSetToTableModel(result));
		prstatement.close();
		result.close();	
		
		
	}
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	}

	protected void refreshExperiancesTable() {
		// TODO Auto-generated method stub
		String query=" select * from LIS_EMP_EXPERIANCES where emp_id='"+empId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		tableExperiances.setModel(DbUtils.resultSetToTableModel(result));
		prstatement.close();
		result.close();	
		
		
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void refreshEducationTable() {
		// TODO Auto-generated method stub
		String query=" select * from LIS_EMP_EDUCATIONS where emp_id='"+empId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		tableEducation.setModel(DbUtils.resultSetToTableModel(result));
		prstatement.close();
		result.close();	
		
		
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void refreshIncrementTable() {
		// TODO Auto-generated method stub
		String query=" select * from LIS_EMP_INCREMENT where emp_id='"+empId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		tableIncrement.setModel(DbUtils.resultSetToTableModel(result));
		prstatement.close();
		result.close();	
		
		
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void getBankDetails() {
		// TODO Auto-generated method stub
		String firstName=null,lastName=null;
		String query="select bd.emp_bank_account,bd.bank_name, emp.firstname,emp.lastname,emp.tin_no,adr.cur_village from LIS_EMP_BANK_DETAILS bd join LIS_EMPLOYEE_INFO emp on emp.emp_id=bd.emp_id join LIS_EMP_ADDRESS_DETAILS adr on emp.emp_id=adr.emp_id where emp.emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			//tableAttnDetails.setModel(DbUtils.resultSetToTableModel(rs));
			while(rs.next()){
				firstName=rs.getString("FIRSTNAME");				
				lastName=rs.getString("LASTNAME");
				textFieldEmpAddress.setText(rs.getString("CUR_VILLAGE"));
				textFieldAccounNo.setText(rs.getString("BANK_NAME"));
				textFieldBank.setText(rs.getString("EMP_BANK_ACCOUNT"));
				textFieldTinNo.setText(rs.getString("TIN_NO"));
				

			}
			String name=firstName+" "+lastName;
			textFieldEmpName.setText(name);
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}

	protected void netPayment() {
		// TODO Auto-generated method stub
		String query="select sum(new_amount) as salary_amount from LIS_EMP_ALLOWNCES where emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			//tableAttnDetails.setModel(DbUtils.resultSetToTableModel(rs));
			while(rs.next()){
				textFieldTotalPay.setText(rs.getString("salary_amount"));

			}
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		String queryDeduction="select sum(amount) as deduction_amount from LIS_EMP_DEDUCTION where emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(queryDeduction);
			ResultSet rs=prst.executeQuery();
			//tableAttnDetails.setModel(DbUtils.resultSetToTableModel(rs));
			while(rs.next()){
				textFieldTotalDeduction.setText(rs.getString("deduction_amount"));

			}
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		String tpay=textFieldTotalPay.getText();
		String tdeduct=textFieldTotalDeduction.getText();
		double _tpay=Double.parseDouble(tpay);
		double _tdeduct=Double.parseDouble(tdeduct);
		double netPayment=_tpay-_tdeduct;
		String disNetPayment=String.valueOf(netPayment);
		textFieldNetSalary.setText(disNetPayment);

		
	}

	protected void getAttnDetails() {
		// TODO Auto-generated method stub
		String dfrom=((JTextField)dateChooserFrom.getDateEditor().getUiComponent()).getText();
		String dto=((JTextField)dateChooserTo.getDateEditor().getUiComponent()).getText();

		String query="select count(remarks) as Leave from LIS_SESSIONS where employeeid='"+empId+"' and remarks='"+"Leave"+"' and login_time between '"+dfrom+"' and '"+dto+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			//tableAttnDetails.setModel(DbUtils.resultSetToTableModel(rs));
			while(rs.next()){
				textFieldEmpLeave.setText(rs.getString("Leave"));

			}
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		String query1="select count( remarks) as Late from LIS_SESSIONS where employeeid='"+empId+"' and remarks='"+"Late"+"' and login_time between '"+dfrom+"' and '"+dto+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query1);
			ResultSet rs=prst.executeQuery();
			//tableAttnDetails.setModel(DbUtils.resultSetToTableModel(rs));
			while(rs.next()){
				textFieldEmplate.setText(rs.getString("Late"));

			}
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		String query2="select count(distinct to_char(login_time,'dd-mm-yy'))as present from LIS_SESSIONS where employeeid='"+empId+"'  and login_time between '"+dfrom+"' and '"+dto+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query2);
			ResultSet rs=prst.executeQuery();
			//tableAttnDetails.setModel(DbUtils.resultSetToTableModel(rs));
			while(rs.next()){
				textFieldEmpPresents.setText(rs.getString("present"));

			}
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		
	}

	protected void refreshAllowncesTable() {
		// TODO Auto-generated method stub
		String query=" select * from LIS_EMP_ALLOWNCES where emp_id='"+empId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		//tableEmpAllownce.setModel(DbUtils.resultSetToTableModel(result));
		tablevarAllownces.setModel(DbUtils.resultSetToTableModel(result));
		prstatement.close();
		result.close();	
		
		
	}
		catch(Exception e){
			e.printStackTrace();
		}


		
	}

	protected void getDeductionData() {
		// TODO Auto-generated method stub
		refreshDeductTable();
		
	}

	private void refreshDeductTable() {
		// TODO Auto-generated method stub
		String query="select * from LIS_EMP_DEDUCTION where emp_id='"+empId+"' ";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			//tableEmpDeduction.setModel(DbUtils.resultSetToTableModel(rs));
			tableVariableDeduct.setModel(DbUtils.resultSetToTableModel(rs));
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
		
	}

	protected void getAllowncesData() {
		// TODO Auto-generated method stub
		refreshAllowncesTable();
		
	}
	private String empId;
	private JTextField textFieldNetSalary;
	private JTextField textFieldTotalDeduction;
	private JTextField textFieldTotalPay;
	private JTextField textFieldEmpPresents;
	private JTextField textFieldEmpLeave;
	private JTextField textFieldEmplate;
	private JTextField textFieldEmpName;
	private JTextField textFieldBank;
	private JTextField textFieldAccounNo;
	private JTable tableEducation;
	private JTable tableExperiances;
	private JTable tableIssuedAssets;
	private JTextField textFieldTtlBasicHour;
	private JTextField textFieldOverTime;
	private JTextField textFieldOtRateTK;
	private JTextField textFieldOverTimeTK;
	private JTable tablevarAllownces;
	private JTable tableVariableDeduct;
	private JTable tableOverTimeRate;
	private JTextField textFieldEmpAddress;
	private JTextField textFieldTinNo;
	protected void getDataFromEmpTable() {
		// TODO Auto-generated method stub
		int roid=tableEmployeeList.getSelectedRow();
		empId=tableEmployeeList.getModel().getValueAt(roid,0).toString();
		String query=" select * from lis_employee_info where emp_id='"+empId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		//Date date;
		while(result.next()){
			textFieldEmpId.setText(result.getString("EMP_ID"));
			//textField.setText(result.getString("FIRSTNAME"));
			
			//textFieldLastName.setText(result.getString("LASTNAME"));
			//date=result.getDate("BIRTHDATE");
			//dateOfBirth.setDate(date);
			/*dateOfBirth.setDate(result.getDate("BIRTHDATE"));
			dateVisaExpire.setDate(result.getDate("VISAEXPIREDATE"));
			image_data=result.getBytes("IMAGE");
			lblEmployeePicture.setIcon(new ImageIcon(image_data));
			textFieldMobile.setText(result.getString("MOBILE"));
			textFieldEmail.setText(result.getString("EMAIL"));
			textFieldHouseNo.setText(result.getString("HOUSENO"));
			textFieldRaodName.setText(result.getString("RAODNAME"));
			textFieldCounty.setText(result.getString("COUNTY"));
			textFieldCity.setText(result.getString("CITY"));
			textFieldFathersName.setText(result.getString("FATHERSNAME"));
			textFieldMothersName.setText(result.getString("MOTHERSNAME"));
			textFieldOverseaseHouse.setText(result.getString("OVERHOUSENO"));
			textFieldOverseasRoadName.setText(result.getString("OVERROADNAME"));
			textFieldOverseasCity.setText(result.getString("OVERCITY"));
			textFieldOverseasCountry.setText(result.getString("OVERCOUNTRY"));*/
			
			
		}
		result.close();
		
	}
		catch(Exception e){
			e.printStackTrace();
		}

		
	}

	private void refreshEmployeelistTable() {
		// TODO Auto-generated method stub
		String query="select EMP_ID,firstname,dept_id from lis_employee_info";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			tableEmployeeList.setModel(DbUtils.resultSetToTableModel(rs));
			prst.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
