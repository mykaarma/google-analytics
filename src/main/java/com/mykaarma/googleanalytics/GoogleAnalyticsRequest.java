package com.mykaarma.googleanalytics;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class GoogleAnalyticsRequest<T> {
	protected Map<GoogleAnalyticsParameter, String> parms = new HashMap<GoogleAnalyticsParameter, String>();
	protected Map<String, String> customDimensions = new HashMap<String, String>();
	
	public GoogleAnalyticsRequest(String hitType) {
		this(hitType, null);
	}
	
	public GoogleAnalyticsRequest(String hitType, String trackingId) {
		hitType(isEmpty(hitType)?"pageview":hitType);
		trackingId(trackingId);

		protocolVersion("1");
	}
	
	public T customDimension(int index, String value) {
		if(value == null) customDimensions.remove("cd" + index);
		else customDimensions.put("cd" + index, value);
		return (T) this;
	}
	
	public T customDimensions(Map<Integer, String> cds) {
		if(cds == null) return (T) this;
		for(Integer key : cds.keySet()) {
			if(cds.get(key) == null) customDimensions.remove("cd" + key);
			else customDimensions.put("cd" + key, cds.get(key));
		}
		return (T) this;
	}
	
	public T hitType(String value) {
		setString(GoogleAnalyticsParameter.HIT_TYPE, value);
	   	return (T) this;
	}
	
	public T trackingId(String value) {
		setString(GoogleAnalyticsParameter.TRACKING_ID, value);
	   	return (T) this;
	}
	
	public T clientId(String value) {
		setString(GoogleAnalyticsParameter.CLIENT_ID, value);
	   	return (T) this;
	}
	
	public T protocolVersion(String value) {
		setString(GoogleAnalyticsParameter.PROTOCOL_VERSION, value);
	   	return (T) this;
	}
	
	public T documentUrl(String value) {
		setString(GoogleAnalyticsParameter.DOCUMENT_URL, value);
	   	return (T) this;
	}
	
	public T documentTitle(String value) {
		setString(GoogleAnalyticsParameter.DOCUMENT_TITLE, value);
	   	return (T) this;
	}
	
	public T contentDescription(String value) {
		setString(GoogleAnalyticsParameter.CONTENT_DESCRIPTION, value);
	   	return (T) this;
	}
	
	public Map<GoogleAnalyticsParameter, String> getParameters() {
		return parms;
	}
	
	public Map<String, String> getCustomDimensions() {
		return customDimensions;
	}
	
	protected T setString(GoogleAnalyticsParameter parameter, String value) {
		if (value == null) {
			parms.remove(parameter);
		} else {
			String stringValue = value;
			parms.put(parameter, stringValue);
		}
		return (T) this;
	}
	
	protected T setInteger(GoogleAnalyticsParameter parameter, Integer value) {
		if (value == null) {
			parms.remove(parameter);
		} else {
			String stringValue = fromInteger(value);
			parms.put(parameter, stringValue);
		}
		return (T) this;
	}
	
	protected String fromInteger(Integer intValue) {
		if (intValue == null) {
			return null;
		}

		return "" + intValue;
	}

	protected boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}
}
