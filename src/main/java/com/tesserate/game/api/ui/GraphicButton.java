package com.tesserate.game.api.ui;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import com.tesserate.game.api.math.Vector2D;

public class GraphicButton extends GraphicsObjects{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private BufferedImage imageClicked;
	private int id;
	private int clicked = 0;
	
	public GraphicButton(BufferedImage image, MouseAdapter mouseAdapter, int x, int y, int id) {
		this.image = image;
		this.addMouseListener(mouseAdapter);
		this.position = new Vector2D(x, y);
		this.setId(id);
	}
	
	public GraphicButton(BufferedImage image, BufferedImage imageClicked, MouseAdapter mouseAdapter, int x, int y, int id) {
		this.image = image;
		this.imageClicked = imageClicked;
		this.addMouseListener(mouseAdapter);
		this.position = new Vector2D(x, y);
		this.setId(id);
		setBounds(x, y, image.getWidth(), image.getHeight());
	}

	@Override
	public void render(Graphics2D g) {
		if(clicked > 0){
			g.drawImage(imageClicked, this.getLocation().x, this.getLocation().y, null);
			if(clicked < 0) 
				clicked = 0;
			else clicked--;
		}
		else{
			g.drawImage(image, this.getLocation().x, this.getLocation().y, null);
		}
	}

	public void onClick(){
		this.clicked  = 3;
	}

	public BufferedImage getImageClicked() {
		return imageClicked;
	}

	public void setImageClicked(BufferedImage imageClicked) {
		this.imageClicked = imageClicked;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
