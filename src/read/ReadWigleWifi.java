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

	private String[][] matrix;
	
	
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
	        for(CSVRecord re : records) {
	           	if(!re.get(0).contains("WigleWifi") && i==0) return;
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
	        parser.close();
	        reader.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	public String[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}
	
}
