package objects;

public class Wifi {

	String id, mac;
	int signal, frequency;
	
	
	public Wifi(String id, String mac, int frequency, int signal) {
		this.mac = mac;
		this.id = id;
		this.signal = signal;
		this.frequency = frequency;
		emptyName();
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

	
	public int getSignal() {
		return signal;
	}


	public void setSignal(int signal) {
		this.signal = signal;
	}


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
