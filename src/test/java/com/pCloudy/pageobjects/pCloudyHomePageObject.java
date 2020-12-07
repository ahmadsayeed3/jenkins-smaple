package com.pCloudy.pageobjects;

import com.pCloudy.annotation.values.ElementDescription;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
;

/**
 * 
 * @author Shibu Prasad Panda
 * 
 * This class is used to store the objects of Android Page.
 *
 */

public class pCloudyHomePageObject {

	@ElementDescription(value = "Allow Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/accept']")
	private MobileElement allowButton;

	@ElementDescription(value = "flight Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/flightButton']")
	private MobileElement flightButton;

	
	@ElementDescription(value = " spinner from")
	@AndroidFindBy(xpath = "//android.widget.Spinner[@resource-id='com.pcloudy.appiumdemo:id/spinnerfrom']")
	private MobileElement spinnerfrom;

	@ElementDescription(value = " Select Bangalore Title")
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Bangalore, India (BLR)']")
	private MobileElement selectCity;


	public MobileElement getSelectCity() {
		return selectCity;
	}


	public MobileElement allowButton() {
		return allowButton;
	}


	public MobileElement getFlightButton() {
		return flightButton;
	}

	public MobileElement spinnerfrom() {
		return spinnerfrom;
	}

	



}
