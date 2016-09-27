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
 * The persistent class for the userspouse database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "userspouse")
public class Userspouse implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userSpouseID;
	private String contactNo;
	private int createdBy;
	private Date createdOn;
	private Date dob;
	private String firstName;
	private String gender;
	private String hobbies;
	private String income;
	private int modifiedBy;
	private Date modifiedOn;
	private String occupation;
	private String race;
	private String religion;
	private User user;
	private String preference;

	public Userspouse() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getUserSpouseID() {
		return this.userSpouseID;
	}

	public void setUserSpouseID(int userSpouseID) {
		this.userSpouseID = userSpouseID;
	}

	@Column(length = 15)
	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(nullable = false, length = 60)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(nullable = false, length = 1)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(length = 256)
	public String getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Column(length = 15)
	public String getIncome() {
		return this.income;
	}

	public void setIncome(String income) {
		this.income = income;
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

	@Column(length = 60)
	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(length = 45)
	public String getPreference() {
		return this.preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	@Column(length = 45)
	public String getRace() {
		return this.race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Column(length = 45)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
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
