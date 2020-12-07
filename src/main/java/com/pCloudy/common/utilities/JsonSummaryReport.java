package com.pCloudy.common.utilities;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pCloudy.report.dto.Device;
import com.pCloudy.report.dto.Report;
import com.pCloudy.report.dto.TestResults;
import com.pCloudy.result.dto.Failed;
import com.pCloudy.result.dto.Passed;
import com.pCloudy.result.dto.PojoMain;
import com.pCloudy.result.dto.Skipped;

public class JsonSummaryReport {

	public Report jsonSummaryReport(String jsonString) {

		final GsonBuilder gsonBuilder = new GsonBuilder();
		final Gson gson = gsonBuilder.create();

		PojoMain[] pojoArray = gson.fromJson(jsonString, PojoMain[].class);
		PojoMain pojoMain = pojoArray[0];

		Map<String, Device> devices = new HashMap<String, Device>();
		System.out.println(pojoMain.getPassed().size());

		for (Passed passed : pojoMain.getPassed()) {

			Device device;
			if (!devices.containsKey(passed.getDevice())) {
				device = new Device();
				device.setDevice(passed.getDevice());
				devices.put(passed.getDevice(), device);
			} else {
				device = devices.get(passed.getDevice());
			}

			TestResults testResults = new TestResults();
			testResults.setName(passed.getName());
			testResults.setResult("pass");
			device.getTestresults().add(testResults);
		}

		for (Failed failed : pojoMain.getFailed()) {

			Device device;
			if (!devices.containsKey(failed.getDevice())) {
				device = new Device();
				devices.put(failed.getDevice(), device);
			} else {
				device = devices.get(failed.getDevice());
			}

			TestResults testResults = new TestResults();
			testResults.setName(failed.getName());
			testResults.setResult("fail");
			device.getTestresults().add(testResults);
		}

		for (Skipped skipped : pojoMain.getSkipped()) {

			Device device;
			if (!devices.containsKey(skipped.getDevice())) {
				device = new Device();
				device.setDevice(skipped.getDevice());
				devices.put(skipped.getDevice(), device);
			} else {
				device = devices.get(skipped.getDevice());
			}

			TestResults testResults = new TestResults();
			testResults.setName(skipped.getName());
			testResults.setResult("skip");
			device.getTestresults().add(testResults);
		}

		Report report = new Report();
		report.setName("Pcloudy Suite");

		for(String key: devices.keySet()) {
			report.getDevices().add(devices.get(key));
		}
		System.out.println(report.getDevices().size());
		Gson gson2 = new Gson();
		String finalJson = gson2.toJson(report);
		System.out.println(finalJson);
		return report;
	}
}
