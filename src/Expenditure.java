import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Expenditure extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmount;
	private JTextField textFieldType;
	private JTextField textFieldName;
	private JTextField textFieldSlipNo;
	Connection connection;
	JComboBox comboBoxType,comboBoxName;
	JDateChooser dateChooserExpDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Expenditure frame = new Expenditure();
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
	public Expenditure() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
		}
		
		setBounds(100, 100, 656, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.inactiveCaption, 1, true));
		panel.setBounds(50, 63, 233, 165);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="Insert into LIS_EXPENDITURE_SETUP values(?,?)";
				
				
				try {
					PreparedStatement prStatement= connection.prepareStatement(query);
					prStatement.setString(1,textFieldType.getText() );
					prStatement.setString(2,textFieldName.getText() );
					

					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully added new Expenditure");
					textFieldType.setText("");
					textFieldName.setText("");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database connection fault");
				}
				addtypeInCombobox();

			}
		});
		btnAdd.setBounds(46, 101, 66, 23);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldType.setText("");
				textFieldName.setText("");

			}
		});
		btnClear.setBounds(118, 101, 66, 23);
		panel.add(btnClear);
		
		textFieldType = new JTextField();
		textFieldType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldType.setColumns(10);
		textFieldType.setBounds(94, 26, 97, 20);
		panel.add(textFieldType);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(94, 51, 97, 20);
		panel.add(textFieldName);
		
		JLabel label_2 = new JLabel("Type");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_2.setBounds(32, 29, 58, 14);
		panel.add(label_2);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblName.setBounds(32, 54, 58, 14);
		panel.add(lblName);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.inactiveCaption, 1, true));
		panel_1.setBounds(339, 63, 233, 165);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllFields();
			}
		});
		button.setBounds(120, 131, 66, 23);
		panel_1.add(button);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String expDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserExpDate.getDateEditor().getUiComponent()).getText());
				String query="Insert into LIS_EXPENDITURE values(?,?,?,?,?)";
				
				
				try {
					PreparedStatement prStatement= connection.prepareStatement(query);
					prStatement.setString(1,comboBoxType.getSelectedItem().toString() );
					prStatement.setString(2,comboBoxName.getSelectedItem().toString() );
					prStatement.setString(3,textFieldSlipNo.getText() );
					prStatement.setString(4,expDate);
					prStatement.setString(5,textFieldAmount.getText() );
					

					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully added Expenditure");
					clearAllFields();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database connection fault");
				}


			}
		});
		btnSave.setBounds(48, 131, 66, 23);
		panel_1.add(btnSave);
		
		comboBoxType = new JComboBox();
		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxType.addItem("garbeg");
				String typ=comboBoxType.getSelectedItem().toString();
				comboBoxType.removeItem("garbeg");
				try {
					String q="select name from lis_expenditure_setup where type='"+typ+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					comboBoxName.removeAllItems();
					while(result.next()){
						comboBoxName.addItem(result.getString("NAME"));
						
					}	
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
			}
		});
		comboBoxType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxType.setBounds(127, 39, 96, 20);
		panel_1.add(comboBoxType);
		
		 comboBoxName = new JComboBox();
		comboBoxName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxName.setBounds(128, 62, 96, 20);
		panel_1.add(comboBoxName);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldAmount.setBounds(127, 105, 97, 20);
		panel_1.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblType.setBounds(39, 43, 58, 14);
		panel_1.add(lblType);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblName_1.setBounds(39, 68, 58, 14);
		panel_1.add(lblName_1);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblAmount.setBounds(39, 108, 58, 14);
		panel_1.add(lblAmount);
		
		JLabel lblSlipNo = new JLabel("Slip No");
		lblSlipNo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblSlipNo.setBounds(39, 88, 58, 14);
		panel_1.add(lblSlipNo);
		
		textFieldSlipNo = new JTextField();
		textFieldSlipNo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldSlipNo.setColumns(10);
		textFieldSlipNo.setBounds(127, 84, 97, 20);
		panel_1.add(textFieldSlipNo);
		
		 dateChooserExpDate = new JDateChooser();
		dateChooserExpDate.setBounds(128, 11, 95, 20);
		panel_1.add(dateChooserExpDate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblDate.setBounds(39, 11, 58, 14);
		panel_1.add(lblDate);
		
		JLabel lblAddNewItems = new JLabel("Add New Expenditure");
		lblAddNewItems.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAddNewItems.setBounds(93, 32, 164, 24);
		contentPane.add(lblAddNewItems);
		
		JLabel lblExpenditure = new JLabel("Expenditure");
		lblExpenditure.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblExpenditure.setBounds(376, 32, 130, 24);
		contentPane.add(lblExpenditure);
		addtypeInCombobox();
	}

	protected void clearAllFields() {
		// TODO Auto-generated method stub
		dateChooserExpDate.setCalendar(null);
		comboBoxType.setSelectedIndex(0);
		comboBoxName.setSelectedItem(0);
		textFieldSlipNo.setText("");
		textFieldAmount.setText("");
		
	}

	protected void addtypeInCombobox() {
		// TODO Auto-generated method stub
		try {
			String q="select distinct type from lis_expenditure_setup";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			comboBoxType.removeAllItems();
			while(result.next()){
				comboBoxType.addItem(result.getString("TYPE"));
				
			}	
			
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		
	}
}
