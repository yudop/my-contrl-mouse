package com.fbb;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import net.sf.json.JSONObject;

public class ProcressRunnable implements Runnable{
	private Socket client;
	private Robot robot;
	private boolean tag;
	
	public ProcressRunnable(Socket client) {
		super();
		this.client = client;
		tag = true;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			while (tag) {
//				System.out.println("server get input from client socket..");
				String txt = reader.readLine();
				if(txt!=null&& !"".equalsIgnoreCase(txt)){
//					System.out.println("txt:"+txt);
					processContext(txt);
				}else{
					
				}
			}
		} catch (IOException e) {
			tag = false;
			e.printStackTrace();
		}
		
	}
	
	public void stop(){
		tag = false;
	}
	
	private void processContext(String txt) {
		JSONObject jsonObject = JSONObject.fromObject(txt);
		if(jsonObject == null){
			return;
		}
		int type = jsonObject.getInt("type");
		switch (type) {
		case Event.MOVE:
			int x = jsonObject.getInt("x");
			int y  = jsonObject.getInt("y");
			Point mousepoint = MouseInfo.getPointerInfo().getLocation();
			int x2 = (int)mousepoint.getX()+x;
			int y2 = (int)mousepoint.getY()+y;
			robot.mouseMove(x2, y2);
			break;
		case Event.CLICK:
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			break;
		case Event.RIGHTCLICK:
			robot.mousePress(InputEvent.BUTTON3_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_MASK);
			break;
		case Event.DOUBLECLICK:
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			break;
		default:
			break;
		}
		
	}
	
	

}
