package com.agreeya.chhs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.agreeya.chhs.response.hhsapi.Facility;
import com.agreeya.chhs.response.hhsapi.FacilityList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * utility class to call HHS API
 * @author AgreeYa Solutions
 *
 */
public class HHSApiCaller {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String ZIPCODE_PARAM = "?facility_zip=";
	private static final String LOCATION_PARAM = "?facility_type=FOSTER%20FAMILY%20AGENCY&$where=within_circle(location,";

	public static FacilityList getFacilitiesByZipCode(String zipcode) throws IOException {
		String hhsApiUrl = ResourceBundleUtil.getProperty(Constants.HHS_API_URL) + ZIPCODE_PARAM + zipcode;
		String response = sendGET(hhsApiUrl);
		List<Facility> facilitiesByZip = hhsResponsetoPOJO(response);
		FacilityList facilityList = new FacilityList();
		facilityList.setPojoList(facilitiesByZip);
		return facilityList;
	}

	public static FacilityList getFacilitiesByLocation(String lat, String lon, String radius) throws IOException {
		String hhsApiUrl = ResourceBundleUtil.getProperty(Constants.HHS_API_URL)
				+ LOCATION_PARAM + lon + "," + lat + "," + radius + ")";

		String response = sendGET(hhsApiUrl);
		List<Facility> facilitiesByZip = hhsResponsetoPOJO(response);
		FacilityList facilityList = new FacilityList();
		facilityList.setPojoList(facilitiesByZip);
		return facilityList;
	}

	private static String sendGET(String getURL) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(getURL);
		httpGet.addHeader("User-Agent", USER_AGENT);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		httpClient.close();
		return response.toString();
	}

	private static List<Facility> hhsResponsetoPOJO(String response) {

		if (response.length() < 3) {
			return new ArrayList<Facility>();
		}

		Type listType = new TypeToken<ArrayList<Facility>>() { } .getType();
		List<Facility> facilityList = new Gson().fromJson(response, listType);

		return facilityList;

	}

	public static void main(String[] args) throws IOException {
		HHSApiCaller.getFacilitiesByLocation("-118", "34", "10000");
	}

}
