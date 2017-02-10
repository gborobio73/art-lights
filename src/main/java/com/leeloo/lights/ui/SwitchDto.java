package com.leeloo.lights.ui;

public class SwitchDto {
	private int index;
	private boolean state;
	
	public SwitchDto() {}
	
	public SwitchDto(int id, boolean state) {
		this.index = id;
		this.state = state;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int id) {
		this.index = id;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
