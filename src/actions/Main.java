package actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.Placemark;

import algorithms.FirstAlgo;
import algorithms.SecondAlgo;
import filter.Filter;
import filter.IDFilter;
import filter.LocationFilter;
import filter.TimeFilter;
import objects.MacInfo;
import objects.SampleScanAlgo;
import objects.SampleScanCombo;
import objects.Wifi;
import read.ReadFolder;
import write.WriteFile;
import write.WriteKml;

public class Main {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";
	private static final String COMBO_PATH = "C:/Users/OREL SHALOM/Downloads/sample.csv";

	public static void main(String[] args) throws ParseException {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		MergeFiles mer = new MergeFiles(rfo.getCsv_files());
		ArrayList<SampleScanCombo> scs = mer.comboMat();
		WriteFile wf = new WriteFile(COMBO_PATH, scs);
		wf.write();
		
//		Filter f1 = new IDFilter("PKQ1.1302.001");
//		f1.filter(scs);
//		EarthCoordinate ec = new EarthCoordinate(34.95398122, 32.08680173, 82.4484252929687);
//		Filter f2 = new LocationFilter(ec, 100.0);
//		scs = f2.filter(scs);
//	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		GregorianCalendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
//		gc1.setTime(fmt.parse("2019-12-06 16:18:12"));
//		gc2.setTime(fmt.parse("2019-12-08 08:30:09"));
//		Filter f3 = new TimeFilter(gc1, gc2);
//		scs = f3.filter(scs);
//		WriteKml wk = new WriteKml(scs, "map");
//		wk.write();
		
//		FirstAlgo fa = new FirstAlgo(scs);
//		for(Entry<String, ArrayList<MacInfo>> entry : fa.getMacs().entrySet()){
//			System.out.println(entry.getKey()+ " " +fa.algo1(entry.getKey()));
//		}
		SecondAlgo sa = new SecondAlgo(scs);
		for(SampleScanCombo sc : scs)
			System.out.println(sa.algo2(sc, sa.getMap()));
//		System.out.println(sa.algo2(scs.get(0), sa.getMap()));
		
		

		
		
		 
		 
	}

}
