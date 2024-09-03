package com.pollingsystem;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

public class Vote extends JFrame implements ActionListener{
	JLabel ldate,ltitle,label,lname,lid,uid,lemail,lphone,lstatus,lgender,uname,uemail,uphone,ustatus,ugender;
	JLabel lblwardno,uwardno,lblcandl,lblcand1,lblcand2,lblcand3,lblcand4,lblcand5;
	JLabel lblsign,lblcandnm,lblpnm,lblpnm1,lblpnm2,lblpnm3,lblpnm4,lblpnm5,lbleid,lblid;
	JPanel jp;	
	JButton back,btn1,btn2,btn3,btn4,btn5;
	JComboBox cuid;
	Connection con;
	PreparedStatement st;
	ResultSet rs;

	public Vote()
	{
		setSize(1920,1080);
		setTitle("VOTE");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);

		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("Voting.jpeg"));
	    Image bg2= bg1.getImage().getScaledInstance(2000, 1080, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 2000,1080);
	    add(image);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("vote.png"));
	    Image i2 = i1.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("   VOTE HERE !!",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(650,0,400, 150);
	    add(image1);
	    image.add(image1);
	
	    ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("elephant.png"));
	    Image i21 = i11.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT);
	    ImageIcon i31 = new ImageIcon(i21);
	    JLabel image11 = new JLabel("",i31,SwingConstants.CENTER);
	    image11.setVerticalTextPosition(JLabel.CENTER);
	    image11.setHorizontalTextPosition(JLabel.RIGHT);
	    image11.setForeground(new Color(0,0,128));
	    image11.setFont(new Font("Arial",Font.BOLD,30));
	    image11.setBounds(950,0,500,860);
	    add(image11);
	    image.add(image11);
	    
	    ImageIcon i12 = new ImageIcon(ClassLoader.getSystemResource("lotus.png"));
	    Image i22 = i12.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT);
	    ImageIcon i32 = new ImageIcon(i22);
	    JLabel image12 = new JLabel("",i32,SwingConstants.CENTER);
	    image12.setVerticalTextPosition(JLabel.CENTER);
	    image12.setHorizontalTextPosition(JLabel.RIGHT);
	    image12.setForeground(new Color(0,0,128));
	    image12.setFont(new Font("Arial",Font.BOLD,30));
	    image12.setBounds(950,0,500,650);
	    add(image12);
	    image.add(image12);

	    ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("hello.png"));
	    Image i23 = i13.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT);
	    ImageIcon i33 = new ImageIcon(i23);
	    JLabel image13 = new JLabel("",i33,SwingConstants.CENTER);
	    image13.setVerticalTextPosition(JLabel.CENTER);
	    image13.setHorizontalTextPosition(JLabel.RIGHT);
	    image13.setForeground(new Color(0,0,128));
	    image13.setFont(new Font("Arial",Font.BOLD,30));
	    image13.setBounds(950,0,500,1300);
	    add(image13);
	    image.add(image13);
	    
	    ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("broom.png"));
	    Image i24 = i14.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT);
	    ImageIcon i34 = new ImageIcon(i24);
	    JLabel image14 = new JLabel("",i34,SwingConstants.CENTER);
	    image14.setVerticalTextPosition(JLabel.CENTER);
	    image14.setHorizontalTextPosition(JLabel.RIGHT);
	    image14.setForeground(new Color(0,0,128));
	    image14.setFont(new Font("Arial",Font.BOLD,30));
	    image14.setBounds(950,0,500,1070);
	    add(image14);
	    image.add(image14);

	    ImageIcon i15 = new ImageIcon(ClassLoader.getSystemResource("bicycle.png"));
	    Image i25 = i15.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT);
	    ImageIcon i35= new ImageIcon(i25);
	    JLabel image15= new JLabel("",i35,SwingConstants.CENTER);
	    image15.setVerticalTextPosition(JLabel.CENTER);
	    image15.setHorizontalTextPosition(JLabel.RIGHT);
	    image15.setForeground(new Color(0,0,128));
	    image15.setFont(new Font("Arial",Font.BOLD,30));
	    image15.setBounds(950,0,500,1520);
	    add(image15);
	    image.add(image15);

	    jp= new JPanel();
		jp.setBounds(0, 0,300, 1080);
		jp.setBackground(new Color(207,208,211));
		image.add(jp);
		jp.setLayout(null);
		
	  	ltitle=new JLabel("        USER ");
	  	ltitle.setBounds(0,0,300,40);
	  	ltitle.setOpaque(true);
	  	ltitle.setForeground(Color.WHITE);
	  	ltitle.setBackground(Color.BLACK);
	  	ltitle.setFont(new Font("Arial",Font.BOLD,50));
	  	jp.add(ltitle);
	  	
	  	label=new JLabel(" ");
	  	label.setBounds(100,100,90,100);
	  	label.setOpaque(true);
	  	label.setForeground(Color.WHITE);
	  	label.setBackground(Color.BLACK);
	  	label.setFont(new Font("Arial",Font.BOLD,100));
	  	jp.add(label);

	  	lid=new JLabel("USER ID:");
	  	lid.setBounds(20,300,100,50);
	  	lid.setForeground(new Color(0,0,0));
	  	lid.setFont(new Font("Arial",Font.BOLD,16));
	  	jp.add(lid);
	  	uid=new JLabel();
	  	uid.setBounds(100,300,200,50);
	  	uid.setForeground(new Color(24, 123, 205));
	  	uid.setFont(new Font("Arial",Font.BOLD,16));
	  	add(uid);
	  	jp.add(uid);
	  	
	  	lname=new JLabel("NAME:");
	  	lname.setBounds(20,360,200,50);
	  	lname.setForeground(new Color(0,0,0));
	  	lname.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lname);
	  	jp.add(lname);
	  	uname=new JLabel();
	  	uname.setBounds(100,360,300,50);
	  	uname.setForeground(new Color(24, 123, 205));
	  	uname.setFont(new Font("Arial",Font.BOLD,16));
	  	add(uname);
	  	jp.add(uname);

	  	lemail=new JLabel("EMAIL:");
	  	lemail.setBounds(20,420,100,50);
	  	lemail.setForeground(new Color(0,0,0));
	  	lemail.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lemail);
	  	jp.add(lemail);
	  	uemail=new JLabel();
	  	uemail.setBounds(100,420,200,50);
	  	uemail.setForeground(new Color(24, 123, 205));
	  	uemail.setFont(new Font("Arial",Font.BOLD,16));
	  	add(uemail);
	  	jp.add(uemail);
	  	
	  	
	  	lphone=new JLabel("PHONE:");
	  	lphone.setBounds(20,480,100,50);
	  	lphone.setForeground(new Color(0,0,0));
	  	lphone.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lphone);
	  	jp.add(lphone);
	  	uphone=new JLabel();
	  	uphone.setBounds(100,480,200,50);
	  	uphone.setForeground(new Color(24, 123, 205));
	  	uphone.setFont(new Font("Arial",Font.BOLD,16));
	  	add(uphone);
	  	jp.add(uphone);
	  	
	  	
	  	lgender=new JLabel("GENDER:");
	  	lgender.setBounds(20,540,100,50);
	  	lgender.setForeground(new Color(0,0,0));
	  	lgender.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lgender);
	  	jp.add(lgender);
	  	ugender=new JLabel();
	  	ugender.setBounds(100,540,200,50);
	  	ugender.setForeground(new Color(24, 123, 205));
	  	ugender.setFont(new Font("Arial",Font.BOLD,16));
	  	add(ugender);
	  	jp.add(ugender);
	  	
	  	lblwardno=new JLabel("WARDNO:");
	  	lblwardno.setBounds(20,600,100,50);
	  	lblwardno.setForeground(new Color(0,0,0));
	  	lblwardno.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblwardno);
	  	jp.add(lblwardno);
	  	uwardno=new JLabel();
	  	uwardno.setBounds(100,600,200,50);
	  	uwardno.setForeground(new Color(24, 123, 205));
	  	uwardno.setFont(new Font("Arial",Font.BOLD,16));
	  	add(uwardno);
	  	jp.add(uwardno);
	  	
	  	lstatus=new JLabel("STATUS:");
	  	lstatus.setBounds(20,660,100,50);
	  	lstatus.setForeground(new Color(0,0,0));
	  	lstatus.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lstatus);
	  	jp.add(lstatus);
	  	ustatus=new JLabel();
	  	ustatus.setBounds(100,660,200,50);
	  	ustatus.setForeground(new Color(24, 123, 205));
	  	ustatus.setFont(new Font("Arial",Font.BOLD,16));
	  	add(ustatus);
	  	jp.add(ustatus);
	  	
	  	back=new JButton("<< BACK");
	  	back.setBounds(0,740,400,50);
	  	back.setForeground(Color.WHITE);
	  	back.setBackground(Color.BLACK);
	  	back.setFont(new Font("Arial",Font.BOLD,16));
	  	add(back);
	  	jp.add(back);
	  	back.addActionListener(this);
	  	
	  	lblcandnm=new JLabel("CANDIDATE NAME");
	  	lblcandnm.setBounds(320,180,250,50);
	  	lblcandnm.setForeground(Color.BLACK);
	  	lblcandnm.setFont(new Font("BOLD",Font.BOLD,20));
	  	add(lblcandnm);
	  	image.add(lblcandnm);

	  	lblcand1=new JLabel();
	  	lblcand1.setBounds(340,300,200,50);
	  	lblcand1.setForeground(Color.BLUE);
	  	lblcand1.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblcand1);
	  	image.add(lblcand1);
	  	
	  	lblcand2=new JLabel();
	  	lblcand2.setBounds(340,410,200,50);
	  	lblcand2.setForeground(Color.BLUE);
	  	lblcand2.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblcand2);
	  	image.add(lblcand2);

	  	lblcand3=new JLabel();
	  	lblcand3.setBounds(340,520,200,50);
	  	lblcand3.setForeground(Color.BLUE);
	  	lblcand3.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblcand3);
	  	image.add(lblcand3);

	  	lblcand4=new JLabel();
	  	lblcand4.setBounds(340,630,200,50);
	  	lblcand4.setForeground(Color.BLUE);
	  	lblcand4.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblcand4);
	  	image.add(lblcand4);

	  	lblcand5=new JLabel();
	  	lblcand5.setBounds(340,740,200,50);
	  	lblcand5.setForeground(Color.BLUE);
	  	lblcand5.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblcand5);
	  	image.add(lblcand5);

	  	lblpnm=new JLabel("PARTY NAME");
	  	lblpnm.setBounds(750,180,250,50);
	  	lblpnm.setForeground(Color.BLACK);
	  	lblpnm.setFont(new Font("BOLD",Font.BOLD,20));
	  	add(lblpnm);
	  	image.add(lblpnm);

	  	lblpnm1=new JLabel();
	  	lblpnm1.setBounds(780,300,200,50);
	  	lblpnm1.setForeground(Color.BLUE);
	  	lblpnm1.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblpnm1);
	  	image.add(lblpnm1);
	  	
	  	lblpnm2=new JLabel();
	  	lblpnm2.setBounds(780,410,200,50);
	  	lblpnm2.setForeground(Color.BLUE);
	  	lblpnm2.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblpnm2);
	  	image.add(lblpnm2);
	    
	  	lblpnm3=new JLabel();
	  	lblpnm3.setBounds(780,520,200,50);
	  	lblpnm3.setForeground(Color.BLUE);
	  	lblpnm3.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblpnm3);
	  	image.add(lblpnm3);

	  	lblpnm4=new JLabel();
	  	lblpnm4.setBounds(780,630,200,50);
	  	lblpnm4.setForeground(Color.BLUE);
	  	lblpnm4.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblpnm4);
	  	image.add(lblpnm4);

	  	lblpnm5=new JLabel();
	  	lblpnm5.setBounds(780,740,200,50);
	  	lblpnm5.setForeground(Color.BLUE);
	  	lblpnm5.setFont(new Font("Arial",Font.BOLD,16));
	  	add(lblpnm5);
	  	image.add(lblpnm5);
	  	
	  	lblsign=new JLabel("SYMBOL");
	  	lblsign.setBounds(1150,180,250,50);
	  	lblsign.setForeground(Color.BLACK);
	  	lblsign.setFont(new Font("BOLD",Font.BOLD,20));
	  	add(lblsign);
	  	image.add(lblsign);

	  	lblid=new JLabel("ELECTION-ID:");
	  	lblid.setBounds(310,30,250,30);
	  	lblid.setForeground(Color.BLACK);
	  	lblid.setFont(new Font("BOLD",Font.BOLD,20));
	  	add(lblid);
	  	image.add(lblid);

	  	lbleid=new JLabel();
	  	lbleid.setBounds(450,30,250,30);
	  	lbleid.setForeground(Color.RED);
	  	lbleid.setFont(new Font("BOLD",Font.BOLD,20));
	  	add(lbleid);
	  	image.add(lbleid);

		btn1=new JButton("VOTE->>");
		btn1.setBounds(1350,300,115,30);
		btn1.setBackground(new Color(173,216,230));
		btn1.setFont(new Font("BOLD",Font.BOLD,16));
		add(btn1);
		image.add(btn1);

		btn2=new JButton("VOTE->>");
		btn2.setBounds(1350,410,115,30);
		btn2.setBackground(new Color(173,216,230));
		btn2.setFont(new Font("BOLD",Font.BOLD,16));
		add(btn2);
		image.add(btn2);

		btn3=new JButton("VOTE->>");
		btn3.setBounds(1350,520,115,30);
		btn3.setBackground(new Color(173,216,230));
		btn3.setFont(new Font("BOLD",Font.BOLD,16));
		add(btn3);
		image.add(btn3);

		btn4=new JButton("VOTE->>");
		btn4.setBounds(1350,630,115,30);
		btn4.setBackground(new Color(173,216,230));
		btn4.setFont(new Font("BOLD",Font.BOLD,16));
		add(btn4);
		image.add(btn4);

		btn5=new JButton("VOTE->>");
		btn5.setBounds(1350,740,115,30);
		btn5.setBackground(new Color(173,216,230));
		btn5.setFont(new Font("BOLD",Font.BOLD,16));
		add(btn5);
		image.add(btn5);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		
	  	setVisible(true);
	  	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Vote();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==back)
		{
			this.dispose();
		}
		if(ae.getSource()==btn1)
		{
			if(ustatus.getText().equals("Inactive"))
			{
				JOptionPane.showMessageDialog(this,"You Have Already Voted");
			}
			else {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql1="insert into vote values(?,?,?,?,?,?)";
				st=con.prepareStatement(sql1);
				String a1=uid.getText();
				String b1=lbleid.getText();
				String c1=uwardno.getText();
				String d1=lblcand1.getText();
				String e1=lblpnm1.getText();
				
				//insert * into user value(?)
				st.setString(1,a1);
				st.setString(2,b1);
				st.setString(3,c1);
				st.setString(4,d1);
				st.setString(5,e1);
				st.setInt(6,1);
				int g=st.executeUpdate();
				con.close();
				
				JOptionPane.showMessageDialog(this, "You Have Voted");
				mail(uemail.getText(),uname.getText(),uwardno.getText(),lblcand1.getText(),lblpnm1.getText());
				}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="update userpoll set status=? where userid=?";
				
						st=con.prepareStatement(sql);
						st.setInt(1,0);
						st.setString(2,uid.getText());
						int p=st.executeUpdate();
						con.close();
						}				
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
			this.dispose();
		}
		}
		if(ae.getSource()==btn2)
		{
			if(ustatus.getText().equals("Inactive"))
			{
				JOptionPane.showMessageDialog(this,"You Have Already Voted");
			}
			else {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql2="insert into vote values(?,?,?,?,?,?)";
				st=con.prepareStatement(sql2);
				String a2=uid.getText();
				String b2=lbleid.getText();
				String c2=uwardno.getText();
				String d2=lblcand2.getText();
				String e2=lblpnm2.getText();
				
				//insert * into user value(?)
				st.setString(1,a2);
				st.setString(2,b2);
				st.setString(3,c2);
				st.setString(4,d2);
				st.setString(5,e2);
				st.setInt(6,2);
				int g=st.executeUpdate();
				con.close();
				
				JOptionPane.showMessageDialog(this, "You Have Voted");
				mail(uemail.getText(),uname.getText(),uwardno.getText(),lblcand2.getText(),lblpnm2.getText());
				}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="update userpoll set status=? where userid=?";
				
						st=con.prepareStatement(sql);
						st.setInt(1,0);
						st.setString(2,uid.getText());
						int p=st.executeUpdate();
						con.close();
						}				
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
			this.dispose();
		}
		}
		if(ae.getSource()==btn3)
		{
			if(ustatus.getText().equals("Inactive"))
			{
				JOptionPane.showMessageDialog(this,"You Have Already Voted");
			}
			else {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql3="insert into vote values(?,?,?,?,?,?)";
				st=con.prepareStatement(sql3);
				String a3=uid.getText();
				String b3=lbleid.getText();
				String c3=uwardno.getText();
				String d3=lblcand3.getText();
				String e3=lblpnm3.getText();
				
				//insert * into user value(?)
				st.setString(1,a3);
				st.setString(2,b3);
				st.setString(3,c3);
				st.setString(4,d3);
				st.setString(5,e3);
				st.setInt(6,3);
				int g=st.executeUpdate();
				con.close();
				
				JOptionPane.showMessageDialog(this, "You Have Voted");
				mail(uemail.getText(),uname.getText(),uwardno.getText(),lblcand3.getText(),lblpnm3.getText());
				}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="update userpoll set status=? where userid=?";
				
						st=con.prepareStatement(sql);
						st.setInt(1,0);
						st.setString(2,uid.getText());
						int p=st.executeUpdate();
						con.close();
						}				
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
			this.dispose();
		}
		}
		if(ae.getSource()==btn4)
		{
			if(ustatus.getText().equals("Inactive"))
			{
				JOptionPane.showMessageDialog(this,"You Have Already Voted");
			}
			else {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql4="insert into vote values(?,?,?,?,?,?)";
				st=con.prepareStatement(sql4);
				String a4=uid.getText();
				String b4=lbleid.getText();
				String c4=uwardno.getText();
				String d4=lblcand4.getText();
				String e4=lblpnm4.getText();
				
				//insert * into user value(?)
				st.setString(1,a4);
				st.setString(2,b4);
				st.setString(3,c4);
				st.setString(4,d4);
				st.setString(5,e4);
				st.setInt(6,4);
				int g=st.executeUpdate();
				con.close();
				
				JOptionPane.showMessageDialog(this, "You Have Voted");
				mail(uemail.getText(),uname.getText(),uwardno.getText(),lblcand4.getText(),lblpnm4.getText());
				}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="update userpoll set status=? where userid=?";
				
						st=con.prepareStatement(sql);
						st.setInt(1,0);
						st.setString(2,uid.getText());
						int p=st.executeUpdate();
						con.close();
						}				
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
			this.dispose();
		}
		}
		if(ae.getSource()==btn5)
		{
			if(ustatus.getText().equals("Inactive"))
			{
				JOptionPane.showMessageDialog(this,"You Have Already Voted");
			}
			else {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql5="insert into vote values(?,?,?,?,?,?)";
				st=con.prepareStatement(sql5);
				String a5=uid.getText();
				String b5=lbleid.getText();
				String c5=uwardno.getText();
				String d5=lblcand5.getText();
				String e5=lblpnm5.getText();
				
				//insert * into user value(?)
				st.setString(1,a5);
				st.setString(2,b5);
				st.setString(3,c5);
				st.setString(4,d5);
				st.setString(5,e5);
				st.setInt(6,5);
				int g=st.executeUpdate();
				con.close();
				
				JOptionPane.showMessageDialog(this, "You Have Voted");
				mail(uemail.getText(),uname.getText(),uwardno.getText(),lblcand5.getText(),lblpnm5.getText());
				}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="update userpoll set status=? where userid=?";
				
						st=con.prepareStatement(sql);
						st.setInt(1,0);
						st.setString(2,uid.getText());
						int p=st.executeUpdate();
						con.close();
						}				
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
			this.dispose();
		}
		}

	}
	void candnm() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql1="select NAME from CANDIDATE where WARDNO=?";
			String wno=uwardno.getText();
			st=con.prepareStatement(sql1);
			st.setString(1,wno);
			rs=st.executeQuery();
			if(rs.next())
			{
				lblcand1.setText(rs.getString(1));
							}
			if(rs.next())
			{
				lblcand2.setText(rs.getString(1));
			}
			if(rs.next())
			{
				lblcand3.setText(rs.getString(1));
			}
			if(rs.next())
			{
				lblcand4.setText(rs.getString(1));
			}
			if(rs.next())
			{
				lblcand5.setText(rs.getString(1));
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void pnm1() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select PARTYNAME from CANDIDATE where NAME=?";
			String nm=lblcand1.getText();
			st=con.prepareStatement(sql);
			st.setString(1,nm);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblpnm1.setText(rs.getString(1));
							}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void pnm2() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select PARTYNAME from CANDIDATE where NAME=?";
			String nm=lblcand2.getText();
			st=con.prepareStatement(sql);
			st.setString(1,nm);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblpnm2.setText(rs.getString(1));
							}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void pnm3() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select PARTYNAME from CANDIDATE where NAME=?";
			String nm=lblcand3.getText();
			st=con.prepareStatement(sql);
			st.setString(1,nm);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblpnm3.setText(rs.getString(1));
							}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void pnm4() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select PARTYNAME from CANDIDATE where NAME=?";
			String nm=lblcand4.getText();
			st=con.prepareStatement(sql);
			st.setString(1,nm);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblpnm4.setText(rs.getString(1));
							}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void pnm5() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select PARTYNAME from CANDIDATE where NAME=?";
			String nm=lblcand5.getText();
			st=con.prepareStatement(sql);
			st.setString(1,nm);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblpnm5.setText(rs.getString(1));
							}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void eid() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select ELECTIONID from ELECTIONSCHEDULE where WARDNO=?";
			String wn=uwardno.getText();
			st=con.prepareStatement(sql);
			st.setString(1,wn);
			rs=st.executeQuery();
			if (rs.next())
			{
				lbleid.setText(rs.getString(1));
							}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	void mail(String to,String name,String wno,String cnm,String pnm)
	{
		  String host="mail.gmail.com";  
		  final String user="nikhilsoni492@gmail.com";//change accordingly  
		  final String password="jvojndvdoeazunte";//change accordingly  
		    
		   //Get the session object  
		   Properties p = new Properties();  
		   //props.put("mail.smtp.host",host);  
		   //props.put("mail.smtp.auth", "true");  
		   p.put("mail.smtp.auth", "true");
		   p.put("mail.transport.protocol", "smtp");
		   p.put("mail.smtp.host", "smtp.gmail.com");
		   p.put("mail.smtp.port", "587");
		   p.put("mail.smtp.starttls.enable","true");
		   p.put("mail.smtp.starttls.required","true");   
		   Session session = Session.getDefaultInstance(p,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		   
		     MimeMessage message = new MimeMessage(session);  
		     try {
				message.setFrom(new InternetAddress(user));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.setSubject("Online Polling");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.setText("Hi "+name+" ,\n"+"Ward-No:"+wno+",\n Candidate-Name: "+cnm+",\n Party-Name: "+pnm+",\n\n Thanks For Voting");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     		       
		    //send the message  
		     try {
				Transport.send(message);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  		    
	}

		
	}


