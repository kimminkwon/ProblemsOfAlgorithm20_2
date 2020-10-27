package BaekJoon;

import java.util.Scanner;

public class 동전0_11047 {

	private static int n;
	private static long k;
	private static int[] costs;
	private static int result;
	
	public static void main(String[] args) {
		makeInput();
		findMinumumCoins();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findMinumumCoins() {
		for(int i = n-1; i >= 0; i--) {
			if(costs[i] <= k) {
				result = (int) (result + (k / costs[i]));
				k = k % costs[i];
			}
			if(k <= 0)
				break;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		k = input.nextLong();
		
		costs = new int[n];
		for(int i = 0; i < n; i++) {
			costs[i] = input.nextInt();
		}
	}

}
