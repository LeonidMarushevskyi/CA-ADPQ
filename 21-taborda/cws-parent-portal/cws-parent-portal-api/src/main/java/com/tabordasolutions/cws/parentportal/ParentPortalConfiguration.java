package com.tabordasolutions.cws.parentportal;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentPortalConfiguration extends Configuration {
    /**
     * The application name
     */
    @NotEmpty
    private String applicationName;

    /**
     * The data source factory configuration
     */
    private DataSourceFactory dataSourceFactory;

    /**
     * The flyway factory configuration
     */
    private FlywayFactory flywayFactory;

    /**
     * The URL to access CHHS Open Data API for Foster Agencies
     */
    @NotEmpty
    private String apiChhsUrl;

    /**
     * The key to access CHHS Open Data API for Foster Agencies
     */
    @NotEmpty
    private String apiChhsKey;

    /**
     * The query param used on the CHHS Open Data API for Foster Agencies
     */
    @NotEmpty
    private String apiChhsQueryParam;

    /**
     * The query param used for salting encoding tokens
     */
    @NotEmpty
    private String encryptSalt;
    
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
    
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

    @JsonProperty
    public String getApplicationName() {
        return applicationName;
    }

    @JsonProperty
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @JsonProperty
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @JsonProperty
    public FlywayFactory getFlywayFactory() {
        return flywayFactory;
    }

    @JsonProperty
    public void setFlywayFactory(FlywayFactory flywayFactory) {
        this.flywayFactory = flywayFactory;
    }

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClient() {
        return jerseyClientConfiguration;
    }

    @JsonProperty("api.chhs.url")
    public String getApiChhsUrl() {
        return apiChhsUrl;
    }

    @JsonProperty("api.chhs.url")
    public void setApiChhsUrl(String apiChhsUrl) {
        this.apiChhsUrl = apiChhsUrl;
    }

    @JsonProperty("api.chhs.key")
    public String getApiChhsKey() {
        return apiChhsKey;
    }

    @JsonProperty("api.chhs.key")
    public void setApiChhsKey(String apiChhsKey) {
        this.apiChhsKey = apiChhsKey;
    }

    @JsonProperty("api.chhs.queryParam")
    public String getApiChhsQueryParam() {
        return apiChhsQueryParam;
    }

    @JsonProperty("api.chhs.queryParam")
    public void setApiChhsQueryParam(String apiChhsQueryParam) { this.apiChhsQueryParam = apiChhsQueryParam; }

    @JsonProperty("encrypt.salt")
    public String getEncryptSalt() { return encryptSalt; }

    @JsonProperty("encrypt.salt")
    public void setEncryptSalt(String encryptSalt) { this.encryptSalt = encryptSalt; }
}
