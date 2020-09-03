package BaekJoon;

import java.util.Scanner;

public class 소수찾기_1978 {
	
	static int N; // < 100
	static int[] numbers; // < 1000
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		findPrimeNumbers();
		printResult();
	}
	
	private static void findPrimeNumbers() {
		for(int i = 0; i < N; i++) {
			if(isPrimeNumber(numbers[i]) == true) {
				result++;
			}
		}
	}

	private static boolean isPrimeNumber(int number) {
		if(number <= 1) {
			return false;
		}
		for(int i = 2; i < number / 2; i++) {
			if(number % i == 0)
				return false;
		}
		return true;
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		numbers = new int[N];
		for(int i = 0; i < N; i++) {
			numbers[i] = input.nextInt();
		}
		result = 0;
	}
}
