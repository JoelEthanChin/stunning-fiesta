package database;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.io.*;


/*This class will control all interaction
 * with the database. This includes
 * retrieving the alarms and any other
 * function regarding the database I 
 * create. It will later be broken down 
 * into MVC
 */


public class SmartClockgui extends JFrame {
	/**
	 * @author Joel Chin 101001146
	 */
	private JPanel timePane, datePane, weatPane; //Panels for each component
	private JPanel leftPane, rightPane;
	private JPanel mainPane;
	private WeatherPane weather;
	private DatePane dateInfo;
	private TimePane timeInfo;
	private final int PACKETSIZE = 100;
	
	public SmartClockgui(String country, String city){
		
		super();
		mainPane = new JPanel(new BorderLayout());
		leftPane = new JPanel(new BorderLayout());
		rightPane = new JPanel(new BorderLayout());
		timePane = new JPanel();
		datePane = new JPanel();
		weatPane = new JPanel();
		
		leftPane.add(timePane, BorderLayout.PAGE_START);
		leftPane.add(datePane, BorderLayout.PAGE_END);
		
		rightPane.add(weatPane, BorderLayout.CENTER);
		
		mainPane.add(leftPane, BorderLayout.LINE_START);
		mainPane.add(rightPane, BorderLayout.LINE_END);
		
		weather = new WeatherPane(city, country, "Metric");
		weatPane.add(weather, BorderLayout.CENTER);
		
		dateInfo = new DatePane(city, country, "Metric");
		datePane.add(dateInfo, BorderLayout.NORTH);
		
		timeInfo = new TimePane("UTC-05:00");
		timePane.add(timeInfo, BorderLayout.SOUTH);
		
		add(mainPane);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException{
		
		
		//initialize ports for receiving and sending
		int portR = Integer.parseInt(args[0]);
		int portS = Integer.parseInt(args[1]);
		
		String city, country;
		try{//try block to get country and city information
			
			Socket sr = new Socket("10.0.0.1", portR);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(sr.getInputStream()));
			
			country = input.readLine();
			city = input.readLine();
			
			SmartClockgui test = new SmartClockgui(country, city);
			
			sr.close();
			
		}
		catch (IOException e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
}
