package com.pCloudy.common.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;



/**
 * 
 * @author Shibu Prasad Panda This module is used to Write and read the Property File.
 *
 */
public class PropertyHandler {

	public final Logger log = Logger.getLogger(PropertyHandler.class.getName());


	/**
	 * 
	 * @param propertyName
	 *            -Set property name
	 * @param key
	 *            -Set the key for get the value
	 * @return-return the value of Key
	 */
	public static String getKeyValues(String propertyFileName, String key) {
		try {
			Properties prop = new Properties();
			InputStream input = null;
			String value = "";

			input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + propertyFileName);
			prop.load(input);
			value = prop.getProperty(key);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return "No such value present in mentioned key";
		}
	}

	/**
	 * 
	 * @param propertyName
	 *            -Set property name
	 * @return
	 */
	public static Properties loadproperty(String propertyFileName) {

		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("property/" + propertyFileName);
		System.out.println(resourceAsStream);
		Properties propertyValue = new Properties();
		if (resourceAsStream != null) {
			try {
				propertyValue.load(resourceAsStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return propertyValue;

		}
		return propertyValue;
	}

	/**
	 * This Method used to write property file based on set property values
	 * 
	 * @param propertyFile-
	 *            Locating the property file
	 * @param propertyName
	 *            -Set property name
	 * @param value-
	 *            Value of the property
	 * @throws IOException-
	 *             throws exception if anything goes wrong
	 */
	public static void WriteProperties(String propertyFileName, String propertyName, String value) {

		try {

			FileInputStream in = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/" + propertyFileName);
			Properties props = new Properties();
			props.load(in);
			in.close();
			props.setProperty(propertyName, value);
			FileOutputStream fileOut = new FileOutputStream(
					System.getProperty("user.dir") + "/src/test/resources/" + propertyFileName);
			props.store(fileOut, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

