package com.agreeya.chhs.to;

import java.io.Serializable;

/**
 * @author amit.sharma
 *
 */
public class EmailTo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// addresses
	private String from;
	private String[] to;
	private String[] cc;
	private String[] bcc;

	// content
	private String subject;
	private String content;
	private UserContextTO userContextTO;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserContextTO getUserContextTO() {
		return userContextTO;
	}

	public void setUserContextTO(UserContextTO userContextTO) {
		this.userContextTO = userContextTO;
	}
}
