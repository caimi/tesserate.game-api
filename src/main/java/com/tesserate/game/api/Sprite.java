package com.tesserate.game.api;

import java.awt.image.BufferedImage;

import com.tesserate.game.api.fs.ImageResource;

public class Sprite {

	protected int cols;
	protected int rows;
	protected int dx = 0;
	protected int dy = 0;
	protected int height;
	protected int width;
	protected ImageResource image;
	protected int frame = 0;
	protected BufferedImage[] frames;
	
	public Sprite(){
		
	}

	public Sprite(ImageResource image, int cols, int rows, int width, int height, int dx, int dy) {
		this.image = image;
		this.cols = cols;
		this.rows = rows;
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
		this.initFrames();
	}
	
	public int size(){
		return cols*rows;
	}
		
	public BufferedImage getFrame(int i){
		return frames[i];
	}
	
	protected void initFrames() {
		frames = new BufferedImage[this.cols * this.rows];
		for (int r = 0; r < rows; r++)
		{
		    for (int c = 0; c < cols; c++)
		    {
		    	frames[(r * cols) + c] = image.getImage().getSubimage( c*width + dx, r*height + dy, width, height );
		    }
		}
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}