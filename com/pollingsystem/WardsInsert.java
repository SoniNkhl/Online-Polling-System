package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class WardsInsert extends JFrame implements ActionListener,KeyListener{

			JLabel lblwardno,lblwardnm,lbldesc;
			JTextField txtwardno,txtwardnm;
			JTextArea txtdesc;
			JButton btnsave,btnclose,btnclear;
			Connection con;
			PreparedStatement st;
			JProgressBar jp;
			int val=0;
			
	WardsInsert(){
		setSize(1400,800);
		setTitle("WARD INSERT");
		setLayout(null);
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("polling-insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Ward Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(450,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
	    lblwardno=new JLabel("WARD NO.");
	    lblwardno.setBounds(450,150,150,30);
	    lblwardno.setFont(new Font("Arial",Font.BOLD,16));
		add(lblwardno);
		image.add(lblwardno);
		txtwardno=new JTextField();
		txtwardno.setFont(new Font("Arial",Font.BOLD,16));
		txtwardno.addKeyListener(this);
		txtwardno.setBounds(600,150,250,30);
		add(txtwardno);
		
		lblwardnm=new JLabel("WARD NAME");
		lblwardnm.setBounds(450,200,150,30);
		lblwardnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblwardnm);
		image.add(lblwardnm);
		txtwardnm=new JTextField();
		txtwardnm.setFont(new Font("Arial",Font.BOLD,16));
		txtwardnm.addKeyListener(this);
		txtwardnm.setBounds(600,200,250,30);
		add(txtwardnm);
		 
		lbldesc=new JLabel("DESCRIPTION");
		lbldesc.setBounds(450,250,150,30);
		lbldesc.setFont(new Font("Arial",Font.BOLD,16));
		add(lbldesc);
		image.add(lbldesc);
		txtdesc=new JTextArea();
		txtdesc.setFont(new Font("Arial",Font.BOLD,16));
		txtdesc.addKeyListener(this);
		txtdesc.setBounds(600,250,250,60);
		add(txtdesc);
		image.add(txtdesc);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,350,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);

		btnsave=new JButton("SAVE");
		btnsave.setBounds(450,400,150,35);
		btnsave.setBackground(new Color(173,216,230));
		btnsave.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnsave);
		image.add(btnsave);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(620,400,150,35);
		btnclose.setBackground(new Color(173,216,230));
		btnclose.setFont(new Font("BOLD",Font.BOLD,16));
		add(btnclose);
		image.add(btnclose);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(790,400,150,35);
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
					new WardsInsert ();
				}


				@Override
				public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub
					//txtwardno,txtwardnm,txtdesc
					if (ae.getSource()==btnsave)
							{
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
							String sql="insert into Wards values(?,?,?)";
							st=con.prepareStatement(sql);
							String a=txtwardno.getText();
							String b=txtwardnm.getText();
							String c=txtdesc.getText();

							
							//insert * into user value(?)
							st.setString(1,a);
							st.setString(2, b);
							st.setString(3, c);
							int g=st.executeUpdate();
							con.close();
							JOptionPane.showMessageDialog(this, "Data Saved");
							}
					
					
					

					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
					
					}
					
				
					
					if(ae.getSource()==btnclose)
					{
						this.dispose();//for close the window
					}
					if(ae.getSource()==btnclear)
					{
						txtwardno.setText("");
						txtwardnm.setText("");
						txtdesc.setText("");

					}
				}


				@Override
				public void keyPressed(KeyEvent ar) {
					// TODO Auto-generated method stub
					//txtwardno,txtwardnm,txtdesc
					if(ar.getSource()==txtwardno && txtwardno.getText().length()>0)
					{
						if(ar.getKeyCode()==ar.VK_ENTER)
						{
							txtwardnm.requestFocus();
							val=val+35;
							jp.setValue(val);

						}
					}
					if(ar.getSource()==txtwardnm && txtwardnm.getText().length()>0)
					{
						if(ar.getKeyCode()==ar.VK_ENTER)
						{
							txtdesc.requestFocus();
							val=val+35;
							jp.setValue(val);

						}
					}
					if(ar.getSource()==txtdesc && txtdesc.getText().length()>0)
					{
						if(ar.getKeyCode()==ar.VK_ENTER)
						{
							txtdesc.requestFocus();
							val=val+30;
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
				}
