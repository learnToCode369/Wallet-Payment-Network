package DB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	PreparedStatement mystmt = null;
	
	String url = "jdbc:mysql://localhost:3306/paymentdb";
	String user = "root";
	String Password = "root";
	private JTextField textFieldBAid;
	private JTextField textFieldBACC;
	private JTextField SSN;
	private JTextField Name;
	private JTextField Balance;
	private JTextField Verified;

	
	/**
	 * Create the frame.
	 */
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblWalletPaymentNetworkSign = new JLabel("Wallet Payment Network");
		contentPane.setLayout(new MigLayout("", "[142px][130px][6px][47px][131px,grow]", "[16px][26px][34px][26px][26px][29px][][]"));
		
		JLabel lblUsername = new JLabel("Email");
		contentPane.add(lblUsername, "cell 0 2, alignx center, aligny center");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		contentPane.add(textFieldEmail, "cell 1 2, alignx center, aligny top");
		
		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSsn, "cell 3 2,alignx center,aligny center");
		
		SSN = new JTextField();
		SSN.setColumns(10);
		contentPane.add(SSN, "cell 4 2,alignx center,aligny top");
		
		JLabel lblPassword = new JLabel("PhoneNo");
		contentPane.add(lblPassword, "cell 0 3,alignx center,aligny center");
		
		textFieldPhoneNo = new JTextField();
		textFieldPhoneNo.setColumns(10);
		contentPane.add(textFieldPhoneNo, "cell 1 3,alignx center,aligny top");
		contentPane.add(lblWalletPaymentNetworkSign, "cell 1 0 3 1,alignx center,aligny center");
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					conn  = DriverManager.getConnection(url, user, Password);
					
					
				    String sql1  = "insert into Login (email, phoneno, SSN) values (?,?,?)";
				    mystmt = conn.prepareStatement(sql1);
					mystmt.setString(1, textFieldEmail.getText());
					mystmt.setString(2, textFieldPhoneNo.getText());
					mystmt.setString(3, SSN.getText());
					
					mystmt.executeUpdate();
					
					String sql2 = "insert into BANK_ACCOUNT (BankID, BANumber) values (?,?)";
					mystmt = conn.prepareStatement(sql2);		
					mystmt.setString(1, textFieldBAid.getText());
					mystmt.setString(2, textFieldBACC.getText());
					
					mystmt.executeUpdate();
					
					String sql3  = "insert into USER_ACCOUNT (SSN, Name, email, phoneno, Balance, BankID, BANumber, PBAVerified) values (?,?,?,?,?,?,?,?)";
					
				    mystmt = conn.prepareStatement(sql3);
					
				    mystmt.setString(1, SSN.getText());
					mystmt.setString(2, Name.getText());
					mystmt.setString(3, textFieldEmail.getText());
					mystmt.setString(4, textFieldPhoneNo.getText());
					mystmt.setString(5, Balance.getText());
					mystmt.setString(6, textFieldBAid.getText());
					mystmt.setString(7, textFieldBACC.getText());
					mystmt.setString(8, Verified.getText());
					
					mystmt.executeUpdate();
					
					System.out.println("User Added");

					DB.login frame = new DB.login();
					frame.setVisible(true);
					
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblName, "cell 3 3,alignx center,aligny center");
		
		Name = new JTextField();
		Name.setColumns(10);
		contentPane.add(Name, "cell 4 3,alignx center,aligny bottom");
		
		JLabel lblBankid = new JLabel("BankID");
		contentPane.add(lblBankid, "cell 0 4,alignx center,aligny center");
		
		textFieldBAid = new JTextField();
		textFieldBAid.setColumns(10);
		contentPane.add(textFieldBAid, "cell 1 4,alignx center,aligny top");
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBalance, "cell 3 4,alignx center,aligny center");
		
		Balance = new JTextField();
		Balance.setColumns(10);
		contentPane.add(Balance, "cell 4 4,alignx center,aligny top");
		
		JLabel lblBankAccount = new JLabel("Bank Account");
		contentPane.add(lblBankAccount, "cell 0 5,alignx center,aligny center");
		
		textFieldBACC = new JTextField();
		textFieldBACC.setColumns(10);
		contentPane.add(textFieldBACC, "cell 1 5,alignx center,aligny top");
		
		JLabel lblVerified = new JLabel("Verified");
		contentPane.add(lblVerified, "cell 3 5,alignx center");
		
		Verified = new JTextField();
		contentPane.add(Verified, "cell 4 5,alignx center");
		Verified.setColumns(10);
		contentPane.add(btnSignUp, "cell 1 7 3 1,alignx center,aligny center");
	}
}
