package BaekJoon;

import java.util.*;

public class 소수구하기_1929 {

	static long M, N;

	public static void main(String[] args) {
		makeInput();
		checkPrimeNumbers();
	}
	
	private static void checkPrimeNumbers() {
		for(long i = M; i <= N; i++) {
			checkPrimeNumber(i);
		}
	}

	private static void checkPrimeNumber(long number) {
		if(number <= 1) {
			return;
		}
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0)
				return;
		}
		System.out.println(number);		
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		M = input.nextLong();
		N = input.nextLong();
	}
}
