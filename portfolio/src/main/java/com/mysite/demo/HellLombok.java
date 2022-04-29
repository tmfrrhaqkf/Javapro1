package com.mysite.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HellLombok {
	
	private String hello;
	private int test;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HellLombok hellLombok = new HellLombok();
		hellLombok.setHello("으하하하 롬복 짜증 ㅋㅋ");
		hellLombok.setTest(10);
		
		System.out.println(hellLombok.getHello());
		System.out.println(hellLombok.getTest());
	}

}
