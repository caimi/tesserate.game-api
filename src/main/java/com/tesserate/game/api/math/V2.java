package com.tesserate.game.api.math;

public class V2 {
	private double x;
	private double y;
	
	public V2(){
		
	}

	public V2(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	public V2(V2 v){
		this.setX(v.x);
		this.setY(v.y);
	}
	
	public V2 add(V2 v){
		return add(v.x, v.y);
	}

	public V2 add(double x, double y){
		this.x += x;
		this.y += y;
		return this;
	}
	
	public V2 subtract(V2 v){
		return this.subtract(v.x, v.y);
	}
	
	public V2 subtract(double x, double y){
		this.x -= x;
		this.y -= y;
		return this;
	}

	public V2 distance(V2 v){
		return new V2(v.x-x, v.y-y);
	}
	
	public V2 multiply(double e){
		this.x *= e;
		this.y *= e;
		return this;
	}
	
	public double length(){
		return Math.sqrt(getX()*getX() + getY()*getY());
	}

	public double squareLength(){
		return getX()*getX() + getY()*getY();
	}

	public V2 normalize(){
		if(this.squareLength() == 0){
			throw new IllegalArgumentException("Can't normalize vector with length equal zero (0)."); 
		}
		V2 normalize = new V2(this.x, this.y);
		return normalize.multiply(1/this.length());
	}
	
	public double dot(V2 v){
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
