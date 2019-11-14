import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


public class PermissionForLeaves extends JFrame {

	private JPanel contentPane;
	private JTable tableApp;
	JButton btnNotify ;
	Connection connection=null;
	String id_fromtable;
	
	JTextArea textAreaReason;
	JDateChooser dateChooserFrom,dateChooserTo;
	String applicationID;
	private JTextField textFieldID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PermissionForLeaves frame = new PermissionForLeaves();
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
	public PermissionForLeaves() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		setBounds(100, 100, 798, 635);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setBounds(47, 50, 523, 249);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblFrom.setBounds(20, 62, 46, 14);
		panel.add(lblFrom);
		
		 dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(110, 58, 95, 20);
		panel.add(dateChooserFrom);
		
		 dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(303, 58, 95, 20);
		panel.add(dateChooserTo);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblTo.setBounds(245, 62, 46, 14);
		panel.add(lblTo);
		
		JLabel lblDescriptions = new JLabel("Descriptions");
		lblDescriptions.setBackground(Color.BLUE);
		lblDescriptions.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDescriptions.setBounds(20, 87, 82, 14);
		panel.add(lblDescriptions);
		
		 textAreaReason = new JTextArea();
		 textAreaReason.setBackground(new Color(245, 222, 179));
		textAreaReason.setBounds(74, 102, 387, 94);
		panel.add(textAreaReason);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String appFrom=((JTextField)dateChooserFrom.getDateEditor().getUiComponent()).getText();
				String appTo=((JTextField)dateChooserTo.getDateEditor().getUiComponent()).getText();

				String query="update LIS_APP_FOR_LEAVE set app_from='"+appFrom+"', app_to='"+appTo+"',status='"+"Accepted"+"' where app_id='"+applicationID+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					insertLeaveInfoIntoEmpAttnTable(empStuId,category,appFrom,appTo);

					dateChooserFrom.setCalendar(null);
					dateChooserTo.setCalendar(null);
					textAreaReason.setText("");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnAccept.setBounds(142, 207, 89, 23);
		panel.add(btnAccept);
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String appFrom=((JTextField)dateChooserFrom.getDateEditor().getUiComponent()).getText();
				String appTo=((JTextField)dateChooserTo.getDateEditor().getUiComponent()).getText();

				String query="update LIS_APP_FOR_LEAVE set app_from='"+appFrom+"', app_to='"+appTo+"',status='"+"Declined"+"' where app_id='"+applicationID+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.executeQuery();
					JOptionPane.showMessageDialog(null, "Application Declined");
					dateChooserFrom.setCalendar(null);
					dateChooserTo.setCalendar(null);
					textAreaReason.setText("");
					//refreshAppTable();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnDecline.setForeground(Color.RED);
		btnDecline.setBounds(238, 207, 89, 23);
		panel.add(btnDecline);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 355, 523, 126);
		contentPane.add(scrollPane);
		
		tableApp = new JTable();
		tableApp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_UP||event.getKeyCode()==KeyEvent.VK_DOWN){
					getDataintoAppTable();
				}
			}
		});
		tableApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getDataintoAppTable();
					
			}
		});
		scrollPane.setViewportView(tableApp);
		tableApp.setBackground(SystemColor.control);
		
		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="select * from LIS_APP_FOR_LEAVE ";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet rs=prst.executeQuery();
					tableApp.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		button.setBounds(454, 332, 89, 23);
		contentPane.add(button);
		
		textFieldID = new JTextField();
		textFieldID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String sid=textFieldID.getText();
				String query="select * from LIS_APP_FOR_LEAVE where id='"+sid+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet rs=prst.executeQuery();
					tableApp.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		});
		textFieldID.setBounds(82, 333, 86, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblId.setBounds(57, 336, 32, 14);
		contentPane.add(lblId);
		
		JLabel lblApproval = new JLabel("Approval ");
		lblApproval.setForeground(new Color(255, 228, 181));
		lblApproval.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblApproval.setBounds(61, 21, 134, 14);
		contentPane.add(lblApproval);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(51, 19, 672, 20);
		contentPane.add(label_1);
		Image imga=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		label_1.setIcon(new ImageIcon(imga));
		
		
		JLabel lblApplicationTable = new JLabel("Application Table");
		lblApplicationTable.setForeground(new Color(255, 228, 181));
		lblApplicationTable.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblApplicationTable.setBounds(57, 303, 134, 14);
		contentPane.add(lblApplicationTable);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(47, 301, 672, 20);
		contentPane.add(label_2);
		Image imgat=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		label_2.setIcon(new ImageIcon(imgat));
		
		 btnNotify = new JButton("");
		 btnNotify.setBackground(new Color(230, 230, 250));
		 btnNotify.setBounds(497, 19, 46, 20);
		 contentPane.add(btnNotify);
		 btnNotify.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		getNewAppIntoTable();
		 	}
		 });
		 Thread thread = new Thread() {
		      public void run(){
		    	  while(true){
		    		 String query="select count(status) as nottify from LIS_APP_FOR_LEAVE where status='"+"New"+"' ";
				  		  try {
								PreparedStatement prst= connection.prepareStatement(query);
								ResultSet rs =prst.executeQuery();
								if(rs.next()){
									String v=rs.getString("nottify");
									btnNotify.setText(v);
									btnNotify.setForeground(Color.red);
									sleep(30000);
								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
		    	  }
		    	  
		      }
		     
		      
		   };
		   thread.start();
		


		   
		
	}
	String empStuId,category;

	protected void getDataintoAppTable() {
		int roid=tableApp.getSelectedRow();
		  applicationID=tableApp.getModel().getValueAt(roid, 0).toString();
		  empStuId=tableApp.getModel().getValueAt(roid, 2).toString();
		  category=tableApp.getModel().getValueAt(roid, 1).toString();

		  //System.out.println("id,ca: "+empStuId+" "+category);
		
		 String query="select * from LIS_APP_FOR_LEAVE where app_id='"+applicationID+"'";
		  try {
				PreparedStatement prst= connection.prepareStatement(query);
				ResultSet rs =prst.executeQuery();
				if(rs.next()){
					dateChooserFrom.setDate(rs.getDate("APP_FROM"));
					dateChooserTo.setDate(rs.getDate("APP_TO"));
					textAreaReason.setText(rs.getString("REASON"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
	}
	private String stuName,roll,year;
	protected void insertLeaveInfoIntoEmpAttnTable(String empStuId, String category,String appFrom,String appTo) {
		
		if(category.equals("Student")){
			getStudentsDetails(empStuId);
			InBetweenDates nDays=new InBetweenDates();
			int i=nDays.getNoOfDays(appFrom,appTo)+1;
			//System.out.println("Def day: "+i);
			
			
			String dt1;// = appFrom;  // Start date
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar c1 = Calendar.getInstance();
			dt1 = sdf1.format(c1.getTime()).toString();  // dt is now the new date
			InBetweenDates nD=new InBetweenDates();
			int d=nD.getNoOfDays(dt1,appFrom);
		//	System.out.println("Todays date: "+dt1);
			
			
			for(int j=1;j<=i;j++){
				String dt;// = appFrom;  // Start date
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, j+d-1 );  // number of days to add
				dt = sdf.format(c.getTime());  // dt is now the new date
				System.out.println("date: "+dt);
				

				//String query="insert into LIS_STUDENTS_ATTENDANCE values(?,?,?,?,to_date(?,'yyyy-MM-dd hh:mi:ss'),?)";
				String query="insert into LIS_STUDENTS_ATTENDANCE values(?,?,?,?,to_date(?,'dd-MM-yyyy'),?)";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.setString(1, empStuId);
					prst.setString(2, roll);
					prst.setString(3, stuName);
					prst.setString(4, year);
					prst.setString(5, dt);
					prst.setString(6, "Leave");

					ResultSet rs=prst.executeQuery();
					//tableApp.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					
				}		
				


				
			}
					
		}
		else {
			//this block for any other staff including teachers;
			//getStudentsDetails(empStuId);
			InBetweenDates nDays=new InBetweenDates();
			int i=nDays.getNoOfDays(appFrom,appTo)+1;
			//System.out.println("Def day: "+i);
			
			
			String dt1;// = appFrom;  // Start date
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar c1 = Calendar.getInstance();
			dt1 = sdf1.format(c1.getTime()).toString();  // dt is now the new date
			InBetweenDates nD=new InBetweenDates();
			int d=nD.getNoOfDays(dt1,appFrom);
		//	System.out.println("Todays date: "+dt1);
			
			
			for(int j=1;j<=i;j++){
				String dt;// = appFrom;  // Start date
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, j+d-1 );  // number of days to add
				dt = sdf.format(c.getTime());  // dt is now the new date
				System.out.println("date: "+dt);
				

				//String query="insert into LIS_STUDENTS_ATTENDANCE values(?,?,?,?,to_date(?,'yyyy-MM-dd hh:mi:ss'),?)";
				String query="insert into LIS_SESSIONS values(?,?,to_date(?,'dd-MM-yyyy'),?,?,?)";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.setString(1, empStuId);
					prst.setString(2, "");
					prst.setString(3, dt);
					prst.setString(4, "");
					prst.setString(5, "");
					prst.setString(6, "Leave");

					ResultSet rs=prst.executeQuery();
					//tableApp.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					
				}		
				


				
			}
					

			
		}
	}
	

	

	private void getStudentsDetails(String stuid) {
		 String query="select * from LIS_STUDENT_INFO where stu_id='"+stuid+"'";
		  try {
				PreparedStatement prst= connection.prepareStatement(query);
				ResultSet rs =prst.executeQuery();
				if(rs.next()){
					stuName=rs.getString("FIRSTNAME");	
					roll=rs.getString("STUDENT_ROLL");					
					year=rs.getString("YEAR");					

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
	}
	int count=0;
		// TODO Auto-generated method stub
		private void getFile(String dirPath) {
		    File f = new File(dirPath);
		    File[] files = f.listFiles();

		    if (files != null)
		    for (int i = 0; i < files.length; i++) {
		        count++;
		        File file = files[i];

		        if (file.isDirectory()) {   
		             getFile(file.getAbsolutePath()); 
		        }
		    }
		    System.out.println("Total No of File: "+count );
		    count=0;
		}
		
	

	protected void getNewAppIntoTable() {
		// TODO Auto-generated method stub
		String query="select * from LIS_APP_FOR_LEAVE where status='"+"New"+"'";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			tableApp.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
