package filter;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import objects.SampleScanCombo;


public class TimeFilter extends Filter {
	
	private GregorianCalendar beginningTime;
	private GregorianCalendar endingTime;
	
	
	/**
	 * @param beginningTime
	 * @param endingTime
	 */
	public TimeFilter(GregorianCalendar beginningTime, GregorianCalendar endingTime) {
		super();
		this.beginningTime = beginningTime;
		this.endingTime = endingTime;
	}


	@Override
	public ArrayList<SampleScanCombo> filter(ArrayList<SampleScanCombo> scs) {
		if (scs.size() == 0) throw new IndexOutOfBoundsException("File is empty.");
		ArrayList<SampleScanCombo> tmp = removeDupMac(scs);
		tmp.removeIf(sc -> sc.getTime().after(endingTime) || sc.getTime().before(beginningTime));
		for(SampleScanCombo sc : tmp) System.out.println(sc.toStrings());
		return scs;
	}

	

}
