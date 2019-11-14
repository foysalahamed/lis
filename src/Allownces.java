import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;


import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Allownces extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Allownces frame = new Allownces();
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
	public Allownces() {
		setBounds(100, 100, 597, 289);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(64, 60, 467, 106);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Allownces Name");
		lblNewLabel.setBounds(38, 17, 95, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(143, 14, 178, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("Save");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOk.setBounds(143, 69, 89, 23);
		panel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(232, 69, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(38, 37, 95, 14);
		panel.add(lblAmount);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(143, 34, 178, 20);
		panel.add(textField_1);
		
		JLabel lblAllownces = new JLabel("Allownces");
		lblAllownces.setForeground(new Color(255, 228, 181));
		lblAllownces.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAllownces.setBounds(73, 42, 134, 14);
		contentPane.add(lblAllownces);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(63, 40, 468, 20);
		contentPane.add(label_1);
		Image imgovr=new ImageIcon(this.getClass().getResource("/lblbac1.png")).getImage();
		label_1.setIcon(new ImageIcon(imgovr));
		
	}
}
