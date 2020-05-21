package DB;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.Box;

import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class account extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					account frame = new account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Scanner scanner = null;
	Connection conn = null;
	PreparedStatement mystmt = null;
	
	String url = "jdbc:mysql://localhost:3306/paymentdb";
	String user = "root";
	String Password = "root";
	
	private JTextField textFieldSSN;
	private JTextField textFieldName;
	private JTextField textFieldBalance;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNo;
	private JTextField textFieldBankID;
	private JTextField textFieldBANumber;
	
	/**
	 * Create the frame.
	 */
	public account() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 297);
		contentPane = new JPanel();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblMainMenu = new JLabel("Modify Account");
		lblMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSSN = new JLabel("SSN");
		lblSSN.setHorizontalAlignment(SwingConstants.CENTER);
		lblSSN.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		textFieldSSN = new JTextField();
		textFieldSSN.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSSN.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setAlignmentX(0.5f);
		
		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setAlignmentX(0.5f);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBalance.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setAlignmentX(0.5f);

		textFieldEmail = new JTextField();
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmail.setColumns(10);

		JLabel lblPhoneNo = new JLabel("Phone No");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setAlignmentX(0.5f);

		textFieldPhoneNo = new JTextField();
		textFieldPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhoneNo.setColumns(10);
		
		Box horizontalBox = Box.createHorizontalBox();
		
		JLabel lblBankid = new JLabel("BankID");
		lblBankid.setHorizontalAlignment(SwingConstants.CENTER);
		lblBankid.setAlignmentX(0.5f);
		
		textFieldBankID = new JTextField();
		textFieldBankID.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBankID.setColumns(10);
		
		JLabel lblBANumber = new JLabel("BANumber");
		lblBANumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblBANumber.setAlignmentX(0.5f);
		
		textFieldBANumber = new JTextField();
		textFieldBANumber.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBANumber.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					conn  = DriverManager.getConnection(url, user, Password);
					
					
				    String sql1  = "UPDATE USER_ACCOUNT SET Name = '"+textFieldName.getText()+"', Balance = '"+textFieldBalance.getText()+"', email = '"+textFieldEmail.getText()+"', phoneno = '"+textFieldPhoneNo.getText()
				    		+ "'WHERE SSN = '"+textFieldSSN.getText()+"'";
					String sql2 = "UPDATE lOGIN SET email = '"+textFieldEmail.getText()+"', phoneno = '"+textFieldPhoneNo.getText()+ "'WHERE SSN = '"+textFieldSSN.getText()+"'";

					Statement mystmt = conn.createStatement();
					int rowsAffected = mystmt.executeUpdate(sql1);
					int rowsAffected1 = mystmt.executeUpdate(sql2);
					System.out.println("Rows Affected: " + rowsAffected);
					System.out.println("Rows Affected: " + rowsAffected1);
					System.out.println("Update Successful.");

					DB.login frame = new DB.login();
					frame.setVisible(true);
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
		
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					conn  = DriverManager.getConnection(url, user, Password);
					
					String sql1 = "delete from USER_ACCOUNT where SSN = '"+textFieldSSN.getText()+"'";
					String sql2 = "delete from BANK_ACCOUNT where BankID = '"+textFieldBankID.getText()+"'and BANumber = '"+textFieldBANumber.getText()+"'";
					String sql3 = "delete from LOGIN where SSN = '"+textFieldSSN.getText()+"'";
				 	Statement mystmt = conn.createStatement();
				 	int rowsAffected = mystmt.executeUpdate(sql1);
					int rowsAffected1 = mystmt.executeUpdate(sql2);
					int rowsAffected2 = mystmt.executeUpdate(sql3);
					System.out.println("Rows Affected: " + rowsAffected);
					System.out.println("Rows Affected: " + rowsAffected1);
					System.out.println("Rows Affected: " + rowsAffected2);
					System.out.println("Delete Complete.");

					DB.login frame = new DB.login();
					frame.setVisible(true);
				 }
				catch (Exception exc) {
					exc.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(262)
					.addComponent(lblMainMenu))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(lblSSN)
					.addGap(163)
					.addComponent(textFieldSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(lblName)
					.addGap(157)
					.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addComponent(lblBalance)
					.addGap(152)
					.addComponent(textFieldBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(48)
						.addComponent(lblEmail)
						.addGap(157)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(42)
						.addComponent(lblPhoneNo)
						.addGap(152)
						.addComponent(textFieldPhoneNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(lblBankid)
					.addGap(153)
					.addComponent(textFieldBankID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(lblBANumber)
					.addGap(142)
					.addComponent(textFieldBANumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(150)
					.addComponent(btnUpdate)
					.addGap(141)
					.addComponent(btnDelete))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblMainMenu)
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblSSN))
						.addComponent(textFieldSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblName))
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblBalance))
						.addComponent(textFieldBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(5)
										.addComponent(lblEmail))
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(5)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(5)
										.addComponent(lblPhoneNo))
								.addComponent(textFieldPhoneNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblBankid))
						.addComponent(textFieldBankID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblBANumber))
						.addComponent(textFieldBANumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpdate)
						.addComponent(btnDelete)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
