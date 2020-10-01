package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 일로만들기_1463 {

	private static int n;
	private static int result;
	
	public static void main(String[] args) {
		makeInput();
		findOneValue();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findOneValue() {
		int[] checkNum = new int[n + 1];
		Arrays.fill(checkNum, Integer.MAX_VALUE);
		checkNum[1] = 0;
		for(int i = 1; i <= n; i++) {		
			// 1. 3으로 나누어떨어져서 해당 숫자가 된다.
			int divideThree = i * 3;
			// 2. 2로 나누어떨어져서 해당 숫자가 된다.
			int divideTwo = i * 2;
			// 3. 1을 빼서 해당 숫자가 된다.
			int minusOne = i + 1;

			if(divideThree <= n)
				checkNum[divideThree] = checkNum[divideThree] > checkNum[i] + 1? checkNum[i] + 1 : checkNum[divideThree];
			if(divideTwo <= n)
				checkNum[divideTwo] = checkNum[divideTwo] > checkNum[i] + 1? checkNum[i] + 1 : checkNum[divideTwo];
			if(minusOne <= n)
				checkNum[minusOne] = checkNum[minusOne] > checkNum[i] + 1? checkNum[i] + 1 : checkNum[minusOne];
		}
		result = checkNum[n];
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
	}

}
