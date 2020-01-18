package actions;

import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.boehn.kmlframework.coordinates.EarthCoordinate;

import write.WriteCombo;


public class Format {
	
	private static final int HEADER_SIZE = 8;
	private static final int INNER_HEADER_SIZE = 11;
	private static final String[] WigleWifiHeader = {"WigleWifi-","appRelease=", "model=",
			"release=",	"device=", "display=", "board=", "brand="};
	private static final String[] WigleWifiHeader2 = {"MAC","SSID", "AuthMode", "FirstSeen",
			"Channel", "RSSI", "CurrentLatitude", "CurrentLongitude",
			"AltitudeMeters", "AccuracyMeters", "Type"};
	
	private static final double EARTHRADIUS = 6372795.477598; // in meters
	private static final double LONGITUDE_MAX = 180;
	private static final double LONGITUDE_MIN = -LONGITUDE_MAX;
	private static final double LATITUDE_MAX = 90;
	private static final double LATITUDE_MIN = -LATITUDE_MAX;

    
	public static boolean isWigleWifiFormat(List<CSVRecord> records) {
		if(records.get(0).size() != HEADER_SIZE) return false;
		if(records.get(1).size() != INNER_HEADER_SIZE) return false;
		int i = 0;
		for(String s : records.get(0)){
	    	if(!s.contains(WigleWifiHeader[i])) return false;
	    	i++;
	    }
		i = 0;
		for(String s : records.get(1)){
	    	if(!s.equals(WigleWifiHeader2[i])) return false;
	    	i++;
	    }
		return true;
	}

	
	public static boolean isComboFormat(List<CSVRecord> records) {
		if(records.get(0).size() != WriteCombo.getNewheader().length) return false;
		int i = 0;
		for(String s : records.get(0)){
	    	if(!s.equals(WriteCombo.getNewheader()[i])) return false;
	    	i++;
	    }
		return true;
	}
	

	public static boolean isLocationFormat(EarthCoordinate ec){
		if((LONGITUDE_MAX >= ec.getLongitude() && ec.getLongitude() >= LONGITUDE_MIN) && 
				(LATITUDE_MAX >= ec.getLatitude() && ec.getLatitude() >= LATITUDE_MIN) &&
				(LATITUDE_MAX >= ec.getLatitude() && ec.getLatitude() >= LATITUDE_MIN)) 
			return true;
		return false;
	}
	
	
	public static boolean isValidRadius(double radius){
		if(radius >= 0 && radius <= EARTHRADIUS) return true;
		return false;
	}
	
	
	public static boolean isSignalFormat(double signal){
		if(signal <= 0 && signal >= -130) return true;
		return false;	
	}

	
	public static boolean isFreqFormat(double freq){
		if (freq == 2400 || freq == 5000) return true;
		return false;
	}

	
	public static boolean isMacFormat(String mac){ 
		if(mac.length() != 17) return false;
		for(int i = 2; i<mac.length(); i+=3)
			if(mac.charAt(i) != ':') return false;
		for(int i = 0; i<mac.length(); i+=3){
			if(!(mac.charAt(i) >= 97 && mac.charAt(i) <= 122) ||
					!(mac.charAt(i) >= 48 && mac.charAt(i) <= 57)) return false;
			if(!(mac.charAt(i+1) >= 97 && mac.charAt(i+1) <= 122) ||
					!(mac.charAt(i+1) >= 48 && mac.charAt(i+1) <= 57)) return false;
		}
		return true;
	}
	
	
}
