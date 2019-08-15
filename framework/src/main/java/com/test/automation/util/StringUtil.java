package com.test.automation.util;

import java.util.Random;

public class StringUtil {
	
	private static final String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String Numberic="0123456789";
	
	public static String generateRandomString(int size) {
		Random rand=new Random();
		StringBuilder res=new StringBuilder();
		for(int i=0;i<size;i++)
		{
			int randIndex = rand.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		return res.toString();
	}
	
	public static String generateRandomNumberAsString(int size) {
		Random rand=new Random();
		StringBuilder res=new StringBuilder();
		for(int i=1;i<size;i++)
		{
			int randIndex=rand.nextInt(Numberic.length());
			res.append(Numberic.charAt(randIndex));
		}
		return res.toString();
	}

}
