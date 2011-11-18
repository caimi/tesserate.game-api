package com.tesserate.game.api.fs;

import java.awt.image.BufferedImage;
import java.io.IOException;

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
			image = ImageIO.read(loadFile(filename));
		} catch (IOException e) {
			System.err.println("Arquivo não encontrado: " + filename);
		}
			
		return image;
	}
}
