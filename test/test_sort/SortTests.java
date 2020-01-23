package test_sort;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	SortByTimeTest.class,
	SortByMacTest.class,
	SortBySignalTest.class,
	SortByPiTest.class,
	SortMacsBySignalTest.class
})

public class SortTests {

}
