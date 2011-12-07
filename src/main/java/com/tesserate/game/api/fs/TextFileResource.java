package com.tesserate.game.api.fs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileResource extends Resource {
	private List<String> lineFile = new ArrayList<String>();
	
	public TextFileResource(String id, String filename){
		this.id = id;
		loadLines(filename);
	}


	public void loadLines(String filename){
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
		    String str;
		    while ((str = in.readLine()) != null) {
		    	getLineFile().add(str);
		    }
		    in.close();
		} catch (IOException e) {
			System.err.println("Oops, arquivo não encontrado: " + filename);
			System.exit(0);
		}
	}


	public List<String> getLineFile() {
		return lineFile;
	}

}
