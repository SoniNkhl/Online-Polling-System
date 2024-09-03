package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class Party_Insert extends JFrame implements ActionListener,KeyListener{
	JLabel lblpnm,lblpdesc,lblps,lblsdesc,lblpem;
	JTextField txtpnm,txtps,txtpem;
	JTextArea txtpdesc,txtsdesc;
	JButton btnsave,btnclose,btnclear;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	JProgressBar jp;
	int val=0;

	public Party_Insert()
	{
		setSize(1400,800);
		setTitle("PARTY INSERT");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
	    Image bg2= bg1.getImage().getScaledInstance(1400,800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0,1400,800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("party.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("PARTY",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Party Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(500,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
	    lblpnm=new JLabel("PARTY NAME");
	    lblpnm.setBounds(400,150,150,30);
	    lblpnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblpnm);
		image.add(lblpnm);
		txtpnm=new JTextField();
		txtpnm.setFont(new Font("Arial",Font.BOLD,16));
		txtpnm.addKeyListener(this);
		txtpnm.setBounds(600,150,250,30);
		add(txtpnm);
		
		lblpdesc=new JLabel("NAME DESCRIPTION");
		lblpdesc.setBounds(400,200,200,30);
		lblpdesc.setFont(new Font("Arial",Font.BOLD,16));
		add(lblpdesc);
		image.add(lblpdesc);
		txtpdesc=new JTextArea();
		txtpdesc.setFont(new Font("Arial",Font.BOLD,16));
		txtpdesc.addKeyListener(this);
		txtpdesc.setBounds(600,200,250,50);
		add(txtpdesc);
		image.add(txtpdesc);
		
		lblps=new JLabel("PARTY SYMBOL");
		lblps.setBounds(400,270,150,30);
		lblps.setFont(new Font("Arial",Font.BOLD,16));
		add(lblps);
		image.add(lblps);
		txtps=new JTextField();
		txtps.setFont(new Font("Arial",Font.BOLD,16));
		txtps.addKeyListener(this);
		txtps.setBounds(600,270,250,30);
		add(txtps);
		
		lblsdesc=new JLabel("SYMBOL DESCRIPTION");
		lblsdesc.setBounds(400,320,200,30);
		lblsdesc.setFont(new Font("Arial",Font.BOLD,16));
		add(lblsdesc);
		image.add(lblsdesc);
		txtsdesc=new JTextArea();
		txtsdesc.setFont(new Font("Arial",Font.BOLD,16));
		txtsdesc.addKeyListener(this);
		txtsdesc.setBounds(600,320,250,50);
		add(txtsdesc);
		image.add(txtsdesc);

		lblpem=new JLabel("PARTY EMAIL");
		lblpem.setBounds(400,390,200,30);
		lblpem.setFont(new Font("Arial",Font.BOLD,16));
		add(lblpem);
		image.add(lblpem);
		txtpem=new JTextField();
		txtpem.setFont(new Font("Arial",Font.BOLD,16));
		txtpem.addKeyListener(this);
		txtpem.setBounds(600,390,250,30);
		add(txtpem);
		image.add(txtpem);

		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,440,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
	    
		btnsave=new JButton("SAVE");
		btnsave.setBounds(450,480,150,35);
		btnsave.setBackground(new Color(173,216,230));
		btnsave.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnsave);
		image.add(btnsave);
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(620,480,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(790,480,150,35);
		btnclear.setBackground(new Color(173,216,230));
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclear);
		image.add(btnclear);
		
		btnsave.addActionListener(this);
		btnclose.addActionListener(this);
		btnclear.addActionListener(this);
	    
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Party_Insert();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		//txtpnm,txtps,txtpdesc,txtsdesc
		if (ae.getSource()==btnsave)
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="insert into party values(?,?,?,?,?)";
				st=con.prepareStatement(sql);
				String a=txtpnm.getText();
				String b=txtpdesc.getText();
				String c=txtps.getText();
				String d=txtsdesc.getText();
				String e=txtpem.getText();
				
				//insert * into user value(?)
				st.setString(1,a);
				st.setString(2, b);
				st.setString(3, c);
				st.setString(4,d);
				st.setString(5,e);
				int g=st.executeUpdate();
				con.close();
				JOptionPane.showMessageDialog(this, "Data Saved");
				}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();//for close the window
		}
		if(ae.getSource()==btnclear)
		{
			txtpnm.setText("");
			txtpdesc.setText("");
			txtps.setText("");
			txtsdesc.setText("");
			txtpem.setText("");
		}
	}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==txtpnm && txtpnm.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtpdesc.requestFocus();
				val=val+20;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtpdesc && txtpdesc.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtps.requestFocus();
				val=val+20;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtps && txtps.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtsdesc.requestFocus();
				val=val+20;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtsdesc && txtsdesc.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtpem.requestFocus();
				val=val+20;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtpem && txtpem.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtpem.requestFocus();
				val=val+20;
				jp.setValue(val);

			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
