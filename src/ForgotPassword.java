import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.print.attribute.standard.PresentationDirection;
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
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmpID;
	private JTextField textFieldFPAns;
	private JPasswordField passwordFieldFP;
	private JPasswordField passwordFieldFPRenter;
	private JPanel panel_1;
	JComboBox comboBoxFPQuestion;
	private Connection connection;
	private String empID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			connection=DbConnection.dbconnection();
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			// TODO: handle exception
		}
		setBounds(100, 100, 752, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(54, 34, 353, 199);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Employee ID");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label.setBounds(18, 64, 72, 14);
		panel.add(label);
		
		textFieldEmpID = new JTextField();
		textFieldEmpID.setColumns(10);
		textFieldEmpID.setBounds(105, 61, 180, 20);
		panel.add(textFieldEmpID);
		
		JLabel label_1 = new JLabel("Answer");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(19, 117, 46, 14);
		panel.add(label_1);
		
		JLabel lblQuestions = new JLabel("Questions");
		lblQuestions.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuestions.setBounds(16, 91, 62, 14);
		panel.add(lblQuestions);
		
		comboBoxFPQuestion = new JComboBox();
		comboBoxFPQuestion.setModel(new DefaultComboBoxModel(new String[] {"", "Who is your favourite person?", "What is your favourite place?", "What is your cousines last name?", "Who is your fathers favourite singer? "}));
		comboBoxFPQuestion.setBounds(105, 88, 226, 20);
		panel.add(comboBoxFPQuestion);
		
		textFieldFPAns = new JTextField();
		textFieldFPAns.setColumns(10);
		textFieldFPAns.setBounds(105, 114, 226, 20);
		panel.add(textFieldFPAns);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empid=textFieldEmpID.getText();
				String question=comboBoxFPQuestion.getSelectedItem().toString();
				String ans=textFieldFPAns.getText();
				String query="select * from lis_password_settings where employeeid='"+empid+"' and  quastions='"+question+"' and answer='"+ans+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet result=prst.executeQuery();
					if(result.next()){
						panel_1.setVisible(true);	
						empID=empid;
					}
					else{
						JOptionPane.showMessageDialog(null, "Your secret answer is wrong. please try again");
					}
					prst.close();
					result.close();
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnResetPassword.setForeground(Color.BLUE);
		btnResetPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnResetPassword.setBounds(156, 145, 129, 23);
		panel.add(btnResetPassword);
		
		JLabel lblForgotPassword = new JLabel("Forgot Password");
		lblForgotPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblForgotPassword.setBounds(54, 11, 117, 14);
		contentPane.add(lblForgotPassword);
		
		 panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(417, 34, 282, 199);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		passwordFieldFP = new JPasswordField();
		passwordFieldFP.setBounds(109, 57, 108, 20);
		panel_1.add(passwordFieldFP);
		
		passwordFieldFPRenter = new JPasswordField();
		passwordFieldFPRenter.setBounds(110, 81, 108, 20);
		panel_1.add(passwordFieldFPRenter);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPassword.setBounds(10, 60, 69, 14);
		panel_1.add(lblPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter password");
		lblReenterPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblReenterPassword.setBounds(10, 88, 103, 14);
		panel_1.add(lblReenterPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pw=passwordFieldFP.getText();
				String repw=passwordFieldFPRenter.getText();
				
				if(!pw.equals("")&&!repw.equals("")){
					if(pw.equals(repw)){
						String md5hash=getMD5Hash(pw);
						String query="update lis_password_settings set password='"+md5hash+"' where employeeid='"+empID+"'";
						try {
							PreparedStatement prst=connection.prepareStatement(query);
							prst.executeQuery();
							prst.close();
							JOptionPane.showMessageDialog(null, "Successfully saved your new password");
							textFieldEmpID.setText("");
							textFieldFPAns.setText("");
							comboBoxFPQuestion.setSelectedIndex(0);
							passwordFieldFP.setText("");
							passwordFieldFPRenter.setText("");
							
						} catch (Exception e) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, e);
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "Mix match password");

					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Fill all password fields");
				}
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.setBounds(120, 112, 77, 23);
		panel_1.add(btnSave);
		
		JLabel lblEnterNewPassword = new JLabel("Enter new  password");
		lblEnterNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEnterNewPassword.setBounds(77, 24, 140, 14);
		panel_1.add(lblEnterNewPassword);
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
}
