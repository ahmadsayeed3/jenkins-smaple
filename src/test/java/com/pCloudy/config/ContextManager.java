package com.pCloudy.config;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * Created by pCloudy.com.
 *
 * @author: Shibu Prasad Panda
 * Date:    11.05.2019
 * Purpose:	To set the configuration for type of drivers
 */

public class ContextManager {

    private static ThreadLocal<ExtentTest> extentReportForTestMethod = new ThreadLocal<>();

    public static ExtentTest getExtentReportForTestMethods() {
        return extentReportForTestMethod.get();
    }

    public static void setExtentReportsForTestMethods(ExtentTest extent) {
        extentReportForTestMethod.set(extent);
    }
    
    private static ThreadLocal<ExtentTest> extentReportForClassMethod = new ThreadLocal<>();

    public static ExtentTest getExtentReportForClassMethods() {
        return extentReportForClassMethod.get();
    }

    public static void setExtentReportsForClassMethods(ExtentTest extent) {
    	extentReportForClassMethod.set(extent);
    }
    
    private static ThreadLocal<ExtentTest> extentReportForPrecondition = new ThreadLocal<>();

    public static ExtentTest getExtentReportForPrecondition() {
        return extentReportForPrecondition.get();
    }

    public static void setExtentReportsForPrecondition(ExtentTest extent) {
    	extentReportForPrecondition.set(extent);
    }
    
    private static ThreadLocal<AppiumDriver<MobileElement>> androidDriver = new ThreadLocal<>();
    
    public static void setAndroidDriver(AppiumDriver<MobileElement> driver) {
    	androidDriver.set(driver);
    }
    
    public static AppiumDriver<MobileElement> getAndroidDriver(){
    	return androidDriver.get();
    }

}

