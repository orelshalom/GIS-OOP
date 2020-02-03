package run;

import java.text.ParseException;
import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import actions.Cast;
import actions.CastFilesToCombo;
import algorithms.Algorithm1;
import algorithms.Algorithm2;
import objects.SampleScan;
import objects.Wifi;
import read.ReadCombo;
import read.ReadFolder;
import write.WriteAlgo1;
import write.WriteCombo;
import write.WriteFile;
import write.WriteKml;

public class Main {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";

	public static void main(String[] args) throws ParseException {
//		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
//		rfo.read();
//		CastFilesToCombo mer = new CastFilesToCombo(rfo.getCsv_files());
//		ArrayList<SampleScan> scs = mer.toSampleScansCombo();

//		WriteFile wf = new WriteCombo("combo", FOLDER_PATH, scs);
//		wf.write();
		
//		WriteKml wk = new WriteKml("map", FOLDER_PATH, scs);
//		wk.write();
		
//		ReadCombo rcInput = new ReadCombo("C:/Users/OREL SHALOM/Downloads/_comb_all_BM3_.csv");
//		rcInput.read();
//		ReadCombo rcTest = new ReadCombo("C:/Users/OREL SHALOM/Downloads/_comb_no_gps_ts1.csv");
//		rcTest.read();
		
//		Algorithm1 a1 = new Algorithm1(Cast.CombotoSamples(Cast.matToArrayList(rcInput.getMatrix())));
//		WriteAlgo1 wa = new WriteAlgo1("Our_Algo1_BM2_4", FOLDER_PATH, a1.toAlgoMat());
//		wa.write();
//		WriteAlgo1 wa = new WriteAlgo1("Our_Algo1_BM3_4", FOLDER_PATH, a1.toAlgoMat());
//		wa.write();

//		Algorithm2 a2 = new Algorithm2(Cast.CombotoSamples(Cast.matToArrayList(rcInput.getMatrix())),
//				Cast.CombotoSamples(Cast.matToArrayList(rcTest.getMatrix())));
//		WriteCombo wa2 = new WriteCombo("Our_Algo2_BM3_TS1_4", FOLDER_PATH, a2.toAlgoMat());
//		wa2.write();
		
	}

}
