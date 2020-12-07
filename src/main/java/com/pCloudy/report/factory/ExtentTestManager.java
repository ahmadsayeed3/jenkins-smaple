package com.pCloudy.report.factory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.util.Properties;

public class ExtentTestManager { // new
	public static Properties prop = new Properties();



	private static ExtentTest test;
	public static ExtentReports extent = ExtentManager.getExtent();


	public synchronized static ExtentTest createTest(String name, String description, String deviceId) {
		test = extent.createTest(name, description).assignCategory(deviceId);
		return test;
	}

	public synchronized static ExtentTest createTest(String name, String deviceId) {
		test = extent.createTest(name).assignCategory(deviceId);
		return test;
	}

}

