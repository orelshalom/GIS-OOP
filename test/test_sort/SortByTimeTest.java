package test_sort;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Test;
import actions.ParseDate;
import objects.SampleScan;
import sort.SortByTime;

public class SortByTimeTest {

	@Test
	public void test() {
		String date = "2019-12-06 22:56:03";
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(ParseDate.stringToDate(date));
		
		date = "2019-10-25 20:40:03";
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(ParseDate.stringToDate(date));
		
		date = "2018-11-04 23:15:22";
		GregorianCalendar gc3 = new GregorianCalendar();
		gc3.setTime(ParseDate.stringToDate(date));
		
		SampleScan sc1 = new SampleScan(gc1, "", null, null);
		SampleScan sc2 = new SampleScan(gc2, "", null, null);
		SampleScan sc3 = new SampleScan(gc3, "", null, null);

		ArrayList<SampleScan> scs = new ArrayList<>();
		scs.add(sc2);
		scs.add(sc1);
		scs.add(sc3);
		scs.sort(new SortByTime());
		
		assertSame(sc3, scs.get(0));
		assertSame(sc2, scs.get(1));
		assertSame(sc1, scs.get(2));
	}

}
