package com.ibm.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.demo.service.FeignService.HystrixFallback;

@FeignClient( name = "cloud-api", fallback = HystrixFallback.class )
public interface FeignService {
	@RequestMapping( "/instance-info" )
	public String showInfo(); // TODO: 熔斷器有點怪怪的, 要再確認

	@Component
	static class HystrixFallback implements FeignService {
		private final Logger log = LoggerFactory.getLogger( this.getClass() );

		@Override
		public String showInfo() {
			log.info( "feign發生錯誤, 進入fallback!" );

			return "{}";
		}
	}
}