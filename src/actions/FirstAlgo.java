package actions;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.MacInfo;
import objects.SampleScan;
import objects.Wifi;
import sort.SortMacsBySignal;

public class FirstAlgo {

	private static final int NUM_OF_SAMPLES = 4;
	
	
	public EarthCoordinate algo1(String mac, ArrayList<SampleScan> scs) {
		ArrayList<MacInfo> mis = getSamples(mac, scs);
		double sumLon = 0.0, sumLat = 0.0, sumAlt = 0.0, sumWeight = 0.0;
		
		for(MacInfo mi : mis){
			WCoordinate(mi);
			sumLon += mi.getLocation().getLongitude();
			sumLat += mi.getLocation().getLatitude();
			sumAlt += mi.getLocation().getAltitude();
			sumWeight += mi.getSignal();
		}
		return new EarthCoordinate(sumLon/sumWeight, sumLat/sumWeight, sumAlt/sumWeight);
	}
	

	private ArrayList<MacInfo> getSamples(String mac, ArrayList<SampleScan> scs) {
		ArrayList<MacInfo> mis = new ArrayList<>();
		for(SampleScan sc : scs){
			Wifi wf = sc.hasMac(mac);
			if(wf != null) mis.add(new MacInfo(sc.getLocation(), wf.getSignal()));
		}
		
		mis.sort(new SortMacsBySignal());
		while(mis.size() > NUM_OF_SAMPLES) mis.remove(mis.size()-1);
		return mis;
	}
	
	
	private MacInfo WCoordinate(MacInfo mi) {
		double weight = 1/Math.pow(mi.getSignal(),2);
		mi.setSignal(weight);
		mi.setLocation(new EarthCoordinate(mi.getLocation().getLongitude()*weight,
				mi.getLocation().getLatitude()*weight,
				mi.getLocation().getAltitude()*weight));
		return mi;
	}

}

	