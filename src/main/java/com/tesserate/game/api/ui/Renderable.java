package com.tesserate.game.api.ui;

import java.awt.Graphics2D;

import com.tesserate.game.api.math.Vector2D;

public interface Renderable {
	public void render(Graphics2D g);
	public Vector2D getPosition();
	public void setPosition(Vector2D p);
	public boolean isVisible();
	public void setVisible(boolean visivel);
}
