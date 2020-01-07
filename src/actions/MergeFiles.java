package actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.SampleScan;
import objects.Wifi;
import sort.SortBySignal;

public class MergeFiles {

	private ArrayList<String[][]> arrMat;
	private ArrayList<SampleScan> matrix;
	private ArrayList<String> device_ids;
	private String time;
	
	/**
	 * @param arrMat
	 */
	public MergeFiles(ArrayList<String[][]> arrMat) {
		super();
		this.arrMat = arrMat;
		this.device_ids = new ArrayList<>();
		this.matrix = new ArrayList<>();
	}


	public ArrayList<SampleScan> comboMat() throws ParseException{
//	    long start = System.currentTimeMillis();

		for(String[][] mat : arrMat){
			ArrayList<ArrayList<String>> tmp;
			tmp = ToArrayList(mat);
			tmp = cleanMatrix(tmp);
			forEachTime10(tmp);
			device_ids.remove(0);
		}
//	    long end = System.currentTimeMillis();
//	    float sec = (end - start) / 1000F;
//	    System.out.println(sec + " seconds");
		return matrix;
	}
	
	
	private ArrayList<ArrayList<String>> ToArrayList(String[][] mat) {
		ArrayList<ArrayList<String>> tmp = new ArrayList<>();
		
		for(int i = 0; i<mat.length; i++){
			ArrayList<String> row = new ArrayList<>();
			for(int j = 0; j<mat[0].length; j++){
				row.add(mat[i][j]);
			}
			tmp.add(row);
		}
		return tmp;	
	}

	
	private ArrayList<ArrayList<String>> cleanMatrix(ArrayList<ArrayList<String>> tmp) {
       	device_ids.add(tmp.get(0).get(5).substring(8));
       	tmp.remove(0);
        for(int i = 1; i<tmp.size(); i++){
        	if(!tmp.get(i).get(10).equals("WIFI") || tmp.get(i).get(3).contains("1970")){
        		tmp.remove(i);
        		i--;
        	}
        }
        return tmp;
	}
	
	
	private void forEachTime10(ArrayList<ArrayList<String>> tmp) throws ParseException {
		SampleScan sc;
		ArrayList<Wifi> wifis;
		GregorianCalendar gc;  
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	    
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
		    gc.setTime(fmt.parse(time));

			sc = new SampleScan(gc, device_ids.get(0), 
					new EarthCoordinate(Double.parseDouble(tmp.get(n).get(7)), 
							Double.parseDouble(tmp.get(n).get(6)), 
							Double.parseDouble(tmp.get(n).get(8))), 
					getStrongerWifis(wifis));
			matrix.add(sc);
			n+=count-1;
		}
	}

	
	private ArrayList<Wifi> getStrongerWifis(ArrayList<Wifi> wifis) {
		Collections.sort(wifis, new SortBySignal());
		if (wifis.size() > 10) {
			ArrayList<Wifi> arrayStrongerWifi = new ArrayList<Wifi>();
			for (int i = 0; i < 10; i++)  arrayStrongerWifi.add(wifis.get(i));
			wifis = arrayStrongerWifi;
		}
		return wifis;
	}
	
	
	private int channelToFrequency(String channel) {
		int freq = -1;
		int channelInt = Integer.parseInt(channel);
		if (channelInt >= 1 && channelInt <= 14) freq = 2400;
		else if (channelInt >= 33 && channelInt <= 64) freq = 5000;
		return freq;
	}
	
	
	public ArrayList<SampleScan> getMatrix() {
		return matrix;
	}

	
	public void setMatrix(ArrayList<SampleScan> matrix) {
		this.matrix = matrix;
	}

}
