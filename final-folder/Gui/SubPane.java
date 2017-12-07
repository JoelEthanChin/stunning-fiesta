 

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class SubPane extends JPanel{
    
    /**
     * @author Joel Chin 101001146
     */
    private static JLabel lblComponent;
    public static final Font FONT = new Font("American Typewriter", Font.PLAIN, 60); //Font for every weather element
    public static final Font TEMP_FONT = new Font("American Typewriter", Font.PLAIN, 120); //Specific font to display temperature larger than others
    
    public SubPane(String pnlText, boolean isTemp){ //Constructor for Temperature
        
        super();
        this.lblComponent = new JLabel(pnlText);
        
        if(isTemp)
            this.getLblComponent().setFont(TEMP_FONT);
        else
            this.getLblComponent().setFont(FONT);
        
        this.add(getLblComponent(), BorderLayout.CENTER);
        
    }
    
    public SubPane(String pnlText){ //Constructor for every other info panel
        super();
        
        this.lblComponent = new JLabel(pnlText);
        this.getLblComponent().setFont(FONT);
        this.add(getLblComponent(), BorderLayout.CENTER);
        }
    //Setters and Getters
    public static JLabel getLblComponent() {
        return lblComponent;
    }
    
    public static void setLblComponent(JLabel l) {
        lblComponent = l;
    }
    
    public static void  updateText(String s){
         lblComponent.setText(s);
    }
}
