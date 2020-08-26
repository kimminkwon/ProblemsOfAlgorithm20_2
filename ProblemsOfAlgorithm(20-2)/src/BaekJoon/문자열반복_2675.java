package BaekJoon;

import java.util.*;
import java.io.*;

public class 문자열반복_2675 {

	static int numOfTest;
	static int[] numOfRepeats;
	static String[] strings;
	static String[] results;
	
	public static void main(String[] args) {
		makeInput();
		makeResults();
		printResults();
	}

	private static void printResults() {
		for(int i = 0; i < numOfTest; i++) {
			System.out.println(results[i]);
		}	
	}

	private static void makeResults() {
		for(int i = 0; i < numOfTest; i++) {
			results[i] = makeResult(i);
		}
	}

	private static String makeResult(int index) {
		String result = "";
		for(int i = 0; i < strings[index].length(); i++) {
			for(int j = 0; j < numOfRepeats[index]; j++) {
				result = result + strings[index].charAt(i);
			}
		}
		return result;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfTest = input.nextInt();
		numOfRepeats = new int[numOfTest]; strings = new String[numOfTest]; results = new String[numOfTest];
		
		for(int i = 0; i < numOfTest; i++) {
			numOfRepeats[i] = input.nextInt();
			strings[i] = input.next();
		}
	}

}
