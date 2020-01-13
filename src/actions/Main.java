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
import objects.SampleScan;
import objects.Wifi;
import read.ReadFolder;
import write.WriteAlgo;
import write.WriteCombo;
import write.WriteFile;
import write.WriteKml;

public class Main {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";

	public static void main(String[] args) throws ParseException {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		MergeFiles mer = new MergeFiles(rfo.getCsv_files());
		ArrayList<SampleScan> scs = mer.comboMat();
		WriteFile wf = new WriteCombo("combo", FOLDER_PATH, scs);
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
//		WriteKml wk = new WriteKml("map", FOLDER_PATH, scs);
//		wk.write();
		
//		FirstAlgo fa = new FirstAlgo(scs);
//		WriteAlgo wa = new WriteAlgo("Algo1", FOLDER_PATH, fa.toAlgo1Mat());
//		wa.write();
//		SecondAlgo sa = new SecondAlgo(scs);
//		WriteCombo wa2 = new WriteCombo("Algo2", FOLDER_PATH, sa.toAlgo2Mat());
//		wa2.write();

		
		
		 
		 
	}

}
