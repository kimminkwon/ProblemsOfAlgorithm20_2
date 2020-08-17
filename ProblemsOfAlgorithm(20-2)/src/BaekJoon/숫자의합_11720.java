package BaekJoon;

import java.util.*;
import java.io.*;

public class 숫자의합_11720 {
	static int n; 
	static String number;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		sumOfNumber();
		printResult();	
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void sumOfNumber() {
		for(int i = 0; i < number.length(); i++) {
			String boxString = Character.toString(number.charAt(i));
			result = result + Integer.parseInt(boxString);
		}
	}

	private static void makeInput() {
		result = 0;
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		number = input.next();
	}
	
	
	
	
}
