package actions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.SampleScan;
import objects.Wifi;
import tools.Cast;
import tools.ParseDate;


public class CastFilesToCombo extends Cast {

	private ArrayList<String[][]> arrMat;
	private ArrayList<SampleScan> scs;
	private String device_id;
	
	/**
	 * @param arrMat
	 */
	public CastFilesToCombo(ArrayList<String[][]> arrMat) {
		super();
		this.arrMat = arrMat;
	}


	public ArrayList<SampleScan> toSampleScansCombo() throws ParseException {
		scs = new ArrayList<>();
//	    long start = System.currentTimeMillis();

		for(String[][] mat : arrMat){
			ArrayList<ArrayList<String>> tmp;
			tmp = Cast.matToArrayList(mat);
			cleanMatrix(tmp);
			addToScansArray(tmp);
		}
//	    long end = System.currentTimeMillis();
//	    float sec = (end - start) / 1000F;
//	    System.out.println(sec + " seconds");
		return scs;
	}
	
		
	private void cleanMatrix(ArrayList<ArrayList<String>> tmp) {
       	device_id = tmp.get(0).get(5).substring(8);
       	tmp.remove(0);
        for(int i = 1; i<tmp.size(); i++){
        	if(!tmp.get(i).get(10).equals("WIFI") || tmp.get(i).get(3).contains("1970")){
        		tmp.remove(i);
        		i--;
        	}
        }
	}
	
	
	private void addToScansArray(ArrayList<ArrayList<String>> tmp) throws ParseException {
		SampleScan sc;
		GregorianCalendar gc;
		EarthCoordinate ec;
		ArrayList<Wifi> wifis;
		String time;
	    	    
		for(int n = 1; n<tmp.size(); n++){
			wifis = new ArrayList<>();
			wifis.add(new Wifi(tmp.get(n).get(1), tmp.get(n).get(0), 
					channelToFrequency(tmp.get(n).get(4)), Integer.parseInt(tmp.get(n).get(5))));
			
			int count = 1, k = n+1;
			time = tmp.get(n).get(3);
			while(k<tmp.size() && time.compareTo(tmp.get(k).get(3)) == 0){
				Wifi wf =  new Wifi(tmp.get(k).get(1), tmp.get(k).get(0),
						channelToFrequency(tmp.get(k).get(4)), Integer.parseInt(tmp.get(k).get(5)));
				wifis.add(wf);
				count++;
				k++;
			}
			gc = new GregorianCalendar();
		    gc.setTime(ParseDate.stringToDate(time));
		    ec = new EarthCoordinate(Double.parseDouble(tmp.get(n).get(7)), 
					Double.parseDouble(tmp.get(n).get(6)), Double.parseDouble(tmp.get(n).get(8)));
			sc = new SampleScan(gc, device_id, ec, wifis);
			scs.add(sc);
			n += count-1;
		}
	}
	
	
	public ArrayList<SampleScan> getScans() {
		return scs;
	}
	
	public void setScans(ArrayList<SampleScan> scans) {
		this.scs = scans;
	}

}