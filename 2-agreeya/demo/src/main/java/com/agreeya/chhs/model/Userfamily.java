package com.agreeya.chhs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

/**
 * The persistent class for the userfamily database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "userfamily")
public class Userfamily implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int createdBy;
	private Date createdOn;
	private String description;
	private String haveKids;
	private String kid;
	private String kidsInfo;
	private int modifiedBy;
	private Date modifiedOn;
	private User user;
	private List<Userkid> kids;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHaveKids() {
		return this.haveKids;
	}

	public void setHaveKids(String haveKids) {
		this.haveKids = haveKids;
	}

	public String getKid() {
		return this.kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	@Lob
	public String getKidsInfo() {
		return this.kidsInfo;
	}

	public void setKidsInfo(String kidsInfo) {
		this.kidsInfo = kidsInfo;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@ManyToOne
	@JoinColumn(name = "UserID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public List<Userkid> getKids() {
		return kids;
	}

	public void setKids(List<Userkid> kids) {
		this.kids = kids;
	}

}
