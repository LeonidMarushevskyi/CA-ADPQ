package com.agreeya.chhs.request;

/**
 * Interface implemented by all the request classes. The interface defines the validate() method which is used to verify the validity of the
 * input data.
 *
 */
public interface WSRequest {

	/**
	 * The method can be used to do a field-by-field validation of the input data.
	 * The returned ValidationResult contains the error
	 * messages, if any, for all the data violations.
	 *  If there are no error messages present in the returned result, then the input data is
	 * considered to be completely valid.
	 * 
	 * @return
	 */
	ValidationResult validate();

}
