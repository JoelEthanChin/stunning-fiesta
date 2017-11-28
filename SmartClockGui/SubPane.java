package database;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class SubPane extends JPanel{
	
	private JLabel lblComponent;
	public static final Font FONT = new Font("American Typewriter", Font.PLAIN, 60);
	public static final Font TEMP_FONT = new Font("American Typewriter", Font.PLAIN, 120);
	
	public SubPane(String pnlText, boolean isTemp){
		
		super();
		this.lblComponent = new JLabel(pnlText);
		
		if(isTemp)
			this.getLblComponent().setFont(TEMP_FONT);
		else
			this.getLblComponent().setFont(FONT);
		
		this.add(getLblComponent(), BorderLayout.CENTER);
		
	}


	public JLabel getLblComponent() {
		return lblComponent;
	}


	public void setLblComponent(JLabel lblComponent) {
		this.lblComponent = lblComponent;
	}

}
