package com.pollingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.pollingsystem.AdminScreen;

public class AdminLogin extends JFrame implements ActionListener,KeyListener{
	JLabel admin_id,admin_password,login,login1,sign,a,b;
	JComboBox admin_idf;
	JPasswordField admin_passwordf;
	JButton loginb,newsign,forgot,back;
	Connection con;PreparedStatement st;ResultSet rs;
public AdminLogin() {
		
		setSize(1400,800);
		setTitle("ADMIN LOGIN");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
				
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("Login-Polling.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("welcome_admin.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("ADMIN LOGIN",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setFont(new Font("Arial",Font.BOLD,16));
	    image1.setBounds(620,30, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(400,100,600, 500);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		admin_id=new JLabel("Admin Id:");
		admin_id.setBounds(100,150,100,30);
		admin_id.setFont(new Font("Arial",Font.BOLD,16));
		admin_id.setForeground(Color.BLACK);
		con.add(admin_id);
		admin_idf=new JComboBox();
		admin_idf.setBounds(100,190,350,30);
		con.add(admin_idf);
		filldata1();
		admin_idf.addKeyListener(this);
		
		
		admin_password=new JLabel("Password:");
		admin_password.setBounds(100,240,100,30);
		admin_password.setFont(new Font("Arial",Font.BOLD,16));
		admin_password.setForeground(Color.BLACK);
		con.add(admin_password);
		admin_passwordf=new JPasswordField();
		admin_passwordf.setBounds(100,280,350,30);
		con.add(admin_passwordf);
		admin_passwordf.addKeyListener(this);
		
		forgot=new JButton("Forgot Password?");
		forgot.setBounds(60,310,200,30);
		forgot.setFont(new Font("Arial",Font.BOLD,14));
		forgot.setContentAreaFilled(false);
	    forgot.setFocusPainted(false);
	    forgot.setBorderPainted(false);
	    forgot.setForeground(Color.BLUE);
		con.add(forgot);
		forgot.addActionListener(this);
		
		loginb=new JButton("Login");
	    loginb.setBounds(200,350,200,30);
	    loginb.setFont(new Font("Arial",Font.BOLD,16));
	    loginb.setBackground(Color.BLACK);
		loginb.setForeground(Color.WHITE);
		con.add(loginb);
		loginb.addActionListener(this);
		loginb.addKeyListener(this);
		
		newsign=new JButton("Create new account!");
	    newsign.setBounds(50,400,250,30);
	    newsign.setFont(new Font("Arial",Font.BOLD,16));
	    newsign.setContentAreaFilled(false);
	    newsign.setFocusPainted(false);
	    newsign.setBorderPainted(false);
		newsign.setForeground(Color.RED);
		con.add(newsign);
		newsign.addActionListener(this);
		
		back=new JButton("<< Back");
		back.setBounds(400,400,100,30);
		back.setFont(new Font("Arial",Font.BOLD,16));
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setForeground(new Color(4,118,208));
		con.add(back);
		back.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminLogin();

	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(loginb))
		{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

				String aid=admin_idf.getSelectedItem().toString();
				String pas=admin_passwordf.getText();
				Statement st=con.createStatement();
				
				String sql="select * from admin where adminid='"+aid+"'and password='"+pas+"'";
				ResultSet rs=st.executeQuery(sql);
				
				if(admin_idf.getSelectedItem().toString().length()==0||admin_passwordf.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Fill all the entries First");
				}
				if (rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login successfully");
				    
				    AdminScreen d=new AdminScreen();
				    String z=rs.getString("name");
				    char y=z.charAt(0);
				    d.id.setText(admin_idf.getSelectedItem().toString());
				    d.label.setText(Character.toString(y));
				    d.name.setText(rs.getString("name"));
				    d.email.setText(rs.getString("email"));
				    d.phone.setText("+91 "+rs.getString("phone"));
				    d.gender.setText(rs.getString("gender"));
				    
				    if(rs.getString("status").equals("1"))
				    {
				    	d.status.setText("Active");
				    }
				    else
				    {
				    	d.status.setText("Inactive");
				    }
				    d.setVisible(true);
				    
					
				}
				else
					JOptionPane.showMessageDialog(null, "Login Failed");
				con.close();
			}
			catch(Exception ex) {
			}
			
			
		}

		if(ae.getSource()==(newsign))
		{
			new AdminInsert();
		}
		if(ae.getSource()==(forgot))
		{
			new AdminUpdate();
		}
		if(ae.getSource()==(back))
		{
			this.dispose();
		}
	}
		void filldata1()
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select AdminId from Admin";
				st=con.prepareStatement(sql);
				rs=st.executeQuery();
				while(rs.next())
				{
					admin_idf.addItem(rs.getString(1));
				}
				
			}
			catch(Exception ex) {}
		
	}
	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==admin_idf)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				admin_passwordf.requestFocus();
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
