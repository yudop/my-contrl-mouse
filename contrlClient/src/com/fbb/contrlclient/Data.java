package com.fbb.contrlclient;

import java.util.ArrayList;

public class Data {
	public ArrayList<Event> events;
	private Data(){
		events = new ArrayList<Event>();
	}
	private static Data instance;
	
	public static Data getInstance(){
		if(instance == null){
			instance = new Data();
		}
		return instance;
	}
	
}
