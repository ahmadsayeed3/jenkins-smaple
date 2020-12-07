package com.pCloudy.android.tests;


import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.pCloudy.annotation.values.Author;
import com.pCloudy.annotation.values.Description;
import com.pCloudy.base.UserBaseTest;
import com.pCloudy.pages.PcloudyHomePage;


public class pCloudyAndroidTest extends UserBaseTest {

	Logger logger = Logger.getLogger(pCloudyAndroidTest.class.getName());

	@Test
	@Description(value = "TestCases For Pcloudy Sample Project")
	@Author(name = "Shibu prasad panda")
	@Parameters({"version"})
	public void SampleAndroidTest(String version) throws InterruptedException {

		PcloudyHomePage homePage = new PcloudyHomePage(driver);


		homePage.clickOnAllowButton();
		report.info("click on Allow button");

		
		Assert.assertTrue(homePage.isFlightButtonDisplayed(),
				"Flight button not displayed");
		report.log(Status.PASS, "Flight button displayed");
		homePage.clickOnflightButton();
		report.info("Flight button clicked");
		
		homePage.tapOnspinnerfrom();
		report.info("click on SpinnerFrom ");

		homePage.clickOnselectCity();
		report.info("click To Select city");

	}


}
