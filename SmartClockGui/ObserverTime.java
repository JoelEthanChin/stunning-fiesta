package database;

import java.util.*;

public class ObserverTime implements Observer{
	private Date curTime; //Current Time

	@Override
	public void update(Observable o, Object arg) {
		
		if(!curTime.equals(System.currentTimeMillis())){//if curTime != System Time update
			
			this.setCurTime((Date)arg);
			
		}
		
	}
	
	public ObserverTime(){
		
	}

	public Date getCurTime() {
		
		return curTime;
		
	}

	public void setCurTime(Date curTime) {
		
		this.curTime = curTime;
		
	}
	
	

}
