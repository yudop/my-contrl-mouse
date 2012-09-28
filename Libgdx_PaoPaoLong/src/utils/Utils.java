package utils;

import model.Ball;

public class Utils {
	
	private static Utils instance;
	private Utils(){}
	public static Utils getInstance(){
		if(instance==null){
			instance = new Utils();
		}
		return instance;
	}
	
	public boolean isStrike(Ball ball1, Ball ball2){
		return false;
	}

}
