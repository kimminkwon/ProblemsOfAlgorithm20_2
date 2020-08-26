package BaekJoon;

import java.util.*;
import java.io.*;

public class 다이얼_5622 {

	static String str;
	static int[] numbers;
	
	public static void main(String[] args) {
		makeInput();
		convertStringToNumber();
		printResult();
	}
	
	private static void printResult() {
		System.out.println(makeResult());
	}

	private static int makeResult() {
		int result = 0;
		
		for(int i = 0; i < numbers.length; i++) {
			result = result + numbers[i] + 1;
		}
		
		return result;
	}

	private static void convertStringToNumber() {
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = convertCharToNumber(str.charAt(i));
		}
	}

	private static int convertCharToNumber(char c) {
		int number = 0;
		switch (c) {
		case 'A': case 'B': case 'C':
			number = 2;
			break;
		case 'D': case 'E': case 'F':
			number = 3;
			break;
		case 'G': case 'H': case 'I':
			number = 4;
			break;
		case 'J': case 'K': case 'L':
			number = 5;
			break;
		case 'M': case 'N': case 'O':
			number = 6;
			break;
		case 'P': case 'Q': case 'R': case 'S':
			number = 7;
			break;
		case 'T': case 'U': case 'V':
			number = 8;
			break;
		case 'W': case 'X': case 'Y': case 'Z':
			number = 9;
			break;
		default:
			break;
		}
		
		return number;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		str = input.next();
		numbers = new int[str.length()];
	}

}
