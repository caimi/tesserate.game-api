package com.tesserate.game.api;

import java.awt.image.BufferedImage;

import com.tesserate.game.api.fs.ImageResource;

public class AnimatedSprite extends Sprite{
	public enum Status {READY, STARTED, ENDED };

	private int fps;
	private boolean loopback=false;	
	private Status status = Status.READY;
	private long time=0;
	/**
	 * @param image BufferedImage for sprite
	 * @param fps Frames per second
	 * @param cols Image columns
	 * @param rows Image rows
	 * @param width Width frame
	 * @param height Height frame
	 * @param dx Delta variantions in cols for many sprites in same file
	 * @param dy Delta variantions in rows for many sprites in same file
	 */
	public AnimatedSprite(ImageResource image, int fps, int cols, int rows, int width, int height, int dx, int dy){
		this.image = image;
		this.fps = fps;
		this.cols = cols;
		this.rows = rows;
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
		this.initFrames();
	}


	public BufferedImage getFrame(){
		//TODO BUG java.lang.ArrayIndexOutOfBoundsException 
		//nextFrame faz frame++ e alguem da getFrame. sincronia
		if(frame >=cols*rows)
			return frames[cols*rows-1];
		return frames[frame];
	}
	
	public void start(){
		status = Status.STARTED;
	}
	
	public void update(long elapsedTime){
		if(status == Status.STARTED){
			this.time += elapsedTime;
			if(this.time >= 1000/fps){
				this.time -=1000/fps;
				nextFrame();
			}
		}
	}
	
	private void nextFrame(){
		frame++;
		if(frame >= cols*rows){
			if(loopback){
				frame = 0;
				return;
			}
			status = Status.ENDED;
		}
	}
	
	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public boolean isLoopback() {
		return loopback;
	}

	public void setLoopback(boolean loopback) {
		this.loopback = loopback;
	}

	public Status getStatus(){
		return status;
	}

}	
