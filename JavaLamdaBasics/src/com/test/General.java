package com.test;

public class General {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int port=8080; 
		
		String url = String.format("http://localhost:%d/bus/%s/%s", port, "test", "534");
		System.out.println(url);;

	}

}
