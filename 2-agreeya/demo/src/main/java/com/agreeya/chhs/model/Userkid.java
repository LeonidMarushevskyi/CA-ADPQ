package com.agreeya.chhs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the userkids database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "userkids")
public class Userkid implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userKidID;
	private String name;
	private String ageGroup;
	private String hobbies;
	private Userfamily userfamily;

	public Userkid() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUserKidID() {
		return this.userKidID;
	}

	public void setUserKidID(int userKidID) {
		this.userKidID = userKidID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeGroup() {
		return this.ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	// bi-directional many-to-one association to Userfamily
	@ManyToOne
	@JoinColumn(name = "familyID")
	public Userfamily getUserfamily() {
		return this.userfamily;
	}

	public void setUserfamily(Userfamily userfamily) {
		this.userfamily = userfamily;
	}

}
