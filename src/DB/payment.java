package DB;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class payment extends JFrame {

	

	protected static final String btnNewButtonModify = null;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldRequest;
	private JTextField textFieldAmt;
	private JTextField textFieldSSN;
	private JTextField textFieldMemo;
	private JTextField textFieldCReason;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					payment frame = new payment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	PreparedStatement mystmt = null;
	ArrayList<String> name = new ArrayList<>();;
	
	String url = "jdbc:mysql://localhost:3306/paymentdb";
	String user = "root";
	String Password = "root";
	private JTextField textFieldAmount;
	
	/**
	 * Create the frame.
	 */
	public payment() {
//		databaseNam();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnLogout = new JButton("logout");
		btnLogout.setBounds(600, 6, 110, 38);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				System.exit(0);
			}
		});

		JButton btnNewButtonModify = new JButton("Modify Account");
		btnNewButtonModify.setBounds(5, 6, 110, 38);
		btnNewButtonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButtonModify != null) {
					account frame = new account();
					frame.setVisible(true);
					//btnDelete.setEnabled(false); // This will disable the Button
					//btnUpdate.setVisible(false); // This will make the button Disappear visually only.
					//btnUpdate.setEnabled(false); // This will disable the Button

				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButtonModify);

		JLabel label = new JLabel("");
		label.setBounds(115, 6, 110, 38);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(225, 6, 110, 38);
		contentPane.add(label_1);
		contentPane.add(btnLogout);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(5, 44, 110, 38);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(325, 44, 110, 38);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("");
		label_4.setBounds(425, 44, 110, 38);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(525, 44, 110, 38);
		contentPane.add(label_5);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(115, 44, 110, 38);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUser);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(225, 44, 110, 38);
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAmount);

		JLabel lblSSN = new JLabel("SSN");
		lblSSN.setBounds(325, 44, 110, 38);
		lblSSN.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSSN);

		JLabel lblMemo = new JLabel("Memo");
		lblMemo.setBounds(425, 44, 110, 38);
		lblMemo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMemo);

		JLabel lblCReason = new JLabel("Cancel Reason");
		lblCReason.setBounds(525, 44, 110, 38);
		lblCReason.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCReason);

		JLabel label_6 = new JLabel("");
		label_3.setBounds(635, 44, 110, 38);
		contentPane.add(label_6);

		JButton btnSendMoney = new JButton("Send Money");
		btnSendMoney.setBounds(5, 82, 110, 38);
		btnSendMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DriverManager.getConnection(url, user, Password);


					String sql1 = "insert into SEND_TRANSACTION (STid, User, Amount, SSN, Memo, Cancel_Reason, Date_Time) values (NULL,?,?,?,?,?,now())";
					mystmt = conn.prepareStatement(sql1);
					mystmt.setString(1, textFieldUser.getText());
					mystmt.setString(2, textFieldAmount.getText());
					mystmt.setString(3, textFieldSSN.getText());
					mystmt.setString(4, textFieldMemo.getText());
					mystmt.setString(5, textFieldCReason.getText());
					mystmt.executeUpdate();

//					String sql2 = "insert into BANK_ACCOUNT (BankID, BANumber) values (?,?)";
//					mystmt = conn.prepareStatement(sql2);		
//					mystmt.setString(1, textFieldBAid.getText());
//					mystmt.setString(2, textFieldBACC.getText());
//					
//					mystmt.executeUpdate();
//					
					System.out.println("Money Sent");

					DB.login frame = new DB.login();
					frame.setVisible(true);

				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		contentPane.add(btnSendMoney);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(115, 82, 110, 38);
		textFieldUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				/*switch (e.getKeyCode()) 
				{
				 case KeyEvent.VK_BACK_SPACE:
				 break;
				 case KeyEvent.VK_ENTER:
				 textFieldUser.setText(textFieldUser.getText());
				 break;
				 default:
				 EventQueue.invokeLater(new Runnable() {
					@Override
					
					public void run() {
						String txt = textFieldUser.getText();
						autoComplete(txt);
					}					
					
				});
				}*/
			}
		});

		textFieldUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				try 
//				{
//					conn  = DriverManager.getConnection(url, user, Password);
//					
//					
//				    String sql1  = "insert into SEND_TRANSACTION (STid, Amount) values (?,?)";
//				    mystmt = conn.prepareStatement(sql1);
//					mystmt.setString(1, textFieldUser.getText());
//					mystmt.setString(2, textFieldAmount.getText());
//					
//					mystmt.executeUpdate();
//					
////					String sql2 = "insert into BANK_ACCOUNT (BankID, BANumber) values (?,?)";
////					mystmt = conn.prepareStatement(sql2);		
////					mystmt.setString(1, textFieldBAid.getText());
////					mystmt.setString(2, textFieldBACC.getText());
////					
////					mystmt.executeUpdate();
////					
//					System.out.println("User Added");
//					
//					login frame = new login();
//					frame.setVisible(true);
//					
//				}
//				catch (Exception exc) {
//					exc.printStackTrace();
//				}

			}
		});
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(225, 82, 110, 38);
		contentPane.add(textFieldAmount);
		textFieldAmount.setColumns(10);

		textFieldSSN = new JTextField();
		textFieldSSN.setBounds(335, 82, 110, 38);
		contentPane.add(textFieldSSN);
		textFieldSSN.setColumns(10);

		textFieldMemo = new JTextField();
		textFieldMemo.setBounds(443, 82, 110, 38);
		contentPane.add(textFieldMemo);
		textFieldMemo.setColumns(10);

		textFieldCReason = new JTextField();
		textFieldCReason.setBounds(550, 82, 110, 38);
		contentPane.add(textFieldCReason);
		textFieldCReason.setColumns(10);


		JLabel label_7 = new JLabel("");
		label_7.setBounds(635, 82, 110, 38);
		contentPane.add(label_7);

		JButton btnRequestMoney = new JButton("Request Money");
		btnRequestMoney.setBounds(5, 120, 110, 38);
		btnRequestMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn = DriverManager.getConnection(url, user, Password);


					String sql1 = "insert into REQUEST_TRANSACTION (RTid, User, Amount, SSN, Memo, Date_Time) values (NULL,?,?,?,?,now())";
					mystmt = conn.prepareStatement(sql1);
					mystmt.setString(1, textFieldRequest.getText());
					mystmt.setString(2, textFieldAmt.getText());
					mystmt.setString(3, textFieldSSN.getText());
					mystmt.setString(4, textFieldMemo.getText());
					mystmt.executeUpdate();

//					String sql2 = "insert into BANK_ACCOUNT (BankID, BANumber) values (?,?)";
//					mystmt = conn.prepareStatement(sql2);		
//					mystmt.setString(1, textFieldBAid.getText());
//					mystmt.setString(2, textFieldBACC.getText());
//					
//					mystmt.executeUpdate();
//					
					System.out.println("Money Requested");

					DB.login frame = new DB.login();
					frame.setVisible(true);

				} catch (Exception exc) {
					exc.printStackTrace();
				}

			}

		});
		contentPane.add(btnRequestMoney);

		textFieldRequest = new JTextField();
		textFieldRequest.setBounds(115, 120, 110, 38);
		contentPane.add(textFieldRequest);
		textFieldRequest.setColumns(10);

		textFieldAmt = new JTextField();
		textFieldAmt.setBounds(225, 120, 110, 38);
		contentPane.add(textFieldAmt);
		textFieldAmt.setColumns(10);

		textFieldSSN = new JTextField();
		textFieldSSN.setBounds(335, 120, 110, 38);
		contentPane.add(textFieldSSN);
		textFieldSSN.setColumns(10);

		textFieldMemo = new JTextField();
		textFieldMemo.setBounds(443, 120, 110, 38);
		contentPane.add(textFieldMemo);
		textFieldMemo.setColumns(10);

		JLabel label_8 = new JLabel("");
		label_8.setBounds(635, 120, 110, 38);
		contentPane.add(label_8);

		JButton btnStatements = new JButton("Statements");
		btnStatements.setBounds(5, 158, 110, 38);
		btnStatements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// Get a Connection to DB
					conn = DriverManager.getConnection(url, user, Password);
					Statement mystmt = conn.createStatement();

					ResultSet rs = mystmt.executeQuery("SELECT * FROM SEND_TRANSACTION WHERE date_time Between '2020-01-01' AND '2020-05-30'");
					System.out.println("STid		            	User                         Amount                       SSN");
					while (rs.next()) {
						String STid = rs.getString("STid");
						System.out.print(STid + " \t\t\t\t\t ");
						String User = rs.getString("User");
						System.out.print(User + " \t\t\t\t\t ");
						String Amount = rs.getString("Amount");
						System.out.print(Amount + " \t\t\t\t\t ");
						String SSN = rs.getString("SSN");
						System.out.print(SSN + " \t\t\t\t\n ");
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		contentPane.add(btnStatements);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(115, 158, 110, 38);
		contentPane.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(225, 158, 110, 38);
		contentPane.add(dateChooser_1);

		JLabel label_9 = new JLabel("");
		label_9.setBounds(335, 158, 110, 38);
		contentPane.add(label_9);

		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(115, 196, 110, 38);
		contentPane.add(dateChooser_2);

		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(225, 196, 110, 38);
		contentPane.add(dateChooser_3);

		JButton btnSearchTransactions = new JButton("Search Transactions");
		btnSearchTransactions.setBounds(5, 196, 110, 38);
		btnSearchTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Get a Connection to DB
					conn = DriverManager.getConnection(url, user, Password);
					Statement mystmt = conn.createStatement();
					ResultSet rs = mystmt.executeQuery("SELECT * FROM SEND_TRANSACTION WHERE date_time BETWEEN '2020-05-06' AND '2020-05-30'");// WHERE date_time BETWEEN NOW() - INTERVAL 30 DAY AND NOW()");

					//String date_time = rs.getString(dateChooser_2());
					//String date_time = rs.getString(dateChooser_3());

					System.out.println("STid			User           Amount          Date                SSN 			             MEMO                   CANCEL_REASON \t ");

					while (rs.next()) {
						String STid = rs.getString("STid");
						System.out.print(STid + " \t\t ");
						String User = rs.getString("User");
						System.out.print(User + " \t\t ");
						String Amount = rs.getString("Amount");
						System.out.print(Amount + " \t\t ");
						String Date = rs.getString("Date_Time");
						System.out.print(Date + " \t\t ");
						String SSN = rs.getString("SSN");
						System.out.print(SSN + " \t\t\t\t\t ");
						String Memo = rs.getString("Memo");
						System.out.print(Memo + " \t\t\t\t\t ");
						String CReason = rs.getString("Cancel_Reason");
						System.out.print(CReason + " \t\t\t\t\n ");
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		contentPane.add(btnSearchTransactions);

		JLabel label_10 = new JLabel("");
		label_10.setBounds(735, 196, 110, 38);
		contentPane.add(label_8);

		JLabel label_11 = new JLabel("");
		label_11.setBounds(5, 234, 110, 38);
		contentPane.add(label_11);


/*	private void databaseNam() {
		
			try
			{
				conn  = DriverManager.getConnection(url, user, Password);
                Statement stmt = conn.createStatement();
                ResultSet rs = mystmt.executeQuery("SELECT * FROM USER_ACCOUNT WHERE Name = '" + textFieldUser.getText() + "'");
         
				while (rs.next()) {
					//String Name = rs.getString("Name");
//					name.add(Name);
					textFieldUser.setText(rs.getString("Name"));
				}
				rs.close();
				stmt.close();
				conn.close();
			}catch (Exception exc) {System.out.println(exc);}
		
	}

	public void autoComplete(String txt) {
		String complete = "";
		int start = txt.length();
		int last = txt.length();
		int a;
		for (a=0; a<name.size(); a++ )
		{
			if (name.get(a).toString().startsWith(txt)) {
				complete = name.get(a).toString();
				last = complete.length();
				break;
			}
		}
		if (last > start)
		{
			textFieldUser.setText(complete);
			textFieldUser.setCaretPosition(last);
			textFieldUser.moveCaretPosition(start);
		}
	}*/
		JButton btnTAmount = new JButton("Total Amount");
		btnTAmount.setBounds(5, 234, 110, 38);
		btnTAmount.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {

				// Get a Connection to DB
				conn = DriverManager.getConnection(url, user, Password);
				Statement mystmt = conn.createStatement();


				String sql1 = "SELECT SUM(Amount) FROM SEND_TRANSACTION WHERE date_time Between '2020-01-01' AND '2020-05-30'";

				PreparedStatement ps = conn.prepareStatement(sql1);
				ResultSet rs = ps.executeQuery(sql1);
				System.out.println("Total amount of money received by the users: ");

				while (rs.next()) {
					System.out.println(rs.getString(1));
				}

				String sql2 = "SELECT SUM(Amount) FROM SEND_TRANSACTION WHERE date_time Between '2020-05-01' AND '2020-05-30'";

				PreparedStatement ps1 = conn.prepareStatement(sql2);
				ResultSet rs1 = ps1.executeQuery(sql2);
				System.out.println("Total amount of money sent by the users in the current month: ");

				while (rs1.next()) {
					System.out.println(rs1.getString(1));
				}

			}
			catch (Exception exc) {
				exc.printStackTrace();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	});
		contentPane.add(btnTAmount);

		JDateChooser dateChooser_4 = new JDateChooser();
		dateChooser_4.setBounds(115, 234, 110, 38);
		contentPane.add(dateChooser_4);

		JDateChooser dateChooser_5 = new JDateChooser();
		dateChooser_5.setBounds(225, 234, 110, 38);
		contentPane.add(dateChooser_5);

		JLabel label_12 = new JLabel("");
		label_12.setBounds(335, 234, 110, 38);
		contentPane.add(label_12);

		JDateChooser dateChooser_6 = new JDateChooser();
		dateChooser_6.setBounds(115, 272, 110, 38);
		contentPane.add(dateChooser_6);

		JDateChooser dateChooser_7 = new JDateChooser();
		dateChooser_7.setBounds(225, 272, 110, 38);
		contentPane.add(dateChooser_7);

		JButton btnBUser = new JButton("Best Users");
		btnBUser.setBounds(5, 272, 110, 38);
		btnBUser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				// Get a Connection to DB
				conn = DriverManager.getConnection(url, user, Password);
				Statement mystmt = conn.createStatement();


				String sql1 = "SELECT  STid, MAX(Amount) FROM SEND_TRANSACTION WHERE date_time Between '2020-05-01' AND '2020-05-30'";

				PreparedStatement ps = conn.prepareStatement(sql1);
				ResultSet rs = ps.executeQuery(sql1);

				System.out.println("Transactions with maximum amount of money: ");
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}

				String sql2 = "SELECT User, MAX(AMOUNT) FROM SEND_TRANSACTION WHERE date_time Between '2020-05-01' AND '2020-05-30'";

				PreparedStatement ps1 = conn.prepareStatement(sql2);
				ResultSet rs1 = ps1.executeQuery(sql2);
				System.out.println("Best User: ");

				while (rs1.next()) {
					System.out.println(rs1.getString(1));
				}
				}
			catch (Exception exc)
			{
				exc.printStackTrace();
			}
		}
	});
		contentPane.add(btnBUser);

	JLabel label_13 = new JLabel("");
		label_13.setBounds(735, 196, 110, 38);
		contentPane.add(label_13);

	JLabel label_14 = new JLabel("");
		label_14.setBounds(5, 234, 110, 38);
		contentPane.add(label_14);
	}
}