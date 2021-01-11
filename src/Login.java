
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Login {

	private JFrame frame;
	private JTextField textFieldEmployeeID;
	private JPasswordField passwordField;
	Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(isLogedin()){
						String userid=new Employee().getEmployeeID();
						if(userid.equals("admin")){
							new Administrator().setVisible(true);
						}
						else{
							new Transection().setVisible(true);
						}
						
					}
					else{
						Login window = new Login();
						window.frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
			try {
			
			conn=DbConnection.dbconnection();
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
			}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 611, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("New label");
		lblLogo.setBounds(53, 23, 199, 272);
		frame.getContentPane().add(lblLogo);
		

		Image logo=new ImageIcon(this.getClass().getResource("/lis.png")).getImage();
		lblLogo.setIcon(new ImageIcon(logo));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(317, 85, 249, 124);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(10, 29, 46, 14);
		panel.add(lblUserId);
		lblUserId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 54, 59, 14);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		textFieldEmployeeID = new JTextField();
		textFieldEmployeeID.setBounds(66, 26, 156, 20);
		panel.add(textFieldEmployeeID);
		textFieldEmployeeID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
				
				
				
			}

			
		});
		
		passwordField.setBounds(66, 51, 156, 20);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setForeground(new Color(205, 133, 63));
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLogin.setBounds(36, 79, 70, 23);
		panel.add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ForgotPassword f_password=new ForgotPassword();
				f_password.setVisible(true);
			}
		});
		btnForgotPassword.setForeground(new Color(205, 133, 63));
		btnForgotPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnForgotPassword.setBounds(110, 79, 124, 23);
		panel.add(btnForgotPassword);
		
		JLabel lblDevelopedBy = new JLabel("Developed By");
		lblDevelopedBy.setBounds(372, 281, 79, 14);
		frame.getContentPane().add(lblDevelopedBy);
		
		JLabel lblFoysalAhamed = new JLabel("Foysal Ahamed");
		lblFoysalAhamed.setBounds(367, 294, 96, 14);
		frame.getContentPane().add(lblFoysalAhamed);
		
		JLabel lblFoysalhotmailcom = new JLabel("foysal_269@hotmail.com");
		lblFoysalhotmailcom.setBounds(337, 308, 147, 21);
		frame.getContentPane().add(lblFoysalhotmailcom);
	}

	
	public String getMD5Hash(String passwrd) {
		// TODO Auto-generated method stub
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(passwrd.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        System.out.print(sb.toString());
			return sb.toString();

	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
		return null;
	}
public void login(){
		
		
		String employeeID=textFieldEmployeeID.getText();
		String ps=passwordField.getText();
		String md5pass=getMD5Hash(ps);
		//System.out.print("eid:"+employeeID);
		
		
		try {
//			String query="select * from lis_password_settings where employeeid='"+employeeID+"' and password='"+md5pass+"'";
			String query="select * from lis_password_settings where employeeid='"+employeeID+"' and password='"+md5pass+"'";
			PreparedStatement prst=conn.prepareStatement(query);
			ResultSet result=prst.executeQuery();
			if(result.next()){
				session();
				
				frame.dispose();
				if(employeeID.equals("admin")){
					Administrator admin=new Administrator();
				//frame.dispose();
				admin.setVisible(true);
				}
				else{
					new Transection().setVisible(true);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Wrong password or employee id,Please try again");

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}

private void session(){
	String employeeID=textFieldEmployeeID.getText();
	String ps=passwordField.getText();
	String username=null;
	String query1="select firstname from lis_employee_info where emp_id='"+employeeID+"'";
	try {
		PreparedStatement prst= conn.prepareStatement(query1);
		ResultSet rs=prst.executeQuery();
		if(rs.next()){
			 username=rs.getString("FIRSTNAME");
			//System.out.println(username);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	DateFormat loginTime=new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
	DateFormat login_date=new SimpleDateFormat("dd-MMM-yy");

	Calendar canl=Calendar.getInstance();
	String d=(String)login_date.format(canl.getTime());
	String t=(String)loginTime.format(canl.getTime());
	String query="insert into lis_sessions values(?,?,to_date(?,'dd-mm-yy hh24:mi:ss'),?,?,?,?)";
	try {
		 PreparedStatement prst=conn.prepareStatement(query);
		
		 prst.setString(1,employeeID);
		 prst.setString(2, username);
		 prst.setString(3, t);
		 prst.setString(4, "");
		 prst.setString(5, "");
		 prst.setDouble(6, 0);
		 prst.setDouble(7, 0);
		
		 prst.executeQuery();
		 prst.close();
		 //JOptionPane.showMessageDialog(null, "successfully inserted");
		 //keep the employee id as a global variable
		 Employee empid=new Employee();
		 empid.setEmployeeID(employeeID);
		 System.out.println("empLogin:"+empid.getEmployeeID());
		 PrintWriter pr=new PrintWriter("C:\\LIS\\logfile\\login.txt");
		 pr.println("logedin");
		 PrintWriter userPr=new PrintWriter("C:\\LIS\\logfile\\user.txt");
		 userPr.println(empid.getEmployeeID());
		pr.close();
		userPr.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}

private static boolean isLogedin(){
	try {
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream("C:\\LIS\\logfile\\login.txt")));
		BufferedReader brUserID= new BufferedReader(new InputStreamReader(new FileInputStream("C:\\LIS\\logfile\\user.txt")));

		String line,userid;
		while(((line=br.readLine())!=null)&&((userid=brUserID.readLine())!=null)){
			if(line.equals("logedin")){
				 Employee empid=new Employee();
				 empid.setEmployeeID(userid);
				
				//System.out.println("logedin");
				return true;
				
			}
			else return false;
			
		}
	} catch (Exception e) {
		// TODO: handle exception
		JOptionPane.showMessageDialog(null, e);
	}
	return false;
}




}
