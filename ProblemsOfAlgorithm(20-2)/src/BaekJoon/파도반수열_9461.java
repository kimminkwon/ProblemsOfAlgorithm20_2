package BaekJoon;

import java.util.Scanner;

public class 파도반수열_9461 {
	
	private static int numOfTestcase;
	private static long[] padobans;
	private static int[] n;
	private static int maxFlag;

	public static void main(String[] args) {
		makeInput();
		findPadobanPerm();
		printResult();
	}

	private static void printResult() {
		for(int i = 0; i < numOfTestcase; i++) {
			System.out.println(padobans[n[i]]);
		}
	}

	private static void findPadobanPerm() {
		for(int i = 1; i <= maxFlag; i++) {
			if(i < 4)
				padobans[i] = 1;
			else {
				padobans[i] = padobans[i - 3] + padobans[i - 2];
			}
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfTestcase = input.nextInt();
		n = new int[numOfTestcase];
		maxFlag = 0;
		for(int i = 0; i < numOfTestcase; i++) {
			n[i] = input.nextInt();
			if(n[i] > maxFlag)
				maxFlag = n[i];
		}
		padobans = new long[maxFlag + 1];
	}

}
