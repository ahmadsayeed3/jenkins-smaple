package com.pCloudy.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pCloudy.annotation.values.Author;
import com.pCloudy.annotation.values.Description;
import com.pCloudy.appium.utils.ConfigurationManager;
import com.pCloudy.common.utilities.ScreenshotUtils;
import com.pCloudy.config.ContextManager;
import com.pCloudy.report.factory.ExtentManager;
import com.pCloudy.report.factory.ExtentTestManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * 
 * @author Shibu Prasad Panda
 *
 */

public class UserBaseTest extends TestListenerAdapter implements ITestListener{

	public AppiumDriver<MobileElement> driver = null;
	public ConfigurationManager prop;
	protected boolean dontStopAppOnReset = false;
	public String device_udid;
	public ExtentTest report;
	public ExtentTest parentReport;
	EnviromentVariables variables=new EnviromentVariables();

	Logger logger = Logger.getLogger(UserBaseTest.class);

	public UserBaseTest() {
		try {
			prop = ConfigurationManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Shibu Prasad Panda
	 * @param name- device name/udid
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	@Parameters({ "device", "version" })
	public void startApp(String device, String version) throws Exception {
		parentReport = ExtentTestManager
				.createTest(getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1),
						device);

		report = parentReport.createNode(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"LaunchApp");
		ContextManager.setExtentReportsForClassMethods(parentReport);
		DesiredCapabilities androidCaps = androidNative(device, version);
		try {
			this.driver = startingServerInstance(androidCaps);
			ContextManager.setAndroidDriver(this.driver);
			report.log(Status.PASS, "Application Launched Successfully");
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			report.fail("Driver not created");
			report.log(Status.ERROR, "<pre>" + sw.toString() + "</pre>");
			throw e;
		}
	}

	@Parameters({ "device" })
	@AfterClass(alwaysRun = true)
	public void stopApp(String device) {
		System.out.println("stopApp");
		if (driver != null) {
			this.driver.quit();
		}
		report = parentReport.createNode(Thread.currentThread().getStackTrace()[1].getMethodName(), "CloseApp");
		report.log(Status.PASS, "Application Closed Successfully");
	}

	@Parameters({ "device" })
	@BeforeMethod(alwaysRun = true)
	public void reportStartUp(Method name, String device) {
		report = parentReport.createNode(name.getName(), name.getAnnotation(Description.class).value())
				.assignAuthor(name.getAnnotation(Author.class).name());
		ContextManager.setExtentReportsForTestMethods(report);
	}

	@Parameters({ "device" })
	@AfterMethod(alwaysRun = true)
	public void reportTearDown(Method name, String device, ITestResult result) throws Exception {
		/*
		 * Success Block
		 */
		if (result.getStatus() == ITestResult.SUCCESS) {
			report.log(Status.PASS, name.getName() + " TestCase Passed");
		}

		/*
		 * Failure Block
		 */
		if (result.getStatus() == ITestResult.FAILURE) {
			report.log(Status.FAIL, name.getName() + " TestCase Failed \n " + result.getThrowable().getMessage());
			report.addScreenCaptureFromBase64String(ScreenshotUtils.base64conversion(ContextManager.getAndroidDriver()), name.getName());
		}
		System.out.println("reportTearDown");
	}

	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		System.out.println("My Report Flushed Start.....");
		if (ExtentManager.getExtent() != null) {
			ExtentManager.getExtent().flush();
			System.out.println("Report Flushed.....");
		}

		System.out.println("My Report Flushed End.....");
	}

	/**
	 * This will return the Desired Capabilities instance
	 * @param device Id
	 * @return capabilities instance
	 * @throws IOException 
	 */
	public synchronized DesiredCapabilities androidNative(String device_udid, String version) throws IOException {

		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			DesiredCapabilities androidCapabilities = new DesiredCapabilities();
			androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
					prop.getProperty("PLATFORM"));
			androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					prop.getProperty("APP_ACTIVITY"));
			androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, 
					prop.getProperty("APP_PACKAGE"));
			androidCapabilities.setCapability(MobileCapabilityType.APP,
					System.getProperty("user.dir")+"/App/"+prop.getProperty("APP_NAME"));
			androidCapabilities.setCapability(MobileCapabilityType.UDID, device_udid);
			if (checkDeviceVersion(version)) {
				androidCapabilities.setCapability("automationName", "UiAutomator2");
				androidCapabilities.setCapability("uiautomator2ServerLaunchTimeout", 90000);
				androidCapabilities.setCapability("noSign", true);
			}else {
				androidCapabilities.setCapability("automationName", "UiAutomator1");
			}
			if (dontStopAppOnReset == true) {
				androidCapabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				androidCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			androidCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
			return androidCapabilities;
		} 
		else if(prop.getProperty("Runner").equalsIgnoreCase("pCloudyAndroid")){
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("pCloudy_Username", prop.getProperty("pCloudy_Username"));
			capabilities.setCapability("pCloudy_ApiKey", prop.getProperty("pCloudy_ApiKey"));
			capabilities.setCapability("pCloudy_ApplicationName", prop.getProperty("pCloudy_ApplicationName"));
			capabilities.setCapability("pCloudy_DurationInMinutes", prop.getProperty("pCloudy_DurationInMinutes"));
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("appPackage", prop.getProperty("APP_PACKAGE"));
			capabilities.setCapability("appActivity", prop.getProperty("APP_ACTIVITY"));
			capabilities.setCapability("noReset", true);
			if (checkDeviceVersion(version)) {
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("uiautomator2ServerLaunchTimeout", 90000);
				capabilities.setCapability("noSign", true);
			}else {
				capabilities.setCapability("automationName", "UiAutomator1");
			}
			return capabilities;
		}
		else if(prop.getProperty("Runner").equalsIgnoreCase("pCloudyIOS"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("pCloudy_Username", prop.getProperty("pCloudy_Username"));
			capabilities.setCapability("pCloudy_ApiKey", prop.getProperty("pCloudy_ApiKey"));
			capabilities.setCapability("pCloudy_ApplicationName", prop.getProperty("pCloudy_ApplicationName"));
			capabilities.setCapability("pCloudy_DurationInMinutes", prop.getProperty("pCloudy_DurationInMinutes"));
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("bundleId", prop.getProperty("BUNDLEID"));
			capabilities.setCapability("acceptAlerts", true);
			capabilities.setCapability("automationName", "XCUITest");

			return capabilities;

		}
		else
		{
			System.out.println("Please Select pCloudyAndroid OR pCloudyIOS in properties File");
			return null;
		}
	}

	/**
	 * This will Start the Server 
	 * @param Capabilities
	 * @return Driver Instance
	 * @throws Exception
	 */
	public AppiumDriver<MobileElement> startingServerInstance(DesiredCapabilities androidCaps) throws Exception {

		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), androidCaps);
		} else if (prop.getProperty("Runner").equalsIgnoreCase("pCloudyAndroid")){
			driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("pCloudy_Endpoint")+"/appiumcloud/wd/hub"),
					androidCaps);
		}

		else
		{
			driver = new IOSDriver<MobileElement>(new URL(prop.getProperty("pCloudy_Endpoint")+"/appiumcloud/wd/hub"),
					androidCaps);
		}
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		return driver;
	}

	/**
	 * This method is to check Memory is deducted or not
	 * @param availableMemoryBeforeDownload
	 * @param availableMemoryAfterDownload
	 * @return
	 */
	public boolean isMemoryDeducted(double availableMemoryBeforeDownload, double availableMemoryAfterDownload) {

		if (availableMemoryBeforeDownload > availableMemoryAfterDownload)
			return true;
		else
			return false;
	}

	/**
	 * This method is to check Memory is reverted or not
	 * @param availableMemoryBeforeCancel -Memory Space At start
	 * @param availableMemoryAfterCancel  -Memory Space After Cancellation
	 * @return-return the boolean value
	 */
	public boolean isMemoryReverted(double availableMemoryAfterCancel, double availableMemoryBeforeCancel) {

		if (availableMemoryAfterCancel > availableMemoryBeforeCancel)
			return true;
		else
			return false;
	}

	public boolean isEpisodeFitOnDeviceQueue(int startEpisode, int lastEpisode, Set<Integer> episodeList) {
		for (Integer episode : episodeList) {
			if (episode >= startEpisode && episode <= lastEpisode) {
				return true;
			} else {
				break;
			}
		}
		return false;
	}

	/**
	 * To check whether the device version is >=6
	 * @param deviceVersion
	 * @return true/false
	 */
	public boolean checkDeviceVersion(String deviceVersion) {
		try {
			String str = deviceVersion.substring(0,1);
			int version = Integer.parseInt(str);
			if (version >= 6) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("version not given. Required to set automationName:UiAutomator2");
		}

		return false;
	}

}
