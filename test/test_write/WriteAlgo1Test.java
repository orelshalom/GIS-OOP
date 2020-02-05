package test_write;

import static org.junit.Assert.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import actions.Cast;
import algorithms.Algorithm1;
import read.ReadCombo;
import write.WriteAlgo1;

public class WriteAlgo1Test {

	private static final String FOLDER_PATH = "C:/Users/OREL SHALOM/Downloads/Output";

	@Test
	public void test() {
		ReadCombo rc = new ReadCombo("C:/Users/OREL SHALOM/Downloads/Output/combo.csv");
		rc.read();
		
		try {
			Algorithm1 a1 = new Algorithm1(Cast.CombotoSamples(Cast.matToArrayList(rc.getMatrix())));
			WriteAlgo1 wa = new WriteAlgo1("Algo1", FOLDER_PATH, a1.toAlgoMat());
			wa.write();
			
			Reader reader = Files.newBufferedReader(Paths.get(FOLDER_PATH + "/Algo1.csv"), Charset.defaultCharset());
			CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
			List<CSVRecord> records = parser.getRecords();
			
			assertTrue(records.size()-1 == a1.getAlgoMat().size());
			ArrayList<String> rowOfFile, rowOfAlgo;

			for(int i = 0; i<wa.getMat().size(); i++){
				rowOfFile = new ArrayList<>();
				for(String s : records.get(i+1)) rowOfFile.add(s);
				
				rowOfAlgo = a1.getAlgoMat().get(i).toStringAlgo1();
				rowOfAlgo.add(0, i+"");
				assertTrue(rowOfFile.equals(rowOfAlgo));
			}
			parser.close();
			reader.close();
			
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

}
