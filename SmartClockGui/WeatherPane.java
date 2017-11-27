package database;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

import WeatherUtil.classes.*;
import WeatherUtil.exceptions.*;
import WeatherUtil.classes.WeatherDataServiceFactory.service;

public class WeatherPane extends JPanel{
	private IWeatherDataService weatherConnection;
	private WeatherData data;
	private JLabel lblClouds;
	private JLabel lblDirection;
	private JLabel lblHumidity;
	private JLabel lblLastUpdate;
	private JLabel lblPrecipitation;
	private JLabel lblPressure;
	private JLabel lblSunrise;
	private JLabel lblSunset;
	private JLabel lblTemp; 
	private JLabel lblWind;
	private JPanel pnlClouds;
	private JPanel pnlDirection;
	private JPanel pnlHumidity;
	private JPanel pnlLastUpdate;
	private JPanel pnlPrecipitation;
	private JPanel pnlPressure;
	private JPanel pnlSunrise;
	private JPanel pnlSunset;
	private JPanel pnlTemp;
	private JPanel pnlWeather; 
	private JPanel pnlWind;
	private String strClouds;
	private String strDirection;
	private String strHumidity;
	private String strLastUpdate;
	private String strPrecipitation;
	private String strPressure;
	private String strSunrise;
	private String strSunset;
	private String strTemp;
	private String strWind;
	
	public WeatherPane(String city, String country, String unit){
		weatherConnection = WeatherDataServiceFactory.getWeatherDataService(service.OPEN_WEATHER_MAP);
		try{
			//Displays Temperature
			this.setLayout(new BorderLayout());
			data = weatherConnection.getWeatherData(new Location(city, country));
			strTemp = data.getTemperature().getValue() + " °C";
			lblTemp = new JLabel(strTemp);
			lblTemp.setFont(new Font("American Typewriter",Font.PLAIN,120));
			pnlTemp = new JPanel();
			pnlTemp.add(lblTemp, BorderLayout.CENTER);
			this.add(pnlTemp, BorderLayout.CENTER);
			
			//Display weather panel to format preceding information
			pnlWeather = new JPanel();
			pnlWeather.setLayout(new GridLayout(9, 1));
			this.add(pnlWeather, BorderLayout.SOUTH);
			
			//Displays Clouds
			strClouds =  data.getClouds().getValue();
			lblClouds = new JLabel(strClouds);
			lblClouds.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlClouds = new JPanel();
			pnlClouds.add(lblClouds, BorderLayout.CENTER);
			pnlWeather.add(pnlClouds);
			
			//Displays Humidity
			strHumidity = data.getHumidity().getValue() + " " + data.getHumidity().getUnit();
			lblHumidity = new JLabel(strHumidity);
			lblHumidity.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlHumidity = new JPanel();
			pnlHumidity.add(lblHumidity, BorderLayout.CENTER);
			pnlWeather.add(pnlHumidity);
			
			//Displays Precipitation
			strPrecipitation = data.getPrecipitation().getValue() + " " + data.getPrecipitation().getMode();
			lblPrecipitation = new JLabel(strPrecipitation);
			lblPrecipitation.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlPrecipitation = new JPanel();
			pnlPrecipitation.add(lblPrecipitation);
			pnlWeather.add(pnlPrecipitation);
			
			//Displays Wind Speed
			strWind = data.getWind().getSpeed().getName();
			lblWind = new JLabel(strWind);
			lblWind.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlWind = new JPanel();
			pnlWind.add(lblWind);
			pnlWeather.add(pnlWind);
			
			//Displays Wind Direction
			strDirection = data.getWind().getDirection().getName();
			lblDirection = new JLabel(strDirection);
			lblDirection.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlDirection = new JPanel();
			pnlDirection.add(lblDirection);
			pnlWeather.add(pnlDirection);
			
			//Displays Pressure
			strPressure = data.getPressure().getValue() + " " + data.getPressure().getUnit();
			lblPressure = new JLabel(strPressure);
			lblPressure.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlPressure = new JPanel();
			pnlPressure.add(lblPressure);
			pnlWeather.add(pnlPressure);
			
			//Displays Sunrise
			strSunrise = data.getSun().getRise();
			lblSunrise = new JLabel(strSunrise.substring(10, strSunrise.length()-3) + " AM");
			lblSunrise.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlSunrise = new JPanel();
			pnlSunrise.add(lblSunrise);
			pnlWeather.add(pnlSunrise);
			
			//Displays Sunset
			strSunset = data.getSun().getSet();
			lblSunset = new JLabel(strSunset.substring(10, strSunset.length()-3) + " PM");
			lblSunset.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlSunset = new JPanel();
			pnlSunset.add(lblSunset);
			pnlWeather.add(pnlSunset);
			
			//Display the Last Update
			strLastUpdate = data.getLastUpdate().getValue();
			lblLastUpdate = new JLabel(strLastUpdate);
			lblLastUpdate.setFont(new Font("American Typewriter", Font.PLAIN, 55));
			pnlLastUpdate = new JPanel();
			pnlLastUpdate.add(lblLastUpdate);
			pnlWeather.add(pnlLastUpdate);
			
		}
		catch (WeatherDataServiceException e)
 		{
 			e.printStackTrace();
 		}
		
		
	}

}
