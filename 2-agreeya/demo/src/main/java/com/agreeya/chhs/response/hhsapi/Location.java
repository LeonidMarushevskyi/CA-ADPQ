package com.agreeya.chhs.response.hhsapi;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Transfer Object for Location in Facility
 * @author AgreeYa Solutions
 *
 */
public class Location {

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("coordinates")
	@Expose
	private List<Double> coordinates = new ArrayList<Double>();

	/**
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The coordinates
	 */
	public List<Double> getCoordinates() {
		return coordinates;
	}

	/**
	 * 
	 * @param coordinates
	 *            The coordinates
	 */
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

}
