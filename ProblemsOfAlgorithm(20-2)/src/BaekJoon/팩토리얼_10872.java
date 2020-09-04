package BaekJoon;

import java.util.Scanner;

public class 팩토리얼_10872 {
	
	static int N;

	public static void main(String[] args) {
		makeInput();
		int res = factorial(N);
		printResult(res);
	}

	private static void printResult(int res) {
		System.out.println(res);
	}

	private static int factorial(int n) {
		if(n <= 1) {
			return 1;
		}
		else if (n == 2) {
			return 2;
		}
		else
			return n * factorial(n-1);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
	}

}
