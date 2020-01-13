package objects;


public class DataAlgo2 {
	
	private SampleScan sc;
	private double pi;
	
	
	/**
	 * @param sc
	 * @param pi
	 */
	public DataAlgo2(SampleScan sc, double pi) {
		super();
		this.sc = sc;
		this.pi = pi;
	}
	
	
	public SampleScan getSc() {
		return sc;
	}
	
	public void setSc(SampleScan sc) {
		this.sc = sc;
	}
	
	public double getPi() {
		return pi;
	}
	
	public void setPi(double pi) {
		this.pi = pi;
	}
	
	

}
