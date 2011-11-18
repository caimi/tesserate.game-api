package com.tesserate.game.api.ui;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class GraphicsObjects extends JPanel implements Renderable{
	
	private static final long serialVersionUID = 3876125648966630272L;
	protected Point posicao;
	public static Graphics2D g;
	protected boolean visivel;
	
	public GraphicsObjects() {
		super();
		posicao = new Point();
		this.visivel = true;
	}

	public abstract void render(Graphics2D g);
	
	public Point getPosicao() {
		return posicao;
	}

	public int getX() {
		return posicao.getLocation().x;
	}

	public int getY() {
		return posicao.getLocation().y;
	}

	public void setPosicao(Point p) {
		this.posicao = p; 
	}

	public void setX(int x) {
		posicao.setLocation(x, posicao.y);
	}

	public void setY(int y) {
		posicao.setLocation(posicao.x, y);
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
}