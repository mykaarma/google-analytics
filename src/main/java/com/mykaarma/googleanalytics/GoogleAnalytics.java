package com.mykaarma.googleanalytics;

import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class GoogleAnalytics {
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleAnalytics.class);
	
	private String trackingId;
	private ThreadPoolExecutor executor;

	public GoogleAnalytics(String trackingId) {
		this.trackingId = trackingId;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GoogleAnalyticsResponse post(GoogleAnalyticsRequest request) {
		GoogleAnalyticsResponse response = new GoogleAnalyticsResponse();

		try {
			MultiValueMap<String, String> postParms = new LinkedMultiValueMap<String, String>();

			logger.debug("Processing " + request);

			//Process the parameters
			processParameters(request, postParms);
			
			logger.debug("Processed all parameters and sending the request " + postParms);
			
			RestTemplate rest = new RestTemplate();
			rest.setMessageConverters(Arrays.asList(new StringHttpMessageConverter(), new FormHttpMessageConverter()));
			String url = "https://ssl.google-analytics.com/collect";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParms, headers);
			ResponseEntity<String> responseEntity = rest.postForEntity(url, requestEntity, String.class);
			response.setStatusCode(responseEntity.getStatusCode().toString());
			response.setPostedParms(postParms);

		} catch (Exception e) {
			if (e instanceof UnknownHostException) {
				logger.warn("Coudln't connect to Google Analytics. Internet may not be available. " + e.toString());
			} else {
				logger.warn("Exception while sending the Google Analytics tracker request " + request, e);
			}
		}

		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void processParameters(GoogleAnalyticsRequest request, MultiValueMap<String, String> postParms) {
		request.trackingId(trackingId);
		Map<GoogleAnalyticsParameter, String> requestParms = request.getParameters();
		for (GoogleAnalyticsParameter key : requestParms.keySet()) {
			postParms.add(key.getParameterName(), requestParms.get(key));
		}
	}

	@SuppressWarnings("rawtypes")
	public Future<GoogleAnalyticsResponse> postAsync(final GoogleAnalyticsRequest request) {
		Future<GoogleAnalyticsResponse> future = getExecutor().submit(new Callable<GoogleAnalyticsResponse>() {
			public GoogleAnalyticsResponse call() throws Exception {
				return post(request);
			}
		});
		return future;
	}
	
	protected ThreadPoolExecutor getExecutor() {
		if (executor == null) {
			executor = createExecutor();
		}
		return executor;
	}

	protected synchronized ThreadPoolExecutor createExecutor() {
		return new ThreadPoolExecutor(0, 1, 5, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(), createThreadFactory());
	}
	
	protected ThreadFactory createThreadFactory() {
		return new GoogleAnalyticsThreadFactory("googleanalytics-thread-{0}");
	}
}

class GoogleAnalyticsThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private String threadNameFormat = null;

    public GoogleAnalyticsThreadFactory(String threadNameFormat) {
    	this.threadNameFormat = threadNameFormat;
	}

	public Thread newThread(Runnable r) {
        Thread thread = new Thread(Thread.currentThread().getThreadGroup(), r, MessageFormat.format(threadNameFormat, threadNumber.getAndIncrement()), 0);
        thread.setDaemon(true);
        thread.setPriority(Thread.MIN_PRIORITY);
        return thread;
    }
}

