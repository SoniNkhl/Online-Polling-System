package com.pollingsystem;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminScreen extends JFrame implements ActionListener{
	JMenuBar mb;
	JLabel ldate,ltitle,label,lname,lid,lemail,lphone,lstatus,lgender,name,id,email,phone,status,gender;
	JMenu Admin,User,Wards,Candidate,ElectionSchedule,Result,Party;
	JMenuItem AdminInsert,AdminDelete,AdminFind,AdminUpdate,AdminShow,UserInsert,UserDelete,UserFind,UserUpdate,UserShow,WardsInsert ,WardsDelete,WardsFind,WardsUpdate,WardsShow,
	Cand_Insert,Cand_Delete,Cand_Find,Cand_Update,Cand_Show,ElectionScheduleInsert,ElectionScheduleInsertAgain,ElectionScheduleDelete,ElectionScheduleFind,ElectionScheduleUpdate,ElectionScheduleShow;
	JMenuItem PartyInsert,PartyDelete,PartyFind,PartyUpdate,PartyShow,Cand_List,overall,ward;
	JFrame f;
	String nn;
	JPanel jp;
	JButton logout,manage;

	
	AdminScreen()
	{
		setSize(1920,1080);
		setTitle("ADMIN");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);

	ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("Admin-Polling.jpg"));
    Image bg2= bg1.getImage().getScaledInstance(2000, 1080, Image.SCALE_DEFAULT);
    ImageIcon bg3 = new ImageIcon(bg2);
    JLabel image = new JLabel(bg3);
    image.setBounds(0,0, 2000, 1080);
    add(image);
    
    jp= new JPanel();
	jp.setBounds(0, 0,350, 1080);
	jp.setBackground(new Color(207,208,211));
	image.add(jp);
	jp.setLayout(null);
	
		mb=new JMenuBar();
	Admin=new JMenu("ADMIN");
      // create menu items
      AdminInsert = new JMenuItem("INSERT");
      Admin.add(AdminInsert);
      AdminInsert.addActionListener(this);
      Admin.addSeparator();
      
      AdminDelete = new JMenuItem("DELETE");
      Admin.add(AdminDelete);
      AdminDelete.addActionListener(this);
      Admin.addSeparator();

      AdminFind = new JMenuItem("FIND");
      Admin.add(AdminFind);
      AdminFind.addActionListener(this);
      Admin.addSeparator();
      
      AdminUpdate = new JMenuItem("UPDATE");
      Admin.add(AdminUpdate);
      AdminUpdate.addActionListener(this);
      Admin.addSeparator();

      AdminShow = new JMenuItem("SHOW");
      Admin.add(AdminShow);
      AdminShow.addActionListener(this);
  
      mb.add(Admin);
      
    //USER
      User=new JMenu("USER");
      
      UserInsert = new JMenuItem("INSERT");
      User.add(UserInsert);
      UserInsert.addActionListener(this);
      User.addSeparator();

      UserDelete = new JMenuItem("DELETE");
      User.add(UserDelete);
      UserDelete.addActionListener(this);
      User.addSeparator();

      UserFind = new JMenuItem("FIND");
      User.add(UserFind);
      UserFind.addActionListener(this);
      User.addSeparator();

      UserUpdate = new JMenuItem("UPDATE");
      User.add(UserUpdate);
      UserUpdate.addActionListener(this);
      User.addSeparator();

      UserShow = new JMenuItem("SHOW");
      User.add(UserShow);
      UserShow.addActionListener(this);
    

      // add menu to menu bar
      mb.add(User);
      
      //Wards
      Wards=new JMenu("WARDS");
      
      WardsInsert = new JMenuItem("INSERT");
      Wards.add(WardsInsert);
      WardsInsert.addActionListener(this);
      Wards.addSeparator();

      WardsDelete = new JMenuItem("DELETE");
      Wards.add(WardsDelete);
      WardsDelete.addActionListener(this);
      Wards.addSeparator();

      WardsFind = new JMenuItem("FIND");
      Wards.add(WardsFind);
      WardsFind.addActionListener(this);
      Wards.addSeparator();

      WardsUpdate = new JMenuItem("UPDATE");
      Wards.add(WardsUpdate);
      WardsUpdate.addActionListener(this);
      Wards.addSeparator();

      WardsShow = new JMenuItem("SHOW");
      Wards.add(WardsShow);
      WardsShow.addActionListener(this);

    

      // add menu to menu bar
      mb.add(Wards);
      
      //Candidate
      Candidate=new JMenu("CANDIDATE");
      
      Cand_Insert = new JMenuItem("INSERT");
      Candidate.add(Cand_Insert);
      Cand_Insert.addActionListener(this);
      Candidate.addSeparator();

      Cand_Delete = new JMenuItem("DELETE");
      Candidate.add(Cand_Delete);
      Cand_Delete.addActionListener(this);
      Candidate.addSeparator();
      
      Cand_Find = new JMenuItem("FIND");
      Candidate.add(Cand_Find);
      Cand_Find.addActionListener(this);
      Candidate.addSeparator();

      Cand_Update = new JMenuItem("UPDATE");
      Candidate.add(Cand_Update);
      Cand_Update.addActionListener(this);
      Candidate.addSeparator();

      Cand_Show = new JMenuItem("SHOW");
      Candidate.add(Cand_Show);
      Cand_Show.addActionListener(this);
      // add menu to menu bar
      mb.add(Candidate);
      
      
      //ElectionSchedule
      ElectionSchedule=new JMenu("ELECTION SCHEDULE");
      
      ElectionScheduleInsert = new JMenuItem("INSERT");
      ElectionSchedule.add(ElectionScheduleInsert);
      ElectionScheduleInsert.addActionListener(this);
      ElectionSchedule.addSeparator();

      ElectionScheduleInsertAgain = new JMenuItem("INSERT-AGAIN");
      ElectionSchedule.add(ElectionScheduleInsertAgain);
      ElectionScheduleInsertAgain.addActionListener(this);
      ElectionSchedule.addSeparator();

      ElectionScheduleDelete = new JMenuItem("DELETE");
      ElectionSchedule.add(ElectionScheduleDelete);
      ElectionScheduleDelete.addActionListener(this);
      ElectionSchedule.addSeparator();

      ElectionScheduleFind = new JMenuItem("FIND");
      ElectionSchedule.add(ElectionScheduleFind);
      ElectionScheduleFind.addActionListener(this);
      ElectionSchedule.addSeparator();

      ElectionScheduleUpdate = new JMenuItem("UPDATE");
      ElectionSchedule.add(ElectionScheduleUpdate);
      ElectionScheduleUpdate.addActionListener(this);
      ElectionSchedule.addSeparator();

      ElectionScheduleShow = new JMenuItem("SHOW");
      ElectionSchedule.add(ElectionScheduleShow);
      ElectionScheduleShow.addActionListener(this);

    

      // add menu to menu bar
      mb.add(ElectionSchedule);
      
      Party=new JMenu("PARTY");
      
      PartyInsert=new JMenuItem("INSERT");
      Party.add(PartyInsert);
      PartyInsert.addActionListener(this);
      Party.addSeparator();
   
      PartyDelete=new JMenuItem("DELETE");
      Party.add(PartyDelete);
      PartyDelete.addActionListener(this);
      Party.addSeparator();

      PartyFind=new JMenuItem("FIND");
      Party.add(PartyFind);
      PartyFind.addActionListener(this);
      Party.addSeparator();

      PartyUpdate=new JMenuItem("UPDATE");
      Party.add(PartyUpdate);
      PartyUpdate.addActionListener(this);
      Party.addSeparator();

      PartyShow=new JMenuItem("SHOW");
      Party.add(PartyShow);
      PartyShow.addActionListener(this);
 
      mb.add(Party);
      
      Result=new JMenu("RESULT");
      
      overall=new JMenuItem("OVER-ALL");
      Result.add(overall);
      overall.addActionListener(this);
  
      ward=new JMenuItem("WARD-WISE");
      Result.add(ward);
      ward.addActionListener(this);

      mb.add(Result);
      
  	ltitle=new JLabel("        ADMIN ");
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
  	
  	lid=new JLabel("ADMIN ID:");
  	lid.setBounds(20,300,100,50);
  	lid.setForeground(new Color(0,0,0));
  	lid.setFont(new Font("Arial",Font.BOLD,16));
  	jp.add(lid);
  	id=new JLabel();
  	id.setBounds(100,300,200,50);
  	id.setForeground(new Color(24, 123, 205));
  	id.setFont(new Font("Arial",Font.BOLD,16));
  	add(id);
  	jp.add(id);
  	
  	lname=new JLabel("NAME:");
  	lname.setBounds(20,360,200,50);
  	lname.setForeground(new Color(0,0,0));
  	lname.setFont(new Font("Arial",Font.BOLD,16));
  	add(lname);
  	jp.add(lname);
  	name=new JLabel();
  	name.setBounds(100,360,100,50);
  	name.setForeground(new Color(24, 123, 205));
  	name.setFont(new Font("Arial",Font.BOLD,16));
  	add(name);
  	jp.add(name);

  	lemail=new JLabel("EMAIL:");
  	lemail.setBounds(20,420,100,50);
  	lemail.setForeground(new Color(0,0,0));
  	lemail.setFont(new Font("Arial",Font.BOLD,16));
  	add(lemail);
  	jp.add(lemail);
  	email=new JLabel();
  	email.setBounds(100,420,200,50);
  	email.setForeground(new Color(24, 123, 205));
  	email.setFont(new Font("Arial",Font.BOLD,16));
  	add(email);
  	jp.add(email);
  	
  	
  	lphone=new JLabel("PHONE:");
  	lphone.setBounds(20,480,100,50);
  	lphone.setForeground(new Color(0,0,0));
  	lphone.setFont(new Font("Arial",Font.BOLD,16));
  	add(lphone);
  	jp.add(lphone);
  	phone=new JLabel();
  	phone.setBounds(100,480,200,50);
  	phone.setForeground(new Color(24, 123, 205));
  	phone.setFont(new Font("Arial",Font.BOLD,16));
  	add(phone);
  	jp.add(phone);
  	
  	
  	lgender=new JLabel("GENDER:");
  	lgender.setBounds(20,540,100,50);
  	lgender.setForeground(new Color(0,0,0));
  	lgender.setFont(new Font("Arial",Font.BOLD,16));
  	add(lgender);
  	jp.add(lgender);
  	gender=new JLabel();
  	gender.setBounds(100,540,200,50);
  	gender.setForeground(new Color(24, 123, 205));
  	gender.setFont(new Font("Arial",Font.BOLD,16));
  	add(gender);
  	jp.add(gender);
  	
  	lstatus=new JLabel("STATUS:");
  	lstatus.setBounds(20,600,100,50);
  	lstatus.setForeground(new Color(0,0,0));
  	lstatus.setFont(new Font("Arial",Font.BOLD,16));
  	add(lstatus);
  	jp.add(lstatus);
  	status=new JLabel();
  	status.setBounds(100,600,200,50);
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
    String str = formatter.format(obj.getTime());

	ldate=new JLabel("DATE: "+str);
	ldate.setBounds(1300,30,300,50);
	ldate.setForeground(Color.BLACK);
	ldate.setFont(new Font("Arial",Font.BOLD,25));
	add(ldate);
	image.add(ldate);

      
      setJMenuBar(mb);
  	setVisible(true);
  	setDefaultCloseOperation(EXIT_ON_CLOSE);
  	
      

      }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminScreen();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==AdminInsert)
		{
			new AdminInsert();
		}
		
		if(ae.getSource()==AdminFind)
		{
			new AdminFind();
		}
		
		if(ae.getSource()==AdminDelete)
		{
			new AdminDelete();
		}
		if(ae.getSource()==AdminUpdate)
		{
			new AdminUpdate();
		}		
		if(ae.getSource()==AdminShow)
		{
			new AdminTable();
		}

		if(ae.getSource()==WardsInsert)
		{
			new WardsInsert();
		}
		
		if(ae.getSource()==WardsFind)
		{
			new WardsFind();
		}
		
		if(ae.getSource()==WardsDelete)
		{
			new WardsDelete();
		}
		
		if(ae.getSource()==WardsUpdate)
		{
			new WardsUpdate();
		}
		if(ae.getSource()==WardsShow)
		{
			new WardDataTable();
		}
	
		if(ae.getSource()==Cand_Insert)
		{
			new Cand_Insert();
		}
		
		if(ae.getSource()==Cand_Find)
		{
			new Cand_Find();
		}
		
		if(ae.getSource()==Cand_Delete)
		{
			new Cand_Delete();
		}
		
		if(ae.getSource()==Cand_Update)
		{
			new Cand_Update();
		}
		if(ae.getSource()==Cand_Show)
		{
			new Cand_Table();
		}
	
		if(ae.getSource()== ElectionScheduleInsert)
		{
			new  ElectionScheduleInsert();
		}
		if(ae.getSource()== ElectionScheduleInsertAgain)
		{
			new  ElectionScheduleInsertAgain();
		}	
		if(ae.getSource()== ElectionScheduleFind)
		{
			new  ElectionScheduleFind();
		}
		
		if(ae.getSource()== ElectionScheduleDelete)
		{
			new  ElectionScheduleDelete();
		}
		
		if(ae.getSource()== ElectionScheduleUpdate)
		{
			new  ElectionScheduleUpdate();
		}
		if(ae.getSource()== ElectionScheduleShow)
		{
			new  ElectionScheduleTable();
		}
		if(ae.getSource()==overall)
		{
			new ElectionResult_Overall();
		}
		if(ae.getSource()==ward)
		{
			new ElectionResult_Ward();
		}
		if(ae.getSource()==UserInsert)
		{
			new UserInsert();
		}
		
		if(ae.getSource()==UserFind)
		{
			new UserFind();
		}
		
		if(ae.getSource()==UserDelete)
		{
			new UserDelete();
		}
		
		if(ae.getSource()==UserUpdate)
		{
			new UserUpdate();
		}
		if(ae.getSource()==UserShow)
		{
			new UserTable();
		}
		if(ae.getSource()==PartyInsert)
		{
			new Party_Insert();
		}
		if(ae.getSource()==PartyDelete)
		{
			new Party_delete();
		}
		if(ae.getSource()==PartyFind)
		{
			new Party_find();
		}
		if(ae.getSource()==PartyUpdate)
		{
			new Party_Update();
		}
		if(ae.getSource()==PartyShow)
		{
			new PartyTable();
		}
		if(ae.getSource()==manage)
		{
			new AdminUpdate();
		}
		if(ae.getSource()==logout)
		{
			JOptionPane.showMessageDialog(null, "Logout Successful");
			this.dispose();
		}

				
	}}
