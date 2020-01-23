package test_sort;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import objects.MacInfo;
import objects.Wifi;
import sort.SortMacsBySignal;

public class SortMacsBySignalTest {

	@Test
	public void test() {
		MacInfo mi1 = new MacInfo(null, null, null, new Wifi("", null, 0, -78));
		MacInfo mi2 = new MacInfo(null, null, null, new Wifi("", null, 0, -120));
		MacInfo mi3 = new MacInfo(null, null, null, new Wifi("", null, 0, -24));

		ArrayList<MacInfo> mis = new ArrayList<>();
		mis.add(mi1);
		mis.add(mi2);
		mis.add(mi3);
		mis.sort(new SortMacsBySignal());
		
		assertSame(mi3, mis.get(0));
		assertSame(mi1, mis.get(1));
		assertSame(mi2, mis.get(2));
	}

}
