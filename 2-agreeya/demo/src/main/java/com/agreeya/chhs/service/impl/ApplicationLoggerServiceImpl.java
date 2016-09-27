package com.agreeya.chhs.service.impl;

import org.apache.log4j.Logger;

import com.agreeya.chhs.dao.ApplicationLoggerDAO;
import com.agreeya.chhs.model.ApplicationLog;
import com.agreeya.chhs.service.ApplicationLoggerService;

/**
 * Implementation for ApplicationLoggerService
 * 
 * @author AgreeYa Solutions
 *
 */
public class ApplicationLoggerServiceImpl implements ApplicationLoggerService {

	private ApplicationLoggerDAO applicationLoggerDAO;
	private static Logger logging = Logger.getLogger(ApplicationLoggerServiceImpl.class);

	@Override
	public void log(ApplicationLog applicationLog) {
		logging.info("ApplicationLoggerServiceImpl hand over appLog to chhsLoggerProducer");
		applicationLoggerDAO.saveLog(applicationLog);
	}

	public ApplicationLoggerDAO getApplicationLoggerDAO() {
		return applicationLoggerDAO;
	}

	public void setApplicationLoggerDAO(ApplicationLoggerDAO applicationLoggerDAO) {
		this.applicationLoggerDAO = applicationLoggerDAO;
	}

	
}
