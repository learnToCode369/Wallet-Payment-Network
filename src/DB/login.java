package DB;

import java.awt.EventQueue;

import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class login extends JFrame {

	//private JFrame frame;
	private JPanel contentPane;
	private JTextField email;
	private JPasswordField phoneno;
	private JPasswordField SSN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{53, 89, 264, 0};
		gbl_contentPane.rowHeights = new int[]{64, 26, 36, 26, 29, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					// Get a Connection to DB
					conn  = DriverManager.getConnection(url, user, Password);
                    Statement stmt = conn.createStatement();
                    String sql = "Select * from Login where email = '"+email.getText()+"'and phoneno ='"+phoneno.getText().toString()+"'and SSN ='"+SSN.getText().toString()+"'";
                    ResultSet rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                    	payment frame = new payment();
    					frame.setVisible(true);
                    }
                    else if(rs.next()) 
                    {
    					account acc = new account();
                     	acc.setVisible(true);
                    }
                    else
                    {
                           JOptionPane.showMessageDialog(null, "Incorrect Username, Password or SSN...");
                    }
                    conn.close();
             }catch (Exception exc) {System.out.println(exc);}
			}
		});
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		email = new JTextField();
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.anchor = GridBagConstraints.NORTH;
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.gridx = 2;
		gbc_username.gridy = 1;
		contentPane.add(email, gbc_username);
		email.setColumns(10);
		
		JLabel lblPassword = new JLabel("PhoneNo");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);

		phoneno = new JPasswordField();
		phoneno.setColumns(10);
		GridBagConstraints gbc_pass = new GridBagConstraints();
		gbc_pass.anchor = GridBagConstraints.NORTH;
		gbc_pass.insets = new Insets(0, 0, 5, 0);
		gbc_pass.gridx = 2;
		gbc_pass.gridy = 3;
		contentPane.add(phoneno, gbc_pass);

		JLabel lblSSN = new JLabel("SSN");
		lblSSN.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSSN = new GridBagConstraints();
		gbc_lblSSN.fill = GridBagConstraints.VERTICAL;
		gbc_lblSSN.insets = new Insets(0, 0, 5, 5);
		gbc_lblSSN.gridx = 1;
		gbc_lblSSN.gridy = 4;
		contentPane.add(lblSSN, gbc_lblSSN);

		SSN = new JPasswordField();
		SSN.setColumns(10);
		GridBagConstraints gbc_SSN = new GridBagConstraints();
		gbc_SSN.anchor = GridBagConstraints.NORTH;
		gbc_SSN.insets = new Insets(0, 0, 5, 0);
		gbc_SSN.gridx = 2;
		gbc_SSN.gridy = 4;
		contentPane.add(SSN, gbc_SSN);
		

		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 5;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					DB.signup frame = new DB.signup();
					frame.setVisible(true);
				}
		});
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.gridx = 2;
		gbc_btnCreateAccount.gridy = 6;
		contentPane.add(btnCreateAccount, gbc_btnCreateAccount);
	}

}
