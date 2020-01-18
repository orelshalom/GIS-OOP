package sort;

import java.util.Comparator;
import objects.MacInfo;

public class SortMacsBySignal implements Comparator<MacInfo> { 
	
	@Override
    public int compare(MacInfo a, MacInfo b) { 
        return Double.compare(Math.abs(a.getWifi().getSignal()), Math.abs(b.getWifi().getSignal())); 
    }

}
