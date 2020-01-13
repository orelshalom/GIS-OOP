package sort;

import java.util.Comparator;
import objects.SampleScan;

public class SortByMac implements Comparator<SampleScan> {

	@Override
	public int compare(SampleScan a, SampleScan b) {
		return a.getWifiArray().get(0).getMac().compareTo(b.getWifiArray().get(0).getMac());
	}

}
