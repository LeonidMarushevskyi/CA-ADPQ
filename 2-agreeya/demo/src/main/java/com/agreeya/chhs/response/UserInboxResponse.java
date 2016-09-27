package com.agreeya.chhs.response;

import java.util.List;

import com.agreeya.chhs.response.inbox.UserEmail;

/**
 * @author AgreeYa Solutions
 *
 */
public class UserInboxResponse extends WSResponse {

	private List<UserEmail> emails;
	private int totalCount;
	public List<UserEmail> getEmails() {
		return emails;
	}
	public void setEmails(List<UserEmail> emails) {
		this.emails = emails;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
