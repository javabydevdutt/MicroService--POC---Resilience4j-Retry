package com.devdutt.retry.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class RetryRestController {

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(RetryRestController.class);

	@GetMapping("/getInvoice")
	@Retry(name = "getInvoiceRetry", fallbackMethod = "getInvoiceFallback")
	public String getInvoice() {
		logger.info("getInvoice() call starts here");

		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8888/invoice/rest/find/2",
				String.class);

		logger.info("Response :" + response.getStatusCode());
		return response.getBody();
	}

	public String getInvoiceFallback(Exception e) {
		logger.info("---RESPONSE FROM FALLBACK METHOD---");
		return "SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
	}
}
