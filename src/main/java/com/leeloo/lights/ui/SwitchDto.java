package com.leeloo.lights.ui;

public class SwitchDto {
	private int id;
	private boolean state;
	
	public SwitchDto() {}
	
	public SwitchDto(int id, boolean state) {
		this.id = id;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
