package objects;


public class DataAlgo2 {
	
	private SampleScanCombo sc;
	private double pi;
	
	
	/**
	 * @param sc
	 * @param pi
	 */
	public DataAlgo2(SampleScanCombo sc, double pi) {
		super();
		this.sc = sc;
		this.pi = pi;
	}
	
	
	public SampleScanCombo getSc() {
		return sc;
	}
	
	public void setSc(SampleScanCombo sc) {
		this.sc = sc;
	}
	
	public double getPi() {
		return pi;
	}
	
	public void setPi(double pi) {
		this.pi = pi;
	}
	
	

}
