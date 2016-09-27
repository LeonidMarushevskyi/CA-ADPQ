package com.agreeya.chhs.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.agreeya.chhs.dao.ApplicationLoggerDAO;
import com.agreeya.chhs.model.ApplicationLog;

/**
 * Implementation class for ApplicationLoggerDAO.
 * @author AgreeYa Solutions
 *
 */
public class ApplicationLoggerDAOImpl extends BaseDAOImpl implements ApplicationLoggerDAO {

	private static Logger logging = Logger.getLogger(ApplicationLoggerDAOImpl.class);
	
	@Override
	@Transactional
	public void saveLog(ApplicationLog log) {
		logging.info("Saving the Application log");
		save(log);
	}

}
