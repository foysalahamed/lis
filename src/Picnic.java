import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Picnic extends JFrame {

	private JPanel contentPane;
	JComboBox comboBoxPlace;
	Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Picnic frame = new Picnic();
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
	public Picnic() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			
			connection=DbConnection.dbconnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database Connection Fault.");
			// TODO: handle exception
		}
		
		setBounds(100, 100, 348, 159);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxPlace = new JComboBox();
		getDataForPicnicPlace();
		comboBoxPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getPicnicfee();
				
			}
		});
		comboBoxPlace.setBounds(111, 33, 157, 20);
		contentPane.add(comboBoxPlace);
		
		JLabel lblNewLabel = new JLabel("Place");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(62, 36, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(111, 64, 82, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(195, 64, 75, 23);
		contentPane.add(btnCancel);
	}
	public double getPicnicfee() {
		
		// TODO Auto-generated method stub
		 double amnt = 0;

		String pls=comboBoxPlace.getSelectedItem().toString();
		String query="select amount from LIS_FEE_SETUP where fee_categories='"+pls +"'";
		//System.out.print(pls);

		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			if(result.next()){
				
				amnt=Double.parseDouble(result.getString("AMOUNT"));
				PicnicFee pic_fee=new PicnicFee();
				pic_fee.setPicnicFee(amnt);
				///System.out.print(pic_fee.getPicnicFee());
				dispose();
			}
			//return amnt;
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return amnt;
		
	}

	private void getDataForPicnicPlace() {
		// TODO Auto-generated method stub
		String query="select fee_categories from LIS_FEE_SETUP where fee_occurances='Picnic'";
		
		try {
			PreparedStatement prstatement=connection.prepareStatement(query);
			ResultSet result=prstatement.executeQuery();
			while(result.next()){
				comboBoxPlace.addItem(result.getString("FEE_CATEGORIES"));
				
			}	
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		
		
	}
}
