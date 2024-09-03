package com.pollingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ElectionResult_Ward extends JFrame implements ItemListener{
	JPanel jp,jr;
	JLabel lbleid,lblwn;
	JComboBox Comboeid,Combown;
	JTable jt;int i=0;int j=0;
	String cols[]= {"PARTY NAME","VOTE COUNT"};
	DefaultTableModel tb=new DefaultTableModel();
	Connection con;
	PreparedStatement st;
    ResultSet rs;

	ElectionResult_Ward()
	{
		setSize(1400,800);
		setTitle("Ward Result");
		setLocationRelativeTo(null);
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("Admin-Polling.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(2000, 1080, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 2000, 1080);
	    add(image);
	    
	    lbleid=new JLabel("ELECTION ID:");
	    lbleid.setBounds(170,40,200,30);
	    lbleid.setFont(new Font("Arial",Font.BOLD,25));
		add(lbleid);
		image.add(lbleid);
		
		Comboeid=new JComboBox();
		Comboeid.setBounds(350,40,250,30);
		Comboeid.insertItemAt("", 0);
		add(Comboeid);
		image.add(Comboeid);
		Comboeid.setFont(new Font("Arial",Font.BOLD,25));
		Comboeid.addItemListener(this);
		filleid();

		lblwn=new JLabel("WARD NO.:");
		lblwn.setBounds(800,40,200,30);
		lblwn.setFont(new Font("Arial",Font.BOLD,25));
		add(lblwn);
		image.add(lblwn);
		
		Combown=new JComboBox();
		Combown.setBounds(950,40,250,30);
		Combown.insertItemAt("", 0);
		add(Combown);
		image.add(Combown);
		Combown.setFont(new Font("Arial",Font.BOLD,25));
		Combown.addItemListener(this);
		fillwn();

	    jp= new JPanel();
		jp.setBounds(20, 10,1350,80);
		jp.setBackground(new Color(255,229,204));
		image.add(jp);
		jp.setLayout(null);

	    jr= new JPanel();
		jr.setBounds(20,100,1350,650);
		jr.setBackground(new Color(255,255,255));
		image.add(jr);

		tb.setColumnIdentifiers(cols);
		
		jt=new JTable();
		
		jt.setModel(tb);
		jt.setFont(new Font("Arial",Font.BOLD,14));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setBackground(new Color(40, 100, 80));
		jt.setForeground(new Color(255,255,255));

		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.yellow);
		header.setFont(new Font("Arial",Font.BOLD,16));
		JScrollPane jp=new JScrollPane(jt);
		jr.add(jp);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ElectionResult_Ward();
	}

	void filleid()
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
				Comboeid.addItem(rs.getString(1));
			}
			}
		catch(Exception ex)                                                       
		{
			System.out.println(ex.toString());
		}
		
		}
	void fillwn()
	{
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select wardno from wards";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while (rs.next())
			{
				Combown.addItem(rs.getString(1));
			}
			}
		catch(Exception ex)                                                       
		{
			System.out.println(ex.toString());
		}
		
		}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Combown)
		{
			String eid=Comboeid.getSelectedItem().toString();
			String wid=Combown.getSelectedItem().toString();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select partyname,count(votecount) from vote where electionid=? and wardno=? group by partyname,votecount";
				st=con.prepareStatement(sql);
				System.out.println(st);
				st.setString(1,eid);
				st.setString(2,wid);
				System.out.println(st);
				rs=st.executeQuery();
				while(rs.next())
				{
			
					tb.addRow(new Object[] {rs.getString(1),rs.getString(2)});
					
				}
				
				con.close();
			}
			catch(Exception ex) {
				System.out.println(ex.toString());
			}
		

		}
		
	}

}
