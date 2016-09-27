package com.agreeya.chhs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the userlicence database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "userlicence")
public class Userlicence implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String agencyContact;
	private String agencyWorker;
	private int createdBy;
	private Date createdon;
	private Date dateOfIssue;
	private String licenceNo;
	private int modifiedBy;
	private Date modifiedOn;
	private User user;

	public Userlicence() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length = 15)
	public String getAgencyContact() {
		return this.agencyContact;
	}

	public void setAgencyContact(String agencyContact) {
		this.agencyContact = agencyContact;
	}

	public String getAgencyWorker() {
		return this.agencyWorker;
	}

	public void setAgencyWorker(String agencyWorker) {
		this.agencyWorker = agencyWorker;
	}

	@Column(nullable = false)
	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateOfIssue() {
		return this.dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	@Column(nullable = false, length = 45)
	public String getLicenceNo() {
		return this.licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UserID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
