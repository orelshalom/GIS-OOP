package test_read;

import static org.junit.Assert.*;

import org.junit.Test;

import read.ReadCombo;

public class ReadComboTest {

	@Test
	public void testComboFiles() {
		
		ReadCombo rc = new ReadCombo("C:/Users/OREL SHALOM/Downloads/Output/combo.csv");
		rc.read();
		
		assertTrue(rc.getFile_path().endsWith(".csv"));
		assertNotNull(rc.getMatrix());
		assertTrue(rc.getMatrix().length >= 1);
		
		ReadCombo rcTest = new ReadCombo("C:/Users/OREL SHALOM/Downloads/_comb_no_gps_ts1.csv");
		rcTest.read();
		assertNull(rcTest.getMatrix());
		
	}

}
