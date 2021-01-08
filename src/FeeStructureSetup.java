import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

public class FeeStructureSetup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFeeCategories;
	private JTextField textFieldFeeAmount;
	Connection connection;
	JComboBox comboBoxFeeOccurance;
	JComboBox comboBoxNameFeeDB;
	JComboBox comboBoxFeeIdDB;
	JCheckBox chckbxYearOne, chckbxNursery, chckbxPlay;
	JCheckBox chckbxYearTwo, chckbxYearThree, chckbxYearFour, chckbxYearFive, chckbxYearSix;
	JCheckBox chckbxAll;
	private JTable tableFeeSetup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeeStructureSetup frame = new FeeStructureSetup();
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
	public FeeStructureSetup() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {

			connection = DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		setBounds(100, 100, 965, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(34, 93, 375, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFeeCategories = new JLabel("Add New Categories");
		lblFeeCategories.setForeground(new Color(205, 133, 63));
		lblFeeCategories.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblFeeCategories.setBounds(78, 21, 190, 28);
		panel.add(lblFeeCategories);

		JLabel lblFeeOccurances = new JLabel("Fee ID");
		lblFeeOccurances.setForeground(new Color(205, 133, 63));
		lblFeeOccurances.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFeeOccurances.setBounds(54, 81, 60, 23);
		panel.add(lblFeeOccurances);

		comboBoxFeeOccurance = new JComboBox();
		comboBoxFeeOccurance.setModel(new DefaultComboBoxModel(
				new String[] { "Select", "Admition", "Exam", "Sports", "Picnic", "Tuition Fee", "Others" }));
		comboBoxFeeOccurance.setBounds(123, 61, 116, 20);
		panel.add(comboBoxFeeOccurance);

		JLabel lblFeeCategories_1 = new JLabel("Fee Name");
		lblFeeCategories_1.setForeground(new Color(205, 133, 63));
		lblFeeCategories_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFeeCategories_1.setBounds(54, 63, 67, 20);
		panel.add(lblFeeCategories_1);

		textFieldFeeCategories = new JTextField();
		textFieldFeeCategories.setBounds(123, 83, 116, 20);
		panel.add(textFieldFeeCategories);
		textFieldFeeCategories.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "Insert into LIS_FEE_CATEGORIES values(?,?)";

				// connection=DbConnection.dbconnection();

				try {
					PreparedStatement prStatement = connection.prepareStatement(query);

					prStatement.setString(1, (String) comboBoxFeeOccurance.getSelectedItem());
					prStatement.setString(2, textFieldFeeCategories.getText());
					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Saved data");
					comboBoxFeeOccurance.setSelectedIndex(0);
					textFieldFeeCategories.setText("");
					comboBoxFeeOccuranceDBSetup();

					prStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Duplicate ID");
				}

			}
		});
		btnAdd.setForeground(new Color(205, 133, 63));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnAdd.setBounds(124, 102, 60, 23);
		panel.add(btnAdd);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxFeeOccurance.setSelectedIndex(0);
				textFieldFeeCategories.setText("");
			}
		});
		btnClear.setForeground(new Color(205, 133, 63));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnClear.setBounds(181, 102, 60, 23);
		panel.add(btnClear);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(417, 93, 375, 310);
		contentPane.add(panel_1);

		JLabel lblFeeSetup = new JLabel("Fee Setup");
		lblFeeSetup.setForeground(new Color(205, 133, 63));
		lblFeeSetup.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblFeeSetup.setBounds(134, 21, 116, 28);
		panel_1.add(lblFeeSetup);

		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "Insert into LIS_FEE_SETUP values(?,?,?,?)";

				// connection=DbConnection.dbconnection();

				try {
					PreparedStatement prStatement = connection.prepareStatement(query);
					prStatement.setString(1, (String) comboBoxFeeIdDB.getSelectedItem());
					prStatement.setString(2, (String) comboBoxNameFeeDB.getSelectedItem());
					prStatement.setString(3, textFieldFeeAmount.getText());
					if (chckbxNursery.isSelected()) {
						prStatement.setString(4, "Nursery");

					}
					if (chckbxPlay.isSelected()) {
						prStatement.setString(4, "Play");

					}

					if (chckbxYearOne.isSelected()) {
						prStatement.setString(4, "One");

					}
					if (chckbxYearTwo.isSelected()) {
						prStatement.setString(4, "Two");

					}
					if (chckbxYearThree.isSelected()) {
						prStatement.setString(4, "Three");

					}
					if (chckbxYearFour.isSelected()) {
						prStatement.setString(4, "Four");

					}
					if (chckbxYearFive.isSelected()) {
						prStatement.setString(4, "Five");

					}
					if (chckbxYearSix.isSelected()) {
						prStatement.setString(4, "Six");

					}
					if (chckbxAll.isSelected()) {
						prStatement.setString(4, "All");

					}

					// prStatement.setString(4,(String)c.getSelectedItem() );

					prStatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully Saved.");
					prStatement.close();
					feeSetupTable();
					clear_fee_setup();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		button.setForeground(new Color(205, 133, 63));
		button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button.setBounds(30, 272, 60, 23);
		panel_1.add(button);

		JButton btnEdit = new JButton("Clear");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear_fee_setup();
			}
		});
		btnEdit.setForeground(new Color(205, 133, 63));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEdit.setBounds(154, 272, 60, 23);
		panel_1.add(btnEdit);

		JLabel lblFee = new JLabel("Fee Amount");
		lblFee.setForeground(new Color(205, 133, 63));
		lblFee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFee.setBounds(29, 111, 85, 20);
		panel_1.add(lblFee);

		comboBoxNameFeeDB = new JComboBox();
		comboBoxNameFeeDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxNameFeeDB.addItem("garbeg");

				String str = comboBoxNameFeeDB.getSelectedItem().toString();
				comboBoxNameFeeDB.removeItem("garbeg");
				comboBoxFeeIdDB.removeAllItems();
				String query = "select distinct FEE_ID from LIS_FEE_CATEGORIES where fee_name='" + str + "'";

				try {
					PreparedStatement prstatement = connection.prepareStatement(query);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						comboBoxFeeIdDB.addItem(result.getString("FEE_ID"));

					}
					prstatement.close();
					result.close();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					// e.printStackTrace();
				}

			}
		});

		comboBoxNameFeeDB.setBounds(128, 66, 110, 20);
		panel_1.add(comboBoxNameFeeDB);

		comboBoxFeeIdDB = new JComboBox();
		comboBoxFeeIdDB.setBounds(128, 89, 110, 20);
		panel_1.add(comboBoxFeeIdDB);

		textFieldFeeAmount = new JTextField();
		textFieldFeeAmount.setColumns(10);
		textFieldFeeAmount.setBounds(129, 112, 110, 20);
		panel_1.add(textFieldFeeAmount);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(30, 155, 210, 105);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		chckbxYearOne = new JCheckBox("Year One");
		chckbxYearOne.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxYearOne.setBounds(26, 33, 75, 20);
		panel_2.add(chckbxYearOne);

		chckbxYearThree = new JCheckBox("Year Three");
		chckbxYearThree.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxYearThree.setBounds(26, 54, 87, 20);
		panel_2.add(chckbxYearThree);

		chckbxYearFive = new JCheckBox("Year Five");
		chckbxYearFive.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxYearFive.setBounds(26, 77, 75, 20);
		panel_2.add(chckbxYearFive);

		chckbxYearTwo = new JCheckBox("Year Two");
		chckbxYearTwo.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxYearTwo.setBounds(115, 32, 75, 20);
		panel_2.add(chckbxYearTwo);

		chckbxYearFour = new JCheckBox("Year Four");
		chckbxYearFour.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxYearFour.setBounds(115, 53, 75, 20);
		panel_2.add(chckbxYearFour);

		chckbxYearSix = new JCheckBox("Year Six");
		chckbxYearSix.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxYearSix.setBounds(115, 76, 75, 20);
		panel_2.add(chckbxYearSix);

		chckbxNursery = new JCheckBox("Nursery");
		chckbxNursery.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxNursery.setBounds(26, 10, 75, 20);
		panel_2.add(chckbxNursery);

		chckbxPlay = new JCheckBox("Play");
		chckbxPlay.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxPlay.setBounds(115, 9, 75, 20);
		panel_2.add(chckbxPlay);

		JLabel lblApplicableFor = new JLabel("Applicable For");
		lblApplicableFor.setForeground(new Color(205, 133, 63));
		lblApplicableFor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblApplicableFor.setBounds(29, 134, 99, 20);
		panel_1.add(lblApplicableFor);

		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String yr = null;
				if (chckbxNursery.isSelected()) {
					yr = "Nursery";
				}
				if (chckbxPlay.isSelected()) {
					yr = "Play";

				}
				if (chckbxYearOne.isSelected()) {
					yr = "One";
				}
				if (chckbxYearTwo.isSelected()) {
					yr = "Two";

				}
				if (chckbxYearThree.isSelected()) {
					yr = "Three";

				}
				if (chckbxYearFour.isSelected()) {
					yr = "Four";

				}
				if (chckbxYearFive.isSelected()) {
					yr = "Five";

				}
				if (chckbxYearSix.isSelected()) {
					yr = "Six";

				}
				if (chckbxAll.isSelected()) {
					yr = "All";

				}

				String feeid = comboBoxFeeIdDB.getSelectedItem().toString();
				double fee = Double.parseDouble(textFieldFeeAmount.getText());
				String query = "update lis_fee_setup set amount='" + fee + "' where fee_id='" + feeid
						+ "' and applicable_for='" + yr + "'";
				try {
					PreparedStatement prst = connection.prepareStatement(query);
					prst.executeQuery();
					prst.close();
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					feeSetupTable();
					clear_fee_setup();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnEdit_1.setForeground(new Color(205, 133, 63));
		btnEdit_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEdit_1.setBounds(92, 272, 60, 23);
		panel_1.add(btnEdit_1);

		JLabel label = new JLabel("Fee ID");
		label.setForeground(new Color(205, 133, 63));
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label.setBounds(30, 86, 60, 23);
		panel_1.add(label);

		JLabel label_1 = new JLabel("Fee Name");
		label_1.setForeground(new Color(205, 133, 63));
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_1.setBounds(30, 68, 67, 20);
		panel_1.add(label_1);
		
		chckbxAll = new JCheckBox("All");
		chckbxAll.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxAll.setBounds(128, 134, 75, 20);
		panel_1.add(chckbxAll);

		JLabel label_3 = new JLabel("London Intelligence School");
		label_3.setForeground(new Color(205, 133, 63));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 28));
		label_3.setBounds(237, 31, 338, 33);
		contentPane.add(label_3);

		JLabel labelTitleLogo = new JLabel("");
		labelTitleLogo.setBounds(185, 14, 50, 68);
		contentPane.add(labelTitleLogo);
		Image title_logo = new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		labelTitleLogo.setIcon(new ImageIcon(title_logo));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 431, 758, 215);
		contentPane.add(scrollPane);

		tableFeeSetup = new JTable();
		scrollPane.setViewportView(tableFeeSetup);
		comboBoxFeeOccuranceDBSetup();
		feeSetupTable();
	}

	private void feeSetupTable() {
		// TODO Auto-generated method stub
		String query = "select * from lis_fee_setup";

		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			tableFeeSetup.setModel(DbUtils.resultSetToTableModel(result));
			prstatement.close();
			result.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}

	protected void clear_fee_setup() {
		// TODO Auto-generated method stub
		comboBoxNameFeeDB.setSelectedIndex(0);
		// comboBoxFeeCategoriesDB.setSelectedIndex(0);
		textFieldFeeAmount.setText("");
		chckbxNursery.setSelected(false);
		chckbxPlay.setSelected(false);
		chckbxYearOne.setSelected(false);
		chckbxYearTwo.setSelected(false);
		chckbxYearThree.setSelected(false);
		chckbxYearFour.setSelected(false);
		chckbxYearFive.setSelected(false);
		chckbxYearSix.setSelected(false);

	}

	private void comboBoxFeeOccuranceDBSetup() {
		// TODO Auto-generated method stub
		comboBoxNameFeeDB.removeAllItems();
		String query = "select distinct FEE_NAME from LIS_FEE_CATEGORIES";

		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			while (result.next()) {
				comboBoxNameFeeDB.addItem(result.getString("FEE_NAME"));

			}
			prstatement.close();
			result.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}
}
