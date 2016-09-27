package com.agreeya.chhs.jms.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.agreeya.chhs.dao.ApplicationLoggerDAO;
import com.agreeya.chhs.model.ApplicationLog;

/**
 * @author AgreeYa Solutions
 *
 */
public class CHHSLoggerListener implements MessageListener {
	private static Logger log = Logger.getLogger(CHHSLoggerListener.class);
	private ApplicationLoggerDAO applicationLoggerDAO;
	
	@Override
	public void onMessage(Message message) {
		log.info("Received .... " + message);
		if (message instanceof ObjectMessage) {
			try {
				ObjectMessage objMessage = (ObjectMessage) message;
				ApplicationLog appLog = (ApplicationLog) objMessage.getObject();
				applicationLoggerDAO.saveLog(appLog);
			} catch (Exception ex) {
				log.error(ex);
			}
		} else {
			log.info("Cannot process this message :(" + message);
		}
	}

	public ApplicationLoggerDAO getApplicationLoggerDAO() {
		return applicationLoggerDAO;
	}

	public void setApplicationLoggerDAO(ApplicationLoggerDAO applicationLoggerDAO) {
		this.applicationLoggerDAO = applicationLoggerDAO;
	}

}
