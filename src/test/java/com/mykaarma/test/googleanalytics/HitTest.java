package com.mykaarma.test.googleanalytics;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mykaarma.googleanalytics.EventHit;
import com.mykaarma.googleanalytics.GoogleAnalytics;
import com.mykaarma.googleanalytics.GoogleAnalyticsResponse;
import com.mykaarma.googleanalytics.PageViewHit;

public class HitTest {
	@Test
    public void test() throws Exception {
    		GoogleAnalytics googleAnalytics = new GoogleAnalytics("UA-105826168-1");
    		GoogleAnalyticsResponse res = googleAnalytics.postAsync(new PageViewHit("/url", "title").clientId("12345").customDimension(1, "cd1")).get();
    		assertEquals("200", res.getStatusCode());
    		Map<Integer, String> cds = new HashMap<Integer, String>();
    		cds.put(2, "dealer name");
    		cds.put(1, "dealer id 1");
		GoogleAnalyticsResponse res1 = googleAnalytics.postAsync(
				new EventHit("frauddetection", "kount-rule", "rule description in more more detail", 1)
				.clientId("123465")
				.customDimensions(cds)).get();
		assertEquals("200", res1.getStatusCode()); 
    }
}
