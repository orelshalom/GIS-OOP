package read;

import java.io.File;
import java.util.ArrayList;

public class ReadFolder implements Read {

	private String path;
	private ArrayList<String[][]> csv_files;
	private ArrayList<File> kml_files;
	
		
	public ReadFolder(String path) {
		super();
		this.path = path;
		this.csv_files = new ArrayList<>();
		this.kml_files = new ArrayList<>();
	}


	@Override
	public void read() {
		File directory = new File(path);
		File[] contents = directory.listFiles();
		for ( File f : contents) {
			if (f.getName().endsWith(".csv")) {
				ReadFile rfi = new ReadFile(f.getAbsolutePath());
				if(f.length() != 0) rfi.read();
				if(rfi.getMatrix() != null) csv_files.add(rfi.getMatrix());	
			}
			else if (f.getName().endsWith(".kml")){
				kml_files.add(f);
			}
		}
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<String[][]> getCsv_files() {
		return csv_files;
	}

	public void setCsv_files(ArrayList<String[][]> csv_files) {
		this.csv_files = csv_files;
	}

	public ArrayList<File> getKml_files() {
		return kml_files;
	}

	public void setKml_files(ArrayList<File> kml_files) {
		this.kml_files = kml_files;
	}
	
	

}
