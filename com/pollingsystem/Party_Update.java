package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class Party_Update extends JFrame implements ActionListener,ItemListener{
	JLabel lblpnm,lblpdesc,lblps,lblsdesc,lblpem;
	JTextField txtps,txtpem;
	JTextArea txtpdesc,txtsdesc;
	JComboBox cb;
	JButton btnupdate,btnclear,btnclose;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	public Party_Update()
	{
		setSize(1400,800);
		setTitle("PARTY UPDATE");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-update.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Party Update",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(500,0, 400, 150);
	    image1.setForeground(new Color(0,0,128));
	    add(image1);
	    image.add(image1);
	    
	    lblpnm=new JLabel("PARTY NAME");
	    lblpnm.setBounds(400,150,150,30);
	    lblpnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblpnm);
		image.add(lblpnm);
		cb=new JComboBox();
		cb.setBounds(600,150,250,30);
		cb.insertItemAt("", 0);
		add(cb);
		image.add(cb);
		cb.addItemListener(this);
		filldata();
		
		lblpdesc=new JLabel("NAME DESCRIPTION");
		lblpdesc.setBounds(400,200,200,30);
		lblpdesc.setFont(new Font("Arial",Font.BOLD,16));
		add(lblpdesc);
		image.add(lblpdesc);
		txtpdesc=new JTextArea();
		txtpdesc.setFont(new Font("Arial",Font.BOLD,16));
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
		txtps.setBounds(600,270,250,30);
		add(txtps);
		
		lblsdesc=new JLabel("SYMBOL DESCRIPTION");
		lblsdesc.setBounds(400,320,200,30);
		lblsdesc.setFont(new Font("Arial",Font.BOLD,16));
		add(lblsdesc);
		image.add(lblsdesc);
		txtsdesc=new JTextArea();
		txtsdesc.setFont(new Font("Arial",Font.BOLD,16));
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
		txtpem.setBounds(600,390,250,30);
		add(txtpem);
		image.add(txtpem);

		btnupdate=new JButton("UPDATE");
		btnupdate.setBounds(400,440,150,35);
		btnupdate.setBackground(new Color(173,216,230));
		btnupdate.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnupdate);
		image.add(btnupdate);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(600,440,150,35);
		btnclear.setBackground(new Color(173,216,230));
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclear);
		image.add(btnclear);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(800,440,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		
		btnupdate.addActionListener(this);
		btnclear.addActionListener(this);
		btnclose.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Party_Update();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		//		//txtpdesc,txtps,txtsdesc,txtpem
		if(ae.getSource()==btnupdate)
	{
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="update party set namedescription=?,partysymbol=?,symboldescription=? partyemail=? where partyname=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,txtpdesc.getText());
			st.setString(2,txtps.getText());
			st.setString(3,txtsdesc.getText());
			st.setString(4,txtpem.getText());
			st.setString(5,cb.getSelectedItem().toString());
			int g=st.executeUpdate();
			con.close();
			
			JOptionPane.showMessageDialog(this,"Record Updated");
		}
		
		
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
		if(ae.getSource()==btnclear)
		{
			txtpdesc.setText("");
			txtps.setText("");
			txtsdesc.setText("");
			txtpem.setText("");
		}
	if(ae.getSource()==btnclose)
	{
		this.dispose();		
	}

		
	}
	void filldata()
	{
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select partyname from party";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				cb.addItem(rs.getString(1));
			}
			}
		catch(Exception ex)                                                       
		{
			System.out.println(ex.toString());
		}
		
		
		}

	@Override
	public void itemStateChanged(ItemEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==cb)
		{
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="select namedescription,partysymbol,symboldescription,partyemail from party where partyname=?";
				String aid=cb.getSelectedItem().toString();
				st=con.prepareStatement(sql);
				st.setString(1, aid);
				rs=st.executeQuery();
				if (rs.next())
				{
					txtpdesc.setText(rs.getString(1));
					txtps.setText(rs.getString(2));
					txtsdesc.setText(rs.getString(3));	
					txtpem.setText(rs.getString(4));	
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Data not found");
				}
				con.close();
			}



	catch(Exception ex)                                                       
	{
		System.out.println(ex.toString());
	}

	}

		
	}

}
