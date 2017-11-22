package database;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;

import WeatherUtil.classes.*;
import WeatherUtil.exceptions.*;
import WeatherUtil.classes.WeatherDataServiceFactory.service;

public class DatePane extends JPanel{
	private IWeatherDataService weatherConnection;
	private WeatherData data;
	private String strDate;
	private JLabel lblDate;
	private JPanel pnlDate;
	
	public DatePane(String city, String country, String units){
		super();
		weatherConnection = WeatherDataServiceFactory.getWeatherDataService(service.OPEN_WEATHER_MAP);
		
		try{
			this.setLayout(new BorderLayout());
			data = weatherConnection.getWeatherData(new Location(city, country));
			//Display Date Information
			strDate = data.getSun().getRise();
			lblDate = new JLabel(strDate.substring(0, 10));
			lblDate.setFont(new Font("American Typewriter", Font.PLAIN, 120));
			pnlDate = new JPanel();
			pnlDate.add(lblDate, BorderLayout.CENTER);
			this.add(pnlDate, BorderLayout.CENTER);
		}
		catch (WeatherDataServiceException e)
 		{
 			e.printStackTrace();
 		}
		
	}

}
