import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAnswer;
	private JTextField textFieldEmpID;
	private JPasswordField passwordFieldEmp;
	private JPasswordField passwordFieldEmpCon;
	JComboBox comboBoxQestion;
	private Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetPassword frame = new SetPassword();
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
	public SetPassword() {
		
		try {
			connection=DbConnection.dbconnection();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(44, 43, 364, 159);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblEmployeeId.setBounds(23, 14, 72, 14);
		panel.add(lblEmployeeId);
		
		textFieldAnswer = new JTextField();
		textFieldAnswer.setColumns(10);
		textFieldAnswer.setBounds(125, 114, 218, 20);
		panel.add(textFieldAnswer);
		
		JLabel label_1 = new JLabel("Answer");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(24, 117, 46, 14);
		panel.add(label_1);
		
		JLabel lblQuestions = new JLabel("Questions");
		lblQuestions.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuestions.setBounds(21, 91, 62, 14);
		panel.add(lblQuestions);
		
		comboBoxQestion = new JComboBox();
		comboBoxQestion.setModel(new DefaultComboBoxModel(new String[] {"", "Who is your favourite person?", "What is your favourite place?", "What is your cousines last name?", "Who is your fathers favourite singer? "}));
		comboBoxQestion.setBounds(125, 88, 218, 20);
		panel.add(comboBoxQestion);
		
		JLabel label_3 = new JLabel("Re-Enter Password");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(21, 60, 93, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Password");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(21, 37, 55, 14);
		panel.add(label_4);
		
		textFieldEmpID = new JTextField();
		textFieldEmpID.setColumns(10);
		textFieldEmpID.setBounds(125, 11, 120, 20);
		panel.add(textFieldEmpID);
		
		passwordFieldEmp = new JPasswordField();
		passwordFieldEmp.setBounds(125, 34, 120, 20);
		panel.add(passwordFieldEmp);
		
		passwordFieldEmpCon = new JPasswordField();
		passwordFieldEmpCon.setBounds(125, 57, 120, 20);
		panel.add(passwordFieldEmpCon);
		
		JLabel lblNewLabel = new JLabel("Password Settings");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(44, 22, 134, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String empID=textFieldEmpID.getText();
				String pass=passwordFieldEmp.getText();
				String re_pass=passwordFieldEmpCon.getText();
				String question=(String)comboBoxQestion.getSelectedItem();
				String ans=textFieldAnswer.getText();
				if(!empID.equals("")&&!pass.equals("")&&!re_pass.equals("")&&!question.equals("")&&!ans.equals("")){
					if(pass.equals(re_pass)){
						String r_md5Hashpass=getMD5Hash(pass);
						//System.out.println("password:"+r_md5Hashpass);
						insertDataintoPassword(empID,r_md5Hashpass,question,ans);
					}else{
						JOptionPane.showMessageDialog(null, "Password mix match");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Fill the all text field");
				}
				
				
			}
		});
		btnSave.setForeground(Color.BLUE);
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.setBounds(54, 213, 67, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empID=textFieldEmpID.getText();
				String pass=passwordFieldEmp.getText();
				String re_pass=passwordFieldEmpCon.getText();
				String question=(String)comboBoxQestion.getSelectedItem();
				String ans=textFieldAnswer.getText();
				String r_md5Hashpass=null;
				System.out.println("Q:"+question);
				if(!empID.equals("")&&!pass.equals("")&&!re_pass.equals("")&&!question.equals("")&&!ans.equals("")){
					if(pass.equals(re_pass)){
						 r_md5Hashpass=getMD5Hash(pass);
						//System.out.println("password:"+r_md5Hashpass);
						 
						 String query="update lis_password_settings set password='"+r_md5Hashpass+"',quastions='"+question+"',answer='"+ans+"' where employeeid='"+empID+"'";
							try {
								PreparedStatement prst=connection.prepareStatement(query);
								prst.executeQuery();
								prst.close();
								
								JOptionPane.showMessageDialog(null, "Successfully updated.");
								textFieldEmpID.setText("");
								textFieldAnswer.setText("");
								passwordFieldEmp.setText("");
								passwordFieldEmpCon.setText("");
								comboBoxQestion.setSelectedIndex(0);

							} catch (Exception e) {
								// TODO: handle exception
								//JOptionPane.showMessageDialog(null, e);
								e.printStackTrace();
							}
						 
						
					}else{
						JOptionPane.showMessageDialog(null, "Password mix match");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Fill the all text field");
				}
				
				
			
				
			}
		});
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnUpdate.setBounds(121, 213, 77, 23);
		contentPane.add(btnUpdate);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empId=JOptionPane.showInputDialog(null, "Employee ID","Search Employee",JOptionPane.QUESTION_MESSAGE);
				//System.out.println("employee ID:"+empId);
				String query="select * from lis_password_settings where employeeid='"+empId+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet result=prst.executeQuery();
					while(result.next()){
						textFieldEmpID.setText(result.getString("EMPLOYEEID"));
						passwordFieldEmp.setText(result.getString("PASSWORD"));
						passwordFieldEmpCon.setText(result.getString("PASSWORD"));
						textFieldAnswer.setText(result.getString("ANSWER"));
						String questions=result.getString("QUASTIONS");
						
						comboBoxQestion.setSelectedItem(questions);
						
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSearch.setForeground(Color.BLUE);
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSearch.setBounds(197, 213, 77, 23);
		contentPane.add(btnSearch);
	}
	private String getMD5Hash(String passwrd) {
		// TODO Auto-generated method stub
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(passwrd.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        //System.out.print(sb.toString());
			return sb.toString();

	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
		return null;
	}
	private void insertDataintoPassword(String empid,String md5HashPassword,String qestion,String ans){
		String query="insert into lis_password_settings values(?,?,?,?)";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			prst.setString(1,empid);
			prst.setString(2,md5HashPassword );
			prst.setString(3,qestion);
			prst.setString(4,ans);
			prst.executeQuery();
			prst.close();
			JOptionPane.showMessageDialog(null, "Successfully Saved.");
			textFieldEmpID.setText("");
			passwordFieldEmp.setText("");
			passwordFieldEmpCon.setText("");
			comboBoxQestion.setSelectedIndex(0);
			textFieldAnswer.setText("");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
