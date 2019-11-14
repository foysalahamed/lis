import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CheckExpenditure extends JFrame {

	private JPanel contentPane;
	private JTable tableExpenditure;
	Connection connection=null;
	JDateChooser dateChooser;
	JLabel labelTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckExpenditure frame = new CheckExpenditure();
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
	public CheckExpenditure() {
		
		try {
			connection=DbConnection.dbconnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(172, 110, 420, 93);
		contentPane.add(panel);
		panel.setLayout(null);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(195, 33, 95, 20);
		panel.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(137, 33, 46, 14);
		panel.add(lblNewLabel);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String expDate=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText() ;
				String query="select * from lis_expenditure where expdate='"+expDate+"'";
				try {

					PreparedStatement prstatement=connection.prepareStatement(query);
					ResultSet resul=prstatement.executeQuery();
					
					tableExpenditure.setModel(DbUtils.resultSetToTableModel(resul));
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				String querySum="select sum(amount) as Total from lis_expenditure where expdate='"+expDate+"'";
				try {

					PreparedStatement prstatement=connection.prepareStatement(querySum);
					ResultSet resu=prstatement.executeQuery();
					if(resu.next()){
						labelTotal.setText(resu.getString("Total"));
						
					}
					
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}

			}
		});
		btnCheck.setBounds(137, 58, 89, 23);
		panel.add(btnCheck);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(233, 58, 76, 23);
		panel.add(btnPrint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 234, 430, 116);
		contentPane.add(scrollPane);
		
		tableExpenditure = new JTable();
		scrollPane.setViewportView(tableExpenditure);
		
		JLabel lblCheckExpenditure = new JLabel("Check Expenditure");
		lblCheckExpenditure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCheckExpenditure.setBounds(310, 72, 202, 27);
		contentPane.add(lblCheckExpenditure);
		
		JLabel lblTotal = new JLabel("Total Expenditure TK:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(351, 361, 158, 14);
		contentPane.add(lblTotal);
		
		 labelTotal = new JLabel("");
		labelTotal.setForeground(Color.BLUE);
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTotal.setBounds(519, 361, 83, 14);
		contentPane.add(labelTotal);
	}
}
