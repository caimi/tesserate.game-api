package com.tesserate.game.api.util;

public class Util {

	public static int rnd(int x){
		return (int) Math.rint(Math.random()*x);
	}
	public static int rnd(int min, int max){
		return (int) Math.rint(Math.random()*(max-min))+min;
	}

	public static double rnd(double x){
		return Math.rint(Math.random()*x);
	}
	public static double rnd(double min, double max){
		return Math.rint(Math.random()*(max-min))+min;
	}

	public static String removeLowerCase(String msg) {
		String upperCase = "ABCDEFGHIJKLMNOPKRSTUVXWYZ";
		String result = "";
		
		for (int i = 0; i < msg.length(); i++)
			for (int j = 0; j < upperCase.length(); j++){
				if(msg.charAt(i)==upperCase.charAt(j)){
					result += msg.charAt(i);
				}
			}
		return result;
	}
}
