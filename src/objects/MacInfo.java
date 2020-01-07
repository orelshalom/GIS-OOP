package objects;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

public class MacInfo {
	
	private EarthCoordinate ec;
	private double signal;
	
	
	/**
	 * @param ec
	 * @param signal
	 */
	public MacInfo(EarthCoordinate ec, int signal) {
		super();
		this.ec = ec;
		this.signal = signal;
	}


	public EarthCoordinate getLocation() {
		return ec;
	}


	public void setLocation(EarthCoordinate ec) {
		this.ec = ec;
	}


	public double getSignal() {
		return signal;
	}


	public void setSignal(double signal) {
		this.signal = signal;
	}

}
