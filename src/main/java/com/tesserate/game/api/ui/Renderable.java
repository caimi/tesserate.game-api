package com.tesserate.game.api.ui;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Renderable {
	public void render(Graphics2D g);
	public Point getPosicao();
	public void setPosicao(Point p);
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);	
	public boolean isVisivel();
	public void setVisivel(boolean visivel);
}
