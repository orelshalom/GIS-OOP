package write;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import objects.SampleScan;
import sort.SortByMac;


public class WriteAlgo extends WriteFile {

	private static final String[] algoHeader = {"Index", "Mac", "SSID", "Frequency", 
			"Signal", "Lat","Lon", "Alt", "Time"};
	
	
	public WriteAlgo(String name, String path, ArrayList<SampleScan> mat) {
		super(name + ".csv", path + "/", mat);
	}
	
	
	@Override
	public void write() {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(getFolderPath() + file_name), Charset.defaultCharset());
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(algoHeader));
			
			Collections.sort(getMat(), new SortByMac());
		    int index = 0;
			for(SampleScan sc : getMat()){
				csvPrinter.print(index++);
				csvPrinter.printRecord(sc.toStringAlgo());
			}
			
            csvPrinter.flush();  
            writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
