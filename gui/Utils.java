package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Utils {
   public static String getFileExtension(String name)
   {
	 int pointIndx=name.lastIndexOf(".");
	 if(pointIndx==-1)
	   {return null;}
	 if(pointIndx==name.length()-1)
	 {return null;}
	 
	return name.substring(pointIndx+1, name.length());  
   }
   
   public static String getFileName(String abspath)
   {
	 	int debutIndx=abspath.lastIndexOf('\\')+1;
		int finIndx=abspath.length();
		return abspath.substring(debutIndx, finIndx);
   }
   public static void openFile(String path) throws IOException
   {
	   Desktop desk = Desktop.getDesktop();
		
	   desk.open(new File(path));
	    
	   try {
			desk.open(new File(path));
		} catch (IllegalArgumentException e) {
			
			JOptionPane.showMessageDialog(null,"On ne trouve pas "
					+ "fichier à ce nom", "Erreur",JOptionPane.ERROR_MESSAGE);
			
		}
   }
}
