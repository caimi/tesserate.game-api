package com.tesserate.game.api.ui;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class GraphicsObjects extends JPanel implements Renderable{
	
	private static final long serialVersionUID = 3876125648966630272L;
	protected Point posicao;
	protected Point velocidade;
	
	public static Graphics2D g;
	protected boolean visivel;
	
	public abstract void render(Graphics2D g);

	public GraphicsObjects() {
		super();
		posicao = new Point();
		velocidade = new Point();
		this.visivel = true;
	}

	
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

	public void addXY(int x, int y) {
		posicao.translate(x, y);
	}
	
	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	
	public Point getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int x, int y) {
		this.velocidade.x = x;
		this.velocidade.y = y;
	}
}