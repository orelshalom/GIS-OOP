package read;


public abstract class ReadFile implements Read {

	private String file_path;
	
	
	public ReadFile(String path) {
		super();
		this.file_path = path;
	}

	
	public String getFile_path() {
		return file_path;
	}

	
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
		
}
