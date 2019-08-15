package com.test.automation;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ssl.*;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class InvRstSvc {
	
	public static HashMap<String, String> authMap=new HashMap<String, String>(){
		{
			put("Admin", "sdfFsafsdFGFsdfsdfSewrdfbcvCVBEWiuuy");
			put("PortalUser", "gsdgfdgHdsfd34sdfDSF344lkkldf44FGFDG");
		}	
	};
	
	private InvRstSvc() {	
	}
	
	public static Response submitRestPostRequestWithRelaxedValidation(Headers requestHeaders, Object obj, String endpoint) {
		return RestAssured
				.given()
				.relaxedHTTPSValidation()
				.headers(requestHeaders)
				.when().log().headers()
				.body(new Gson().toJson(obj))
				.when().log().body()
				.post(endpoint);
	}
	
	public static Response submitRestGetRequestWithRelaxedValidation(Headers requestHeaders, String endpoint) {
		if(requestHeaders.size()>0) {
			return RestAssured
					.given()
					.relaxedHTTPSValidation()
					.headers(requestHeaders)
					.when().log().headers()
					.get(endpoint);
		} else {
			return RestAssured
					.given()
					.relaxedHTTPSValidation()
					.get(endpoint);
		}
	}
	
	public static Response RestRequestRelaxedValidationWithBasicAuthAndFormParams(Headers requestHeader, String endpoint, String authkey, Map<String, String> formParams) {
		return RestAssured
				.given()
				.relaxedHTTPSValidation()
				.auth().preemptive().basic(authkey, authMap.get(3))
				.headers(requestHeader)
				.formParams(formParams)
				.when().log().body()
				.post(endpoint);				
	}
	
	public static Response RestRequestGETRelaxedValidationWithSSLAuth(Headers requsetHeaders, String endpoint, String sslCertificate, String password) {
		try {
			KeyStore keyStore = null;
			SSLConfig config = null;
			
			try {
				keyStore=KeyStore.getInstance("JKS"); //getInstance("PKCS12");
				keyStore.load(new FileInputStream(sslCertificate), password.toCharArray());
			}catch(Exception ex) {
				System.out.println("Error while loading keystore >>>>>>>>>>");
				ex.printStackTrace();
			}
			
			if(keyStore != null) {
				@SuppressWarnings("deprecation")
				SSLSocketFactory clientAuthFactory = new SSLSocketFactory(keyStore, password);
				//set the config in restassured
				config = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
				RestAssured.config = RestAssured.config().sslConfig(config);
				
				return RestAssured.given().trustStore(keyStore).headers(requsetHeaders).when().log().body().get(endpoint);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Response RestRequestPOSTRelaxedValidationWithSSLAuth(Headers requestHeaders, String endpoint, Object obj, String sslCertificate, String password) {
		try {
			KeyStore keyStore = null;
			SSLConfig config = null;
			
			try {
				keyStore=KeyStore.getInstance("JKS"); //getInstance("PKCS12");
				keyStore.load(new FileInputStream(sslCertificate), password.toCharArray());
			}catch(Exception ex) {
				System.out.println("Error while loading keystore >>>>>>>>>>");
				ex.printStackTrace();
			}
			
			if(keyStore != null) {
				@SuppressWarnings("deprecation")
				SSLSocketFactory clientAuthFactory = new SSLSocketFactory(keyStore, password);
				//set the config in restassured
				config = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
				RestAssured.config = RestAssured.config().sslConfig(config);				
				if(obj==null) {
					return RestAssured.given().trustStore(keyStore).headers(requestHeaders).when().log().body().post(endpoint);
				} else { //if body exists
					return RestAssured.given().trustStore(keyStore).headers(requestHeaders).body(new Gson().toJson(obj)).when().log().body().post(endpoint);
				}
								
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
