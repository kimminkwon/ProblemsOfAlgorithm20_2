package BaekJoon;

import java.util.Scanner;

public class 피보나치수2_2748 {

	private static int n;
	private static long[] fibonachiNumbers;

	public static void main(String[] args) {
		makeInput();
		fibonachi();
		printResult();
	}

	private static void printResult() {
		System.out.println(fibonachiNumbers[n]);
	}

	private static void fibonachi() {
		for(int i = 2; i <= n; i++) {
			fibonachiNumbers[i] = fibonachiNumbers[i - 2] + fibonachiNumbers[i - 1];
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		fibonachiNumbers = new long[n + 1];
		fibonachiNumbers[0] = 0;
		fibonachiNumbers[1] = 1;
		
	}

}
