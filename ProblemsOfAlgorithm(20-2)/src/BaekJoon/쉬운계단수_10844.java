package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 쉬운계단수_10844 {

	private static int n;
	private static long[][] stairNumberDP;

	public static void main(String[] args) {
		makeInput();
		makeStairNumber();
		printResult();
	}

	private static void printResult() {
		long sum = 0;
		for(int i = 0; i < 10; i++) {
			sum = divideAndSum(sum, stairNumberDP[n][i]);
		}
		System.out.println(sum);
	}

	private static void makeStairNumber() {
		stairNumberDP = new long[n+1][10];
		Arrays.fill(stairNumberDP[1], 1, 10, 1);
		
		for(int i = 2; i < n+1; i++) {
			stairNumberDP[i][0] = stairNumberDP[i-1][1];
			for(int j = 1; j < 9; j++) {
				stairNumberDP[i][j] = divideAndSum(stairNumberDP[i-1][j-1], stairNumberDP[i-1][j+1]);
			}
			stairNumberDP[i][9] = stairNumberDP[i-1][8];
		}
	}
	
	private static long divideAndSum(long num1, long num2) {
		return (num1 + num2) % 1000000000;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
	}

}
