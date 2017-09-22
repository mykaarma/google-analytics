package com.mykaarma.googleanalytics;

public enum GoogleAnalyticsParameter {
	//Required
	PROTOCOL_VERSION("v"),
	TRACKING_ID("tid"),
	CLIENT_ID("cid"),
	HIT_TYPE("t"),
	
	//Content Information
	DOCUMENT_URL("dl"),
	DOCUMENT_HOST_NAME ("dh"),
	DOCUMENT_PATH ("dp"),
	DOCUMENT_TITLE ("dt"),
	CONTENT_DESCRIPTION ("cd"),
	
	//Event Tracking
	EVENT_CATEGORY("ec"),
	EVENT_ACTION("ea"),
	EVENT_LABEL("el"),
	EVENT_VALUE("ev");
	
	private String parameterName = null;
	
	private GoogleAnalyticsParameter(String name) {
		this.parameterName = name;
	}

	public String getParameterName() {
		return parameterName;
	}
}
