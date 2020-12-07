package com.pCloudy.pages;

import java.time.Duration;
import java.util.logging.Logger;
import org.openqa.selenium.support.PageFactory;
import com.pCloudy.appium.utils.CommonAppiumTest;
import com.pCloudy.pageobjects.PcloudyIOSPageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class pCloudySampleIOSpage extends CommonAppiumTest{

	static Logger log = Logger.getLogger(PcloudyHomePage.class.getName());
	public PcloudyIOSPageObject IOShomePgaeObject = new PcloudyIOSPageObject();

	public pCloudySampleIOSpage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), IOShomePgaeObject);
	}

	/**
	 * This method is to Send "Email"
	 */
	
	public void clickOnEmail(String keysToSend) {
		waitForElementInSeconds(IOShomePgaeObject.getEmailID(), 20);
		sendkeys(IOShomePgaeObject.getEmailID(), keysToSend);
		
	}

	/**
	 * This method is to Send "Password"
	 */
	public void clickOnPassword(String keysToSend) {
		waitForElementInSeconds(IOShomePgaeObject.getPassword(), 60);
		sendkeys(IOShomePgaeObject.getPassword(), keysToSend);

	}

	/**
	 * Method to tap on Login Button
	 * @throws InterruptedException
	 */
	public void clickonLogin() throws InterruptedException {
		clickOnElement(IOShomePgaeObject.getLogIn());
	}

}
