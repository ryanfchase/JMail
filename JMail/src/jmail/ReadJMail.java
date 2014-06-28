package jmail;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class ReadJMail extends JPanel{
	private static int currentMsg;
	
	private static JLabel fromLabel;
	private static JLabel dateLabel;
	private static JLabel subjectLabel;
	private static JTextArea contentArea;
	
	private static JButton nextMsg = new JButton();
	private static JButton prevMsg = new JButton();
	
	private static JButton refresh = new JButton();
	
	private int    jMailIndex; 
	
	ReadJMail() {
		this.setLayout(new BorderLayout());
		
		try {
			jMailIndex = JMail.inbox.getMessageCount();
		} catch (MessagingException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setBorder(new EmptyBorder(1,1,1,1));
		
		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(new BoxLayout(fromPanel,BoxLayout.X_AXIS));
		fromPanel.add(new JLabel("FROM: "));
		fromLabel = new JLabel();
		fromPanel.add(fromLabel);
		fromPanel.add(Box.createGlue());
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.X_AXIS));
		datePanel.add(new JLabel("DATE: "));
		dateLabel = new JLabel();
		datePanel.add(dateLabel);
		datePanel.add(Box.createGlue());
		
		JPanel subjectPanel = new JPanel();
		subjectPanel.setLayout(new BoxLayout(subjectPanel,BoxLayout.X_AXIS));
		subjectPanel.add(new JLabel("SUBJECT:"));
		subjectLabel = new JLabel();
		subjectPanel.add(subjectLabel);
		subjectPanel.add(Box.createGlue());
		
		
		/* INITIAL CONDITIONS */
		try {
			Message toRead = JMail.inbox.getMessage(JMail.inbox.getMessageCount());
			//System.out.println(JMail.inbox.getMessageCount());
			Address [] senders = toRead.getFrom(); 
			//do first one
			String from = senders[0].toString() + "...";
			fromLabel.setText(from);
			
			String date =    toRead.getSentDate().toString(); 
			dateLabel.setText(date);
			
			String subject = toRead.getSubject().toString(); 
			subjectLabel.setText(subject);
			
			/* UPDATE CONTENT PAGE */
			
			
			JMail.jMail.repaint();
			//JMail.jMail.invalidate(); ...validate();

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("MessagingException err" + e.getMessage());
		} 
		/* END INITIAL CONDITIONS*/
		
		JPanel buttonPanel = new JPanel();
		prevMsg.setIcon(new ImageIcon("prev.png"));
		prevMsg.setBorder(null);
		prevMsg.setBorder(BorderFactory.createLoweredBevelBorder());
		prevMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 /////////////////////////////////////
//TODO			//Go to the next email in the inbox//
			   /////////////////////////////////////
				try {
					jMailIndex--;
					if(jMailIndex < 1){
						jMailIndex = 1;
						System.out.println("No more messages");
					}
					Message toRead = JMail.inbox.getMessage(JMail.inbox.getMessageCount());
					//System.out.println(JMail.inbox.getMessageCount());
					Address [] senders = toRead.getFrom(); 

					String from = senders[0].toString() + "...";
					fromLabel.setText(from);
					String date =    toRead.getSentDate().toString(); 
					dateLabel.setText(date);
					String subject = toRead.getSubject().toString(); 
					subjectLabel.setText(subject);
					
					JMail.jMail.repaint();
					//JMail.jMail.invalidate(); ...validate();

				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					System.out.println("MessagingException err" + e.getMessage());
				} 

			}
		});
		
		nextMsg.setIcon(new ImageIcon("next.png"));
		nextMsg.setBorder(null);
		nextMsg.setBorder(BorderFactory.createLoweredBevelBorder());
		nextMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 /////////////////////////////////////////
//TODO			//Go to the previous email in the inbox//
			   /////////////////////////////////////////
			}
		});
		
		refresh.setIcon(new ImageIcon("refresh.png"));
		refresh.setBorder(null);
		refresh.setBorder(BorderFactory.createLoweredBevelBorder());
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 /////////////////////////////////////////////////
//TODO			//refresh the inbox (Go to 1st message as well)//
			   /////////////////////////////////////////////////
				try {
					Message toRead = JMail.inbox.getMessage(JMail.inbox.getMessageCount());
					//System.out.println(JMail.inbox.getMessageCount());
					Address [] senders = toRead.getFrom(); 
					//do first one
					String from = senders[0].toString() + "...";
					fromLabel.setText(from);
					
					String date =    toRead.getSentDate().toString(); 
					dateLabel.setText(date);
					
					String subject = toRead.getSubject().toString(); 
					subjectLabel.setText(subject);
					
					JMail.jMail.repaint();
					//JMail.jMail.invalidate(); ...validate();

				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					System.out.println("MessagingException err" + e.getMessage());
				} 
			}
		});
		buttonPanel.add(refresh);
		buttonPanel.add(nextMsg);
		buttonPanel.add(prevMsg);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		infoPanel.add(Box.createGlue());
		infoPanel.add(fromPanel);
		infoPanel.add(datePanel);
		infoPanel.add(subjectPanel);
		infoPanel.add(Box.createGlue());
		
		topPanel.add(infoPanel);
		topPanel.add(buttonPanel,BorderLayout.EAST);
		
		this.add(topPanel,BorderLayout.NORTH);
		
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		JScrollPane msgBox = new JScrollPane(contentArea);
		
		this.add(msgBox);
		///////////////////////////
//TODO	//go to the first message//
		///////////////////////////
		try {
			currentMsg = JMail.inbox.getMessageCount();
			goToMsg(currentMsg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private void goToMsg(int msgNum) {
//TODO //Optional Method, appears in solution
		//You can design it in a different way if you choose
	}
}
