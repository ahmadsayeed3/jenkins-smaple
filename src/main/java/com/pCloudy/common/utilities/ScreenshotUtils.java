package com.pCloudy.common.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ScreenshotUtils {

	/**
	 * This will capture the screen-shot
	 * @param methodName
	 * @return Image Path
	 * @throws IOException
	 */
	public static String captureScreenShot(String methodName, AppiumDriver<MobileElement> driver) throws IOException {

		String folder_name = "FailedTestsScreenshots";
		String screenShotNameWithTimeStamp = currentDateAndTime();
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String failedScreen = System.getProperty("user.dir") + "/" + folder_name + "/" + screenShotNameWithTimeStamp
				+ "_" + methodName + "_failed" + ".png";
		FileUtils.copyFile(srcFile, new File(failedScreen));
		String filePath = failedScreen.toString();
		return filePath;

	}

	/**
	 * This will return Base64 url
	 * @return Base64
	 * @throws Exception
	 */
	public static String base64conversion(AppiumDriver<MobileElement> driver) throws Exception {
		TakesScreenshot newScreen = (TakesScreenshot) driver;
		String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
		return "data:image/jpg;base64, " + scnShot;

	}

	/**
	 * This will return Current Date and Time
	 * @return CurentDate and Time
	 */
	private static String currentDateAndTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		return now.format(dtf);

	}
}
