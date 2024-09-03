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
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;

public class UserInsert extends JFrame implements ActionListener,KeyListener,ItemListener{
	JLabel lbluserid,lblusernm,lbluserem,lbluserph,lblusergen,lbluserad,lbluserpass,lbluserage,validate_msg,lbluw,lblwardno;
	JLabel lblidtype,lblidno;
	JTextField txtuserid,txtusernm,txtuserem,txtuserph,txtuserad,txtuserage,txtidno;
	JPasswordField txtuserpass;
	JRadioButton jm,jf;
	ButtonGroup bg;
	JButton btnsave,btnclose,btnclear;
	JComboBox cwardno,cidtype;
	String gender,userid;
	JProgressBar jp;
	int val=0;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
				
	UserInsert(){
		setSize(1400,800);
		setTitle("USER INSERT");
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
	       
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("User Insert",i3,SwingConstants.CENTER);
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
		txtuserid=new JTextField();
		txtuserid.setText(getID(userid));
		txtuserid.setFont(new Font("Arial",Font.BOLD,16));
		txtuserid.setEditable(false);
		txtuserid.addKeyListener(this);
		txtuserid.setBounds(600,150,250,30);
		add(txtuserid);
		image.add(txtuserid);
			
		lbluw=new JLabel("WARD NO.:");
		lbluw.setBounds(450,200,150,30);
		lbluw.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluw);
		image.add(lbluw);
		cwardno=new JComboBox();
		cwardno.setBounds(600,200,250,30);
		cwardno.insertItemAt("", 0);
		add(cwardno);
		image.add(cwardno);
		cwardno.addItemListener(this);
		cwardno.setFont(new Font("Arial",Font.BOLD,16));
		fillwardno();
		
		lblwardno=new JLabel();
		lblwardno.setBounds(870,200,150,30);
		lblwardno.setFont(new Font("Arial",Font.BOLD,16));
		add(lblwardno);
		image.add(lblwardno);
		
		lblusernm=new JLabel("USER NAME:");
		lblusernm.setBounds(450,250,150,30);
		lblusernm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblusernm);
		image.add(lblusernm);
		txtusernm=new JTextField();
		txtusernm.setBounds(600,250,250,30);
		txtusernm.addKeyListener(this);
		add(txtusernm);
		txtusernm.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtusernm);
		
		lbluserem=new JLabel("EMAIL: ");
		lbluserem.setBounds(450,300,150,30);
		lbluserem.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserem);
		image.add(lbluserem);
		txtuserem=new JTextField();
		txtuserem.setBounds(600,300,250,30);
		txtuserem.addKeyListener(this);
		add(txtuserem);
		txtuserem.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtuserem);
		
		validate_msg=new JLabel();
		validate_msg.setBounds(880,300,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		lbluserph=new JLabel("PHONE No.:");
		lbluserph.setBounds(450,350,150,30);
		lbluserph.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserph);
		image.add(lbluserph);
		txtuserph=new JTextField();
		txtuserph.setBounds(600,350,250,30);
		txtuserph.addKeyListener(this);
		txtuserph.setFont(new Font("Arial",Font.BOLD,16));
		add(txtuserph);
		image.add(txtuserph);
				
		lblusergen=new JLabel("GENDER:");
		lblusergen.setBounds(450,400,100,30);
		lblusergen.setFont(new Font("Arial",Font.BOLD,16));
		add(lblusergen);
		image.add(lblusergen);
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
		
		lblidtype=new JLabel("ID TYPE");
		lblidtype.setBounds(900,400,150,30);
		lblidtype.setFont(new Font("Arial",Font.BOLD,16));
		add(lblidtype);
		image.add(lblidtype);
		
		cidtype=new JComboBox();
		cidtype.setBounds(1020,400,250,30);
		cidtype.insertItemAt("", 0);
		add(cidtype);
		cidtype.addItem("Aadhar Card");
		cidtype.addItem("Voter Id Card");
		cidtype.addItem("Pan Card");
		cidtype.addItem("Passport");
		cidtype.addItem("Driving Liscence");		
		image.add(cidtype);
		cidtype.setFont(new Font("Arial",Font.BOLD,16));
		
		lblidno=new JLabel("ID NUMBER");
		lblidno.setBounds(900,450,150,30);
		lblidno.setFont(new Font("Arial",Font.BOLD,16));
		add(lblidno);
		image.add(lblidno);
		txtidno=new JTextField();
		txtidno.setBounds(1020,450,250,30);
		txtidno.addKeyListener(this);
		txtidno.setFont(new Font("Arial",Font.BOLD,16));
		add(txtidno);
		image.add(txtidno);
	
		lbluserad=new JLabel("ADDRESS:");
		lbluserad.setBounds(450,450,150,30);
		lbluserad.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserad);
		image.add(lbluserad);
		txtuserad=new JTextField();
		txtuserad.setBounds(600,450,250,30);
		txtuserad.addKeyListener(this);
		txtuserad.setFont(new Font("Arial",Font.BOLD,16));
		add(txtuserad);
		image.add(txtuserad);
		
		lbluserpass=new JLabel("PASSWORD:");
		lbluserpass.setBounds(450,500,250,30);
		lbluserpass.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserpass);
		image.add(lbluserpass);
		txtuserpass=new JPasswordField();
		txtuserpass.setBounds(600,500,250,30);
		txtuserpass.addKeyListener(this);
		add(txtuserpass);
		txtuserpass.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtuserpass);
		
		lbluserage=new JLabel("AGE:");
		lbluserage.setBounds(450,550,250,30);
		lbluserage.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserage);
		image.add(lbluserage);
		txtuserage=new JTextField();
		txtuserage.setBounds(600,550,250,30);
		txtuserage.addKeyListener(this);
		add(txtuserage);
		txtuserage.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtuserage);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,600,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
				
		jm.addActionListener(this);
		jf.addActionListener(this);
		
		btnsave=new JButton("SAVE");
		btnsave.setBounds(450,650,150,35);
		btnsave.setBackground(new Color(173,216,230));
		btnsave.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnsave);
		image.add(btnsave);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(620,650,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(790,650,150,35);
		btnclear.setBackground(new Color(173,216,230));
		btnclear.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclear);
		image.add(btnclear);
			
		btnsave.addActionListener(this);
		btnclose.addActionListener(this);
		btnclear.addActionListener(this);
			
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

			
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				new UserInsert ();
			
			}
			void mail(String name,String id,String pass,String to)
			{
				  String host="mail.gmail.com";  
				  final String user="nikhilsoni492@gmail.com";//change accordingly  
				  final String password="pguawcbiciwlpery";//change accordingly  
				    
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
						message.setText("Hi "+name+" ,\n"+"user id:"+id+",\n passwowd: "+pass+",\n\n Thank you For Registering");
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



			@Override
			public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
				//txtuserid,txtusernm,txtuserem,txtuserph,txtuserad,txtuserpass,txtuserage,txtidno,cwardno,cidty
				if (ae.getSource()==btnsave)
						{
						try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
						String sql="insert into Userpoll values(?,?,?,?,?,?,?,?,?,?,?,?)";
						st=con.prepareStatement(sql);
						String a=txtuserid.getText();
						String b=cwardno.getSelectedItem().toString();
						String c=txtusernm.getText();
						String d=txtuserem.getText();
						String e=txtuserph.getText();
						String f=txtuserad.getText();
						String g=txtuserpass.getText();
						Integer h=Integer.parseInt(txtuserage.getText());
						String p=cidtype.getSelectedItem().toString();
						String q=txtidno.getText();
						
						//insert * into user value(?)
						st.setString(1,a);
						st.setString(2, b);
						st.setString(3, c);
						st.setString(4,d);
						st.setString(5,e);
						st.setString(6,gender);
						st.setString(7, f);
						st.setString(8,g);
						st.setInt(9,h);
						st.setString(10,p);
						st.setString(11,q);
						st.setInt(12, 1);
						if(h>=18 && h<=99) {

						int w=st.executeUpdate();
						con.close();
						JOptionPane.showMessageDialog(this, "Data Saved");
						mail(txtusernm.getText(),txtuserid.getText(),txtuserpass.getText(),txtuserem.getText());
						}
						else {
							JOptionPane.showMessageDialog(this,"You are Under Age...!!");
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
				if(ae.getSource()==btnclear)
				{
					txtuserid.setText("");
					txtusernm.setText("");
					txtuserem.setText("");
					txtuserph.setText("");
					txtuserad.setText("");
					txtuserpass.setText("");
					txtuserage.setText("");
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
					String did=cwardno.getSelectedItem().toString();
					st=con.prepareStatement(sql3);
					st.setString(1, did);
					rs=st.executeQuery();
					if (rs.next())
					{
						lblwardno.setText(rs.getString(1));
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
			public void keyPressed(KeyEvent ar) {
				// TODO Auto-generated method stub
				//txtuserid,txtusernm,txtuserem,txtuserph,txtuserad,txtuserpass,txtuserage
				if(ar.getSource()==txtusernm && txtusernm.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtuserem.requestFocus();
						val=val+18;
						jp.setValue(val);

					}
				}
				if(ar.getSource()==txtuserem && txtuserem.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtuserph.requestFocus();
						val=val+18;
						jp.setValue(val);

					}
				}
				if(ar.getSource()==txtuserph && txtuserph.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtuserad.requestFocus();
						val=val+16;
						jp.setValue(val);

					}
				}
				if(ar.getSource()==txtuserad && txtuserad.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtuserpass.requestFocus();
						val=val+16;
						jp.setValue(val);

					}
				}
				if(ar.getSource()==txtuserpass && txtuserpass.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtuserage.requestFocus();
						val=val+16;
						jp.setValue(val);

					}
				}
				if(ar.getSource()==txtuserage && txtuserage.getText().length()>0)
				{
					if(ar.getKeyCode()==ar.VK_ENTER)
					{
						txtuserage.requestFocus();
						val=val+16;
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
			    Matcher matcher = pattern.matcher(txtuserem.getText());
			    //Verifying whether given phone number is valid
			    if(matcher.matches()) {
			  	  validate_msg.setText("Valid");
			  	  validate_msg.setForeground(Color.green);
			       
			    } else {
			  	  validate_msg.setText("Invalid");
			  	  validate_msg.setForeground(Color.RED);
			       
			    }
				
			}
			void fillwardno()
			{
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql2="select WARDNO from WARDS";
					st=con.prepareStatement(sql2);
					rs=st.executeQuery();
					while(rs.next())
					{
						cwardno.addItem(rs.getString(1));
					}
				}
				catch (Exception ex) {
					
				}
			}

				
			


			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			String getID(String userid)
			{
				LocalDateTime myDateObj = LocalDateTime.now();
			    
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

			    userid = "U"+myDateObj.format(myFormatObj);
			   
						
			return userid;
			}


			@Override
			public void itemStateChanged(ItemEvent ae) {
				// TODO Auto-generated method stub
				if(ae.getSource()==cwardno)
				{
					wardnm();
				}

			}

			}
