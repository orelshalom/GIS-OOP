package actions;

import java.io.IOException;


public class OpenFile {

	
	public static void openFile(String file_path) {
		try {
		    Process p;
			if(file_path.endsWith(".csv"))
		    	p = Runtime.getRuntime().exec("C:/Program Files/Microsoft Office/root/Office16/EXCEL.EXE" +
			                                   " " + "\""+file_path);
		    else if(file_path.endsWith(".kml"))
		    	p = Runtime.getRuntime().exec("C:/Program Files/Google/Google Earth Pro/client/googleearth.exe" +
		                                   " " + "\""+file_path);
		} 
		catch (IOException e) {
		    e.printStackTrace();
		} 
	}
    
}
