package com.example2.utils;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	private static final Properties PROPERTIES = new Properties();

	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}

	static {
		loadProperties();
	}

	private static void loadProperties() {
		try {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("database.properties");
			
			PROPERTIES.load(is);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PropertiesUtil() {

	}
}
