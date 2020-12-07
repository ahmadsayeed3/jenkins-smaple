package com.pCloudy.result.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Failed {

	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("device")
	@Expose
	private String device;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
