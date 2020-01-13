package write;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import objects.SampleScan;
import sort.SortByTime;

public class WriteCombo extends WriteFile {

	private static final String[] newHeader = {"Time", "ID", "Lat", "Lon", "Alt", "#WiFi networks",
			"SSID1", "MAC1", "Frequncy1", "Signal1",
			"SSID2", "MAC2", "Frequncy2", "Signal2",
			"SSID3", "MAC3", "Frequncy3", "Signal3",
			"SSID4", "MAC4", "Frequncy4", "Signal4",
			"SSID5", "MAC5", "Frequncy5", "Signal5",
			"SSID6", "MAC6", "Frequncy6", "Signal6",
			"SSID7", "MAC7", "Frequncy7", "Signal7",
			"SSID8", "MAC8", "Frequncy8", "Signal8",
			"SSID9", "MAC9", "Frequncy9", "Signal9",
			"SSID10", "MAC10", "Frequncy10", "Signal10"};
	
	
	/**
	 * @param name
	 * @param path 
	 * @param mat 
	 */
	public WriteCombo(String name, String path, ArrayList<SampleScan> mat) {
		super(name + ".csv", path + "/" , mat);
	}


	@Override
	public void write() {
        try {
    		BufferedWriter writer = Files.newBufferedWriter(Paths.get(getFolderPath()+ file_name), Charset.defaultCharset());
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(getNewheader()));
			
			Collections.sort(getMat(), new SortByTime());
			for(SampleScan sc : getMat()){
				csvPrinter.printRecord(sc.toStringCombo());
			}
			
            csvPrinter.flush();  
            writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public static String[] getNewheader() {
		return newHeader;
	}
}
