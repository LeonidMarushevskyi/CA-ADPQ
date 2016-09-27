package com.agreeya.chhs.jms.producer;

import com.agreeya.chhs.model.ApplicationLog;

/**
 * @author AgreeYa Solutions
 *
 */
public interface CHHSLoggerProducer {
	/**
	 * 
	 * This is for log the httprequest and httpresponse 
	 * 
	 * @param appLog 
	 * 
	 */
	void log(ApplicationLog appLog);
}
