package database;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.*;

import javax.swing.*;


public class TimePane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
//		thrTime = new Thread();
//		thrTime.run();
		//Displays Time
//		this.update((Observer)this, (Object)this);
		lblTime = new JLabel(this.getStrTime());
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

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public Calendar getCalTime() {
		return calTime;
	}

	public void setCalTime(Calendar calTime) {
		this.calTime = calTime;
	}
	
	

}
