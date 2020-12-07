package com.pCloudy.base;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pCloudy.common.utilities.JsonSummaryReport;

public class Reporter implements IReporter {

	@SuppressWarnings("unchecked")
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		ObjectMapper mapper = new ObjectMapper();
		JSONArray results = new JSONArray();
		suites.forEach(element->{
			results.add(createSuiteJsonObject(element));
		});
		try (FileWriter file = new FileWriter(System.getProperty("user.dir") + "/target/report.json")) {
			JsonSummaryReport summaryJsonReport = new JsonSummaryReport();
			file.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(summaryJsonReport.jsonSummaryReport(results.toString())));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("#gotcha #1");
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject createSuiteJsonObject(ISuite suite) {
		JSONObject result = new JSONObject();
		JSONArray passedMethods = new JSONArray();
		JSONArray failedMethods = new JSONArray();
		JSONArray skippedMethods = new JSONArray();
		suite.getResults().entrySet().forEach(element -> {
			ITestContext context = element.getValue().getTestContext();
			passedMethods.addAll(createResultJsonArray(context.getPassedTests().getAllResults(), context));
			failedMethods.addAll(createResultJsonArray(context.getFailedTests().getAllResults(), context));
			skippedMethods.addAll(createResultJsonArray(context.getSkippedTests().getAllResults(), context));
		});
		result.put("name", suite.getName());
		result.put("passed", passedMethods);
		result.put("failed", failedMethods);
		result.put("skipped", skippedMethods);
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONArray createResultJsonArray(Set<ITestResult> results, ITestContext context) {
		JSONArray result = new JSONArray();
		results.forEach(element ->{
			JSONObject currentJsonResult = new JSONObject();
			currentJsonResult.put("device", context.getCurrentXmlTest().getParameter("device"));
			currentJsonResult.put("name", element.getMethod().getMethodName());
			result.add(currentJsonResult);
		});
		return result;
	}


}
