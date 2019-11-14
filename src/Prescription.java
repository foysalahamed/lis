import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
import java.awt.font.TextMeasurer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Prescription extends JFrame {

	
	private JPanel contentPane;
	private JDateChooser dateChooserVisitingDate;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientMobileNo;
	private JTextField textFieldPatientID;
	private JTextField textFieldPatientAge;
	private JTextField textFieldRemarks;
	private Connection connection;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JComboBox comboBoxGroup_of_Medicine;
	ButtonGroup group;
	String gander;
	JTextArea textAreaHistory;
	JTextArea textAreaComplaints;
	JTextArea textAreaAddress;
	JTextArea textAreaMedicalTest;
	JTextArea textAreaInvestigation;
	JComboBox comboBoxMedicineName;
	JComboBox comboBoxTimeInterval;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescription frame = new Prescription();
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
	public Prescription() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		setBounds(100, 100, 952, 775);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonowaraHospitalprivate = new JLabel("Monowara Hospital (Private) Limited");
		lblMonowaraHospitalprivate.setForeground(new Color(60, 179, 113));
		lblMonowaraHospitalprivate.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblMonowaraHospitalprivate.setBounds(202, 27, 419, 31);
		contentPane.add(lblMonowaraHospitalprivate);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(43, 137, 372, 167);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setForeground(Color.BLUE);
		lblPatientName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPatientName.setBounds(10, 39, 86, 14);
		panel.add(lblPatientName);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setForeground(Color.BLUE);
		lblSex.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSex.setBounds(10, 107, 76, 14);
		panel.add(lblSex);
		
		JLabel lblMobileNo = new JLabel("Age");
		lblMobileNo.setForeground(Color.BLUE);
		lblMobileNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMobileNo.setBounds(9, 63, 89, 14);
		panel.add(lblMobileNo);
		
		JLabel lblMobilreNo = new JLabel("Mobilre No");
		lblMobilreNo.setForeground(Color.BLUE);
		lblMobilreNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMobilreNo.setBounds(10, 81, 89, 14);
		panel.add(lblMobilreNo);
		
		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setForeground(Color.BLUE);
		lblPatientId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPatientId.setBounds(10, 14, 89, 14);
		panel.add(lblPatientId);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setBounds(99, 37, 120, 20);
		panel.add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		 rdbtnMale = new JRadioButton("Male");
		 rdbtnMale.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		gander="Male";
		 	}
		 });
		rdbtnMale.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnMale.setBackground(SystemColor.inactiveCaption);
		rdbtnMale.setBounds(96, 104, 52, 23);
		panel.add(rdbtnMale);
		
		 rdbtnFemale = new JRadioButton("Female");
		 rdbtnFemale.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		gander="Female";

		 	}
		 });
		rdbtnFemale.setBackground(SystemColor.inactiveCaption);
		rdbtnFemale.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnFemale.setBounds(155, 105, 69, 23);
		panel.add(rdbtnFemale);
		group=new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		textFieldPatientMobileNo = new JTextField();
		textFieldPatientMobileNo.setColumns(10);
		textFieldPatientMobileNo.setBounds(99, 79, 120, 20);
		panel.add(textFieldPatientMobileNo);
		
		textFieldPatientID = new JTextField();
		textFieldPatientID.setColumns(10);
		textFieldPatientID.setBounds(99, 16, 120, 20);
		panel.add(textFieldPatientID);
		
		textFieldPatientAge = new JTextField();
		textFieldPatientAge.setColumns(10);
		textFieldPatientAge.setBounds(99, 58, 120, 20);
		panel.add(textFieldPatientAge);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.BLUE);
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAddress.setBounds(223, 15, 89, 14);
		panel.add(lblAddress);
		
		 textAreaAddress = new JTextArea();
		textAreaAddress.setBounds(229, 35, 117, 86);
		panel.add(textAreaAddress);
		
		JLabel lblVisitingDate = new JLabel("Visiting Date");
		lblVisitingDate.setForeground(Color.BLUE);
		lblVisitingDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblVisitingDate.setBounds(10, 132, 89, 14);
		panel.add(lblVisitingDate);
		
		 dateChooserVisitingDate = new JDateChooser();
		dateChooserVisitingDate.setBounds(99, 134, 95, 20);
		panel.add(dateChooserVisitingDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(425, 137, 424, 167);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		 textAreaComplaints = new JTextArea();
		textAreaComplaints.setBounds(46, 35, 156, 100);
		panel_1.add(textAreaComplaints);
		
		textAreaHistory = new JTextArea();
		textAreaHistory.setBounds(231, 35, 156, 100);
		panel_1.add(textAreaHistory);
		
		JLabel lblComplaints = new JLabel("Complaints");
		lblComplaints.setBounds(47, 17, 89, 14);
		panel_1.add(lblComplaints);
		lblComplaints.setForeground(Color.BLUE);
		lblComplaints.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setForeground(Color.BLUE);
		lblHistory.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblHistory.setBounds(232, 18, 89, 14);
		panel_1.add(lblHistory);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(419, 332, 430, 231);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		comboBoxGroup_of_Medicine = new JComboBox();
		comboBoxGroup_of_Medicine.setModel(new DefaultComboBoxModel(new String[] {"", "A", "B", "C", "D"}));
		comboBoxGroup_of_Medicine.setBounds(102, 39, 122, 20);
		panel_2.add(comboBoxGroup_of_Medicine);
		
		comboBoxMedicineName = new JComboBox();
		comboBoxMedicineName.setModel(new DefaultComboBoxModel(new String[] {"", "Tab. Napa 500gm", "Cap. Paracitamal 500gm", "Tab. Ace 500gm", "Cap. Ace xtra 500gm", "Syrp. Napa 300ml"}));
		comboBoxMedicineName.setBounds(102, 60, 122, 20);
		panel_2.add(comboBoxMedicineName);
		
		comboBoxTimeInterval = new JComboBox();
		comboBoxTimeInterval.setModel(new DefaultComboBoxModel(new String[] {"", "1+0+0", "1+0+1", "1+1+1"}));
		comboBoxTimeInterval.setBounds(102, 81, 122, 20);
		panel_2.add(comboBoxTimeInterval);
		
		JRadioButton rdbtnBeforeFood = new JRadioButton("Before Food");
		rdbtnBeforeFood.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnBeforeFood.setBackground(SystemColor.inactiveCaption);
		rdbtnBeforeFood.setBounds(225, 79, 92, 23);
		panel_2.add(rdbtnBeforeFood);
		
		JRadioButton rdbtnAfterFood = new JRadioButton("After Food");
		rdbtnAfterFood.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnAfterFood.setBackground(SystemColor.inactiveCaption);
		rdbtnAfterFood.setBounds(319, 80, 92, 23);
		panel_2.add(rdbtnAfterFood);
		
		textFieldRemarks = new JTextField();
		textFieldRemarks.setBounds(102, 103, 122, 20);
		panel_2.add(textFieldRemarks);
		textFieldRemarks.setColumns(10);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setForeground(Color.BLUE);
		lblGroup.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblGroup.setBounds(10, 42, 59, 14);
		panel_2.add(lblGroup);
		
		JLabel lblMedicine = new JLabel("Medicine");
		lblMedicine.setForeground(Color.BLUE);
		lblMedicine.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMedicine.setBounds(11, 62, 59, 14);
		panel_2.add(lblMedicine);
		
		JLabel lblTimeInterval = new JLabel("Time interval");
		lblTimeInterval.setForeground(Color.BLUE);
		lblTimeInterval.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTimeInterval.setBounds(5, 84, 87, 14);
		panel_2.add(lblTimeInterval);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setForeground(Color.BLUE);
		lblRemarks.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRemarks.setBounds(8, 108, 87, 14);
		panel_2.add(lblRemarks);
		
		JLabel lblPatientDetails = new JLabel("Patient Details");
		lblPatientDetails.setForeground(Color.BLUE);
		lblPatientDetails.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPatientDetails.setBounds(43, 122, 115, 14);
		contentPane.add(lblPatientDetails);
		
		JLabel lblPrescription = new JLabel("Prescription");
		lblPrescription.setForeground(Color.BLUE);
		lblPrescription.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrescription.setBounds(420, 312, 89, 14);
		contentPane.add(lblPrescription);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertData();
			}
		});
		btnSave.setForeground(Color.BLUE);
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSave.setBounds(324, 590, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report();
				clearAllTextField();
			}

			
		});
		
		btnPrint.setForeground(Color.BLUE);
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPrint.setBounds(419, 590, 89, 23);
		contentPane.add(btnPrint);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(210, 105, 30));
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSearch.setBounds(758, 108, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel labelRx = new JLabel("");
		labelRx.setBounds(44, 72, 54, 44);
		contentPane.add(labelRx);
		Image potato=new ImageIcon(this.getClass().getResource("/rx.png")).getImage();
		labelRx.setIcon(new ImageIcon(potato));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(SystemColor.inactiveCaption);
		panel_3.setBounds(37, 331, 378, 231);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		 textAreaMedicalTest = new JTextArea();
		textAreaMedicalTest.setBounds(10, 34, 192, 141);
		panel_3.add(textAreaMedicalTest);
		
		JLabel lblMedicalTest = new JLabel("Medical Test");
		lblMedicalTest.setBounds(10, 15, 89, 14);
		panel_3.add(lblMedicalTest);
		lblMedicalTest.setForeground(Color.BLUE);
		lblMedicalTest.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		 textAreaInvestigation = new JTextArea();
		textAreaInvestigation.setBounds(212, 34, 156, 141);
		panel_3.add(textAreaInvestigation);
		
		JLabel lblInvestigation = new JLabel("Investigation");
		lblInvestigation.setForeground(Color.BLUE);
		lblInvestigation.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInvestigation.setBounds(214, 16, 89, 14);
		panel_3.add(lblInvestigation);
		
		JLabel lblBananidhaka = new JLabel("Banani, Dhaka");
		lblBananidhaka.setForeground(Color.BLACK);
		lblBananidhaka.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblBananidhaka.setBounds(317, 59, 125, 31);
		contentPane.add(lblBananidhaka);
	}
	protected void report() {
		// TODO Auto-generated method stub
		try{
			JasperDesign jd=JRXmlLoader.load("C:\\Users\\creativepc\\workspace\\GuiPractice\\pres1.jrxml");
			String patient_id=textFieldPatientID.getText();
			//System.out.print(patient_id);
			//JasperDesign jd=JRXmlLoader.load("C:\\Users\\creativepc\\workspace\\GuiPractice\\shoppingCartReport.jrxml");
			String query="select info.name,info.age,info.sex,info.mobile_no,info.address,his.complaints,his.past_history,his.on_examination,his.investigation,prs.medicine,prs.how_to_take from dr_patients_info info join dr_patients_history his on info.patient_id=his.patient_id join dr_patients_prescription prs on info.patient_id=prs.patient_id where info.patient_id='"+patient_id+"'";
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

	protected void insertData() {
		// TODO Auto-generated method stu
		String query="Insert into DR_PATIENTS_INFO values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
			prStatement.setString(1,textFieldPatientID.getText() );
			prStatement.setString(2,textFieldPatientName.getText() );
			prStatement.setString(3,textFieldPatientAge.getText() );
			prStatement.setString(4,gander );
			prStatement.setString(5,textFieldPatientMobileNo.getText() );
			prStatement.setString(6,textAreaAddress.getText() );
			prStatement.setString(7,((JTextField)dateChooserVisitingDate.getDateEditor().getUiComponent()).getText() );
			prStatement.executeQuery();
		//JOptionPane.showMessageDialog(null, "Successfully Inserted");

		
		
		
		insertHistory();
		insertPrescription();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Duplicate Patient ID");
		}
		
		
		
	}

	private void clearAllTextField() {
		// TODO Auto-generated method stub
		textFieldPatientID.setText("");
		textFieldPatientName.setText("");
		textFieldPatientAge.setText("");
		textFieldPatientMobileNo.setText("");
		textAreaAddress.setText("");
		group.clearSelection();
		dateChooserVisitingDate.setCalendar(null);
		textAreaComplaints.setText("");
		textAreaHistory.setText("");
		textAreaInvestigation.setText("");
		comboBoxMedicineName.setSelectedIndex(0);
		comboBoxTimeInterval.setSelectedIndex(0);
		comboBoxGroup_of_Medicine.setSelectedIndex(0);
		textAreaMedicalTest.setText("");
		
		
		
	}

	private void insertPrescription() {
		// TODO Auto-generated method stub
		String query="Insert into DR_PATIENTS_PRESCRIPTION values(?,?,?)";
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
		prStatement.setString(1,textFieldPatientID.getText() );
		prStatement.setString(2,(String)comboBoxMedicineName.getSelectedItem() );
		prStatement.setString(3,(String)comboBoxTimeInterval.getSelectedItem() );
		prStatement.executeQuery();
		JOptionPane.showMessageDialog(null, "Data Saved Successfully");

	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//JOptionPane.showMessageDialog(null, e);
	}
		
	}

	private void insertHistory() {
		// TODO Auto-generated method stub
		String query="Insert into DR_PATIENTS_HISTORY values(?,?,?,?,?)";
		try {
			PreparedStatement prStatement= connection.prepareStatement(query);
			
		prStatement.setString(1,textFieldPatientID.getText() );
		prStatement.setString(2,textAreaComplaints.getText() );
		prStatement.setString(3,textAreaHistory.getText() );
		prStatement.setString(4,textAreaMedicalTest.getText() );
		prStatement.setString(5,textAreaInvestigation.getText() );
		prStatement.executeQuery();
		
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//JOptionPane.showMessageDialog(null, e);
	}
	}
}
