import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class TransportFeeSetup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRoutID;
	private JTextField textFieldRoutName;
	private JTextField textFieldEmpID;
	private JTextField textFieldAmount;
	private JTextField textFieldVehicleNo;
	Connection connection;
	JComboBox comboBoxSearch;
	JComboBox comboBoxSearchBy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransportFeeSetup frame = new TransportFeeSetup();
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
	public TransportFeeSetup() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Database Connection Fault.");
		}
		setBounds(100, 100, 745, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(139, 53, 464, 307);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldRoutID = new JTextField();
		textFieldRoutID.setBounds(118, 141, 86, 20);
		panel.add(textFieldRoutID);
		textFieldRoutID.setColumns(10);
		
		JLabel lblRoutId = new JLabel("Rout ID");
		lblRoutId.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblRoutId.setBounds(46, 144, 46, 14);
		panel.add(lblRoutId);
		
		JLabel lblRoutName = new JLabel("Rout Name");
		lblRoutName.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblRoutName.setBounds(46, 166, 62, 14);
		panel.add(lblRoutName);
		
		textFieldRoutName = new JTextField();
		textFieldRoutName.setColumns(10);
		textFieldRoutName.setBounds(118, 163, 86, 20);
		panel.add(textFieldRoutName);
		
		JLabel lblDriverId = new JLabel("Employee ID");
		lblDriverId.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDriverId.setBounds(46, 188, 62, 14);
		panel.add(lblDriverId);
		
		textFieldEmpID = new JTextField();
		textFieldEmpID.setColumns(10);
		textFieldEmpID.setBounds(118, 185, 86, 20);
		panel.add(textFieldEmpID);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblAmount.setBounds(242, 168, 46, 14);
		panel.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(314, 165, 86, 20);
		panel.add(textFieldAmount);
		
		JLabel lblVehicleNo = new JLabel("Vehicle No");
		lblVehicleNo.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblVehicleNo.setBounds(242, 146, 62, 14);
		panel.add(lblVehicleNo);
		
		textFieldVehicleNo = new JTextField();
		textFieldVehicleNo.setColumns(10);
		textFieldVehicleNo.setBounds(314, 143, 86, 20);
		panel.add(textFieldVehicleNo);
		
		JButton btnAdd = new JButton("EDIT");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String searchItem=comboBoxSearch.getSelectedItem().toString();
				String srchby=comboBoxSearchBy.getSelectedItem().toString();
				
				String query="update LIS_TRANSPORT_FEE_SETUP set route_id='"+textFieldRoutID.getText()+"',route_name='"+textFieldRoutName.getText()+"',emp_id='"+textFieldEmpID.getText()+"',vehichle_no='"+textFieldVehicleNo.getText()+"',transport_amount='"+textFieldAmount.getText()+"' where "+searchItem+"='"+srchby+"'";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.executeQuery(query);
					prstatement.close();
					JOptionPane.showMessageDialog(null, "Successfull Updated");
					clearAllField();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnAdd.setBounds(208, 225, 89, 23);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllField();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnClear.setForeground(Color.RED);
		btnClear.setBounds(307, 225, 89, 23);
		panel.add(btnClear);
		
		JLabel lblTransportFeeSetup = new JLabel("Transport Fee Setup");
		lblTransportFeeSetup.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTransportFeeSetup.setBounds(158, 11, 160, 23);
		panel.add(lblTransportFeeSetup);
		
		JButton button = new JButton("ADD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="Insert into LIS_TRANSPORT_FEE_SETUP values(?,?,?,?,?)";
				
				
				try {
					PreparedStatement prStatement= connection.prepareStatement(query);
					prStatement.setString(1,textFieldRoutID.getText() );
					prStatement.setString(2,textFieldRoutName.getText() );
					prStatement.setString(3,textFieldEmpID.getText() );
					prStatement.setString(4,textFieldVehicleNo.getText() );
					prStatement.setString(5,textFieldAmount.getText() );

					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully added new rout");
					clearAllField();
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database connection fault");
				}

				
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		button.setBounds(115, 225, 89, 23);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(46, 53, 368, 65);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sItem=comboBoxSearch.getSelectedItem().toString();
		   		comboBoxSearchBy.removeAllItems();
				
				try {
					//String q="select stu_id from lis_student_info where year='"+yr+"' and section='"+sec+"'";
					String q="select "+sItem+" from LIS_TRANSPORT_FEE_SETUP";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					while(result.next()){
						if(sItem.equals("ROUTE_ID")){
							comboBoxSearchBy.addItem(result.getString("ROUTE_ID"));

						}
						else if(sItem.equals("ROUTE_NAME")){
							comboBoxSearchBy.addItem(result.getString("ROUTE_NAME"));

						}
						else if(sItem.equals("EMP_ID")){
							comboBoxSearchBy.addItem(result.getString("EMP_ID"));

						}
						else if(sItem.equals("VEHICHLE_NO")){
							comboBoxSearchBy.addItem(result.getString("VEHICHLE_NO"));

						}
						
						
					}	
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			

				
			}
		});
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"Select", "ROUTE_ID", "ROUTE_NAME", "EMP_ID", "VEHICHLE_NO"}));
		comboBoxSearch.setBounds(99, 11, 100, 20);
		panel_1.add(comboBoxSearch);
		
		comboBoxSearchBy = new JComboBox();
		comboBoxSearchBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxSearchBy.addItem("garbeg");
				String searchitem=comboBoxSearch.getSelectedItem().toString();
				String sItemb=comboBoxSearchBy.getSelectedItem().toString();
				comboBoxSearchBy.removeItem("garbeg");
				
				try {
					//String q="select stu_id from lis_student_info where year='"+yr+"' and section='"+sec+"'";
					String q="select * from LIS_TRANSPORT_FEE_SETUP where "+searchitem+"='"+sItemb+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					while(result.next()){
						
							textFieldRoutID.setText(result.getString("ROUTE_ID"));
							textFieldRoutName.setText(result.getString("ROUTE_NAME"));
							textFieldEmpID.setText(result.getString("EMP_ID"));
							textFieldVehicleNo.setText(result.getString("VEHICHLE_NO"));
							textFieldAmount.setText(result.getString("TRANSPORT_AMOUNT"));


						
					}	
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			

				
			}
		});
		comboBoxSearchBy.setBounds(258, 11, 100, 20);
		panel_1.add(comboBoxSearchBy);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblSearch.setBounds(29, 14, 46, 14);
		panel_1.add(lblSearch);
		
		JLabel lblBy = new JLabel("BY");
		lblBy.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblBy.setBounds(209, 14, 46, 14);
		panel_1.add(lblBy);
	}

	protected void clearAllField() {
		// TODO Auto-generated method stub
		textFieldRoutID.setText("");
		textFieldRoutName.setText("");
		textFieldEmpID.setText("");
		textFieldVehicleNo.setText("");
		textFieldAmount.setText("");
		comboBoxSearch.setSelectedIndex(0);
		//comboBoxSearchBy.setSelectedIndex(0);
		
	}
}
