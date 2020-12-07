package com.pCloudy.android.tests;

import java.util.logging.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.pCloudy.annotation.values.Author;
import com.pCloudy.annotation.values.Description;
import com.pCloudy.base.UserBaseTest;
import com.pCloudy.pages.pCloudySampleIOSpage;

public class pCloudyIosTest extends UserBaseTest{
	
	Logger logger = Logger.getLogger(pCloudyAndroidTest.class.getName());

	@Test
	@Description(value = "TestCases For Pcloudy Sample Project")
	@Author(name = "Shibu Prasad panda")
	@Parameters({"version"})
	public void sampleIosTest(String version) throws InterruptedException {

		pCloudySampleIOSpage homePageios = new pCloudySampleIOSpage(driver);
        
		
		homePageios.clickOnEmail("testmunk@");
		report.info(" Enter Email id");
		
		
		homePageios.clickOnPassword("testmunk");
		report.info("Enter password");
		
		
		homePageios.clickonLogin();
		report.info("Click on  Login Button");
	
		 
	}
		

}
