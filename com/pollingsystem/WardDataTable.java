package com.pollingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class WardDataTable extends JFrame {
	JTable jt;
	String cols[]= {"WARD NUMBER","WARD NAME","DESCRIPTION"};
	Connection con;Statement st;ResultSet rs;
	DefaultTableModel tb=new DefaultTableModel();

	public WardDataTable()
	{
		setSize(1600,800);
		setTitle("WARD DATA TABLE");
		setVisible(true);
		setLocationRelativeTo(null); 
		
		tb.setColumnIdentifiers(cols);
		
		jt=new JTable();
		
		jt.setModel(tb);
		jt.setFont(new Font("Arial",Font.BOLD,14));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setBackground(new Color(40, 100, 80));
		jt.setForeground(new Color(255,255,255));

		fillData();
		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.yellow);
		header.setFont(new Font("Arial",Font.BOLD,16));
		JScrollPane jp=new JScrollPane(jt);
		add(jp);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
	}
	void fillData()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select * from wards";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				
				tb.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3)});
				
			}
			
			con.close();
		}
		catch(Exception ex) {}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WardDataTable();

	}

}
