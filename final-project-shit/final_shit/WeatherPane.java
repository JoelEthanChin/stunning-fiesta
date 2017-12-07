 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

import classes.*;
import exceptions.*;
import classes.WeatherDataServiceFactory.service;

public class WeatherPane extends JPanel{
    /**
     * @author Joel Chin 101001146
     */
    private IWeatherDataService weatherConnection;
    private WeatherData data;
    private JPanel pnlWeather;
    private SubPane clouds;
    private SubPane direction;
    private SubPane humidity;
    private SubPane precipitation;
    private SubPane sunrise;
    private SubPane sunset;
    private SubPane temperature;
    private SubPane wind;
    private SubPane pressure;
    
    
    public WeatherPane(String country, String city, String unit){
        weatherConnection = WeatherDataServiceFactory.getWeatherDataService(service.OPEN_WEATHER_MAP);
        try{
            this.setLayout(new BorderLayout());
            data = weatherConnection.getWeatherData(new Location(city, country));
            
            //Displays Temperature
            temperature = new SubPane(data.getTemperature().getValue() + "C");
            this.add(temperature, BorderLayout.CENTER);
            
            //Display weather panel to format preceding information
            pnlWeather = new JPanel();
            pnlWeather.setLayout(new GridLayout(8, 1));
            this.add(pnlWeather, BorderLayout.SOUTH);
            
            //Displays Clouds
            clouds = new SubPane(data.getClouds().getValue());
            pnlWeather.add(clouds);
            
            //Displays Humidity
            humidity = new SubPane(data.getHumidity().getValue() + " " + data.getHumidity().getUnit(), false);
            pnlWeather.add(humidity);
            
            //Displays Precipitation
            precipitation = new SubPane(data.getPrecipitation().getValue() + " " + data.getPrecipitation().getMode(), false);
            pnlWeather.add(precipitation);
            
            //Displays Wind Speed
            wind = new SubPane(data.getWind().getSpeed().getName());
            pnlWeather.add(wind);
            
            //Displays Wind Direction
            direction = new SubPane(data.getWind().getDirection().getName());
            pnlWeather.add(direction);
            
            //Displays Pressure
            pressure = new SubPane(data.getPressure().getValue() + " " + data.getPressure().getUnit());
            pnlWeather.add(pressure);
            
            //Displays Sunrise
            sunrise = new SubPane(data.getSun().getRise().substring(10, data.getSun().getRise().length()) + " AM");
            pnlWeather.add(sunrise);
            
            //Displays Sunset
            sunset = new SubPane(data.getSun().getSet().substring(10, data.getSun().getSet().length()-3) + " PM");
            pnlWeather.add(sunset);
            
            
        }
        catch (WeatherDataServiceException e)
        {
            e.printStackTrace();
        }
        
        
    }

}
