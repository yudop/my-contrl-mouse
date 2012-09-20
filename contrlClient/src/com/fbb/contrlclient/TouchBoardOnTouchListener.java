package com.fbb.contrlclient;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class TouchBoardOnTouchListener implements OnTouchListener {
	int downX;
	int downY;
	int startX;
	int startY;
	long startTime;

	public boolean onTouch(View v, MotionEvent event) {
		Log.d("fbb", "fbb onTouch:" + event.getAction());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) event.getX();
			startY = (int) event.getY();
			downX = startX;
			downY = startY;
			startTime = System.currentTimeMillis();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) event.getX();
			int moveY = (int) event.getY();
			Event point = new Event(moveX - startX, moveY - startY, Event.MOVE);
			Data.getInstance().events.add(point);
			startX = moveX;
			startY = moveY;
			break;
		case MotionEvent.ACTION_UP:
			long duration = System.currentTimeMillis() - startTime;
			int upX = (int) event.getX();
			int upY = (int) event.getY();
			if (duration > 250 || Math.abs(upX - downX) > 5
					|| Math.abs(upY - downY) > 5) {
				return true;
			} else {
				return false;
			}
		default:
			break;
		}
		return false;
	}

}
