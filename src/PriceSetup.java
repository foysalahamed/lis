import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.peer.TextAreaPeer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;


public class PriceSetup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldProductName;
	private JTextField textFieldWeight;
	private JTextField textFieldVendor;
	private JTextField textFieldPurchasePrice;
	private JTextField textFieldSalePrice;
	private JTextField textFieldOfferPrice;
	private JTextField textFieldOfferQuantity;
	private JTextField textFieldP_InvoiceNo;
	private JTextField textFieldProductID;
	private JTextField textFieldQuantity;
	private JTable tableProductInfo;
	private JTextField textFieldProductSearch;
	JTextArea textAreaDescription;
	Connection connection;
	JDateChooser dateChooserDate;
	char offer='n';
	JCheckBox checkBoxOfferPrice;
	JComboBox comboBoxSearchBy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PriceSetup frame = new PriceSetup();
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
	public PriceSetup() {
		

		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(56, 79, 821, 158);
		contentPane.add(panel_1);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldProductName.setColumns(10);
		textFieldProductName.setBounds(106, 21, 138, 20);
		panel_1.add(textFieldProductName);
		
		JLabel label_4 = new JLabel("Product Name");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_4.setBounds(3, 21, 96, 22);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Description");
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_5.setBounds(274, 86, 81, 22);
		panel_1.add(label_5);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textAreaDescription.setBounds(357, 66, 138, 50);
		panel_1.add(textAreaDescription);
		
		JLabel label_6 = new JLabel("Weight");
		label_6.setForeground(Color.BLUE);
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_6.setBounds(26, 43, 81, 22);
		panel_1.add(label_6);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldWeight.setColumns(10);
		textFieldWeight.setBounds(106, 43, 138, 20);
		panel_1.add(textFieldWeight);
		
		textFieldVendor = new JTextField();
		textFieldVendor.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldVendor.setColumns(10);
		textFieldVendor.setBounds(106, 64, 138, 20);
		panel_1.add(textFieldVendor);
		
		JLabel label_7 = new JLabel("Vendor");
		label_7.setForeground(Color.BLUE);
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_7.setBounds(26, 64, 81, 22);
		panel_1.add(label_7);
		
		textFieldPurchasePrice = new JTextField();
		textFieldPurchasePrice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldPurchasePrice.setColumns(10);
		textFieldPurchasePrice.setBounds(357, 25, 138, 20);
		panel_1.add(textFieldPurchasePrice);
		
		JLabel label_8 = new JLabel("Purchase Price");
		label_8.setForeground(Color.BLUE);
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_8.setBounds(262, 25, 96, 22);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("Sale Price");
		label_9.setForeground(Color.BLUE);
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_9.setBounds(277, 44, 81, 22);
		panel_1.add(label_9);
		
		textFieldSalePrice = new JTextField();
		textFieldSalePrice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldSalePrice.setColumns(10);
		textFieldSalePrice.setBounds(357, 44, 138, 20);
		panel_1.add(textFieldSalePrice);
		
		textFieldOfferPrice = new JTextField();
		textFieldOfferPrice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldOfferPrice.setColumns(10);
		textFieldOfferPrice.setBounds(183, 87, 48, 20);
		panel_1.add(textFieldOfferPrice);
		
		textFieldOfferQuantity = new JTextField();
		textFieldOfferQuantity.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldOfferQuantity.setColumns(10);
		textFieldOfferQuantity.setBounds(106, 87, 48, 20);
		panel_1.add(textFieldOfferQuantity);
		
		JLabel label_10 = new JLabel("for");
		label_10.setForeground(Color.BLUE);
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_10.setBounds(158, 86, 30, 22);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("Offer Price");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_11.setBounds(29, 86, 70, 22);
		panel_1.add(label_11);
		
		 checkBoxOfferPrice = new JCheckBox("Offer Price");
		 checkBoxOfferPrice.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(checkBoxOfferPrice.isSelected()){
					offer='y';
				//System.out.println("Offer Check box is:"+offer);
				}
		 	}
		 });
		checkBoxOfferPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		checkBoxOfferPrice.setBackground(SystemColor.inactiveCaption);
		checkBoxOfferPrice.setBounds(105, 114, 97, 23);
		panel_1.add(checkBoxOfferPrice);
		
		JLabel label_12 = new JLabel("Purchase Invoice No");
		label_12.setForeground(Color.BLUE);
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_12.setBounds(505, 26, 130, 22);
		panel_1.add(label_12);
		
		JLabel label_13 = new JLabel("Product ID");
		label_13.setForeground(Color.BLUE);
		label_13.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_13.setBounds(565, 46, 81, 22);
		panel_1.add(label_13);
		
		JLabel label_14 = new JLabel("Quantity");
		label_14.setForeground(Color.BLUE);
		label_14.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_14.setBounds(563, 68, 81, 22);
		panel_1.add(label_14);
		
		JLabel label_15 = new JLabel("Date");
		label_15.setForeground(Color.BLUE);
		label_15.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_15.setBounds(565, 86, 34, 22);
		panel_1.add(label_15);
		
		textFieldP_InvoiceNo = new JTextField();
		textFieldP_InvoiceNo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldP_InvoiceNo.setColumns(10);
		textFieldP_InvoiceNo.setBounds(644, 26, 138, 20);
		panel_1.add(textFieldP_InvoiceNo);
		
		textFieldProductID = new JTextField();
		textFieldProductID.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldProductID.setColumns(10);
		textFieldProductID.setBounds(645, 46, 138, 20);
		panel_1.add(textFieldProductID);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(645, 66, 138, 20);
		panel_1.add(textFieldQuantity);
		
		 dateChooserDate = new JDateChooser();
		dateChooserDate.setBounds(646, 88, 137, 20);
		panel_1.add(dateChooserDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(152, 314, 578, 149);
		contentPane.add(scrollPane);
		
		tableProductInfo = new JTable();
		tableProductInfo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_DOWN||event.getKeyCode()==KeyEvent.VK_UP){
					getDataFromProductInfoIntoTextField();
					}
				
			}
		});
		tableProductInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getDataFromProductInfoIntoTextField();
			}
		});
		scrollPane.setViewportView(tableProductInfo);
		
		JLabel lblPriceSetUp = new JLabel("Price Setup");
		lblPriceSetUp.setForeground(new Color(205, 133, 63));
		lblPriceSetUp.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblPriceSetUp.setBounds(399, 31, 137, 22);
		contentPane.add(lblPriceSetUp);
		
		JButton buttonCreatOffer = new JButton("Creat Offer");
		buttonCreatOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertDataIntoOfferTable();
				editProductDetails();// change the offer status
				showProductInfoInTable();
				clearAllTextField();
			}
		});
		buttonCreatOffer.setForeground(new Color(139, 0, 0));
		buttonCreatOffer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		buttonCreatOffer.setBounds(56, 248, 114, 25);
		contentPane.add(buttonCreatOffer);
		
		JButton buttonChangeOffer = new JButton("Change Offer");
		buttonChangeOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String offerQuantity=textFieldOfferQuantity.getText();
				String offerPrice=textFieldOfferPrice.getText();
				String product_id=textFieldProductID.getText();
				String query="update lis_offer_details set offer_quantity='"+offerQuantity+"',offer_price='"+offerPrice+"' where product_id='"+product_id+"'" ; 
				try {
					PreparedStatement prStatement=connection.prepareStatement(query);
					prStatement.executeQuery();
					prStatement.close();
					JOptionPane.showMessageDialog(null,"Offer Updated.");
					clearAllTextField();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buttonChangeOffer.setForeground(new Color(139, 0, 0));
		buttonChangeOffer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		buttonChangeOffer.setBounds(170, 248, 127, 25);
		contentPane.add(buttonChangeOffer);
		
		JButton buttonEdit = new JButton("Edit");
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editProductDetails();
				showProductInfoInTable();
				clearAllTextField();
			}
		});
		buttonEdit.setForeground(new Color(0, 128, 0));
		buttonEdit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		buttonEdit.setBounds(299, 248, 71, 25);
		contentPane.add(buttonEdit);
		
		showProductInfoInTable();
		
		
		JButton buttonNewProduct = new JButton("New Product");
		buttonNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertNewProduct();
				purchaseProducts();
				showProductInfoInTable();
				clearAllTextField();

			}
		});
		buttonNewProduct.setForeground(Color.BLUE);
		buttonNewProduct.setFont(new Font("Times New Roman", Font.BOLD, 14));
		buttonNewProduct.setBounds(371, 248, 113, 25);
		contentPane.add(buttonNewProduct);
		
		JButton buttonDelete = new JButton("Delete ");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String query1="Delete from LIS_PRODUCT_PRICE_SETUP where product_id='"+textFieldProductID.getText()+"'";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query1);
					prstatement.executeQuery();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String query="Delete from lis_offer_details where product_id='"+textFieldProductID.getText()+"'";
				try {
					PreparedStatement prstatement=connection.prepareStatement(query);
					prstatement.executeQuery();
					JOptionPane.showMessageDialog(null, "Successfully deleted");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				showProductInfoInTable();
				clearAllTextField();
			
			}
		});
		buttonDelete.setForeground(Color.RED);
		buttonDelete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		buttonDelete.setBounds(484, 248, 98, 25);
		contentPane.add(buttonDelete);
		
		JButton button_5 = new JButton("Refresh");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showProductInfoInTable();
			}
		});
		button_5.setForeground(new Color(0, 128, 0));
		button_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_5.setBounds(585, 248, 88, 25);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Save");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchaseProducts();
				updateQuantity();
				showProductInfoInTable();
				clearAllTextField();
			}
		});
		button_6.setForeground(new Color(205, 133, 63));
		button_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_6.setBounds(678, 248, 88, 25);
		contentPane.add(button_6);
		button_6.setVisible(false);
		
		JButton buttonOrderPlace = new JButton("Order Place");
		buttonOrderPlace.setForeground(new Color(128, 0, 128));
		buttonOrderPlace.setFont(new Font("Times New Roman", Font.BOLD, 12));
		buttonOrderPlace.setBounds(768, 249, 109, 25);
		contentPane.add(buttonOrderPlace);
		buttonOrderPlace.setVisible(false);
		
		JLabel label = new JLabel("Product Search");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label.setBounds(266, 293, 91, 14);
		contentPane.add(label);
		
		textFieldProductSearch = new JTextField();
		textFieldProductSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
					String item=(String) comboBoxSearchBy.getSelectedItem();
					
					
					String query="Select * from LIS_PRODUCT_PRICE_SETUP where "+item+" like '"+textFieldProductSearch.getText()+"'";
					try{
					PreparedStatement prstatemetn=connection.prepareStatement(query);
					ResultSet result=prstatemetn.executeQuery();
					
					tableProductInfo.setModel(DbUtils.resultSetToTableModel(result));
					
					result.close();
					prstatemetn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				
				
			}
		});
		textFieldProductSearch.setColumns(10);
		textFieldProductSearch.setBounds(367, 292, 208, 20);
		contentPane.add(textFieldProductSearch);
		
		JLabel label_1 = new JLabel("By");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_1.setBounds(581, 295, 30, 14);
		contentPane.add(label_1);
		
		comboBoxSearchBy = new JComboBox();
		comboBoxSearchBy.setModel(new DefaultComboBoxModel(new String[] {"product_id", "product_name"}));
		comboBoxSearchBy.setBounds(601, 292, 114, 20);
		contentPane.add(comboBoxSearchBy);
		
		JButton button_8 = new JButton("All Products Report");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_8.setForeground(new Color(128, 0, 128));
		button_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
		button_8.setBounds(725, 291, 146, 23);
		contentPane.add(button_8);
		button_8.setVisible(false);
	}
	
	private JTable tableInventory;
	private JTable tableSummary;
	protected void updateQuantity() {
		// TODO Auto-generated method stub
		int cProducts=Integer.parseInt(currentProducts);
		String newProducts=textFieldQuantity.getText();
		int nProducts=Integer.parseInt(newProducts);
		int temp=cProducts+nProducts;
		String query="Update LIS_PRODUCT_PRICE_SETUP set quantity='"+temp+"' where product_id='"+textFieldProductID.getText()+"'" ;
		try{
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.executeQuery();
			JOptionPane.showMessageDialog(null, "Successufully Updated");
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	protected void purchaseProducts() {
		// TODO Auto-generated method stub
		String pDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserDate.getDateEditor().getUiComponent()).getText());
		Employee emp=new Employee();
		String empid=emp.getEmployeeID();
		String query="Insert into LIS_PURCHASE_INVOICE_DETAILS values(?,?,?,?,?)";
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(query);
			prStatement.setString(1,textFieldP_InvoiceNo.getText() );
			prStatement.setString(2,textFieldProductID.getText() );	
			prStatement.setInt(3,Integer.parseInt(textFieldQuantity.getText()) );
			prStatement.setString(4,pDate);
			prStatement.setString(5,empid );
			
			
			prStatement.executeQuery();
			prStatement.close();
			//JOptionPane.showMessageDialog(null, "Successfully Saved");
			//clear textfield
			
		
		
		
		
		} catch (Exception e) {
			
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}

	protected void editProductDetails() {
		
		String p_name=textFieldProductName.getText();
		String p_des=textAreaDescription.getText();
		String p_vendor=textFieldVendor.getText();
		String p_weight=textFieldWeight.getText();
		String p_pPrice=textFieldPurchasePrice.getText();
		String p_sPrice=textFieldSalePrice.getText();
		String p_date=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserDate.getDateEditor().getUiComponent()).getText());
		String p_quantity=textFieldQuantity.getText();
		double p_price=Double.parseDouble(p_pPrice);
		double s_price=Double.parseDouble(p_sPrice);
		if(checkBoxOfferPrice.isSelected()){
			offer='y';
		}
		else{
			offer='n';
			deletDataFromOfferTable();
		}
		String query="update LIS_PRODUCT_PRICE_SETUP set product_name='"+p_name+"', description='"+p_des+"', vendor='"+p_vendor+"', weight='"+p_weight+"', purchase_price='"+p_price+"', sales_price='"+s_price+"',offer='"+offer+"',parchase_date='"+p_date+"',quantity='"+p_quantity+"' where product_id='"+textFieldProductID.getText()+"'" ;
		try{
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.executeQuery();
			JOptionPane.showMessageDialog(null, "Successufully Edited");
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}

		
	}
	private void deletDataFromOfferTable() {
		// TODO Auto-generated method stub
		String query="Delete from lis_offer_details where product_id='"+textFieldProductID.getText()+"'";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			prstatement.executeQuery();
			//JOptionPane.showMessageDialog(null, "Successfully deleted from offer  ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	String currentProducts;

	protected void getDataFromProductInfoIntoTextField() {
		// TODO Auto-generated method stub
		
		
		char m;
		try{
			int ro_id=tableProductInfo.getSelectedRow();
			String ro_id_=tableProductInfo.getModel().getValueAt(ro_id, 0).toString();
			String query="select * from  LIS_PRODUCT_PRICE_SETUP where  PRODUCT_ID='"+ro_id_+"' ";
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			//Date date;
			while(result.next()){
				textFieldP_InvoiceNo.setText(result.getString("PARCAHSE_INVOICE_ID"));
				textFieldProductID.setText(result.getString("PRODUCT_ID"));
				textAreaDescription.setText(result.getString("DESCRIPTION" ));
				textFieldProductName.setText(result.getString("PRODUCT_NAME"));
				
				textFieldVendor.setText(result.getString("VENDOR"));
				textFieldWeight.setText(result.getString("WEIGHT"));
				textFieldQuantity.setText(result.getString("QUANTITY"));
				textFieldPurchasePrice.setText(result.getString("PURCHASE_PRICE"));
				textFieldSalePrice.setText(result.getString("SALES_PRICE"));
				currentProducts=result.getString("QUANTITY");
				dateChooserDate.setDate(result.getDate("PARCHASE_DATE"));
				String s=result.getString("OFFER");
				m=s.charAt(0);
				//System.out.println("Offer from data base:"+m);

				if(m=='y'){
					//System.out.println("Offer from data base in caharcter:"+s);
					checkBoxOfferPrice.setSelected(true);
					//get data from offer table in text field
					
					dataFromOfferTableInTextfield(ro_id_);

				}else{
					checkBoxOfferPrice.setSelected(false);
					textFieldOfferQuantity.setText("");
					textFieldOfferPrice.setText("");
				}
				
			}
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		
		
		
	

		
		
	}

	private void dataFromOfferTableInTextfield(String pro_id) {
		// TODO Auto-generated method stub
		try {
			String query="select * from  LIS_OFFER_DETAILS where  PRODUCT_ID='"+pro_id+"' ";
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			//Date date;
			if(result.next()){
				textFieldOfferQuantity.setText(result.getString("OFFER_QUANTITY"));
				textFieldOfferPrice.setText(result.getString("OFFER_PRICE"));
			}
			result.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	protected void showProductInfoInTable() {
		// TODO Auto-generated method stub
		String query="Select * from LIS_PRODUCT_PRICE_SETUP";
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			tableProductInfo.setModel(DbUtils.resultSetToTableModel(result));
			result.close();
			prstatement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	protected void insertNewProduct() {
		
		String pDate=new DateFormateSettings().dateFormateCorrection(((JTextField)dateChooserDate.getDateEditor().getUiComponent()).getText());
		String query="Insert into LIS_PRODUCT_PRICE_SETUP values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(query);
			prStatement.setString(1,textFieldProductID.getText() );	
			prStatement.setString(2,textFieldProductName.getText() );
			prStatement.setString(3,textAreaDescription.getText() );
			prStatement.setString(4,textFieldVendor.getText() );
			prStatement.setString(5,textFieldWeight.getText());
			prStatement.setInt(6,Integer.parseInt(textFieldQuantity.getText()) );
			prStatement.setDouble(7,Double.parseDouble(textFieldPurchasePrice.getText() ));
			prStatement.setDouble(8,Double.parseDouble(textFieldSalePrice.getText() ));
			prStatement.setString(9,pDate);
			prStatement.setString(10,"employee_id" );
			prStatement.setString(11,textFieldP_InvoiceNo.getText() );
			prStatement.setString(12,String.valueOf(offer) );

			
			prStatement.executeQuery();
			prStatement.close();
			if(checkBoxOfferPrice.isSelected()){
				insertDataIntoOfferTable();
			}
			JOptionPane.showMessageDialog(null, "Successfully Saved");
			//clear textfield
			checkBoxOfferPrice.setSelected(false);
			offer='n';
		
		
		
		
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}

		

		
	}

	private void clearAllTextField() {
		// TODO Auto-generated method stub
		textFieldProductID.setText("");
		textFieldProductName.setText("");
		textAreaDescription.setText("");
		textFieldVendor.setText("");
		textFieldWeight.setText("");
		textFieldQuantity.setText("");
		textFieldPurchasePrice.setText("");
		textFieldSalePrice.setText("");
		textFieldP_InvoiceNo.setText("");
		dateChooserDate.setCalendar(null);
		textFieldOfferQuantity.setText("");
		textFieldOfferPrice.setText("");
		checkBoxOfferPrice.setSelected(false);
		
	}

	private void insertDataIntoOfferTable() {
		// TODO Auto-generated method stub
		String query="Insert into LIS_OFFER_DETAILS values(?,?,?)";
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(query);
			prStatement.setString(1,textFieldProductID.getText() );	
			prStatement.setString(2,textFieldOfferQuantity.getText() );
			prStatement.setString(3,textFieldOfferPrice.getText() );
			prStatement.executeQuery();
			prStatement.close();
			JOptionPane.showMessageDialog(null, "saved in offer table");
			//clearAllTextField();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	
	
	
	
	
	
}
