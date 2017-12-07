package database;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;


public class DateTimePane extends JPanel{
	/**
	 * @author Joel Chin 101001146
	 */
	private SubPane displayTime;
	private SubPane displayDate;
	
	public DateTimePane(String country, String city){
		super();
		
		this.setLayout(new GridLayout(2, 1, 0, 400));
		
		//setup Calendar Object to get time
		Calendar calTime = Calendar.getInstance();
		calTime.setTimeZone(TimeZone.getTimeZone(this.findTimeZone(country, city)));
		
		displayTime = new SubPane(calTime.getTime().toString().substring(10, 16));
		displayTime.getLblComponent().setFont(new Font("American Typewritter", Font.PLAIN, 120));
		
		this.add(displayTime);
		
		
		displayDate = new SubPane(calTime.get(Calendar.YEAR) + "/" + calTime.get(Calendar.DAY_OF_MONTH) + "/" + this.getMonth(calTime.get(Calendar.DAY_OF_WEEK)));
		displayDate.getLblComponent().setFont(new Font("American Typewritter", Font.PLAIN, 120));
		
		this.add(displayDate);
		
	}
	
	public String findTimeZone(String country, String city){
		country.replace(' ', '_');
		city.replace(' ', '_');
		TimeZone timezoneID = TimeZone.getTimeZone(country + "/" + city);

//		String[] timezoneID;
//		timezoneID = TimeZone.getAvailableIDs(); //retrieve all time zones
//		List<String> relevantTime = new ArrayList<String>(); //create new list to return
//		
//		for(String TimeZoneID : timezoneID){
//			if(TimeZoneID.startsWith(country))
//				relevantTime.add(TimeZoneID);
//		}
		
		return timezoneID.getID();
	}

	
	public String getMonth(int num){
		String month = null;
		switch(num){
		
		case 1: month = "Jan";
				break;
		case 2: month = "Feb";
				break;
		case 3: month = "Mar";
				break;
		case 4: month = "Apr";
				break;
		case 5: month = "May";
				break;
		case 6: month = "Jun";
				break;
		case 7: month = "Jul";
				break;
		case 8: month = "Aug";
				break;
		case 9: month = "Sep";
				break;
		case 10: month = "Oct";
				break;
		case 11: month = "Nov";
				break;
		case 12: month = "Dec";
				break;
		default: month = "Invalid";
				break;
		}
		
		return month;
	}
}
