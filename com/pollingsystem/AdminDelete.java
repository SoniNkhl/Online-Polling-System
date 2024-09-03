package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class AdminDelete extends JFrame implements ActionListener,ItemListener{

			JLabel lbladminid,lbladminnm,lbladminem,lbladminph,lbladmingen,lbladminad;
			JTextField txtadminnm,txtadminem,txtadminph,txtadmingen,txtadminad;
			JButton btndel,btnclear,btnclose;
			Connection bc,con;
			PreparedStatement st,cd;
		   JComboBox cb;
			ResultSet rs;
			
			AdminDelete()
			{
				setSize(1400,800);
				setLayout(null);
				setTitle("Admin Delete");
				setLocationRelativeTo(null); 
				setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Admin Delete",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(500,0, 300, 150);
	    add(image1);
	    image.add(image1);
	    
	    lbladminid=new JLabel("ADMIN ID:");
	    lbladminid.setBounds(450,150,150,30);
	    lbladminid.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminid);
		image.add(lbladminid);
		cb=new JComboBox();
		cb.setBounds(600,150,250,30);
		cb.insertItemAt("", 0);
		add(cb);
		image.add(cb);
		cb.setFont(new Font("Arial",Font.BOLD,16));
		cb.addItemListener(this);
		filldata();
		
		lbladminnm=new JLabel("ADMIN NAME:");
		lbladminnm.setBounds(450,200,150,30);
		lbladminnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminnm);
		image.add(lbladminnm);
		txtadminnm=new JTextField();
		txtadminnm.setBounds(600,200,250,30);
		add(txtadminnm);
		txtadminnm.setFont(new Font("Arial",Font.BOLD,16));
		txtadminnm.setEditable(false);
		image.add(txtadminnm);
		
		lbladminem=new JLabel("EMAIL: ");
		lbladminem.setBounds(450,250,150,30);
		lbladminem.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminem);
		image.add(lbladminem);
		txtadminem=new JTextField();
		txtadminem.setBounds(600,250,250,30);
		add(txtadminem);
		txtadminem.setFont(new Font("Arial",Font.BOLD,16));
		txtadminem.setEditable(false);
		image.add(txtadminem);
		
		lbladminph=new JLabel("PHONE No.:");
		lbladminph.setBounds(450,300,150,30);
		lbladminph.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminph);
		image.add(lbladminph);
		txtadminph=new JTextField();
		txtadminph.setBounds(600,300,250,30);
		add(txtadminph);
		txtadminph.setFont(new Font("Arial",Font.BOLD,16));
		txtadminph.setEditable(false);
		image.add(txtadminph);
		
		lbladmingen=new JLabel("GENDER:");
		lbladmingen.setBounds(450,350,100,30);
		lbladmingen.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladmingen);
		image.add(lbladmingen);
		txtadmingen=new JTextField();
		txtadmingen.setBounds(600,350,250,30);
		add(txtadmingen);
		txtadmingen.setFont(new Font("Arial",Font.BOLD,16));
		txtadmingen.setEditable(false);
		image.add(txtadmingen);
		
		lbladminad=new JLabel("ADDRESS:");
		lbladminad.setBounds(450,400,150,30);
		lbladminad.setFont(new Font("Arial",Font.BOLD,16));
		add(lbladminad);
		image.add(lbladminad);
		txtadminad=new JTextField();
		txtadminad.setBounds(600,400,250,30);
		add(txtadminad);
		txtadminad.setFont(new Font("Arial",Font.BOLD,16));
		txtadminad.setEditable(false);
		image.add(txtadminad);
		
		btndel=new JButton("DELETE");
		btndel.setBounds(400,500,150,33);
		btndel.setBackground(new Color(173,216,230));
		btndel.setFont(new Font("BOLD",Font.BOLD,16));
		add(btndel);
		image.add(btndel);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(600,500,150,35);
		btnclear.setBackground(new Color(173,216,230));
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclear);
		image.add(btnclear);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(800,500,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		
		btndel.addActionListener(this);
		btnclear.addActionListener(this);
		btnclose.addActionListener(this);
			
    	setVisible(true);
   	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 }

			
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				new AdminDelete();
				}
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				//txtadminnm,txtadminem,txtadminph,txtadmingen,txtadminad,txtadminpass
				if(ae.getSource()==(btndel))
				{
					if (txtadminnm.getText().length()==0 ||txtadminem.getText().length()==0 ||txtadminph.getText().length()==0 ||txtadmingen.getText().length()==0||txtadminad.getText().length()==0)
					{
						JOptionPane.showMessageDialog(this,"Check all Data first");
					}
					else
					{
						int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
						if(opt==0)
					try {
						//Step-1 Load the driver
						Class.forName("oracle.jdbc.driver.OracleDriver");
						//Step-2 Connection create
						Connection bc = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
						//Step-3 Statement create
						String sql="delete from Admin where Adminid=?";
						String aid=cb.getSelectedItem().toString();
						 PreparedStatement cd=bc.prepareStatement(sql);
						cd.setString(1, aid);
						int ws=cd.executeUpdate();
						JOptionPane.showMessageDialog(this, "Record Delete");
						bc.close();
						
						
					}
					catch(Exception ex) {
						System.out.println(ex.toString());
					}
					
				}
				}				
		
			if(ae.getSource()==btnclear)
			{
				
				txtadminnm.setText("");
				txtadminem.setText("");
				txtadminph.setText("");
				txtadmingen.setText("");
				txtadminad.setText("");

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
			String sql="select Adminid from Admin";
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
				String sql="select Name,Email,phone,gender,Address,password from Admin where Adminid=?";
				String aid=cb.getSelectedItem().toString();
				st=con.prepareStatement(sql);
				st.setString(1, aid);
				rs=st.executeQuery();
				if (rs.next())
				{
					txtadminnm.setText(rs.getString(1));
					txtadminem.setText(rs.getString(2));
					txtadminph.setText(rs.getString(3));	
					txtadmingen.setText(rs.getString(4));
					txtadminad.setText(rs.getString(5));
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

















