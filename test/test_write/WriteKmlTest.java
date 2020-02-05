package test_write;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import org.junit.Test;
import actions.CastFilesToCombo;
import objects.SampleScan;
import read.ReadFolder;
import write.WriteKml;

public class WriteKmlTest {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";

	@Test
	public void test() {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		CastFilesToCombo mer = new CastFilesToCombo(rfo.getCsv_files());
		
		try {
			ArrayList<SampleScan> scs = mer.toSampleScansCombo();
			WriteKml wk = new WriteKml("map", FOLDER_PATH, scs);
			wk.write();
			
			String map = new String(Files.readAllBytes(Paths.get(FOLDER_PATH + "/map.kml")));
			assertTrue(wk.getKml().toString().equals(map));
			
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

}
