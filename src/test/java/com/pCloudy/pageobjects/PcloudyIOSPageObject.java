package com.pCloudy.pageobjects;

import com.pCloudy.annotation.values.ElementDescription;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * 
 * @author Shibu Prasad Panda
 * 
 * This class is used to store the objects of IOS Page.
 *
 */
public class PcloudyIOSPageObject {

	@ElementDescription(value = "Email id Button")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
	private MobileElement emailID;


	@ElementDescription(value = "Password Button")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]")
	private MobileElement Password;


	@ElementDescription(value = "login Button")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")
	private MobileElement logIn;


	public MobileElement getPassword() {
		return Password;
	}

	public MobileElement getLogIn() {
		return logIn;
	}

	public MobileElement getEmailID() {
		return emailID;
	}





}
