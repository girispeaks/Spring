package com.test;

public class PassBYR {
	//pass by reference
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dummy d=new Dummy();
		d.name="initialValue";
		System.out.println("Initial value is "+d.name);
		PassBYR byr=new PassBYR();
		byr.changeString(d);
		System.out.println("Changed value is "+d.name);	
	}
	
	public void changeString(Dummy s) {
		s.name="changedValue";
		
	}
	
}
