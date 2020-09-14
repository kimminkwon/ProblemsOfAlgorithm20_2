package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 소트인사이드_1427 {

	static String numbers;
	static int[] hashNumbers;
	
	public static void main(String[] args) {
		makeInput();
		makeHashingNumber();
		printResult();
	}

	private static void printResult() {
		for(int i = 9; i >= 0; i--) {
			for(int j = 0; j < hashNumbers[i]; j++)
				System.out.print(i);
		}
	}

	private static void makeHashingNumber() {
		for(int i = 0; i < numbers.length(); i++) {
			int num = Integer.parseInt(String.valueOf(numbers.charAt(i)));
			hashNumbers[num]++;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numbers = input.nextLine();
		hashNumbers = new int[10];
		Arrays.fill(hashNumbers, 0);
	}

}
