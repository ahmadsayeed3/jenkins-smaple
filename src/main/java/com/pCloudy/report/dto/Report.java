package com.pCloudy.report.dto;

import java.util.ArrayList;
import java.util.List;

public class Report {
	public String name;
	List<Device> devices;

	public Report() {
		this.devices = new ArrayList<Device>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
}
