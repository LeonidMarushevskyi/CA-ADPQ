package com.agreeya.chhs.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author AgreeYa Solutions
 *
 */
public class ResourceBundleUtil extends PropertyPlaceholderConfigurer {
	private static Logger log = Logger.getLogger(ResourceBundleUtil.class);
	private static Map propertiesMap;

	/**
	 * @purpose this method is used to read message.properties file get file all properties.
	 * 
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		log.info("enter into ResourceBundleUtil processProperties() method.............");
		propertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			propertiesMap.put(keyStr, parseStringValue(props.getProperty(keyStr), props, new HashSet()));
		}
	}

	public static String getProperty(String name) {
		log.info("enter into ResourceBundleUtil getProperty() method.............");
		return (String) propertiesMap.get(name);
	}

}
