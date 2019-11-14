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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmpAttendance extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmpId;
	private JPasswordField passwordFieldPassword;
	Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpAttendance frame = new EmpAttendance();
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
	public EmpAttendance() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
			
				connection=DbConnection.dbconnection();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			}
		setBounds(100, 100, 744, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));
		
		
		JLabel labelttl = new JLabel("");
		labelttl.setBounds(232, 48, 50, 71);
		contentPane.add(labelttl);
		Image logo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		labelttl.setIcon(new ImageIcon(logo));
		
		
		JLabel label_1 = new JLabel("London Intelligence School");
		label_1.setForeground(new Color(210, 105, 30));
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		label_1.setBounds(289, 63, 278, 40);
		contentPane.add(label_1);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblEmployeeId.setBounds(232, 171, 150, 33);
		contentPane.add(lblEmployeeId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblPassword.setBounds(232, 215, 150, 33);
		contentPane.add(lblPassword);
		
		textFieldEmpId = new JTextField();
		textFieldEmpId.setBounds(379, 171, 226, 40);
		contentPane.add(textFieldEmpId);
		textFieldEmpId.setColumns(10);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empLogin();
			}
		});
		passwordFieldPassword.setBounds(379, 215, 226, 40);
		contentPane.add(passwordFieldPassword);
		
		JLabel lblAtrendance = new JLabel("Attendance");
		lblAtrendance.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblAtrendance.setBounds(403, 114, 150, 33);
		contentPane.add(lblAtrendance);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sessionLogout();
			}
		});
		btnLogout.setForeground(new Color(250, 240, 230));
		btnLogout.setBackground(new Color(210, 180, 140));
		btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 32));
		btnLogout.setBounds(457, 280, 226, 55);
		contentPane.add(btnLogout);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				empLogin();
							
			}
		});
		btnLogin.setForeground(new Color(250, 240, 230));
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 32));
		btnLogin.setBackground(new Color(210, 105, 30));
		btnLogin.setBounds(221, 280, 226, 55);
		contentPane.add(btnLogin);
	}
	private void sessionLogout(){
		
		
		String empId=textFieldEmpId.getText();
		DateFormat logTime=new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		DateFormat dat=new SimpleDateFormat("dd-MMM-yy");
		//to_date(?,'yyyy-mm-dd hh24:mi:ss')
		Calendar canl=Calendar.getInstance();
		String logoutTime=(String)logTime.format(canl.getTime());
		String loginDate=(String)dat.format(canl.getTime());
				
		
		if(checkUserIdPassword()){
			if(checkLoggedin()){
	
			String query="update lis_sessions set logout_time=to_date('"+logoutTime+"','dd-mm-yy hh24:mi:ss') where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"' and regular_time=0";
			try {
				 PreparedStatement prst=connection.prepareStatement(query);
				 prst.executeQuery();
				 prst.close();
				JOptionPane.showMessageDialog(null, "Thank You.See You Later.");
				textFieldEmpId.setText(null);
				passwordFieldPassword.setText(null);
				calculatinTotalHourAndOT(empId,loginDate);

			}catch(Exception e){
				//JOptionPane.showMessageDialog(null, e);
				e.printStackTrace();
				
			}
			}else{
				JOptionPane.showMessageDialog(null,  "You haven't loggedin yet");

			}
		}
		else{
			JOptionPane.showMessageDialog(null,  "Mismatch user id and password");

		}
		
				
}



	private void calculatinTotalHourAndOT(String empId, String loginDate) {
		//background calculatin working hour and over time.
				String query1="select to_char(logout_time,'hh24:mi') as outtime,to_char(login_time,'hh24:mi') as intime from lis_sessions where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"' and regular_time=0";
				String inTime = null,outTime=null;
				try {
					 PreparedStatement prst=connection.prepareStatement(query1);
					 ResultSet rs=prst.executeQuery();
					 while(rs.next()){
						 System.out.println("login time:"+rs.getString("intime"));
						 inTime=rs.getString("intime");
						 System.out.println("logout time:"+rs.getString("outtime"));
						 outTime=rs.getString("outtime");
					 }
					
				}catch(Exception e1){
					//JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
					
				}
				SimpleDateFormat format=new SimpleDateFormat("HH:mm");
				Date signout = null,signin = null;
				try {
					 signin=format.parse(inTime);
				} catch (ParseException   e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 signout=format.parse(outTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long dif=((signout.getTime()-signin.getTime())/1000)/60;
				int h=(int)dif/60;
				int m=(int)dif%60;
				double totalWorkingHour=(double)dif/60;
				double overTime=0;
				double basicWorkingHour=8;
				System.out.println("HH:Min"+h+":"+m);
				System.out.println("MiliSec Dif:"+totalWorkingHour);
				if(totalWorkingHour>basicWorkingHour){
					overTime=totalWorkingHour-basicWorkingHour;
					totalWorkingHour=basicWorkingHour;
				}
				
					
				
				String query="update lis_sessions set regular_time='"+totalWorkingHour+"',over_time='"+overTime+"' where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"' and regular_time=0";
				try {
					 PreparedStatement prst=connection.prepareStatement(query);
					 prst.executeQuery();

					JOptionPane.showMessageDialog(null, "Regular time updated");
					 prst.close();
				}catch(Exception e){
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
					
				}

		
	}

	/* OLD SESSION LOGOUT METHOD.
		private void sessionLogout(){
			
			
			String empId=textFieldEmpId.getText();
			DateFormat logTime=new SimpleDateFormat("HH:mm:ss");
			DateFormat dat=new SimpleDateFormat("dd-MMM-yy");

			Calendar canl=Calendar.getInstance();
			String logoutTime=(String)logTime.format(canl.getTime());
			String loginDate=(String)dat.format(canl.getTime());
			

			if(checkUserIdPassword()){
				if(checkLoggedin()){
		
				String query="update lis_sessions set logout_time=to_date('"+logoutTime+"','hh24:mi:ss') where employeeid='"+empId+"' and login_date='"+loginDate+"'";
				try {
					 PreparedStatement prst=connection.prepareStatement(query);
					 prst.executeQuery();
					 prst.close();
					JOptionPane.showMessageDialog(null, "Thank You.See You Later.");
					textFieldEmpId.setText(null);
					passwordFieldPassword.setText(null);
	
				}catch(Exception e){
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
					
				}
				}else{
					JOptionPane.showMessageDialog(null,  "You haven't loggedin yet");

				}
			}
			else{
				JOptionPane.showMessageDialog(null,  "Mismatch user id and password");

			}
	}
*/
	private boolean checkLoggedin() {
		String empId=textFieldEmpId.getText();
		DateFormat logTime=new SimpleDateFormat("HH:mm:ss");
		DateFormat dat=new SimpleDateFormat("dd-MMM-yy");

		Calendar canl=Calendar.getInstance();
		String loginDate=(String)dat.format(canl.getTime());
		String inTime=null,outTime=null;

			try {
				String query="select * from lis_sessions where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"'";
				PreparedStatement prstmnt=connection.prepareStatement(query);
				ResultSet result=prstmnt.executeQuery();
				while(result.next()){
					inTime=result.getString("LOGIN_TIME");
					outTime=result.getString("LOGOUT_TIME");
				
				}
				result.close();
				prstmnt.close();
				if(inTime==null)
					return false;
				else if(inTime!=null && outTime!=null)
					return false;
				else 
					return true;
			} catch (Exception e) {
				// TODO: handle exception
				//JOptionPane.showMessageDialog(null, e);
				e.printStackTrace();
			}			
		return false;
		}

	protected void empLogin() {
		String late;
			if(checkUserIdPassword()){
				if(checkDoubleLogedIn()){
				DateFormat login_date=new SimpleDateFormat("dd-MMM-yy");
				DateFormat loginTime=new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
				
				Calendar canl=Calendar.getInstance();
				String d=(String)login_date.format(canl.getTime());
				String t=(String)loginTime.format(canl.getTime());
				if(!checkLateAttendance()){
					if(checkPresentEmployee()){
						late=null;
					}
					else{
						late="Late";
						JOptionPane.showMessageDialog(null, "Ohhh!!! \nYou are LATE today");
					}
				}
				else{
					late=null;
				}
				//get employee name from employee info table
				String fname=null;
				try {
					String empid=textFieldEmpId.getText();
					String query="select firstname from lis_employee_info where emp_id='"+empid+"'";
					PreparedStatement prstmnt=connection.prepareStatement(query);
					ResultSet result=prstmnt.executeQuery();
					while(result.next()){
						fname=result.getString("FIRSTNAME");
						//System.out.println("firstNmae: "+fname);
					}
				}
					catch (Exception e) {
					e.printStackTrace();
				}

				//
				String query2="insert into lis_sessions values(?,?,to_date(?,'dd-mm-yy hh24:mi:ss'),?,?,?,?)";
				try {
					 PreparedStatement prst=connection.prepareStatement(query2);
					 prst.setString(1, textFieldEmpId.getText());
					 prst.setString(2, fname);
					 prst.setString(3, t);
					 prst.setString(4, "");
					 prst.setString(5, late);
					 prst.setDouble(6, 0);
					 prst.setDouble(7, 0);
					
					 prst.executeQuery();
					 prst.close();
					
					JOptionPane.showMessageDialog(null, "Have a Good Day!!!");
					textFieldEmpId.setText("");
					passwordFieldPassword.setText("");

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				
			}else{
				JOptionPane.showMessageDialog(null, "You allready Loged in.");

			}
			}else{
				JOptionPane.showMessageDialog(null, "Wrong password or employee id,Please try again");
			}
			
		
		
		
	}

	private boolean checkPresentEmployee() {
		String empId=textFieldEmpId.getText();
		DateFormat logTime=new SimpleDateFormat("HH:mm:ss");
		DateFormat dat=new SimpleDateFormat("dd-MMM-yy");

		Calendar canl=Calendar.getInstance();
		String loginDate=(String)dat.format(canl.getTime());
		String inTime=null,outTime=null;
		try {
			String query="select * from lis_sessions where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"'";
			PreparedStatement prstmnt=connection.prepareStatement(query);
			ResultSet result=prstmnt.executeQuery();
			while(result.next()){
				inTime=result.getString("LOGIN_TIME");
				outTime=result.getString("LOGOUT_TIME");					
			}
			result.close();
			prstmnt.close();
			if(inTime!=null){
				return true;
			}
			else 
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}			
	
		return false;
	}

	private boolean checkDoubleLogedIn() {
		String empId=textFieldEmpId.getText();
		DateFormat logTime=new SimpleDateFormat("HH:mm:ss");
		DateFormat dat=new SimpleDateFormat("dd-MMM-yy");

		Calendar canl=Calendar.getInstance();
		String loginDate=(String)dat.format(canl.getTime());
		String inTime=null,outTime=null;
		try {
			String query="select * from lis_sessions where employeeid='"+empId+"' and to_date(login_time,'dd-mm-yy')='"+loginDate+"'";
			PreparedStatement prstmnt=connection.prepareStatement(query);
			ResultSet result=prstmnt.executeQuery();
			while(result.next()){
				inTime=result.getString("LOGIN_TIME");
				outTime=result.getString("LOGOUT_TIME");					
			}
			result.close();
			prstmnt.close();
			if(inTime==null&& outTime==null){
				return true;
			}
			else if(inTime!=null&& outTime!=null){
				return true;
			}
			else
			return false;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}			
	
		return false;
	}

	private boolean checkLateAttendance() {
		
		DateFormat loginTime=new SimpleDateFormat("HH:mm");
		
		Calendar canl=Calendar.getInstance();
		String t=(String)loginTime.format(canl.getTime());
		String houre=t.substring(0, 2);
		String minute=t.substring(3, 5);
		int hh=Integer.parseInt(houre);
		int mm=Integer.parseInt(minute);

		//System.out.print("Time: "+ houre+"-"+minute);
		
		try {
			
			String query="select * from lis_attn_time_settings";
			PreparedStatement prstmnt=connection.prepareStatement(query);
			ResultSet result=prstmnt.executeQuery();
			if(result.next()){
				String aTime=result.getString("LASTTIME");
				String aH=aTime.substring(0, 2);
				String aMn=aTime.substring(3, 5);
				int ah=Integer.parseInt(aH);
				int amn=Integer.parseInt(aMn);
				if(hh<ah){
					System.out.print("Accellent "+ ah+"-"+amn);

					return true;
				}else if(hh==ah && mm<=amn){
					System.out.print("OK");
					return true;

				}else{
					System.out.print("Oh u late");
					return false;

				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return false;
	}

	private boolean checkUserIdPassword() {
		String passwrd=passwordFieldPassword.getText();
		String empId=textFieldEmpId.getText();
		Login login=new Login();
		String m5=login.getMD5Hash(passwrd);
		//System.out.print("M5 password: "+m5);
		try {
			String query="select * from lis_password_settings where employeeid='"+empId+"' and password='"+m5+"'";
			PreparedStatement prstmnt=connection.prepareStatement(query);
			ResultSet result=prstmnt.executeQuery();
			if(result.next()){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return false;
	}
}
