import java.net.*;
import com.pi4j.wiringpi.Serial;
import java.util.*;

/**
 * @author Joel Chin
 * This class is the master program that creates multiple threads
 * to handle different aspect of the clock.
 */
public class shell {
    public void main(){
        Scanner sc = new Scanner(System.in);
        System.out.print("R u rdy!!!!!!!!!!! (Y/N)");
        
        DataBase db = new DataBase();
        Listener l = new Listener();
        
        String in = sc.nextLine();
        if(in.toUpperCase().equals("Y")) {
            db.start();
            l.start();
        }else{
            System.out.println("sayonara !!!");
        }
        
        while(true){
            System.out.println("Enter 'Q' to stop system");
            in = sc.nextLine();
            if(in.toUpperCase().equals("Q")) {
                break;
            }
        }
        
        sc.close();
        db.Stop();
        l.Stop();
    }
}


/**
 * @author Joel Chin, Dalia Matar, Rami Daham
 * This class controls the main flow of information
 * This includes receiving user input from the Android
 * app and redirecting it to the correct sub classes.
 */
class DataBase extends Thread {
    //sockets
    private DatagramSocket androidSendingSocket, androidReceivingSocket, guiSendingSocket;
    
    //packets
    private DatagramPacket androidReceivingPacket, androidSendingPacket, guiSendingPacket;
    
    //ports
    private int androidSendingPort = 1024, androidReceivingPort = 1025, guiSendingPort = 1027; 
    
    
    
    //other
    private final int PACKET_SIZE = 512;
    boolean running;
    String country, city, alarm, amPm, in;
    boolean pm;
    byte[] Ack = new String("lol").getBytes();
    
    //ip addresses
    InetAddress local;
    InetAddress androidIp;
    
    public DataBase(){
        try {
            //setup sockets
            androidSendingSocket= new DatagramSocket(androidSendingPort);
            guiSendingSocket = new DatagramSocket();
            androidReceivingSocket = new DatagramSocket(androidReceivingPort);
           
            
            //
            running = true;
            
            //
            local = InetAddress.getByName("localhost");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void run(){
        byte[] data = new byte[PACKET_SIZE];
        while(running){
            //receive
            data = receiveFromAndroid();
            
            //parse
            in = parse(data);
            boolean timeSet = false;
            
            if(in.equals("1")){
                //send ack to android
                sendToAndroid();
            
                //receive
                data = receiveFromAndroid();
            
                //parse
                country = parse(data);
            
                //send ack to android
                sendToAndroid();
                
                //receive
                data = receiveFromAndroid();
            
                //parse
                city = parse(data);
                
                //send to gui
                sendToGui();
                
            }else if(in.equals("2")){
                //receive
                data = receiveFromAndroid();
            
                //parse
                alarm = parse(data);
            
                //send ack to android
                sendToAndroid();
            
                //receive
                data = receiveFromAndroid();
            
                //parse
                amPm = parse(data);
                
                timeSet = true;
            
            }
            
            
            
            //set alarm
            if(timeSet){
                if( amPm.equals("true")){
                    /* convert */
                    byte[] b = alarm.getBytes();
                    byte[] hour = {b[0], b[1]};
                    byte[] min = {b[3], b[4]};
                    String temp = new String(hour);
                
                    int timer = Integer.parseInt(temp) + 12;
                    alarm = timer + ":" + new String(min);
                }
                
                CalenderTime cd = new CalenderTime(alarm);
                cd.start();
                timeSet = false;
            }
            
            
        }
    }
    
    public void Stop(){
        
    }
    
    private byte[] receiveFromAndroid(){
        try {
            //create packet
            androidReceivingPacket = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
            
            //receive packet
            androidReceivingSocket.receive(androidReceivingPacket);
            
            //take android ip
            androidIp = androidReceivingPacket.getAddress();
            
            //return data
            return androidReceivingPacket.getData();
        }catch (Exception e){
            System.out.println(e);
        }
        
        return null;
    }
    
    private void sendToAndroid(){
        try{
            //
            androidSendingPacket = new DatagramPacket(Ack, Ack.length, androidIp, androidSendingPort);
            
            //
            androidSendingSocket.send(androidSendingPacket);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    private void sendToGui(){
        try{
            byte[] data = new String(country + "." + city + ".").getBytes(); 
            System.out.println(new String(data).trim());
            
            //
            guiSendingPacket = new DatagramPacket(data, data.length, local, guiSendingPort);
            
            //
            guiSendingSocket.send(guiSendingPacket);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    private String parse(byte[] data){
        if(data == null) return "error";
        
        String msg = new String(data).trim();
        
        System.out.println(msg);
        return msg;
    }
}


/**
 * @author Joel Chin, Dalia Matar, Rami Daham
 * This class connects the database to the arduino serially
 */
class Listener extends Thread{
    
    private DatagramSocket guiSendingSocket;
    private DatagramPacket guiSendingPacket;
    private int guiSendingPort = 1027;
    private final int PACKET_SIZE = 512;
    private InetAddress local;
    public Listener(){
        try{
         guiSendingSocket = new DatagramSocket();
         local = InetAddress.getByName("localhost");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    private void sendToGui(char s){
        try{
            byte[] data ={(byte) s}; 

            guiSendingPacket = new DatagramPacket(data, data.length, local, guiSendingPort);
            
            //
            guiSendingSocket.send(guiSendingPacket);
        }catch (Exception e){
            System.out.println(e);
        }
    }
   
    public void run(){
        int sp = connect();
        char in;
        
        System.out.println("ard rdy " + sp);
        while(true){
            if(Serial.serialDataAvail(sp) > 0) {
                in = (char)Serial.serialGetchar(sp);
                System.out.println(in);
                
                sendToGui(in);
            }
        }
    }
    
    public void Stop(){
        
    }
    
    private int connect(){
        int sp;
        if((sp = Serial.serialOpen("/dev/ttyACM0", 9600)) >= 0) return sp;
        if((sp = Serial.serialOpen("/dev/ttyACM1", 9600)) >= 0) return sp;
        if((sp = Serial.serialOpen("/dev/ttyACM2", 9600)) >= 0) return sp;
        if((sp = Serial.serialOpen("/dev/ttyACM3", 9600)) >= 0) return sp;
        return -1;
    }
}

/**
 * @author Dalia Matar, Rami Daham
 * This class will send the alarm signal to the arduino serially
 */
class CalenderTime extends Thread {
    // instance variables - replace the example below with your own
    String alarm;

    /**
     * Constructor for objects of class CalenderTime
     */
    public CalenderTime(String alarm)
    {
        this.alarm = alarm;
    }
    
    public void run() {
        boolean condition = true;
        // initialise instance variables
        while(condition){
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int min = now.get(Calendar.MINUTE);
         
            String time = hour + ":" + min;
            
           // System.out.println(time);
            
            if(time.equals(alarm)){
                System.out.print("OMG ALARM TIME!!!!");
                condition = false;
                sendHigh();
            }
        }
    }
    
    private void sendHigh(){
        int sp = connect();
        Serial.serialPuts(sp, "1");
    }
    
    private int connect(){
        int sp;
        if((sp = Serial.serialOpen("/dev/ttyACM0", 9600)) >= 0) return sp;
        if((sp = Serial.serialOpen("/dev/ttyACM1", 9600)) >= 0) return sp;
        if((sp = Serial.serialOpen("/dev/ttyACM2", 9600)) >= 0) return sp;
        if((sp = Serial.serialOpen("/dev/ttyACM3", 9600)) >= 0) return sp;
        return -1;
    }
}
