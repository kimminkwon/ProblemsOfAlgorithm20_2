package BaekJoon;

import java.util.Scanner;

public class 피보나치수5_10870 {

	static int N;
	
	public static void main(String[] args) {
		makeInput();
		int res = fibonachi(N);
		printResult(res);
	}

	private static void printResult(int res) {
		System.out.println(res);
	}

	private static int fibonachi(int n) {
		if(n == 0) {
			return 0;
		}
		else if(n == 1) {
			return 1;
		}
		else {
			return fibonachi(n-1) + fibonachi(n-2);
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
	}

}
