package classes;

import exceptions.WeatherDataServiceException;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         An Inteface representing IWeatherDataService object Throws a
 *         WeatherDataServiceException
 */
public interface IWeatherDataService
{
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceException;
}
