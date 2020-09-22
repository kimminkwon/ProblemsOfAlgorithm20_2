package BaekJoon;

import java.util.Scanner;

public class 공일타일_1904 {

	private static int n;
	private static long[] binaryTilesNumber;

	public static void main(String[] args) {
		makeInput();
		makeBinaryTilesNumber();
		printResult();
	}

	private static void printResult() {
		System.out.println(binaryTilesNumber[n]);
	}

	private static void makeBinaryTilesNumber() {
		for(int i = 1; i <= n; i++) {
			if(i == 1) 
				binaryTilesNumber[i] = 1;
			else if(i == 2)
				binaryTilesNumber[i] = 2;
			else {
				binaryTilesNumber[i] = binaryTilesNumber[i-2] + binaryTilesNumber[i-1];
			}
			binaryTilesNumber[i] = binaryTilesNumber[i] % 15746;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		binaryTilesNumber = new long[n + 1];
	}

}
