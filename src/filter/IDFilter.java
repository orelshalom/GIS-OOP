package filter;

import java.util.ArrayList;

import objects.SampleScanCombo;


public class IDFilter extends Filter {

	private String id;
	
	/**
	 * @param id
	 */
	public IDFilter(String id) {
		super();
		this.id = id;
	}


	@Override
	public ArrayList<SampleScanCombo> filter(ArrayList<SampleScanCombo> scs) {
		if (scs.size() == 0) throw new IndexOutOfBoundsException("File is empty.");
		ArrayList<SampleScanCombo> tmp = removeDupMac(scs);
		tmp.removeIf(sc -> !sc.getId().equals(id));
		for(SampleScanCombo sc : tmp) System.out.println(sc.toStrings());
		return tmp;
	}

}
