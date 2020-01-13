package read;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public abstract class ReadFile implements Read {

	private String file_path;
	protected String[][] matrix;

	
	public ReadFile(String path) {
		super();
		this.file_path = path;
	}

	
	protected abstract boolean goodFormat(List<CSVRecord> records);
	
	
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
