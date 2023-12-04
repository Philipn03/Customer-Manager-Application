package labs.lab9;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;

public class CustomerManager{
	
	
	public static void main(String[] args) {
		// All information / functions in Information.java
		JFrame frame = new Information();
		
		// Set size of GUI
		frame.setSize(1100,700);
		frame.setTitle("Philip Nguyen - 57277528");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}