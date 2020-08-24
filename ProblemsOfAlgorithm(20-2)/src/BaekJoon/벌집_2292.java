package BaekJoon;

import java.util.Scanner;

public class 벌집_2292 {

	static long n;
	static long result;
	
	public static void main(String[] args) {
		makeInput();
		findMinimumPath();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}
	
	private static void findMinimumPath() {
		long flag = 1;
		long floor = 1;
		
		while(true) {
			if(flag >= n) // 해당 층에 있다.
				break;
			flag = flag + (floor * 6);
			floor++;
		}	
		result = floor;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextLong();
	}
}
