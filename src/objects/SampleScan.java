package objects;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import sort.SortBySignal;
import tools.ParseDate;


public class SampleScan {
	
	private GregorianCalendar time;
	private String id;
	private EarthCoordinate ec;
	private ArrayList<Wifi> wifiArray;
	
	
	/**
	 * @param time
	 * @param id
	 * @param ec
	 * @param wifiArray
	 */
	public SampleScan(GregorianCalendar time, String id, EarthCoordinate ec, ArrayList<Wifi> wifiArray) {
		super();
		this.time = time;
		this.id = id;
		this.ec = ec;
		this.wifiArray = wifiArray;
		toTenWifis();
	}

	
	public SampleScan(SampleScan sc) {
		this.time = sc.time;
		this.id = sc.id;
		this.ec = sc.ec;
		this.wifiArray = sc.wifiArray;
		toTenWifis();
	}


	public SampleScan() {}
	
	
	private void toTenWifis() {
		wifiArray.sort(new SortBySignal());
		if (wifiArray.size() > 10) wifiArray.subList(10, wifiArray.size()).clear();
	}
	
	
	public ArrayList<Wifi> getStrongerWifisByNum(int num) {
		if (num > wifiArray.size()) return wifiArray;
		ArrayList<Wifi> arr = new ArrayList<>();
		ArrayList<String> macs = new ArrayList<>();
		for(Wifi wf : wifiArray){
			if(!macs.contains(wf.getMac())){
				macs.add(wf.getMac());
				arr.add(wf);
			}
		}
		if(arr.size() > num) arr.subList(num, arr.size()).clear();
		return arr;
	}

	
	public ArrayList<String> toStringCombo() {
		ArrayList<String> row = new ArrayList<>();
		row.add(ParseDate.DateTostring(time)); 
		row.add(id);
		if(ec == null){
			row.add("null"); 
			row.add("null");
			row.add("null");
		} else {
			row.add(""+ec.getLatitude()); 
			row.add(""+ec.getLongitude());
			row.add(""+ec.getAltitude());
		}
		row.add(""+wifiArray.size());
		for(Wifi wf : wifiArray){
			row.add(wf.getId());
			row.add(wf.getMac());
			row.add((int)wf.getFrequency()+"");
			row.add((int)wf.getSignal()+"");
		}
		return row;
	}
	
	
	public ArrayList<String> toStringAlgo1() {
		ArrayList<String> row = new ArrayList<>();
		row.add(wifiArray.get(0).getMac());
		row.add(wifiArray.get(0).getId());
		row.add((int)wifiArray.get(0).getFrequency()+"");
		row.add((int)wifiArray.get(0).getSignal()+"");
		row.add(""+ec.getLatitude()); 
		row.add(""+ec.getLongitude());
		row.add(""+ec.getAltitude());
		row.add(ParseDate.DateTostring(time)); 
		return row;
	}


	public Wifi hasMac(String mac) {
		for (Wifi wifi : wifiArray) 
			if (wifi.getMac().equals(mac)) return wifi;
		return null;
	}
	
	
	
	public GregorianCalendar getTime() {
		return time;
	}

	public void setTime(GregorianCalendar time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EarthCoordinate getLocation() {
		return ec;
	}

	public void setLocation(EarthCoordinate ec) {
		this.ec = ec;
	}

	public ArrayList<Wifi> getWifiArray() {
		return wifiArray;
	}

	public void setWifiArray(ArrayList<Wifi> wifiArray) {
		this.wifiArray = wifiArray;
	}
	
}


