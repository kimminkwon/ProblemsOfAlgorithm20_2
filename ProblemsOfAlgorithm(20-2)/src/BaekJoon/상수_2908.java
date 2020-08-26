package BaekJoon;

import java.util.*;
import java.io.*;

public class 상수_2908 {

	private static String numOne, numTwo;

	public static void main(String[] args) {
		makeInput();
		reverseTwoNum();
		printResult();
	}
	
	private static void printResult() {
		if(isBiggerOne() == true) 
			System.out.println(numOne);
		else
			System.out.println(numTwo);
	}

	private static boolean isBiggerOne() {
		return Integer.parseInt(numOne) > Integer.parseInt(numTwo);
	}
	
	
	private static void reverseTwoNum() {
		numOne = reverseNum(numOne);
		numTwo = reverseNum(numTwo);
	}

	private static String reverseNum(String num) {
		String numReverse = "";
		for(int i = num.length() - 1; i >= 0; i--) {
			numReverse = numReverse + String.valueOf(num.charAt(i));
		}
		return numReverse;
	}
	
	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOne = input.next();
		numTwo = input.next();
	}
}
