package actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.ExtendedData;
import org.boehn.kmlframework.kml.IconStyle;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.SimpleData;
import org.boehn.kmlframework.kml.Style;
import org.boehn.kmlframework.kml.TimePrimitive;
import org.boehn.kmlframework.kml.TimeStamp;

import objects.SampleScan;
import objects.Wifi;

public class KmlHelper {
	
	
	public void addPlacemark(SampleScan sc, Document doc) {
		for(Wifi wf : sc.getWifiArray()){
			Placemark pm = new Placemark(wf.getId());
			TimeStamp ts = new TimeStamp(changeFormat(sc.getTime()));
			pm.setLocation(sc.getLocation().getLongitude(), sc.getLocation().getLatitude());
			pm.setTimePrimitive(ts);
			pm.setStyleUrl(setColor(wf.getSignal()));
			pm.setExtendedData(wifiDetails(sc, wf));
			doc.addFeature(pm);
		}
	}
	
	
	public void addIcon(String color, Document doc) {
		Style st = new Style();
		st.setId(color);
		IconStyle is = new IconStyle();
		is.setColor(color);
		is.setIconHref("http://maps.google.com/mapfiles/kml/paddle/" + color + "-circle.png");
		st.setIconStyle(is);
		doc.addStyleSelector(st);
	}
	

	private String changeFormat(GregorianCalendar gc) {
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(gc.getTime());
	}
	
	private String setColor(int signal) {
		if(signal > -70) return "#grn";
		else if (signal > -90) return "#ylw";
		return "#red";
	}
	
	private ExtendedData wifiDetails(SampleScan sc, Wifi wf) {
		ArrayList<SimpleData> details = new ArrayList<>();
		details.add(new SimpleData("ID", sc.getId()));
		details.add(new SimpleData("Mac", wf.getMac()));
		details.add(new SimpleData("Signal", wf.getSignal()+""));
		details.add(new SimpleData("Frequency", wf.getFrequency()+""));
		details.add(new SimpleData("Date", changeFormat(sc.getTime())));
		ExtendedData ed = new ExtendedData();
		ed.setSimpleDataElements(details);
		return ed;
	}
}
