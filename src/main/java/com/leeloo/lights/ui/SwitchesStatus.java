package com.leeloo.lights.ui;

public class SwitchesStatus {
	private SwitchDto[] statuses;
	public SwitchesStatus() {}
	
	public SwitchesStatus(SwitchDto[] statuses) {
		this.statuses = statuses;
	}
	
	public SwitchDto[] getStatuses() {
		return statuses;
	}
}	
