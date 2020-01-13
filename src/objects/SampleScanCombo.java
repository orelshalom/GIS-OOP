package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import sort.SortBySignal;

public class SampleScanCombo {
	
	private GregorianCalendar time;
	private String id;
	protected EarthCoordinate ec;
	protected ArrayList<Wifi> wifiArray;
	
	
	/**
	 * @param time
	 * @param id
	 * @param pointLocation
	 * @param arrayWifi
	 */
	public SampleScanCombo(GregorianCalendar time, String id, EarthCoordinate ec, ArrayList<Wifi> wifiArray) {
		super();
		this.time = time;
		this.id = id;
		this.ec = ec;
		this.wifiArray = wifiArray;
	}

	
	public SampleScanCombo(SampleScanCombo sc) {
		this.time = sc.time;
		this.id = sc.id;
		this.ec = sc.ec;
		this.wifiArray = sc.wifiArray;
	}


	public ArrayList<Wifi> getStrongerWifisByNum(int num) {
		wifiArray.sort(new SortBySignal());
		if (num > wifiNetworks()) return wifiArray;
		ArrayList<Wifi> arr = new ArrayList<Wifi>();
		for (int i = 0; i < num; i++) 
			arr.add(wifiArray.get(i));
		return arr;
	}

	
	public ArrayList<String> toStrings() {
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<String> row = new ArrayList<>();
		row.add(fmt.format(time.getTime())); 
		row.add(id); 
		row.add(""+ec.getLatitude()); 
		row.add(""+ec.getLongitude());
		row.add(""+ec.getAltitude());
		row.add(""+wifiNetworks());
		for(Wifi wf : wifiArray){
			row.add(wf.getId());
			row.add(wf.getMac());
			row.add(wf.getFrequency()+"");
			row.add(wf.getSignal()+"");
		}
		return row;
	}


	public Wifi hasMac(String mac) {
		for (Wifi wifi : wifiArray) {
			if (wifi.getMac().equals(mac))
				return wifi;
		}
		return null;
	}

		
	public int wifiNetworks() {
		return wifiArray.size();
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


