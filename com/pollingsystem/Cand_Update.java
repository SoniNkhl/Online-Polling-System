package com.pollingsystem;

import java.awt.*;


import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Cand_Update extends JFrame implements ActionListener,ItemListener{
	JLabel lblcandid,lblcandward,lblcandnm,lblcandem,lblcandph,lblcandage,lblcandgen,lblcpnm;
	JTextField txtcandward,txtcandnm,txtcandem,txtcandph,txtcandage,txtcandgen,txtcpnm;
	JComboBox cb;
	JButton btnupdate,btnclear,btnclose;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	Cand_Update(){
		 setSize(1400,800);
			setLayout(null);
			setTitle("Candidate Update");
			setLocationRelativeTo(null); 
			setResizable(false);
			
			ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
		    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
		    ImageIcon bg3 = new ImageIcon(bg2);
		    JLabel image = new JLabel(bg3);
		    image.setBounds(0,0, 1400, 800);
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
		    
		    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-update.png"));
		    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
		    ImageIcon i3 = new ImageIcon(i2);
		    JLabel image1 = new JLabel("Candidate Update",i3,SwingConstants.CENTER);
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
			cb=new JComboBox();
			cb.setBounds(600,150,250,30);
			cb.insertItemAt("", 0);
			add(cb);
			image.add(cb);
			cb.addItemListener(this);
			cb.setFont(new Font("Arial",Font.BOLD,16));
			filldata();
				
			lblcandward=new JLabel("WARD NO.:");
			lblcandward.setBounds(450,200,150,30);
			lblcandward.setFont(new Font("Arial",Font.BOLD,16));
			add(lblcandward);
			image.add(lblcandward);
			txtcandward=new JTextField();
			txtcandward.setBounds(600,200,250,30);
			add(txtcandward);
			txtcandward.setFont(new Font("Arial",Font.BOLD,16));
			image.add(txtcandward);
			
			lblcpnm=new JLabel("PARTY NAME");
			lblcpnm.setBounds(450,250,150,30);
			lblcpnm.setFont(new Font("Arial",Font.BOLD,16));
			add(lblcpnm);
			image.add(lblcpnm);
			txtcpnm=new JTextField();
			txtcpnm.setBounds(600,250,250,30);
			add(txtcpnm);
			txtcpnm.setFont(new Font("Arial",Font.BOLD,16));
			image.add(txtcpnm);

			lblcandnm=new JLabel("NAME: ");
			lblcandnm.setBounds(450,300,150,30);
			lblcandnm.setFont(new Font("Arial",Font.BOLD,16));
			add(lblcandnm);
			image.add(lblcandnm);
			txtcandnm=new JTextField();
			txtcandnm.setBounds(600,300,250,30);
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
			add(txtcandem);
			txtcandem.setFont(new Font("Arial",Font.BOLD,16));
			image.add(txtcandem);
			
			lblcandph=new JLabel("PHONE NO.:");
			lblcandph.setBounds(450,400,150,30);
			lblcandph.setFont(new Font("Arial",Font.BOLD,16));
			add(lblcandph);
			image.add(lblcandph);
			txtcandph=new JTextField();
			txtcandph.setBounds(600,400,250,30);
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
			add(txtcandage);
			txtcandage.setFont(new Font("Arial",Font.BOLD,16));
			image.add(txtcandage);
			
			lblcandgen=new JLabel("GENDER:");
			lblcandgen.setBounds(450,500,100,30);
			lblcandgen.setFont(new Font("Arial",Font.BOLD,16));
			add(lblcandgen);
			image.add(lblcandgen);
			txtcandgen=new JTextField();
			txtcandgen.setBounds(600,500,250,30);
			add(txtcandgen);
			txtcandgen.setFont(new Font("Arial",Font.BOLD,16));
			image.add(txtcandgen);
		 
			btnupdate=new JButton("UPDATE");
			btnupdate.setBounds(400,550,150,35);
			btnupdate.setBackground(new Color(173,216,230));
			btnupdate.setFont(new Font("BOLD",Font.BOLD,16));
			add(btnupdate);
			image.add(btnupdate);
			
			btnclear=new JButton("CLEAR");
			btnclear.setBounds(600,550,150,35);
			btnclear.setBackground(new Color(173,216,230));
			btnclear.setFont(new Font("BOLD",Font.BOLD,16));
			add(btnclear);
			image.add(btnclear);
			
			btnclose=new JButton("CLOSE");
			btnclose.setBounds(800,550,150,35);
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
         new Cand_Update();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		//txtcandward,txtcpnm,txtcandnm,txtcandem,txtcandph,txtcandage,txtcandpass,txtcandgen
			if(ae.getSource()==btnupdate)
		{
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="update candidate set wardno=?,Name=?,Email=?,Phone=?,age=?,Gender=? where candidateid=?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1,txtcandward.getText());
				st.setString(2,txtcpnm.getText());
				st.setString(3,txtcandnm.getText());
				st.setString(4,txtcandem.getText());
				st.setString(5,txtcandph.getText());
				st.setString(6,txtcandage.getText());
				st.setString(7,txtcandgen.getText());
				st.setString(8,cb.getSelectedItem().toString());
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
				txtcandward.setText("");
				txtcpnm.setText("");
				txtcandnm.setText("");
				txtcandem.setText("");
				txtcandph.setText("");
				txtcandage.setText("");
				txtcandgen.setText("");
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
		String sql="select candidateid from candidate";
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
			String sql="select wardno,partyname,name,Email,phone,age,gender from candidate where candidateid=?";
			String aid=cb.getSelectedItem().toString();
			st=con.prepareStatement(sql);
			st.setString(1, aid);
			rs=st.executeQuery();
			if (rs.next())
			{
				txtcandward.setText(rs.getString(1));
				txtcpnm.setText(rs.getString(2));
				txtcandnm.setText(rs.getString(3));
				txtcandem.setText(rs.getString(4));	
				txtcandph.setText(rs.getString(5));
				txtcandage.setText(rs.getString(6));
				txtcandgen.setText(rs.getString(7));
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
