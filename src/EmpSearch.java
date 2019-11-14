import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EmpSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmpIdSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpSearch frame = new EmpSearch();
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
	public EmpSearch() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 133);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("London Intelligence School"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lis_logo.png")));

		textFieldEmpIdSearch = new JTextField();
		textFieldEmpIdSearch.setBounds(98, 11, 145, 20);
		contentPane.add(textFieldEmpIdSearch);
		textFieldEmpIdSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchEmployeeInfo();
			}
		});
		
		btnSearch.setBounds(98, 36, 68, 23);
		contentPane.add(btnSearch);
		
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmployeeId.setBounds(14, 14, 79, 14);
		contentPane.add(lblEmployeeId);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldEmpIdSearch.setText("");
			}
		});
		btnClear.setBounds(168, 36, 67, 23);
		contentPane.add(btnClear);
	}
	protected void searchEmployeeInfo() {
		// TODO Auto-generated method stub
	try{
		//String query="Select"
		//EmpSearch empSearch=new EmpSearch();
		//empSearch.dispose();
		dispose();
	}catch(Exception e){
		
	}
		
	}

	
}
