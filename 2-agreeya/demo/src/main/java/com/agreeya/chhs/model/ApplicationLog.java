package com.agreeya.chhs.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the application log database table.
 * @author AgreeYa Solutions
 * 
 */
@Entity
@Table(name = "applicationlog")
public class ApplicationLog implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String apiName;
	private Date createdOn;
	private String errorCode;
	private String errorText;
	private BigInteger executionTime;
	private String ipAddress;
	private String requestJSON;
	private String responseJSON;
	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length = 255)
	public String getApiName() {
		return this.apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(length = 255)
	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Column(length = 255)
	public String getErrorText() {
		return this.errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public BigInteger getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(BigInteger executionTime) {
		this.executionTime = executionTime;
	}

	@Column(length = 255)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(length = 3000)
	public String getRequestJSON() {
		return this.requestJSON;
	}

	public void setRequestJSON(String requestJSON) {
		this.requestJSON = requestJSON;
	}

	@Column(length = 3000)
	public String getResponseJSON() {
		return this.responseJSON;
	}

	public void setResponseJSON(String responseJSON) {
		this.responseJSON = responseJSON;
	}

	@Column(length = 255)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
