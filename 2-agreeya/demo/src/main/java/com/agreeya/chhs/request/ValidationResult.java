package com.agreeya.chhs.request;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AgreeYa Solutions
 *
 */
public class ValidationResult {
	private List<String> messages;
	private String messageSeparator = " :: ";

	public ValidationResult() {
		messages = new ArrayList<String>();
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public boolean isValid() {
		if (messages.size() > 0) {
			return false;
		}
		return true;
	}

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		if (isValid()) {
			return null;
		}

		boolean separatorFlag = false;

		for (String message : messages) {
			if (separatorFlag) {
				sb.append(messageSeparator);
			}

			sb.append(message);
			separatorFlag = true;
		}

		if (sb.length() > 0) {
			return sb.toString();
		}
		return null;
	}
}
