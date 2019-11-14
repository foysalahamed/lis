import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;


public class StudentsAttendance extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection connection=null;
	 DefaultTableModel model;
	 JDateChooser dateChooserAttn;
	  JComboBox comboBoxYear;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsAttendance frame = new StudentsAttendance();
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
	public StudentsAttendance() {
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 164, 430, 107);
		contentPane.add(scrollPane);
		table = new JTable();
		
		  model = new DefaultTableModel(){

	            private static final long serialVersionUID = 1L;

	           
	            @Override
	            public Class<?> getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return String.class;
	                    case 1:
	                        return String.class;
	                    case 2:
	                        return String.class;
	                    case 3:
	                        return String.class;
	                   
	                    default:
	                        return Boolean.class;
	                }
	            }
	        };
	        table.setModel(model);
	        table.setPreferredScrollableViewportSize(table.getPreferredSize());
			scrollPane.setViewportView(table);

	        getContentPane().add(scrollPane);
	        
	        JButton btnPresent = new JButton("Present");
	        btnPresent.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        		
	        		for(int r=0;r<table.getRowCount();r++){
	        		
	        			String query="insert into LIS_STUDENTS_ATTENDANCE values(?,?,?,?,?,?)";
		        			try {
								PreparedStatement prst=connection.prepareStatement(query);
								prst.setString(1, table.getValueAt(r, 0).toString());
								prst.setString(2, table.getValueAt(r, 1).toString());
								prst.setString(3, table.getValueAt(r, 2).toString());
								prst.setString(4, table.getValueAt(r, 3).toString());		
								prst.setString(5, ((JTextField)dateChooserAttn.getDateEditor().getUiComponent()).getText() );
			        			boolean y=Boolean.valueOf(table.getValueAt(r, 4).toString());
			        			if(y==true){
									prst.setString(6, "P" );

			        			}
			        			else{
									prst.setString(6, "A" );

			        			}
			        			prst.executeQuery();
							} catch (Exception e) {
								// TODO: handle exception
							}
	        			
	        		}
        			JOptionPane.showMessageDialog(null, "Successfully Inerted");
        			clearAllFields();

	        	}
	        });
	        btnPresent.setBounds(382, 272, 89, 23);
	        contentPane.add(btnPresent);
	        
	        JLabel lblNewLabel = new JLabel("Studant Attendance");
	        lblNewLabel.setForeground(new Color(244, 164, 96));
	        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	        lblNewLabel.setBounds(275, 74, 144, 39);
	        contentPane.add(lblNewLabel);
	        
	        JLabel label = new JLabel("London Intelligence School");
	        label.setForeground(new Color(205, 133, 63));
	        label.setFont(new Font("Times New Roman", Font.BOLD, 28));
	        label.setBounds(198, 28, 338, 33);
	        contentPane.add(label);
	        
	        JLabel labellogo = new JLabel("");
	        labellogo.setBounds(146, 11, 50, 68);
	        contentPane.add(labellogo);
	        Image imglogo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
	        labellogo.setIcon(new ImageIcon(imglogo));
	        JLabel lblYear = new JLabel("Year");
	        lblYear.setFont(new Font("Times New Roman", Font.PLAIN, 11));
	        lblYear.setBounds(338, 134, 35, 14);
	        contentPane.add(lblYear);
	        
	        comboBoxYear= new JComboBox();
	        comboBoxYear.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		String yr=comboBoxYear.getSelectedItem().toString();
	        		getStudentsFromDB(yr);
	        	}
	        });
	        comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"---Select---", "Nursery", "Play", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}));
	        comboBoxYear.setFont(new Font("Times New Roman", Font.PLAIN, 11));
	        comboBoxYear.setBounds(372, 130, 99, 23);
	        contentPane.add(comboBoxYear);
	        
	         dateChooserAttn = new JDateChooser();
	        dateChooserAttn.setBounds(239, 133, 95, 20);
	        contentPane.add(dateChooserAttn);
	        
	        JLabel lblDate = new JLabel("Date");
	        lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 11));
	        lblDate.setBounds(198, 139, 35, 14);
	        contentPane.add(lblDate);

		    model.addColumn("STUDENT ID");
		    model.addColumn("ROLL NO");
		    model.addColumn("FIRST NAME");
		    model.addColumn("YEAR");
		    model.addColumn("ATTENDANCE");
		   	
		
		
		
	}

	protected void clearAllFields() {
		// TODO Auto-generated method stub
		comboBoxYear.setSelectedIndex(0);
		dateChooserAttn.setCalendar(null);
		emptymodel();
		
	}

	protected void getStudentsFromDB(String yr) {
		emptymodel();
		//deductLeavingStudents()
		String stuid[]=new String[800];
		int i=0;
		
		String dt=((JTextField)dateChooserAttn.getDateEditor().getUiComponent()).getText();
		String query="select stu_id from LIS_STUDENTS_ATTENDANCE where  attn_status='"+"Leave"+"' and attn_date='"+dt+"' and year='"+yr+"'";
		try{
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet rs=prstatement.executeQuery();
			while(rs.next()){
				stuid[i++]=rs.getString("STU_ID");
				System.out.println("stuid while loop: "+rs.getString("STU_ID"));

			}
			if(i==0){
				stuid[0]="novalue";//no leaving studetns
				System.out.println("stuid: "+stuid[0]);

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int j=0;j<i;j++){
			System.out.println("stuid: "+stuid[j]);
		}
		
		try {
			
			//String query="select info.stu_id,info.student_roll,info.firstname,info.year from lis_student_info info join" +
				//	"LIS_STUDENTS_ATTENDANCE attnsta on info.stu_id=attnsta.stu_id where info.year='"+yr+"' and info.status='"+"active"+"' and attnst.status='"+"Leave"+"'";
			String q="select stu_id,student_roll,firstname,year from LIS_STUDENT_INFO where year='"+yr+"' and status='"+"active"+"'";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet rs=prstatement.executeQuery();
			int c=0,flag=0;
			  while (rs.next()) {
				  for(int j=0;j<i;j++){
					  if(!rs.getString("STU_ID").equals(stuid[j])){
						  System.out.println("no matching "+rs.getString("STU_ID")+" "+stuid[j]);
						  flag=1;
						  break;
					  }
				  }
				  if(i==0)
					  flag=1;
					  if(flag==1){
				  
					    	model.addRow(new Object[0]);
					    	model.setValueAt(rs.getString("STU_ID"), c, 0);
					    	model.setValueAt(rs.getString("STUDENT_ROLL"), c, 1);
					    	
					    	model.setValueAt(rs.getString("FIRSTNAME"), c, 2);
					    	model.setValueAt(rs.getString("YEAR"), c, 3);
		
					    	model.setValueAt(true, c, 4);
					    	c++;
					    	flag=0;
					  }
				  
		
			  }
			
			
		
	} catch (SQLException e) {
		//JOptionPane.showMessageDialog(null, e);
		e.printStackTrace();
	}
			
	}

	private void emptymodel() {
		// TODO Auto-generated method stub
		for(int i=model.getRowCount()-1;i>-1;i--){
			model.removeRow(i);
		}
		
	}
}
        
     
