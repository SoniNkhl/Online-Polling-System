package com.pollingsystem;

import java.awt.*;
import javax.swing.*;
import com.pollingsystem.WelcomePolling;

public class Welcome_SplashPolling extends JFrame {
	JLabel label;
	static JLabel l1,l2;
	static JProgressBar jp;98e7t
	int val=0;
	
	Welcome_SplashPolling()
	{
		setSize(1400,800);
		setLayout(null);
		setBounds(150,50,1400,800);
		setLocationRelativeTo(null); 
		setResizable(false);
	
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("background.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
		
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("welcome_splash.png"));
	    Image logo2= logo1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("ONLINE POLLING SYSTEM",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Copperplate Gothic Light",Font.BOLD,26));
	    image_logo.setForeground(new Color(170,0,255));
	    image_logo.setBounds(500,50, 400, 600);
	    add(image_logo);
	    image.add(image_logo);
	    
	  
	    
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setForeground(Color.RED);
		
		jp.setBounds(410,670,600,30);
		add(jp);
		image.add(jp);

		l1=new JLabel();
		l1.setBounds(710,630, 100, 50);
		l1.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,20));
		add(l1);
		image.add(l1);

		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		Welcome_SplashPolling d=new Welcome_SplashPolling();
		try
		{

		
		
		for(i=0;i<=100;i++)
		{
			Thread.sleep(35);	
			d.jp.setValue(i);
			d.l1.setText(String.valueOf(i)+"%");
			if(i==100)
			{
				jp.setVisible(false);
				
			    
			}
			
			
			
		}
		

		d.dispose();
		new WelcomePolling();

		
		}
		catch(InterruptedException ae){
			
		}

}
}