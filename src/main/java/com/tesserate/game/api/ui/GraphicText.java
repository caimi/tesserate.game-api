package com.tesserate.game.api.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.tesserate.game.api.ui.GraphicsObjects;

public class GraphicText extends GraphicsObjects {

	private static final long serialVersionUID = 1203023930451741329L;
	protected String msg;
	protected FontMetrics fm;
	protected Font font;
	protected Color color;
	protected float alignment = LEFT_ALIGNMENT;
	
	public GraphicText() {
		super();
		color = Color.BLACK;
		alignment = RIGHT_ALIGNMENT;
	}

	public GraphicText(String msg, int x, int y, Font font, Color c) {
		this.font = font;
		this.color = c;
		this.msg = msg;
		setVisivel(true);
		posicao.setLocation(x, y);
	}
	
	protected void redefineBonds(Graphics g) {
		fm = g.getFontMetrics(font);
		int width = fm.stringWidth(this.msg);
		int height = (int)(fm.getHeight() * 0.65);
		if (alignment == RIGHT_ALIGNMENT){
			this.setBounds(getX()-width, getY()-height, width, height);
		}else{
			this.setBounds(getX(), getY()-height, width, height);
		}
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getAlignment() {
		return alignment;
	}

	public void setAlignment(float alignment) {
		this.alignment = alignment;
	}
    
	@Override
	public void render(Graphics2D g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g){
		if (!isVisible()) return; 
		redefineBonds(g);
		g.setFont(font);
		g.setColor(color);
		if ( alignment == RIGHT_ALIGNMENT){
			g.drawString(msg, posicao.getLocation().x-fm.stringWidth(msg), posicao.getLocation().y);
		}else{
			g.drawString(msg, posicao.getLocation().x, posicao.getLocation().y);
		}
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}