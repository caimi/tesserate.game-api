package com.tesserate.game.api.util;

public class Util {

	public static int rnd(int x){
		return (int) Math.rint(Math.random()*x);
	}
	public static int rnd(int min, int max){
		return (int) Math.rint(Math.random()*(max-min))+min;
	}

}
