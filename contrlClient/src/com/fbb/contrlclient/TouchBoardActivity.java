package com.fbb.contrlclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class TouchBoardActivity extends Activity {

	public static String serviceIp = "192.168.1.115";
	public static int port = 5000;
	private BufferedWriter writer;
	
	long downTime;
	long upTime;
	private Button leftButton;
	private Button rightButton;
	private View touchBoard;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touchboard);
		touchBoard = findViewById(R.id.touch_area);
		leftButton = (Button) findViewById(R.id.leftButton);
		rightButton = (Button) findViewById(R.id.rightButton);
		touchBoard.setOnTouchListener(new TouchBoardOnTouchListener());
		touchBoard.setOnClickListener(new TouchBoardOnClickListener());
		leftButton.setOnClickListener(new TouchBoardOnClickListener());
		rightButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Event p = new Event(Event.RIGHTCLICK);
				Data.getInstance().events.add(p);
				
			}
		});
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Socket socket = new Socket(serviceIp, port);
					writer = new BufferedWriter(new OutputStreamWriter(
							socket.getOutputStream()));
					while(true){
						int size = Data.getInstance().events.size();
						if(size>0){
							Event event = Data.getInstance().events.get(0);
							String s = null;
							if(Event.MOVE == event.getType()){
								s = "{type:"+event.getType()+",x:"+event.getX()+",y:"+event.getY()+"}";
							}else{
								s = "{type:"+event.getType()+"}";
							}
							if(s!=null){
								writer.write(s.replace("\n", " ") + "\n");
								writer.flush();
							}
							Data.getInstance().events.remove(0);
						}
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) { 
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			downTime = System.currentTimeMillis();
//			startX = (int) event.getX();
//			startY = (int) event.getY();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			int moveX = (int) event.getX();
//			int moveY = (int) event.getY();
//			Event point = new Event(moveX - startX, moveY - startY, Event.MOVE);
//			events.add(point);
//			startX = moveX;
//			startY = moveY;
//			Log.d("fbb", "加入一个点");
//			break;
//		case MotionEvent.ACTION_UP:
//			long upTime = System.currentTimeMillis();
//			long duration = upTime - downTime;
//			if(duration <= 250){
//				touchTag = true;
//				Timer timer = new Timer(false); 
//				timer.schedule(new TimerTask() {
//					
//					@Override
//					public void run() {
//						if(touchTag){
//							Log.d("fbb", "fbb taskRun");
//							Event p = new Event(Event.CLICK);
//							events.add(p);
//						}
//					}
//				}, 2000);
//			}
//			break;
//		default:
//			break;
//		}
//		return super.onTouchEvent(event);
//	}
	
	

}
