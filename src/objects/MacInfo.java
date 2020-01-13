package objects;

import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

public class MacInfo {
	
	private GregorianCalendar time;
	private String id;
	private EarthCoordinate ec;
	private Wifi wifi;
	
	
	/**
	 * @param time
	 * @param id
	 * @param ec
	 * @param wifi
	 */
	public MacInfo(GregorianCalendar time, String id, EarthCoordinate ec, Wifi wifi) {
		super();
		this.time = time;
		this.id = id;
		this.ec = ec;
		this.wifi = wifi;
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


	public Wifi getWifi() {
		return wifi;
	}


	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}

}
