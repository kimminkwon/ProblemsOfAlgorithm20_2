package BaekJoon;

import java.util.ArrayList;
import java.util.Scanner;

public class 베르트랑공준_4948 {

	static ArrayList<Integer> inputData;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) {
		makeInput();
		countPrimeNumbers();
		printResult();
	}
	
	private static void countPrimeNumbers() {
		for(int i = 0; i < inputData.size(); i++) {
			result.add(countPrimeNumber(inputData.get(i)));
		}
		
	}

	private static int countPrimeNumber(int n) {
		int result = 0;
		for(int i = n+1; i <= 2 * n; i++) {
			if(isPrimeNumber(i) == true)
				result++;
		}
		return result;
	}

	private static void printResult() {
		for(int r : result) {
			System.out.println(r);
		}
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
	
	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		inputData = new ArrayList<Integer>();
		while(true) {
			int n = input.nextInt();
			if(n == 0)
				break;
			else
				inputData.add(n);
		}
		
		result = new ArrayList<Integer>();
	}
}
