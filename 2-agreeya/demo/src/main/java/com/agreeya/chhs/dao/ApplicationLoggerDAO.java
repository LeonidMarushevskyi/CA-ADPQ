package com.agreeya.chhs.dao;

import com.agreeya.chhs.model.ApplicationLog;

/**
 * Interface for Application Logging methods
 * @author AgreeYa Solutions
 *
 */
public interface ApplicationLoggerDAO extends BaseDAO {
	void saveLog(ApplicationLog log);
}
