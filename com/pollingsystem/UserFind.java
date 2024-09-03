package com.pollingsystem;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class UserFind extends JFrame implements ActionListener,ItemListener{

	JLabel lbluserid,lblwno,lblusernm,lbluserem,lbluserph,lblusergen,lbluserad,lbluserage,lbluserpass;
	JTextField txtwno,txtusernm,txtuserem,txtuserph,txtusergen,txtuserad,txtuserage,txtuserpass;
			String gender;
			JButton btnclear,btnclose;
			Connection con;
			PreparedStatement st;
		    JComboBox cb;
		    ResultSet rs;
		    UserFind(){
		    	setSize(1400,800);
				setTitle("USER FIND");
				setLayout(null);
				setLocationRelativeTo(null); 
				setResizable(false);
				
				ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("polling-background1.png"));
			    Image bg2= bg1.getImage().getScaledInstance(1400,800, Image.SCALE_DEFAULT);
			    ImageIcon bg3 = new ImageIcon(bg2);
			    JLabel image = new JLabel(bg3);
			    image.setBounds(0,0,1400,800);
			    add(image);
			    
			    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("user_logo.png"));
			    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			    ImageIcon logo3 = new ImageIcon(logo2);
			    JLabel image_logo = new JLabel("USER",logo3,SwingConstants.CENTER);
			    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
			    image_logo.setHorizontalTextPosition(JLabel.CENTER);
			    image_logo.setFont(new Font("Arial",Font.BOLD,16));
			    image_logo.setBounds(10,10, 100, 150);
			    add(image_logo);
			    image.add(image_logo);
			    
			    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-find.png"));
			    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
			    ImageIcon i3 = new ImageIcon(i2);
			    JLabel image1 = new JLabel("User Find",i3,SwingConstants.CENTER);
			    image1.setVerticalTextPosition(JLabel.CENTER);
			    image1.setHorizontalTextPosition(JLabel.RIGHT);
			    image1.setForeground(new Color(0,0,128));
			    image1.setFont(new Font("Arial",Font.BOLD,30));
			    image1.setBounds(450,0, 400, 150);
			    add(image1);
			    image.add(image1);
		
			    lbluserid=new JLabel(" USER ID:");
			    lbluserid.setBounds(450,150,150,30);
			    lbluserid.setFont(new Font("Arial",Font.BOLD,16));
				add(lbluserid);
				image.add(lbluserid);
		
				cb=new JComboBox();
				cb.setBounds(600,150,250,30);
				cb.insertItemAt("", 0);
				add(cb);
				image.add(cb);
				filldata();
				cb.setFont(new Font("Arial",Font.BOLD,16));
				cb.addItemListener(this);
				
				lblwno=new JLabel("WARD NO.");
				lblwno.setBounds(450,200,150,30);
				lblwno.setFont(new Font("Arial",Font.BOLD,16));
				add(lblwno);
				image.add(lblwno);
				txtwno=new JTextField();
				txtwno.setBounds(600,200,250,30);
				add(txtwno);
				txtwno.setFont(new Font("Arial",Font.BOLD,16));
				txtwno.setEditable(false);
				image.add(txtwno);
		
				lblusernm=new JLabel("USER NAME:");
				lblusernm.setBounds(450,250,150,30);
				lblusernm.setFont(new Font("Arial",Font.BOLD,16));
				add(lblusernm);
				image.add(lblusernm);
				txtusernm=new JTextField();
				txtusernm.setBounds(600,250,250,30);
				add(txtusernm);
				txtusernm.setFont(new Font("Arial",Font.BOLD,16));
				txtusernm.setEditable(false);
				image.add(txtusernm);
				
				lbluserem=new JLabel("EMAIL: ");
				lbluserem.setBounds(450,300,150,30);
				lbluserem.setFont(new Font("Arial",Font.BOLD,16));
				add(lbluserem);
				image.add(lbluserem);
				txtuserem=new JTextField();
				txtuserem.setBounds(600,300,250,30);
				add(txtuserem);
				txtuserem.setFont(new Font("Arial",Font.BOLD,16));
				txtuserem.setEditable(false);
				image.add(txtuserem);
								
				lbluserph=new JLabel("PHONE No.:");
				lbluserph.setBounds(450,350,150,30);
				lbluserph.setFont(new Font("Arial",Font.BOLD,16));
				add(lbluserph);
				image.add(lbluserph);
				txtuserph=new JTextField();
				txtuserph.setBounds(600,350,250,30);
				add(txtuserph);
				txtuserph.setFont(new Font("Arial",Font.BOLD,16));
				txtuserph.setEditable(false);
				image.add(txtuserph);
				
				lblusergen=new JLabel("GENDER:");
				lblusergen.setBounds(450,400,100,30);
				lblusergen.setFont(new Font("Arial",Font.BOLD,16));
				add(lblusergen);
				image.add(lblusergen);
				txtusergen=new JTextField();
				txtusergen.setBounds(600,400,250,30);
				add(txtusergen);
				txtusergen.setFont(new Font("Arial",Font.BOLD,16));
				txtusergen.setEditable(false);
				image.add(txtusergen);
		
				lbluserad=new JLabel("ADDRESS:");
				lbluserad.setBounds(450,450,150,30);
				lbluserad.setFont(new Font("Arial",Font.BOLD,16));
				add(lbluserad);
				image.add(lbluserad);
				txtuserad=new JTextField();
				txtuserad.setBounds(600,450,250,30);
				add(txtuserad);
				txtuserad.setFont(new Font("Arial",Font.BOLD,16));
				txtuserad.setEditable(false);
				image.add(txtuserad);
				
				lbluserage=new JLabel("AGE:");
				lbluserage.setBounds(450,500,250,30);
				lbluserage.setFont(new Font("Arial",Font.BOLD,16));
				add(lbluserage);
				image.add(lbluserage);
				txtuserage=new JTextField();
				txtuserage.setBounds(600,500,250,30);
				add(txtuserage);
				txtuserage.setFont(new Font("Arial",Font.BOLD,16));
				txtuserage.setEditable(false);
				image.add(txtuserage);

				lbluserpass=new JLabel("PASSWORD:");
				lbluserpass.setBounds(450,550,250,30);
				lbluserpass.setFont(new Font("Arial",Font.BOLD,16));
				add(lbluserpass);
				image.add(lbluserpass);
				txtuserpass=new JTextField();
				txtuserpass.setBounds(600,550,250,30);
				add(txtuserpass);
				txtuserpass.setFont(new Font("Arial",Font.BOLD,16));
				txtuserpass.setEditable(false);
				image.add(txtuserpass);

				btnclear=new JButton("CLEAR");
				btnclear.setBounds(520,600,100,35);
				btnclear.setBackground(new Color(173,216,230));
				btnclear.setFont(new Font("BOLD",Font.BOLD,16));
				add(btnclear);
				image.add(btnclear);
				btnclear.addActionListener(this);
				
				btnclose=new JButton("CLOSE");
				btnclose.setBounds(730,600,100,35);
				btnclose.setBackground(new Color(173,216,230));
				btnclose.setFont(new Font("BOLD",Font.BOLD,16));
				add(btnclose);
				image.add(btnclose);
				btnclose.addActionListener(this);
	
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setVisible(true);
	}

			
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				 new  UserFind();
			
			}


			void filldata()
		{
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="select userid from userpoll";
				st=con.prepareStatement(sql);
				rs=st.executeQuery();
				while (rs.next())
				{
					cb.addItem(rs.getString(1));
				}
				}
			catch(Exception ex)                                                       
			{
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
						String sql="select wardno,Name,Email,phone,gender,Address,password,age from Userpoll where Userid=?";
						String aid=cb.getSelectedItem().toString();
						st=con.prepareStatement(sql);
						st.setString(1, aid);
						rs=st.executeQuery();
						if (rs.next())
						{
							txtwno.setText(rs.getString(1));
							txtusernm.setText(rs.getString(2));
							txtuserem.setText(rs.getString(3));
							txtuserph.setText(rs.getString(4));	
							txtusergen.setText(rs.getString(5));
							txtuserad.setText(rs.getString(6));
							txtuserpass.setText(rs.getString(7));
							txtuserage.setText(rs.getString(8));
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


			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(ae.getSource()==btnclose)
				{
					this.dispose();//for close the window
				}
				if(ae.getSource()==btnclear)
				{
					txtwno.setText("");
					txtusernm.setText("");
					txtuserem.setText("");
					txtuserph.setText("");
					txtusergen.setText("");
					txtuserad.setText("");
					txtuserage.setText("");
				}

				
			}}