package hwo;

import java.io.Serializable;

public class FileInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2519953917336037002L;
	private String name;
	private String loc;

	public FileInfo(String name, String loc){
		this.setName(name);
		this.setLoc(loc);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
