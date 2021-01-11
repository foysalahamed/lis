import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JToggleButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	JDateChooser dateChooserBirthdate;
	JDateChooser dateChooserJoiningDate;
	JDateChooser dateChooserEndingDate;
	JComboBox comboBoxMeritalStatus;
	JComboBox comboBoxReligions;
	JComboBox comboBoxGradeScale;
	JComboBox comboBoxDeptID;
	ButtonGroup group;
	ButtonGroup grpActiveStatus;
	JRadioButton radioButtonFemale;
	JRadioButton radioButtonMale;
	JRadioButton rdbtnActive;
	JRadioButton rdbtnInactive;
	String jobStatus;
	JLabel labelEmpPiture;
	JTextArea textAreaRefAddress;
	private JTextField textFieldEmpID;
	private JTextField textFieldLastNme;
	private JTextField textFieldMobile;
	private JTextField textFieldEmail;
	private JTextField textFieldFirstName;
	private JTextField textFieldEmployeePicturesPath;
	private JTextField textFieldParVillage;
	private JTextField textFieldParRoad;
	private JTextField textFieldParPostOffice;
	private JTextField textFieldParPoliceStation;
	private JTextField textFieldParDistrict;
	private JTextField textFieldCountry;
	private JTextField textFieldCurVillage;
	private JTextField textFieldCurRoad;
	private JTextField textFieldCurPostOffice;
	private JTextField textFieldCurPoliceStation;
	private JTextField textFieldCurDistrict;
	private JTextField textFieldFathersName;
	private JTextField textFieldMothersName;
	private Connection connection;
	private String gander;
	private byte[] person_image=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
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
	public EmployeeInfo() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		
		setBounds(100, 100, 1096, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 1, 1249, 703);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		contentPane.add(panel);
		
		JLabel label = new JLabel("Employees Information");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label.setBounds(222, 11, 264, 25);
		panel.add(label);
		group=new ButtonGroup();
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String empId=null;
				empId=JOptionPane.showInputDialog("Employee ID");
				//System.out.print(st);
				searchEmployee(empId);
			}
		});
		btnSearch.setForeground(new Color(147, 112, 219));
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.setBounds(71, 175, 120, 25);
		panel.add(btnSearch);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(395, 56, 271, 132);
		panel.add(panel_4);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		desktopPane.setBounds(57, 11, 181, 110);
		panel_4.add(desktopPane);
		
		labelEmpPiture = new JLabel("");
		labelEmpPiture.setBounds(10, 0, 157, 110);
		desktopPane.add(labelEmpPiture);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(64, 203, 630, 487);
		panel.add(tabbedPane);
		
		JPanel panelBasic = new JPanel();
		panelBasic.setBackground(Color.WHITE);
		tabbedPane.addTab("Basic Details", null, panelBasic, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 228, 225));
		panelBasic.setLayout(null);
		
		JLabel label_12 = new JLabel("Personal Details");
		label_12.setForeground(new Color(255, 228, 181));
		label_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_12.setBounds(38, 29, 125, 14);
		panelBasic.add(label_12);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(34, 47, 514, 316);
		panelBasic.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.WHITE));
		panel_2.setBackground(Color.WHITE);
		
		JLabel label_6 = new JLabel("Date of Birth");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_6.setBounds(245, 38, 77, 14);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("Sex");
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_7.setBounds(247, 195, 33, 14);
		panel_2.add(label_7);
		
		textFieldEmpID = new JTextField();
		textFieldEmpID.setColumns(10);
		textFieldEmpID.setBounds(110, 35, 120, 20);
		panel_2.add(textFieldEmpID);
		
		textFieldLastNme = new JTextField();
		textFieldLastNme.setColumns(10);
		textFieldLastNme.setBounds(111, 78, 120, 20);
		panel_2.add(textFieldLastNme);
		
		JLabel label_8 = new JLabel("Last Name");
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_8.setBounds(14, 81, 77, 14);
		panel_2.add(label_8);
		
		JLabel label_9 = new JLabel("Employee ID");
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_9.setBounds(16, 38, 77, 14);
		panel_2.add(label_9);
		
		radioButtonFemale = new JRadioButton("Female");
		radioButtonFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gander="Female";

			}
		});
		radioButtonFemale.setBackground(Color.WHITE);
		radioButtonFemale.setBounds(349, 193, 77, 18);
		panel_2.add(radioButtonFemale);
		
		radioButtonMale = new JRadioButton("Male");
		radioButtonMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gander="Male";
			}
		});
		radioButtonMale.setBackground(Color.WHITE);
		radioButtonMale.setBounds(290, 193, 59, 18);
		panel_2.add(radioButtonMale);
		group.add(radioButtonMale);
		group.add(radioButtonFemale);
		
		JLabel lblReligions = new JLabel("Religions");
		lblReligions.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblReligions.setBounds(14, 129, 77, 14);
		panel_2.add(lblReligions);
		
		JLabel lblMeritalStatus = new JLabel("Merital Status");
		lblMeritalStatus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblMeritalStatus.setBounds(14, 151, 87, 14);
		panel_2.add(lblMeritalStatus);
		
		comboBoxMeritalStatus = new JComboBox();
		comboBoxMeritalStatus.setModel(new DefaultComboBoxModel(new String[] {"", "Single", "Marid", "Others"}));
		comboBoxMeritalStatus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxMeritalStatus.setBackground(Color.WHITE);
		comboBoxMeritalStatus.setBounds(111, 148, 120, 20);
		panel_2.add(comboBoxMeritalStatus);
		
		 comboBoxReligions = new JComboBox();
		 comboBoxReligions.setModel(new DefaultComboBoxModel(new String[] {"", "Muslim", "Hindu", "Christian", "Bodist"}));
		 comboBoxReligions.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		 comboBoxReligions.setBackground(Color.WHITE);
		 comboBoxReligions.setBounds(111, 126, 120, 20);
		 panel_2.add(comboBoxReligions);
		 
		  dateChooserBirthdate = new JDateChooser();
		  dateChooserBirthdate.setBounds(338, 37, 119, 20);
		  panel_2.add(dateChooserBirthdate);
		  
		  textFieldMobile = new JTextField();
		  textFieldMobile.setColumns(10);
		  textFieldMobile.setBounds(338, 60, 120, 20);
		  panel_2.add(textFieldMobile);
		  
		  JLabel label_14 = new JLabel("Mobile No");
		  label_14.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  label_14.setBounds(245, 63, 77, 14);
		  panel_2.add(label_14);
		  
		  JLabel label_15 = new JLabel("Email");
		  label_15.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  label_15.setBounds(245, 109, 77, 14);
		  panel_2.add(label_15);
		  
		  textFieldEmail = new JTextField();
		  textFieldEmail.setColumns(10);
		  textFieldEmail.setBounds(338, 106, 120, 20);
		  panel_2.add(textFieldEmail);
		  
		  JLabel label_16 = new JLabel("First Name");
		  label_16.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  label_16.setBounds(16, 61, 77, 14);
		  panel_2.add(label_16);
		  
		  textFieldFirstName = new JTextField();
		  textFieldFirstName.setColumns(10);
		  textFieldFirstName.setBounds(110, 57, 120, 20);
		  panel_2.add(textFieldFirstName);
		  
		  textFieldEmployeePicturesPath = new JTextField();
		  textFieldEmployeePicturesPath.setColumns(10);
		  textFieldEmployeePicturesPath.setBounds(110, 255, 239, 20);
		  panel_2.add(textFieldEmployeePicturesPath);
		  
		  JButton buttonEmployeePictures = new JButton("Image");
		  buttonEmployeePictures.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		attachPicture();
		  	}
		  });
		  
		  
		  
		  
		  
		  buttonEmployeePictures.setBounds(369, 253, 89, 23);
		  panel_2.add(buttonEmployeePictures);
		  
		  JLabel label_17 = new JLabel("Picture");
		  label_17.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  label_17.setBounds(14, 258, 77, 14);
		  panel_2.add(label_17);
		  
		  textFieldFathersName = new JTextField();
		  textFieldFathersName.setColumns(10);
		  textFieldFathersName.setBounds(110, 103, 120, 20);
		  panel_2.add(textFieldFathersName);
		  
		  JLabel lblFathersName = new JLabel("Fathers Name");
		  lblFathersName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  lblFathersName.setBounds(12, 106, 77, 14);
		  panel_2.add(lblFathersName);
		  
		  textFieldMothersName = new JTextField();
		  textFieldMothersName.setColumns(10);
		  textFieldMothersName.setBounds(339, 81, 120, 20);
		  panel_2.add(textFieldMothersName);
		  
		  JLabel lblMothersName = new JLabel("Mothers Name");
		  lblMothersName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  lblMothersName.setBounds(242, 84, 90, 14);
		  panel_2.add(lblMothersName);
		  
		  JLabel lblJoiningDate = new JLabel("Joining Date");
		  lblJoiningDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  lblJoiningDate.setBounds(245, 130, 77, 14);
		  panel_2.add(lblJoiningDate);
		  
		   dateChooserJoiningDate = new JDateChooser();
		   dateChooserJoiningDate.setBounds(338, 129, 119, 20);
		   panel_2.add(dateChooserJoiningDate);
		   
		   JLabel lblEndingDate = new JLabel("Ending Date");
		   lblEndingDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		   lblEndingDate.setBounds(246, 153, 77, 14);
		   panel_2.add(lblEndingDate);
		   
		   dateChooserEndingDate = new JDateChooser();
		   dateChooserEndingDate.setBounds(339, 152, 119, 20);
		   panel_2.add(dateChooserEndingDate);
		   
		   JLabel lblDepartmentid = new JLabel("Department");
		   lblDepartmentid.setFont(new Font("Times New Roman", Font.BOLD, 12));
		   lblDepartmentid.setBounds(14, 173, 87, 14);
		   panel_2.add(lblDepartmentid);
		   
		    comboBoxDeptID = new JComboBox();
		    comboBoxDeptID.setModel(new DefaultComboBoxModel(new String[] {"Select", "Teacher", "Staff"}));
		    comboBoxDeptID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    comboBoxDeptID.setBackground(Color.WHITE);
		    comboBoxDeptID.setBounds(111, 170, 120, 20);
		    panel_2.add(comboBoxDeptID);
		    
		    JLabel lblStatus = new JLabel("Status");
		    lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    lblStatus.setBounds(16, 229, 75, 14);
		    panel_2.add(lblStatus);
		    
		     rdbtnActive = new JRadioButton("Active");
		     rdbtnActive.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent arg0) {
		     		jobStatus="active";
		     		
		     	}
		     });
		    rdbtnActive.setBackground(Color.WHITE);
		    rdbtnActive.setBounds(109, 227, 83, 18);
		    panel_2.add(rdbtnActive);
		    
		     rdbtnInactive = new JRadioButton("Inactive");
		     rdbtnInactive.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		jobStatus="inactive";
		     	}
		     });
		    rdbtnInactive.setBackground(Color.WHITE);
		    rdbtnInactive.setBounds(206, 227, 103, 18);
		    panel_2.add(rdbtnInactive);
		    grpActiveStatus= new ButtonGroup();
		    grpActiveStatus.add(rdbtnActive);
		    grpActiveStatus.add(rdbtnInactive);
		    
		    JLabel lblGradeScale = new JLabel("Grade Scale");
		    lblGradeScale.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    lblGradeScale.setBounds(15, 194, 87, 14);
		    panel_2.add(lblGradeScale);
		    
		     comboBoxGradeScale = new JComboBox();
		    comboBoxGradeScale.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B", "C", "D", "E", "F"}));
		    comboBoxGradeScale.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    comboBoxGradeScale.setBackground(Color.WHITE);
		    comboBoxGradeScale.setBounds(112, 191, 120, 20);
		    panel_2.add(comboBoxGradeScale);
		    
		    JLabel lblTin = new JLabel("TIN #");
		    lblTin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    lblTin.setBounds(242, 176, 77, 14);
		    panel_2.add(lblTin);
		    
		    textFieldTinNo = new JTextField();
		    textFieldTinNo.setColumns(10);
		    textFieldTinNo.setBounds(340, 173, 120, 20);
		    panel_2.add(textFieldTinNo);
		    
		    JButton button_1 = new JButton("Save");
		    button_1.setBounds(43, 374, 120, 25);
		    panelBasic.add(button_1);
		    button_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(checkValidity()){
		    			if(emailCheckValidity()){
		    				inserData();
		    			}
		    		}
		    	}
		    });
		    
		    button_1.setForeground(new Color(0, 100, 0));
		    button_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		    
		    JButton button_2 = new JButton("Delete");
		    button_2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    	}
		    });
		    button_2.setBounds(282, 374, 120, 25);
		    panelBasic.add(button_2);
		    button_2.setForeground(Color.RED);
		    button_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		    
		    JButton btnEdit = new JButton("Edit");
		    btnEdit.setBounds(162, 374, 120, 25);
		    panelBasic.add(btnEdit);
		    btnEdit.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		update_info();
		    	}

		    	private void update_info() {
		    		// TODO Auto-generated method stub
		    		String bDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserBirthdate.getDateEditor().getUiComponent()).getText());
		    		String jDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserJoiningDate.getDateEditor().getUiComponent()).getText());
		    		String eDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserEndingDate.getDateEditor().getUiComponent()).getText());
		    		
		    		 String query="update lis_employee_info set firstName='"+textFieldFirstName.getText()+"',"
		    		 		+ "lastName='"+textFieldLastNme.getText()+"',fathersname='"+textFieldFathersName.getText()+"',"
		    		 				+ "mothersname='"+textFieldMothersName.getText()+"',"
		    		 						+ "birthdate='"+bDate+"',"
		    		 								+ "mobile='"+textFieldMobile.getText()+"',religion='"+(String)comboBoxReligions.getSelectedItem()+"',"
		    		 										+ "merital='"+(String)comboBoxMeritalStatus.getSelectedItem()+"',email='"+textFieldEmail.getText()+"',"
		    		 												+ "sex='"+gander+"',joining_date='"+jDate+"',"
		    		 														+ "ending_date='"+eDate+"',"
		    		 																+ "dept_id='"+comboBoxDeptID.getSelectedItem().toString()+"',job_status='"+jobStatus+"'"
		    		 																		+ " where emp_id='"+textFieldEmpID.getText()+"'";
		    			try {
		    				PreparedStatement prstatement=connection.prepareStatement(query);
		    				prstatement.executeQuery();
		    				prstatement.close();
		    				clearAllTextFieldPersonalDetails();
		    				
		    			} catch (SQLException e) {
		    				// TODO Auto-generated catch block
		    				//JOptionPane.showMessageDialog(null, e);
		    				e.printStackTrace();
		    			}
		    			
		    			
		    	 
		    			
		    		
		    	}
		    });
		    btnEdit.setForeground(Color.BLUE);
		    btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		    
		    JLabel label_10 = new JLabel("");
		    label_10.setBounds(34, 26, 581, 21);
		    panelBasic.add(label_10);
		    label_10.setForeground(Color.BLUE);
		    label_10.setFont(new Font("Times New Roman", Font.BOLD, 16));
		    Image lblimgprd=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		    label_10.setIcon(new ImageIcon(lblimgprd));
		    
		    JLabel label_5 = new JLabel("Personal Details");
		    label_5.setBounds(38, 33, 125, 14);
		    panelBasic.add(label_5);
		    
		    JButton btnClear_1 = new JButton("Cancel");
		    btnClear_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		clearAllTextFieldPersonalDetails();
		    	}
		    });
		    btnClear_1.setForeground(Color.RED);
		    btnClear_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		    btnClear_1.setBounds(401, 374, 120, 25);
		    panelBasic.add(btnClear_1);
			
		
		JPanel panelContactInfo = new JPanel();
		panelContactInfo.setBackground(Color.WHITE);
		tabbedPane.addTab("Contact Info", null, panelContactInfo, null);
		tabbedPane.setBackgroundAt(1, new Color(240, 230, 140));
		panelContactInfo.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 52, 514, 94);
		panelContactInfo.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setBackground(Color.WHITE);
		
		textFieldCurVillage = new JTextField();
		textFieldCurVillage.setColumns(10);
		textFieldCurVillage.setBounds(111, 46, 120, 20);
		panel_1.add(textFieldCurVillage);
		
		textFieldCurRoad = new JTextField();
		textFieldCurRoad.setColumns(10);
		textFieldCurRoad.setBounds(111, 68, 120, 20);
		panel_1.add(textFieldCurRoad);
		
		JLabel label_1 = new JLabel("Village");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_1.setBounds(15, 49, 77, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Road Name");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_2.setBounds(15, 71, 77, 14);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Police Stations");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_3.setBounds(250, 51, 87, 14);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Post Office");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_4.setBounds(250, 29, 77, 14);
		panel_1.add(label_4);
		
		textFieldCurPostOffice = new JTextField();
		textFieldCurPostOffice.setColumns(10);
		textFieldCurPostOffice.setBounds(347, 26, 120, 20);
		panel_1.add(textFieldCurPostOffice);
		
		textFieldCurPoliceStation = new JTextField();
		textFieldCurPoliceStation.setColumns(10);
		textFieldCurPoliceStation.setBounds(347, 48, 120, 20);
		panel_1.add(textFieldCurPoliceStation);
		
		textFieldCurDistrict = new JTextField();
		textFieldCurDistrict.setColumns(10);
		textFieldCurDistrict.setBounds(347, 70, 120, 20);
		panel_1.add(textFieldCurDistrict);
		
		JLabel label_11 = new JLabel("District");
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_11.setBounds(249, 73, 77, 14);
		panel_1.add(label_11);
		
		textFieldEmpIdAdrs = new JTextField();
		textFieldEmpIdAdrs.setEditable(false);
		textFieldEmpIdAdrs.setColumns(10);
		textFieldEmpIdAdrs.setBounds(111, 24, 120, 20);
		panel_1.add(textFieldEmpIdAdrs);
		
		JLabel lblEmpId_1 = new JLabel("Emp ID");
		lblEmpId_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmpId_1.setBounds(15, 27, 77, 14);
		panel_1.add(lblEmpId_1);
		
		JLabel lblNewLabel_1 = new JLabel("Present Address");
		lblNewLabel_1.setForeground(new Color(255, 228, 181));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 35, 134, 14);
		panelContactInfo.add(lblNewLabel_1);
		
		JLabel lblPresentAddress = new JLabel("");
		lblPresentAddress.setBounds(10, 33, 605, 19);
		panelContactInfo.add(lblPresentAddress);
		lblPresentAddress.setForeground(Color.BLUE);
		lblPresentAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Image lblimg=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		lblPresentAddress.setIcon(new ImageIcon(lblimg));
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 168, 514, 88);
		panelContactInfo.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.WHITE));
		panel_3.setBackground(Color.WHITE);
		
		textFieldParVillage = new JTextField();
		textFieldParVillage.setColumns(10);
		textFieldParVillage.setBounds(111, 17, 120, 20);
		panel_3.add(textFieldParVillage);
		
		textFieldParRoad = new JTextField();
		textFieldParRoad.setColumns(10);
		textFieldParRoad.setBounds(111, 39, 120, 20);
		panel_3.add(textFieldParRoad);
		
		JLabel label_18 = new JLabel("Village");
		label_18.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_18.setBounds(14, 20, 77, 14);
		panel_3.add(label_18);
		
		JLabel label_19 = new JLabel("Road Name");
		label_19.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_19.setBounds(15, 42, 77, 14);
		panel_3.add(label_19);
		
		JLabel label_20 = new JLabel("Police Stations");
		label_20.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_20.setBounds(250, 39, 87, 14);
		panel_3.add(label_20);
		
		JLabel label_21 = new JLabel("Post Office");
		label_21.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_21.setBounds(250, 17, 77, 14);
		panel_3.add(label_21);
		
		textFieldParPostOffice = new JTextField();
		textFieldParPostOffice.setColumns(10);
		textFieldParPostOffice.setBounds(347, 14, 120, 20);
		panel_3.add(textFieldParPostOffice);
		
		textFieldParPoliceStation = new JTextField();
		textFieldParPoliceStation.setColumns(10);
		textFieldParPoliceStation.setBounds(347, 36, 120, 20);
		panel_3.add(textFieldParPoliceStation);
		
		textFieldParDistrict = new JTextField();
		textFieldParDistrict.setColumns(10);
		textFieldParDistrict.setBounds(111, 61, 120, 20);
		panel_3.add(textFieldParDistrict);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(347, 58, 120, 20);
		panel_3.add(textFieldCountry);
		
		JLabel label_22 = new JLabel("Country");
		label_22.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_22.setBounds(250, 61, 85, 14);
		panel_3.add(label_22);
		
		JLabel label_23 = new JLabel("District");
		label_23.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_23.setBounds(13, 64, 77, 14);
		panel_3.add(label_23);
		
		JLabel lblNewLabel_2 = new JLabel("Parmanent Address");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setForeground(new Color(255, 228, 181));
		lblNewLabel_2.setBounds(15, 152, 163, 14);
		panelContactInfo.add(lblNewLabel_2);
		
		JLabel lblParmanentAddress = new JLabel("");
		lblParmanentAddress.setBounds(13, 151, 602, 19);
		panelContactInfo.add(lblParmanentAddress);
		lblParmanentAddress.setForeground(Color.BLUE);
		lblParmanentAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Image lblimgprmad=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		lblParmanentAddress.setIcon(new ImageIcon(lblimgprmad));
		
		JButton button = new JButton("Edit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query2="update lis_emp_address_details set cur_road_name='"+textFieldCurRoad.getText()+"',cur_village='"+textFieldCurVillage.getText()+"',cur_post_office='"+textFieldCurPostOffice.getText()+"',cur_police_station='"+textFieldCurPoliceStation.getText()+"',cur_district='"+textFieldCurDistrict.getText()+"',par_road_name='"+textFieldParRoad.getText()+"',par_village='"+textFieldParVillage.getText()+"',par_post_office='"+textFieldParPostOffice.getText()+"',par_police_station='"+textFieldParPoliceStation.getText()+"',par_district='"+textFieldParDistrict.getText()+"',country='"+textFieldCountry.getText()+"' where emp_id='"+textFieldEmpIdAdrs.getText()+"'";
    			try {
    				PreparedStatement prstatement=connection.prepareStatement(query2);
    				prstatement.executeQuery(query2);
    				
    				prstatement.close();
    			} catch (SQLException ee) {
    				// TODO Auto-generated catch block
    				JOptionPane.showMessageDialog(null, ee);
    			}
    			
    			String query_ref="update lis_emp_references set ref_name='"+textFieldRefName.getText()+"',ref_mobile='"+textFieldRefMobile.getText()+"',ref_email='"+textFieldRefEmail.getText()+"',ref_relations='"+textFieldRefRelation.getText()+"',ref_address='"+textAreaRefAddress.getText()+"' where emp_id='"+textFieldEmpIdAdrs.getText()+"'";
    			try {
    				PreparedStatement prstatement=connection.prepareStatement(query_ref);
    				prstatement.executeQuery(query_ref);
    				JOptionPane.showMessageDialog(null, "Successfull updated");
    				clearAllTextFieldAddress();
    				
    				prstatement.close();
    			} catch (SQLException ee) {
    				// TODO Auto-generated catch block
    				JOptionPane.showMessageDialog(null, ee);
    			}
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button.setBounds(244, 408, 120, 25);
		panelContactInfo.add(button);
		
		JButton button_3 = new JButton("Save");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertEmpAddressDetails();

			}
		});
		button_3.setForeground(new Color(0, 100, 0));
		button_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_3.setBounds(125, 408, 120, 25);
		panelContactInfo.add(button_3);
		
		JLabel lblReferances = new JLabel("References");
		lblReferances.setForeground(new Color(255, 228, 181));
		lblReferances.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblReferances.setBounds(16, 258, 163, 14);
		panelContactInfo.add(lblReferances);
		
		JLabel labelRef = new JLabel("");
		labelRef.setForeground(Color.BLUE);
		labelRef.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelRef.setBounds(14, 257, 602, 19);
		panelContactInfo.add(labelRef);
		Image imre=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		labelRef.setIcon(new ImageIcon(imre));
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(Color.WHITE));
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(11, 274, 514, 126);
		panelContactInfo.add(panel_6);
		
		textFieldRefName = new JTextField();
		textFieldRefName.setColumns(10);
		textFieldRefName.setBounds(111, 17, 120, 20);
		panel_6.add(textFieldRefName);
		
		textFieldRefEmail = new JTextField();
		textFieldRefEmail.setColumns(10);
		textFieldRefEmail.setBounds(111, 39, 120, 20);
		panel_6.add(textFieldRefEmail);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblName.setBounds(14, 20, 77, 14);
		panel_6.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmail.setBounds(15, 42, 77, 14);
		panel_6.add(lblEmail);
		
		JLabel lblReletions = new JLabel("Relation");
		lblReletions.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblReletions.setBounds(250, 39, 87, 14);
		panel_6.add(lblReletions);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblMobile.setBounds(250, 17, 77, 14);
		panel_6.add(lblMobile);
		
		textFieldRefMobile = new JTextField();
		textFieldRefMobile.setColumns(10);
		textFieldRefMobile.setBounds(347, 14, 120, 20);
		panel_6.add(textFieldRefMobile);
		
		textFieldRefRelation = new JTextField();
		textFieldRefRelation.setColumns(10);
		textFieldRefRelation.setBounds(347, 36, 120, 20);
		panel_6.add(textFieldRefRelation);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAddress_1.setBounds(13, 64, 77, 14);
		panel_6.add(lblAddress_1);
		
		 textAreaRefAddress = new JTextArea();
		textAreaRefAddress.setBackground(new Color(255, 250, 205));
		textAreaRefAddress.setBounds(111, 61, 356, 56);
		panel_6.add(textAreaRefAddress);
		
		JButton btnClear = new JButton("Cancel");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllTextFieldAddress();
			}
		});
		btnClear.setForeground(Color.RED);
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setBounds(361, 408, 120, 25);
		panelContactInfo.add(btnClear);
		
		JPanel panelBankDetails = new JPanel();
		panelBankDetails.setBackground(Color.WHITE);
		tabbedPane.addTab("Bank Details", null, panelBankDetails, null);
		panelBankDetails.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(Color.WHITE));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(27, 107, 514, 107);
		panelBankDetails.add(panel_5);
		
		textFieldBankName = new JTextField();
		textFieldBankName.setColumns(10);
		textFieldBankName.setBounds(111, 31, 140, 20);
		panel_5.add(textFieldBankName);
		
		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setColumns(10);
		textFieldAccountNo.setBounds(111, 51, 140, 20);
		panel_5.add(textFieldAccountNo);
		
		JLabel lblBandName = new JLabel("Bank Name");
		lblBandName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblBandName.setBounds(14, 34, 77, 14);
		panel_5.add(lblBandName);
		
		JLabel lblAccountNo = new JLabel("Account No");
		lblAccountNo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAccountNo.setBounds(15, 54, 77, 14);
		panel_5.add(lblAccountNo);
		
		JLabel lblAddress = new JLabel("Branch");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAddress.setBounds(14, 73, 77, 14);
		panel_5.add(lblAddress);
		
		textFieldBranch = new JTextField();
		textFieldBranch.setColumns(10);
		textFieldBranch.setBounds(111, 70, 140, 20);
		panel_5.add(textFieldBranch);
		
		JLabel lblEmpId = new JLabel("Emp ID");
		lblEmpId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmpId.setBounds(14, 14, 77, 14);
		panel_5.add(lblEmpId);
		
		textFieldEmpIDBnk = new JTextField();
		textFieldEmpIDBnk.setEditable(false);
		textFieldEmpIDBnk.setColumns(10);
		textFieldEmpIDBnk.setBounds(111, 11, 140, 20);
		panel_5.add(textFieldEmpIDBnk);
		
		JLabel lblNewLabel = new JLabel("Bank Details");
		lblNewLabel.setForeground(new Color(255, 228, 181));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(27, 90, 99, 14);
		panelBankDetails.add(lblNewLabel);
		
		JLabel lblBankDetails = new JLabel("");
		lblBankDetails.setForeground(Color.BLUE);
		lblBankDetails.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBankDetails.setBounds(27, 90, 588, 19);
		panelBankDetails.add(lblBankDetails);
		Image lblimgprad=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		lblBankDetails.setIcon(new ImageIcon(lblimgprad));
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empBankDetails();
			}
		});
		btnSave.setBounds(86, 214, 71, 23);
		panelBankDetails.add(btnSave);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="update LIS_EMP_BANK_DETAILS set bank_name='"+textFieldBankName.getText()+"',emp_bank_account='"+textFieldAccountNo.getText()+"',branch='"+textFieldBranch.getText()+"' where emp_id='"+textFieldEmpIDBnk.getText()+"'";
    			try {
    				PreparedStatement prstatement=connection.prepareStatement(query);
    				prstatement.executeQuery(query);
    				JOptionPane.showMessageDialog(null, "Successfull updated");
    				clearAllBankDetails();
    				
    				prstatement.close();
    			} catch (SQLException ee) {
    				// TODO Auto-generated catch block
    				JOptionPane.showMessageDialog(null, ee);
    			}
			}
		});
		btnEdit_1.setBounds(156, 214, 71, 23);
		panelBankDetails.add(btnEdit_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllBankDetails();
			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(226, 214, 83, 23);
		panelBankDetails.add(btnCancel);
		tabbedPane.setBackgroundAt(2, new Color(255, 218, 185));
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBackground(Color.WHITE);
		tabbedPane.addTab("Password Settings", null, panelPassword, null);
		panelPassword.setLayout(null);
		
		JButton button_6 = new JButton("Set Password");
		button_6.setBounds(103, 82, 133, 25);
		panelPassword.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetPassword setpass=new SetPassword();
				setpass.setVisible(true);
			}
		});
		button_6.setForeground(Color.BLUE);
		button_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JButton button_7 = new JButton("Attendances");
		button_7.setBounds(103, 109, 133, 25);
		panelPassword.add(button_7);
		button_7.setForeground(Color.BLUE);
		button_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
	}
	protected void empBankDetails() {
		// TODO Auto-generated method stub
		String query="Insert into LIS_EMP_BANK_DETAILS values(?,?,?,?)";
		
		//connection=DbConnection.dbconnection();
		
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
		prStatement.setString(1,textFieldEmpIDBnk.getText() );
		prStatement.setString(2,textFieldBankName.getText() );
		prStatement.setString(3,textFieldAccountNo.getText() );
		prStatement.setString(4,textFieldBranch.getText() );
		
		prStatement.executeQuery();
		JOptionPane.showMessageDialog(null, "Successfully Inserted.");
		clearAllBankDetails();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		
		
		
		
	}
	private void clearAllBankDetails() {
		// TODO Auto-generated method stub
		textFieldEmpIDBnk.setText("");
		textFieldBankName.setText("");
		textFieldAccountNo.setText("");
		textFieldBranch.setText("");
		
		
		
	}
	byte[] image_data=new byte[1024];
	protected void searchEmployee(String empId) {
		
		/*String query="select info.emp_id ,info.firstname,info.lastname,info.fathersname,info.mothersname," +
				"info.birthdate,info.mobile,info.religion,info.merital,info.email,info.sex,info.image,info.joining_date," +
				"info.ending_date,info.job_status,adrs.cur_road_name,adrs.cur_village,adrs.cur_post_office,adrs.cur_police_station," +
				"adrs.cur_district,adrs.par_road_name,adrs.par_village,adrs.par_post_office,adrs.par_police_station," +
				"adrs.par_district,adrs.country,ebnk.bank_name,ebnk.emp_bank_account,ebnk.branch" +
				" from lis_employee_info info join lis_emp_address_details adrs on info.emp_id=adrs.emp_id " +
				"join LIS_EMP_BANK_DETAILS ebnk on info.emp_id=ebnk.emp_id where info.emp_id='"+empId+"' ";*/
		
		/*String query="select info.emp_id ,info.firstname,info.lastname,info.fathersname,info.mothersname," +
				"info.birthdate,info.mobile,info.religion,info.merital,info.email,info.sex,info.image,info.joining_date," +
				"info.ending_date,info.dept_id,info.tin_no,info.grade_scale,info.job_status,adrs.cur_road_name,adrs.cur_village,adrs.cur_post_office,adrs.cur_police_station," +
				"adrs.cur_district,adrs.par_road_name,adrs.par_village,adrs.par_post_office,adrs.par_police_station," +
				"adrs.par_district,adrs.country,ebnk.bank_name,ebnk.emp_bank_account,ebnk.branch," +
				"ref.ref_name,ref.ref_mobile,ref.ref_email,ref.ref_relations,ref.ref_address"+
				" from lis_employee_info info join lis_emp_address_details adrs on info.emp_id=adrs.emp_id " +
				"join LIS_EMP_BANK_DETAILS ebnk on info.emp_id=ebnk.emp_id " +
				"join LIS_EMP_REFERENCES ref on ref.emp_id=info.emp_id " +
				"where info.emp_id='"+empId+"' ";*/
		String query="select * from lis_employee_info where emp_id='"+empId+"'";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		//Date date;
		while(result.next()){
			textFieldEmpID.setText(result.getString("EMP_ID"));
			textFieldFirstName.setText(result.getString("FIRSTNAME"));
			
			textFieldLastNme.setText(result.getString("LASTNAME"));
			textFieldFathersName.setText(result.getString("FATHERSNAME"));
			textFieldMothersName.setText(result.getString("MOTHERSNAME"));
			dateChooserBirthdate.setDate(result.getDate("BIRTHDATE"));
			dateChooserJoiningDate.setDate(result.getDate("JOINING_DATE"));
			dateChooserEndingDate.setDate(result.getDate("ENDING_DATE"));

			textFieldMobile.setText(result.getString("MOBILE"));
			comboBoxReligions.setSelectedItem(result.getString("RELIGION"));
			comboBoxMeritalStatus.setSelectedItem(result.getString("MERITAL"));
			comboBoxDeptID.setSelectedItem(result.getString("DEPT_ID"));
			if(result.getString("SEX").equals("Male")){
				radioButtonMale.setSelected(true);
				radioButtonFemale.setSelected(false);
			}
			else {
				radioButtonMale.setSelected(false);
				radioButtonFemale.setSelected(true);
			}
			if(result.getString("JOB_STATUS").equals("active")){
				rdbtnActive.setSelected(true);
				rdbtnInactive.setSelected(false);
			}
			else {
				rdbtnActive.setSelected(false);
				rdbtnInactive.setSelected(true);
			}
			textFieldEmail.setText(result.getString("EMAIL"));
			textFieldTinNo.setText(result.getString("TIN_NO"));
			comboBoxGradeScale.setSelectedItem(result.getString("GRADE_SCALE"));
			System.out.println("Tin No"+result.getString("TIN_NO"));
			System.out.println("grade "+result.getString("GRADE_SCALE"));

			
			image_data=result.getBytes("IMAGE");
			labelEmpPiture.setIcon(new ImageIcon(image_data));

			//ADDRESS FIELDS
			/*textFieldEmpIdAdrs.setText(result.getString("EMP_ID"));
			labelEmpPiture.setIcon(new ImageIcon(image_data));
			textFieldCurRoad.setText(result.getString("CUR_ROAD_NAME"));
			textFieldCurVillage.setText(result.getString("CUR_VILLAGE"));
			textFieldCurPostOffice.setText(result.getString("CUR_POST_OFFICE"));
			textFieldCurPoliceStation.setText(result.getString("CUR_POLICE_STATION"));
			textFieldCurDistrict.setText(result.getString("CUR_DISTRICT"));
			
			textFieldParRoad.setText(result.getString("PAR_ROAD_NAME" ));
			textFieldParVillage.setText(result.getString("PAR_VILLAGE"));
			textFieldParPostOffice.setText(result.getString("PAR_POST_OFFICE"));
			textFieldParPoliceStation.setText(result.getString("PAR_POLICE_STATION"));
			textFieldParDistrict.setText(result.getString("PAR_DISTRICT"));
			textFieldCountry.setText(result.getString("COUNTRY"));
			
			//BANK FIELDS
			textFieldEmpIDBnk.setText(result.getString("EMP_ID"));
			textFieldBankName.setText(result.getString("BANK_NAME"));
			textFieldAccountNo.setText(result.getString("EMP_BANK_ACCOUNT"));
			textFieldBranch.setText(result.getString("BRANCH"));

			//Reference details
			textFieldRefName.setText(result.getString("REF_NAME"));
			textFieldRefMobile.setText(result.getString("REF_MOBILE"));
			textFieldRefRelation.setText(result.getString("REF_RELATIONS"));
			textFieldRefEmail.setText(result.getString("REF_EMAIL"));
			textAreaRefAddress.setText(result.getString("REF_ADDRESS"));*/


		}
		
		
		}
		catch(Exception e){
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null,e);
		}
		//ADDRESS FIELDS
	
		
		String query_add="select * from lis_emp_address_details where emp_id='"+empId+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query_add);
			ResultSet result=prstatement.executeQuery();
			
			while(result.next()){
				textFieldEmpIdAdrs.setText(result.getString("EMP_ID"));
				textFieldCurRoad.setText(result.getString("CUR_ROAD_NAME"));
				textFieldCurVillage.setText(result.getString("CUR_VILLAGE"));
				textFieldCurPostOffice.setText(result.getString("CUR_POST_OFFICE"));
				textFieldCurPoliceStation.setText(result.getString("CUR_POLICE_STATION"));
				textFieldCurDistrict.setText(result.getString("CUR_DISTRICT"));
				
				textFieldParRoad.setText(result.getString("PAR_ROAD_NAME" ));
				textFieldParVillage.setText(result.getString("PAR_VILLAGE"));
				textFieldParPostOffice.setText(result.getString("PAR_POST_OFFICE"));
				textFieldParPoliceStation.setText(result.getString("PAR_POLICE_STATION"));
				textFieldParDistrict.setText(result.getString("PAR_DISTRICT"));
				textFieldCountry.setText(result.getString("COUNTRY"));
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String queryBnk="select * from lis_emp_bank_details where emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(queryBnk);
			ResultSet result=prst.executeQuery();
			while(result.next()){
				textFieldEmpIDBnk.setText(result.getString("EMP_ID"));
				textFieldBankName.setText(result.getString("BANK_NAME"));
				textFieldAccountNo.setText(result.getString("EMP_BANK_ACCOUNT"));
				textFieldBranch.setText(result.getString("BRANCH"));

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String queryref="select * from lis_emp_references where emp_id='"+empId+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(queryref);
			ResultSet result=prst.executeQuery();
			while(result.next()){
				textFieldRefName.setText(result.getString("REF_NAME"));
				textFieldRefMobile.setText(result.getString("REF_MOBILE"));
				textFieldRefRelation.setText(result.getString("REF_RELATIONS"));
				textFieldRefEmail.setText(result.getString("REF_EMAIL"));
				textAreaRefAddress.setText(result.getString("REF_ADDRESS"));

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}

	private JTextField textFieldpictureFilePath;
	private String fileName;
	//private byte[] person_image=null;
	private JLabel lblEmployeePicture;
	private JTextField textFieldBankName;
	private JTextField textFieldAccountNo;
	private JTextField textFieldBranch;
	private JTextField textFieldEmpIDBnk;
	private JTextField textFieldEmpIdAdrs;
	private JTextField textFieldRefName;
	private JTextField textFieldRefEmail;
	private JTextField textFieldRefMobile;
	private JTextField textFieldRefRelation;
	private JTextField textFieldTinNo;

	protected void attachPicture() {
		// TODO Auto-generated method stub	
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.showOpenDialog(null);
			File file=fileChooser.getSelectedFile();
			fileName=file.getAbsolutePath();
			textFieldEmployeePicturesPath.setText(fileName);
			try {
				File image =new File(fileName);
				FileInputStream fis=new FileInputStream(image);
				ByteArrayOutputStream bos=new ByteArrayOutputStream();
				byte[] buf=new byte[1024];
				for(int readNum;(readNum=fis.read(buf))!=-1;){
					bos.write(buf,0,readNum);
				}
				person_image=bos.toByteArray();
				//lblEmployeePicture.setIcon(new ImageIcon(person_image));
				//fis.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		
		
	}

	protected boolean emailCheckValidity() {
		// TODO Auto-generated method stub
		String email_pattern="^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}.[a-zA-Z]{2,3}$";
		Pattern pattern=Pattern.compile(email_pattern);
		Matcher regs_match=pattern.matcher(textFieldEmail.getText());
		if(regs_match.matches()){
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Email is not valid,Please insert valid email.");
			return false;
		}
		
				
		
		
	}

	protected boolean checkValidity() {
		try{
			if(!textFieldEmpID.getText().toString().equals("")&&!textFieldFirstName.getText().toString().equals(""))
					/*!textFieldLastName.getText().toString().equals("")&&
					!dateOfBirth.getDate().toLocaleString().equals("")&&!gander.equals("")&&
					!textFieldMobile.getText().toString().equals("")&&
					!textFieldEmail.getText().toString().equals("")&&
					!dateVisaExpire.getDate().toLocaleString().equals("")&&!comboBoxVisaStatus.getSelectedItem().toString().equals("")&&
					!comboBoxWorkpermit.getSelectedItem().toString().equals("")&&!textFieldHouseNo.getText().toString().equals("")&&
					!textFieldRaodName.getText().toString().equals("")&&!textFieldCounty.getText().toString().equals("")&&
					!textFieldCity.getText().toString().equals("")&&!textFieldFathersName.getText().toString().equals("")&&
					!textFieldMothersName.getText().toString().equals("")&&!textFieldOverseaseHouse.getText().toString().equals("")&&
					!textFieldOverseasRoadName.getText().toString().equals("")&&!textFieldOverseasCity.getText().toString().equals("")&&
					!textFieldOverseasCountry.getText().toString().equals("")
					
					
					)*/{
				return true;
				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "Please fill up Emp id and First Name");
				return false;
			}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,  "Please fill up Emp id and First Name");	
				}	
			return false;
	}

	protected void inserData() {
		
		String bDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserBirthdate.getDateEditor().getUiComponent()).getText());
		String jDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserJoiningDate.getDateEditor().getUiComponent()).getText());
//		String eDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserEndingDate.getDateEditor().getUiComponent()).getText());
		
		String query="Insert into LIS_EMPLOYEE_INFO values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		//connection=DbConnection.dbconnection();
		
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
		prStatement.setString(1,textFieldEmpID.getText() );
		prStatement.setString(2,textFieldFirstName.getText() );
		prStatement.setString(3,textFieldLastNme.getText() );
		prStatement.setString(4,textFieldFathersName.getText() );
		prStatement.setString(5,textFieldMothersName.getText() );
		prStatement.setString(6,bDate );
		prStatement.setString(7,textFieldMobile.getText() );
		prStatement.setString(8,(String)comboBoxReligions.getSelectedItem() );
		prStatement.setString(9,(String)comboBoxMeritalStatus.getSelectedItem() );
		prStatement.setString(10,textFieldEmail.getText() );

		prStatement.setString(11,gander );
		prStatement.setBytes(12,person_image );

		prStatement.setString(13,jDate );

		prStatement.setString(14,null);
		prStatement.setString(15,comboBoxDeptID.getSelectedItem().toString() );
		prStatement.setString(16,jobStatus );
		prStatement.setString(17,textFieldTinNo.getText() );
		prStatement.setString(18,comboBoxGradeScale.getSelectedItem().toString() );		
		prStatement.executeQuery();
		JOptionPane.showMessageDialog(null, "Successfully Inserted");

		textFieldEmpIDBnk.setText(textFieldEmpID.getText());
		textFieldEmpIdAdrs.setText(textFieldEmpID.getText());
		clearAllTextFieldPersonalDetails();
		
		prStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
		
	}

	private void clearAllTextFieldPersonalDetails() {
		// TODO Auto-generated method stub
		textFieldEmpID.setText("");
		textFieldFirstName.setText("");
		textFieldLastNme.setText("");
		textFieldFathersName.setText("");
		textFieldMothersName.setText("");
		textFieldMobile.setText("");
		textFieldEmail.setText("");
		comboBoxMeritalStatus.setSelectedIndex(0);
		comboBoxReligions.setSelectedIndex(0);
		comboBoxDeptID.setSelectedIndex(0);
		group.clearSelection();
		textFieldEmployeePicturesPath.setText("");
		labelEmpPiture.setIcon(null);
		grpActiveStatus.clearSelection();
		comboBoxGradeScale.setSelectedIndex(0);
		textFieldTinNo.setText(null);
		dateChooserBirthdate.setCalendar(null);
		dateChooserJoiningDate.setCalendar(null);
		dateChooserEndingDate.setCalendar(null);
				
	}

	private void insertEmpAddressDetails() {
		String query="Insert into LIS_EMP_ADDRESS_DETAILS values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
		prStatement.setString(1,textFieldEmpIdAdrs.getText() );
		prStatement.setString(2,textFieldCurRoad.getText() );
		prStatement.setString(3,textFieldCurVillage.getText() );
		prStatement.setString(4,textFieldCurPostOffice.getText() );
		prStatement.setString(5,textFieldCurPoliceStation.getText() );
		prStatement.setString(6,textFieldCurDistrict.getText() );

		prStatement.setString(7,textFieldParRoad.getText() );
		prStatement.setString(8,textFieldParVillage.getText() );
		prStatement.setString(9,textFieldParPostOffice.getText() );
		prStatement.setString(10,textFieldParPoliceStation.getText() );
		prStatement.setString(11,textFieldParDistrict.getText() );
		prStatement.setString(12,textFieldCountry.getText() );
		prStatement.executeQuery();
		insertReferenceData();

	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
			//e.printStackTrace();
		}
	}

	private void insertReferenceData() {
		String query="Insert into LIS_EMP_REFERENCES values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
			prStatement.setString(1,textFieldEmpIdAdrs.getText() );
			prStatement.setString(2,textFieldRefName.getText() );
			prStatement.setString(3,textFieldRefMobile.getText() );
			prStatement.setString(4,textFieldRefEmail.getText() );
			prStatement.setString(5,textFieldRefRelation.getText() );
			prStatement.setString(6,textAreaRefAddress.getText() );
			prStatement.executeQuery();
			JOptionPane.showMessageDialog(null, "Successfully Inserted");
			clearAllTextFieldAddress();

	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
			//e.printStackTrace();
		}		
	}

	private void clearAllTextFieldAddress() {
		// TODO Auto-generated method stub
		textFieldEmpIdAdrs.setText("");
		textFieldCurRoad.setText("");
		textFieldCurVillage.setText("");
		textFieldCurPostOffice.setText("");
		textFieldCurPoliceStation.setText("");
		textFieldCurDistrict.setText("");
		textFieldParRoad.setText("");
		textFieldParVillage.setText("");
		textFieldParPostOffice.setText("");
		textFieldParPoliceStation.setText("");
		textFieldParDistrict.setText("");
		textFieldCountry.setText("");
		dateChooserBirthdate.setCalendar(null);
		dateChooserJoiningDate.setCalendar(null);
		dateChooserEndingDate.setCalendar(null);
		
		textFieldRefEmail.setText("");
		textFieldRefMobile.setText("");
		textFieldRefName.setText("");
		textFieldRefRelation.setText("");
		textAreaRefAddress.setText("");
		

		
	}
}
