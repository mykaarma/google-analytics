package com.mykaarma.googleanalytics;

public class EventHit extends GoogleAnalyticsRequest<PageViewHit>{

	public EventHit (String eventCategory, String eventAction) {
		this(eventCategory, eventAction, null, null);
	}

	public EventHit (String eventCategory, String eventAction, String eventLabel, Integer eventValue) {
		super("event");
		eventCategory(eventCategory);
		eventAction(eventAction);
		eventLabel(eventLabel);
		eventValue(eventValue);
	}
	
	public EventHit eventCategory(String value) {
		setString(GoogleAnalyticsParameter.EVENT_CATEGORY, value);
	   	return this;
	}
	
	public EventHit eventAction(String value) {
		setString(GoogleAnalyticsParameter.EVENT_ACTION, value);
	   	return this;
	}
	
	public EventHit eventLabel(String value) {
		setString(GoogleAnalyticsParameter.EVENT_LABEL, value);
	   	return this;
	}
	
	public EventHit eventValue(Integer value) {
		setInteger(GoogleAnalyticsParameter.EVENT_VALUE, value);
	   	return this;
	}
}