package com.test;

public class PassBYV {
//PassbyValue
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=new String("test");
		//String str="test";
		System.out.println(str);
		chngStr(str);
		System.out.println(str);
	}
	
	public static String chngStr(String str) {
		return str="testing";
	}

}
