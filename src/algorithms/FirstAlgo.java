package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.MacInfo;
import objects.SampleScanCombo;
import objects.Wifi;
import sort.SortMacsBySignal;

public class FirstAlgo {

	private static final int NUM_OF_WIFIS = 4;
	private Map<String, ArrayList<MacInfo>> macs;
	private ArrayList<SampleScanCombo> scs;
	
		
	/**
	 * @param macs
	 * @param scs
	 */
	public FirstAlgo(ArrayList<SampleScanCombo> scs) {
		super();
		this.scs = scs;
		toMacsMap();
	}

	
	private Map<String, ArrayList<MacInfo>> toMacsMap(){
		macs = new HashMap<>(); 
		for(SampleScanCombo sc : scs) {
			for(Wifi wf : sc.getWifiArray()){
				if(!macs.containsKey(wf.getMac())){
					ArrayList<MacInfo> mis = new ArrayList<>();
					mis.add(new MacInfo(sc.getTime(), sc.getId(), sc.getLocation(), wf));
					macs.put(wf.getMac(), mis);
				}
				else macs.get(wf.getMac()).add(new MacInfo(sc.getTime(), sc.getId(), sc.getLocation(), wf));
			}
		}
		for(Entry<String, ArrayList<MacInfo>> entry : macs.entrySet())
			entry.getValue().sort(new SortMacsBySignal());
		return macs;
	}
	
	
	public EarthCoordinate algo1(String mac) {
		ArrayList<MacInfo> mis = getSamples(macs.get(mac));
		double sumLon = 0.0, sumLat = 0.0, sumAlt = 0.0, sumWeight = 0.0;
		
		for(MacInfo mi : mis){
			mi = WCoordinate(mi);
			sumLon += mi.getLocation().getLongitude();
			sumLat += mi.getLocation().getLatitude();
			sumAlt += mi.getLocation().getAltitude();
			sumWeight += mi.getWifi().getSignal();
		}
		return new EarthCoordinate(sumLon/sumWeight, sumLat/sumWeight, sumAlt/sumWeight);
	}
	
	
	private ArrayList<MacInfo> getSamples(ArrayList<MacInfo> mis) {
		mis.sort(new SortMacsBySignal());
		if(mis.size() > NUM_OF_WIFIS) mis.subList(NUM_OF_WIFIS, mis.size()).clear();
		return mis;
	}
	
	
	private MacInfo WCoordinate(MacInfo mi) {
		double weight = 1/Math.pow(mi.getWifi().getSignal(),2);
		mi.getWifi().setSignal(weight);
		mi.setLocation(new EarthCoordinate(mi.getLocation().getLongitude()*weight,
				mi.getLocation().getLatitude()*weight,
				mi.getLocation().getAltitude()*weight));
		return mi;
	}


	public Map<String, ArrayList<MacInfo>> getMacs() {
		return macs;
	}

}

	