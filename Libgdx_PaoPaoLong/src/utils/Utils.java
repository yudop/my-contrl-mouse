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
		double hypot = Math.hypot(ball1.positionX-ball2.positionX, ball1.positionY-ball2.positionY);
		
		if(hypot<=(ball1.radius+ball2.radius)){
			return true;
		}else{
			return false;
		}
	}

}
