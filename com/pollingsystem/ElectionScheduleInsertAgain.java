package com.pollingsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class ElectionScheduleInsertAgain extends JFrame implements ActionListener,ItemListener{
	JLabel lblelecid,lblcdid,lblwardno,lbldoe,lblwardnm,lblcandnm;
	JTextField txtdoe;
	JButton btnsave,btnclose,btnclear,btnnew;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	JComboBox combocandid,combowardno,comboeid;

	public ElectionScheduleInsertAgain()
	{
		setSize(1400,800);
		setTitle("INSERT-AGAIN");
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
	    JLabel image1 = new JLabel("Insert-Again",i3,SwingConstants.CENTER);
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
		comboeid=new JComboBox();
		comboeid.setBounds(600,150,250,30);
		comboeid.insertItemAt("", 0);
		add(comboeid);
		image.add(comboeid);
		comboeid.addItemListener(this);
		comboeid.setFont(new Font("Arial",Font.BOLD,16));
		filleid();

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
		add(txtdoe);
		txtdoe.setEditable(false);
		txtdoe.setFont(new Font("Arial",Font.BOLD,16));
		image.add(txtdoe);

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

		btnsave.addActionListener(this);
		btnclose.addActionListener(this);
		btnclear.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ElectionScheduleInsertAgain();
	}
	void filleid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql2="select ELECTIONID from ELECTIONSCHEDULE";
			st=con.prepareStatement(sql2);
			rs=st.executeQuery();
			while(rs.next())
			{
				comboeid.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {
			
		}
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
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource()==btnsave)
		{
			try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		String sql="insert into electionschedule values(?,?,?,?,?)";
		st=con.prepareStatement(sql);
		String a=comboeid.getSelectedItem().toString();
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
		if(ae.getSource()==btnclose)
		{
			this.dispose();//for close the window
		}
		if(ae.getSource()==btnclear)
		{
			txtdoe.setText("");
			
		}
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
	void doe() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql3="select DATEOFELECTION from ELECTIONSCHEDULE where ELECTIONID=?";
			String doe=comboeid.getSelectedItem().toString();
			st=con.prepareStatement(sql3);
			st.setString(1,doe);
			rs=st.executeQuery();
			if (rs.next())
			{
				txtdoe.setText(rs.getString(1));
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
		if(ae.getSource()==comboeid)
		{
			doe();
		}

	}


}
