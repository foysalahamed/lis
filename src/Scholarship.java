import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Scholarship extends JFrame {

	private JPanel contentPane;
	Connection connection=null;
	private JTextField textField;
	private JTable tableFeeDetails;
	private JTable tableScholarship;
	private JTextField textFieldScholarshipType;
	JComboBox comboBoxPersentage;
	JComboBox comboBoxTimePeriod;
	JTextArea textAreaRemarks;
	private JTextField textFielDScholarshipID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scholarship frame = new Scholarship();
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
	public Scholarship() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setBounds(100, 100, 719, 553);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(103, 74, 554, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSearch = new JLabel("Student ID");
		lblSearch.setForeground(new Color(0, 0, 0));
		lblSearch.setBackground(new Color(106, 90, 205));
		lblSearch.setBounds(49, 25, 82, 14);
		panel.add(lblSearch);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchStudent();
			}
		});
		textField.setBounds(141, 22, 179, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("Search");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchStudent();
				}
		});
		btnOk.setBounds(330, 21, 89, 23);
		panel.add(btnOk);
		
		JLabel lblScholarship = new JLabel("Scholarship");
		lblScholarship.setBounds(49, 61, 82, 14);
		panel.add(lblScholarship);
		
		 comboBoxPersentage = new JComboBox();
		comboBoxPersentage.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		comboBoxPersentage.setBounds(141, 58, 42, 17);
		panel.add(comboBoxPersentage);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(190, 61, 22, 14);
		panel.add(label);
		
		JButton btnOk_1 = new JButton("Save");
		btnOk_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stu_id=textField.getText();
				String schPersentage=comboBoxPersentage.getSelectedItem().toString();
				double sPers=Double.parseDouble(schPersentage);
				String timePeriod=comboBoxTimePeriod.getSelectedItem().toString();
				double tPeriod=Double.parseDouble(timePeriod);
				String remarks=textAreaRemarks.getText();
				String schlrID=textFielDScholarshipID.getText();
				String schType=textFieldScholarshipType.getText();
				String permittedBy=new Employee().getEmployeeID();
				
				String query="Insert into LIS_SCHOLARSHIP_DETAILS values(?,?,?,?,?,?,?)";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1, stu_id);
					prstatement.setString(2, schlrID);					
					prstatement.setString(3,schType );
					prstatement.setDouble(4, sPers);
					prstatement.setDouble(5, tPeriod);
					prstatement.setString(6, remarks);
					prstatement.setString(7, permittedBy);

					prstatement.executeQuery();
					prstatement.close();
					JOptionPane.showMessageDialog(null, "Saved Successfully");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Duplicate Scholarship ID");
				}
			//clearTextField();
			refreshScholarshipTable();
				
			}
		});
		btnOk_1.setBounds(218, 184, 89, 23);
		panel.add(btnOk_1);
		
		textFieldScholarshipType = new JTextField();
		textFieldScholarshipType.setBounds(384, 85, 136, 20);
		panel.add(textFieldScholarshipType);
		textFieldScholarshipType.setColumns(10);
		
		 textAreaRemarks = new JTextArea();
		textAreaRemarks.setColumns(1);
		textAreaRemarks.setBackground(SystemColor.inactiveCaptionBorder);
		textAreaRemarks.setBounds(142, 119, 380, 54);
		panel.add(textAreaRemarks);
		
		JLabel lblScholarshipType = new JLabel("Scholarship Type");
		lblScholarshipType.setBounds(277, 90, 102, 14);
		panel.add(lblScholarshipType);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(49, 125, 66, 14);
		panel.add(lblRemarks);
		
		JLabel lblTimePeriod = new JLabel("Time Period");
		lblTimePeriod.setBounds(224, 61, 68, 14);
		panel.add(lblTimePeriod);
		
		 comboBoxTimePeriod = new JComboBox();
		comboBoxTimePeriod.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxTimePeriod.setBounds(303, 58, 42, 17);
		panel.add(comboBoxTimePeriod);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stu_id=textField.getText();
				String schPersentage=comboBoxPersentage.getSelectedItem().toString();
				double sPers=Double.parseDouble(schPersentage);
				String timePeriod=comboBoxTimePeriod.getSelectedItem().toString();
				double tPeriod=Double.parseDouble(timePeriod);
				String remark=textAreaRemarks.getText();
				String schlrID=textFielDScholarshipID.getText();
				String schType=textFieldScholarshipType.getText();
				String permittedBy=new Employee().getEmployeeID();
				
				
				String query="Update LIS_SCHOLARSHIP_DETAILS set persentage='"+sPers+"',time_period='"+tPeriod+"',remarks='"+remark+"',scholarship_name='"+schType+"' where scholarship_id='"+schlrID+"'" ;
				try{
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Successufully Updated");
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				//clearTextField();
				refreshScholarshipTable();
				
			}
		});
		btnEdit.setBounds(309, 184, 89, 23);
		panel.add(btnEdit);
		
		JLabel lblMonths = new JLabel("Months");
		lblMonths.setBounds(348, 59, 68, 14);
		panel.add(lblMonths);
		
		JLabel lblScholarshipId = new JLabel("Scholarship ID");
		lblScholarshipId.setBounds(33, 92, 89, 14);
		panel.add(lblScholarshipId);
		
		textFielDScholarshipID = new JTextField();
		textFielDScholarshipID.setColumns(10);
		textFielDScholarshipID.setBounds(140, 87, 100, 20);
		panel.add(textFielDScholarshipID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(103, 351, 278, 122);
		contentPane.add(scrollPane);
		
		tableFeeDetails = new JTable();
		scrollPane.setViewportView(tableFeeDetails);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				
			}
		});
		scrollPane_1.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getDataFromScholarshipTable();
			}
		});
		scrollPane_1.setBounds(402, 352, 253, 122);
		contentPane.add(scrollPane_1);
		
		tableScholarship = new JTable();
		tableScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getDataFromScholarshipTable();
			}
		});
		tableScholarship.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_DOWN||event.getKeyCode()==KeyEvent.VK_UP){
					getDataFromScholarshipTable();
				}
			}
		});
		scrollPane_1.setViewportView(tableScholarship);
		
		JLabel lblScholarship_1 = new JLabel("Scholarship");
		lblScholarship_1.setForeground(Color.ORANGE);
		lblScholarship_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblScholarship_1.setBounds(290, 27, 116, 25);
		contentPane.add(lblScholarship_1);
		
		JLabel lblFeeDetails = new JLabel("Fee Details");
		lblFeeDetails.setBackground(SystemColor.inactiveCaption);
		lblFeeDetails.setBounds(103, 331, 278, 14);
		contentPane.add(lblFeeDetails);
		
		JLabel lblScholarship_2 = new JLabel("Scholarship Details");
		lblScholarship_2.setBackground(SystemColor.inactiveCaption);
		lblScholarship_2.setBounds(402, 331, 255, 14);
		contentPane.add(lblScholarship_2);
	}

	protected void refreshScholarshipTable() {
		// TODO Auto-generated method stub
		String stid=textField.getText();
		try {
			String q="select * from LIS_SCHOLARSHIP_DETAILS where stu_id='"+stid+"'";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			tableScholarship.setModel(DbUtils.resultSetToTableModel(result));	
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		clearTextField();
		
	}

	protected void getDataFromScholarshipTable() {
		// TODO Auto-generated method stub
		try{
			int ro_id=tableScholarship.getSelectedRow();
			String schlrID=tableScholarship.getModel().getValueAt(ro_id, 1).toString();
			String query="select * from  LIS_SCHOLARSHIP_DETAILS where  SCHOLARSHIP_ID='"+schlrID+"' ";
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			//Date date;
			while(result.next()){
				textField.setText(result.getString("STU_ID"));
				textFieldScholarshipType.setText(result.getString("SCHOLARSHIP_NAME"));
				textFielDScholarshipID.setText(result.getString("SCHOLARSHIP_ID"));
				textAreaRemarks.setText(result.getString("REMARKS" ));
				String tp=result.getString("TIME_PERIOD");
				int tipIndex=Integer.parseInt(tp);
				comboBoxTimePeriod.setSelectedIndex(tipIndex);
				String prs=result.getString("PERSENTAGE");
				int prsnIndex=Integer.parseInt(prs)/5;
				comboBoxPersentage.setSelectedIndex(prsnIndex);
				
				
			}
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		

		
	}

	protected void clearTextField() {
		// TODO Auto-generated method stub
		textField.setText("");
		comboBoxPersentage.setSelectedIndex(0);
		textFieldScholarshipType.setText("");
		comboBoxTimePeriod.setSelectedIndex(0);
		textAreaRemarks.setText("");
		textFielDScholarshipID.setText("");
		
		
	}

	protected void searchStudent() {
		String stid=textField.getText();
		try {
			String q="select * from LIS_STUDENT_FEE_DETAILS where stu_id='"+stid+"'";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			tableFeeDetails.setModel(DbUtils.resultSetToTableModel(result));	
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		try {
			String q="select * from LIS_SCHOLARSHIP_DETAILS where stu_id='"+stid+"'";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			tableScholarship.setModel(DbUtils.resultSetToTableModel(result));	
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
			
	}
}
