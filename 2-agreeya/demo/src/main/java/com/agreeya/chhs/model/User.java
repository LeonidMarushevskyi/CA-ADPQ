package com.agreeya.chhs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the user database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userID;
	private int createdBy;
	private Date createdOn;
	private String homestudy;
	private int modifiedBy;
	private Date modifiedOn;
	private String password;
	private String status;
	private int stage;
	private String training;
	private String useremail;
	private String userName;
	private String caseID;
	private Role role;
	private List<Userdetail> userdetails;
	private List<Userfamily> userfamilies;
	private List<Userinbox> userinboxs;
	private List<Userlicence> userlicences;
	private List<UserSession> usersessions;
	private List<Userspouse> userspouses;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(nullable = false, length = 1)
	public String getHomestudy() {
		return this.homestudy;
	}

	public void setHomestudy(String homestudy) {
		this.homestudy = homestudy;
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

	@Column(nullable = false, length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = false, length = 15)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	@Column(nullable = false, length = 1)
	public String getTraining() {
		return this.training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	@Column(nullable = false, length = 15)
	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	@Column(nullable = false, length = 128)
	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	@Column(nullable = false, length = 128)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// bi-directional many-to-one association to Role
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RoleID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	// bi-directional many-to-one association to Userdetail
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public List<Userdetail> getUserdetails() {
		return this.userdetails;
	}

	public void setUserdetails(List<Userdetail> userdetails) {
		this.userdetails = userdetails;
	}

	public Userdetail addUserdetail(Userdetail userdetail) {
		getUserdetails().add(userdetail);
		userdetail.setUser(this);

		return userdetail;
	}

	public Userdetail removeUserdetail(Userdetail userdetail) {
		getUserdetails().remove(userdetail);
		userdetail.setUser(null);

		return userdetail;
	}

	// bi-directional many-to-one association to Userfamily
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public List<Userfamily> getUserfamilies() {
		return this.userfamilies;
	}

	public void setUserfamilies(List<Userfamily> userfamilies) {
		this.userfamilies = userfamilies;
	}

	public Userfamily addUserfamily(Userfamily userfamily) {
		getUserfamilies().add(userfamily);
		userfamily.setUser(this);

		return userfamily;
	}

	public Userfamily removeUserfamily(Userfamily userfamily) {
		getUserfamilies().remove(userfamily);
		userfamily.setUser(null);

		return userfamily;
	}

	// bi-directional many-to-one association to Userinbox
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public List<Userinbox> getUserinboxs() {
		return this.userinboxs;
	}

	public void setUserinboxs(List<Userinbox> userinboxs) {
		this.userinboxs = userinboxs;
	}

	public Userinbox addUserinbox(Userinbox userinbox) {
		getUserinboxs().add(userinbox);
		userinbox.setUser(this);

		return userinbox;
	}

	public Userinbox removeUserinbox(Userinbox userinbox) {
		getUserinboxs().remove(userinbox);
		userinbox.setUser(null);

		return userinbox;
	}

	// bi-directional many-to-one association to Userlicence
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public List<Userlicence> getUserlicences() {
		return this.userlicences;
	}

	public void setUserlicences(List<Userlicence> userlicences) {
		this.userlicences = userlicences;
	}

	public Userlicence addUserlicence(Userlicence userlicence) {
		getUserlicences().add(userlicence);
		userlicence.setUser(this);

		return userlicence;
	}

	public Userlicence removeUserlicence(Userlicence userlicence) {
		getUserlicences().remove(userlicence);
		userlicence.setUser(null);

		return userlicence;
	}

	// bi-directional many-to-one association to Usersession
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public List<UserSession> getUsersessions() {
		return this.usersessions;
	}

	public void setUsersessions(List<UserSession> usersessions) {
		this.usersessions = usersessions;
	}

	public UserSession addUsersession(UserSession usersession) {
		getUsersessions().add(usersession);
		usersession.setUser(this);

		return usersession;
	}

	public UserSession removeUsersession(UserSession usersession) {
		getUsersessions().remove(usersession);
		usersession.setUser(null);

		return usersession;
	}

	// bi-directional many-to-one association to Userspouse
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public List<Userspouse> getUserspouses() {
		return this.userspouses;
	}

	public void setUserspouses(List<Userspouse> userspouses) {
		this.userspouses = userspouses;
	}

	public Userspouse addUserspous(Userspouse userspous) {
		getUserspouses().add(userspous);
		userspous.setUser(this);

		return userspous;
	}

	public Userspouse removeUserspous(Userspouse userspous) {
		getUserspouses().remove(userspous);
		userspous.setUser(null);

		return userspous;
	}

}
