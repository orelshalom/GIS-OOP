package test_read;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ 
	ReadFolderTest.class,
	ReadWigleWifiTest.class, 
	ReadComboTest.class 
})

public class ReadTests {

}
