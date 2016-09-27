package com.agreeya.chhs.request;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Request POJO for finding Facilities by Location
 * @author AgreeYa Solutions
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class FindAgenciesBytLocationRequest extends AbstractDoesNotRequireAnyContext implements WSRequest {

	@NotNull
	@NotEmpty
	private String lattitude;

	@NotNull
	@NotEmpty
	private String longitude;
	private String radius;

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();

		// Code commented as this check is done by the Hibernate Validator
		// annotations.
		// However, this method can be used for more complex condition checks
		// that cannot be
		// easily performed by Hibernate Validator.

		if (lattitude == null || lattitude.isEmpty()) {
			vr.addMessage("lattitude is not provided!.");
		}

		if (longitude == null || longitude.isEmpty()) {
			vr.addMessage("longitude is not provided!.");
		}

		if (radius == null || radius.isEmpty()) {
			vr.addMessage("radius is not provided!.");
		}
		return vr;
	}

}
