package filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objects.SampleScanCombo;
import objects.Wifi;

public abstract class Filter {

	public abstract ArrayList<SampleScanCombo> filter(ArrayList<SampleScanCombo> scs);
	
	public ArrayList<SampleScanCombo> removeDupMac(ArrayList<SampleScanCombo> scs){
		Map<String, Wifi> map = new HashMap<>();
		scs.forEach(sc -> 
			sc.getWifiArray().forEach(wifi -> {
				if (map.containsKey(wifi.getMac())) {
					if (wifi.getSignal() > map.get(wifi.getMac()).getSignal()) 
						map.put(wifi.getMac(), wifi);
				} 
				else map.put(wifi.getMac(), wifi);
		}));
		
		ArrayList<SampleScanCombo> tmp =  (ArrayList<SampleScanCombo>) scs.clone();
		tmp.forEach(sc -> sc.getWifiArray()
				.removeIf(wifi -> !wifi.equals(map.get(wifi.getMac()))));
		return tmp;
	}

}
