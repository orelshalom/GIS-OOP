package filter;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.SampleScan;

public class LocationFilter extends Filter {

	private EarthCoordinate ec;
	private double radius;
	
	/**
	 * @param ec
	 * @param radius
	 */
	public LocationFilter(EarthCoordinate ec, double radius) {
		super();
		this.ec = ec;
		this.radius = radius;
	}


	@Override
	public ArrayList<SampleScan> filter(ArrayList<SampleScan> scs) {
		if (scs.size() == 0) throw new IndexOutOfBoundsException("File is empty.");
		ArrayList<SampleScan> tmp = removeDupMac(scs);
		tmp.removeIf(sc -> sc.getLocation().distanceTo(ec) > radius);
		for(SampleScan sc : tmp){
			System.out.println(sc.toStrings());
			System.out.println(sc.getLocation().distanceTo(ec));

		}

		return tmp;
	}

}
