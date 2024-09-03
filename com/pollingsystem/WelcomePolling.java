package com.pollingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WelcomePolling extends JFrame implements ActionListener{
	JLabel lbl,lbl1,lblvisid,lblNewLabel1;
	JButton Admin,User,abt;
	ButtonGroup bg;	
	Connection con;
	PreparedStatement st;
	
	public WelcomePolling()
	{
		setSize(1400,800);
		setLayout(null);
		setTitle("Welcome");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("welcome_bg.jpg"));
	    Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image = new JLabel(i3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon about = new ImageIcon(ClassLoader.getSystemResource("information.png"));
	    Image about1 = about.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon about2 = new ImageIcon(about1);
	    
	    abt=new JButton(about2);
	    abt.setBounds(1200,600, 100, 100);
	    abt.setContentAreaFilled(false);
	    abt.setFocusPainted(false);
	    abt.setBorderPainted(false);
		add(abt);
		image.add(abt);
		abt.addActionListener(this); 

		lbl=new JLabel("WELCOME TO ONLINE POLLING SYSTEM");
		lbl.setBounds(300,50,1000,50);
		lbl.setFont(new Font("Georgian",Font.BOLD,40));
		lbl.setForeground(Color.WHITE);
		add(lbl);
		image.add(lbl);
		
		ImageIcon user_icon1 = new ImageIcon(ClassLoader.getSystemResource("welcome_user.png"));
	    Image user_icon2 = user_icon1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon user_icon3 = new ImageIcon(user_icon2);
	  
		User=new JButton(user_icon3);
		User.setBounds(350,250,300,100);
		User.setText("USER LOGIN");
		User.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		User.setContentAreaFilled(false);
		User.setFocusPainted(false);
		User.setBorderPainted(false);
		User.setForeground(new Color(117,12,103));
		add(User);
		image.add(User);
		
		ImageIcon admin_icon1 = new ImageIcon(ClassLoader.getSystemResource("welcome_admin.png"));
	    Image admin_icon2 = admin_icon1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon admin_icon3 = new ImageIcon(admin_icon2);
	    
		Admin=new JButton(admin_icon3);
		Admin.setBounds(750,250,300,100);
		Admin.setText("ADMIN LOGIN");
		Admin.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		Admin.setContentAreaFilled(false);
		Admin.setFocusPainted(false);
		Admin.setBorderPainted(false);
		Admin.setForeground(new Color(117,12,103));
		add(Admin);
		image.add(Admin);
		
	
		
		Admin.addActionListener(this);
		User.addActionListener(this);
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WelcomePolling();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==User)
		{
			new UserLogin();
		}
		if(ae.getSource()==Admin)
		{
			new AdminLogin();
		}
		if(ae.getSource()==abt)
		{
			new Polling_About();
		}
	}

}
