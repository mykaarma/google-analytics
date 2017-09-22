package com.mykaarma.googleanalytics;

import org.springframework.util.MultiValueMap;

public class GoogleAnalyticsResponse {
	private String statusCode = null;
	private MultiValueMap<String, String> postedParms = null;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public MultiValueMap<String, String> getPostedParms() {
		return postedParms;
	}
	public void setPostedParms(MultiValueMap<String, String> postedParms) {
		this.postedParms = postedParms;
	}
}
