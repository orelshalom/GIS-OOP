package actions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.SampleScan;
import objects.Wifi;


public class Cast {

	
	public static ArrayList<ArrayList<String>> matToArrayList(String[][] mat) {
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
	
	
	public static ArrayList<SampleScan> CombotoSamples(ArrayList<ArrayList<String>> arr) throws ParseException {
		ArrayList<SampleScan> scs = new ArrayList<>();
		GregorianCalendar gc; 
		EarthCoordinate ec;
		ArrayList<Wifi> wfs;
		SampleScan sc;
		
		for(int i = 1; i<arr.size(); i++){
			gc = new GregorianCalendar();
		    gc.setTime(ParseDate.stringToDate(arr.get(i).get(0)));
			ec = new EarthCoordinate(Double.parseDouble(arr.get(i).get(3)),
					Double.parseDouble(arr.get(i).get(2)), Double.parseDouble(arr.get(i).get(4)));
			wfs = new ArrayList<>();
			for(int j = 0; j<10; j++){
				if(arr.get(i).get(9+j*4) != null && !arr.get(i).get(9+j*4).isEmpty())
					wfs.add(new Wifi(arr.get(i).get(6+j*4), arr.get(i).get(7+j*4),
							Double.parseDouble(arr.get(i).get(8+j*4)), Double.parseDouble(arr.get(i).get(9+j*4))));
				else break;
			}
			sc = new SampleScan(gc, arr.get(i).get(1), ec, wfs);
			scs.add(sc);
		}
		return scs;
	}
		
	
	public static int channelToFrequency(String channel) {
		int freq = -1;
		int channelInt = Integer.parseInt(channel);
		if (channelInt >= 1 && channelInt <= 14) freq = 2400;
		else if (channelInt >= 33 && channelInt <= 64) freq = 5000;
		return freq;
	}
	

}
