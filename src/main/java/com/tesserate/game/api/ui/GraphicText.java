package com.tesserate.game.api.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.tesserate.game.api.ui.GraphicsObjects;

public class GraphicText extends GraphicsObjects {

	private static final long serialVersionUID = 1203023930451741329L;
	protected String msg="";
	protected FontMetrics fm;
	protected Font font = new Font("tahoma", Font.PLAIN, 10);
	protected Color color;
	protected float alignment = LEFT_ALIGNMENT;
	
	public GraphicText() {
		super();
		//color = Color.BLACK;
	}

	public GraphicText(String msg, int x, int y){
		this.msg = msg;
		this.setVisible(true);
		this.setPosition(x, y);
	}
	
	public GraphicText(String msg, int x, int y, Font font, Color c) {
		this.font = font;
		this.color = c;
		this.msg = msg;
		this.setVisible(true);
		this.setPosition(x, y);
	}
	
	protected void redefineBonds(Graphics g) {
		fm = g.getFontMetrics(font);
		int width = fm.stringWidth(
				this.msg);
		int height = (int)(fm.getHeight() * 0.65);
		if (alignment == RIGHT_ALIGNMENT){
			this.setBounds(this.getLocation().x-width, this.getLocation().y-height, width, height);
		}else{
			this.setBounds(this.getLocation().x, this.getLocation().y-height, width, height);
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
		if (!this.isVisible()) return; 
		this.redefineBonds(g);
		g.setFont(font);
		g.setColor(color);
		if ( alignment == RIGHT_ALIGNMENT){
			g.drawString(msg, this.getLocation().x-fm.stringWidth(msg), this.getLocation().y);
		}else{
			g.drawString(msg, this.getLocation().x, this.getLocation().y);
		}
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg ) {
		this.msg = msg;
	}
	
	public void setMsg(int msg ) {
		this.msg = String.format("%d", msg);
	}
	
	public void setMsg(double msg ) {
		this.msg = String.format("%d", msg);
	}
}