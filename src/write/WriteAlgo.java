package write;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public abstract class WriteAlgo implements Write {

	private String file_path;
	private static final String[] algoHeader = {"Index", "Mac", "SSID", "#Wifis",
			"Frequency", "Signal", "Lat","Lon", "Alt", "Time"};
	
	
	@Override
	public void write() {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(file_path), Charset.defaultCharset());
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(algoHeader));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
