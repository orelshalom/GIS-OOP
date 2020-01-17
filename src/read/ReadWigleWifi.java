package read;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ReadWigleWifi extends ReadFile {
	
	private static final int HEADER_SIZE = 8;
	private static final int INNER_HEADER_SIZE = 11;
	private static final String[] WigleWifiHeader = {"WigleWifi-","appRelease=", "model=",
			"release=",	"device=", "display=", "board=", "brand="};
	private static final String[] WigleWifiHeader2 = {"MAC","SSID", "AuthMode", "FirstSeen",
			"Channel", "RSSI", "CurrentLatitude", "CurrentLongitude",
			"AltitudeMeters", "AccuracyMeters", "Type"};
											

	public ReadWigleWifi(String path) {
		super(path);
	}
	
	
	@Override
	public void read() {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(getFile_path()), Charset.defaultCharset());
			CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
	 
	        List<CSVRecord> records = parser.getRecords();
	    	String[][] tmp = new String[records.size()][11];
	    	int i = 0, j = 0;
	    	
	    	if(goodFormat(records)){
		        for(CSVRecord re : records) {
		        	Iterator<String> iterator = re.iterator();
		        	j=0;
		            while (iterator.hasNext()) {
		            	String column = iterator.next();
		            	tmp[i][j] = column;
		            	j++;
		            }
		            i++;
		        }
		        matrix = tmp;
	    	}
	        parser.close();
	        reader.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


	@Override
	protected boolean goodFormat(List<CSVRecord> records) {
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
	
}
