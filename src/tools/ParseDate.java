package tools;

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

	    try {
	        gc.setTime(out.parse(input));
	    } catch (ParseException pe) {
	        try {
		        gc.setTime(fmt1.parse(input));
	        } catch (ParseException pe2) {
	            try {
	            	gc.setTime(fmt2.parse(input));
	            } catch (ParseException pe3) {
	            	try {
	    		        gc.setTime(fmt3.parse(input));
	            	} catch (ParseException pe4) {
	            		pe3.printStackTrace();
	            	}
	            }
	        }
	    }
		return gc.getTime();
	}
	
}
