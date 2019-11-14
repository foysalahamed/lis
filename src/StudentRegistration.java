import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import org.apache.poi.hssf.model.ComboboxShape;
import org.apache.poi.hssf.record.LabelSSTRecord;
import javax.swing.JCheckBox;


public class StudentRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldStuID;
	private JTextField textFieldLastName;
	private JTextField textFieldMobile;
	private JTextField textFieldEmail;
	private JTextField textFieldFirstName;
	private JTextField textFieldPicturePath;
	private JTextField textFieldMothersName;
	private JTextField textFieldFathersName;
	private JTextField textFieldM_Occupations;
	private JTextField textFieldF_Occupations;
	private JTextField textFieldVillage;
	private JTextField textFieldRoad;
	private JTextField textFieldPostOffice;
	private JTextField textFieldPoliceStations;
	private JTextField textFieldDistrict;
	private JTextField textFieldCountry;
	private JTextField textFieldP_Phone;
	private JTextField textFieldP_Email;
	JDateChooser dateChooserBirthDate;
	JComboBox comboBoxReligion;
	JComboBox comboBoxGroup;
	JComboBox comboBoxYear;
	JComboBox comboBoxVehichleNo;
	JCheckBox chckbxVehiclePrivate;
	String gander,studentStatus;
	ButtonGroup group;
	ButtonGroup groupforStudentStatus;
	JRadioButton radioButtonFemale;
	JRadioButton radioButtonMale;
	JRadioButton rdbtnActive, rdbtnInactive;
	private Connection connection;
	private byte[] person_imag;
	private byte[] parent_image;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRegistration frame = new StudentRegistration();
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
	public StudentRegistration() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		
		setBounds(100, 100, 982, 697);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(138, 96, 497, 213);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Date of Birth");
		label.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label.setBounds(245, 14, 77, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Sex");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_1.setBounds(247, 132, 33, 14);
		panel.add(label_1);
		
		textFieldStuID = new JTextField();
		textFieldStuID.setColumns(10);
		textFieldStuID.setBounds(110, 11, 120, 20);
		panel.add(textFieldStuID);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(111, 54, 120, 20);
		panel.add(textFieldLastName);
		
		JLabel label_2 = new JLabel("Last Name");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_2.setBounds(14, 57, 77, 14);
		panel.add(label_2);
		
		JLabel lblEnrolmentId = new JLabel("Student ID");
		lblEnrolmentId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEnrolmentId.setBounds(16, 14, 77, 14);
		panel.add(lblEnrolmentId);
		
		 radioButtonFemale = new JRadioButton("Female");
		radioButtonFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gander="Female";
			}
		});
		radioButtonFemale.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		radioButtonFemale.setBackground(Color.WHITE);
		radioButtonFemale.setBounds(349, 130, 77, 18);
		panel.add(radioButtonFemale);
		
		 radioButtonMale = new JRadioButton("Male");
		radioButtonMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gander="Male";
			}
		});
		radioButtonMale.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		radioButtonMale.setBackground(Color.WHITE);
		radioButtonMale.setBounds(290, 130, 59, 18);
		panel.add(radioButtonMale);
		group=new ButtonGroup();
		group.add(radioButtonMale);
		group.add(radioButtonFemale);
		
		JLabel lblReligions = new JLabel("Religions");
		lblReligions.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblReligions.setBounds(14, 81, 77, 14);
		panel.add(lblReligions);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setBackground(SystemColor.controlDkShadow);
		lblGroup.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblGroup.setBounds(14, 103, 77, 14);
		panel.add(lblGroup);
		
		 comboBoxGroup = new JComboBox();
		 comboBoxGroup.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B", "C", "D", "E"}));
		comboBoxGroup.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxGroup.setBackground(Color.WHITE);
		comboBoxGroup.setBounds(111, 100, 120, 20);
		panel.add(comboBoxGroup);
		
		comboBoxReligion = new JComboBox();
		comboBoxReligion.setModel(new DefaultComboBoxModel(new String[] {"Select", "Muslim", "Hindu", "Christian", "Bodist", "Others"}));
		comboBoxReligion.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxReligion.setBackground(Color.WHITE);
		comboBoxReligion.setBounds(111, 78, 120, 20);
		panel.add(comboBoxReligion);
		
		 dateChooserBirthDate = new JDateChooser();
		dateChooserBirthDate.setBounds(338, 11, 119, 20);
		panel.add(dateChooserBirthDate);
		
		textFieldMobile = new JTextField();
		textFieldMobile.setColumns(10);
		textFieldMobile.setBounds(338, 81, 120, 20);
		panel.add(textFieldMobile);
		
		JLabel label_8 = new JLabel("Mobile No");
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_8.setBounds(245, 84, 77, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Email");
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_9.setBounds(245, 106, 77, 14);
		panel.add(label_9);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(338, 103, 120, 20);
		panel.add(textFieldEmail);
		
		JLabel label_10 = new JLabel("First Name");
		label_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_10.setBounds(16, 37, 77, 14);
		panel.add(label_10);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(110, 33, 120, 20);
		panel.add(textFieldFirstName);
		
		textFieldPicturePath = new JTextField();
		textFieldPicturePath.setColumns(10);
		textFieldPicturePath.setBounds(110, 184, 177, 20);
		panel.add(textFieldPicturePath);
		
		JButton buttonPicture = new JButton("Image");
		buttonPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName;
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.showOpenDialog(null);
				File file=fileChooser.getSelectedFile();
				fileName=file.getAbsolutePath();
			   textFieldPicturePath.setText(fileName);
				person_imag=attachPicture(fileName);
				lblStudentPicture.setIcon(new ImageIcon(person_imag));

			}
		});
		buttonPicture.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		buttonPicture.setBounds(289, 182, 89, 23);
		panel.add(buttonPicture);
		
		JLabel label_11 = new JLabel("Picture");
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_11.setBounds(14, 187, 77, 14);
		panel.add(label_11);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblYear.setBounds(245, 36, 77, 14);
		panel.add(lblYear);
		
		 comboBoxYear = new JComboBox();
		 comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"Select", "Nursery", "Play", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}));
		comboBoxYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxYear.setBackground(Color.WHITE);
		comboBoxYear.setBounds(337, 33, 120, 20);
		panel.add(comboBoxYear);
		
		JLabel lblTransport = new JLabel("Transport");
		lblTransport.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTransport.setBackground(SystemColor.controlDkShadow);
		lblTransport.setBounds(14, 126, 77, 14);
		panel.add(lblTransport);
		
		 comboBoxVehichleNo = new JComboBox();
		 comboBoxVehichleNo.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		comboBoxVehichleNo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxVehichleNo.setBackground(Color.WHITE);
		comboBoxVehichleNo.setBounds(111, 123, 120, 20);
		panel.add(comboBoxVehichleNo);
		
		 chckbxVehiclePrivate = new JCheckBox("Private");
		 chckbxVehiclePrivate.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) { 
		 		if(chckbxVehiclePrivate.isSelected()){
		 			comboBoxVehichleNo.setEnabled(false);
		 		}
		 		else{
		 			comboBoxVehichleNo.setEnabled(true);

		 		}
		 	}
		 });
		chckbxVehiclePrivate.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxVehiclePrivate.setBackground(Color.WHITE);
		chckbxVehiclePrivate.setBounds(141, 145, 89, 18);
		panel.add(chckbxVehiclePrivate);
		
		JLabel lblOr = new JLabel("Or");
		lblOr.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOr.setBounds(97, 148, 33, 14);
		panel.add(lblOr);
		
		JLabel lblStudentRoll = new JLabel("Student Roll");
		lblStudentRoll.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblStudentRoll.setBounds(245, 59, 77, 14);
		panel.add(lblStudentRoll);
		
		textFieldStudentRoll = new JTextField();
		textFieldStudentRoll.setColumns(10);
		textFieldStudentRoll.setBounds(338, 56, 120, 20);
		panel.add(textFieldStudentRoll);
		
		 rdbtnActive = new JRadioButton("Active");
		 rdbtnActive.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		studentStatus="active";
		 	}
		 });
		rdbtnActive.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		rdbtnActive.setBackground(Color.WHITE);
		rdbtnActive.setBounds(290, 153, 59, 18);
		panel.add(rdbtnActive);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblStatus.setBounds(245, 155, 42, 14);
		panel.add(lblStatus);
		
		 rdbtnInactive = new JRadioButton("Inactive");
		 rdbtnInactive.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		studentStatus="inactive";

		 	}
		 });
		rdbtnInactive.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		rdbtnInactive.setBackground(Color.WHITE);
		rdbtnInactive.setBounds(349, 153, 77, 18);
		panel.add(rdbtnInactive);
		groupforStudentStatus=new ButtonGroup();
		groupforStudentStatus.add(rdbtnActive);
		groupforStudentStatus.add(rdbtnInactive);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(138, 330, 497, 130);
		contentPane.add(panel_1);
		
		JLabel lblFathersName = new JLabel("Fathers Name");
		lblFathersName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblFathersName.setBounds(10, 31, 77, 14);
		panel_1.add(lblFathersName);
		
		JLabel lblMothersName = new JLabel("Mothers Name");
		lblMothersName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblMothersName.setBounds(11, 53, 85, 14);
		panel_1.add(lblMothersName);
		
		textFieldMothersName = new JTextField();
		textFieldMothersName.setColumns(10);
		textFieldMothersName.setBounds(107, 50, 120, 20);
		panel_1.add(textFieldMothersName);
		
		textFieldFathersName = new JTextField();
		textFieldFathersName.setColumns(10);
		textFieldFathersName.setBounds(107, 28, 120, 20);
		panel_1.add(textFieldFathersName);
		
		textFieldM_Occupations = new JTextField();
		textFieldM_Occupations.setColumns(10);
		textFieldM_Occupations.setBounds(345, 25, 120, 20);
		panel_1.add(textFieldM_Occupations);
		
		JLabel lblOccupations = new JLabel("Occupations");
		lblOccupations.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOccupations.setBounds(246, 28, 77, 14);
		panel_1.add(lblOccupations);
		
		textFieldF_Occupations = new JTextField();
		textFieldF_Occupations.setColumns(10);
		textFieldF_Occupations.setBounds(345, 47, 120, 20);
		panel_1.add(textFieldF_Occupations);
		
		JLabel label_3 = new JLabel("Occupations");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_3.setBounds(246, 51, 77, 14);
		panel_1.add(label_3);
		
		JLabel lblPhone = new JLabel("Phone No");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPhone.setBounds(10, 75, 85, 14);
		panel_1.add(lblPhone);
		
		textFieldP_Phone = new JTextField();
		textFieldP_Phone.setColumns(10);
		textFieldP_Phone.setBounds(106, 72, 120, 20);
		panel_1.add(textFieldP_Phone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmail.setBounds(246, 73, 77, 14);
		panel_1.add(lblEmail);
		
		textFieldP_Email = new JTextField();
		textFieldP_Email.setColumns(10);
		textFieldP_Email.setBounds(345, 69, 120, 20);
		panel_1.add(textFieldP_Email);
		
		JLabel label_4 = new JLabel("Picture");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_4.setBounds(9, 99, 77, 14);
		panel_1.add(label_4);
		
		textFieldParentPicturePath = new JTextField();
		textFieldParentPicturePath.setColumns(10);
		textFieldParentPicturePath.setBounds(105, 96, 177, 20);
		panel_1.add(textFieldParentPicturePath);
		
		JButton button = new JButton("Image");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName;
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.showOpenDialog(null);
				File file=fileChooser.getSelectedFile();
				fileName=file.getAbsolutePath();
			   textFieldParentPicturePath.setText(fileName);
				parent_image=attachPicture(fileName);
				lblParentsPicture.setIcon(new ImageIcon(parent_image));
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		button.setBounds(284, 94, 89, 23);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(138, 484, 497, 126);
		contentPane.add(panel_2);
		
		textFieldVillage = new JTextField();
		textFieldVillage.setColumns(10);
		textFieldVillage.setBounds(111, 29, 120, 20);
		panel_2.add(textFieldVillage);
		
		textFieldRoad = new JTextField();
		textFieldRoad.setColumns(10);
		textFieldRoad.setBounds(111, 51, 120, 20);
		panel_2.add(textFieldRoad);
		
		JLabel lblVillage = new JLabel("Village");
		lblVillage.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblVillage.setBounds(14, 32, 77, 14);
		panel_2.add(lblVillage);
		
		JLabel label_19 = new JLabel("Road Name");
		label_19.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_19.setBounds(15, 54, 77, 14);
		panel_2.add(label_19);
		
		JLabel lblPoliceStations = new JLabel("Police Stations");
		lblPoliceStations.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPoliceStations.setBounds(250, 51, 87, 14);
		panel_2.add(lblPoliceStations);
		
		JLabel lblPostOffice = new JLabel("Post Office");
		lblPostOffice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPostOffice.setBounds(250, 29, 77, 14);
		panel_2.add(lblPostOffice);
		
		textFieldPostOffice = new JTextField();
		textFieldPostOffice.setColumns(10);
		textFieldPostOffice.setBounds(347, 26, 120, 20);
		panel_2.add(textFieldPostOffice);
		
		textFieldPoliceStations = new JTextField();
		textFieldPoliceStations.setColumns(10);
		textFieldPoliceStations.setBounds(347, 48, 120, 20);
		panel_2.add(textFieldPoliceStations);
		
		textFieldDistrict = new JTextField();
		textFieldDistrict.setColumns(10);
		textFieldDistrict.setBounds(111, 73, 120, 20);
		panel_2.add(textFieldDistrict);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(347, 70, 120, 20);
		panel_2.add(textFieldCountry);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCountry.setBounds(250, 73, 85, 14);
		panel_2.add(lblCountry);
		
		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDistrict.setBounds(13, 76, 77, 14);
		panel_2.add(lblDistrict);
		
		JLabel lblStudentDetails = new JLabel("Student Details");
		lblStudentDetails.setBounds(138, 76, 120, 21);
		contentPane.add(lblStudentDetails);
		lblStudentDetails.setForeground(Color.BLUE);
		lblStudentDetails.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel lblParentDetails = new JLabel("Parent Details");
		lblParentDetails.setBounds(138, 309, 120, 20);
		contentPane.add(lblParentDetails);
		lblParentDetails.setForeground(Color.BLUE);
		lblParentDetails.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(138, 462, 61, 19);
		contentPane.add(lblAddress);
		lblAddress.setForeground(Color.BLUE);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(649, 115, 160, 185);
		contentPane.add(panel_3);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		desktopPane.setBounds(9, 11, 140, 163);
		panel_3.add(desktopPane);
		
		lblStudentPicture = new JLabel("");
		lblStudentPicture.setBounds(7, 11, 130, 141);
		desktopPane.add(lblStudentPicture);
		
		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertStudentInfo();
			}
		});
		button_1.setForeground(new Color(0, 100, 0));
		button_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_1.setBounds(138, 621, 120, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.setForeground(Color.RED);
		button_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_2.setBounds(261, 621, 120, 25);
		contentPane.add(button_2);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update_StudentInfo();
			}
		});
		btnEdit.setForeground(Color.BLUE);
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnEdit.setBounds(385, 621, 120, 25);
		contentPane.add(btnEdit);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stuId=null;
				stuId=JOptionPane.showInputDialog("Student ID");
				searchStudentInfo(stuId);
		
			}
		});
		btnSearch.setForeground(new Color(147, 112, 219));
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.setBounds(692, 79, 120, 25);
		contentPane.add(btnSearch);
		
		JLabel lblLondonInteligenceSchool = new JLabel("London Intelligence School");
		lblLondonInteligenceSchool.setForeground(new Color(205, 133, 63));
		lblLondonInteligenceSchool.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblLondonInteligenceSchool.setBounds(405, 33, 338, 33);
		contentPane.add(lblLondonInteligenceSchool);
		
		JLabel labelTitleLogo = new JLabel("");
		labelTitleLogo.setBounds(351, 17, 50, 68);
		contentPane.add(labelTitleLogo);
		Image title_logo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		labelTitleLogo.setIcon(new ImageIcon(title_logo));
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(648, 330, 158, 185);
		contentPane.add(panel_4);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		desktopPane_1.setBounds(10, 11, 135, 163);
		panel_4.add(desktopPane_1);
		
		lblParentsPicture = new JLabel("");
		lblParentsPicture.setBounds(10, 11, 120, 141);
		desktopPane_1.add(lblParentsPicture);
		
		JButton btnStatus = new JButton("Status");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				makeTuitionFeeSchdule();
			}
		});
		btnStatus.setForeground(Color.BLUE);
		btnStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnStatus.setBounds(506, 621, 120, 25);
		contentPane.add(btnStatus);
		getvehichleNo();
	}
	private void getvehichleNo() {
		// TODO Auto-generated method stub
		String query="select VEHICHLE_NO from LIS_TRANSPORT_FEE_SETUP";
		
		try{
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet result=prst.executeQuery();
			while(result.next()){
				comboBoxVehichleNo.addItem(result.getString("VEHICHLE_NO"));
			}
			result.close();
			prst.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void update_StudentInfo() {
		// TODO Auto-generated method stub
		String vhc=null;
		if(chckbxVehiclePrivate.isSelected()){
			vhc="private";
		}else {
			vhc=comboBoxVehichleNo.getSelectedItem().toString();
		}

		String query="update lis_student_info set firstname='"+textFieldFirstName.getText()+"',lastname='"+textFieldLastName.getText()+"',birthdate='"+((JTextField)dateChooserBirthDate.getDateEditor().getUiComponent()).getText()+"',year='"+(String)comboBoxYear.getSelectedItem()+"',student_roll='"+textFieldStudentRoll.getText()+"',section='"+(String)comboBoxGroup.getSelectedItem()+"',mobile='"+textFieldMobile.getText()+"',religion='"+(String)comboBoxReligion.getSelectedItem()+"',email='"+textFieldEmail.getText()+"',sex='"+gander+"',vehichle='"+vhc+"',status='"+studentStatus+"' where stu_id='"+textFieldStuID.getText()+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.executeQuery(query);
			prstatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		String query_address="update lis_student_address_details set road_name='"+textFieldRoad.getText()+"',village='"+textFieldVillage.getText()+"',post_office='"+textFieldPostOffice.getText()+"',police_station='"+textFieldPoliceStations.getText()+"',district='"+textFieldDistrict.getText()+"',country='"+textFieldCountry.getText()+"' where stu_id='"+textFieldStuID.getText()+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query_address);
			prstatement.executeQuery(query_address);
			prstatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		String query_parents="update lis_parents_info set fathers_name='"+textFieldFathersName.getText()+"',mothers_name='"+textFieldMothersName.getText()+"',fathers_occupation='"+textFieldF_Occupations.getText()+"',mothers_occupation='"+textFieldM_Occupations.getText()+"',parent_mobile='"+textFieldP_Phone.getText()+"',parent_email='"+textFieldP_Email.getText()+"' where stu_id='"+textFieldStuID.getText()+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query_parents);
			prstatement.executeQuery(query_parents);
			prstatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Successfully updated");
		clearAllTextField();

	}
	JLabel lblParentsPicture;
	byte[] image_data=new byte[1024];
	byte[] parent_image_data=new byte[1024];
	protected void searchStudentInfo(String stuId) {
		// TODO Auto-generated method stub
		System.out.print(stuId);
		String query="select info.stu_id ,info.firstname,info.lastname,info.birthdate,info.year,info.student_roll,info.mobile,info.religion,info.email,info.section,info.sex,info.image,info.vehichle,info.status,par.fathers_name,par.mothers_name,par.fathers_occupation,par.mothers_occupation,par.parent_image,par.parent_mobile,par.parent_email,adrs.road_name,adrs.village,adrs.post_office,adrs.police_station,adrs.district,adrs.country from lis_student_info info join lis_student_address_details adrs on info.stu_id=adrs.stu_id join lis_parents_info par on info.stu_id=par.stu_id where info.stu_id='"+stuId+"' ";
		try{
		PreparedStatement prstatement=connection.prepareStatement(query);
		ResultSet result=prstatement.executeQuery();
		//Date date;
		if(result.next()){
			textFieldStuID.setText(result.getString("STU_ID"));
			textFieldFirstName.setText(result.getString("FIRSTNAME"));
			
			textFieldLastName.setText(result.getString("LASTNAME"));
			dateChooserBirthDate.setDate(result.getDate("BIRTHDATE"));
			textFieldMobile.setText(result.getString("MOBILE"));
			comboBoxReligion.setSelectedItem(result.getString("RELIGION"));
			comboBoxGroup.setSelectedItem(result.getString("SECTION"));
			comboBoxYear.setSelectedItem(result.getString("YEAR"));
			textFieldStudentRoll.setText(result.getString("STUDENT_ROLL"));
			
			
			//textFieldsetText(result.getString("SECTION"));

			textFieldFathersName.setText(result.getString("FATHERS_NAME"));
			textFieldMothersName.setText(result.getString("MOTHERS_NAME"));
			textFieldF_Occupations.setText(result.getString("FATHERS_OCCUPATION"));
			textFieldM_Occupations.setText(result.getString("MOTHERS_OCCUPATION"));
			parent_image=result.getBytes("PARENT_IMAGE");
			lblParentsPicture.setIcon(new ImageIcon(parent_image));
			textFieldP_Phone.setText(result.getString("PARENT_MOBILE"));
			textFieldP_Email.setText(result.getString("PARENT_EMAIL"));

			
			
			
			
			textFieldEmail.setText(result.getString("EMAIL"));
			image_data=result.getBytes("IMAGE");
			lblStudentPicture.setIcon(new ImageIcon(image_data));
			textFieldRoad.setText(result.getString("ROAD_NAME"));
			textFieldVillage.setText(result.getString("VILLAGE"));
			textFieldPostOffice.setText(result.getString("POST_OFFICE"));
			textFieldPoliceStations.setText(result.getString("POLICE_STATION"));
			textFieldDistrict.setText(result.getString("DISTRICT"));
			textFieldCountry.setText(result.getString("COUNTRY"));
			System.out.println("vehicle no: "+result.getString("VEHICHLE"));
			if(result.getString("VEHICHLE").equals("private")){
				chckbxVehiclePrivate.setSelected(true);
				comboBoxVehichleNo.setSelectedItem(null);
			}
			else{
				chckbxVehiclePrivate.setSelected(false);
				comboBoxVehichleNo.setSelectedItem(result.getString("VEHICHLE"));

			}
			if(result.getString("SEX").equals("Male")){
				radioButtonMale.setSelected(true);
				radioButtonFemale.setSelected(false);
			}
			else{
				radioButtonMale.setSelected(false);
				radioButtonFemale.setSelected(true);
			}
			if(result.getString("STATUS").equals("active")){
				rdbtnActive.setSelected(true);
				rdbtnInactive.setSelected(false);
			}
			else{
				rdbtnActive.setSelected(false);
				rdbtnInactive.setSelected(true);
			}

		}
		else{
			JOptionPane.showMessageDialog(null, "Wrong ID!!! Please try again");

		}
		
	}catch(Exception e){
		//e.printStackTrace();
		JOptionPane.showMessageDialog(null,e);
	}
	}

	protected void insertStudentInfo() {
		// TODO Auto-generated method stub
		String vehi=null;
		if(chckbxVehiclePrivate.isSelected()){
			vehi="private";
		}
		else{
			vehi=comboBoxVehichleNo.getSelectedItem().toString();
		}
		String query="Insert into LIS_STUDENT_INFO values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		//connection=DbConnection.dbconnection();
		
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
		prStatement.setString(1,textFieldStuID.getText() );
		prStatement.setString(2,textFieldFirstName.getText() );
		prStatement.setString(3,textFieldLastName.getText() );
		
		prStatement.setString(4,((JTextField)dateChooserBirthDate.getDateEditor().getUiComponent()).getText() );
		prStatement.setString(5,(String)comboBoxYear.getSelectedItem() );
		prStatement.setString(6,textFieldStudentRoll.getText() );
		prStatement.setString(7,textFieldMobile.getText() );

		prStatement.setString(8,(String)comboBoxReligion.getSelectedItem() );
		prStatement.setString(9,textFieldEmail.getText() );
		prStatement.setString(10,(String)comboBoxGroup.getSelectedItem() );

		prStatement.setString(11,gander );
		prStatement.setBytes(12,person_imag );
		prStatement.setString(13,vehi );
		prStatement.setString(14,studentStatus );

		prStatement.executeQuery();
		prStatement.close();
		//clear textfield
		
		//refresh();
		insertStuAddressDetails();
		insertStudentParentsDetails();
		makeTuitionFeeSchdule();
		clearAllTextField();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Duplicate Student ID");
		}
		
		
		

		
	}
	String[] monthName={"","January","February","March","April","May","Jun","July","August","September","October","November","December"};
	private void makeTuitionFeeSchdule() {
		int j=0;
		for(int i=1;i<=12;i++){
			
//			String installmentDate=null ;
//			SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
//			Calendar c = Calendar.getInstance();
//			
//			
//				c.add(Calendar.MONTH, j++ );  // number of days to add
//				
//				installmentDate = sdf.format(c.getTime());  // dt is now the new date
//			
//				System.out.println("Month:"+monthName[i]);
				String stuId=textFieldStuID.getText();
				System.out.println("student id"+stuId);

				String classname=stuId.substring(0,2);
				String feeId="tutionfee"+classname;
				System.out.println("fee id"+feeId);
				double feeAmount=getFeeofStudent(feeId);
			
			
//			String query1="Insert into LIS_FEE_SCHEDUL values(?,?,?,to_date(?,'MM-yyyy'),?,?,?,?,?,?,?,?)";
				String query1="Insert into LIS_FEE_SCHEDUL values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement prStatement= connection.prepareStatement(query1);
				prStatement.setDouble(1,i);
				prStatement.setString(2,textFieldStuID.getText());
				prStatement.setString(3,comboBoxYear.getSelectedItem().toString());
				prStatement.setString(4,monthName[i] );
				
				prStatement.setDouble(5,feeAmount);
				prStatement.setString(6,"Due" );
				prStatement.setString(7,"" );
				prStatement.setString(8,"" );
				prStatement.setString(9,"" );
				prStatement.setString(10,"" );
				prStatement.setString(11,"" );
				prStatement.setString(12,"n/a" );
				
				prStatement.executeQuery();
				
			
			prStatement.close();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//JOptionPane.showMessageDialog(null, e);
				e1.printStackTrace();
			}
	}
		
	}

	private double getFeeofStudent(String feeId) {
		try {
			double feeAmount=0;
			String q="select sales_price from lis_product_price_setup where product_id='"+feeId+"'";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			if(result.next()){
				 feeAmount=Double.parseDouble(result.getString("SALES_PRICE"));
				 prstatement.close();
				 result.close();
				 return feeAmount;
				
			}	
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return 0;
	}

	private void insertStudentParentsDetails() {
		// TODO Auto-generated method stub
		try {
			String query="Insert into LIS_PARENTS_INFO values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement prStatement= connection.prepareStatement(query);
			
			prStatement.setString(1,textFieldFathersName.getText() );
			prStatement.setString(2,textFieldMothersName.getText() );
			prStatement.setString(3,textFieldF_Occupations.getText() );
			prStatement.setString(4,textFieldM_Occupations.getText() );
			prStatement.setBytes(5,parent_image );
			prStatement.setString(6,textFieldP_Phone.getText() );
			prStatement.setString(7,textFieldP_Email.getText() );
			prStatement.setString(8,textFieldStuID.getText() );
			
			prStatement.executeQuery();
			JOptionPane.showMessageDialog(null, "Successfully Saved parents info");
			
			

			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, "E");

		}
		

		
	}

	private void insertStuAddressDetails() {
		// TODO Auto-generated method stub
		try {
			String query="Insert into LIS_STUDENT_ADDRESS_DETAILS values(?,?,?,?,?,?,?)";
			
			PreparedStatement prStatement= connection.prepareStatement(query);
			
			prStatement.setString(1,textFieldStuID.getText() );
			prStatement.setString(2,textFieldRoad.getText() );
			prStatement.setString(3,textFieldVillage.getText() );
			prStatement.setString(4,textFieldPostOffice.getText() );
			prStatement.setString(5,textFieldPoliceStations.getText() );
			prStatement.setString(6,textFieldDistrict.getText() );
			prStatement.setString(7,textFieldCountry.getText() );
			prStatement.executeQuery();
			JOptionPane.showMessageDialog(null, "Successfully Saved");
			
			//clearAllTextField();

			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, "E");

		}
		

		
	}
	private void clearAllTextField() {
		// TODO Auto-generated method stub
		textFieldStuID.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");
		textFieldFathersName.setText("");
		textFieldF_Occupations.setText("");
		textFieldMothersName.setText("");
		textFieldM_Occupations.setText("");
		textFieldP_Email.setText("");
		textFieldP_Phone.setText("");

		textFieldMobile.setText("");
		textFieldEmail.setText("");
		comboBoxYear.setSelectedIndex(0);
		textFieldStudentRoll.setText("");
		comboBoxGroup.setSelectedIndex(0);
		comboBoxReligion.setSelectedIndex(0);
		group.clearSelection();
		groupforStudentStatus.clearSelection();
		comboBoxVehichleNo.setSelectedIndex(0);
		chckbxVehiclePrivate.setSelected(false);
		
		textFieldPicturePath.setText("");
		lblStudentPicture.setIcon(null);
		textFieldParentPicturePath.setText("");
		lblParentsPicture.setIcon(null);
		textFieldRoad.setText("");
		textFieldVillage.setText("");
		textFieldPostOffice.setText("");
		textFieldPoliceStations.setText("");
		textFieldDistrict.setText("");
		textFieldCountry.setText("");
		dateChooserBirthDate.setCalendar(null);
		

		
		
	}
	private JTextField textFieldpicturePath;
	//private String fileName;
	//private byte[] person_image=null;
	private JLabel lblStudentPicture;
	private JTextField textFieldParentPicturePath;
	private JTextField textFieldStudentRoll;
	protected byte[] attachPicture(String fileName) {
		// TODO Auto-generated method stub
		 byte[] person_image=null;
			
			try {
				File image =new File(fileName);
				FileInputStream fis=new FileInputStream(image);
				ByteArrayOutputStream bos=new ByteArrayOutputStream();
				byte[] buf=new byte[1024];
				for(int readNum;(readNum=fis.read(buf))!=-1;){
					bos.write(buf,0,readNum);
				}
				person_image=bos.toByteArray();
				return person_image;
				//lblEmployeePicture.setIcon(new ImageIcon(person_image));
				//fis.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			return person_image;
		
		
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
			if(!textFieldStuID.getText().toString().equals("")&&!textFieldFirstName.getText().toString().equals(""))
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
}
