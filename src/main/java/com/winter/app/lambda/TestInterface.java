package com.winter.app.lambda;

@FunctionalInterface //하나의 추상메서드가 있는것을 보장 
public interface TestInterface {

	
	abstract int t1(int n1 , int n2) throws Exception ;
		
	default void test() {
		
	}
	
}
