package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 연속합_1912 {

	private static int n;
	private static int[] arr;
	private static int result;
	
	public static void main(String[] args) {
		makeInput();
		continuousSum();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void continuousSum() {
		int[] dp = new int[n];
		result = Integer.MIN_VALUE;
		
		for(int i = 0; i < n; i++) {
			if(i == 0)
				dp[i] = arr[i];
			else {
				dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
			}
			result = result < dp[i]? dp[i] : result;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		arr = new int[n];
		
		for(int i = 0; i < n; i++)
			arr[i] = input.nextInt();
	}
}
