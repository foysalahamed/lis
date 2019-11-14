import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;


public class FineSetup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmount;
	Connection connection;
	JComboBox comboBoxFineType;
	private JTable tableFine;
	private JTextField textFieldStID;
	JLabel labelName;
	JComboBox comboBoxMonth;
	JCheckBox chckbxFree;
	private JTable tableFineAllownces;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FineSetup frame = new FineSetup();
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
	public FineSetup() {
		
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 24, 277, 329);
		contentPane.add(panel);
		panel.setLayout(null);
		
		 comboBoxFineType = new JComboBox();
		comboBoxFineType.setModel(new DefaultComboBoxModel(new String[] {"Select", "LateFee", "Absence"}));
		comboBoxFineType.setBounds(72, 81, 101, 20);
		panel.add(comboBoxFineType);
		
		JLabel lblNewLabel = new JLabel("Fine Type");
		lblNewLabel.setBounds(8, 84, 55, 14);
		panel.add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(72, 105, 101, 20);
		panel.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(8, 109, 55, 14);
		panel.add(lblAmount);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String query="Insert into LIS_FINE_SETUP values(?,?)";
				
				//connection=DbConnection.dbconnection();
				
				try {
					PreparedStatement prStatement= connection.prepareStatement(query);
					
				prStatement.setString(1,comboBoxFineType.getSelectedItem().toString() );
				prStatement.setDouble(2,Double.parseDouble(textFieldAmount.getText()) );

				prStatement.executeQuery();
				
				//clear textfield
				comboBoxFineType.setSelectedIndex(0);
				textFieldAmount.setText("");
				JOptionPane.showMessageDialog(null, "Saved.");
				refresh();
				
			
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Fine Setup Problem");
				}

			}
		});
		btnSave.setBounds(8, 145, 67, 23);
		panel.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type=comboBoxFineType.getSelectedItem().toString();
				double amnt=Double.parseDouble(textFieldAmount.getText());
				String query="Update lis_fine_setup set amount='"+amnt+"' where fine_type='"+type+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					prst.executeQuery();
					prst.close();
					JOptionPane.showMessageDialog(null, "Updated");
					refresh();
					comboBoxFineType.setSelectedIndex(0);
					textFieldAmount.setText("");
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnEdit.setBounds(75, 145, 67, 23);
		panel.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 195, 196, 52);
		panel.add(scrollPane);
		
		tableFine = new JTable();
		scrollPane.setViewportView(tableFine);
		
		JLabel lblFineS = new JLabel("Fine Setup");
		lblFineS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFineS.setBounds(72, 37, 131, 28);
		panel.add(lblFineS);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(297, 24, 329, 329);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textFieldStID = new JTextField();
		textFieldStID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String id=textFieldStID.getText();
				String query="select firstname,lastname from lis_student_info where stu_id='"+id+"'";
				try {
					PreparedStatement prst=connection.prepareStatement(query);
					ResultSet result=prst.executeQuery();
					while(result.next()){
						String fn=result.getString("FIRSTNAME");
						String ln=result.getString("LASTNAME");
						labelName.setText(fn+" "+ln);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		textFieldStID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				}
		});
		textFieldStID.setBounds(87, 103, 86, 20);
		panel_1.add(textFieldStID);
		textFieldStID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID");
		lblNewLabel_1.setBounds(7, 106, 70, 14);
		panel_1.add(lblNewLabel_1);
		
		 comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "Jun", "Julay", "August", "September", "October", "November", "December"}));
		comboBoxMonth.setBounds(234, 103, 89, 20);
		panel_1.add(comboBoxMonth);
		
		 chckbxFree = new JCheckBox("Free");
		chckbxFree.setBounds(88, 150, 97, 23);
		panel_1.add(chckbxFree);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fre=null;
				String stu_id=textFieldStID.getText();
				String mnth=comboBoxMonth.getSelectedItem().toString();
				if (chckbxFree.isSelected()) {
					 fre="free";
				}
				String query="Insert into LIS_F_ALLOWNCES values(?,?,?)";
				
				//connection=DbConnection.dbconnection();
				
				try {
					PreparedStatement prStatement= connection.prepareStatement(query);
					
					prStatement.setString(1,stu_id);
					prStatement.setString(2,mnth );
					prStatement.setString(3,fre);

					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Saved.");

					//clear textfield
					comboBoxMonth.setSelectedIndex(0);
					textFieldStID.setText("");
					labelName.setText("");
					chckbxFree.setSelected(false);
					refresh2();
				
			
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Fine Setup Problem");
				}
			}
		});
		btnNewButton.setBounds(16, 185, 89, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(184, 106, 43, 14);
		panel_1.add(lblMonth);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(8, 132, 43, 14);
		panel_1.add(lblName);
		
		labelName = new JLabel("");
		labelName.setBounds(87, 132, 170, 14);
		panel_1.add(labelName);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 218, 301, 91);
		panel_1.add(scrollPane_1);
		
		tableFineAllownces = new JTable();
		scrollPane_1.setViewportView(tableFineAllownces);
		
		JLabel lblApprovalSetu = new JLabel("Approval setup");
		lblApprovalSetu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblApprovalSetu.setBounds(70, 45, 131, 28);
		panel_1.add(lblApprovalSetu);
		
		JButton btnDelete = new JButton("Cancel");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cancel="cancel";
				String sid=textFieldStID.getText();
				String query="update LIS_F_ALLOWNCES set allow='"+cancel+"' where stu_id='"+sid+"'";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.executeQuery();
					refresh2();
					prstatement.close();
					comboBoxMonth.setSelectedIndex(0);
					textFieldStID.setText("");
					labelName.setText("");
					chckbxFree.setSelected(false);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(204, 185, 89, 23);
		panel_1.add(btnDelete);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fre=null;
				if(chckbxFree.isSelected()){
					fre="free";
				}
				String sid=textFieldStID.getText();
				String query="update LIS_F_ALLOWNCES set allow='"+fre+"' where stu_id='"+sid+"'";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.executeQuery();
					refresh2();
					prstatement.close();
					comboBoxMonth.setSelectedIndex(0);
					textFieldStID.setText("");
					labelName.setText("");
					chckbxFree.setSelected(false);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnEdit_1.setBounds(108, 185, 89, 23);
		panel_1.add(btnEdit_1);
		refresh();
		refresh2();
	}

	protected void refresh() {
		// TODO Auto-generated method stub
		try {
			String q="select * from lis_fine_setup";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			tableFine.setModel(DbUtils.resultSetToTableModel(result));
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}
	protected void refresh2() {
		// TODO Auto-generated method stub
		try {
			String q="select * from lis_f_allownces";
			PreparedStatement prstatement=connection.prepareStatement(q);
			ResultSet result=prstatement.executeQuery();
			tableFineAllownces.setModel(DbUtils.resultSetToTableModel(result));
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}
}
