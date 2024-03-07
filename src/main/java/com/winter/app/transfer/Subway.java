package com.winter.app.transfer;

import org.springframework.stereotype.Component;

@Component
public class Subway {

	public int getSubway(int num) {
		System.out.println("지하철 타기");
		return num;
	}
}
