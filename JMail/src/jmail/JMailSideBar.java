package jmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class JMailSideBar extends JPanel{
	
	private static JLabel icon = new JLabel(new ImageIcon("JMailLogoSmall.png"));
	
	private static JButton read = new JButton();
	private static JButton write = new JButton();
	
	private static boolean reading;
	private static boolean writing;
	
	JMailSideBar() {
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.setBorder(new EmptyBorder(5,5,5,5));
		
		reading = false;
		read.setIcon(new ImageIcon("read.png"));
		read.setBorder(null);
		read.setBorder(BorderFactory.createLoweredBevelBorder());
		read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (reading) {
					JMail.mainMenu();
				} else {
					JMail.readMenu();
				}
				writing = false;
				reading = !reading;
			}
		});
		
		writing = false;
		write.setIcon(new ImageIcon("write.png"));
		write.setBorder(null);
		write.setBorder(BorderFactory.createLoweredBevelBorder());
		write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(writing) {
					JMail.mainMenu();
				} else {
					JMail.writeMenu();
				}
				reading = false;
				writing = !writing;
			}
		});
		
		this.add(icon);
		this.add(Box.createGlue());
		this.add(read);
		this.add(new JLabel("Read Mail"));
		this.add(Box.createGlue());
		this.add(write);
		this.add(new JLabel("Write Mail"));
		this.add(Box.createGlue());
	}
}
