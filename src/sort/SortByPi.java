package sort;

import java.util.Comparator;

import objects.DataAlgo2;

public class SortByPi implements Comparator<DataAlgo2> {

	@Override
	public int compare(DataAlgo2 o1, DataAlgo2 o2) {
		return Double.compare(o2.getPi(), o1.getPi());
	}

}
