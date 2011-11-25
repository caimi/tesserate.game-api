package com.tesserate.game.api.math;

public class Vector2D {
	private double x;
	private double y;
	
	public Vector2D(){
		
	}

	public Vector2D(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	public Vector2D add(Vector2D v){
		return add(v.getX(), v.getY());
	}

	public Vector2D add(double x, double y){
		return new Vector2D(this.getX() + x, this.getY() + y);
	}
	
	public Vector2D subtract(Vector2D v){
		return this.subtract(v.getX(), v.getY());
	}
	
	public Vector2D subtract(double x, double y){
		return new Vector2D(this.getX() - x, this.getY() - y);
	}
	
	public Vector2D multiply(double e){
		return new Vector2D(this.getX() * e, this.getY() * e);
	}
	
	public double length(){
		return Math.sqrt(getX()*getX() + getY()*getY());
	}

	public double squareLength(){
		return getX()*getX() + getY()*getY();
	}

	public Vector2D normalize(){
		if(this.squareLength() == 0){
			throw new IllegalArgumentException("Can't normalize vector with length equal zero (0)."); 
		}
		return this.multiply(1/this.length());
	}
	
	public double dot(Vector2D v){
		return this.getX()*v.getX() + this.getY()*v.getY();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("(%f, %f)", x,y);
	}
}
