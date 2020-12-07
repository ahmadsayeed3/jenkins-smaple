package com.pCloudy.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import org.openqa.selenium.support.PageFactory;
import com.pCloudy.appium.utils.CommonAppiumTest;
import com.pCloudy.pageobjects.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PcloudyHomePage extends CommonAppiumTest{

	static Logger log = Logger.getLogger(PcloudyHomePage.class.getName());
	public pCloudyHomePageObject homePgaeObject = new pCloudyHomePageObject();

	public PcloudyHomePage(AppiumDriver<MobileElement>driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), homePgaeObject);
	}

	/**
	 * This method is to click "Allow" Button
	 */
	public void clickOnAllowButton( ) {
		waitForElementInSeconds(homePgaeObject.allowButton(), 20);
		clickOnElement(homePgaeObject.allowButton());
	}

	/**
	 * This method is to click "Flight button"
	 */
	public void clickOnflightButton() {
		waitForElementInSeconds(homePgaeObject.getFlightButton(), 60);
		clickOnElement(homePgaeObject.getFlightButton());
		
	}
	
	/**
	 * Method return is Flight Buttonis displayed or not
	 * @return
	 * @throws InterruptedException
	 */
	public boolean isFlightButtonDisplayed() throws InterruptedException {
		try {
			isElementVisible(homePgaeObject.getFlightButton());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * Method to tap on spinner from
	 * @throws InterruptedException
	 */
	public void tapOnspinnerfrom() throws InterruptedException {
		clickOnElement(homePgaeObject.spinnerfrom());
	}
	
	/**
	 * method to click on Downlaod Button For select the city
	 * @throws InterruptedException 
	 */
	public void clickOnselectCity() throws InterruptedException {
	
		clickOnElement(homePgaeObject.getSelectCity());
	}
	
	
}

