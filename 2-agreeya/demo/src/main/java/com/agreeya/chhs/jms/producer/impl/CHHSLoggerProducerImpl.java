package com.agreeya.chhs.jms.producer.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.agreeya.chhs.jms.producer.CHHSLoggerProducer;
import com.agreeya.chhs.model.ApplicationLog;

/**
 * @author AgreeYa Solutions
 *
 */
public class CHHSLoggerProducerImpl implements CHHSLoggerProducer {

	private static Logger log = Logger.getLogger(CHHSLoggerProducerImpl.class);

	private JmsTemplate jmsTemplate;
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void log(final ApplicationLog appLog) {
/*		jmsTemplate.send(Constants.CHHS_LOGGER_QUEUE, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage();
				message.setObject(appLog);
				return message;
			}
		});
*/		//sessionFactory.getCurrentSession().save(appLog);
		log.info("Sent Application Log to JMS server");
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
