package database;
import java.net.Socket;
import javax.swing.*;

import WeatherUtil.classes.IWeatherDataService;
import WeatherUtil.classes.WeatherDataServiceFactory;
import WeatherUtil.classes.WeatherDataServiceFactory.service;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.net.*;

//import test1;
/*This class will control all interaction
 * with the database. This includes
 * retrieving the alarms and any other
 * function regarding the database I 
 * create. It will later be broken down 
 * into MVC
 */
public class SmartClockgui extends JFrame {
	private JPanel timePane, datePane, weatPane; //Panels for each component
	private JPanel leftPane, rightPane;
	private JPanel mainPane;
	private WeatherPane weather;
	private DatePane dateInfo;
	private TimePane timeInfo;
	
	public SmartClockgui(){
		
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
		
		weather = new WeatherPane("Ottawa", "Canada", "Metric");
		weatPane.add(weather, BorderLayout.CENTER);
		
		dateInfo = new DatePane("Ottawa", "Canada", "Metric");
		datePane.add(dateInfo, BorderLayout.NORTH);
		
		timeInfo = new TimePane("GMT-8:00");
		timePane.add(timeInfo, BorderLayout.SOUTH);
		
		add(mainPane);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public static void main(String args[]){
		SmartClockgui test = new SmartClockgui();
		/*
		 * enter UDP receiver stuff here so WeatherPane constructor 
		 * can be completed
		 */
		//Socket reciever = new Socket(/*enter IP address*/, /*enter port*/);
		String city, country;
		//city = first thing from receiver
		//country = second thing from receiver
		
		
	}
	
}
