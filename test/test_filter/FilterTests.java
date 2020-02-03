package test_filter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	IDFilterTest.class,
	LocationFilterTest.class,
	TimeFilterTest.class
})

public class FilterTests {}
