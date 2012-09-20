package com.fbb.contrlclient;

public class Event {
	public static final int MOVE = 1;
	public static final int CLICK = 2;
	public static final int DOUBLECLICK = 3;
	public static final int RIGHTCLICK = 4;
	private int x;
	private int y;
	private int type;

	public Event(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Event(int type) {
		super();
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}
