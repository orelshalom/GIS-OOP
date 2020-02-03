package test_filter;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;
import filter.Filter;
import filter.LocationFilter;
import objects.SampleScan;
import objects.Wifi;

public class LocationFilterTest {

	@Test
	public void test() {
		ArrayList<Wifi> wfs1 = new ArrayList<>();
		wfs1.add(new Wifi("", "a0:d3:c1:da:42:1d", 0.0, -68));
		wfs1.add(new Wifi("", "7c:b7:33:a1:92:66", 0.0, -77));
		wfs1.add(new Wifi("", "ec:41:18:32:ff:73", 0.0, -43)); 
		
		EarthCoordinate ec1 = new EarthCoordinate(34.95402937, 32.08703503, 100.16119384765625); // 31
		EarthCoordinate ec2 = new EarthCoordinate(34.95362183, 32.08760946, 86.16229248046875); // 96
		EarthCoordinate ec3 = new EarthCoordinate(34.95408403, 32.08702244, 120.093994140625); // 45
		
		SampleScan sc1 = new SampleScan(null, "", ec1, wfs1);
		SampleScan sc2 = new SampleScan(null, "", ec2, wfs1);
		SampleScan sc3 = new SampleScan(null, "", ec3, wfs1);

		ArrayList<SampleScan> scs = new ArrayList<>();
		scs.add(sc1);
		scs.add(sc2);
		scs.add(sc3);
		
		EarthCoordinate center = new EarthCoordinate(34.95398122, 32.08680173, 82.4484252929687);
		Filter f2 = new LocationFilter(center, 70);		
		ArrayList<SampleScan> filter = f2.filter(scs);
		scs.remove(1);
		
		assertEquals(scs, filter);
	}

}
