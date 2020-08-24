package BaekJoon;

import java.util.Scanner;

public class 달팽이는올라가고싶다_2869 {

	static long a;
	static long b;
	static long v;
	static long result;
	
	public static void main(String[] args) {
		makeInput();
		snailClimbDays();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void snailClimbDays() {
		long flagLength = v - a;
		long oneDayLength = a - b;
		
		result = flagLength % oneDayLength == 0? (flagLength / oneDayLength) : (flagLength / oneDayLength) + 1;
		result = result + 1;
	}	

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		a = input.nextLong();
		b = input.nextLong();
		v = input.nextLong();
	}
}
