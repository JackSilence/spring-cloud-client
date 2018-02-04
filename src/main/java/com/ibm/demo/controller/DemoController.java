package com.ibm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

@RestController
public class DemoController {
	// @Autowired
	// private Registration registration;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping( "/get" )
	public ImmutableMap<String, String> get() {
		return ImmutableMap.of( "key", "value" );
	}

	@SuppressWarnings( "deprecation" )
	@GetMapping( "/instance-info" )
	public ServiceInstance showInfo() {
		return this.discoveryClient.getLocalServiceInstance();
	}
}