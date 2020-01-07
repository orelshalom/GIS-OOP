package filter;

import java.util.ArrayList;

import objects.SampleScan;

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
	public ArrayList<SampleScan> filter(ArrayList<SampleScan> scs) {
		if (scs.size() == 0) throw new IndexOutOfBoundsException("File is empty.");
		ArrayList<SampleScan> tmp = removeDupMac(scs);
		tmp.removeIf(sc -> !sc.getId().equals(id));
		return tmp;
	}

}
