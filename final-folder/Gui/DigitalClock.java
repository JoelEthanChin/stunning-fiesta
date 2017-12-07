import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.IOException;
/**
 * @author Joel Chin, Dalia Matar, Rami Daham
 * 
 * This class implements the date and time functions for the Smart Clock
 * This class also sends and receives information to get location specific
 * weather information
 */
public class DigitalClock {
    private static final int PACKETSIZE = 512;
    private static DatagramSocket sr;
    private static DatagramPacket p;
    public static void main(String[] arguments) {

        ClockLabel dateLabel = new ClockLabel("date");
        ClockLabel timeLabel = new ClockLabel("time");

        String country = "", city = "";
        try{
            sr = new DatagramSocket(1027);
            p = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
        }catch (IOException e){

            e.printStackTrace();

        }
        

        try{//try block to get country and city information
            sr.receive(p);
            byte[] data = p.getData();
            int parse = 0;
            
            char input;
            //for loop to iterate through received byte arra
            for(int i = 0; i < PACKETSIZE; i++){
                input = (char) data[i];
                if(input == '.'){
                    parse++;
                }

                if(parse == 0 && input != '.'){
                    country += input;

                }

                if(parse == 1 && input != '.'){
                    city += input;

                }
            }
        }catch (IOException e){

            e.printStackTrace();

        }

        JPanel mainPane, leftPane, rightPane, dateTimePane, weatherPane;

        //create weather information
        weatherPane = new WeatherPane(country, city, "Metric");

        mainPane = new JPanel(new BorderLayout());
        leftPane = new JPanel(new BorderLayout());
        rightPane = new JPanel(new BorderLayout());

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame f = new JFrame("Digital Clock");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(1, 2));
        
        leftPane.add(timeLabel, BorderLayout.NORTH);
        leftPane.add(dateLabel, BorderLayout.SOUTH);

        rightPane.add(weatherPane, BorderLayout.CENTER);
        f.add(leftPane);
        f.add(rightPane);
        f.setSize(600, 150);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        f.setVisible(true);
        
        String s="";

        while(true){
            try{
                p = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
                sr.receive(p);
                s = new String(p.getData()).trim();
            }catch (IOException e){

                 e.printStackTrace();

            } 
            
            if( s.equals("L")){
                
                f.setVisible(true);
                
            }else if(s.equals("N")){
               
                f.setVisible(false);

            }
        }
    }
}

class ClockLabel extends JLabel implements ActionListener {

    String type;
    SimpleDateFormat sdf;

    public ClockLabel(String type) {
        this.type = type;
        //create the format for the specified information
        switch (type) {
            case "date" : sdf = new SimpleDateFormat("  MMM dd yyyy");
            setFont(new Font("American Typewriter", Font.PLAIN, 80));
            setHorizontalAlignment(SwingConstants.CENTER);
            break;
            case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
            setFont(new Font("American Typewritter", Font.PLAIN, 80));
            setHorizontalAlignment(SwingConstants.CENTER);
            break;
            default     : sdf = new SimpleDateFormat();
            break;
        }

        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        setText(sdf.format(d));
    }
}