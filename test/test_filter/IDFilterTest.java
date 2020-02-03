package test_filter;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import filter.Filter;
import filter.IDFilter;
import objects.SampleScan;
import objects.Wifi;

public class IDFilterTest {

	@Test
	public void test() {
		ArrayList<Wifi> wfs1 = new ArrayList<>();
		wfs1.add(new Wifi("", "a0:d3:c1:da:42:1d", 0.0, -68));
		wfs1.add(new Wifi("", "7c:b7:33:a1:92:66", 0.0, -77));
		wfs1.add(new Wifi("", "ec:41:18:32:ff:73", 0.0, -43));
		
		ArrayList<Wifi> wfs2 = new ArrayList<>();
		wfs2.add(new Wifi("", "be:4e:26:b8:30:88", 0.0, -100));
		wfs2.add(new Wifi("", "7c:b7:33:a1:92:66", 0.0, -86));
		wfs2.add(new Wifi("", "a0:d3:c1:da:42:1d", 0.0, -33));
		
		SampleScan sc1 = new SampleScan(null, "ABC", null, wfs1);
		SampleScan sc2 = new SampleScan(null, "ABC", null, wfs2);
		SampleScan sc3 = new SampleScan(null, "DEF", null, wfs2);

		ArrayList<SampleScan> scs = new ArrayList<>();
		scs.add(sc1);
		scs.add(sc2);
		scs.add(sc3);
		
		Filter f1 = new IDFilter("ABC");
		ArrayList<SampleScan> filter = f1.filter(scs);
		
		scs.remove(2);
		scs.get(0).getWifiArray().remove(0);
		scs.get(1).getWifiArray().remove(1);
		
		assertEquals(scs, filter);
	}

}
