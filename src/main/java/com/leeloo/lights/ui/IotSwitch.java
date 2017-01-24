package com.leeloo.lights.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IotSwitch {
	private int index;
	private boolean state;
	
	public IotSwitch() {}
	public IotSwitch(int index, boolean state) {
		this.index = index;
		this.state = state;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
