package actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParseDate {

	GregorianCalendar gc;

	
	/**
	 * @param gc
	 */
	public ParseDate(GregorianCalendar gc) {
		super();
		this.gc = gc;
	}
	
	
	public static Date stringToDate(String input) {
		input = input.replace('/', '-');
		GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat fmt1 = new SimpleDateFormat("dd-MM-yy HH:mm");
	    SimpleDateFormat fmt2 = new SimpleDateFormat("dd-MM-yy HH:mm aa");
	    SimpleDateFormat fmt3 = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

	    try {gc.setTime(out.parse(input));}
	    catch (ParseException pe) {
	        try {gc.setTime(fmt1.parse(input));}
	        catch (ParseException pe2) {
	        	try {gc.setTime(fmt2.parse(input));} 
	        	catch (ParseException pe3) {
	            	try {gc.setTime(fmt3.parse(input));}
	            	catch (ParseException pe4) {pe4.getMessage();}
	            }
	        }
	    }
		return gc.getTime();
	}
	
	
	public static String DateTostring(GregorianCalendar gc) {
		String date = "";
	    SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat fmt1 = new SimpleDateFormat("dd-MM-yy HH:mm");
	    SimpleDateFormat fmt2 = new SimpleDateFormat("dd-MM-yy HH:mm aa");
	    SimpleDateFormat fmt3 = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	    
	    try {date = out.format(gc.getTime());}
	    catch (Exception e) {
	    	try {date = fmt1.format(gc.getTime());} 
	    	catch (Exception e2) {
	    		try {date = fmt2.format(gc.getTime());} 
	    		catch (Exception e3) {
	    			try {date = fmt3.format(gc.getTime());} 
	    			catch (Exception e4) {e4.getMessage();}
	    		}
	    	}
 		}
	    return date;
	}
	
}
