package com.pCloudy.result.dto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoMain {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("passed")
	@Expose
	private List<Passed> passed = null;
	@SerializedName("failed")
	@Expose
	private List<Failed> failed = null;
	@SerializedName("skipped")
	@Expose
	private List<Skipped> skipped = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Passed> getPassed() {
		return passed;
	}

	public void setPassed(List<Passed> passed) {
		this.passed = passed;
	}

	public List<Failed> getFailed() {
		return failed;
	}

	public void setFailed(List<Failed> failed) {
		this.failed = failed;
	}

	public List<Skipped> getSkipped() {
		return skipped;
	}

	public void setSkipped(List<Skipped> skipped) {
		this.skipped = skipped;
	}

}
