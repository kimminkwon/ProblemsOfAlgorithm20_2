package BaekJoon;

import java.io.*;
import java.util.*;

public class 아스키코드_11654 {
	
	static String s;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		findAsciiCode();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findAsciiCode() {
		char c = s.charAt(0);
		result = (int)c;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		s = input.next();
	}

}
