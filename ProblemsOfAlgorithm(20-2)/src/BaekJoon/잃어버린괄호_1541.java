package BaekJoon;

import java.util.Scanner;
import java.util.StringTokenizer;

public class 잃어버린괄호_1541 {
	
	// 계산 시 가장 작은 수 - 가장 큰수 = 최솟값
	private static String exp;
	private static long result;
	
	public static void main(String[] args) {
		makeInput();
		makeTokens();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void makeTokens() {
		int minusIndex = exp.indexOf('-');
		String firstNum = minusIndex == -1? exp : exp.substring(0, minusIndex);
		String secondNum = minusIndex == -1? "" : exp.substring(minusIndex, exp.length());
		
		StringTokenizer tokenFirstNum = new StringTokenizer(firstNum, "+-");
		StringTokenizer tokenSecondNum = new StringTokenizer(secondNum, "+-");
		
		while(tokenFirstNum.hasMoreTokens()) {
			int t = Integer.parseInt(tokenFirstNum.nextToken());
			result = result + t;
		}
		
		while(tokenSecondNum.hasMoreTokens()) {
			int t = Integer.parseInt(tokenSecondNum.nextToken());
			result = result - t;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		exp = input.next();
	}
}
