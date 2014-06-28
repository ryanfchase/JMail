package jmail;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginPage extends JFrame{
	private static JLabel loginLabel;
	private static JLabel passwordLabel;
	public static JTextField loginField;
	public static JTextField passwordField;
	private static JButton loginButton;
	
	LoginPage() {
		super("JMail Login");
		this.setIconImage(JMail.windowIcon.getImage());
		this.setSize(300,150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		loginPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		loginLabel = new JLabel("Login:");
		passwordLabel = new JLabel("Password:");
		loginField = new JTextField(15);
		loginField.setMaximumSize(new Dimension(1,20));
		passwordField = new JPasswordField(15);
		passwordField.setMaximumSize(new Dimension(1,20));
		loginButton = new JButton("Login");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 //////////////////////////////////
//TODO			//Login and authenticate account//
			   //////////////////////////////////
				
				JMail.properties = new Properties(); 
				JMail.properties.setProperty("mail.store.protocol", "imaps"); 
				JMail.properties.put("mail.smtp.auth", "true"); 
				JMail.properties.put("mail.smtp.starttls.enable", "true"); 
				JMail.properties.put("mail.smtp.host", "smtp.gmail.com"); 
				JMail.properties.put("mail.smtp.port", "587");	
				
				
				JMail.email = loginField.getText();
				JMail.pass = passwordField.getText(); 

				
				
				JMail.session =  Session.getInstance( JMail.properties,
						new javax.mail.Authenticator() { 
					protected PasswordAuthentication getPasswordAuthentication() { 
						return new PasswordAuthentication(JMail.email, JMail.pass); 
						} 
						 }); 
				
				
				try {
					JMail.store = JMail.session.getStore();
				} catch (NoSuchProviderException e) {
					System.out.println("JMail.store = JMail.session.getStore() -- err");
					e.printStackTrace();
				} 
				try {
					JMail.store.connect("imap.gmail.com",JMail.email, JMail.pass);
				} catch (MessagingException e) {
					System.out.println("JMail.store.connect(\"...\") -- err");
					e.printStackTrace();
				} 
				
				
				try {
					JMail.inbox = JMail.store.getFolder("INBOX");
				} catch (MessagingException e) {
					System.out.println("inbox = JMail.store.getFolder()");
					e.printStackTrace();
				} 
				try {
					JMail.inbox.open(Folder.READ_ONLY);
				} catch (MessagingException e) {
					System.out.println("Jmail.inbox.open()");
					e.printStackTrace();
				}

				JMail.initMenus();
				JMail.mainMenu();

				
			}
        });      
		
		JPanel login = new JPanel();
		login.setLayout(new BoxLayout(login, BoxLayout.X_AXIS));
		login.add(loginLabel);
		login.add(Box.createGlue());
		login.add(loginField);
		
		JPanel password = new JPanel();
		password.setLayout(new BoxLayout(password, BoxLayout.X_AXIS));
		password.add(passwordLabel);
		password.add(Box.createGlue());
		password.add(passwordField);
		
		loginPanel.add(Box.createGlue());
		loginPanel.add(login);
		loginPanel.add(password);
		loginPanel.add(Box.createGlue());
		loginPanel.add(loginButton);
		loginPanel.add(Box.createGlue());
		
		this.add(loginPanel);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void reset() {
		loginField.setText("");
		passwordField.setText("");
	}
}
