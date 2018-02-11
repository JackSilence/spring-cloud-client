package com.ibm.demo.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.ibm.demo.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DemoController {
	private final Logger log = LoggerFactory.getLogger( this.getClass() );

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FeignService feignService;

	@GetMapping( "/ribbon" )
	@HystrixCommand( fallbackMethod = "fallback" )
	public Map<String, TreeMap<?, ?>> ribbon( HttpServletRequest request ) {
		return result( request, restTemplate.getForObject( "http://cloud-api/instance-info", String.class ) );
	}

	@GetMapping( "/feign" )
	public Map<String, TreeMap<?, ?>> feign( HttpServletRequest request ) {
		return result( request, feignService.showInfo() );
	}

	private Map<String, TreeMap<?, ?>> result( HttpServletRequest request, String json ) {
		return ImmutableMap.of( request.getRequestURI(), new Gson().fromJson( json, TreeMap.class ) );
	}

	public Map<String, TreeMap<?, ?>> fallback( HttpServletRequest request ) {
		log.info( "ribbon發生錯誤, 進入fallback!" );

		return result( request, "{}" );
	}
}