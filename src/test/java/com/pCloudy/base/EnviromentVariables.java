package com.pCloudy.base;

public class EnviromentVariables extends UserBaseTest{
	public String getpCloudy_CloudUrl()
	{
		String pCloudy_CloudUrl = System.getenv("pCloudy_CloudUrl");
		System.out.println("pCloudy_CloudUrl : " + pCloudy_CloudUrl);
		return pCloudy_CloudUrl;

	}
	public String getpCloudy_ApiKey()
	{
		String pCloudy_ApiKey = System.getenv("pCloudy_ApiKey");
		System.out.println("pCloudy_ApiKey : " + pCloudy_ApiKey);
		return pCloudy_ApiKey;

	}
	public String getpCloudy_ApplicationName()
	{
		String pCloudy_ApplicationName = System.getenv("pCloudy_ApplicationName");
		System.out.println("pCloudy_ApplicationName : " + pCloudy_ApplicationName);
		return pCloudy_ApplicationName;

	}

	public String getpCloudy_Username()
	{
		String pCloudy_Username = System.getenv("pCloudy_Username");
		System.out.println("pCloudy_Username : " + pCloudy_Username);
		return pCloudy_Username;

	}
	public String getpCloudy_DurationInMinutes()
	{
		String pCloudy_DurationInMinutes = System.getenv("pCloudy_DurationInMinutes");
		System.out.println("pCloudy_DurationInMinutes : " + pCloudy_DurationInMinutes);
		return pCloudy_DurationInMinutes;

	}
	public String getdeviceJSON()
	{
		String deviceJSON = System.getenv("pCloudy_Devices");
		System.out.println("deviceJSON: " + deviceJSON);
		return deviceJSON;

	}




}
