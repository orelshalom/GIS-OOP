package test_write;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.ArrayList;
import org.junit.Test;
import actions.Cast;
import actions.CastFilesToCombo;
import objects.SampleScan;
import read.ReadCombo;
import read.ReadFolder;
import write.WriteCombo;
import write.WriteFile;

public class WriteComboTest {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";

	@Test
	public void test() {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		CastFilesToCombo mer = new CastFilesToCombo(rfo.getCsv_files());
		
		try {
			ArrayList<SampleScan> scs = mer.toSampleScansCombo();
			WriteFile wf = new WriteCombo("combo", FOLDER_PATH, scs);
			wf.write();
			
			ReadCombo rc = new ReadCombo("C:/Users/OREL SHALOM/Downloads/combo.csv");
			rc.read();
			ArrayList<SampleScan> arr = Cast.CombotoSamples(Cast.matToArrayList(rc.getMatrix()));
			
			assertTrue(arr.size() == wf.getMat().size());
			for(int i = 0; i<wf.getMat().size(); i++)
				assertTrue(wf.getMat().get(i).toStringCombo().equals(arr.get(i).toStringCombo()));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
