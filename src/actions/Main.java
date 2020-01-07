package actions;

import java.text.ParseException;
import java.util.ArrayList;

import objects.SampleScan;
import read.ReadFolder;
import write.WriteFile;

public class Main {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads";
	private static final String tryial = "C:/Users/OREL SHALOM/Downloads/sample.csv";
	
	public static void main(String[] args) {
		ReadFolder rfo = new ReadFolder(FOLDER_PATH);
		rfo.read();
		MergeFiles mer = new MergeFiles(rfo.getCsv_files());
		ArrayList<SampleScan> scs;
		try {
			scs = mer.comboMat();
			WriteFile wf = new WriteFile(tryial, scs);
			wf.write();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
