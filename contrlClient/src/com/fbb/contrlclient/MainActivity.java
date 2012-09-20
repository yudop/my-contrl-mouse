package com.fbb.contrlclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static String serviceIp = "192.168.1.115";
	public static int port = 5000;
	Button buttonOpenConnection;
	Button buttonOpenTouchBoard;
	TextView result;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonOpenConnection = (Button) findViewById(R.id.button_open);
		buttonOpenTouchBoard = (Button) findViewById(R.id.button_touchboard);
		result = (TextView) findViewById(R.id.textView_result);
		
		buttonOpenConnection.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
			}
		});
		buttonOpenTouchBoard.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TouchBoardActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

}
