package com.tabordasolutions.cws.parentportal.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.ws.rs.client.Client;

import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabordasolutions.cws.parentportal.api.Agency;

@Api("/agencies")
public class ChhsOpenDataAgencyResource implements AgencyResource {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(ChhsOpenDataAgencyResource.class);

	private final String HEADER_API_KEY = "X-App-Token";
	private final String QUERY_PARAM_FACILITY_ZIP = "facility_zip";
	private final String QUERY_PARAM_FACILITY_STATUS = "facility_status";
	private final String VALUE_QUERY_PARAM_FACILITY_STATUS = "LICENSED";

	private final Client client;
	private final String apiKey;
	private final String apiUrl;
	private final String apiQueryParam;

	public ChhsOpenDataAgencyResource(Client client, String apiUrl,
			String apiKey, String queryParam) {
		this.client = Preconditions.checkNotNull(client,
				"Client must not be null");
		this.apiUrl = Preconditions.checkNotNull(apiUrl,
				"apiUrl must not be null").trim();
		Preconditions.checkArgument(this.apiUrl.length() > 0,
				"apiUrl must not be empty");
		this.apiKey = Preconditions.checkNotNull(apiKey,
				"apiKey must not be null").trim();
		Preconditions.checkArgument(this.apiKey.length() > 0,
				"apiKey must not be empty");
		this.apiQueryParam = Preconditions.checkNotNull(queryParam,
				"apiQueryParam must not be null").trim();
		Preconditions.checkArgument(this.apiQueryParam.length() > 0,
				"apiQueryParam must not be empty");
	}

	@ApiOperation("Find agencies")
	@Override
	public List<Agency> listAgencies(@ApiParam(required=true )String zipcode) {
		LOGGER.debug("Searching on zipcode {}", zipcode);
		if( zipcode != null) {
			@SuppressWarnings({ "rawtypes" })
			List agenciesAsMaps = client
					.target(this.apiUrl)
					.queryParam(QUERY_PARAM_FACILITY_ZIP, zipcode)
					.queryParam(QUERY_PARAM_FACILITY_STATUS,
							VALUE_QUERY_PARAM_FACILITY_STATUS).request()
					.header(HEADER_API_KEY, this.apiKey).get(List.class);
	
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			return mapper.convertValue(agenciesAsMaps,
					new TypeReference<List<Agency>>() {
					});
		} else {
			return new ImmutableList.Builder<Agency>().build();
		}
	}
}
