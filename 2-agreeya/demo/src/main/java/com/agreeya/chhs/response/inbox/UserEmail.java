package com.agreeya.chhs.response.inbox;

import java.util.Date;

/**
 * Transfer Object for Communication
 * @author AgreeYa Solutions
 *
 */
public class UserEmail {

	private String mailbody;
	private String mailcc;
	private String mailfrom;
	private String mailread;
	private String mailto;
	private Date recieveddate;
	private String sentcaseid;
	private String subject;

	public String getMailbody() {
		return mailbody;
	}

	public void setMailbody(String mailbody) {
		this.mailbody = mailbody;
	}

	public String getMailcc() {
		return mailcc;
	}

	public void setMailcc(String mailcc) {
		this.mailcc = mailcc;
	}

	public String getMailfrom() {
		return mailfrom;
	}

	public void setMailfrom(String mailfrom) {
		this.mailfrom = mailfrom;
	}

	public String getMailread() {
		return mailread;
	}

	public void setMailread(String mailread) {
		this.mailread = mailread;
	}

	public String getMailto() {
		return mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	public Date getRecieveddate() {
		return recieveddate;
	}

	public void setRecieveddate(Date recieveddate) {
		this.recieveddate = recieveddate;
	}

	public String getSentcaseid() {
		return sentcaseid;
	}

	public void setSentcaseid(String sentcaseid) {
		this.sentcaseid = sentcaseid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
