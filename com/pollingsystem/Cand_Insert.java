package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cand_Insert extends JFrame implements ActionListener,KeyListener,ItemListener{
	JLabel lblcandid,lblcandward,lblcandnm,lblcandem,lblcandph,lblcandage,lblcandgen,validate_msg,lblwardnm,lblpnm,lblcpnm;
	JTextField txtcandid,txtcandnm,txtcandem,txtcandph,txtcandage;
	JButton btnsave,btnclose,btnclear,btnnew;
	JRadioButton jm,jf;
	JComboBox combowardno,candpnm;
	ButtonGroup bg;
	String gender;
	JProgressBar jp;
	int val=0;
	Connection con;
	PreparedStatement st;
	ResultSet rs;

	Cand_Insert(){
		setSize(1400,800);
		setTitle("CANDIDATE INSERT");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
	    Image bg2= bg1.getImage().getScaledInstance(1400,800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0,1400,800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("employee.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("CANDIDATE",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Candidate Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(500,0, 400, 150);
	    image1.setForeground(new Color(0,0,128));
	    add(image1);
	    image.add(image1);

	    lblcandid=new JLabel("CANDIDATE ID:");
	    lblcandid.setBounds(450,150,150,30);
	    lblcandid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandid);
		image.add(lblcandid);
		txtcandid=new JTextField();
		txtcandid.setText(getID());
		txtcandid.setFont(new Font("Arial",Font.BOLD,16));
		txtcandid.setEditable(false);
		txtcandid.addKeyListener(this);
		txtcandid.setBounds(600,150,250,30);
		add(txtcandid);
		
		lblcandward=new JLabel("WARD NO.:");
		lblcandward.setBounds(450,200,150,30);
		lblcandward.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandward);
		image.add(lblcandward);
		combowardno=new JComboBox();
		combowardno.setBounds(600,200,250,30);
		combowardno.insertItemAt("", 0);
		add(combowardno);
		image.add(combowardno);
		combowardno.setFont(new Font("Arial",Font.BOLD,16));
		combowardno.addItemListener(this);
		fillwardno();
		
		lblwardnm=new JLabel();
		lblwardnm.setBounds(870,200,150,30);
		lblwardnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblwardnm);
		image.add(lblwardnm);

		lblcpnm=new JLabel("PARTY NAME");
		lblcpnm.setBounds(450,250,150,30);
		lblcpnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcpnm);
		image.add(lblcpnm);
		candpnm=new JComboBox();
		candpnm.setBounds(600,250,250,30);
		candpnm.insertItemAt("", 0);
		add(candpnm);
		image.add(candpnm);
		candpnm.addItemListener(this);
		candpnm.setFont(new Font("Arial",Font.BOLD,16));
		fillpartyname();
		
		lblpnm=new JLabel();
		lblpnm.setBounds(870,250,150,30);
		lblpnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblpnm);
		image.add(lblpnm);

		lblcandnm=new JLabel("NAME: ");
		lblcandnm.setBounds(450,300,150,30);
		lblcandnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandnm);
		image.add(lblcandnm);
		txtcandnm=new JTextField();
		txtcandnm.setBounds(600,300,250,30);
		txtcandnm.addKeyListener(this);
		add(txtcandnm);
		txtcandnm.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtcandnm);
				
		lblcandem=new JLabel("EMAIL:");
		lblcandem.setBounds(450,350,150,30);
		lblcandem.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandem);
		image.add(lblcandem);
		txtcandem=new JTextField();
		txtcandem.setBounds(600,350,250,30);
		txtcandem.addKeyListener(this);
		add(txtcandem);
		txtcandem.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtcandem);
		
		validate_msg=new JLabel();
		validate_msg.setBounds(880,350,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		lblcandph=new JLabel("PHONE NO.:");
		lblcandph.setBounds(450,400,150,30);
		lblcandph.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandph);
		image.add(lblcandph);
		txtcandph=new JTextField();
		txtcandph.setBounds(600,400,250,30);
		txtcandph.addKeyListener(this);
		add(txtcandph);
		txtcandph.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtcandph);
		
		lblcandage=new JLabel("AGE:");
		lblcandage.setBounds(450,450,150,30);
		lblcandage.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandage);
		image.add(lblcandage);
		txtcandage=new JTextField();
		txtcandage.setBounds(600,450,250,30);
		txtcandage.addKeyListener(this);
		add(txtcandage);
		txtcandage.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtcandage);
				
		lblcandgen=new JLabel("GENDER:");
		lblcandgen.setBounds(450,500,100,30);
		lblcandgen.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcandgen);
		image.add(lblcandgen);
		jm=new JRadioButton("Male");
		jm.setBounds(600,500,100,30);
		jm.setFont(new Font("Arial",Font.BOLD,16));
		jm.setContentAreaFilled(false);
		jf=new JRadioButton("Female");
		jf.setBounds(700,500,100,30);
		jf.setFont(new Font("Arial",Font.BOLD,16));
		jf.setContentAreaFilled(false);
		bg=new ButtonGroup();
		bg.add(jm);
		bg.add(jf);
		add(jm);
		add(jf);
		image.add(jm);
		image.add(jf);
				
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,550,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
				
		jm.addActionListener(this);
		jf.addActionListener(this);
		
		btnsave=new JButton("SAVE");
		btnsave.setBounds(450,600,150,35);
		btnsave.setBackground(new Color(173,216,230));
		btnsave.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnsave);
		image.add(btnsave);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(620,600,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(790,600,150,35);
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		image.add(btnclear);
			
		btnnew=new JButton("NEW ID");
		btnnew.setBounds(870,150,100,30);
		btnnew.setBackground(new Color(173,216,230));
		add(btnnew);
		btnnew.setFont(new Font("BOLD",Font.BOLD,16));
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
             new Cand_Insert();
	}
	void wardnm() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql3="select WARDNAME from WARDS where WARDNO=?";
			String cwn=combowardno.getSelectedItem().toString();
			st=con.prepareStatement(sql3);
			st.setString(1,cwn);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblwardnm.setText(rs.getString(1));
							}
			else
			{
				JOptionPane.showMessageDialog(this,"not found");
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	void partynm() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql3="select PARTYSYMBOL from PARTY where PARTYNAME=?";
			String cpn=candpnm.getSelectedItem().toString();
			st=con.prepareStatement(sql3);
			st.setString(1,cpn);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblpnm.setText(rs.getString(1));
							}
			else
			{
				JOptionPane.showMessageDialog(this,"not found");
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		//txtcandid,txtcandward,txtcandnm,txtcandem,txtcandph,txtcandage,txtcandpass
		if (ae.getSource()==btnsave)
		{
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="insert into candidate values(?,?,?,?,?,?,?,?,?)";
				st=con.prepareStatement(sql);
				String a=txtcandid.getText();
				String b=combowardno.getSelectedItem().toString();
				String c=candpnm.getSelectedItem().toString();
				String d=txtcandnm.getText();
				String e=txtcandem.getText();
				String f=txtcandph.getText();
				int g=Integer.parseInt(txtcandage.getText());
				
				st.setString(1, a);
				st.setString(2, b);
				st.setString(3, c);
				st.setString(4, d);
				st.setString(5,e);
				st.setString(6,f);
				st.setInt(7,g);
				st.setString(8,gender);
				st.setInt(9, 1);
				if(g>=18 && g<=99)
				{
				int w=st.executeUpdate();
				con.close();
				JOptionPane.showMessageDialog(this, "Data Saved");
				}
				else {
					JOptionPane.showMessageDialog(this,"Not Eligible!You are Under Age");
				}
			}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
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
		if(ae.getSource()==btnnew)
		{
			String r=getID();
			txtcandid.setText(r);
		}
		if(ae.getSource()==btnclear)
		{
			txtcandid.setText("");
			txtcandnm.setText("");
			txtcandem.setText("");
			txtcandph.setText("");
			txtcandage.setText("");
		}

		
		
	}
	String getID()
	{
		String aid;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select CANDIDATEID from CANDIDATE";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
			{
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(3,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					aid="CND"+"000"+(x+1);
				else if(x>=10 && x<99)
					aid="CND"+"00"+(x+1);
				else if(x>=100 && x<999)
					aid="CND"+"0"+(x+1);
				else
					aid="CND"+(x+1);
			}
			else {
				JOptionPane.showMessageDialog(this, "Record Not Found");
				aid="CND0001";
			}
			return aid;
		}


		catch (Exception ex) {
			return ex.toString();
		}
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
	    //Creating a pattern object
	    Pattern pattern = Pattern.compile(regex);
	    //Creating a Matcher object
	    Matcher matcher = pattern.matcher(txtcandem.getText());
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
	public void keyTyped(KeyEvent ar) {
		// TODO Auto-generated method stub
		//txtcandid,txtcandnm,txtcandem,txtcandph,txtcandage
		if(ar.getSource()==txtcandnm && txtcandnm.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcandem.requestFocus();
				val=val+25;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtcandem && txtcandem.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcandph.requestFocus();
				val=val+25;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtcandph && txtcandph.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcandage.requestFocus();
				val=val+25;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtcandage && txtcandage.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcandage.requestFocus();
				val=val+25;
				jp.setValue(val);

			}
		}

	}
	void fillwardno()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select WARDNO from WARDS";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combowardno.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {
			
		}
	}
	void fillpartyname()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select PARTYNAME from PARTY";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				candpnm.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==combowardno)
		{
			wardnm();
		}
		if(ae.getSource()==candpnm)
		{
			partynm();
		}

	}

	
}


