package test_read;

import static org.junit.Assert.*;
import org.junit.Test;
import read.ReadWigleWifi;

public class ReadWigleWifiTest {

	private static final String GOOD_FILE = "C:/Users/OREL SHALOM/Downloads/WigleWifi/WigleWifi_20191207181838.csv";
	private static final String WORNG_PATH = "C:/Users/OREL SHALOM/Downloads/WigleWifi/WiglWifi_20191207181838";
	private static final String WORNG_FILE = "C:/Users/OREL SHALOM/Downloads/WigleWifi/WigleWifiTest.csv";
	
	private static final String[] WigleWifiHeader = {"WigleWifi-","appRelease=", "model=",
			"release=",	"device=", "display=", "board=", "brand="};
	private static final String[] WigleWifiHeader2 = {"MAC","SSID", "AuthMode", "FirstSeen",
			"Channel", "RSSI", "CurrentLatitude", "CurrentLongitude",
			"AltitudeMeters", "AccuracyMeters", "Type"};


	@Test
	public void testGoodFile() {
		ReadWigleWifi rww = new ReadWigleWifi(GOOD_FILE);
		rww.read();
		
		assertNotNull(rww.getMatrix());
		assertTrue(rww.getFile_path().endsWith(".csv"));
		assertTrue(rww.getMatrix().length > 2);
		
		for(int i = 0; i < WigleWifiHeader.length; i++)
			assertTrue(rww.getMatrix()[0][i].contains(WigleWifiHeader[i]));
		assertArrayEquals(WigleWifiHeader2, rww.getMatrix()[1]);
	}
	
	
	@Test
	public void testWrongPath() {
		ReadWigleWifi rww2 = new ReadWigleWifi(WORNG_PATH);
		rww2.read();
		
		assertFalse(rww2.getFile_path().endsWith(".csv"));
		assertNull(rww2.getMatrix());
	}
	
	
	@Test
	public void testWrongFile() {
		ReadWigleWifi rww3 = new ReadWigleWifi(WORNG_FILE);
		rww3.read();
		
		assertTrue(rww3.getFile_path().endsWith(".csv"));
		assertNull(rww3.getMatrix());
	}
	
}
