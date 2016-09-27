package com.agreeya.chhs.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * utility is used to get the server side validation message from the properties file
 * @author AgreeYa Solutions
 *
 */
public class HibernateValidationErrorUtil extends PropertyPlaceholderConfigurer {

	private static Map propertiesMap;

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);

		propertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			propertiesMap.put(keyStr, parseStringValue(props.getProperty(keyStr), props, new HashSet()));
		}
	}

	public static String getProperty(String name) {
		return (String) propertiesMap.get(name);
	}

}
