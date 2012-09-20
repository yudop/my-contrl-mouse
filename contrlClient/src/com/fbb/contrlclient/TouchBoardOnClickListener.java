package com.fbb.contrlclient;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class TouchBoardOnClickListener implements OnClickListener {
	long clickTime = 0;
	boolean tag = true;
	public void onClick(View v) {
		long time = System.currentTimeMillis();
		if(time - clickTime<200){
			tag = false;
			Event p = new Event(Event.DOUBLECLICK);
			Data.getInstance().events.add(p);
			Log.d("fbb", "fbb DoubleClick");
		}else{
			tag = true;
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					if(tag){
						Log.d("fbb", "fbb Click");
						Event p = new Event(Event.CLICK);
						Data.getInstance().events.add(p);
					}
				}
			}, 200);
		}
		clickTime = time;
	}
	

}
