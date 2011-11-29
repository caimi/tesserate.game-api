package com.tesserate.game.api.fs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageResource extends Resource {
	private BufferedImage image;
	
	public ImageResource(String id, String filename){
		this.id = id;
		image = loadImage(filename);
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public static BufferedImage loadImage(String filename){
		BufferedImage image = null;
		try {
			InputStream is = loadFile(filename);
			if(is == null){ 
				throw new IOException("File not found.");
			};
			image = ImageIO.read(is);
		} catch (Exception e) {
			System.err.println("File not found: " + filename);
			System.exit(0);
		}
			
		return image;
	}
}
