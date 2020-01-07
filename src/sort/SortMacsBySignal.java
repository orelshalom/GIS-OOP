package sort;

import java.util.Comparator;

import objects.MacInfo;
import objects.Wifi;

public class SortMacsBySignal implements Comparator<MacInfo> { 
	
	@Override
    public int compare(MacInfo a, MacInfo b) { 
        return Double.compare(Math.abs(a.getSignal()), Math.abs(b.getSignal())); 
    }

}
