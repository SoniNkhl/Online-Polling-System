package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class ElectionScheduleInsert extends JFrame implements ActionListener,KeyListener,ItemListener{

			JLabel lblelecid,lblcdid,lblwardno,lbldoe,lblwardnm,lblcandnm;
			JTextField txtelecid,txtdoe;
			JButton btnsave,btnclose,btnclear,btnnew;
			Connection con;
			PreparedStatement st;
			ResultSet rs;
			JProgressBar jp;
			int val=0;
			JComboBox combocandid,combowardno;
			
			ElectionScheduleInsert(){
				setSize(1400,800);
				setTitle("ELECTION SCHEDULE INSERT");
				setLayout(null);
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
			    
			    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-insert.png"));
			    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
			    ImageIcon i3 = new ImageIcon(i2);
			    JLabel image1 = new JLabel("Election Schedule Insert",i3,SwingConstants.CENTER);
			    image1.setVerticalTextPosition(JLabel.CENTER);
			    image1.setHorizontalTextPosition(JLabel.RIGHT);
			    image1.setForeground(new Color(0,0,128));
			    image1.setFont(new Font("Arial",Font.BOLD,30));
			    image1.setBounds(380,0,600, 150);
			    add(image1);
			    image.add(image1);
		
			    lblelecid=new JLabel("ELECTION ID:");
			    lblelecid.setBounds(450,150,150,30);
			    lblelecid.setFont(new Font("Arial",Font.BOLD,16));
				add(lblelecid);
				image.add(lblelecid);
				txtelecid=new JTextField();
				txtelecid.setText(getID());
				txtelecid.setFont(new Font("Arial",Font.BOLD,16));
				txtelecid.setEditable(false);
				txtelecid.addKeyListener(this);
				txtelecid.setBounds(600,150,250,30);
				add(txtelecid);
	
				lblcdid=new JLabel("CANDIDATE-ID:");
				lblcdid.setBounds(450,200,150,30);
				lblcdid.setFont(new Font("Arial",Font.BOLD,16));
				add(lblcdid);
				image.add(lblcdid);
				combocandid=new JComboBox();
				combocandid.setBounds(600,200,250,30);
				combocandid.insertItemAt("", 0);
				add(combocandid);
				image.add(combocandid);
				combocandid.addItemListener(this);
				combocandid.setFont(new Font("Arial",Font.BOLD,16));
				fillcandid();
				
				lblcandnm=new JLabel();
				lblcandnm.setBounds(870,200,150,30);
				lblcandnm.setFont(new Font("Arial",Font.BOLD,16));
				add(lblcandnm);
				image.add(lblcandnm);

				lblwardno=new JLabel("WARD NO. ");
				lblwardno.setBounds(450,250,150,30);
				lblwardno.setFont(new Font("Arial",Font.BOLD,16));
				add(lblwardno);
				image.add(lblwardno);
				combowardno=new JComboBox();
				combowardno.setBounds(600,250,250,30);
				combowardno.insertItemAt("", 0);
				add(combowardno);
				image.add(combowardno);
				combowardno.addItemListener(this);
				combowardno.setFont(new Font("Arial",Font.BOLD,16));
				fillwardno();
				
				lblwardnm=new JLabel();
				lblwardnm.setBounds(870,250,150,30);
				lblwardnm.setFont(new Font("Arial",Font.BOLD,16));
				add(lblwardnm);
				image.add(lblwardnm);
				
				lbldoe=new JLabel("Date Of Election:");
				lbldoe.setBounds(450,300,150,30);
				lbldoe.setFont(new Font("Arial",Font.BOLD,16));
				add(lbldoe);
				image.add(lbldoe);
				txtdoe=new JTextField();
				txtdoe.setBounds(600,300,250,30);
				txtdoe.addKeyListener(this);
				add(txtdoe);
				txtdoe.setFont(new Font("Arial",Font.BOLD,16));
				image.add(txtdoe);
		
				jp=new JProgressBar();
				jp.setMinimum(0);
				jp.setMaximum(100);
				jp.setValue(val);
				jp.setBounds(310,350,800,20);
				add(jp);
				image.add(jp);
				jp.setStringPainted(true);
						
				btnsave=new JButton("SAVE");
				btnsave.setBounds(450,400,150,30);
				btnsave.setBackground(new Color(173,216,230));
				btnsave.setFont(new Font("BOLD",Font.BOLD,16));
				add(btnsave);
				image.add(btnsave);
				
				btnclose=new JButton("CLOSE");
				btnclose.setBounds(620,400,150,30);
				btnclose.setBackground(new Color(173,216,230));
				btnclose.setFont(new Font("BOLD",Font.BOLD,16));
				add(btnclose);
				image.add(btnclose);
				
				btnclear=new JButton("CLEAR");
				btnclear.setBounds(790,400,150,30);
				btnclear.setBackground(new Color(173,216,230));
				btnclear.setFont(new Font("BOLD",Font.BOLD,16));
				add(btnclear);
				image.add(btnclear);
					
				btnnew=new JButton("NEW ID");
				btnnew.setBounds(870,150,100,30);
				btnnew.setBackground(new Color(173,216,230));
				btnnew.setFont(new Font("BOLD",Font.BOLD,16));
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
				new ElectionScheduleInsert ();
			
			}
			void cand() {
				try {
					//Step-1 Load the driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql3="select name from candidate where candidateid=?";
					String did=combocandid.getSelectedItem().toString();
					st=con.prepareStatement(sql3);
					st.setString(1, did);
					rs=st.executeQuery();
					if (rs.next())
					{
						lblcandnm.setText(rs.getString(1));

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

			void wardnm() {
				try {
					//Step-1 Load the driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql3="select WARDNAME from WARDS where WARDNO=?";
					String did=combowardno.getSelectedItem().toString();
					st=con.prepareStatement(sql3);
					st.setString(1, did);
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

			@Override
			public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
				//txtelecid,txtcandid,txtwardno,txtdoe
				if (ae.getSource()==btnsave)
						{
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
						String sql="insert into electionschedule values(?,?,?,?,?)";
						st=con.prepareStatement(sql);
						String a=txtelecid.getText();
						String b=combocandid.getSelectedItem().toString();
						String c=combowardno.getSelectedItem().toString();
						String d=txtdoe.getText();						
						//insert * into user value(?)
						st.setString(1,a);
						st.setString(2, b);
						st.setString(3, c);
						st.setString(4,d);
						st.setInt(5,1);
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
					txtelecid.setText(r);
				}	
				if(ae.getSource()==btnclose)
				{
					this.dispose();//for close the window
				}
				if(ae.getSource()==btnclear)
				{
					txtelecid.setText("");
					txtdoe.setText("");
					
				}
			}
			String getID()
			{
				String aid;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					String sql="select ELECTIONID from ELECTIONSCHEDULE";
					Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st.executeQuery(sql);
					if (rs.next())
					{
						rs.last();
						String g=rs.getString(1);
						String w=g.substring(2,g.length());
						int x=Integer.parseInt(w);
						if (x<10)
							aid="E"+"000"+(x+1);
						else if(x>=10 && x<99)
							aid="E"+"00"+(x+1);
						else if(x>=100 && x<999)
							aid="E"+"0"+(x+1);
						else
							aid="E"+(x+1);
					}
					else {
						JOptionPane.showMessageDialog(this, "Record Not Found");
						aid="E0001";
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
				//txtelecid,txtcandid,txtwardno,txtdoe
				if(ar.getSource()==txtdoe && txtdoe.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtdoe.requestFocus();
						val=val+100;
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
			void fillcandid()
			{
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql="select candidateid from candidate";
					st=con.prepareStatement(sql);
					rs=st.executeQuery();
					while(rs.next())
					{
						combocandid.addItem(rs.getString(1));
					}
				}
				catch (Exception ex) {
					
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


			@Override
			public void itemStateChanged(ItemEvent ae) {
				// TODO Auto-generated method stub
				if(ae.getSource()==combowardno)
				{
					wardnm();
				}
				if(ae.getSource()==combocandid)
				{
					cand();
				}

			}

			}
