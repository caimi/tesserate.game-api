package com.tesserate.game.api.ui;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import com.tesserate.game.api.math.Vector2D;

public abstract class GraphicsObjects extends JPanel implements Renderable{
	
	private static final long serialVersionUID = 3876125648966630272L;
	protected Vector2D position;
	public static Graphics2D g;
	
	public abstract void render(Graphics2D g);

	public GraphicsObjects() {
		super();
		position = new Vector2D();
	}

	@Override
	public Point getLocation() {
		return new Point((int)position.getX(), (int)position.getY());
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(double x, double y) {
		this.position.setX(x);
		this.position.setY(y);
	}
	public void setPosition(Vector2D v) {
		this.setPosition(v.getX(), v.getY());
	}
}