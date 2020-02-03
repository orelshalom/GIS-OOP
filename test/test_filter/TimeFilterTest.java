package test_filter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import actions.ParseDate;
import filter.Filter;
import filter.TimeFilter;
import objects.SampleScan;
import objects.Wifi;

public class TimeFilterTest {

	@Test
	public void test() {
		ArrayList<Wifi> wfs1 = new ArrayList<>();
		wfs1.add(new Wifi("", "a0:d3:c1:da:42:1d", 0.0, -68));
		wfs1.add(new Wifi("", "7c:b7:33:a1:92:66", 0.0, -77));
		wfs1.add(new Wifi("", "ec:41:18:32:ff:73", 0.0, -43)); 
		
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(ParseDate.stringToDate("2019-12-12 15:51:12"));
		SampleScan sc1 = new SampleScan(gc1, "", null, wfs1);
		
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(ParseDate.stringToDate("2019-12-04 07:44:02"));
		SampleScan sc2 = new SampleScan(gc2, "", null, wfs1);
		
		GregorianCalendar gc3 = new GregorianCalendar();
		gc3.setTime(ParseDate.stringToDate("2019-12-07 00:52:32"));
		SampleScan sc3 = new SampleScan(gc3, "", null, wfs1);
		
		ArrayList<SampleScan> scs = new ArrayList<>();
		scs.add(sc1);
		scs.add(sc2);
		scs.add(sc3);
		
		GregorianCalendar beg = new GregorianCalendar(), end = new GregorianCalendar();
		beg.setTime(ParseDate.stringToDate("2019-12-06 16:18:12"));
		end.setTime(ParseDate.stringToDate("2019-12-08 08:30:09"));
		
		Filter f3 = new TimeFilter(beg, end);
		ArrayList<SampleScan> filter = f3.filter(scs);
		scs.remove(1);
		scs.remove(0);
		
		assertEquals(scs, filter);		
	}

}
