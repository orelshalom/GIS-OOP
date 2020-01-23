package test_sort;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import objects.Wifi;
import sort.SortBySignal;

public class SortBySignalTest {

	@Test
	public void test() {
		ArrayList<Wifi> wfs = new ArrayList<>();
		wfs.add(new Wifi("", "be:4e:26:b8:30:88", 0.0, -68));
		wfs.add(new Wifi("", "7c:b7:33:a1:92:66", 0.0, -90));
		wfs.add(new Wifi("", "a0:d3:c1:da:42:1d", 0.0, -43));
		
		wfs.sort(new SortBySignal());
		
		assertTrue(wfs.get(0).getSignal() == -43);
		assertTrue(wfs.get(1).getSignal() == -68);
		assertTrue(wfs.get(2).getSignal() == -90);
	}

}
