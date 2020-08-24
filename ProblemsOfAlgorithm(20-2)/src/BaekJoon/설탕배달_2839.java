package BaekJoon;

import java.util.Scanner;

public class 설탕배달_2839 {

	static int n;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		findMinimumThreeBag();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}
	
	private static void findMinimumThreeBag() {
		int res = 0;
		
		while(true) {
			if(n % 5 == 0) {
				res = res + (n / 5);
				break;
			}
				
			else if(n < 3) {
				res = -1;
				break;
			}
			else {
				n = n - 3;
				res++;
			}
		}
		result = res;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
	}
}
