package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class ElectionScheduleDelete extends JFrame implements ActionListener,ItemListener{

	JLabel lblelecid,lblcandid,lblwardno,lbldoe;
	JTextField txtcandid,txtwardno,txtdoe;
	JButton btndel,btnclear,btnclose;
	Connection con;
	PreparedStatement st;
    JComboBox cb;
    ResultSet rs;
			
		    ElectionScheduleDelete(){
				setSize(1400,800);
				setLayout(null);
				setTitle("Election Schedule Delete");
				setLocationRelativeTo(null); 
				setResizable(false);
				
				ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
			    Image bg2= bg1.getImage().getScaledInstance(1400,800, Image.SCALE_DEFAULT);
			    ImageIcon bg3 = new ImageIcon(bg2);
			    JLabel image = new JLabel(bg3);
			    image.setBounds(0,0,1400,800);
			    add(image);
			    
			    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("clock.png"));
			    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			    ImageIcon logo3 = new ImageIcon(logo2);
			    JLabel image_logo = new JLabel("ELECTION SCHEDULE",logo3,SwingConstants.CENTER);
			    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
			    image_logo.setHorizontalTextPosition(JLabel.CENTER);
			    image_logo.setFont(new Font("Arial",Font.BOLD,16));
			    image_logo.setBounds(10,10,300, 150);
			    add(image_logo);
			    image.add(image_logo);
			    
			    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-delete.png"));
			    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
			    ImageIcon i3 = new ImageIcon(i2);
			    JLabel image1 = new JLabel("Election Scedule Delete",i3,SwingConstants.CENTER);
			    image1.setVerticalTextPosition(JLabel.CENTER);
			    image1.setHorizontalTextPosition(JLabel.RIGHT);
			    image1.setForeground(new Color(0,0,128));
			    image1.setFont(new Font("Arial",Font.BOLD,30));
			    image1.setBounds(400,0,500, 150);
			    add(image1);
			    image.add(image1);
		
			    lblelecid=new JLabel("ELECTION ID:");
			    lblelecid.setBounds(450,150,150,30);
			    lblelecid.setFont(new Font("Arial",Font.BOLD,16));
				add(lblelecid);
				image.add(lblelecid);
				cb=new JComboBox();
				cb.setBounds(600,150,250,30);
				cb.insertItemAt("", 0);
				add(cb);
				image.add(cb);
				cb.addItemListener(this);
				filldata();
		 
				lblcandid=new JLabel("CANDIDATE ID:");
				lblcandid.setBounds(450,200,150,30);
				lblcandid.setFont(new Font("Arial",Font.BOLD,16));
				add(lblcandid);
				image.add(lblcandid);
				txtcandid=new JTextField();
				txtcandid.setBounds(600,200,250,30);
				add(txtcandid);
				txtcandid.setEditable(false);
				image.add(txtcandid);
				
				lblwardno=new JLabel("WARD NO. ");
				lblwardno.setBounds(450,250,150,30);
				lblwardno.setFont(new Font("Arial",Font.BOLD,16));
				add(lblwardno);
				image.add(lblwardno);
				txtwardno=new JTextField();
				txtwardno.setBounds(600,250,250,30);
				add(txtwardno);
				txtwardno.setEditable(false);
				image.add(txtwardno);
				
				lbldoe=new JLabel("Date Of Election:");
				lbldoe.setBounds(450,300,150,30);
				lbldoe.setFont(new Font("Arial",Font.BOLD,16));
				add(lbldoe);
				image.add(lbldoe);
				txtdoe=new JTextField();
				txtdoe.setBounds(600,300,250,30);
				add(txtdoe);
				txtdoe.setEditable(false);
				image.add(txtdoe);
		
				btndel=new JButton("DELETE");
				btndel.setBounds(400,350,150,35);
				btndel.setBackground(new Color(173,216,230));
				btndel.setFont(new Font("BOLD",Font.BOLD,16));
				add(btndel);
				image.add(btndel);
				
				btnclear=new JButton("CLEAR");
				btnclear.setBounds(600,350,150,35);
				btnclear.setBackground(new Color(173,216,230));
				btnclear.setFont(new Font("BOLD",Font.BOLD,16));
				add(btnclear);
				image.add(btnclear);
				
				btnclose=new JButton("CLOSE");
				btnclose.setBounds(800,350,150,35);
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
				new ElectionScheduleDelete();
			
			}
             


			@Override
			public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
				//txtcandid,txtwardno,txtdoe
				if(ae.getSource()==btndel)
				{
					if (txtcandid.getText().length()==0 ||txtwardno.getText().length()==0 ||txtdoe.getText().length()==0)
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
						String sql="delete from ElectionSchedule where Electionid=?";
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
					txtcandid.setText("");
					txtwardno.setText("");
					txtdoe.setText("");
					
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
				String sql="select Electionid from Electionschedule";
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
					String sql="select Candidateid,Wardno,Dateofelection from electionschedule where Electionid=?";
					String aid=cb.getSelectedItem().toString();
					st=con.prepareStatement(sql);
					st.setString(1, aid);
					rs=st.executeQuery();
					if (rs.next())
					{
						txtcandid.setText(rs.getString(1));
						txtwardno.setText(rs.getString(2));
						txtdoe.setText(rs.getString(3));	
						
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

