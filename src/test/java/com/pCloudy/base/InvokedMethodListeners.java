package com.pCloudy.base;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.pCloudy.common.utilities.ScreenshotUtils;
import com.pCloudy.config.ContextManager;


public class InvokedMethodListeners extends UserBaseTest implements IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		if(method.getTestMethod().getMethodName().contains("Precondition")) {
			report = ContextManager.getExtentReportForClassMethods().createNode(method.getTestMethod().getMethodName(), "");
			ContextManager.setExtentReportsForPrecondition(report);
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.getTestMethod().getMethodName().contains("Precondition")) {
			if (testResult.getStatus() == ITestResult.SUCCESS) {
				System.out.println("PASS");
				ContextManager.getExtentReportForPrecondition().log(Status.PASS, method.getTestMethod().getMethodName() + "Test Passed");
			}

			if (testResult.getStatus() == ITestResult.FAILURE) {
				System.out.println("FAIL");
				ContextManager.getExtentReportForPrecondition().log(Status.FAIL, method.getTestMethod().getMethodName() + "Test Failed \n"+testResult.getThrowable().getMessage());
				try {
					ContextManager.getExtentReportForPrecondition().addScreenCaptureFromBase64String(ScreenshotUtils.base64conversion(ContextManager.getAndroidDriver()), method.getTestMethod().getMethodName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (testResult.getStatus() == ITestResult.SKIP) {
				System.out.println("SKIP");
				ContextManager.getExtentReportForPrecondition().log(Status.SKIP, method.getTestMethod().getMethodName() + "Test Skiped");
			}
		}
	}
}