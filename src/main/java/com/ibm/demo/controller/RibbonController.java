package com.ibm.demo.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@Profile( "ribbon" )
public class RibbonController extends DemoController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping( "/ribbon" )
	@HystrixCommand( fallbackMethod = "fallback" )
	public Map<String, TreeMap<?, ?>> ribbon( HttpServletRequest request ) {
		return result( request, restTemplate.getForObject( "http://cloud-api/instance-info", String.class ) );
	}

	public Map<String, TreeMap<?, ?>> fallback( HttpServletRequest request ) {
		log.info( "ribbon發生錯誤, 進入fallback!" );

		return result( request, "{}" );
	}
}