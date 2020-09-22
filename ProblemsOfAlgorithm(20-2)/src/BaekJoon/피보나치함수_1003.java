package BaekJoon;

import java.util.Scanner;

public class 피보나치함수_1003 {

	private static int numOfTestcase, maxNum;
	private static long[] fibonachiCallZero, fibonachiCallOne;
	private static int[] fibonachiFlags;

	public static void main(String[] args) {
		makeInput();
		makeFibonachiCallOneAndZero();
		printResult();
	}

	private static void printResult() {
		for(int i = 0; i < numOfTestcase; i++) {
			int index = fibonachiFlags[i];
			System.out.printf("%d %d\n", fibonachiCallZero[index], fibonachiCallOne[index]);
		}
	}

	private static void makeFibonachiCallOneAndZero() {
		for(int i = 0; i <= maxNum; i++) {
			if(i == 0) {
				fibonachiCallZero[i] = 1; 
				fibonachiCallOne[i] = 0; 
			} 
			else if (i == 1) {
				fibonachiCallZero[i] = 0;
				fibonachiCallOne[i] = 1;
			} 
			else {
				fibonachiCallOne[i] = fibonachiCallOne[i - 2] + fibonachiCallOne[i - 1];
				fibonachiCallZero[i] = fibonachiCallZero[i - 2] + fibonachiCallZero[i - 1];
			}
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfTestcase = input.nextInt();
		fibonachiFlags = new int[numOfTestcase];
		maxNum = 0;
		for(int i = 0; i < numOfTestcase; i++) {
			fibonachiFlags[i] = input.nextInt();
			if(fibonachiFlags[i] > maxNum) { 
				maxNum = fibonachiFlags[i];
			}
		}
		fibonachiCallZero = new long[maxNum + 1];
		fibonachiCallOne = new long[maxNum + 1];
		
	}
}
