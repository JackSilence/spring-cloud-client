package com.ibm.demo.controller;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

public abstract class DemoController {
	protected final Logger log = LoggerFactory.getLogger( this.getClass() );

	protected final Map<String, TreeMap<?, ?>> result( HttpServletRequest request, String json ) {
		// 觀察有時服務發現異常或服務提供者剛起來時拿到的資訊可能會是null... 所以加個預設值
		log.info( "json: " + json + ", isNull: " + Objects.isNull( json ) );

		return ImmutableMap.of( request.getRequestURI(), new Gson().fromJson( StringUtils.defaultString( json, "{}" ), TreeMap.class ) );
	}
}