package test_read;

import static org.junit.Assert.*;
import org.junit.Test;
import read.ReadFolder;

public class ReadFolderTest {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";
	private static final String WRONG_PATH = "C:/Users/OREL/Downloads";
	
	
	@Test
	public void testFilesOfGoodPath() {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		
		assertTrue(rfo.getCsv_files().size() == 3);
		for(String[][] csv : rfo.getCsv_files()){
			assertNotNull(csv);
			assertTrue(csv.length > 2);
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testWrongPath() {
		ReadFolder rfo = new ReadFolder(WRONG_PATH);
		rfo.read();
	}

}
