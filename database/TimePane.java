package database;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.*;

import javax.swing.*;


public class TimePane extends JPanel{
	private String strTime;
	private JLabel lblTime;
	private JPanel pnlTime;
	private Calendar calTime;
	private Thread thrTime;
	
	
	public TimePane(String timeZone){
		super();
		
		//setup Calendar Object to get time
		calTime = Calendar.getInstance();
		calTime.setTimeZone(TimeZone.getTimeZone(timeZone));
		
		//create Thread Object to continuously retrieve time
		thrTime = new Thread();
		
		//Displays Time
		lblTime = new JLabel(this.repeatTime());
		lblTime.setFont(new Font("American Typewriter", Font.PLAIN, 120));
		pnlTime = new JPanel();
		pnlTime.add(lblTime, BorderLayout.CENTER);
		this.add(pnlTime, BorderLayout.CENTER);
		
	}
	
	public String repeatTime(){
		while(true){
			this.strTime = calTime.getTime().toString().substring(11, 16);
			return this.strTime;
		}
		
	}
	

}
