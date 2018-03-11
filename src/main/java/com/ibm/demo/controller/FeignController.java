package com.ibm.demo.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.service.FeignService;

@RestController
@Profile( "feign" )
public class FeignController extends DemoController {
	@Autowired
	private FeignService feignService;

	@GetMapping( "/feign" )
	public Map<String, TreeMap<?, ?>> feign( HttpServletRequest request ) {
		return result( request, feignService.showInfo() );
	}
}