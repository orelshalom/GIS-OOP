package read;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.boehn.kmlframework.coordinates.EarthCoordinate;

import tools.ParseDate;
import objects.SampleScan;
import objects.Wifi;

public abstract class ReadFile implements Read {

	private String file_path;
	protected String[][] matrix;
	private ArrayList<SampleScan> scs;

	
	public ReadFile(String path) {
		super();
		this.file_path = path;
	}

	
	protected abstract boolean goodFormat(List<CSVRecord> records); // TODO Format.class
	
	
	public ArrayList<SampleScan> toArrSamples(ArrayList<ArrayList<String>> arr) throws ParseException {
		scs = new ArrayList<>();
		GregorianCalendar gc; 
		EarthCoordinate ec;
		ArrayList<Wifi> wfs;
		SampleScan sc;
		
		for(int i = 0; i<arr.size(); i++){
			sc = new SampleScan();
			gc = new GregorianCalendar();
		    gc.setTime(ParseDate.stringToDate(arr.get(i).get(0)));
			ec = new EarthCoordinate(Double.parseDouble(arr.get(i).get(3)),
					Double.parseDouble(arr.get(i).get(2)), Double.parseDouble(arr.get(i).get(4)));
			sc.setTime(gc);
			sc.setId(arr.get(i).get(1));
			sc.setLocation(ec);
			wfs = new ArrayList<>();
			for(int j = 0; j<10; j++){
				if(arr.get(i).get(9+j*4) != null && !arr.get(i).get(9+j*4).isEmpty())
					wfs.add(new Wifi(arr.get(i).get(6+j*4), arr.get(i).get(7+j*4),
							Double.parseDouble(arr.get(i).get(8+j*4)), Double.parseDouble(arr.get(i).get(9+j*4))));
				else break;
			}
			sc.setWifiArray(wfs);
			scs.add(sc);
		}
		return scs;
	}
	
	public String getFile_path() {
		return file_path;
	}

	
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


	public String[][] getMatrix() {
		return matrix;
	}


	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}

}
