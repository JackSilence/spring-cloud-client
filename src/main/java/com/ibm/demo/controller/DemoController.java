package com.ibm.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

@RestController
public class DemoController {
	@Autowired
	private Registration registration;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping( "/get" )
	public ImmutableMap<String, String> get() {
		return ImmutableMap.of( "key", "value" );
	}

	@GetMapping( "/instance-info" )
	public ServiceInstance showInfo() {
		List<ServiceInstance> list = discoveryClient.getInstances( registration.getServiceId() );

		return list.isEmpty() ? null : list.get( 0 );
	}
}