package com.agreeya.chhs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the userinbox database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "userinbox")
public class Userinbox implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userinboxid;
	private String mailbody;
	private String mailcc;
	private String mailfrom;
	private String mailread;
	private String mailto;
	private Date recieveddate;
	private String sentcaseid;
	private String subject;
	private User user;

	public Userinbox() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getUserinboxid() {
		return this.userinboxid;
	}

	public void setUserinboxid(int userinboxid) {
		this.userinboxid = userinboxid;
	}

	@Lob
	public String getMailbody() {
		return this.mailbody;
	}

	public void setMailbody(String mailbody) {
		this.mailbody = mailbody;
	}

	@Column(length = 500)
	public String getMailcc() {
		return this.mailcc;
	}

	public void setMailcc(String mailcc) {
		this.mailcc = mailcc;
	}

	@Column(length = 128)
	public String getMailfrom() {
		return this.mailfrom;
	}

	public void setMailfrom(String mailfrom) {
		this.mailfrom = mailfrom;
	}

	@Column(nullable = false, length = 1)
	public String getMailread() {
		return this.mailread;
	}

	public void setMailread(String mailread) {
		this.mailread = mailread;
	}

	@Column(length = 500)
	public String getMailto() {
		return this.mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getRecieveddate() {
		return this.recieveddate;
	}

	public void setRecieveddate(Date recieveddate) {
		this.recieveddate = recieveddate;
	}

	@Column(length = 45)
	public String getSentcaseid() {
		return this.sentcaseid;
	}

	public void setSentcaseid(String sentcaseid) {
		this.sentcaseid = sentcaseid;
	}

	@Column(length = 500)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userid", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
