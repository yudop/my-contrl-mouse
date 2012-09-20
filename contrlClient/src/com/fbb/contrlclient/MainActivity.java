package com.fbb.contrlclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	public static String serviceIp = "192.168.1.100";
	public static int port = 5000;
	private BufferedWriter writer;
	int startX;
	int startY;
	private ArrayList<point> points;
	
	
	class point{
		int x;
		int y;
		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		points =new ArrayList<MainActivity.point>();
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Socket socket = new Socket(serviceIp, port);
					writer = new BufferedWriter(new OutputStreamWriter(
							socket.getOutputStream()));
					while(true){
						int size = points.size();
						if(size>0){
							point point = points.get(0);
							String s = "{x:"+point.x+",y:"+point.y+"}";
							Log.d("fbb", "读取一个点："+s);
							writer.write(s.replace("\n", " ") + "\n");
							writer.flush();
							points.remove(0);
							Log.d("fbb", "移除一个点");
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) event.getX();
			startY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) event.getX();
			int moveY = (int) event.getY();
			point point = new point(moveX - startX, moveY - startY);
			points.add(point);
			startX = moveX;
			startY = moveY;
			Log.d("fbb", "加入一个点");
			break;
		case MotionEvent.ACTION_UP:

			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
