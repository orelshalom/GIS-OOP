package write;

import java.util.ArrayList;
import objects.SampleScan;

public abstract class WriteFile {

	protected String file_name;
	private String folder_path;
	private ArrayList<SampleScan> mat;
	
	
	/**
	 * @param file_path
	 * @param mat 
	 */
	public WriteFile(String name, String path, ArrayList<SampleScan> mat) {
		super();
		this.file_name = name;
		this.folder_path = path;
		this.mat = mat;
	}

	public abstract void write();
	
	
	public String getFolderPath() {
		return folder_path;
	}

	
	public void setFolderPath(String file_path) {
		this.folder_path = file_path;
	}

	
	public ArrayList<SampleScan> getMat() {
		return mat;
	}

	
	public void setMat(ArrayList<SampleScan> mat) {
		this.mat = mat;
	}


	public String getFileName() {
		return file_name;
	}


	public void setFileName(String file_name) {
		this.file_name = file_name;
	}
	
}

