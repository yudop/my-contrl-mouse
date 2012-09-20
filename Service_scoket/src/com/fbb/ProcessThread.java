package com.fbb;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import net.sf.json.JSONObject;

public class ProcessThread extends Thread{
	private Socket client;
	private Robot robot;
	private boolean tag;
//	class MyRunnable implements Runnable{
//
//		@Override
//		public void run() {
//			BufferedReader reader;
//			try {
//				reader = new BufferedReader(new InputStreamReader(
//						client.getInputStream()));
//				while (tag) {
//					String txt = reader.readLine();
//					if(txt!=null&& !"".equalsIgnoreCase(txt)){
//						System.out.println("txt:"+txt);
//						processContext(txt);
//					}
//				}
//			} catch (IOException e) {
//				tag = false;
//				e.printStackTrace();
//			}
//		}
//		
//	}
//	private static MyRunnable runnable;
	
	public ProcessThread(Socket client, ProcressRunnable runnable) {
		super(runnable);
		this.client = client;
		tag = true;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}



	private void processContext(String txt) {
		JSONObject jsonObject = JSONObject.fromObject(txt);
		if(jsonObject == null){
			return;
		}
		int x = jsonObject.getInt("x");
		int y  = jsonObject.getInt("y");
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		int x2 = (int)mousepoint.getX()+x;
		int y2 = (int)mousepoint.getY()+y;
		robot.mouseMove(x2, y2);
	}
}
