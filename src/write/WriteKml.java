package write;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;

import actions.KmlHelper;
import objects.SampleScanCombo;

public class WriteKml implements Write {
	
	private ArrayList<SampleScanCombo> scs;
	private final String DOWNLOAD_PATH = "C:/Users/OREL SHALOM/Downloads/";
	private String name;
	
	
	/**
	 * @param scs
	 * @param name
	 */
	public WriteKml(ArrayList<SampleScanCombo> scs, String name) {
		super();
		this.scs = scs;
		this.name = name + ".kml";
	}

	
	@Override
	public void write() {
		Kml kml = new Kml();
		Document doc = new Document();
		kml.setFeature(doc);
		
		KmlHelper kh = new KmlHelper();
		kh.addIcon("red", doc);
        kh.addIcon("ylw", doc);
        kh.addIcon("grn", doc);
		for(SampleScanCombo sc : scs){
			kh.addPlacemark(sc, doc);
		}
		
		try {
			PrintWriter pw = new PrintWriter(new File(DOWNLOAD_PATH ,name));
			kml.createKml(pw);
		} catch (KmlException | IOException e) {
			e.printStackTrace();
		}
	}

}
