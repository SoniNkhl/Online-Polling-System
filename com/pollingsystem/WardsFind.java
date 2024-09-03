package com.pollingsystem;


import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class WardsFind extends JFrame implements ActionListener,ItemListener{

	JLabel lblwardno,lblwardnm,lbldesc;
	JTextField txtwardnm;
	JTextArea txtdesc;
	JButton btnclear,btnclose;
	Connection con;
	PreparedStatement st;
    JComboBox cb;
    ResultSet rs;
			
	WardsFind(){
		setSize(1400,800);
		setLayout(null);
		setTitle("WARD FIND");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
	    Image bg2= bg1.getImage().getScaledInstance(1400,800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0,1400,800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("ward.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("WARD",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-find.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Ward Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(400,0,500, 150);
	    add(image1);
	    image.add(image1);

	    lblwardno=new JLabel("WARD NO.");
	    lblwardno.setBounds(450,150,150,30);
	    lblwardno.setFont(new Font("Arial",Font.BOLD,16));
		add(lblwardno);
		image.add(lblwardno);
		cb=new JComboBox();
		cb.setBounds(600,150,250,30);
		cb.insertItemAt("", 0);
		add(cb);
		image.add(cb);
		cb.addItemListener(this);
		cb.setFont(new Font("Arial",Font.BOLD,16));
		filldata();
		 
		lblwardnm=new JLabel("WARD NAME");
		lblwardnm.setBounds(450,200,150,30);
		lblwardnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblwardnm);
		image.add(lblwardnm);
		txtwardnm=new JTextField();
		txtwardnm.setFont(new Font("Arial",Font.BOLD,16));
		txtwardnm.setEditable(false);
		txtwardnm.setBounds(600,200,250,30);
		add(txtwardnm);
		 
		lbldesc=new JLabel("DESCRIPTION");
		lbldesc.setBounds(450,250,150,30);
		lbldesc.setFont(new Font("Arial",Font.BOLD,16));
		add(lbldesc);
		image.add(lbldesc);
		txtdesc=new JTextArea();
		txtdesc.setFont(new Font("Arial",Font.BOLD,16));
		txtdesc.setEditable(false);
		txtdesc.setBounds(600,250,250,60);
		add(txtdesc);
		image.add(txtdesc);

		btnclear=new JButton("CLEAR");
		btnclear.setBounds(520,350,100,35);
		btnclear.setBackground(new Color(173,216,230));
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclear);
		image.add(btnclear);
		btnclear.addActionListener(this);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(730,350,100,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		btnclose.addActionListener(this);
		
		setVisible(true);
   	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);			
		}

				
				public static void main(String[] args) {
					// TODO Auto-generated method stub
					WardsFind obj=new WardsFind ();
				
				}


				@Override
				public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub
					if(ae.getSource()==btnclear)
					{
						
						txtwardnm.setText("");
						txtdesc.setText("");


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
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql="select wardno from wards";
					st=con.prepareStatement(sql);
					rs=st.executeQuery();
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
						String sql="select Wardname,Description from wards where Wardno=?";
						String aid=cb.getSelectedItem().toString();
						st=con.prepareStatement(sql);
						st.setString(1, aid);
						rs=st.executeQuery();
						if (rs.next())
						{
							txtwardnm.setText(rs.getString(1));
							txtdesc.setText(rs.getString(2));
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

				
			}}
