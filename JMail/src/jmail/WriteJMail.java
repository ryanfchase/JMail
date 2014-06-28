package jmail;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WriteJMail extends JPanel{

	private static JTextField toLabel;
	private static JTextField subjectLabel;
	private static JTextArea contentArea;
	private static JButton send = new JButton();
	
	WriteJMail() {
		this.setLayout(new BorderLayout());
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setBorder(new EmptyBorder(1,1,1,1));
		
		JPanel toPanel = new JPanel();
		toPanel.setLayout(new BoxLayout(toPanel,BoxLayout.X_AXIS));
		toPanel.add(new JLabel("TO: "));
		toLabel = new JTextField(15);
		toLabel.setMaximumSize(new Dimension(1,20));
		toPanel.add(toLabel);
		toPanel.add(Box.createGlue());
		
		JPanel subjectPanel = new JPanel();
		subjectPanel.setLayout(new BoxLayout(subjectPanel,BoxLayout.X_AXIS));
		subjectPanel.add(new JLabel("SUBJECT:"));
		subjectLabel = new JTextField(15);
		subjectLabel.setMaximumSize(new Dimension(1,20));
		subjectPanel.add(subjectLabel);
		subjectPanel.add(Box.createGlue());
		
		infoPanel.add(toPanel);
		infoPanel.add(Box.createGlue());
		infoPanel.add(subjectPanel);
		infoPanel.add(Box.createGlue());
		
		send.setIcon(new ImageIcon("send.png"));
		send.setBorder(null);
		send.setBorder(BorderFactory.createLoweredBevelBorder());
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 //////////////////
//TODO		//Send a Message//
		   //////////////////
				try{
					Message message = new MimeMessage(JMail.session);
					message.setFrom(new InternetAddress("Sender")); 
					message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("Recipient")); 
					message.setSubject("Subject"); 
					message.setText("Content");
				} catch(AddressException e){
					System.out.println("AddressException err" + e.getMessage());
				} catch(MessagingException e){
					System.out.println("MessagingException err" + e.getMessage());
				}
				

			}
		});
		
		contentArea = new JTextArea();
		JScrollPane msgBox = new JScrollPane(contentArea);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		topPanel.add(infoPanel);
		topPanel.add(send,BorderLayout.EAST);
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(msgBox);
	}
}
