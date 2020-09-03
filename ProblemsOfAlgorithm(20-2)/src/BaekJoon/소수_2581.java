package BaekJoon;

import java.util.Scanner;

public class 소수_2581 {

	static int M, N;
	static int sumOfPrimeNumbers;
	static int minOfPrimeNumbers;
	static boolean havePrimeNumber;
	
	public static void main(String[] args) {
		makeInput();
		checkPrimeNumbers();
		printResult();
	}
	
	private static void checkPrimeNumbers() {
		for(int i = M; i <= N; i++) {
			if(isPrimeNumber(i) == true) {
				setPrimtNumber(i);
			}
		}
	}

	private static void setPrimtNumber(int number) {
		sumOfPrimeNumbers = sumOfPrimeNumbers + number;
		if(minOfPrimeNumbers > number) 
			minOfPrimeNumbers = number;
		havePrimeNumber = true;
	}

	private static boolean isPrimeNumber(int number) {
		if(number <= 1) {
			return false;
		}
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0)
				return false;
		}
		return true;
	}

	private static void printResult() {
		if(havePrimeNumber == true) {
			System.out.println(sumOfPrimeNumbers);
			System.out.println(minOfPrimeNumbers);
		}
		else
			System.out.println("-1");
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		M = input.nextInt();
		N = input.nextInt();
		sumOfPrimeNumbers = 0;
		minOfPrimeNumbers = Integer.MAX_VALUE;
		havePrimeNumber = false;
	}
}
