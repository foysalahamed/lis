import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.UIManager;

/*import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;*/

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;
import javax.xml.ws.handler.MessageContext.Scope;

import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JList;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Scrollbar;
import java.awt.Button;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.JRadioButton;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Transection extends JFrame {

	private JPanel contentPane;
	private JTextField textField_search;
	public Connection connection = null;
	private JScrollPane scroll;
	JList list;
	JLabel labelTotal;
	JLabel labelCash, labelChange;
	private DefaultListModel dlm = new DefaultListModel();
	private DefaultListModel productIdIndlm = new DefaultListModel();
	private DefaultListModel priceIndlm = new DefaultListModel();
	private DefaultListModel itemNoIndlm = new DefaultListModel();
	private DefaultListModel productNameInDLM = new DefaultListModel();
	JComboBox comboBoxFeeID, comboBoxFeeName;
	JComboBox comboBoxBookName;
	JComboBox comboBoxBookID;
	JLabel labelStatus;

	ButtonGroup group;
	JRadioButton radioButtonStudent;
	JRadioButton radioButtonTeacher;
	JRadioButton radioButtonStaff;
	JTextArea textAreaReasons;
	JDateChooser dateChooserTo, dateChooserFrom;
	String categories;
	JDateChooser dateChooserAppDate;
	JDateChooser dateChooserCheckAppDate;

	private String isOffer[] = new String[50];
	private String invoice_srl;
	int noOfItem = 1;
	double tFee = 0;

	double deductSchTimeperiod = 0;
	double total = 0.0, price;
	int ii = 0;
	int Q[] = new int[50];
	double save = 0.0;
	private JTextField textFieldDisplay;
	JComboBox comboBoxSection, comboBoxYear, comboBoxStudentID;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Home frame = new Home();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public Transection() {
		try {
			connection = DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setTitle("London Intelligence School");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 758);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		JMenu mnAdministrator = new JMenu("Administrator");
		menuBar.add(mnAdministrator);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// scroll=new JScrollPane(table);
		// contentPane.add(scroll);

		JTabbedPane tabbed_food = new JTabbedPane(JTabbedPane.TOP);
		tabbed_food.setBounds(449, 89, 534, 437);
		contentPane.add(tabbed_food);
		// Image img6=new
		// ImageIcon(this.getClass().getResource("/rice2.png")).getImage();
		// Image img7=new
		// ImageIcon(this.getClass().getResource("/sunflower.png")).getImage();

		JPanel panel_TuitionFee = new JPanel();
		panel_TuitionFee.setBackground(Color.WHITE);
		tabbed_food.addTab("Tuition Fee", null, panel_TuitionFee, null);
		tabbed_food.setBackgroundAt(0, new Color(240, 230, 140));
		panel_TuitionFee.setLayout(null);

		JButton btnJanuary = new JButton("");
		btnJanuary.setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource("/Jan.png")).getImage();
		btnJanuary.setIcon(new ImageIcon(img));
		btnJanuary.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;

					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "January")) {

						boolean due = checkDueTuitionFee(stu_id, "January");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "January");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "January");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "January");
								getLateFeeFine("January");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "January");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for Januray");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");
				}

				// System.out.print("student year and id "+yr+" "+stu_id);
			}
		});
		btnJanuary.setForeground(new Color(205, 133, 63));
		btnJanuary.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnJanuary.setBounds(10, 21, 82, 83);
		panel_TuitionFee.add(btnJanuary);

		JButton btnFebruary = new JButton("");
		btnFebruary.setBackground(Color.WHITE);
		Image imgfeb = new ImageIcon(this.getClass().getResource("/Feb.png")).getImage();
		btnFebruary.setIcon(new ImageIcon(imgfeb));
		btnFebruary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					boolean due = checkDueTuitionFee(stu_id, "February");
					if (!checkPaid(stu_id, "February")) {
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "February");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "February");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "February");
								getLateFeeFine("February");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "February");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							// update time period table for scholarship
							// updateScholarshipTimePeriod(stu_id);
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for February");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}
			}

		});
		btnFebruary.setForeground(new Color(205, 133, 63));
		btnFebruary.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnFebruary.setBounds(109, 21, 82, 83);
		panel_TuitionFee.add(btnFebruary);

		JButton btnMar = new JButton("");
		btnMar.setBackground(Color.WHITE);
		Image imgmar = new ImageIcon(this.getClass().getResource("/Mar.png")).getImage();
		btnMar.setIcon(new ImageIcon(imgmar));
		btnMar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "March")) {
						boolean due = checkDueTuitionFee(stu_id, "March");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "March");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "March");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "March");
								getLateFeeFine("March");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "March");
						}
						double scholarship = getScholarship(stu_id, tFee);

						if (scholarship > 0) {
							// update time period table for scholarship
							// updateScholarshipTimePeriod(stu_id);
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for March");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}

			}
		});
		btnMar.setForeground(new Color(205, 133, 63));
		btnMar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnMar.setBounds(209, 21, 82, 83);
		panel_TuitionFee.add(btnMar);

		JButton btnApr = new JButton("");
		btnApr.setBackground(Color.WHITE);
		Image imgapr = new ImageIcon(this.getClass().getResource("/Apr.png")).getImage();
		btnApr.setIcon(new ImageIcon(imgapr));
		btnApr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "April")) {
						boolean due = checkDueTuitionFee(stu_id, "April");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "April");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "April");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "April");
								getLateFeeFine("April");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "April");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for April");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}
			}
		});
		btnApr.setForeground(new Color(205, 133, 63));
		btnApr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnApr.setBounds(307, 21, 82, 83);
		panel_TuitionFee.add(btnApr);

		JButton btnMay = new JButton("");
		btnMay.setBackground(Color.WHITE);
		Image imgmay = new ImageIcon(this.getClass().getResource("/May.png")).getImage();
		btnMay.setIcon(new ImageIcon(imgmay));
		btnMay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "May")) {
						boolean due = checkDueTuitionFee(stu_id, "May");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "May");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "May");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "May");
								getLateFeeFine("May");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "May");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for May");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}
			}
		});
		btnMay.setForeground(new Color(205, 133, 63));
		btnMay.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnMay.setBounds(10, 117, 82, 83);
		panel_TuitionFee.add(btnMay);

		JButton btnJun = new JButton("");
		btnJun.setBackground(Color.WHITE);
		Image imgjun = new ImageIcon(this.getClass().getResource("/Jun.png")).getImage();
		btnJun.setIcon(new ImageIcon(imgjun));
		btnJun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "Jun")) {
						boolean due = checkDueTuitionFee(stu_id, "Jun");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "Jun");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "Jun");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "Jun");
								getLateFeeFine("Jun");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "Jun");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for Jun");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}
			}
		});
		btnJun.setForeground(new Color(205, 133, 63));
		btnJun.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnJun.setBounds(109, 117, 82, 83);
		panel_TuitionFee.add(btnJun);

		JButton btnJul = new JButton("");
		btnJul.setBackground(Color.WHITE);
		Image imgjul = new ImageIcon(this.getClass().getResource("/Jul.png")).getImage();
		btnJul.setIcon(new ImageIcon(imgjul));
		btnJul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "July")) {
						boolean due = checkDueTuitionFee(stu_id, "July");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "July");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "July");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "July");
								getLateFeeFine("July");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "July");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for July");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}

			}
		});
		btnJul.setForeground(new Color(205, 133, 63));
		btnJul.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnJul.setBounds(208, 117, 82, 83);
		panel_TuitionFee.add(btnJul);

		JButton btnAug = new JButton("");
		btnAug.setBackground(Color.WHITE);
		Image imgaug = new ImageIcon(this.getClass().getResource("/Aug.png")).getImage();
		btnAug.setIcon(new ImageIcon(imgaug));
		btnAug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "August")) {
						boolean due = checkDueTuitionFee(stu_id, "August");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "August");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "August");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "August");
								getLateFeeFine("August");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "August");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for August");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}

			}
		});
		btnAug.setForeground(new Color(205, 133, 63));
		btnAug.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAug.setBounds(307, 117, 82, 83);
		panel_TuitionFee.add(btnAug);

		JButton btnSep = new JButton("");
		btnSep.setBackground(Color.WHITE);
		Image imgsep = new ImageIcon(this.getClass().getResource("/Sep.png")).getImage();
		btnSep.setIcon(new ImageIcon(imgsep));
		btnSep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();

					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);
					if (!checkPaid(stu_id, "September")) {

						boolean due = checkDueTuitionFee(stu_id, "September");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "September");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "September");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "September");
								getLateFeeFine("September");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "September");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for September");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}

			}
		});
		btnSep.setForeground(new Color(205, 133, 63));
		btnSep.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSep.setBounds(10, 215, 82, 83);
		panel_TuitionFee.add(btnSep);

		JButton btnOct = new JButton("");
		btnOct.setBackground(Color.WHITE);
		Image imgoct = new ImageIcon(this.getClass().getResource("/Oct.png")).getImage();
		btnOct.setIcon(new ImageIcon(imgoct));
		btnOct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "October")) {
						boolean due = checkDueTuitionFee(stu_id, "October");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "October");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "October");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "October");
								getLateFeeFine("October");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "October");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for October");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}

			}
		});
		btnOct.setForeground(new Color(205, 133, 63));
		btnOct.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnOct.setBounds(109, 215, 82, 83);
		panel_TuitionFee.add(btnOct);

		JButton btnNov = new JButton("");
		btnNov.setBackground(Color.WHITE);
		Image imgnov = new ImageIcon(this.getClass().getResource("/Nov.png")).getImage();
		btnNov.setIcon(new ImageIcon(imgnov));
		btnNov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "November")) {
						boolean due = checkDueTuitionFee(stu_id, "November");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "November");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "November");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "November");
								getLateFeeFine("November");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "November");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for November");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}

			}
		});
		btnNov.setForeground(new Color(205, 133, 63));
		btnNov.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNov.setBounds(208, 215, 82, 83);
		panel_TuitionFee.add(btnNov);

		JButton btnDec = new JButton("");
		btnDec.setBackground(Color.WHITE);
		Image imgdec = new ImageIcon(this.getClass().getResource("/Dec.png")).getImage();
		btnDec.setIcon(new ImageIcon(imgdec));
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine = false;
					// String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id = textFieldStudentId.getText();
					String yr = stu_id.substring(2, 4);

					if (!checkPaid(stu_id, "December")) {
						boolean due = checkDueTuitionFee(stu_id, "December");
						if (due) {
							fine = checkLateFeeAllownces(stu_id, "December");

							if (fine) {
								System.out.println("Allownces Free");

								getTuitionFee(yr, stu_id, "monthly", "December");
								fine = false;
							} else {
								// System.out.println("get tuition fee with
								// fine");
								getTuitionFee(yr, stu_id, "monthly", "December");
								getLateFeeFine("December");

							}
						} else {
							getTuitionFee(yr, stu_id, "monthly", "December");
						}
						double scholarship = getScholarship(stu_id, tFee);
						if (scholarship > 0) {
							deductSchTimeperiod++;
						}
					} else
						JOptionPane.showMessageDialog(null, "Already Paid for December");

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");

				}
			}
		});
		btnDec.setForeground(new Color(205, 133, 63));
		btnDec.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDec.setBounds(307, 215, 82, 83);
		panel_TuitionFee.add(btnDec);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 306, 509, 85);
		panel_TuitionFee.add(scrollPane_1);

		tableFeePaid = new JTable();
		scrollPane_1.setViewportView(tableFeePaid);

		JPanel panel_TermFee = new JPanel();
		tabbed_food.addTab("Others Fee", null, panel_TermFee, null);
		panel_TermFee.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_5.setBounds(48, 115, 430, 112);
		panel_TermFee.add(panel_5);
		panel_5.setLayout(null);

		comboBoxFeeName = new JComboBox();
		comboBoxFeeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxFeeName.addItem("garbeg");

				String str = comboBoxFeeName.getSelectedItem().toString();
				comboBoxFeeName.removeItem("garbeg");
				comboBoxFeeID.removeAllItems();
				String query = "select distinct FEE_ID from LIS_FEE_CATEGORIES where fee_name='" + str + "'";

				try {
					PreparedStatement prstatement = connection.prepareStatement(query);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						comboBoxFeeID.addItem(result.getString("FEE_ID"));

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					// e.printStackTrace();
				}

			}
		});
		comboBoxFeeName.setBounds(86, 32, 118, 20);
		panel_5.add(comboBoxFeeName);

		comboBoxFeeID = new JComboBox();
		comboBoxFeeID.setBounds(264, 32, 118, 20);
		panel_5.add(comboBoxFeeID);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(142, 62, 131, 37);
		panel_5.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getClassTestFee();
			}
		});
		btnOK.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblFeeName = new JLabel("Fee Name");
		lblFeeName.setBounds(10, 35, 66, 14);
		panel_5.add(lblFeeName);

		JLabel lblFeeId = new JLabel("Fee ID");
		lblFeeId.setBounds(212, 35, 45, 14);
		panel_5.add(lblFeeId);
		tabbed_food.setBackgroundAt(1, new Color(250, 240, 230));

		JPanel panel_BooksStationaries = new JPanel();
		panel_BooksStationaries.setBackground(Color.WHITE);
		tabbed_food.addTab("Books & Stationaries", null, panel_BooksStationaries, null);
		panel_BooksStationaries.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_6.setBounds(56, 65, 430, 112);
		panel_BooksStationaries.add(panel_6);

		comboBoxBookName = new JComboBox();
		comboBoxBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxBookName.addItem("garbeg");

				String productName = comboBoxBookName.getSelectedItem().toString();
				comboBoxBookName.removeItem("garbeg");
				comboBoxBookID.removeAllItems();
				String query = "select distinct PRODUCT_ID from LIS_PRODUCT_PRICE_SETUP  where product_name='"
						+ productName + "'";

				try {
					PreparedStatement prstatement = connection.prepareStatement(query);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						comboBoxBookID.addItem(result.getString("PRODUCT_ID"));

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					// e.printStackTrace();
				}

			}
		});
		comboBoxBookName.setBounds(86, 32, 118, 20);
		panel_6.add(comboBoxBookName);

		comboBoxBookID = new JComboBox();
		comboBoxBookID.setBounds(264, 32, 118, 20);
		panel_6.add(comboBoxBookID);

		JButton btnBooOK = new JButton("OK");
		btnBooOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p_name = comboBoxBookName.getSelectedItem().toString();
				String pId = comboBoxBookID.getSelectedItem().toString();
				productname = pId;
				String query = "select SALES_PRICE,OFFER from LIS_PRODUCT_PRICE_SETUP where product_id='" + pId + "' ";
				try {
					PreparedStatement prstatement = connection.prepareStatement(query);
					ResultSet result = prstatement.executeQuery();
					if (result.next()) {
						String amount = result.getString("SALES_PRICE");
						String items = String.valueOf((int) noOfItem);

						String yORn = result.getString("OFFER");
						// String yORn="n";//this is not for offer if offer
						// working ommit this line
						char yon = yORn.charAt(0);
						System.out.println("\n yon: " + yon);

						if (yon == 'y') {
							int flag = 0;
							for (int i = 0; i <= o; i++) {

								if (pId.equals(isOffer[i])) {
									flag = 1;
									break;
								}
							}
							if (flag == 0) {
								isOffer[o++] = pId; // keep the offered product
													// in isoffer array.
								System.out.println("\n O: " + o);
							}
						}

						dataInShoppingCart(productname, amount, items, pId);

					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});
		btnBooOK.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnBooOK.setBounds(142, 62, 131, 37);
		panel_6.add(btnBooOK);

		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(10, 35, 66, 14);
		panel_6.add(lblBookName);

		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(212, 35, 45, 14);
		panel_6.add(lblBookId);
		tabbed_food.setBackgroundAt(2, new Color(230, 230, 250));

		JPanel panel_Transport = new JPanel();
		tabbed_food.addTab("Transport", null, panel_Transport, null);
		panel_Transport.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(30, 45, 455, 299);
		panel_Transport.add(panel_7);
		panel_7.setLayout(null);

		JButton buttonDec = new JButton("");
		buttonDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("December");

			}
		});
		Image imdec = new ImageIcon(this.getClass().getResource("/Dec.png")).getImage();
		buttonDec.setIcon(new ImageIcon(imdec));
		buttonDec.setForeground(new Color(205, 133, 63));
		buttonDec.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonDec.setBackground(Color.WHITE);
		buttonDec.setBounds(335, 205, 82, 83);
		panel_7.add(buttonDec);

		JButton buttonNov = new JButton("");
		buttonNov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("November");

			}
		});
		Image imnov = new ImageIcon(this.getClass().getResource("/Nov.png")).getImage();
		buttonNov.setIcon(new ImageIcon(imnov));
		buttonNov.setForeground(new Color(205, 133, 63));
		buttonNov.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonNov.setBackground(Color.WHITE);
		buttonNov.setBounds(236, 205, 82, 83);
		panel_7.add(buttonNov);

		JButton buttonOct = new JButton("");
		buttonOct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("October");

			}
		});
		Image imoct = new ImageIcon(this.getClass().getResource("/Oct.png")).getImage();
		buttonOct.setIcon(new ImageIcon(imoct));
		buttonOct.setForeground(new Color(205, 133, 63));
		buttonOct.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonOct.setBackground(Color.WHITE);
		buttonOct.setBounds(137, 205, 82, 83);
		panel_7.add(buttonOct);

		JButton buttonSep = new JButton("");
		buttonSep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("September");

			}
		});
		Image imsep = new ImageIcon(this.getClass().getResource("/Sep.png")).getImage();
		buttonSep.setIcon(new ImageIcon(imsep));
		buttonSep.setForeground(new Color(205, 133, 63));
		buttonSep.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonSep.setBackground(Color.WHITE);
		buttonSep.setBounds(38, 205, 82, 83);
		panel_7.add(buttonSep);

		JButton buttonMay = new JButton("");
		Image immay = new ImageIcon(this.getClass().getResource("/May.png")).getImage();
		buttonMay.setIcon(new ImageIcon(immay));
		buttonMay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTransportFare("May");

			}
		});
		buttonMay.setForeground(new Color(205, 133, 63));
		buttonMay.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonMay.setBackground(Color.WHITE);
		buttonMay.setBounds(38, 107, 82, 83);
		panel_7.add(buttonMay);

		JButton buttonJan = new JButton("");
		Image imgjan = new ImageIcon(this.getClass().getResource("/Jan.png")).getImage();
		buttonJan.setIcon(new ImageIcon(imgjan));
		buttonJan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTransportFare("January");
			}
		});
		buttonJan.setForeground(new Color(205, 133, 63));
		buttonJan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonJan.setBackground(Color.WHITE);
		buttonJan.setBounds(38, 11, 82, 83);
		panel_7.add(buttonJan);

		JButton buttonJun = new JButton("");
		Image imjun = new ImageIcon(this.getClass().getResource("/Jun.png")).getImage();
		buttonJun.setIcon(new ImageIcon(imjun));
		buttonJun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTransportFare("Jun");

			}
		});
		buttonJun.setForeground(new Color(205, 133, 63));
		buttonJun.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonJun.setBackground(Color.WHITE);
		buttonJun.setBounds(137, 107, 82, 83);
		panel_7.add(buttonJun);

		JButton buttonFeb = new JButton("");
		Image imfeb = new ImageIcon(this.getClass().getResource("/Feb.png")).getImage();
		buttonFeb.setIcon(new ImageIcon(imfeb));
		buttonFeb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTransportFare("February");
			}
		});
		buttonFeb.setForeground(new Color(205, 133, 63));
		buttonFeb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonFeb.setBackground(Color.WHITE);
		buttonFeb.setBounds(137, 11, 82, 83);
		panel_7.add(buttonFeb);

		JButton buttonMar = new JButton("");
		buttonMar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("March");

			}
		});
		Image immar = new ImageIcon(this.getClass().getResource("/Mar.png")).getImage();
		buttonMar.setIcon(new ImageIcon(immar));
		buttonMar.setForeground(new Color(205, 133, 63));
		buttonMar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonMar.setBackground(Color.WHITE);
		buttonMar.setBounds(237, 11, 82, 83);
		panel_7.add(buttonMar);

		JButton buttonJul = new JButton("");
		buttonJul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("July");

			}
		});
		Image imjul = new ImageIcon(this.getClass().getResource("/Jul.png")).getImage();
		buttonJul.setIcon(new ImageIcon(imjul));
		buttonJul.setForeground(new Color(205, 133, 63));
		buttonJul.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonJul.setBackground(Color.WHITE);
		buttonJul.setBounds(236, 107, 82, 83);
		panel_7.add(buttonJul);

		JButton buttonAug = new JButton("");
		buttonAug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("August");

			}
		});
		Image imaug = new ImageIcon(this.getClass().getResource("/Aug.png")).getImage();
		buttonAug.setIcon(new ImageIcon(imaug));
		buttonAug.setForeground(new Color(205, 133, 63));
		buttonAug.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonAug.setBackground(Color.WHITE);
		buttonAug.setBounds(335, 107, 82, 83);
		panel_7.add(buttonAug);

		JButton buttonApr = new JButton("");
		buttonApr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTransportFare("April");

			}
		});
		Image imapr = new ImageIcon(this.getClass().getResource("/Apr.png")).getImage();
		buttonApr.setIcon(new ImageIcon(imapr));
		buttonApr.setForeground(new Color(205, 133, 63));
		buttonApr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		buttonApr.setBackground(Color.WHITE);
		buttonApr.setBounds(335, 11, 82, 83);
		panel_7.add(buttonApr);

		JLabel lblTransport = new JLabel("Transport");
		lblTransport.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTransport.setBounds(217, 11, 66, 23);
		panel_Transport.add(lblTransport);
		tabbed_food.setBackgroundAt(3, new Color(216, 191, 216));

		JPanel Leaves = new JPanel();
		tabbed_food.addTab("Leaves", null, Leaves, null);
		Leaves.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 509, 387);
		Leaves.add(panel_4);
		panel_4.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(12, 21, 484, 75);
		panel_4.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDate_1 = new JLabel("Application Date");
		lblDate_1.setForeground(Color.BLACK);
		lblDate_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDate_1.setBounds(12, 19, 95, 14);
		panel_3.add(lblDate_1);

		textFieldCheckID = new JTextField();
		textFieldCheckID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAppStatus();
			}
		});
		textFieldCheckID.setColumns(10);
		textFieldCheckID.setBounds(107, 44, 118, 20);
		panel_3.add(textFieldCheckID);

		JLabel label_1 = new JLabel("ID");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(53, 44, 21, 14);
		panel_3.add(label_1);

		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAppStatus();

			}
		});
		btnCheck.setBounds(235, 45, 77, 23);
		panel_3.add(btnCheck);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPrint.setBounds(393, 45, 65, 23);
		panel_3.add(btnPrint);

		labelStatus = new JLabel("");
		labelStatus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		labelStatus.setBounds(383, 13, 65, 17);
		panel_3.add(labelStatus);

		dateChooserCheckAppDate = new JDateChooser();
		dateChooserCheckAppDate.setBounds(107, 19, 95, 20);
		panel_3.add(dateChooserCheckAppDate);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCheckID.setText("");
				dateChooserCheckAppDate.setCalendar(null);
				labelStatus.setText("");
			}
		});
		btnClear.setHorizontalAlignment(SwingConstants.LEFT);
		btnClear.setBounds(318, 45, 65, 23);
		panel_3.add(btnClear);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(10, 122, 489, 254);
		panel_4.add(panel_8);
		panel_8.setLayout(null);

		JLabel label_3 = new JLabel("Categories");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(24, 23, 65, 14);
		panel_8.add(label_3);

		radioButtonStudent = new JRadioButton("Student");
		radioButtonStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				categories = "Student";

			}
		});
		radioButtonStudent.setForeground(Color.BLACK);
		radioButtonStudent.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		radioButtonStudent.setBackground(Color.WHITE);
		radioButtonStudent.setBounds(95, 19, 65, 23);
		panel_8.add(radioButtonStudent);

		radioButtonTeacher = new JRadioButton("Teacher");
		radioButtonTeacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				categories = "Teacher";

			}
		});
		radioButtonTeacher.setForeground(Color.BLACK);
		radioButtonTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		radioButtonTeacher.setBackground(Color.WHITE);
		radioButtonTeacher.setBounds(175, 19, 65, 23);
		panel_8.add(radioButtonTeacher);

		radioButtonStaff = new JRadioButton("staff");
		radioButtonStaff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				categories = "Staff";
			}
		});
		radioButtonStaff.setForeground(Color.BLACK);
		radioButtonStaff.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		radioButtonStaff.setBackground(Color.WHITE);
		radioButtonStaff.setBounds(258, 19, 65, 23);
		panel_8.add(radioButtonStaff);
		group = new ButtonGroup();
		group.add(radioButtonStudent);
		group.add(radioButtonTeacher);
		group.add(radioButtonStaff);

		JLabel label_4 = new JLabel("ID");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(22, 49, 21, 14);
		panel_8.add(label_4);

		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(78, 47, 275, 20);
		panel_8.add(textFieldID);

		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(76, 80, 95, 20);
		panel_8.add(dateChooserFrom);

		JLabel label_5 = new JLabel("From");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_5.setBounds(22, 80, 44, 14);
		panel_8.add(label_5);

		JLabel label_6 = new JLabel("To");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_6.setBounds(191, 84, 29, 14);
		panel_8.add(label_6);

		dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(246, 79, 95, 20);
		panel_8.add(dateChooserTo);

		JLabel label_7 = new JLabel("Reasons");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_7.setBounds(24, 118, 44, 14);
		panel_8.add(label_7);

		textAreaReasons = new JTextArea();
		textAreaReasons.setBackground(new Color(255, 250, 240));
		textAreaReasons.setBounds(80, 121, 400, 94);
		panel_8.add(textAreaReasons);

		JButton buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "insert into LIS_APP_FOR_LEAVE values(appl_id_auto_inc.nextval,?,?,?,?,?,?,?)";
				try {
					PreparedStatement prst = connection.prepareStatement(query);
					prst.setString(1, categories);
					prst.setString(2, textFieldID.getText());
					prst.setString(3,new DateFormateSettings().dateFormateCorrection(((JTextField) dateChooserAppDate.getDateEditor().getUiComponent()).getText())) ; 
							
					prst.setString(4,new DateFormateSettings().dateFormateCorrection(((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText())) ;
							
					prst.setString(5,new DateFormateSettings().dateFormateCorrection(((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText()));
							
					prst.setString(6, textAreaReasons.getText());
					prst.setString(7, "New");
					prst.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully Sent");

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				group.clearSelection();
				textFieldID.setText("");
				dateChooserAppDate.setCalendar(null);
				dateChooserFrom.setCalendar(null);
				dateChooserTo.setCalendar(null);
				textAreaReasons.setText("");

			}
		});
		buttonSend.setBounds(273, 225, 65, 23);
		panel_8.add(buttonSend);

		dateChooserAppDate = new JDateChooser();
		dateChooserAppDate.setBounds(358, 23, 95, 20);
		panel_8.add(dateChooserAppDate);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDate.setBounds(331, 23, 44, 14);
		panel_8.add(lblDate);

		JLabel lblApplication = new JLabel("Application");
		lblApplication.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblApplication.setBounds(10, 102, 65, 14);
		panel_4.add(lblApplication);

		JLabel lblApplicationStatus = new JLabel("Application Status");
		lblApplicationStatus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblApplicationStatus.setBounds(10, 3, 115, 14);
		panel_4.add(lblApplicationStatus);
		tabbed_food.setBackgroundAt(4, new Color(210, 180, 140));

		final JPanel panel_Others = new JPanel();
		tabbed_food.addTab("Others", null, panel_Others, null);
		tabbed_food.setBackgroundAt(5, new Color(240, 255, 240));
		panel_Others.setLayout(null);
		JButton btnStudentAttendance = new JButton("S. Attendance");
		btnStudentAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StudentsAttendance().setVisible(true);
			}
		});
		btnStudentAttendance.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnStudentAttendance.setBounds(40, 107, 184, 50);
		panel_Others.add(btnStudentAttendance);

		JButton btnSAttnCheck = new JButton("S. Attn. Check");
		btnSAttnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AttendanceCheck().setVisible(true);
			}
		});
		btnSAttnCheck.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSAttnCheck.setBounds(249, 107, 145, 50);
		panel_Others.add(btnSAttnCheck);
		// getPicnicPlace();

		textField_search = new JTextField();
		textField_search.setBounds(36, 63, 201, 28);
		textField_search.addKeyListener(new KeyAdapter() {
			/*
			 * @Override public void keyReleased(KeyEvent arg0) {
			 * 
			 * 
			 * getDataFromProductList(textField_search.getText());// product
			 * search from database after scanning the bar code
			 * 
			 * textField_search.setText("");5060172541683 5060118541623
			 * 5060172541683
			 * 
			 * 
			 * 
			 * 
			 * }
			 */
			@Override
			public void keyPressed(KeyEvent arg0) {
				String str = textField_search.getText();
				getDataFromProductList(str);// product search from database
											// after scanning the bar code

			}

		});
		textField_search.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(textField_search);
		textField_search.setColumns(10);

		JButton btnLogout = new JButton("");
		btnLogout.setBounds(863, 12, 102, 50);

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// PrintWriter pw=new PrintWriter("C:\\login.txt");
					PrintWriter pw = new PrintWriter("C:\\Users\\creativepc\\Desktop\\LIS\\logfile\\login.txt");
					pw.println("");
					PrintWriter pwuser = new PrintWriter("C:\\Users\\creativepc\\Desktop\\LIS\\logfile\\user.txt");
					pwuser.println("");
					pw.close();
					pwuser.close();
					sessionLogout();
					dispose();

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		contentPane.add(btnLogout);

		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setBounds(881, 64, 70, 14);
		lblLogout.setForeground(new Color(0, 0, 255));
		lblLogout.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblLogout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 93, 201, 396);
		contentPane.add(scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);

		JButton buttonFinish = new JButton("Cash");
		buttonFinish.setBounds(450, 537, 218, 88);
		buttonFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cashPayment = textFieldDisplay.getText();
				double cashPay = Double.parseDouble(cashPayment);
				if (cashPay >= total) {
					if (dlm.size() > 0) {
						payment();
						invoice();
						invoiceDetails();
						tuitionFeeStatus();
						cashDetails("sale");

						report();
						print();
						deductProductFromDatabase();
						if (deductSchTimeperiod > 0) {
							String stId = textFieldStudentId.getText();
							updateScholarshipTimePeriod(stId);
						}

						deleteDLM();
						clearStudentInfo();

					} else {
						JOptionPane.showMessageDialog(null, "Basket is empty,Take any product please");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You havent paid enough money");

				}
			}
		});
		buttonFinish.setForeground(SystemColor.textHighlight);
		buttonFinish.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(buttonFinish);

		JButton buttonDeleteItem = new JButton("Delete Item");
		buttonDeleteItem.setBounds(245, 151, 194, 53);
		buttonDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItem();
			}
		});
		buttonDeleteItem.setForeground(Color.RED);
		buttonDeleteItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(buttonDeleteItem);

		JPanel panel = new JPanel();
		panel.setBounds(243, 217, 201, 387);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panel);
		panel.setLayout(null);

		JButton buttonFour = new JButton("4");
		buttonFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(4);
			}
		});
		buttonFour.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonFour.setBounds(16, 128, 50, 50);
		panel.add(buttonFour);

		JButton buttonFive = new JButton("5");
		buttonFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(5);

			}
		});
		buttonFive.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonFive.setBounds(76, 128, 50, 50);
		panel.add(buttonFive);

		JButton buttonSix = new JButton("6");
		buttonSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(6);

			}
		});
		buttonSix.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonSix.setBounds(136, 128, 50, 50);
		panel.add(buttonSix);

		JButton buttonOne = new JButton("1");
		buttonOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(1);
			}
		});
		buttonOne.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonOne.setBounds(16, 189, 50, 50);
		panel.add(buttonOne);

		JButton buttonTwo = new JButton("2");
		buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(2);
			}
		});
		buttonTwo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonTwo.setBounds(76, 189, 50, 50);
		panel.add(buttonTwo);

		JButton buttonThree = new JButton("3");
		buttonThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(3);
			}
		});
		buttonThree.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonThree.setBounds(136, 189, 50, 50);
		panel.add(buttonThree);

		JButton buttonZero = new JButton("0");
		buttonZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(0);
			}
		});
		buttonZero.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonZero.setBounds(16, 250, 50, 50);
		panel.add(buttonZero);

		JButton buttonAC = new JButton("Clear");
		buttonAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal_initial = 0.0;
				textFieldDisplay.setText("0.");
			}
		});
		buttonAC.setForeground(Color.RED);
		buttonAC.setFont(new Font("Times New Roman", Font.BOLD, 22));
		buttonAC.setBounds(76, 250, 110, 50);
		panel.add(buttonAC);

		JButton buttonSeven = new JButton("7");
		buttonSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(7);

			}
		});
		buttonSeven.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonSeven.setBounds(16, 67, 50, 50);
		panel.add(buttonSeven);

		JButton buttonEight = new JButton("8");
		buttonEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(8);

			}
		});
		buttonEight.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonEight.setBounds(76, 67, 50, 50);
		panel.add(buttonEight);

		JButton buttonNine = new JButton("9");
		buttonNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator(9);

			}
		});
		buttonNine.setFont(new Font("Times New Roman", Font.BOLD, 24));
		buttonNine.setBounds(136, 67, 50, 50);
		panel.add(buttonNine);

		textFieldDisplay = new JTextField();
		textFieldDisplay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char c = event.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});
		textFieldDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldDisplay.setBounds(16, 19, 170, 37);

		panel.add(textFieldDisplay);
		textFieldDisplay.setColumns(10);

		JButton btnCard = new JButton("Card Payment");
		btnCard.setBounds(678, 537, 212, 88);
		btnCard.setForeground(SystemColor.textHighlight);
		btnCard.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnCard);

		JButton btnMulti = new JButton("Multi");
		btnMulti.setBounds(245, 93, 194, 53);
		btnMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeNoOfItems();
			}
		});
		btnMulti.setForeground(SystemColor.activeCaption);
		btnMulti.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnMulti);
		textFieldDisplay.setText("0.");

		JButton btnDept = new JButton("Dept");
		btnDept.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("offered items:" + o);
				if (o >= 1) {
					calculating_offered_product();
					calculating_offered_price();

				}
				payment();
				cashPay = Math.round(cashPay * 100.0) / 100.0;
				String str = String.valueOf(cashPay);
				labelCash.setText(str);
				change = Math.round(change * 100.0) / 100.0;
				String chn = String.valueOf(change);
				labelChange.setText(chn);

			}

			private void calculating_offered_price() {
				double total_reduced_price = 0.0;
				double reduced_price = 0.0;
				double ttl_offr_prd_and_price = 0.0;
				for (int i = 0; i < o; i++) {
					String query = "Select offer_quantity,offer_price from LIS_OFFER_DETAILS where product_id='"
							+ isOffer[i] + "'";
					try {
						PreparedStatement prstatement = connection.prepareStatement(query);
						ResultSet rs = prstatement.executeQuery();

						if (rs.next()) {
							int offer_quantity = Integer.parseInt(rs.getString("OFFER_QUANTITY"));
							double offer_price = Double.parseDouble(rs.getString("OFFER_PRICE"));
							// System.out.println("ofer
							// quantity:"+offer_quantity+" offer price:
							// "+offer_price);
							int reminder = Q[i] % offer_quantity;
							int div_result = Q[i] / offer_quantity;
							// System.out.println("reminder:"+reminder+" div
							// result:"+div_result);
							double acPrice = 0.0;
							for (int j = 0; j < productIdIndlm.size(); j++) {
								if (isOffer[i].equals(productIdIndlm.get(j))) {
									String pr = priceIndlm.get(j).toString();
									acPrice = getActualPriceFromDb(productIdIndlm.get(j).toString());
									ttl_offr_prd_and_price = ttl_offr_prd_and_price + Q[i] * acPrice;
									// System.out.println("ttl_offer_products
									// actual Price:"+ttl_offr_prd_and_price);

									break;
								}
							}

							reduced_price = div_result * offer_price + reminder * acPrice;

							// System.out.println("reduced
							// Price:"+reduced_price);
						}
					} catch (Exception e) {
						// TODO: handle exception
						// JOptionPane.showMessageDialog(null,e);
						e.printStackTrace();
					}
					total_reduced_price = total_reduced_price + reduced_price;

				}
				save = ttl_offr_prd_and_price - total_reduced_price;
				// System.out.println(" Saved:"+save);
				total = total - save;
				total = Math.round(total * 100.0) / 100.0;

				labelTotal.setText(String.valueOf(total));

				for (int i = 0; i < o; i++) {
					isOffer[i] = null;
				}
				o = 0;
			}

			private void calculating_offered_product() {
				int temp = 0;
				for (int i = 0; i < o; i++) {
					for (int j = 0; j < productIdIndlm.size(); j++) {
						if (isOffer[i].equals(productIdIndlm.get(j))) {
							// itemNoIndlm
							temp = temp + (int) itemNoIndlm.get(j);

						}
					}
					Q[i] = temp;
					temp = 0;

				}
				for (int i = 0; i < o; i++) {
					System.out.println("Q[%d] " + Q[i]);
				}

			}

		});
		btnDept.setForeground(Color.BLUE);
		btnDept.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnDept.setBounds(16, 311, 170, 50);
		panel.add(btnDept);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 489, 201, 115);
		panel_1.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(11, 81, 86, 30);
		panel_1.add(lblChange);
		lblChange.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblChange.setBackground(SystemColor.inactiveCaption);

		labelChange = new JLabel("0.0");
		labelChange.setBounds(113, 79, 84, 30);
		panel_1.add(labelChange);
		labelChange.setForeground(Color.BLUE);
		labelChange.setFont(new Font("Times New Roman", Font.BOLD, 24));

		labelCash = new JLabel("0.0");
		labelCash.setBounds(113, 50, 84, 30);
		panel_1.add(labelCash);
		labelCash.setForeground(Color.BLUE);
		labelCash.setFont(new Font("Times New Roman", Font.BOLD, 24));

		labelTotal = new JLabel("");
		labelTotal.setBounds(113, 11, 84, 30);
		panel_1.add(labelTotal);
		labelTotal.setForeground(Color.BLUE);
		labelTotal.setFont(new Font("Times New Roman", Font.BOLD, 24));
		labelTotal.setText("0.0");

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 11, 77, 30);
		panel_1.add(lblTotal);
		lblTotal.setBackground(SystemColor.inactiveCaption);
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 24));

		JLabel lblCash = new JLabel("Cash:");
		lblCash.setBounds(11, 50, 77, 30);
		panel_1.add(lblCash);
		lblCash.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblCash.setBackground(SystemColor.inactiveCaption);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(255, 9, 563, 76);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		comboBoxYear = new JComboBox();
		comboBoxYear.setModel(new DefaultComboBoxModel(
				new String[] { "Select", "Nursery", "Play", "One", "Two", "Three", "Four", "Five", "Six" }));
		comboBoxYear.setBounds(84, 36, 27, 20);
		panel_2.add(comboBoxYear);
		comboBoxYear.setVisible(false);
		comboBoxSection = new JComboBox();
		comboBoxSection.setVisible(false);
		comboBoxSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String yr = comboBoxYear.getSelectedItem().toString();
				String sec = comboBoxSection.getSelectedItem().toString();
				comboBoxStudentID.removeAllItems();
				System.out.print("year " + yr + "and section " + sec);

				try {
					// String q="select stu_id from lis_student_info where
					// year='"+yr+"' and section='"+sec+"'";
					String q = "select stu_id from lis_student_info where year='" + yr + "'and section='" + sec + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					while (result.next()) {
						comboBoxStudentID.addItem(result.getString("STU_ID"));

					}

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		comboBoxSection.setModel(new DefaultComboBoxModel(new String[] { "Select ", "A", "B", "C", "D" }));
		comboBoxSection.setBounds(84, 22, 36, 20);
		panel_2.add(comboBoxSection);

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
						textFieldStudentName
								.setText(result.getString("FIRSTNAME") + " " + result.getString("LASTNAME"));
					} else {
						textFieldStudentName.setText("");
					}

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

				// String s_id=comboBoxStudentID.getSelectedItem().toString();
				try {
					String q = "select * from LIS_STUDENT_FEE_DETAILS where stu_id='" + st_id + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					tableFeePaid.setModel(DbUtils.resultSetToTableModel(result));

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				try {
					String q = "select month_fee,allow from LIS_F_ALLOWNCES where stu_id='" + st_id + "'";
					PreparedStatement prstatement = connection.prepareStatement(q);
					ResultSet result = prstatement.executeQuery();
					// tableAllownces.setModel(DbUtils.resultSetToTableModel(result));

				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		comboBoxStudentID.setBounds(84, 4, 36, 20);
		panel_2.add(comboBoxStudentID);

		textFieldStudentName = new JTextField();
		textFieldStudentName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldStudentName.setEditable(false);
		textFieldStudentName.setBounds(213, 47, 210, 20);
		panel_2.add(textFieldStudentName);
		textFieldStudentName.setColumns(10);

		JLabel labelLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		labelLogo.setIcon(new ImageIcon(logo));
		labelLogo.setBounds(10, 4, 64, 65);
		panel_2.add(labelLogo);

		textFieldStudentId = new JTextField();
		textFieldStudentId.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textFieldStudentId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String stu_id = textFieldStudentId.getText();
				getStudent(stu_id);
			}
		});
		textFieldStudentId.setColumns(10);
		textFieldStudentId.setBounds(213, 4, 210, 38);
		panel_2.add(textFieldStudentId);

		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setForeground(new Color(205, 133, 63));
		lblStudentId.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStudentId.setBounds(121, 20, 103, 20);
		panel_2.add(lblStudentId);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(205, 133, 63));
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(157, 45, 53, 20);
		panel_2.add(lblName);

		// data loading when the form is open
		feeNameIncomboBox();
		bookNameIncomboBox();
	}

	protected boolean checkPaid(String stu_id, String monthName) {
		String query = "select status from LIS_FEE_SCHEDUL where student_id='" + stu_id + "' and month_of_fee='"
				+ monthName + "' and status='" + "Paid" + "'";
		int i = 0;
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {

				prstatement.close();
				result.close();
				return true;
			} else
				return false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		return false;
	}

	protected String getStudentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void getStudent(String st_id) {

		try {
			String q = "select firstname,lastname from lis_student_info where stu_id='" + st_id + "'";
			PreparedStatement prstatement = connection.prepareStatement(q);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {
				textFieldStudentName.setText(result.getString("FIRSTNAME") + " " + result.getString("LASTNAME"));
				prstatement.close();
				result.close();
			} else {
				textFieldStudentName.setText("");
			}

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		// String s_id=comboBoxStudentID.getSelectedItem().toString();
		try {
			String q = "select * from LIS_FEE_SCHEDUL where student_id='" + st_id + "'";
			PreparedStatement prstatement = connection.prepareStatement(q);
			ResultSet result = prstatement.executeQuery();
			tableFeePaid.setModel(DbUtils.resultSetToTableModel(result));
			prstatement.close();
			result.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		// try {
		// String q="select month_fee,allow from LIS_F_ALLOWNCES where
		// stu_id='"+st_id+"'";
		// PreparedStatement prstatement=connection.prepareStatement(q);
		// ResultSet result=prstatement.executeQuery();
		// tableAllownces.setModel(DbUtils.resultSetToTableModel(result));
		// prstatement.close();
		// result.close();
		//
		// } catch (Exception e) {
		// //JOptionPane.showMessageDialog(null, e);
		// e.printStackTrace();
		// }

	}

	protected void checkAppStatus() {
		// TODO Auto-generated method stub
		String appDate =new DateFormateSettings().dateFormateCorrection(((JTextField) dateChooserCheckAppDate.getDateEditor().getUiComponent()).getText()) ;
				
		String id = textFieldCheckID.getText();
		String query = "select status from LIS_APP_FOR_LEAVE where id='" + id + "' and app_date='" + appDate + "'";
		String st = null;
		try {
			PreparedStatement prst = connection.prepareStatement(query);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				st = rs.getString("STATUS");
				if (st.equals("New")) {
					labelStatus.setText("Pending...");
					labelStatus.setForeground(Color.YELLOW);
					labelStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));

				} else if (st.equals("Declined")) {
					labelStatus.setText("Declined.");
					labelStatus.setForeground(Color.RED);
					labelStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
				} else {
					labelStatus.setText("Accepted.");
					labelStatus.setForeground(Color.green);
					labelStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));

				}
			} else
				JOptionPane.showMessageDialog(null, "OOOOps!!!\n Wrong ID or Application Date");

		} catch (Exception e) {
		}

	}

	protected void getTransportFare(String mnth) {
		// TODO Auto-generated method stub
		// String sid = comboBoxStudentID.getSelectedItem().toString();
		String sid = textFieldStudentId.getText();
		String query = "select vehichle from lis_student_info where stu_id='" + sid + "'";
		String veNo = null;
		try {
			PreparedStatement prst = connection.prepareStatement(query);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				veNo = rs.getString("VEHICHLE");
				rs.close();
				prst.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		String query2 = "select transport_amount from LIS_TRANSPORT_FEE_SETUP where vehichle_no='" + veNo + "'";
		String fare = null;
		try {
			PreparedStatement prstatement = connection.prepareStatement(query2);
			ResultSet result = prstatement.executeQuery(query2);
			while (result.next()) {
				fare = result.getString("transport_amount");
			}
			result.close();
			String items = String.valueOf((int) noOfItem);
			System.out.println("\nfare: " + fare);
			dataInShoppingCart("Transport", fare, items, mnth);

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Private Transport.");
		}

	}

	protected double getActualPriceFromDb(String prodID) {
		String query = "Select sales_price from LIS_PRODUCT_PRICE_SETUP where product_id='" + prodID + "'";
		double acp = 0;
		try {
			PreparedStatement prst = connection.prepareStatement(query);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				String acpr = rs.getString("SALES_PRICE");
				acp = Double.parseDouble(acpr);
				rs.close();
				prst.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return acp;
	}

	private void bookNameIncomboBox() {
		// TODO Auto-generated method stub
		comboBoxBookName.removeAllItems();
		String query = "select distinct PRODUCT_NAME from LIS_PRODUCT_PRICE_SETUP";

		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			while (result.next()) {
				comboBoxBookName.addItem(result.getString("PRODUCT_NAME"));

			}
			result.close();
			prstatement.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}

	private void feeNameIncomboBox() {
		// TODO Auto-generated method stub
		comboBoxFeeName.removeAllItems();
		String query = "select distinct FEE_NAME from LIS_FEE_CATEGORIES where fee_name!='" + "Tuition Fee" + "'";

		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			while (result.next()) {
				comboBoxFeeName.addItem(result.getString("FEE_NAME"));

			}
			result.close();
			prstatement.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}

	protected void getClassTestFee() {
		// TODO Auto-generated method stub
		String clas = comboBoxYear.getSelectedItem().toString();
		String p_id = comboBoxFeeName.getSelectedItem().toString();
		String feeId = comboBoxFeeID.getSelectedItem().toString();
		productname = p_id;
		String stu_id = textFieldStudentId.getText();

		String applyFor = getApplyfor(stu_id.substring(2, 4));

		// String query = "select amount from lis_fee_setup where fee_id='" +
		// feeId + "' and applicable_for='"+clas+"' ";
//		String query = "select amount from lis_fee_setup where fee_id='" + feeId + "' and applicable_for='" + applyFor+ "' ";
		String query = "select amount from lis_fee_setup where fee_id='" + feeId + "' ";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {
				String feeAmount = result.getString("AMOUNT");
				String items = String.valueOf((int) noOfItem);
				dataInShoppingCart(productname, feeAmount, items, p_id);

			}
			result.close();
			prstatement.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String getApplyfor(String yr) {
		String applyFor = null;

		if (yr.equals("00"))
			applyFor = "Nursery";
		else if (yr.equals("01"))
			applyFor = "Play";
		else if (yr.equals("02"))
			applyFor = "One";
		else if (yr.equals("03"))
			applyFor = "Two";
		else if (yr.equals("04"))
			applyFor = "Three";
		else if (yr.equals("05"))
			applyFor = "Four";
		else if (yr.equals("06"))
			applyFor = "Five";
		else if (yr.equals("07"))
			applyFor = "Six";
		else if (yr.equals("08"))
			applyFor = "Seven";
		else
			applyFor = "All";

		return applyFor;
	}

	protected void updateScholarshipTimePeriod(String stu_id) {
		// TODO Auto-generated method stub
		if (deductSchTimeperiod > timePeriod) {
			timePeriod = 0;
		} else {
			timePeriod = timePeriod - deductSchTimeperiod;

		}
		String query = "update LIS_SCHOLARSHIP_DETAILS set time_period ='" + timePeriod + "' where stu_id='" + stu_id
				+ "' and time_period>0 ";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			prstatement.executeQuery();
			deductSchTimeperiod = 0;
			prstatement.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	double timePeriod = 0;

	protected double getScholarship(String s_id, double tFee) {
		// TODO Auto-generated method stub
		double schPer = 0;
		String p_id = "Scholarship";
		String query = "select persentage,time_period from LIS_SCHOLARSHIP_DETAILS where stu_id='" + s_id
				+ "' and time_period>0";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {
				String sch = result.getString("PERSENTAGE");
				schPer = Double.parseDouble(sch);
				double amnt = (schPer * tFee) / 100;

				String deductSch = String.valueOf(amnt);

				String tp = result.getString("TIME_PERIOD");
				timePeriod = Double.parseDouble(tp);
				productname = "Scholarship " + sch + "%";
				String items = String.valueOf((int) noOfItem);
				if (timePeriod - deductSchTimeperiod > 0) {
					dataInShoppingCart(productname, "-" + deductSch, items, p_id);
				}
				result.close();
				prstatement.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return schPer;
	}

	protected void getLateFeeFine(String monthName) {
		// TODO Auto-generated method stub
		String typ = "LateFee";
		String query = "select amount from lis_fine_setup where fine_type='" + typ + "'";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {
				System.out.println("Late Fee Fine: " + result.getString("AMOUNT"));
				productname = monthName + " Fine";
				String p_id = "LateFeeFine";
				String str2 = result.getString("AMOUNT");
				String prc = str2;
				String items = String.valueOf((int) noOfItem);
				result.close();
				prstatement.close();
				dataInShoppingCart(productname, prc, items, p_id);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	protected boolean checkFineAllownces(String stu_id, String mnth) {
		// TODO Auto-generated method stub
		String query = "select allow from LIS_F_ALLOWNCES where stu_id='" + stu_id + "'  and allow='" + "free"
				+ "' and month_fee='" + mnth + "'";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {
				result.close();
				prstatement.close();
				return true;
			} else
				return false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			// e.printStackTrace();
		}
		return false;
	}

	String[] month = new String[12];
	int mnt = 0;

	protected boolean checkDueTuitionFee(String stu_id, String monthName) {
		String query = "select status from LIS_FEE_SCHEDUL where student_id='" + stu_id + "' and month_of_fee='"
				+ monthName + "' and status='" + "Overdue" + "'";
		int i = 0;
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet result = prstatement.executeQuery();
			if (result.next()) {
				// System.out.print("Due:"+result.getString("FEE_STATUS"));
				month[mnt++] = monthName;
				// System.out.println("Due Month name :"+month[i-1]);
				prstatement.close();
				result.close();
				return true;
			} else
				return false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return false;
	}

	// Find late fee allownces from database
	String[] allow = new String[12];

	private boolean checkLateFeeAllownces(String stu_id, String monthName) {

		String query = "select * from LIS_F_ALLOWNCES where stu_id='" + stu_id + "' and month_fee='" + monthName
				+ "' and allow='" + "free" + "'";
		int i = 0, a = 0;
		try {

			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet resul = prstatement.executeQuery();
			if (resul.next()) {
				// allow[a++]=result.getString("ALLOW");
				// check
				prstatement.close();
				resul.close();
				return true;

			} else
				return false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return false;

	}

	protected void tuitionFeeStatus() {

		// String stu_id=comboBoxStudentID.getSelectedItem().toString();
		String stu_id = textFieldStudentId.getText();

		// if(checkDueTuitionFee(stu_id,))
		int j = 0;
		for (int i = 0; i < productIdIndlm.size(); i++) {
			System.out.println("product:" + month[i]);

			if (month[j] == productNameInDLM.get(i).toString()) {
				String mn = productNameInDLM.get(i).toString();
				// String mnth=mn.substring(0, mn.indexOf(" "));

				j++;

				String query = "update lis_fee_schedul set status='" + "Paid" + "' where student_id='" + stu_id
						+ "' and " + " month_of_fee='" + mn + "'";
				try {
					PreparedStatement prstatement = connection.prepareStatement(query);
					prstatement.executeQuery();
					prstatement.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		for (int ii = 0; ii <= i; ii++) {
			month[ii] = null;
		}
		i = 0;
	}

	protected void clearStudentInfo() {
		// TODO Auto-generated method stub
		comboBoxYear.setSelectedIndex(0);
		comboBoxSection.setSelectedIndex(0);
		// comboBoxStudentID.setSelectedIndex(0);
		// comboBoxPicnicPlace.setSelectedIndex(0);
		textFieldStudentName.setText("");
		textFieldStudentId.setText(null);

	}

	int i = 0;

	protected void getTuitionFee(String yr, String stu_id, String monthly, String monthName) {

		String applyFor = getApplyfor(yr);
		System.out.println("applyfor:" + applyFor);
		month[i++] = monthName;
		String query = "Select * from lis_fee_setup where applicable_for='" + applyFor + "'";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet rs = prstatement.executeQuery();

			if (rs.next()) {

				productname = monthName;
				String p_id = rs.getString("FEE_NAME");
				String str2 = rs.getString("AMOUNT");
				tFee = Double.parseDouble(str2); // deduct scholarship from
													// tuition fee

				rs.close();
				prstatement.close();
				String prc = str2;
				String items = String.valueOf((int) noOfItem);

				dataInShoppingCart(productname, prc, items, p_id);
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please Select Student ID!!!");
		}
		// textField_search.setText("");

	}

	private void dataInShoppingCart(String productname2, String prc, String items, String p_id) {
		// TODO Auto-generated method stub

		/*
		 * String yORn=rs.getString("offer"); //String yORn="n";//this is not
		 * for offer if offer working ommit this line char yon=yORn.charAt(0);
		 * if(yon=='y'){ int flag=0; for(int i=0;i<=o;i++){
		 * 
		 * if(product.equals(isOffer[i])){ flag=1; break; } } if(flag==0){
		 * isOffer[o++]=product; //keep the offered product in isoffer array.
		 * 
		 * } }
		 */

		productname = productname2;
		priceIndlm.addElement(prc); // price
		price = Double.parseDouble(prc);
		double subtotal = price * noOfItem;
		total = total + subtotal;
		double value = Math.round(total * 100.0) / 100.0;
		String str4 = String.valueOf(value);

		String subttl = String.valueOf(subtotal);

		labelTotal.setText(str4);
		String str3 = productname + "     " + items + "    " + subttl;
		dlm.addElement(str3); // basket

		itemNoIndlm.addElement(noOfItem); // quantity

		productNameInDLM.addElement(productname);
		productIdIndlm.addElement(p_id); /// PRODUCT ID
		ii++;
		// productFound=true; //if the product find from the system then clear
		// the text_search field.
		textField_search.setText("");

		list.setModel(dlm);

	}

	String productname;
	int o = 0;

	private void getDataFromProductList(String product) {

		// String query="Select product_name,sales_price from productdetails
		// where product_id='"+product+"'";
		String query = "Select product_name,sales_price,offer from LIS_PRODUCT_PRICE_SETUP where product_id='" + product
				+ "'";// this is for offer query
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet rs = prstatement.executeQuery();

			if (rs.next()) { // if any product got offer like 3 for £1,so
								// tracking the offered product id
				String yORn = rs.getString("offer");
				// String yORn="n";//this is not for offer if offer working
				// ommit this line
				char yon = yORn.charAt(0);
				if (yon == 'y') {
					int flag = 0;
					for (int i = 0; i <= o; i++) {

						if (product.equals(isOffer[i])) {
							flag = 1;
							break;
						}
					}
					if (flag == 0) {
						isOffer[o++] = product;

					}
				}

				productname = rs.getString("product_name");
				String prc = rs.getString("sales_price");

				// dataInShoppingCart(productname,prc);
				priceIndlm.addElement(prc); // price
				price = Double.parseDouble(prc);
				double subtotal = price * noOfItem;
				total = total + subtotal;
				double value = Math.round(total * 100.0) / 100.0;
				String str4 = String.valueOf(value);
				String items = String.valueOf((int) noOfItem);

				String subttl = String.valueOf(subtotal);

				labelTotal.setText(str4);
				String str3 = productname + "     " + items + "    " + subttl;
				dlm.addElement(str3); // basket

				itemNoIndlm.addElement(noOfItem); // quantity

				productIdIndlm.addElement(product); // product id
				productNameInDLM.addElement(productname);
				ii++;
				// productFound=true; //if the product find from the system then
				// clear the text_search field.
				textField_search.setText("");
			}
			list.setModel(dlm);

			rs.close();
			prstatement.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// textField_search.setText("");
	}

	// if taking multi-products
	private void takeNoOfItems() {
		String str = textFieldDisplay.getText();
		double ddd = Double.parseDouble(str);
		String parsString = String.valueOf((int) ddd);
		int iii = Integer.parseInt(parsString);

		// System.out.println("iii: "+iii);

		if (iii > 1) {
			try {
				int len = itemNoIndlm.size() - 1; // length of lists
				// noOfItem=Double.parseDouble(str);
				//// noOfItem=Integer.parseInt(str);
				double stotal = Double.parseDouble(priceIndlm.get(len).toString()) * iii;
				double temp = Double.parseDouble(priceIndlm.get(len).toString());
				total = total - temp;

				itemNoIndlm.remove(len); // remove quantity
				priceIndlm.remove(len); // remove price
				dlm.remove(len); // remove item + no of item + price from
									// shopping basket

				itemNoIndlm.add(len, iii);// add quantity in dlm

				priceIndlm.add(len, stotal);

				String ss = itemNoIndlm.get(len).toString();
				double ii = Double.parseDouble(ss);
				String sss = String.valueOf((int) ii);
				String st = productname + "   " + sss + "  " + priceIndlm.get(len).toString();
				dlm.addElement(st);
				list.setModel(dlm);
				// edit price...................
				total = total + Double.parseDouble(priceIndlm.get(len).toString());
				String str4 = String.valueOf(total);
				labelTotal.setText(str4);

				// make initial state of calculator
				cal_initial = 0.0;
				textFieldDisplay.setText("0.");

				noOfItem = 1;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Insert the numbur of quantity please");
		}
		// "Take any product first please."
	}

	private void invoice() {
		// String customerName=comboBoxStudentID.getSelectedItem().toString();

		String studentId = textFieldStudentId.getText();
		// String employee_id="employee_id";
		Employee emp_id = new Employee();
		String employee_id = emp_id.getEmployeeID();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		invoice_srl = dateFormat.format(cal.getTime());
		System.out.println(dateFormat.format(cal.getTime()));
		DateFormat dat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar canl = Calendar.getInstance();
		String d = (String) dat.format(canl.getTime());
		System.out.println(d);

		String query = "Insert into lis_sales_invoice values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prstatement = connection.prepareStatement(query);
			prstatement.setString(1, invoice_srl);
			prstatement.setString(2, d);
			prstatement.setString(3, studentId);
			prstatement.setString(4, employee_id);
			prstatement.setDouble(5, total);
			prstatement.setDouble(6, cashPay);
			prstatement.setDouble(7, change);
			prstatement.executeQuery();
			prstatement.close();
			// JOptionPane.showMessageDialog(null, "Saved data in
			// sales_invoice");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "problem in sales invoice");
		}

		ii = 0;
		labelTotal.setText("0.0");
		labelCash.setText("0.0");
		labelChange.setText("0.0");

	}

	private void invoiceDetails() {
		int quantity = 1, discount = 0;
		// System.out.println("Invoice details:");

		double subPrice;
		try {
			for (int i = 0; i < productIdIndlm.size(); i++) {
				String query = "Insert into LIS_SALES_INVOICE_DETAILS values(?,?,?,?,?,?)";

				String sub_price = priceIndlm.get(i).toString();
				double sp = Double.parseDouble(sub_price);
				subPrice = sp * quantity;
				PreparedStatement prstatement = connection.prepareStatement(query);
				prstatement.setString(1, invoice_srl);
				prstatement.setString(2, productIdIndlm.get(i).toString());
				prstatement.setString(3, productNameInDLM.get(i).toString());
				prstatement.setInt(4, Integer.parseInt(itemNoIndlm.get(i).toString()));
				prstatement.setInt(5, discount);
				prstatement.setDouble(6, sp);
				prstatement.executeQuery();
				prstatement.close();
				System.out.println("save  quantity in sales_invoice_details :" + itemNoIndlm.get(i));

			}
			// JOptionPane.showMessageDialog(null, "Saved data");
			// deleteDLM();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "problelm in LIS sales_invoice_details");
		}

	}

	private void deleteDLM() {
		for (int j = productIdIndlm.size() - 1; j >= 0; j--) {

			productIdIndlm.remove(j);
			productNameInDLM.remove(j);
			dlm.remove(j);
			priceIndlm.remove(j);
			itemNoIndlm.remove(j);

			System.out.print("J: " + j);
		}

		textFieldDisplay.setText("0.");
		total = 0.0;
		cal_initial = 0.0;

	}

	double change = 0.0;
	double cashPay = 0.0;

	private void payment() {
		String cashPayment = textFieldDisplay.getText();
		cashPay = Double.parseDouble(cashPayment);
		total = Math.round(total * 100.0) / 100.0;
		change = cashPay - total;
		double change_value = Math.round(change * 100.0) / 100.0;

		// System.out.println("Change: "+change_value);
		// System.out.println("Cash pay: "+cashPay);
		// System.out.println("total: "+total);

	}

	private void report() {
		// making report
		try {
			JasperDesign jd = JRXmlLoader.load("C:\\lis\\report\\transection.jrxml");

			// JasperDesign
			// jd=JRXmlLoader.load("C:\\Users\\creativepc\\workspace\\GuiPractice\\shoppingCartReport.jrxml");
			String query = "select salinvo.invoice_id,salinvo.paid_date,salinvo.employeeid,salinvo.total,salinvo.cash,salinvo.change,indetails.product_name,indetails.product_quantity,indetails.subtotal from lis_sales_invoice salinvo join lis_sales_invoice_details indetails on salinvo.invoice_id=indetails.invoice_id where salinvo.invoice_id='"
					+ invoice_srl + "'";
			// String query="select
			// pro.product_name,salinvo.invoice_id,salinvo.paid_date,salinvo.employeeid,salinvo.total,salinvo.cash,salinvo.change,indetails.product_quantity,pro.sales_price,indetails.subtotal
			// from lis_sales_invoice salinvo join lis_sales_invoice_details
			// indetails on salinvo.invoice_id=indetails.invoice_id join
			// LIS_PRODUCT_PRICE_SETUP pro on
			// indetails.product_id=pro.product_id where
			// salinvo.invoice_id='"+invoice_srl+"'";
			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(query);
			jd.setQuery(newQuery);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);
			// JasperViewer.viewReport(jp);

			JRViewer test = new JRViewer(jp);

			JFrame frame = new JFrame("Report");
			frame.getContentPane().add(test); // new JRViewer(jasperPrint)
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.pack();
			frame.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void print() {
		// report print
	}

	private void deleteItem() {
		int index = list.getSelectedIndex();

		if (index > -1) {
			dlm.remove(index);
			productIdIndlm.remove(index);
			itemNoIndlm.remove(index);
			productNameInDLM.remove(index);
			// System.out.println("price in
			// index:"+priceIndlm.get(index).toString());
			String str = priceIndlm.get(index).toString();
			double temp = Double.parseDouble(str);
			total = total - temp;
			total = Math.round(total * 100.0) / 100.0;
			String str4 = String.valueOf(total);
			labelTotal.setText(str4);
			priceIndlm.remove(index);
			// System.out.println("Product
			// Id:"+productIdIndlm.get(index).toString());
			// trakingTheDeletedProducts(productIdIndlm.get(index).toString());//keep
			// record of the deleted product.
		} else {
			JOptionPane.showMessageDialog(null, "Select any item to delete please!");

		}

	}

	private void trakingTheDeletedProducts(String product_id) {
		// TODO Auto-generated method stub

		DateFormat dat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar canl = Calendar.getInstance();
		String d = (String) dat.format(canl.getTime());
		DateFormat tim = new SimpleDateFormat("HH:mm:ss");
		Calendar cl = Calendar.getInstance();
		String ti = (String) tim.format(cl.getTime());
		System.out.println(ti);
		/*
		 * Employee emp=new Employee(); String emp_id=emp.getEmployeeID();
		 * System.out.println(emp_id);
		 */

		String query = "insert into employee_deleted_products values(to_date(?,'dd-mon-yy'),to_date(?,'hh24:mi:ss'),?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(query);
			prst.setString(1, d);
			prst.setString(2, ti);
			prst.setString(3, "emp_id");
			prst.setString(4, product_id);
			prst.executeQuery();
			prst.close();
			// JOptionPane.showMessageDialog(null, "success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	static double cal_initial = 0.0;
	private JTextField textFieldStudentName;
	private JTable tableFeePaid;
	private JTextField textFieldCheckID;
	private JTextField textFieldID;
	private JTextField textFieldStudentId;

	private void calculator(double num) {
		double temp;
		cal_initial = cal_initial * 10 + num;
		temp = cal_initial / 100;
		String s = String.valueOf(temp);
		textFieldDisplay.setText(s);

	}

	private void deductProductFromDatabase() {
		try {
			for (int i = 0; i < productIdIndlm.size(); i++) {
				String no_pro = itemNoIndlm.get(i).toString();
				int nPro = Integer.parseInt(no_pro);
				// System.out.println("product ID:
				// "+productIdIndlm.get(i).toString());
				System.out.println("product no todeduct : " + nPro);
				// String query="update productdetails set
				// quantity=quantity-(select product_quantity from
				// sales_invoice_details where
				// product_id='"+productIdIndlm.get(i).toString()+"'and
				// invoice_id='"+invoice_srl+"')where
				// product_id='"+productIdIndlm.get(i).toString()+"'";
				String query = "update LIS_PRODUCT_PRICE_SETUP set quantity=quantity-'" + nPro + "'where product_id='"
						+ productIdIndlm.get(i).toString() + "'";
				PreparedStatement prstatement = connection.prepareStatement(query);
				prstatement.executeQuery();
				prstatement.close();

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		deleteDLM();
	}

	private void cashDetails(String key) {
		double cash_in = 0.0, cash_out = 0.0, sale = 0.0, cash_flow = 0.0;
		DateFormat dat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar canl = Calendar.getInstance();
		String d = (String) dat.format(canl.getTime());
		// System.out.println(d);
		if (key.equals("in")) {
			cash_in = Double.parseDouble(textFieldDisplay.getText());
			JOptionPane.showMessageDialog(null, "Cash In " + "£" + cash_in);

		} else if (key.equals("out")) {
			cash_out = Double.parseDouble(textFieldDisplay.getText());
			JOptionPane.showMessageDialog(null, "Cash Out " + "£" + cash_out);

		} else if (key.equals("cashflow")) {
			cash_flow = Double.parseDouble(textFieldDisplay.getText());
		} else if (key.equals("sale")) {
			sale = total;
		}

		String query = "insert into lis_cash_details values(?,?,?,?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(query);

			prst.setString(1, d);
			prst.setDouble(2, cash_in);
			prst.setDouble(3, cash_out);
			prst.setDouble(4, cash_flow);
			prst.setDouble(5, sale);
			prst.executeQuery();
			prst.close();

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}

		cal_initial = 0.0;
		textFieldDisplay.setText("0.");

	}

	private void sessionLogout() {

		Employee empid = new Employee();
		String empId = empid.getEmployeeID();
		// String empId="njj";
		DateFormat logTime = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		DateFormat dat = new SimpleDateFormat("dd-MMM-yy");

		Calendar canl = Calendar.getInstance();
		String logoutTime = (String) logTime.format(canl.getTime());
		String loginDate = (String) dat.format(canl.getTime());

		// System.out.println("time:"+logoutTime+" date:"+loginDate);
		// System.out.println("employeeid logout:"+empId);
		String query = "update lis_sessions set logout_time=to_date('" + logoutTime
				+ "','dd-mm-yy hh24:mi:ss') where employeeid='" + empId + "' and to_date(login_time,'dd-mm-yy')='"
				+ loginDate + "' and regular_time=0";
		try {
			PreparedStatement prst = connection.prepareStatement(query);
			prst.executeQuery();
			prst.close();
			JOptionPane.showMessageDialog(null, "logout successfully");

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();

		}
	}
}
