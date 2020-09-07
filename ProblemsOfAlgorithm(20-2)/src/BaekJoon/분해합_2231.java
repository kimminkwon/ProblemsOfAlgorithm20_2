package BaekJoon;

import java.util.Scanner;

public class 분해합_2231 {

	static int N;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		printDecompositionSum();
	}

	private static void printDecompositionSum() {
		boolean isDecomposable = findDecompositionSum();
		if(isDecomposable == false) {
			System.out.println("0");
		}
		else {
			System.out.println(result);
		}
	}

	private static boolean findDecompositionSum() {
		boolean res = false;
		
		for(int k = 1; k <= N; k++) {
			int digitSum = k < 10? k : makeDigitSum(k);
			
			if((N - k) == digitSum) {
				result = k;
				res = true;
				break;
			}
		}
		
		return res;
	}

	private static int makeDigitSum(int k) {
		String s = String.valueOf(k);
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			sum = sum + Integer.parseInt(String.valueOf(s.charAt(i)));
		}
		return sum;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
	}

}
