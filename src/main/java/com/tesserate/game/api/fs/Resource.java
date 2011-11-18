package com.tesserate.game.api.fs;

import java.io.InputStream;

public class Resource {
	protected String id;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public static InputStream loadFile(String filename){
		InputStream input;
		input = java.lang.ClassLoader.getSystemResourceAsStream(filename);
		return input;
	}
	
}
