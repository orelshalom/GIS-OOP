package test_sort;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import objects.SampleScan;
import objects.Wifi;
import sort.SortByMac;

public class SortByMacTest {

	@Test
	public void test() {
		ArrayList<Wifi> wfs = new ArrayList<>();
		wfs.add(new Wifi("", "be:4e:26:b8:30:88", 0.0, 0.0));
		
		ArrayList<Wifi> wfs2 = new ArrayList<>();
		wfs2.add(new Wifi("", "7c:b7:33:a1:92:66", 0.0, 0.0));
		
		ArrayList<Wifi> wfs3 = new ArrayList<>();
		wfs3.add(new Wifi("", "a0:d3:c1:da:42:1d", 0.0, 0.0));

		SampleScan sc1 = new SampleScan(null, "", null, wfs);
		SampleScan sc2 = new SampleScan(null, "", null, wfs2);
		SampleScan sc3 = new SampleScan(null, "", null, wfs3);
		
		ArrayList<SampleScan> scs = new ArrayList<>();
		scs.add(sc1);
		scs.add(sc2);
		scs.add(sc3);
		scs.sort(new SortByMac());
		
		assertSame(sc2, scs.get(0));
		assertSame(sc3, scs.get(1));
		assertSame(sc1, scs.get(2));
	}

}
