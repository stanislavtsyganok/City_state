package com.example.first_app;

import java.util.Random;

public class Common {
	public int randNumber(int maxValue){
		int temp;
		Random randomGen = new Random();
		temp = randomGen.nextInt(maxValue);
		return temp;
	}
}
