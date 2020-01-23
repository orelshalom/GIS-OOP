package test_sort;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import objects.DataAlgo2;
import sort.SortByPi;

public class SortByPiTest {

	@Test
	public void test() {
		DataAlgo2 da1 = new DataAlgo2(null, 0.02345);
		DataAlgo2 da2 = new DataAlgo2(null, 1.45001);
		DataAlgo2 da3 = new DataAlgo2(null, 0.00972);
		
		ArrayList<DataAlgo2> das = new ArrayList<>();
		das.add(da1);
		das.add(da2);
		das.add(da3);
		das.sort(new SortByPi());
		
		assertSame(da2, das.get(0));
		assertSame(da1, das.get(1));
		assertSame(da3, das.get(2));
	}

}
