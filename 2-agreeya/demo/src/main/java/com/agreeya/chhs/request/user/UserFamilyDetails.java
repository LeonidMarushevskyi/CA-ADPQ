package com.agreeya.chhs.request.user;

import java.util.List;

/**
 * Transfer Object class for User family Information
 * 
 * @author AgreeYa Solutions
 *
 */
public class UserFamilyDetails {

	private String description;
	private String haveKids;
	private String numberOfKids;
	private String kidsPref;
	private List<UserKidsDetails> kids;
	

	public UserFamilyDetails(String description, String haveKids, String numberOfKids, String kidsPref,
			List<UserKidsDetails> kids) {
		super();
		this.description = description;
		this.haveKids = haveKids;
		this.numberOfKids = numberOfKids;
		this.kidsPref = kidsPref;
		this.kids = kids;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(String numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public String getKidsPref() {
		return kidsPref;
	}

	public void setKidsPref(String kidsPref) {
		this.kidsPref = kidsPref;
	}

	public String getHaveKids() {
		return haveKids;
	}

	public void setHaveKids(String haveKids) {
		this.haveKids = haveKids;
	}

	public List<UserKidsDetails> getKids() {
		return kids;
	}

	public void setKids(List<UserKidsDetails> kids) {
		this.kids = kids;
	}
	

	
}
