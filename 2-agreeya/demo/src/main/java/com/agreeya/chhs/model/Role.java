package com.agreeya.chhs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the role database table.
 * @author AgreeYa Solutions
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private int roleID;
	private String roleName;
	private String status;
	private List<User> users;

	public Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getRoleID() {
		return this.roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	@Column(length = 60)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// bi-directional many-to-one association to User
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}
