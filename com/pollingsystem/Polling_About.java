package com.pollingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Polling_About extends JFrame {
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8;
	public Polling_About() 
	{
		setSize(1400,800);
		setLayout(null);
		setTitle("About");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("AAbout.jpg"));
	    Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image = new JLabel(i3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
		lbl1=new JLabel("ABOUT :-");
		lbl1.setBounds(600,50,300,50);
		lbl1.setFont(new Font("Georgian",Font.BOLD,40));
		lbl1.setForeground(Color.WHITE);
		add(lbl1);
		image.add(lbl1);

		lbl2=new JLabel("'ONLINE POLLING SYSTEM'");
		lbl2.setBounds(400,100,1000,50);
		lbl2.setFont(new Font("Georgian",Font.BOLD,40));
		lbl2.setForeground(Color.ORANGE);
		add(lbl2);
		image.add(lbl2);
		
		lbl3=new JLabel("~ In this Project I have created the way that people vote and how the votes are counted.");
		lbl3.setBounds(200,200,1000,30);
		lbl3.setFont(new Font("Georgian",Font.BOLD,20));
		lbl3.setForeground(Color.WHITE);
		add(lbl3);
		image.add(lbl3);
	
		lbl4=new JLabel("~ This is very helpful in today's time, as most of the people can't go to polling booths");
		lbl4.setBounds(200,250,1000,50);
		lbl4.setFont(new Font("Georgian",Font.BOLD,20));
		lbl4.setForeground(Color.WHITE);
		add(lbl4);
		image.add(lbl4);

		lbl5=new JLabel("So they can vote easily with these systems");
		lbl5.setBounds(220,300,1000,50);
		lbl5.setFont(new Font("Georgian",Font.BOLD,20));
		lbl5.setForeground(Color.WHITE);
		add(lbl5);
		image.add(lbl5);

		lbl6=new JLabel("* Application Type:-         Desktop");
		lbl6.setBounds(400,400,1000,50);
		lbl6.setFont(new Font("Georgian",Font.BOLD,25));
		lbl6.setForeground(Color.WHITE);
		add(lbl6);
		image.add(lbl6);

		lbl7=new JLabel("* Database Used:-            Oracle");
		lbl7.setBounds(400,450,1000,50);
		lbl7.setFont(new Font("Georgian",Font.BOLD,25));
		lbl7.setForeground(Color.WHITE);
		add(lbl7);
		image.add(lbl7);

		lbl8=new JLabel("* Technologies Used:-     Java,Java-Swing,Java-AWT");
		lbl8.setBounds(400,500,1000,50);
		lbl8.setFont(new Font("Georgian",Font.BOLD,25));
		lbl8.setForeground(Color.WHITE);
		add(lbl8);
		image.add(lbl8);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Polling_About();
	}

}
