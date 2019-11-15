package javaapplication1;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class intro extends JFrame{
	// creation de progress bar 
	private Thread t;
	private JProgressBar bar;
    private JLabel label = new JLabel ("Lancement du logiciel");
	
	 public intro()
	 {
		 this.setSize(300,80); 
		 this.setLocationRelativeTo(null);
		 this.setUndecorated(true);
		 
		 Font POLICE = new Font("Arial" ,Font.BOLD, 25 );	
		 label.setFont(POLICE); 
		 t = new Thread(new Traitement()); 
		 bar = new JProgressBar();
		 bar.setMaximum(500);
		 bar.setMinimum(0);
		 bar.setStringPainted(true);
		 
		 this.getContentPane().add(bar, BorderLayout.SOUTH);
		 this.getContentPane().add(label, BorderLayout.CENTER);
		 t.start();
		 this.setVisible(true);
		 
	 }
	 class Traitement implements Runnable
	 {
		 public void run()
		 {
			 for(int val = 0; val <= 500; val++)
			 {
				 bar.setValue(val);
				 try
				 {
					 t.sleep(10);
				 }catch (InterruptedException e){e.printStackTrace(); }
			 }		      
			 
		 }
	 }
}
