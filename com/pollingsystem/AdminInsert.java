package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;

public class AdminInsert extends JFrame implements ActionListener,KeyListener{

			JLabel lbladminid,lbladminnm,lbladminem,lbladminph,lbladmingen,lbladminad,lbladminpass,validate_msg;
			JTextField txtadminid,txtadminnm,txtadminem,txtadminph,txtadminad;
			JPasswordField txtadminpass;
			JRadioButton jm,jf;
			ButtonGroup bg;
			JButton btnsave,btnclose,btnclear,btnnew;
			String gender;
			Connection con;
 			PreparedStatement st;
			JProgressBar jp;
			int val=0;
			
	AdminInsert(){
		setSize(1400,800);
		setTitle("ADMIN INSERT");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
	    Image bg2= bg1.getImage().getScaledInstance(1400,800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0,1400,800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("admin_logo.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("ADMIN",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Admin Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(500,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
	    lbladminid=new JLabel("ADMIN ID:");
	    lbladminid.setBounds(450,150,150,30);
	    lbladminid.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminid);
		image.add(lbladminid);
		txtadminid=new JTextField();
		txtadminid.setText(getID());
		txtadminid.setFont(new Font("Arial",Font.BOLD,16));
		txtadminid.setEditable(false);
		txtadminid.addKeyListener(this);
		txtadminid.setBounds(600,150,250,30);
		add(txtadminid);
		
		lbladminnm=new JLabel("ADMIN NAME:");
		lbladminnm.setBounds(450,200,150,30);
		lbladminnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminnm);
		image.add(lbladminnm);
		txtadminnm=new JTextField();
		txtadminnm.setBounds(600,200,250,30);
		txtadminnm.addKeyListener(this);
		txtadminnm.setFont(new Font("Arial",Font.BOLD,16));
		add(txtadminnm);
		image.add(txtadminnm);
		
		lbladminem=new JLabel("EMAIL: ");
		lbladminem.setBounds(450,250,150,30);
		lbladminem.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminem);
		image.add(lbladminem);
		txtadminem=new JTextField();
		txtadminem.setBounds(600,250,250,30);
		txtadminem.addKeyListener(this);
		txtadminem.setFont(new Font("Arial",Font.BOLD,16));
		add(txtadminem);
		image.add(txtadminem);
		
		validate_msg=new JLabel();
		validate_msg.setBounds(880,250,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		lbladminph=new JLabel("PHONE No.:");
		lbladminph.setBounds(450,300,150,30);
		lbladminph.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminph);
		image.add(lbladminph);
		txtadminph=new JTextField();
		txtadminph.setBounds(600,300,250,30);
		txtadminph.addKeyListener(this);
		txtadminph.setFont(new Font("Arial",Font.BOLD,16));
		add(txtadminph);
		image.add(txtadminph);
		
		lbladmingen=new JLabel("GENDER:");
		lbladmingen.setBounds(450,400,100,30);
		lbladmingen.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladmingen);
		image.add(lbladmingen);
		jm=new JRadioButton("Male");
		jm.setBounds(600,400,100,30);
		jm.setFont(new Font("Arial",Font.BOLD,16));
		jm.setContentAreaFilled(false);
		jf=new JRadioButton("Female");
		jf.setBounds(700,400,100,30);
		jf.setFont(new Font("Arial",Font.BOLD,16));
		jf.setContentAreaFilled(false);
		bg=new ButtonGroup();
		bg.add(jm);
		bg.add(jf);
		add(jm);
		add(jf);
		image.add(jm);
		image.add(jf);
		
		lbladminad=new JLabel("ADDRESS:");
		lbladminad.setBounds(450,350,150,30);
		lbladminad.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminad);
		image.add(lbladminad);
		txtadminad=new JTextField();
		txtadminad.setBounds(600,350,250,30);
		txtadminad.addKeyListener(this);
		add(txtadminad);
		txtadminad.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtadminad);
		
		lbladminpass=new JLabel("PASSWORD:");
		lbladminpass.setBounds(450,450,250,30);
		lbladminpass.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminpass);
		image.add(lbladminpass);
		txtadminpass=new JPasswordField();
		txtadminpass.setBounds(600,450,250,30);
		txtadminpass.addKeyListener(this);
		add(txtadminpass);
		txtadminpass.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtadminpass);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,500,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
				
		jm.addActionListener(this);
		jf.addActionListener(this);
		
		btnsave=new JButton("SAVE");
		btnsave.setBounds(450,550,150,35);
		btnsave.setBackground(new Color(173,216,230));
		btnsave.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnsave);
		image.add(btnsave);
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(620,550,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(790,550,150,35);
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		image.add(btnclear);
			
		btnnew=new JButton("NEW ID");
		btnnew.setBounds(870,150,100,30);
		btnnew.setFont(new Font("BOLD",Font.BOLD,16));
		btnnew.setBackground(new Color(173,216,230));
		add(btnnew);
		image.add(btnnew);
		
		btnsave.addActionListener(this);
		btnclose.addActionListener(this);
		btnclear.addActionListener(this);
		btnnew.addActionListener(this);
			
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

			
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				new AdminInsert ();
			
			}


			@Override
			public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
				//txtadminid,txtadminnm,txtadminem,txtadminph,txtadminad,txtadminpass;
				if (ae.getSource()==btnsave)
				{
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
						String sql="insert into Admin values(?,?,?,?,?,?,?,?)";
						st=con.prepareStatement(sql);
						String a=txtadminid.getText();
						String b=txtadminnm.getText();
						String c=txtadminem.getText();
						String d=txtadminph.getText();
						String e=txtadminph.getText();
						String f=txtadminpass.getText();
						
						//insert * into user value(?)
						st.setString(1,a);
						st.setString(2, b);
						st.setString(3, c);
						st.setString(4,d);
						st.setString(5,gender);
						st.setString(6, e);
						st.setString(7, f);
						st.setInt(8, 1);
						int g=st.executeUpdate();
						con.close();
						JOptionPane.showMessageDialog(this, "Data Saved");
						}
				
				
				

				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
				
				}
				
				
				if(ae.getSource()==btnnew)
				{
					String r=getID();
					txtadminid.setText(r);
				}
				
			

				if(ae.getSource()==jm) {
					gender="MALE";
				}
				if (ae.getSource()==jf) {
					gender="FEMALE";
				}
			

				
				if(ae.getSource()==btnclose)
				{
					this.dispose();//for close the window
				}
				if(ae.getSource()==btnclear)
				{
					txtadminid.setText("");
					txtadminnm.setText("");
					txtadminem.setText("");
					txtadminph.setText("");
					txtadminad.setText("");
					txtadminpass.setText("");
				}
			}
		


String getID()
{
	String aid;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		String sql="select adminid  from admin";
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery(sql);
		if (rs.next())
		{
			rs.last();
			String g=rs.getString(1);
			String w=g.substring(2,g.length());
			int x=Integer.parseInt(w);
			if (x<10)
				aid="AD"+"000"+(x+1);
			else if(x>=10 && x<99)
				aid="AD"+"00"+(x+1);
			else if(x>=100 && x<999)
				aid="AD"+"0"+(x+1);
			else
				aid="AD"+(x+1);
		}
		else {
			JOptionPane.showMessageDialog(this, "Record Not Found");
			aid="AD0001";
		}
		return aid;
	}


	catch (Exception ex) {
		return ex.toString();
	}
}


@Override
public void keyPressed(KeyEvent ar) {
	// TODO Auto-generated method stub
	//txtadminid,txtadminnm,txtadminem,txtadminph,txtadminad,txtadminpass
	if(ar.getSource()==txtadminid && txtadminid.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminnm.requestFocus();
			val=val+15;
			jp.setValue(val);

		}
	}
	if(ar.getSource()==txtadminnm && txtadminnm.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminem.requestFocus();
			val=val+15;
			jp.setValue(val);

		}
	}
	if(ar.getSource()==txtadminem && txtadminem.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminnm.requestFocus();
			val=val+14;
			jp.setValue(val);

		}
	}
	if(ar.getSource()==txtadminid && txtadminid.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminph.requestFocus();
			val=val+14;
			jp.setValue(val);

		}
	}
	if(ar.getSource()==txtadminph && txtadminph.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminad.requestFocus();
			val=val+14;
			jp.setValue(val);

		}
	}
	if(ar.getSource()==txtadminad && txtadminad.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminpass.requestFocus();
			val=val+14;
			jp.setValue(val);

		}
	}
	if(ar.getSource()==txtadminpass && txtadminpass.getText().length()>0)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtadminpass.requestFocus();
			val=val+14;
			jp.setValue(val);

		}
	}

}


@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
    //Creating a pattern object
    Pattern pattern = Pattern.compile(regex);
    //Creating a Matcher object
    Matcher matcher = pattern.matcher(txtadminem.getText());
    //Verifying whether given phone number is valid
    if(matcher.matches()) {
  	  validate_msg.setText("Valid");
  	  validate_msg.setForeground(Color.green);
       
    } else {
  	  validate_msg.setText("Invalid");
  	  validate_msg.setForeground(Color.RED);
       
    }
	
}


@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

}