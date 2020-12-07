package com.pCloudy.appium.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Shibu Prasad Panda.
 */

public class ConfigurationManager {
	private static ConfigurationManager instance;
	private Properties prop = new Properties();

	private ConfigurationManager(String configFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(configFile);
		prop.load(inputStream);
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	public static ConfigurationManager getInstance() throws IOException {
		if (instance == null) {
			String configFile = "config.properties";
			instance = new ConfigurationManager(configFile);
		}
		return instance;
	}

	public boolean containsKey(String key) {
		return prop.containsKey(key);
	}
}
