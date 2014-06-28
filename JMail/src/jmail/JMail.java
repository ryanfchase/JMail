package jmail;

import java.awt.BorderLayout;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JMail extends JFrame{
	
	public static JMail jMail;
	
	private static LoginPage login;
	
	public static ImageIcon windowIcon = new ImageIcon("icon.png");
	public static JLabel logoLabel = new JLabel(new ImageIcon("JMailLogo.png"));
	
	private static JMailSideBar sideBar;
	private static WriteJMail write;
	private static ReadJMail read;
	
	static Properties properties;
	static Store store;
	static Session session;
	static Folder inbox;

	static String email = "";

	static String pass = "";
	
	JMail() {
		super("JMail Client");
		
		System.out.println("Test");
		
		this.setIconImage(windowIcon.getImage());
		this.setSize(800,600);
		this.setVisible(false);
		sideBar = new JMailSideBar();
		this.add(sideBar,BorderLayout.WEST);
		this.add(new JLabel(new ImageIcon("JMailLogo.png")),BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void initMenus() {
		read = new ReadJMail();
		write = new WriteJMail();
		jMail.setTitle("JMail Client - "+email);
	}
	
	public static void mainMenu() {
		login.setVisible(false);
		jMail.remove(read);
		jMail.remove(write);
		jMail.add(logoLabel);
		jMail.revalidate();
		jMail.repaint();
		jMail.setVisible(true);
	}
	
	public static void writeMenu() {
		jMail.remove(read);
		jMail.remove(logoLabel);
		jMail.add(write);
		jMail.revalidate();
		jMail.repaint();
	}
	
	public static void readMenu() {
		jMail.remove(write);
		jMail.remove(logoLabel);
		jMail.add(read);
		jMail.revalidate();
		jMail.repaint();
	}
	
	public static void main(String [] args) {
		login = new LoginPage();
		jMail = new JMail();
	}	
}
