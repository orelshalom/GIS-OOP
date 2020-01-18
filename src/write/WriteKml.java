package write;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;

import actions.KmlHelper;
import objects.SampleScan;

public class WriteKml extends WriteFile { 
		
	/**
	 * @param scs
	 * @param name
	 */
	public WriteKml(String name, String path, ArrayList<SampleScan> scs) {
		super(name + ".kml", path, scs);
	}

	
	@Override
	public void write() {
		Kml kml = new Kml();
		Document doc = new Document();
		kml.setFeature(doc);
		
		KmlHelper.addIcon("red", doc);
		KmlHelper.addIcon("ylw", doc);
		KmlHelper.addIcon("grn", doc);
		for(SampleScan sc : getMat())
			KmlHelper.addPlacemark(sc, doc);
		
		
		try {
			PrintWriter pw = new PrintWriter(new File(getFolderPath() ,file_name));
			kml.createKml(pw);
		} catch (KmlException | IOException e) {
			e.printStackTrace();
		}
	}

}
