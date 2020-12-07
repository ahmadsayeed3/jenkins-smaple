package com.pCloudy.report.dto;

import java.util.ArrayList;
import java.util.List;

public class Device {
	public String device;
	public List<TestResults> testresults;

	public Device() {
		this.testresults = new ArrayList<TestResults>();
	}
	
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public List<TestResults> getTestresults() {
		return testresults;
	}

	public void setTestresults(List<TestResults> testresults) {
		this.testresults = testresults;
	}

}
