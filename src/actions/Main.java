package actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import filter.Filter;
import filter.IDFilter;
import filter.LocationFilter;
import filter.TimeFilter;
import objects.SampleScan;
import read.ReadFolder;
import write.WriteFile;

public class Main {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";
	private static final String COMBO_PATH = "C:/Users/OREL SHALOM/Downloads/sample.csv";
	private static final String FILTER_PATH = "C:/Users/OREL SHALOM/Downloads/map.kml";

	public static void main(String[] args) throws ParseException {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		MergeFiles mer = new MergeFiles(rfo.getCsv_files());
		ArrayList<SampleScan> scs;
		scs = mer.comboMat();
		WriteFile wf = new WriteFile(COMBO_PATH, scs);
		wf.write();
		Filter f1 = new IDFilter("PKQ1.1302.001");
		f1.filter(scs);
		EarthCoordinate ec = new EarthCoordinate(34.95398122, 32.08680173, 82.4484252929687);
		Filter f2 = new LocationFilter(ec, 35.0);
		f2.filter(scs);
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
		gc1.setTime(fmt.parse("2019-12-06 16:18:12"));
		gc2.setTime(fmt.parse("2019-12-08 08:30:09"));
		Filter f3 = new TimeFilter(gc1, gc2);
		f3.filter(scs);
	}

}
