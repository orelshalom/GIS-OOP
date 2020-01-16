package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.MacInfo;
import objects.SampleScan;
import objects.Wifi;
import sort.SortMacsBySignal;

public class Algorithm1 extends Algo<MacInfo> { 
	
		
	/**
	 * @param macs
	 * @param scs
	 */
	public Algorithm1(ArrayList<SampleScan> scs) {
		super(scs);
		toAlgoMap();
	}

	
	protected Map<String, ArrayList<MacInfo>> toAlgoMap(){
		map = new HashMap<>(); 
		for(SampleScan sc : scs) {
			for(Wifi wf : sc.getWifiArray()){
				if(!map.containsKey(wf.getMac())){
					ArrayList<MacInfo> mis = new ArrayList<>();
					mis.add(new MacInfo(sc.getTime(), sc.getId(), sc.getLocation(), wf));
					map.put(wf.getMac(), mis);
				}
				else map.get(wf.getMac()).add(new MacInfo(sc.getTime(), sc.getId(), sc.getLocation(), wf));
			}
		}
		return map;
	}
	
	
	@Override
	public ArrayList<SampleScan> toAlgoMat() {
		algoMat = new ArrayList<>();
		ArrayList<Wifi> wfs;

		for(Entry<String, ArrayList<MacInfo>> entry : map.entrySet()){
			ArrayList<MacInfo> mi = entry.getValue();
			mi.sort(new SortMacsBySignal());
			map.put(entry.getKey(), mi);
			
			wfs = new ArrayList<>();
			Wifi wf = new Wifi(mi.get(0).getWifi());
			wfs.add(wf);
			algoMat.add(new SampleScan(mi.get(0).getTime(), "", algo1(entry.getKey()), wfs));
		}
		return algoMat;
	}
	
	
	private EarthCoordinate algo1(String mac) {
		ArrayList<MacInfo> mis = getSamples(map.get(mac));
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

}

	