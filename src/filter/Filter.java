package filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objects.SampleScan;
import objects.Wifi;

public abstract class Filter {

	public abstract ArrayList<SampleScan> filter(ArrayList<SampleScan> scs);
	
	public ArrayList<SampleScan> removeDupMac(ArrayList<SampleScan> scs){
		Map<String, Wifi> map = new HashMap<>();
		scs.forEach(sc -> 
			sc.getWifiArray().forEach(wifi -> {
				if (map.containsKey(wifi.getMac())) {
					if (wifi.getSignal() > map.get(wifi.getMac()).getSignal()) 
						map.put(wifi.getMac(), wifi);
				} 
				else map.put(wifi.getMac(), wifi);
		}));
		
		ArrayList<SampleScan> tmp =  (ArrayList<SampleScan>) scs.clone();
		tmp.forEach(sc -> sc.getWifiArray()
				.removeIf(wifi -> !wifi.equals(map.get(wifi.getMac()))));
		return tmp;
	}

}
