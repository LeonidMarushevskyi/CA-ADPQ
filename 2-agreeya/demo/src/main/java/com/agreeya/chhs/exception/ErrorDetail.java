package com.agreeya.chhs.exception;

import java.util.Map;

/**
 * Error Detail POJO this will be used to pass error details in WS Exception
 * @author AgreeYa Solutions
 *
 */
public class ErrorDetail {

	private String code;
	private String message;
	private Map<String, String> templateParams;

	public ErrorDetail() {

	}

	public ErrorDetail(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorDetail(String code, String message, Map<String, String> templateParams) {
		this.code = code;
		this.message = message;
		this.setTemplateParams(templateParams);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getTemplateParams() {
		return templateParams;
	}

	public void setTemplateParams(Map<String, String> templateParams) {
		this.templateParams = templateParams;
	}

	public ErrorDetail createCopy() {
		return new ErrorDetail(this.code, this.message);
	}
}
