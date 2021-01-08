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


public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textField_search;
	public Connection connection=null;
	private JScrollPane scroll;
	JList list;
	JLabel labelTotal;
	JLabel labelCash,labelChange;
	private DefaultListModel dlm=new DefaultListModel();
	private DefaultListModel productIdIndlm=new DefaultListModel();
	private DefaultListModel priceIndlm=new DefaultListModel();
	private DefaultListModel itemNoIndlm=new DefaultListModel();
	private DefaultListModel productNameInDLM=new DefaultListModel();
	JComboBox comboBoxFeeID,comboBoxFeeName;
	private String isOffer[]=new String[50];
	private String invoice_srl;
	int noOfItem=1;
	double tFee=0;
	double deductSchTimeperiod=0;
	double total=0.0,price;
	int ii=0;
	int Q[]=new int[50];
	double save=0.0;
	private JTextField textFieldDisplay;
	
	JComboBox comboBoxPicnicPlace;
	JComboBox comboBoxSection,comboBoxYear,comboBoxStudentID;
	
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Home() {
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("customer3.png")));
		setTitle("London Intelligence School"); 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		JTabbedPane tabbed_food = new JTabbedPane(JTabbedPane.TOP);
		tabbed_food.setBounds(449, 89, 534, 437);
		contentPane.add(tabbed_food);
	
		JPanel panel_TuitionFee = new JPanel();
		panel_TuitionFee.setBackground(Color.WHITE);
		tabbed_food.addTab("Tuition Fee", null, panel_TuitionFee, null);
		tabbed_food.setBackgroundAt(0, new Color(240, 230, 140));
		panel_TuitionFee.setLayout(null);
		
		JButton btnJanuary = new JButton("");
		btnJanuary.setBackground(Color.WHITE);
		Image img=new ImageIcon(this.getClass().getResource("/Jan.png")).getImage();
		btnJanuary.setIcon(new ImageIcon(img));
		btnJanuary.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"January");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"January");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","January");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","January");
								getLateFeeFine("January");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","January");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select Student ID !!!");
				}
				
				//System.out.print("student year and id "+yr+" "+stu_id);
			}
		});
		btnJanuary.setForeground(new Color(205, 133, 63));
		btnJanuary.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnJanuary.setBounds(10, 21, 82, 83);
		panel_TuitionFee.add(btnJanuary);
		
		JButton btnFebruary = new JButton("");
		btnFebruary.setBackground(Color.WHITE);
		Image imgfeb=new ImageIcon(this.getClass().getResource("/Feb.png")).getImage();
		btnFebruary.setIcon(new ImageIcon(imgfeb));
		btnFebruary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"February");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"February");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","February");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","February");
								getLateFeeFine("February");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","February");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						//update time period table for scholarship
						//updateScholarshipTimePeriod(stu_id);
						deductSchTimeperiod++;
					}


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
		Image imgmar=new ImageIcon(this.getClass().getResource("/Mar.png")).getImage();
		btnMar.setIcon(new ImageIcon(imgmar));
		btnMar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"March");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"March");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","March");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","March");
								getLateFeeFine("March");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","March");
					}
					double scholarship=getScholarship(stu_id,tFee);

					if(scholarship>0){
						//update time period table for scholarship
						//updateScholarshipTimePeriod(stu_id);
						deductSchTimeperiod++;
					}

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
		Image imgapr=new ImageIcon(this.getClass().getResource("/Apr.png")).getImage();
		btnApr.setIcon(new ImageIcon(imgapr));
		btnApr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"April");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"April");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","April");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","April");
								getLateFeeFine("April");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","April");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}


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
		Image imgmay=new ImageIcon(this.getClass().getResource("/May.png")).getImage();
		btnMay.setIcon(new ImageIcon(imgmay));
		btnMay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"May");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"May");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","May");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","May");
								getLateFeeFine("May");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","May");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		Image imgjun=new ImageIcon(this.getClass().getResource("/Jun.png")).getImage();
		btnJun.setIcon(new ImageIcon(imgjun));
		btnJun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"Jun");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"Jun");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","Jun");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","Jun");
								getLateFeeFine("Jun");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","Jun");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		Image imgjul=new ImageIcon(this.getClass().getResource("/Jul.png")).getImage();
		btnJul.setIcon(new ImageIcon(imgjul));
		btnJul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"July");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"July");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","July");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","July");
								getLateFeeFine("July");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","July");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}
					
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
		Image imgaug=new ImageIcon(this.getClass().getResource("/Aug.png")).getImage();
		btnAug.setIcon(new ImageIcon(imgaug));
		btnAug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"August");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"August");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","August");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","August");
								getLateFeeFine("August");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","August");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		Image imgsep=new ImageIcon(this.getClass().getResource("/Sep.png")).getImage();
		btnSep.setIcon(new ImageIcon(imgsep));
		btnSep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"Sept.");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"Sept.");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","Sept.");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","Sept.");
								getLateFeeFine("Sept.");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","Sept.");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		Image imgoct=new ImageIcon(this.getClass().getResource("/Oct.png")).getImage();
		btnOct.setIcon(new ImageIcon(imgoct));
		btnOct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"October");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"October");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","October");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","October");
								getLateFeeFine("October");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","October");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		Image imgnov=new ImageIcon(this.getClass().getResource("/Nov.png")).getImage();
		btnNov.setIcon(new ImageIcon(imgnov));
		btnNov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"November");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"November");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","November");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","November");
								getLateFeeFine("November");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","November");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		Image imgdec=new ImageIcon(this.getClass().getResource("/Dec.png")).getImage();
		btnDec.setIcon(new ImageIcon(imgdec));
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean fine=false;
					String yr=comboBoxYear.getSelectedItem().toString();
					String stu_id=comboBoxStudentID.getSelectedItem().toString();
					boolean due=checkDueTuitionFee(stu_id,"December");
					if(due){
						 fine=checkLateFeeAllownces(stu_id,"December");
					
						 if(fine){
								System.out.println("Allownces Free");

							 getTuitionFee(yr,stu_id,"monthly","December");
							 fine=false;
						 }
						 else{
								//System.out.println("get tuition fee with fine");
								getTuitionFee(yr,stu_id,"monthly","December");
								getLateFeeFine("December");

						 }
					}
					else{
						getTuitionFee(yr,stu_id,"monthly","December");
					}
					double scholarship=getScholarship(stu_id,tFee);
					if(scholarship>0){
						deductSchTimeperiod++;
					}

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
		scrollPane_1.setBounds(10, 306, 385, 85);
		panel_TuitionFee.add(scrollPane_1);
		
		tableFeePaid = new JTable();
		scrollPane_1.setViewportView(tableFeePaid);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(397, 306, 122, 85);
		panel_TuitionFee.add(scrollPane_2);
		
		tableAllownces = new JTable();
		scrollPane_2.setViewportView(tableAllownces);
		
		
		JPanel panel_TermFee = new JPanel();
		tabbed_food.addTab(" Fees", null, panel_TermFee, null);
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
		 		
		 		String str=comboBoxFeeName.getSelectedItem().toString();
		 		comboBoxFeeName.removeItem("garbeg");
		 		//comboBoxFeeName.removeAllItems();
				String query="select distinct FEE_ID from LIS_FEE_CATEGORIES where fee_name='"+str+"'";
				
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					ResultSet result=prstatement.executeQuery();
					while(result.next()){
						comboBoxFeeID.addItem(result.getString("FEE_ID"));
						
					}	
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					//e.printStackTrace();
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
				getClassTestFee("calssTest");
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
		
		
		JPanel panel_Books = new JPanel();
		tabbed_food.addTab("Books", null, panel_Books, null);
		tabbed_food.setBackgroundAt(2, new Color(230, 230, 250));
		panel_Books.setLayout(null);
		
		JPanel panel_Stationary = new JPanel();
		tabbed_food.addTab("Stationaries", null, panel_Stationary, null);
		panel_Stationary.setLayout(null);
		
		JButton button_23 = new JButton("");
		button_23.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		button_23.setBounds(40, 107, 102, 50);
		panel_Stationary.add(button_23);
		
		JButton button_24 = new JButton("");
		button_24.setBounds(145, 107, 102, 50);
		panel_Stationary.add(button_24);
		
		JButton button_25 = new JButton("");
		button_25.setBounds(249, 107, 102, 50);
		panel_Stationary.add(button_25);
		
		JButton button_26 = new JButton("");
		button_26.setBounds(355, 107, 102, 50);
		panel_Stationary.add(button_26);
		
		JButton button_27 = new JButton("");
		button_27.setBounds(40, 159, 102, 50);
		panel_Stationary.add(button_27);
		
		JButton button_28 = new JButton("");
		button_28.setBounds(145, 159, 102, 50);
		panel_Stationary.add(button_28);
		
		JButton button_29 = new JButton("");
		button_29.setBounds(249, 159, 102, 50);
		panel_Stationary.add(button_29);
		
		JButton button_30 = new JButton("");
		button_30.setBounds(355, 159, 102, 50);
		panel_Stationary.add(button_30);
		tabbed_food.setBackgroundAt(3, new Color(240, 255, 240));
		
		JPanel panel_Transport = new JPanel();
		tabbed_food.addTab("Transport", null, panel_Transport, null);
		panel_Transport.setLayout(null);
		
		JButton button_15 = new JButton("");
		button_15.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		button_15.setBounds(38, 78, 102, 50);
		panel_Transport.add(button_15);
		
		JButton button_16 = new JButton("");
		button_16.setBounds(143, 78, 102, 50);
		panel_Transport.add(button_16);
		
		JButton button_17 = new JButton("");
		button_17.setBounds(247, 78, 102, 50);
		panel_Transport.add(button_17);
		
		JButton button_18 = new JButton("");
		button_18.setBounds(353, 78, 102, 50);
		panel_Transport.add(button_18);
		
		JButton button_19 = new JButton("");
		button_19.setBounds(38, 130, 102, 50);
		panel_Transport.add(button_19);
		
		JButton button_20 = new JButton("");
		button_20.setBounds(143, 130, 102, 50);
		panel_Transport.add(button_20);
		
		JButton button_21 = new JButton("");
		button_21.setBounds(247, 130, 102, 50);
		panel_Transport.add(button_21);
		
		JButton button_22 = new JButton("");
		button_22.setBounds(353, 130, 102, 50);
		panel_Transport.add(button_22);
		tabbed_food.setBackgroundAt(4, new Color(216, 191, 216));
		
		JPanel panel_3 = new JPanel();
		tabbed_food.addTab("Others", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnPicnic = new JButton("");
		btnPicnic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnPicnic.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnPicnic.setBounds(10, 11, 102, 50);
		panel_3.add(btnPicnic);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(115, 11, 102, 50);
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(219, 11, 102, 50);
		panel_3.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(325, 11, 102, 50);
		panel_3.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(325, 63, 102, 50);
		panel_3.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(10, 63, 102, 50);
		panel_3.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setBounds(115, 63, 102, 50);
		panel_3.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setBounds(219, 63, 102, 50);
		panel_3.add(button_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(10, 136, 241, 69);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
	    comboBoxPicnicPlace = new JComboBox();
		comboBoxPicnicPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
			}
		});
		comboBoxPicnicPlace.setBounds(51, 25, 118, 20);
		panel_4.add(comboBoxPicnicPlace);
		
		JLabel lblNewLabel = new JLabel("Place");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 28, 41, 14);
		panel_4.add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 double amnt = 0;
				 String pls;
					 pls=comboBoxPicnicPlace.getSelectedItem().toString();
					String query="select * from LIS_FEE_SETUP where fee_id='"+pls +"'";
					//System.out.print(pls);

					try {
						PreparedStatement prstatement=connection.prepareStatement(query);
						ResultSet result=prstatement.executeQuery();
						if(result.next()){
							
							//amnt=Double.parseDouble(result.getString("AMOUNT"));
							String items=String.valueOf((int)noOfItem);
							
							String p_id=result.getString("FEE_ID");

							dataInShoppingCart(pls,result.getString("AMOUNT"),items,p_id);
							//System.out.print(pls);
						
						}
						//return amnt;
						
					} catch (Exception e) {
						//JOptionPane.showMessageDialog(null, e);
						e.printStackTrace();
					}
				


			}
		});
		btnOk.setBounds(169, 24, 67, 23);
		panel_4.add(btnOk);
		
		JLabel lblPicnic = new JLabel("Picnic");
		lblPicnic.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPicnic.setBounds(83, 7, 67, 14);
		panel_4.add(lblPicnic);
		tabbed_food.setBackgroundAt(5, new Color(210, 180, 140));
		getPicnicPlace();
		
		textField_search = new JTextField();
		textField_search.setBounds(36, 63, 201, 28);
		textField_search.addKeyListener(new KeyAdapter() {
			/*@Override
			public void keyReleased(KeyEvent arg0) {
				
				
				getDataFromProductList(textField_search.getText());// product search from database after scanning the bar code
				
				textField_search.setText("");5060172541683
				5060118541623
				5060172541683
				
				
				
				
			}*/
			@Override
			public void keyPressed(KeyEvent arg0) {
				String str=textField_search.getText();
				getDataFromProductList(str);// product search from database after scanning the bar code
				
				

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
					//PrintWriter pw=new PrintWriter("C:\\login.txt");
					PrintWriter pw=new PrintWriter("C:\\possystem\\logfile\\login.txt");
					pw.println("");
					pw.close();
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
		 		String cashPayment=textFieldDisplay.getText();
				double cashPay=Double.parseDouble(cashPayment);
				if(cashPay>=total){
			 		if(dlm.size()>0){
			 			payment();
				 		invoice();
						invoiceDetails();
						tuitionFeeStatus();
			 			cashDetails("sale");

						report();
						print();
						deductProductFromDatabase();
						if(deductSchTimeperiod>0){
							String stId=comboBoxStudentID.getSelectedItem().toString();
							updateScholarshipTimePeriod(stId);
						}

						deleteDLM();
						clearStudentInfo();
						
				 	}else{
				 			JOptionPane.showMessageDialog(null, "Basket is empty,Take any product please");
					 	}
				}else{
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
		 		cal_initial=0.0;
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
		 		char c=event.getKeyChar();
		 		if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
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
		 		
				 if(o>=1){
					 calculating_offered_product();
					 calculating_offered_price();
					 
				 }
		 		payment();
		 		cashPay=Math.round(cashPay*100.0)/100.0;
		 		String str=String.valueOf(cashPay);
		 		labelCash.setText(str);
		 		change=Math.round(change*100.0)/100.0;
		 		String chn=String.valueOf(change);
		 		labelChange.setText(chn);
		 		
		 	}

			private void calculating_offered_price() {
				double total_reduced_price=0.0;
				double reduced_price=0.0;
				double ttl_offr_prd_and_price=0.0;
				for(int i=0;i<o;i++){
					String query="Select offer_quantity,offer_price from offer_details where product_id='"+isOffer[i]+"'";
					try {
						PreparedStatement prstatement=connection.prepareStatement(query);
						ResultSet rs=prstatement.executeQuery();
						
						if(rs.next()){
							int offer_quantity=Integer.parseInt(rs.getString("OFFER_QUANTITY"));
							double offer_price=Double.parseDouble(rs.getString("OFFER_PRICE"));
							//System.out.println("ofer quantity:"+offer_quantity+" offer price: "+offer_price);
							int reminder=Q[i]%offer_quantity;
							int div_result=Q[i]/offer_quantity;
							//System.out.println("reminder:"+reminder+" div result:"+div_result);
							double actual_price = 0.0;
							for(int j=0;j<productIdIndlm.size();j++){
								if(isOffer[i].equals(productIdIndlm.get(j))){
									String pr= priceIndlm.get(j).toString();
									actual_price=Double.parseDouble(pr);
									ttl_offr_prd_and_price=ttl_offr_prd_and_price+Q[i]*actual_price;
									//System.out.println("ttl_offer_products actual Price:"+ttl_offr_prd_and_price);

									break;
								}
							}

							reduced_price=div_result*offer_price+reminder*actual_price;
							
							//System.out.println("reduced Price:"+reduced_price);
						}
					} catch (Exception e) {
						// TODO: handle exception
						//JOptionPane.showMessageDialog(null,e);
						e.printStackTrace();
					}
					total_reduced_price=total_reduced_price+reduced_price;
					
					
				}
				save=ttl_offr_prd_and_price-total_reduced_price;
				//System.out.println(" Saved:"+save);
				total=total-save;
				total=Math.round(total*100.0)/100.0;

				
				labelTotal.setText(String.valueOf(total));

				for(int i=0;i<o;i++){
					isOffer[i]=null;
				}
				o=0;
			}

			private void calculating_offered_product() {
				 int temp=0;
				for(int i=0;i<o;i++){
					for(int j=0;j<productIdIndlm.size();j++){
						if(isOffer[i].equals(productIdIndlm.get(j))){
							//itemNoIndlm
							 temp=temp+(int) itemNoIndlm.get(j);
							
						}
					}
					Q[i]=temp;
					temp=0;
					
				}
				for(int i=0;i<o;i++){
					System.out.println("Q[%d] "+Q[i]);
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
		   comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"Select", "Narsary", "Play", "One", "Two", "Three", "Four", "Five", "Six"}));
		   comboBoxYear.setBounds(129, 11, 92, 20);
		   panel_2.add(comboBoxYear);
		   
		   comboBoxSection = new JComboBox();
		   comboBoxSection.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		String yr=comboBoxYear.getSelectedItem().toString();
		   		String sec=comboBoxSection.getSelectedItem().toString();
		   		comboBoxStudentID.removeAllItems();
		   		System.out.print("year "+ yr + "and section " +sec );
				
				try {
					//String q="select stu_id from lis_student_info where year='"+yr+"' and section='"+sec+"'";
					String q="select stu_id from lis_student_info where year='"+yr+"'and section='"+sec+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					while(result.next()){
						comboBoxStudentID.addItem(result.getString("STU_ID"));
						
					}	
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			
		   	}
		   });
		   comboBoxSection.setModel(new DefaultComboBoxModel(new String[] {"Select ", "A", "B", "C", "D"}));
		   comboBoxSection.setBounds(280, 11, 92, 20);
		   panel_2.add(comboBoxSection);
		   
		   comboBoxStudentID = new JComboBox();
		   comboBoxStudentID.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		comboBoxStudentID.addItem("garbeg");
		   		String st_id=comboBoxStudentID.getSelectedItem().toString();
		   		comboBoxStudentID.removeItem("garbeg");
				
				try {
					String q="select firstname,lastname from lis_student_info where stu_id='"+st_id+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					if(result.next()){
						textFieldStudentName.setText(result.getString("FIRSTNAME")+ " "+result.getString("LASTNAME"));
					}	
					else{
						textFieldStudentName.setText("");
					}
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
		   		
				//String s_id=comboBoxStudentID.getSelectedItem().toString();
				try {
					String q="select * from LIS_STUDENT_FEE_DETAILS where stu_id='"+st_id+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					tableFeePaid.setModel(DbUtils.resultSetToTableModel(result));	
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				try {
					String q="select month_fee,allow from LIS_F_ALLOWNCES where stu_id='"+st_id+"'";
					PreparedStatement prstatement=connection.prepareStatement(q);
					ResultSet result=prstatement.executeQuery();
					tableAllownces.setModel(DbUtils.resultSetToTableModel(result));	
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

				
				
		   	}
		   });
		   comboBoxStudentID.setBounds(454, 11, 92, 20);
		   panel_2.add(comboBoxStudentID);
		   
		   textFieldStudentName = new JTextField();
		   textFieldStudentName.setBounds(206, 36, 164, 20);
		   panel_2.add(textFieldStudentName);
		   textFieldStudentName.setColumns(10);
		   
		   JLabel lblYear = new JLabel("Year");
		   lblYear.setForeground(new Color(205, 133, 63));
		   lblYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		   lblYear.setBounds(84, 14, 36, 14);
		   panel_2.add(lblYear);
		   
		   JLabel lblSection = new JLabel("Section");
		   lblSection.setForeground(new Color(205, 133, 63));
		   lblSection.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		   lblSection.setBounds(234, 14, 36, 14);
		   panel_2.add(lblSection);
		   
		   JLabel lblEnrolmentId = new JLabel("Student ID");
		   lblEnrolmentId.setForeground(new Color(205, 133, 63));
		   lblEnrolmentId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		   lblEnrolmentId.setBounds(382, 14, 74, 14);
		   panel_2.add(lblEnrolmentId);
		   
		   JLabel lblName = new JLabel("Name");
		   lblName.setForeground(new Color(205, 133, 63));
		   lblName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		   lblName.setBounds(149, 39, 36, 14);
		   panel_2.add(lblName);
		   
		   JLabel labelLogo = new JLabel("");
		   Image logo=new ImageIcon(this.getClass().getResource("/lis_logo.png")).getImage();
		   labelLogo.setIcon(new ImageIcon(logo));
		   labelLogo.setBounds(10, 4, 64, 65);
		   panel_2.add(labelLogo);
		   feeNameIncomboBox();
	}
	
	private void feeNameIncomboBox() {
		// TODO Auto-generated method stub
		comboBoxFeeName.removeAllItems();
		String query="select distinct FEE_NAME from LIS_FEE_CATEGORIES";
		
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			while(result.next()){
				comboBoxFeeName.addItem(result.getString("FEE_NAME"));
		
			}	
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}

	protected void getClassTestFee(String classTest) {
		// TODO Auto-generated method stub
		String clas=comboBoxYear.getSelectedItem().toString();
		String query="select amount from lis_fee_setup where fee_id= applicable_for='"+clas+"' ";
		
	}
	protected void updateScholarshipTimePeriod(String stu_id) {
		// TODO Auto-generated method stub
		if(deductSchTimeperiod>timePeriod){
			timePeriod=0;
		}else{
			timePeriod=timePeriod-deductSchTimeperiod;

		}
		String query="update LIS_SCHOLARSHIP_DETAILS set time_period ='"+timePeriod+"' where stu_id='"+stu_id+"' and time_period>0 ";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.executeQuery();
			deductSchTimeperiod=0;

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	double timePeriod=0;
	protected double getScholarship(String s_id,double tFee) {
		// TODO Auto-generated method stub
		double schPer=0;
		String p_id="Scholarship";
		String query="select persentage,time_period from LIS_SCHOLARSHIP_DETAILS where stu_id='"+s_id+"' and time_period>0";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			if(result.next()){
				String sch=result.getString("PERSENTAGE");
				schPer=Double.parseDouble(sch);
				double amnt=(schPer*tFee)/100;
				
				
				String deductSch=String.valueOf(amnt);
				
				String tp=result.getString("TIME_PERIOD");
				timePeriod=Double.parseDouble(tp);
				productname="Scholarship "+sch+"%";
				String items=String.valueOf((int)noOfItem);
				if(timePeriod-deductSchTimeperiod>0){
					dataInShoppingCart(productname,"-"+deductSch,items,p_id);
				}

				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return schPer;
	}
	protected void getLateFeeFine(String monthName) {
		// TODO Auto-generated method stub
		String typ="LateFee";
		String query="select amount from lis_fine_setup where fine_type='"+typ+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			if(result.next()){
				System.out.println("Late Fee Fine: "+result.getString("AMOUNT"));
				 productname=monthName+" Fine";
				 String p_id="LateFeeFine";
				String str2=result.getString("AMOUNT");
				String prc=str2;
				String items=String.valueOf((int)noOfItem);

				dataInShoppingCart(productname,prc,items,p_id);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	protected boolean checkFineAllownces(String stu_id, String mnth) {
		// TODO Auto-generated method stub
		String query="select allow from LIS_F_ALLOWNCES where stu_id='"+stu_id+"'  and allow='"+"free"+"' and month_fee='"+mnth+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			if(result.next()){
				return true;
			}	
			else
				return false;
				
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		return false;
	}
	String[] month=new String[12];
	protected boolean checkDueTuitionFee(String stu_id,String monthName) {
		// TODO Auto-generated method stub
		String query="select fee_status from LIS_STUDENT_FEE_DETAILS where stu_id='"+stu_id+"' and month_of_fee='"+monthName+"' and fee_status='"+"Due"+"'";
		int i=0;
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			if(result.next()){
				System.out.print("Due:"+result.getString("FEE_STATUS"));
				month[i++]=monthName;
				result.close();
				return true;
			}	
			else 
				return false;
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return false;
	}
	//Find late fee allownces from database 
	String[] allow=new String[12];
	private boolean checkLateFeeAllownces(String stu_id,String monthName) {
		
		String query="select * from LIS_F_ALLOWNCES where stu_id='"+stu_id+"' and month_fee='"+monthName+"' and allow='"+"free"+"'";
		int i=0,a=0;
		try {

			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet resul=prstatement.executeQuery();
			if(resul.next()){
				//allow[a++]=result.getString("ALLOW");
				//check 
				return true;
				
			}
			else return false;
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return false;
		
	}
	protected void tuitionFeeStatus() {
		// TODO Auto-generated method stub
		
		
		
		
		
		String stu_id=comboBoxStudentID.getSelectedItem().toString();

		for(int i=0;i<productIdIndlm.size();i++){

			if(productIdIndlm.get(i).equals("tuitionfee")){
				String query="Insert into LIS_STUDENT_FEE_DETAILS values(?,?,?,?,?)";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.setString(1, stu_id);
					prstatement.setString(2,invoice_srl );
					prstatement.setString(3, productNameInDLM.get(i).toString());
					prstatement.setDouble(4, Double.parseDouble( (String) priceIndlm.get(i)));
					prstatement.setString(5, "paid");
					prstatement.executeQuery();
					prstatement.close();
					//JOptionPane.showMessageDialog(null, "Saved data in sales_invoice");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//JOptionPane.showMessageDialog(null, "");
				}
			}
		}
	}
	protected void clearStudentInfo() {
		// TODO Auto-generated method stub
		comboBoxYear.setSelectedIndex(0);
		comboBoxSection.setSelectedIndex(0);
		//comboBoxStudentID.setSelectedIndex(0);
		comboBoxPicnicPlace.setSelectedIndex(0);
		textFieldStudentName.setText("");
		
	}
	private void getPicnicPlace() {
		// TODO Auto-generated method stub
		String query="select fee_id from LIS_FEE_SETUP where fee_name='Picnic'";

		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			while(result.next()){
				comboBoxPicnicPlace.addItem(result.getString("FEE_ID"));

			}	
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		

		
	}
	protected void getTuitionFee(String yr, String stu_id,String monthly,String monthName) {
		//String query="Select product_name,sales_price from productdetails where product_id='"+product+"'";
		String f_id="tuition21";
		System.out.println("Student ID:"+stu_id);
//		String query="Select * from lis_fee_setup where applicable_for='"+yr+"' and fee_id='"+f_id+"'";//this is for offer query
		String query="Select * from lis_fee_setup where  fee_id='"+f_id+"'";//this is for offer query

		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet rs=prstatement.executeQuery();
			
			if(rs.next()){                             //if any product got offer like 3 for £1,so tracking the offered product id 
				//System.out.print("HELLOO");

				//System.out.print(rs.getString("AMOUNT"));
				 productname=monthName;
				 String p_id=rs.getString("FEE_ID");
				String str2=rs.getString("AMOUNT");
				tFee=Double.parseDouble(str2);				//deduct scholarship from tuition fee

				String prc=str2;
				String items=String.valueOf((int)noOfItem);

				dataInShoppingCart(productname,prc,items,p_id);
			}	
			rs.close();
			prstatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please Select Student ID!!!");
		}
		//textField_search.setText("");

	}
	private void dataInShoppingCart(String productname2, String prc,
			String items,String p_id) {
		// TODO Auto-generated method stub
		productname=productname2;
		priceIndlm.addElement(prc);                      //price
		price=Double.parseDouble(prc);
		double subtotal=price*noOfItem;
		total=total+subtotal;
		double value=Math.round(total*100.0)/100.0;
		String str4=String.valueOf(value);
		

		String subttl=String.valueOf(subtotal);

		labelTotal.setText(str4);
		String str3=productname+ "     "+items+"    "+subttl;
		dlm.addElement(str3);                                    //basket
		
		itemNoIndlm.addElement(noOfItem);                        //quantity
		
		productNameInDLM.addElement(productname);
		productIdIndlm.addElement(p_id); ///PRODUCT ID 
		ii++;
		//productFound=true; //if the product find from the system then clear the text_search field.
		textField_search.setText("");
	
	list.setModel(dlm);
	

		
	}
	String productname;
	int o=0;
	private void getDataFromProductList(String product){
		
		//String query="Select product_name,sales_price from productdetails where product_id='"+product+"'";
		String query="Select product_name,sales_price,offer from LIS_PRODUCT_PRICE_SETUP where product_id='"+product+"'";//this is for offer query
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet rs=prstatement.executeQuery();
			
			if(rs.next()){                             //if any product got offer like 3 for £1,so tracking the offered product id 
				String yORn=rs.getString("offer");
				//String yORn="n";//this is not for offer if offer working ommit this line
				char yon=yORn.charAt(0);
				if(yon=='y'){
					int flag=0;
					for(int i=0;i<=o;i++){

						if(product.equals(isOffer[i])){
							flag=1;
							break;
						}
					}
					if(flag==0){
						isOffer[o++]=product;

					}
				}
				
				 productname=rs.getString("product_name");
				String prc=rs.getString("sales_price");
				
				//dataInShoppingCart(productname,prc);
				priceIndlm.addElement(prc);                      //price
				price=Double.parseDouble(prc);
				double subtotal=price*noOfItem;
				total=total+subtotal;
				double value=Math.round(total*100.0)/100.0;
				String str4=String.valueOf(value);
				String items=String.valueOf((int)noOfItem);
				

				String subttl=String.valueOf(subtotal);

				labelTotal.setText(str4);
				String str3=productname+ "     "+items+"    "+subttl;
				dlm.addElement(str3);                                    //basket
				
				itemNoIndlm.addElement(noOfItem);                        //quantity
				
				productIdIndlm.addElement(product);                        //product id
				productNameInDLM.addElement(productname);
				ii++;
				//productFound=true; //if the product find from the system then clear the text_search field.
				textField_search.setText("");
			}
			list.setModel(dlm);
			
			
			rs.close();
			prstatement.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//textField_search.setText("");
	}
	
	// if taking multi-products 
	private void takeNoOfItems(){
		String str=textFieldDisplay.getText();
		double ddd=Double.parseDouble(str);
		String parsString=String.valueOf((int)ddd);
		int iii=Integer.parseInt(parsString);
		
		//System.out.println("iii: "+iii);

		if(iii>1){
		try{
			int len=itemNoIndlm.size()-1;                   //length of lists
			 //noOfItem=Double.parseDouble(str);
			////noOfItem=Integer.parseInt(str);
			 double stotal=Double.parseDouble(priceIndlm.get(len).toString())*iii;
			 double temp=Double.parseDouble(priceIndlm.get(len).toString());
			 total=total-temp;
			 
			
			 itemNoIndlm.remove(len);  //remove quantity
			 priceIndlm.remove(len);  //remove price
			 dlm.remove(len);       //remove item + no of item + price from shopping basket
			 
			 
			 itemNoIndlm.add(len, iii);// add quantity in dlm
			 
			 priceIndlm.add(len,stotal);
			 
			 String ss=itemNoIndlm.get(len).toString();
			 double ii=Double.parseDouble(ss);
			 String sss=String.valueOf((int)ii);
			 String st=productname+"   "+sss+"  "+priceIndlm.get(len).toString();
			 dlm.addElement(st);
			 list.setModel(dlm);
			//edit price...................
			 total=total+Double.parseDouble(priceIndlm.get(len).toString());
			 String str4=String.valueOf(total);
			 labelTotal.setText(str4);
			  
			//make initial state of calculator
			cal_initial=0.0;
			textFieldDisplay.setText("0.");
			
			noOfItem=1;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		}else {
			JOptionPane.showMessageDialog(null,"Insert the numbur of quantity please");
		}
		//"Take any product first please."
	}
	private void invoice(){
		String customerName=comboBoxStudentID.getSelectedItem().toString();
		//String employee_id="employee_id";
		Employee emp_id=new Employee();
		String employee_id=emp_id.getEmployeeID();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		invoice_srl=dateFormat.format(cal.getTime());
		System.out.println(dateFormat.format(cal.getTime()));
		DateFormat dat=new SimpleDateFormat("dd-MMM-yyyy");
		Calendar canl=Calendar.getInstance();
		String d=(String)dat.format(canl.getTime());
		System.out.println(d);

		String query="Insert into lis_sales_invoice values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.setString(1, invoice_srl);
			prstatement.setString(2,d );
			prstatement.setString(3, customerName);
			prstatement.setString(4, employee_id);
			prstatement.setDouble(5, total);
			prstatement.setDouble(6, cashPay);
			prstatement.setDouble(7, change);
			prstatement.executeQuery();
			prstatement.close();
			//JOptionPane.showMessageDialog(null, "Saved data in sales_invoice");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "problem in sales invoice");
		}
				
		
		

		ii=0;
		labelTotal.setText("0.0");
		labelCash.setText("0.0");
		labelChange.setText("0.0");
		
	}
	private void invoiceDetails(){
		int quantity=1,discount=0;
		//System.out.println("Invoice details:");

		double subPrice;
		try {
			for(int i=0;i<productIdIndlm.size();i++){
			String query="Insert into LIS_SALES_INVOICE_DETAILS values(?,?,?,?,?,?)";
			
				String sub_price=priceIndlm.get(i).toString();
				double sp=Double.parseDouble(sub_price);
				subPrice=sp*quantity;
				PreparedStatement prstatement=connection.prepareStatement(query);
				prstatement.setString(1, invoice_srl);
				prstatement.setString(2, productIdIndlm.get(i).toString());
				prstatement.setString(3,productNameInDLM.get(i).toString());
				prstatement.setInt(4,Integer.parseInt(itemNoIndlm.get(i).toString()) );
				prstatement.setInt(5, discount);
				prstatement.setDouble(6,sp);
				prstatement.executeQuery();
				prstatement.close();
				System.out.println("save  quantity in sales_invoice_details :"+itemNoIndlm.get(i));
				
				 
			 
			}
			//JOptionPane.showMessageDialog(null, "Saved data");
			//deleteDLM();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"problelm in LIS sales_invoice_details");
		}
		
	}
	private void deleteDLM(){
		for( int j=productIdIndlm.size()-1;j>=0;j--){

			productIdIndlm.remove(j);
			productNameInDLM.remove(j);
			dlm.remove(j);
			priceIndlm.remove(j);
			itemNoIndlm.remove(j);
			
			System.out.print("J: "+j);
		}

		textFieldDisplay.setText("0.");
		total=0.0;
		cal_initial=0.0;
		
		
	}
	double change=0.0;
	double cashPay=0.0;
	private void payment(){
		String cashPayment=textFieldDisplay.getText();
		cashPay=Double.parseDouble(cashPayment);
		total=Math.round(total*100.0)/100.0;
		change=cashPay-total;
		double change_value=Math.round(change*100.0)/100.0;

		//System.out.println("Change: "+change_value);
		//System.out.println("Cash pay: "+cashPay);
		//System.out.println("total: "+total);
		

	}
	private void report(){
		//making report
		/*try{
			JasperDesign jd=JRXmlLoader.load("C:\\possystem\\shoppingCartReport.jrxml");

			//JasperDesign jd=JRXmlLoader.load("C:\\Users\\creativepc\\workspace\\GuiPractice\\shoppingCartReport.jrxml");
			String query="select pro.product_name,salinvo.invoice_id,salinvo.paid_date,salinvo.employeeid,salinvo.total,salinvo.cash,salinvo.change,indetails.product_quantity,pro.sales_price,indetails.subtotal from sales_invoice salinvo join sales_invoice_details indetails on salinvo.invoice_id=indetails.invoice_id join productdetails pro on indetails.product_id=pro.product_id where salinvo.invoice_id='"+invoice_srl+"'";
			JRDesignQuery newQuery=new JRDesignQuery();
			newQuery.setText(query);
			jd.setQuery(newQuery);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr,null,connection);
			//JasperViewer.viewReport(jp);
			
			
			JRViewer test = new JRViewer(jp);
	        
	        JFrame frame = new JFrame("Report");
	        frame.getContentPane().add(test); //new JRViewer(jasperPrint)
	        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.pack();
	        frame.setVisible(true);   

		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}*/
	}
	private void print(){
		//report print
	}
	private void deleteItem(){	
		int index=list.getSelectedIndex();
		
		if(index>-1){
			dlm.remove(index);
			productIdIndlm.remove(index);
			itemNoIndlm.remove(index);
			productNameInDLM.remove(index);
			//System.out.println("price in index:"+priceIndlm.get(index).toString());
			String str=priceIndlm.get(index).toString();
			double temp=Double.parseDouble(str);
			total=total-temp;
			total=Math.round(total*100.0)/100.0;
			String str4=String.valueOf(total);
			labelTotal.setText(str4);
			priceIndlm.remove(index);
			//System.out.println("Product Id:"+productIdIndlm.get(index).toString());
			trakingTheDeletedProducts(productIdIndlm.get(index).toString());//keep record of the  deleted product.
		}
		else{
			JOptionPane.showMessageDialog(null, "Select any item to delete please!");
			
		}
		
	}
	private void trakingTheDeletedProducts(String product_id) {
		// TODO Auto-generated method stub
		
		
		DateFormat dat=new SimpleDateFormat("dd-MMM-yyyy");
		Calendar canl=Calendar.getInstance();
		String d=(String)dat.format(canl.getTime());
		DateFormat tim=new SimpleDateFormat("HH:mm:ss");
		Calendar cl=Calendar.getInstance();
		String ti=(String)tim.format(cl.getTime());
		System.out.println(ti);
	/*	Employee emp=new Employee();
		String emp_id=emp.getEmployeeID();
		System.out.println(emp_id);*/

		String query="insert into employee_deleted_products values(to_date(?,'dd-mon-yy'),to_date(?,'hh24:mi:ss'),?,?)";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			prst.setString(1, d);
			prst.setString(2, ti);
			prst.setString(3, "emp_id");
			prst.setString(4, product_id);
			prst.executeQuery();
			prst.close();
			//JOptionPane.showMessageDialog(null, "success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	static double  cal_initial=0.0;
	private JTextField textFieldStudentName;
	private JTable tableFeePaid;
	private JTable tableAllownces;
	private void calculator(double num){
		double temp;
		cal_initial=cal_initial*10+num;
		temp=cal_initial/100;
		String s=String.valueOf(temp);
		textFieldDisplay.setText(s);
		
		
		
	}
	private void deductProductFromDatabase(){
		try{
			for(int i=0;i<productIdIndlm.size();i++){
				String no_pro=itemNoIndlm.get(i).toString();
				int nPro=Integer.parseInt(no_pro);
				//System.out.println("product ID: "+productIdIndlm.get(i).toString());
				System.out.println("product no todeduct : "+nPro);
				//String query="update productdetails set quantity=quantity-(select product_quantity from sales_invoice_details where product_id='"+productIdIndlm.get(i).toString()+"'and invoice_id='"+invoice_srl+"')where product_id='"+productIdIndlm.get(i).toString()+"'";
				String query="update productdetails set quantity=quantity-'"+nPro+"'where product_id='"+productIdIndlm.get(i).toString()+"'";
				PreparedStatement prstatement=connection.prepareStatement(query);
				prstatement.executeQuery();
				
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		deleteDLM();
	}
	
	
	private void cashDetails(String key){
		double cash_in=0.0,cash_out=0.0,sale=0.0,cash_flow=0.0;
		DateFormat dat=new SimpleDateFormat("dd-MMM-yyyy");
		Calendar canl=Calendar.getInstance();
		String d=(String)dat.format(canl.getTime());
		//System.out.println(d);
		if(key.equals("in")){
			cash_in=Double.parseDouble(textFieldDisplay.getText());	
			JOptionPane.showMessageDialog(null, "Cash In "+"£"+cash_in);

		}
		else if(key.equals("out")){
			cash_out=Double.parseDouble(textFieldDisplay.getText());	
			JOptionPane.showMessageDialog(null, "Cash Out "+"£"+cash_out);

		}
		else if(key.equals("cashflow")){
			cash_flow=Double.parseDouble(textFieldDisplay.getText());
		}
		else if(key.equals("sale")){
			sale=total;	
		}
		
		String query="insert into cash_details values(?,?,?,?,?)";
		try {
			PreparedStatement prst=connection.prepareStatement(query);
			
			prst.setString(1,d);
			prst.setDouble(2,cash_in);
			prst.setDouble(3,cash_out);
			prst.setDouble(4,cash_flow);
			prst.setDouble(5,sale);
			prst.executeQuery();
			prst.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
		cal_initial=0.0;
		textFieldDisplay.setText("0.");

	}
	private void sessionLogout(){
		
		/*Employee empid=new Employee();
		String empId=empid.getEmployeeID();*/
		String empId="njj";
		DateFormat logTime=new SimpleDateFormat("HH:mm:ss");
		DateFormat dat=new SimpleDateFormat("dd-MMM-yy");

		Calendar canl=Calendar.getInstance();
		String logoutTime=(String)logTime.format(canl.getTime());
		String loginDate=(String)dat.format(canl.getTime());

		System.out.println("time:"+logoutTime+" date:"+loginDate);
		System.out.println("employeeid logout:"+empId);
		String query="update sessions set logout_time=to_date('"+logoutTime+"','hh24:mi:ss') where employeeid='"+empId+"' and login_date='"+loginDate+"'";
		try {
			 PreparedStatement prst=connection.prepareStatement(query);
			 prst.executeQuery();
			 prst.close();
			JOptionPane.showMessageDialog(null, "logout successfully");

		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
			
		}
	}
}
