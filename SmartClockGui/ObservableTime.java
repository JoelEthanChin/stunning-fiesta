package database;

import java.util.*;

public class ObservableTime extends Observable{
	/**
	 * @author Joel Chin 101001146
	 */
	private ObserverTime time;
	private Date curDate;
	
	public ObservableTime(){
		
		this.curDate = new Date();
		
	}
	
	public void setCurDate(Date date){
		this.curDate = date;
		setChanged();
		notifyObservers(date);
		
	}
	
	public static void main(String args[]){
		ObservableTime t = new ObservableTime();
		
		ObserverTime t1 = new ObserverTime();
		ObserverTime t2 = new ObserverTime();
		
		t.addObserver(t1);
		t.addObserver(t2);
		
		Date newDate = new Date();
		
		t.setCurDate(newDate);
		
		System.out.println(t2.getCurTime().toString());
	}

}
