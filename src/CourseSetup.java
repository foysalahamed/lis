import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import com.lowagie.text.pdf.PRStream;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class CourseSetup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCourseID;
	private JTextField textFieldTitle;
	private JTextField textFieldAuthor;
	private JTextField textFieldEdition;
	Connection connection=null;
	private JTable tableCourse;
	JComboBox comboBoxTeacherID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseSetup frame = new CourseSetup();
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
	public CourseSetup() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setBounds(100, 100, 780, 494);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(127, 55, 532, 282);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(54, 47, 67, 14);
		panel.add(lblCourseId);
		
		textFieldCourseID = new JTextField();
		textFieldCourseID.setBounds(131, 44, 86, 20);
		panel.add(textFieldCourseID);
		textFieldCourseID.setColumns(10);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setColumns(10);
		textFieldTitle.setBounds(319, 44, 86, 20);
		panel.add(textFieldTitle);
		
		JLabel lblTile = new JLabel("Title");
		lblTile.setBounds(242, 47, 67, 14);
		panel.add(lblTile);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setBounds(131, 72, 86, 20);
		panel.add(textFieldAuthor);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(54, 75, 67, 14);
		panel.add(lblAuthor);
		
		textFieldEdition = new JTextField();
		textFieldEdition.setColumns(10);
		textFieldEdition.setBounds(319, 72, 86, 20);
		panel.add(textFieldEdition);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setBounds(242, 75, 67, 14);
		panel.add(lblEdition);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="insert into lis_course values(?,?,?,?,?)";
				try{
					PreparedStatement prst=connection.prepareStatement(query);
					prst.setString(1, textFieldCourseID.getText());
					prst.setString(2, textFieldTitle.getText());
					prst.setString(3, textFieldAuthor.getText());
					prst.setString(4, textFieldEdition.getText());
					prst.setString(5, comboBoxTeacherID.getSelectedItem().toString());
					prst.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfullu Inserted.");
					textFieldCourseID.setText("");
					textFieldTitle.setText("");
					textFieldAuthor.setText("");
					textFieldEdition.setText("");
					comboBoxTeacherID.setSelectedIndex(0);
					prst.close();
					refreshCourseTable();
					
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnSave.setBounds(193, 126, 89, 23);
		panel.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID=textFieldCourseID.getText();				
				String title=textFieldTitle.getText();
				String author=textFieldAuthor.getText();
				String edition=textFieldEdition.getText();
				String t_id=comboBoxTeacherID.getSelectedItem().toString();
				String query="update lis_course set course_name='"+title+"',author='"+author+"',edition='"+edition+"',teacher_id='"+t_id+"' where course_id='"+courseID+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully Updated.");
					refreshCourseTable();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnEdit.setBounds(289, 126, 89, 23);
		panel.add(btnEdit);
		
		JLabel lblCourseSetup = new JLabel("Course Setup");
		lblCourseSetup.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCourseSetup.setBounds(192, 11, 116, 25);
		panel.add(lblCourseSetup);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 177, 445, 94);
		panel.add(scrollPane);
		
		tableCourse = new JTable();
		tableCourse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_DOWN||event.getKeyCode()==KeyEvent.VK_UP){
					getCourse();
				}
			}
		});
		tableCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getCourse();
			}
		});
		scrollPane.setViewportView(tableCourse);
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(54, 100, 67, 14);
		panel.add(lblTeacher);
		
		 comboBoxTeacherID = new JComboBox();
		 comboBoxTeacherID.setModel(new DefaultComboBoxModel(new String[] {"--Select--"}));
		comboBoxTeacherID.setBounds(131, 97, 86, 20);
		panel.add(comboBoxTeacherID);
		setTitle("London Intelligence School");
		refreshCourseTable();
		getTeacherID();

	}

	private void getTeacherID() {
		// TODO Auto-generated method stub
		String query="select emp_id from lis_employee_info where dept_id='"+"Teacher"+"'";
		try{
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			while(rs.next()){
				comboBoxTeacherID.addItem(rs.getString("EMP_ID"));
			}
			
		}catch(Exception e){
			
		}
		
		
	}

	protected void getCourse() {
		// TODO Auto-generated method stub
		try{
			int ro_id=tableCourse.getSelectedRow();
			String courseID=tableCourse.getModel().getValueAt(ro_id, 0).toString();
			String query="select * from  lis_course where  course_id='"+courseID+"' ";
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			//Date date;
			while(result.next()){
				textFieldCourseID.setText(result.getString("COURSE_ID"));
				textFieldTitle.setText(result.getString("COURSE_NAME"));
				textFieldAuthor.setText(result.getString("AUTHOR" ));
				textFieldEdition.setText(result.getString("EDITION"));
				comboBoxTeacherID.setSelectedItem(result.getString("TEACHER_ID"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void refreshCourseTable() {
		// TODO Auto-generated method stub
		String query="select * from lis_course";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			ResultSet rs=prst.executeQuery();
			tableCourse.setModel(DbUtils.resultSetToTableModel(rs));
			prst.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
