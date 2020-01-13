package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.DataAlgo2;
import objects.MacInfo;
import objects.SampleScanCombo;
import objects.Wifi;
import sort.SortByPi;
import sort.SortMacsBySignal;


public class SecondAlgo {

	private static final int POWER = 2;
	private static final int NORM = 10000;
	private static final double SIG_DIFF = 0.4;
	private static final int MIN_DIFF = 3;
	private static final int NO_SIGNAL = -120;
	private static final int DIFF_NO_SIG = 100;
	private static final int NUM_OF_WIFIS = 4;
	private static final int CHOSEN_SAMPLES = 4;

	private static Map<String, ArrayList<SampleScanCombo>> map;
	private static ArrayList<SampleScanCombo> scs;
	
	
	/**
	 * @param scs
	 */
	public SecondAlgo(ArrayList<SampleScanCombo> scs) {
		super();
		this.scs = scs;
		toSamplesMap();
	}


	private Map<String, ArrayList<SampleScanCombo>> toSamplesMap(){
		map = new HashMap<>(); 
		for(SampleScanCombo sc : scs) {
			for(Wifi wf : sc.getWifiArray()){
				if(!map.containsKey(wf.getMac())){
					ArrayList<SampleScanCombo> arr = new ArrayList<>();
					arr.add(sc);
					map.put(wf.getMac(), arr);
				}
				else map.get(wf.getMac()).add(sc);
			}
		}
		return map;
	}
	
	
	public EarthCoordinate algo2(SampleScanCombo input, Map<String, ArrayList<SampleScanCombo>> map) {
		Set<SampleScanCombo> dataset = new HashSet<>();
		for(Wifi wf : input.getStrongerWifisByNum(NUM_OF_WIFIS))
			dataset.addAll(map.get(wf.getMac()));
			
		ArrayList<DataAlgo2> datalist = new ArrayList<>();
		for(SampleScanCombo sc : dataset)
			datalist.add(new DataAlgo2(new SampleScanCombo(sc), calculatePi(input, sc)));
		datalist = getSamples(datalist);
		
		double sumLon = 0.0, sumLat = 0.0, sumAlt = 0.0, sumWeight = 0.0;
		for(DataAlgo2 da : datalist){
			da = WCoordinate(da);
			sumLon += da.getSc().getLocation().getLongitude();
			sumLat += da.getSc().getLocation().getLatitude();
			sumAlt += da.getSc().getLocation().getAltitude();
			sumWeight += da.getPi();
		}
		return new EarthCoordinate(sumLon/sumWeight, sumLat/sumWeight, sumAlt/sumWeight);
	}
	
	private double calculatePi(SampleScanCombo input, SampleScanCombo data) {
		double pi = 1.0;
		for(Wifi wf : input.getStrongerWifisByNum(NUM_OF_WIFIS))
			pi *= weight(wf, data.hasMac(wf.getMac()));
		return pi;
	}
	
	
	private ArrayList<DataAlgo2> getSamples(ArrayList<DataAlgo2> das) {
		das.sort(new SortByPi());
		if(das.size() > CHOSEN_SAMPLES)
			das.subList(CHOSEN_SAMPLES, das.size()).clear();
		return das;
	}
	
		
	private DataAlgo2 WCoordinate(DataAlgo2 da) {
		da.getSc().setLocation(new EarthCoordinate(da.getSc().getLocation().getLongitude()*da.getPi(),
				da.getSc().getLocation().getLatitude()*da.getPi(),
				da.getSc().getLocation().getAltitude()*da.getPi()));
		return da;		
	}


	private double weight(Wifi input, Wifi data) {
		if(data == null) data = new Wifi("NONE", "NONE", 0, -120);
		return NORM / (Math.pow(diff(input, data), SIG_DIFF) * Math.pow(input.getSignal(), POWER)) ;
	}
	
	
	private double diff(Wifi input, Wifi data){
		return data.getSignal() == NO_SIGNAL ? DIFF_NO_SIG 
				: Math.max(Math.abs(input.getSignal()-data.getSignal()), MIN_DIFF);
	}
	
	
	public Map<String, ArrayList<SampleScanCombo>> getMap() {
		return map;
	}

}
