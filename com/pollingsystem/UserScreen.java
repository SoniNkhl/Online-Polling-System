package com.pollingsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import com.pollingsystem.Vote;

public class UserScreen extends JFrame implements ActionListener{
	JMenuBar mb;
	JMenu Result,Candidate;
	JMenuItem Cand_Show,overall,ward;
	JLabel ldate,ltitle,label,lname,lid,lemail,lphone,lstatus,lgender,name,id,email,phone,status,gender,lbldate,lbldoe;
	JLabel lblwardno,wardno;
	String str;
	JPanel jp;
	JFrame f;
	JButton logout,manage,vote;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	UserScreen()
	{
		setSize(1920,1080);
		setTitle("USER");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);

	ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("UserScreen-Polling.jpg"));
    Image bg2= bg1.getImage().getScaledInstance(2000, 1080, Image.SCALE_DEFAULT);
    ImageIcon bg3 = new ImageIcon(bg2);
    JLabel image = new JLabel(bg3);
    image.setBounds(0,0, 2000,1080);
    add(image);
    
	mb=new JMenuBar();
    Result=new JMenu("RESULT");
    
    overall=new JMenuItem("OVER-ALL");
    Result.add(overall);
    overall.addActionListener(this);

    ward=new JMenuItem("WARD-WISE");
    Result.add(ward);
    ward.addActionListener(this);
    
    mb.add(Result);
	
	Candidate=new JMenu("CANDIDATE");
	mb.add(Candidate);

	Cand_Show=new JMenuItem("LIST");
	Candidate.add(Cand_Show);
	Cand_Show.addActionListener(this);
    setJMenuBar(mb);
    
    jp= new JPanel();
	jp.setBounds(0, 0,330, 1080);
	jp.setBackground(new Color(207,208,211));
	image.add(jp);
	jp.setLayout(null);
	
  	ltitle=new JLabel("        USER ");
  	ltitle.setBounds(0,0,400,50);
  	ltitle.setOpaque(true);
  	ltitle.setForeground(Color.WHITE);
  	ltitle.setBackground(Color.BLACK);
  	ltitle.setFont(new Font("Arial",Font.BOLD,50));
  	jp.add(ltitle);
  	
  	label=new JLabel(" ");
  	label.setBounds(100,100,90,100);
  	label.setOpaque(true);
  	label.setForeground(Color.WHITE);
  	label.setBackground(Color.BLACK);
  	label.setFont(new Font("Arial",Font.BOLD,100));
  	jp.add(label);
  	
  	lid=new JLabel("USER ID:");
  	lid.setBounds(20,250,100,50);
  	lid.setForeground(new Color(0,0,0));
  	lid.setFont(new Font("Arial",Font.BOLD,16));
  	jp.add(lid);
  	id=new JLabel();
  	id.setBounds(100,250,200,50);
  	id.setForeground(new Color(24, 123, 205));
  	id.setFont(new Font("Arial",Font.BOLD,16));
  	add(id);
  	jp.add(id);
  	
  	lname=new JLabel("NAME:");
  	lname.setBounds(20,310,200,50);
  	lname.setForeground(new Color(0,0,0));
  	lname.setFont(new Font("Arial",Font.BOLD,16));
  	add(lname);
  	jp.add(lname);
  	name=new JLabel();
  	name.setBounds(100,310,300,50);
  	name.setForeground(new Color(24, 123, 205));
  	name.setFont(new Font("Arial",Font.BOLD,16));
  	add(name);
  	jp.add(name);

  	lemail=new JLabel("EMAIL:");
  	lemail.setBounds(20,370,100,50);
  	lemail.setForeground(new Color(0,0,0));
  	lemail.setFont(new Font("Arial",Font.BOLD,16));
  	add(lemail);
  	jp.add(lemail);
  	email=new JLabel();
  	email.setBounds(100,370,200,50);
  	email.setForeground(new Color(24, 123, 205));
  	email.setFont(new Font("Arial",Font.BOLD,16));
  	add(email);
  	jp.add(email);
  	
  	
  	lphone=new JLabel("PHONE:");
  	lphone.setBounds(20,430,100,50);
  	lphone.setForeground(new Color(0,0,0));
  	lphone.setFont(new Font("Arial",Font.BOLD,16));
  	add(lphone);
  	jp.add(lphone);
  	phone=new JLabel();
  	phone.setBounds(100,430,200,50);
  	phone.setForeground(new Color(24, 123, 205));
  	phone.setFont(new Font("Arial",Font.BOLD,16));
  	add(phone);
  	jp.add(phone);
  	
  	
  	lgender=new JLabel("GENDER:");
  	lgender.setBounds(20,490,100,50);
  	lgender.setForeground(new Color(0,0,0));
  	lgender.setFont(new Font("Arial",Font.BOLD,16));
  	add(lgender);
  	jp.add(lgender);
  	gender=new JLabel();
  	gender.setBounds(100,490,200,50);
  	gender.setForeground(new Color(24, 123, 205));
  	gender.setFont(new Font("Arial",Font.BOLD,16));
  	add(gender);
  	jp.add(gender);
  	
  	lblwardno=new JLabel("WARDNO:");
  	lblwardno.setBounds(20,550,100,50);
  	lblwardno.setForeground(new Color(0,0,0));
  	lblwardno.setFont(new Font("Arial",Font.BOLD,16));
  	add(lblwardno);
  	jp.add(lblwardno);
  	wardno=new JLabel();
  	wardno.setBounds(100,550,200,50);
  	wardno.setForeground(new Color(24, 123, 205));
  	wardno.setFont(new Font("Arial",Font.BOLD,16));
  	add(wardno);
  	jp.add(wardno);
  	
  	lstatus=new JLabel("STATUS:");
  	lstatus.setBounds(20,610,100,50);
  	lstatus.setForeground(new Color(0,0,0));
  	lstatus.setFont(new Font("Arial",Font.BOLD,16));
  	add(lstatus);
  	jp.add(lstatus);
  	status=new JLabel();
  	status.setBounds(100,610,200,50);
  	status.setForeground(new Color(24, 123, 205));
  	status.setFont(new Font("Arial",Font.BOLD,16));
  	add(status);
  	jp.add(status);
  	
  	manage=new JButton("MANAGE PROFILE");
  	manage.setBounds(0,680,400,50);
  	manage.setForeground(Color.WHITE);
  	manage.setBackground(Color.BLACK);
  	manage.setFont(new Font("Arial",Font.BOLD,16));
  	add(manage);
  	jp.add(manage);
  	manage.addActionListener(this);
  	
  	vote=new JButton("CLICK TO VOTE  >>");
    vote.setBounds(1200,750,300,50);
  	vote.setForeground(Color.WHITE);
  	vote.setBackground(Color.BLACK);
  	vote.setFont(new Font("Arial",Font.BOLD,20));
  	add(vote);
  	image.add(vote);
  	vote.addActionListener(this);

  	
  	logout=new JButton("LOGOUT");
  	logout.setBounds(0,730,400,50);
  	logout.setForeground(Color.WHITE);
  	logout.setBackground(Color.BLACK);
  	logout.setFont(new Font("Arial",Font.BOLD,16));
  	add(logout);
  	jp.add(logout);
  	logout.addActionListener(this);

	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Calendar obj = Calendar.getInstance();
    str = formatter.format(obj.getTime());

	ldate=new JLabel("DATE: "+str);
	ldate.setBounds(1300,30,300,50);
	ldate.setForeground(Color.BLACK);
	ldate.setFont(new Font("Arial",Font.BOLD,25));
	add(ldate);
	image.add(ldate);

	lbldate=new JLabel("DATE OF ELECTION: ");
	lbldate.setBounds(360,30,300,50);
	lbldate.setForeground(Color.BLACK);
	lbldate.setFont(new Font("Arial",Font.BOLD,25));
	add(lbldate);
	image.add(lbldate);
	
	lbldoe=new JLabel(" ");
	lbldoe.setBounds(620,30,300,50);
	lbldoe.setForeground(Color.BLACK);
	lbldoe.setFont(new Font("Arial",Font.BOLD,25));
	add(lbldoe);
	image.add(lbldoe);
  	setVisible(true);
  	setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserScreen();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==manage)
		{
			new UserUpdate();
		}
		if(ae.getSource()==logout)
		{
			JOptionPane.showMessageDialog(null, "Logout Successful");
			this.dispose();
		}
		if(ae.getSource()==Cand_Show)
		{
			new Cand_ListScreen();
		}
		if(ae.getSource()==overall)
		{
			new ElectionResult_Overall();
		}
		if(ae.getSource()==ward)
		{
			new ElectionResult_Ward();
		}

		if(ae.getSource()==vote)
		{
			if(str.equals(lbldoe.getText()))
			{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

				String uid=id.getText();
				String wno=wardno.getText();
				Statement st=con.createStatement();
				String sql="select * from userpoll where userid='"+uid+"'and wardno='"+wno+"'";
				
				ResultSet rs=st.executeQuery(sql);
				if (rs.next())
				{
					 Vote e=new Vote();
					    String z=rs.getString("name");
					    char y=z.charAt(0);
					    e.uid.setText(id.getText());
					    e.label.setText(Character.toString(y));
					    e.uname.setText(rs.getString("name"));
					    e.uemail.setText(rs.getString("email"));
					    e.uphone.setText("+91 "+rs.getString("phone"));
					    e.ugender.setText(rs.getString("gender"));
					    e.uwardno.setText(rs.getString("wardno"));
					    e.candnm();
					  	e.pnm1();
					  	e.pnm2();
					  	e.pnm3();
					  	e.pnm4();
					  	e.pnm5();
					  	e.eid();
					    if(rs.getString("status").equals("1"))
					    {
					    	e.ustatus.setText("Active");
					    }
					    else
					    {
					    	e.ustatus.setText("Inactive");
					    }
					
				}
				else
					JOptionPane.showMessageDialog(null, "CHECK ALL DATA FIRST...");
				con.close();
			}
			
			catch(Exception ex) {
			}
			}
			else{	
				JOptionPane.showMessageDialog(this,"Date Of Election is "+lbldoe.getText());
			}

		}
		}

	
	void doe() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql3="select DATEOFELECTION from electionschedule where WARDNO=?";
			String did=wardno.getText();
			st=con.prepareStatement(sql3);
			st.setString(1, did);
			rs=st.executeQuery();
			if (rs.next())
			{
				lbldoe.setText(rs.getString(1));
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
}


