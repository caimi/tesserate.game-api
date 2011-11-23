package com.tesserate.game.api.math;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestVector {
	private static double PRECISION = 0.001;
	private Vector2D r;
	private Vector2D v1;
	private Vector2D v2;
	private Vector2D v3;
	private Vector2D v34;
	
	@Before
	public void test(){
		r = new Vector2D();
		v1 = new Vector2D(1,1);
		v2 = new Vector2D(2,2);
		v3 = new Vector2D(3,3);
		v34 = new Vector2D(3,4);
	}
	
	@Test
	public void testNew(){
		assertEquals(0, r.getX(), PRECISION);
		assertEquals(0, r.getY(), PRECISION);
		assertEquals(1, v1.getX(), PRECISION);
		assertEquals(1, v1.getY(), PRECISION);
	}
	
	@Test
	public void testAddYorself(){
		r = v1.add(v1);
		assertEquals(2, r.getX(), PRECISION);
		assertEquals(2, r.getY(), PRECISION);
	}

	
	@Test
	public void testAdd(){
		r = v1.add(v2);
		assertEquals(3, r.getX(), PRECISION);
		assertEquals(3, r.getY(), PRECISION);
	}

	@Test
	public void testAddComutation(){
		r = v2.add(v1);
		assertEquals(3, r.getX(), PRECISION);
		assertEquals(3, r.getY(), PRECISION);
	}

	@Test
	public void testSubtract(){
		r = v3.subtract(v1);
		assertEquals(2, r.getX(), PRECISION);
		assertEquals(2, r.getY(), PRECISION);
	}
	
	@Test
	public void testSubtractNotComutation(){
		r = v1.subtract(v3);
		assertEquals(-2, r.getX(), PRECISION);
		assertEquals(-2, r.getY(), PRECISION);
	}
	
	@Test
	public void testMultiply(){
		r = v2.multiply(3.5);
		assertEquals(7, r.getX(), PRECISION);
		assertEquals(7, r.getY(), PRECISION);
		r = v3.multiply(-0.5);
		assertEquals(-1.5, r.getX(), PRECISION);
		assertEquals(-1.5, r.getY(), PRECISION);
	}

	@Test
	public void testLength(){
		assertEquals(1.4142135623730951, v1.length(), PRECISION);
		assertEquals(5, v34.length(), PRECISION);
		r = v2.subtract(v3);
		assertEquals(1.4142135623730951, r.length(), PRECISION);
	}
	
	@Test
	public void testNormalize(){
		r = v2.normalize();
		assertEquals(0.7071067811865475, r.getX(), PRECISION);
		assertEquals(0.7071067811865475, r.getY(), PRECISION);
		assertEquals(1, r.length(), PRECISION);
	}

	@Test
	public void testDot(){
		assertEquals(4, v1.dot(v2), PRECISION);
		assertEquals(4, v2.dot(v1), PRECISION);
		v1 = v1.multiply(-1);
		assertEquals(-4, v1.dot(v2), PRECISION);
		v2 = v2.multiply(-1);
		assertEquals(4, v1.dot(v2), PRECISION);
	}

	@Test
	public void testObject(){
		r.setX(v1.getX());
		r.setY(v1.getY());
		
		assertEquals(1, r.getX(), PRECISION);
		assertEquals(1, r.getY(), PRECISION);
		
		v1.setX(10);
		assertEquals(10, v1.getX(), PRECISION);
		assertEquals(1, r.getX(), PRECISION);
		
		v1 = v1.multiply(3.33);
		assertEquals(33.3, v1.getX(), PRECISION);
		assertEquals(1, r.getX(), PRECISION);
		
		v1 = v1.normalize();
		assertEquals(0.995, v1.getX(), PRECISION);		
	}
}
