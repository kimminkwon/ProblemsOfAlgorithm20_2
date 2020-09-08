package BaekJoon;

import java.util.Scanner;

public class 영화감독숌_1436 {

	static int n;
	static long result;
	
	public static void main(String[] args) {
		makeInput();
		findNthSixSixSix();
		printNthSixSixSix();
	}

	private static void printNthSixSixSix() {
		System.out.println(result);
	}

	private static void findNthSixSixSix() {
		int cnt = 0;
		long num = 666;
		while(true) {
			if(String.valueOf(num).contains("666")) {
				cnt++;
				if(cnt == n)
					break;
			}
			num++;
		}
		result = num;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
	}

}
