package com.pCloudy.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.pCloudy.appium.utils.ConfigurationManager; 

public class AlterSuite implements IAlterSuiteListener {

	public ConfigurationManager prop;
	public AlterSuite() {
		try {
			prop = ConfigurationManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void alter(List<XmlSuite> suites) {
		AlterSuite alterSuite = new AlterSuite();
		String jsonString = System.getenv("pCloudy_Devices");
		alterSuite.validatejSonString(jsonString);
		List<Map<String, String>> deviceList = alterSuite.getDevices(jsonString);
		XmlSuite suite = suites.get(0);
		suite.setThreadCount(deviceList.size());
		suite.setPreserveOrder(true);
		List<XmlTest> dynamictests = suite.getTests().stream().filter(xmlTest -> xmlTest.getName().startsWith("Test")).collect(Collectors.toList());
		dynamictests.get(0).getLocalParameters().put("device", deviceList.get(0).get("name"));
		dynamictests.get(0).getLocalParameters().put("version", deviceList.get(0).get("version"));
		
		List<XmlTest> clonedTests = new ArrayList<>();
		for (XmlTest each : dynamictests) {
			for (int i = 1; i < deviceList.size(); i++) {
				XmlTest cloned = new XmlTest(suite);
				cloned.setName(deviceList.get(i).get("name"));
				cloned.getLocalParameters().put("device", deviceList.get(i).get("name"));
				cloned.getLocalParameters().put("version", deviceList.get(i).get("version"));
				cloned.getXmlClasses().addAll(each.getClasses());
				clonedTests.add(cloned);
			}
		}
		dynamictests.addAll(clonedTests);
	}
	
	@SuppressWarnings("resource")
	public String readText() {
		// pass the path to the file as a parameter
		String value = "";
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/"+prop.getProperty("DEVICES_JSON_FILE_NAME"));
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine())
				value += sc.nextLine();
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return value;
	}

	public void validatejSonString(String jsonString) {
		if(jsonString.trim().isEmpty()) {
			System.out.println("Empty JsonString");
			throw new RuntimeException("Emty Device Json File");
		}

	}
	public static List<Map<String, String>> getDevices(String jsonString){
		List<Map<String, String>> deviceList = new ArrayList<Map<String, String>>();
		JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("devices");
		for(int i =0; i<jsonArray.length(); i++) {
			Map<String, String> device = new HashMap<String, String>() ;
			String fullName = jsonArray.getString(i);
			String[] deviceArray = fullName.split("_");
			device.put("name", fullName);
			device.put("version", deviceArray[deviceArray.length-2]);
			deviceList.add(device);
		}
		return deviceList;
	}
/*
	public List<Map<String, String>> getDevices(String jsonString){
		List<Map<String, String>> deviceList = new ArrayList<Map<String, String>>();

		JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("devices");
		for(int i =0; i<jsonArray.length(); i++) {
			Map<String, String> device = new HashMap<String, String>() ;
			JSONObject jsonObject = jsonArray.getJSONObject(i);

		//	device.put("name", jsonObject.getString("name"));
		//	device.put("version", jsonObject.getString("version"));
			deviceList.add(device);
		}
		return deviceList;
	}*/

}