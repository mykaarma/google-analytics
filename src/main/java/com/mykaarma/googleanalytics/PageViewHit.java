package com.mykaarma.googleanalytics;

public class PageViewHit  extends GoogleAnalyticsRequest<PageViewHit> {

	public PageViewHit(String url, String title) {
		this(url, title, null);
	}

	public PageViewHit(String url, String title, String description) {
		super("pageview");
		documentUrl(url);
		documentTitle(title);
		contentDescription(description);
	}
}
