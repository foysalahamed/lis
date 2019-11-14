import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

import org.apache.poi.hssf.record.formula.TblPtg;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JYearChooser;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DueFee extends JFrame {

	private JPanel contentPane;
	Connection connection = null;
	JComboBox comboBoxSection, comboBoxYear, comboBoxStudentID;
	JLabel labelStudentName;
	String[] student_id = new String[1000];
	String[] paid_student_id = new String[1000];
	JComboBox comboBoxMonth;
	String[] month = new String[1000];
	String[] status = new String[1000];
	String[] temp = new String[1000];
	private JTable tableViewTuitionFee;
	JCheckBox chckbxVisible;
	JButton btnDue;
	private JTextField textFieldStudentId;
	private JTextField textFieldTotalAmount;
	private JTextField textFieldStudentNo;
	private JTable tableDue;
	private JTextField textFieldDueAmunt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DueFee frame = new DueFee();
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
	public DueFee() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			connection = DbConnection.dbconnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBounds(100, 100, 1088, 550);
		setTitle("London Intelligence School");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(152, 103, 860, 152);
		contentPane.add(panel);
		panel.setLayout(null);

		comboBoxYear = new JComboBox();
		comboBoxYear.setVisible(false);
		comboBoxYear.setModel(new DefaultComboBoxModel(
				new String[] { "Select", "Nursery", "Play", "One", "Two", "Three", "Four", "Five", "Six" }));
		comboBoxYear.setBounds(221, 11, 99, 20);
		panel.add(comboBoxYear);

		comboBoxStudentID = new JComboBox();
		comboBoxStudentID.setVisible(false);
		comboBoxStudentID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxStudentID.addItem("garbeg");
				String st_id = comboBoxStudentID.getSelectedItem().toString();
				comboBoxStudentID.removeItem("garbeg");

				try {
					String q = "select firstname,lastname from lis_student_info where stu_id='" + st_id + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					if (result.next()) {
						labelStudentName.setText(result.getString("FIRSTNAME") + " " + result.getString("LASTNAME"));
					} else {
						labelStudentName.setText("");
					}

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		comboBoxStudentID.setBounds(221, 32, 99, 20);
		panel.add(comboBoxStudentID);

		comboBoxSection = new JComboBox();
		comboBoxSection.setVisible(false);
		comboBoxSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String yr = comboBoxYear.getSelectedItem().toString();
				String sec = comboBoxSection.getSelectedItem().toString();
				comboBoxStudentID.removeAllItems();

				try {
					String q = "select stu_id from lis_student_info where year='" + yr + "'and section='" + sec + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						comboBoxStudentID.addItem(result.getString("STU_ID"));

					}

					prstatement.close();
					result.close();
				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		comboBoxSection.setModel(new DefaultComboBoxModel(new String[] { "Select ", "A", "B", "C", "D" }));
		comboBoxSection.setBounds(238, 11, 99, 20);
		panel.add(comboBoxSection);

		JButton btnSearch = new JButton("Search");
		btnSearch.setVisible(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s_id = comboBoxStudentID.getSelectedItem().toString();
				try {
					String q = "select * from lis_fee_schedul where student_id='" + s_id + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					tableViewTuitionFee.setModel(DbUtils.resultSetToTableModel(result));

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSearch.setBounds(221, 31, 89, 23);
		panel.add(btnSearch);

		labelStudentName = new JLabel("");
		labelStudentName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		labelStudentName.setBounds(291, 71, 135, 14);
		panel.add(labelStudentName);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(613, 25, 223, 116);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnDue = new JButton("Due");
		btnDue.setVisible(false);
		btnDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String st, str;
				String mnth = comboBoxMonth.getSelectedItem().toString();
				DateFormat dat = new SimpleDateFormat("yy");
				Calendar canl = Calendar.getInstance();
				String yr = (String) dat.format(canl.getTime());
				String mn = mnth + "-" + yr;
				System.out.println(mn);

				int i = 0;
				try {
					String q = "select stu_id from lis_student_info";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						// System.out.print(result.getString("STU_ID")+"\n");
						student_id[i++] = result.getString("STU_ID");
					}
					prstatement.close();
					result.close();

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				int k = 0, m = 0, n = 0;

				DateFormat da = new SimpleDateFormat("MM");
				Calendar can = Calendar.getInstance();
				String mon = (String) da.format(can.getTime());
				System.out.println(mon);

				try {
					String pd = "paid";
					//
					String q = "select stu_id, month_of_fee,fee_status from LIS_STUDENT_FEE_DETAILS where fee_status='"
							+ pd + "' and month_of_fee='" + mnth + "' ";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						// System.out.print(result.getString("STU_ID")+"\n");
						paid_student_id[m++] = result.getString("STU_ID");
						month[k++] = result.getString("MONTH_OF_FEE");
						status[n++] = result.getString("FEE_STATUS");
					}

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

				int mo = Integer.parseInt(mon);
				int x = 0, ii;
				for (int j = 0; j < i; j++) {
					// System.out.print(student_id[j]+"\n");
					for (ii = 0; ii < m; ii++) {
						if (student_id[j].equals(paid_student_id[ii])) {
							break;

						}

					}
					if (ii == m) {
						temp[x++] = student_id[j];
					}

				}
				for (int jj = 0; jj < x; jj++) {
					System.out.print(temp[jj] + "\n");

				}
				for (int c = 0; c < x; c++) {

					String query = "Insert into LIS_STUDENT_FEE_DETAILS values(?,?,?,?,?)";
					try {
						PreparedStatement prstatement = connection.prepareStatement(query);
						prstatement.setString(1, temp[c]);
						prstatement.setString(2, " ");
						prstatement.setString(3, mnth);
						prstatement.setDouble(4, 0);
						prstatement.setString(5, "Due");
						prstatement.executeQuery();
						prstatement.close();
						// JOptionPane.showMessageDialog(null, "Saved data in
						// sales_invoice");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						JOptionPane.showMessageDialog(null, e);
					}
				}

			}
		});
		btnDue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDue.setBounds(142, 66, 53, 23);
		panel_1.add(btnDue);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMonth.setBounds(12, 42, 44, 14);
		panel_1.add(lblMonth);

		JLabel lblWhoAreNot = new JLabel("Unpaid Student");
		lblWhoAreNot.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblWhoAreNot.setBounds(66, 9, 123, 23);
		panel_1.add(lblWhoAreNot);

		comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May",
				"Jun", "July", "August", "September", "October", "November", "December" }));
		comboBoxMonth.setBounds(53, 35, 99, 20);
		panel_1.add(comboBoxMonth);

		chckbxVisible = new JCheckBox("Visible");
		chckbxVisible.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chckbxVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxVisible.isSelected()) {
					btnDue.setVisible(true);
				} else {
					btnDue.setVisible(false);
				}

			}
		});
		chckbxVisible.setBounds(157, 66, 66, 23);
		panel_1.add(chckbxVisible);
		
		JButton btnDues = new JButton("Dues");
		btnDues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String monthName=comboBoxMonth.getSelectedItem().toString();

				try {
					String q = "select * from lis_fee_schedul where status='"
							+ "Overdue" + "' and month_of_fee='"+monthName+"' ";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					tableViewTuitionFee.setModel(DbUtils.resultSetToTableModel(result));
					prstatement.close();
					result.close();

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				countStudentAndAmount(monthName);
				
			}
		});
		btnDues.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDues.setBounds(10, 66, 114, 23);
		panel_1.add(btnDues);
		
		JButton btnPaidStudents = new JButton("Paid Students");
		btnPaidStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String monthName=comboBoxMonth.getSelectedItem().toString();

				try {
					String q = "select * from lis_fee_schedul where status='"
							+ "Paid" + "' and month_of_fee='"+monthName+"' ";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					tableViewTuitionFee.setModel(DbUtils.resultSetToTableModel(result));
					prstatement.close();
					result.close();

				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
				}
				textFieldStudentNo.setText(null);
				try {
					String q = "select count(student_id) as noOfStudent from lis_fee_schedul where status='"
							+ "Paid" + "' and month_of_fee='"+monthName+"' ";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					if(result.next()){
						String noOfStudent=result.getString("noOfStudent");
						textFieldStudentNo.setText(noOfStudent);
					}
					prstatement.close();
					result.close();

				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
				}
				try {
					String q = "select sum(fee_amount) as amount from lis_fee_schedul where status='"
							+ "Paid" + "' and month_of_fee='"+monthName+"' ";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					if(result.next()){
						String amount=result.getString("amount");
						textFieldTotalAmount.setText(amount);
					}
					prstatement.close();
					result.close();

				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
				}
			}
		});
		btnPaidStudents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnPaidStudents.setBounds(10, 93, 114, 23);
		panel_1.add(btnPaidStudents);
		
		JButton btnDues_1 = new JButton("Dues >1");
		btnDues_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q = "select student_id,count(student_id) as No_of_Month_Due,sum(fee_amount) as amount, status from lis_fee_schedul"
							+ " where status='"
							+ "Overdue" + "' group by student_id,status";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					tableViewTuitionFee.setModel(DbUtils.resultSetToTableModel(result));
					prstatement.close();
					result.close();

				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
				}
				textFieldStudentNo.setText(null);
				
				try {
					String q = "select   count( distinct student_id) as noOfStudent from lis_fee_schedul where status='"
							+ "Overdue" + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					if(result.next()){
						String noOfStudent=result.getString("noOfStudent");
						textFieldStudentNo.setText(noOfStudent);
					}
					prstatement.close();
					result.close();

				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
				}
				try {
					String q = "select sum(fee_amount) as amount from lis_fee_schedul where status='"
							+ "Overdue" + "'  ";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					if(result.next()){
						String amount=result.getString("amount");
						textFieldTotalAmount.setText(amount);
					}
					prstatement.close();
					result.close();

				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, e);
					e1.printStackTrace();
				}		
			}
		});
		btnDues_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDues_1.setBounds(109, 66, 114, 23);
		panel_1.add(btnDues_1);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReport.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnReport.setBounds(109, 93, 114, 23);
		panel_1.add(btnReport);
		chckbxVisible.setVisible(false);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(401, 25, 202, 116);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnAllDue = new JButton("Select  Due");
		btnAllDue.setBounds(49, 59, 114, 23);
		panel_2.add(btnAllDue);
		btnAllDue.setVisible(false);
		btnAllDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DateFormat dat = new SimpleDateFormat("yy");
				Calendar canl = Calendar.getInstance();
				String yr = (String) dat.format(canl.getTime());

				try {
					String q = "select stu_id,count(stu_id) as TotalDue, fee_status from LIS_STUDENT_FEE_DETAILS where fee_status='"
							+ "Due" + "' group by stu_id,fee_status";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					//tableDueFee.setModel(DbUtils.resultSetToTableModel(result));
					prstatement.close();
					result.close();

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		btnAllDue.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JLabel lblFrom = new JLabel("Select  Due Fee");
		lblFrom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFrom.setBounds(39, 25, 153, 23);
		panel_2.add(lblFrom);
		
				JButton btnMakeDue = new JButton("Make Due");
				btnMakeDue.setBounds(41, 82, 114, 23);
				panel_2.add(btnMakeDue);
				btnMakeDue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String[] monthName={"","January","February","March","April","May","Jun","July","August","September","October","November","December"};

						String installmentDate = null;
						SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
						Calendar c = Calendar.getInstance();
						 c.add(Calendar.MONTH, 0); // number of days to add
						installmentDate = sdf.format(c.getTime()); // dt is now the new
						System.out.println("month"+installmentDate);
						int c_month=Integer.parseInt(installmentDate.substring(0, 2));
						
						double panaltyforDue=getPanaltyforDue();
						String query1 = "update LIS_FEE_SCHEDUL set status='" + "Overdue" + "',penalty_amount='"+panaltyforDue+"' where month_of_fee= '"+monthName[c_month]+"' and status='"+"Due"+"'";

						try {
							PreparedStatement prStatement = connection.prepareStatement(query1);

							prStatement.executeQuery();

							prStatement.close();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							// JOptionPane.showMessageDialog(null, e);
							e1.printStackTrace();
						}

					}
				});
				btnMakeDue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				
				textFieldStudentId = new JTextField();
				textFieldStudentId.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						String s_id = textFieldStudentId.getText();
						try {
							String q = "select * from lis_fee_schedul where student_id='" + s_id + "'";
							PreparedStatement prstatement = connection.prepareStatement(q);
							ResultSet result = prstatement.executeQuery();
							tableViewTuitionFee.setModel(DbUtils.resultSetToTableModel(result));

						} catch (Exception e) {
							// JOptionPane.showMessageDialog(null, e);
							e.printStackTrace();
						}
						
					}
				});
				textFieldStudentId.setBounds(113, 102, 86, 20);
				panel.add(textFieldStudentId);
				textFieldStudentId.setColumns(10);
				
				JLabel label = new JLabel("Student ID");
				label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				label.setBounds(48, 105, 77, 14);
				panel.add(label);

		JLabel lblLondonIntelligenceSchool = new JLabel("London Intelligence School");
		lblLondonIntelligenceSchool.setForeground(new Color(210, 105, 30));
		lblLondonIntelligenceSchool.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblLondonIntelligenceSchool.setBounds(315, 22, 297, 49);
		contentPane.add(lblLondonIntelligenceSchool);

		JLabel labelLogo = new JLabel("");
		labelLogo.setBounds(249, 22, 53, 59);
		Image logo = new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		labelLogo.setIcon(new ImageIcon(logo));
		contentPane.add(labelLogo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(151, 301, 609, 169);
		contentPane.add(scrollPane_1);

		tableViewTuitionFee = new JTable();
		tableViewTuitionFee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ro_id=tableViewTuitionFee.getSelectedRow();
				String studentId=tableViewTuitionFee.getModel().getValueAt(ro_id, 0).toString();
				try {
					String q = "select student_year,month_of_fee,fee_amount,status from lis_fee_schedul "
							+ "where student_id='" + studentId + "' and status='"+"Overdue"+"'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					tableDue.setModel(DbUtils.resultSetToTableModel(result));

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
				try {
					String q = "select sum(fee_amount) amount from lis_fee_schedul"
							+ " where student_id='" + studentId + "' and status='"+"Overdue"+"'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();

					if(result.next()){
						String amount=result.getString("amount");
						textFieldDueAmunt.setText(amount);
						
					}
				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(tableViewTuitionFee);
		
		JLabel lblNoOfStudent = new JLabel("No of Student");
		lblNoOfStudent.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNoOfStudent.setBounds(152, 481, 96, 14);
		contentPane.add(lblNoOfStudent);
		
		textFieldTotalAmount = new JTextField();
		textFieldTotalAmount.setEditable(false);
		textFieldTotalAmount.setBounds(466, 478, 86, 20);
		contentPane.add(textFieldTotalAmount);
		textFieldTotalAmount.setColumns(10);
		
		textFieldStudentNo = new JTextField();
		textFieldStudentNo.setEditable(false);
		textFieldStudentNo.setColumns(10);
		textFieldStudentNo.setBounds(234, 478, 86, 20);
		contentPane.add(textFieldStudentNo);
		
		JLabel lblAmount = new JLabel("Amount TK");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAmount.setBounds(390, 478, 76, 14);
		contentPane.add(lblAmount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(817, 302, 219, 169);
		contentPane.add(scrollPane);
		
		tableDue = new JTable();
		scrollPane.setViewportView(tableDue);
		
		textFieldDueAmunt = new JTextField();
		textFieldDueAmunt.setEditable(false);
		textFieldDueAmunt.setColumns(10);
		textFieldDueAmunt.setBounds(926, 478, 86, 17);
		contentPane.add(textFieldDueAmunt);
		
		JLabel label_1 = new JLabel("Amount TK");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(850, 478, 76, 14);
		contentPane.add(label_1);
	}

	protected void countStudentAndAmount(String monthName) {
		textFieldStudentNo.setText(null);
		
		try {
			String q = "select count(student_id) as noOfStudent from lis_fee_schedul where status='"
					+ "Overdue" + "' and month_of_fee='"+monthName+"' ";
			PreparedStatement prstatement = connection.prepareStatement(q);
			ResultSet result = prstatement.executeQuery();
			if(result.next()){
				String noOfStudent=result.getString("noOfStudent");
				textFieldStudentNo.setText(noOfStudent);
			}
			prstatement.close();
			result.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		try {
			String q = "select sum(fee_amount) as amount from lis_fee_schedul where status='"
					+ "Overdue" + "' and month_of_fee='"+monthName+"' ";
			PreparedStatement prstatement = connection.prepareStatement(q);
			ResultSet result = prstatement.executeQuery();
			if(result.next()){
				String amount=result.getString("amount");
				textFieldTotalAmount.setText(amount);
			}
			prstatement.close();
			result.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}		
	}

	protected double getPanaltyforDue() {
		try {
			double latefee=0;
			String q = "select amount from lis_fine_setup where fine_type='"+"LateFee"+"'";
			PreparedStatement prstatement = connection.prepareStatement(q);
			ResultSet result = prstatement.executeQuery();
			if(result.next()){
				latefee=Double.parseDouble(result.getString("AMOUNT"));
			}
			prstatement.close();
			result.close();
			return latefee;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return 0;
	}
}
