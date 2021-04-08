package gui;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppliRecettesRun {

	public static void main(String[] args) throws FileNotFoundException {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() 
			{
				
				MainFrame myFrame=new MainFrame();
			}
			
		});
		
		
		
	}

}
