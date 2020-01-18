package algorithms;

import java.util.ArrayList;
import java.util.Map;
import objects.SampleScan;


public abstract class Algo<T> {

	protected static final int NUM_OF_WIFIS = 4;
	protected Map<String, ArrayList<T>> map;
	protected ArrayList<SampleScan> algoMat;
	protected ArrayList<SampleScan> scs;
	
	
	public Algo(ArrayList<SampleScan> scs) {
		this.scs = scs;
	}

		
	protected abstract Map<String, ArrayList<T>> toAlgoMap();
	
	public abstract ArrayList<SampleScan> toAlgoMat();

	
	public ArrayList<SampleScan> getAlgoMat() {
		return algoMat;
	}

	public void setAlgoMat(ArrayList<SampleScan> algoMat) {
		this.algoMat = algoMat;
	}

	public ArrayList<SampleScan> getScs() {
		return scs;
	}

	public void setScs(ArrayList<SampleScan> scs) {
		this.scs = scs;
	}

	protected Map<String, ArrayList<T>> getMap() {
		return map;
	}
}
