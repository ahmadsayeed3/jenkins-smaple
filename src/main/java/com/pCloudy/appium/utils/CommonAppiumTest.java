package com.pCloudy.appium.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonAppiumTest extends CommandPrompt{

	public AppiumDriver<MobileElement> driver;

	public CommonAppiumTest(AppiumDriver<MobileElement>driver) {
		this.driver = driver;
	}

	/**
	 * Click on element
	 * @param element
	 */
	public void clickOnElement(MobileElement element) {
		element.click();
	}

	/**
	 * SendText  to textFiled
	 * @param element
	 * @param TextWhich need to send to textfield
	 */
	public void sendkeys(MobileElement element,String keysToSend) {
		element.sendKeys(keysToSend);;
	}
	/**
	 * Extract Text from Element
	 * @param element
	 * @return text
	 */
	public String getTexOfElement(MobileElement element) {
		return element.getText();
	}

	/**
	 * Check whether element is displayed
	 * @param element
	 * @return boolean
	 */
	public boolean isElementVisible(MobileElement element) {
		return element.isDisplayed();
	}

	/**
	 * 
	 * @param element
	 * @return Point(x and Y co-ordinate)
	 */
	public Point getLocationOfElement(MobileElement element) {
		return element.getLocation();
	}

	/**
	 * 
	 * @param element
	 * @return X co-ordinate
	 */
	public int getXCoordinateOfElement(MobileElement element) {
		return element.getLocation().getX();
	}

	/**
	 * 
	 * @param element
	 * @return Y co-ordinate
	 */
	public int getYCoordinateOfElement(MobileElement element) {
		return element.getLocation().getY();
	}

	/**
	 * Click On Home Button
	 */
	public void clickOnHomeButton() {
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.HOME));
	}

	/**
	 * Lock the device
	 */
	public void lockTheDevice() {
		((AndroidDriver<MobileElement>) driver).lockDevice();
	}

	/**
	 * Check if device is locked
	 * @return 
	 */
	public boolean isDeviceLocked() {
		boolean isLocked = ((AndroidDriver<MobileElement>) driver).isDeviceLocked();
		return isLocked;
	}

	/**
	 * Unlock the device
	 */

	public void unlockTheDevice() {
		((AndroidDriver<MobileElement>) driver).unlockDevice();
	}

	/**
	 * Wait for Element
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementInSeconds(MobileElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for Element
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementInMinutes(MobileElement element, long timeOutInMinutes) {
		long seconds = TimeUnit.MINUTES
			    .toSeconds(timeOutInMinutes);
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * Enable Wifi
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void enableWifi() throws InterruptedException, IOException {

		ConfigurationManager prop = ConfigurationManager.getInstance();
		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			((AndroidDriver<MobileElement>) driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
			System.out.println("Enable Wifi");
		}
		else {
			driver.executeScript("pCloudy_enableWifi", true);
		}
	}

	/**
	 * Open Notification bar
	 */
	public void clickOnopenNotificationDrawer() {
		((AndroidDriver<MobileElement>) driver).openNotifications();

	}

	public void backButton() {
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
	}

	/**
	 * disable Wifi
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void disableWifi() throws InterruptedException, IOException {
		ConfigurationManager prop = ConfigurationManager.getInstance();
		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			((AndroidDriver<MobileElement>) driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
			System.out.println("Disable Wifi");
		}
		else {
			driver.executeScript("pCloudy_enableWifi", false);

		}
	}

	public void resetApplication() {
		driver.resetApp();
	}

	/**
	 * Launch the App again.
	 */

	public void launchApp() {
		driver.launchApp();
	}

	/**
	 * Swiping from top panel to open quick settings.
	 */
	public void scrollQuickSettings()
	{
		Dimension windowSize = driver.manage().window().getSize();
		int starty = (int) (windowSize.height * 0.10);
		int endy = (int) (windowSize.height * 0.90);
		int startx = windowSize.width / 2;
		new TouchAction<>(driver).press(PointOption.point(startx, endy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startx, starty)).release().perform();
	}

	/**
	 * Keep App in background
	 */
	public void runInBackgroundApp() {
		driver.runAppInBackground(Duration.ofMillis(10));
	}

	/**
	 * Open Background app
	 */
	public void openbackGroundAPP()
	{
		((StartsActivity) driver).currentActivity();
	}

	/**
	 * 
	 * @param srcElement
	 * @param destElement
	 * @throws InterruptedException
	 */
	public void horizontalSwipeToElement(MobileElement srcElement, MobileElement destElement) throws InterruptedException{

		try{
			MobileElement firstElement = srcElement;
			MobileElement secondElement = destElement;

			Point source = firstElement.getLocation();
			int width = secondElement.getLocation().x;
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence dragNDrop = new Sequence(finger, 1);
			dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
					PointerInput.Origin.viewport(), source.x, source.y));
			dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
			dragNDrop.addAction(new Pause(finger, Duration.ofMillis(600)));
			dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),
					PointerInput.Origin.viewport(),
					width, source.y));
			dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
			driver.perform(Arrays.asList(dragNDrop));
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}


}
