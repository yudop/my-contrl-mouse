package utils;


public class PPData {

	private static PPData instance;
	private PPData(){}
	public static PPData getInstance(){
		if(instance==null){
			instance = new PPData();
		}
		return instance;
	}
	
	public int screenWidth;
	public int screenHeight;
	public int ballRadius;

}
