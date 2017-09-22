package com.mykaarma.test.googleanalytics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mykaarma.googleanalytics.EventHit;
import com.mykaarma.googleanalytics.GoogleAnalytics;
import com.mykaarma.googleanalytics.GoogleAnalyticsResponse;
import com.mykaarma.googleanalytics.PageViewHit;

public class HitTest {
	@Test
    public void test() throws Exception {
    		GoogleAnalytics googleAnalytics = new GoogleAnalytics("UA-105826168-1");
    		GoogleAnalyticsResponse res = googleAnalytics.postAsync(new PageViewHit("/url", "title").clientId("12345")).get();
    		assertEquals("200", res.getStatusCode());
    		GoogleAnalyticsResponse res1 = googleAnalytics.postAsync(new EventHit("frauddetection", "kount-rule", "rule description in more more detail", 1).clientId("123465")).get();
		assertEquals("200", res1.getStatusCode()); 
    }
}
