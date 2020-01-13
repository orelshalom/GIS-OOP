package objects;

public class Wifi {

	String id, mac;
	double signal, frequency;
	
	
	public Wifi(String id, String mac, double frequency, double signal) {
		this.mac = mac;
		this.id = id;
		this.signal = signal;
		this.frequency = frequency;
		emptyName();
	}


	public Wifi(Wifi wifi) {
		this.mac = wifi.getMac();
		this.id = wifi.getId();
		this.signal = wifi.getSignal();
		this.frequency = wifi.getFrequency();
	}


	private void emptyName() {
		if(this.id.equals("")) id = "No name";
	}
	
	
	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	
	public double getSignal() {
		return signal;
	}


	public void setSignal(double signal) {
		this.signal = signal;
	}


	public double getFrequency() {
		return frequency;
	}


	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

}
