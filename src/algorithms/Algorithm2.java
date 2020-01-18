package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.DataAlgo2;
import objects.SampleScan;
import objects.Wifi;
import sort.SortByPi;


public class Algorithm2 extends Algo<SampleScan> { 

	private static final int POWER = 2;
	private static final int NORM = 10000;
	private static final double SIG_DIFF = 0.4;
	private static final int MIN_DIFF = 3;
	private static final int NO_SIGNAL = -120;
	private static final int DIFF_NO_SIG = 100;
	private static final int CHOSEN_SAMPLES = 4;
	private ArrayList<SampleScan> arrTest;
	
	
	/**
	 * @param scs
	 */
	public Algorithm2(ArrayList<SampleScan> scs, ArrayList<SampleScan> arrTest) {
		super(scs);
		this.arrTest = arrTest;
		toAlgoMap();
	}


	@Override
	protected Map<String, ArrayList<SampleScan>> toAlgoMap(){
		map = new HashMap<>(); 
		for(SampleScan sc : scs) {
			for(Wifi wf : sc.getWifiArray()){
				if(!map.containsKey(wf.getMac())){
					ArrayList<SampleScan> arr = new ArrayList<>();
					arr.add(sc);
					map.put(wf.getMac(), arr);
				}
				else map.get(wf.getMac()).add(sc);
			}
		}
		return map;
	}
	
	
	@Override
	public ArrayList<SampleScan> toAlgoMat() {
		algoMat = new ArrayList<>();
		for(SampleScan sc : arrTest)
			algoMat.add(new SampleScan(sc.getTime(), sc.getId(), algo2(sc, map), sc.getWifiArray()));
		return algoMat;
	}
	
	
	public EarthCoordinate algo2(SampleScan input, Map<String, ArrayList<SampleScan>> map) {
		Set<SampleScan> dataset = new HashSet<>();
		for(Wifi wf : input.getStrongerWifisByNum(NUM_OF_WIFIS)){
			if(map.containsKey(wf.getMac()))
				dataset.addAll(map.get(wf.getMac()));}
		
		if(dataset.size() == 0) return null;
		ArrayList<DataAlgo2> datalist = new ArrayList<>();
		for(SampleScan sc : dataset)
			datalist.add(new DataAlgo2(new SampleScan(sc), calculatePi(input, sc)));
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

	
	private double calculatePi(SampleScan input, SampleScan data) {
		double pi = 1.0;
		for(Wifi wf : input.getStrongerWifisByNum(NUM_OF_WIFIS))
			pi *= weight(wf, data.hasMac(wf.getMac()));
		return pi;
	}
	

	private double weight(Wifi input, Wifi data) {
		if(data == null) data = new Wifi("NONE", "NONE", 0, -120);
		return NORM / (Math.pow(diff(input, data), SIG_DIFF) * Math.pow(input.getSignal(), POWER)) ;
	}
	
	
	private double diff(Wifi input, Wifi data){
		return data.getSignal() == NO_SIGNAL ? DIFF_NO_SIG 
				: Math.max(Math.abs(input.getSignal()-data.getSignal()), MIN_DIFF);
	}
	
}
