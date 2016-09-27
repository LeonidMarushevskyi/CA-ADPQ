package com.agreeya.chhs.util;

import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Class to convert JSON to/from Java/XML.
 * @author AgreeYa Solutions
 * 
 */
public class JsonConverter {

	/**
	 * Converts the input JSON string into a Java object.
	 * 
	 * @param jsonString
	 * @param javaClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object convertJsonToJavaObject(String jsonString, Class javaClass) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, javaClass);
	}

	/**
	 * Converts the input Java object into a JSON string.
	 * 
	 * @param javaObject
	 * @return
	 */
	public static String convertJavaObjectToJsonString(Object javaObject) {
		/*
		 * GsonBuilder builder = new GsonBuilder(); builder.disableHtmlEscaping(); Gson gson = builder.create();
		 */
		Gson gson = new Gson();
		return gson.toJson(javaObject);
	}

	/**
	 * Converts the input Java object into a JSON string, skipping some speficied fields in the process.
	 * 
	 * @param javaObject
	 * @param fieldsToSkip
	 * @return
	 */
	public static String convertJavaObjectToJsonString(Object javaObject, final List<String> fieldsToSkip) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes field) {
				if (fieldsToSkip != null && fieldsToSkip.contains(field.getName())) {
					return true;
				}
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		}).create();

		return gson.toJson(javaObject);
	}
}
