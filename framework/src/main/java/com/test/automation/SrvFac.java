package com.test.automation;

import java.util.Map;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class SrvFac {
	
	public static Response invokeRestService(String system, String method, Headers headers, Object obj, String endpoint, String authKey, Map<String, String> formParams) {
		switch(system.toUpperCase()) {
			case "REST_RELAXED_VAL_NO_FORM_PARAMS" :
				if (method.equalsIgnoreCase("GET")) {
					return InvRstSvc.submitRestGetRequestWithRelaxedValidation(headers, endpoint);
				} else if (method.equalsIgnoreCase("POST")) {
					return InvRstSvc.submitRestPostRequestWithRelaxedValidation(headers, obj, endpoint);
				} else {
					return null;
				}
			
			case "REST_RELAXED_VAL_BASIC_AUTH_FORM_PARAMS" :
				if(method.equalsIgnoreCase("GET")) {
					return null;
				} else if (method.equalsIgnoreCase("POST")) {
					return InvRstSvc.RestRequestRelaxedValidationWithBasicAuthAndFormParams(headers, endpoint, authKey, formParams);
				} else {
					return null;
				}
			
			default :
				System.out.println("No Matching System found !!!");				
	}		
		return null;	
}
	
	public static Response invokeRestService(String system, String method, Headers headers, Object obj, String endpoint, String authKey, Map<String, String> formParams, String sslCertificate, String sslPassword) {
		switch(system.toLowerCase()) {
			case "REST_RELAXED_VAL_SSL_AUTH" :
				if(method.equalsIgnoreCase("GET")){
					return InvRstSvc.RestRequestGETRelaxedValidationWithSSLAuth(headers, endpoint, sslCertificate, sslPassword);
				} else if(method.equalsIgnoreCase("POST")) {
					return InvRstSvc.RestRequestPOSTRelaxedValidationWithSSLAuth(headers, endpoint, obj, sslCertificate, sslPassword);
				} else {
					return null;
				}
			
			default :
				System.out.println("No Matching System found !!!");		
		}
		return null;
	}	
}
