package BaekJoon;

import java.util.*;
import java.io.*;

public class 단어공부_1157 {

	static String str;
	static int[] strHash;
	static String result;
	
	public static void main(String[] args) {
		makeInput();
		findMaximumAlphabet();
		makeResult();
		printResult();
	}
	
	
	private static void printResult() {
		System.out.println(result);
	}

	private static void makeResult() {
		int maxIndex = 0; int maxValue = 0;
		for(int i = 0; i < 26; i++) {
			if(maxValue < strHash[i]) {
				maxValue = strHash[i];
				maxIndex = i;
			}
		}
		result = isOverlap(maxIndex, maxValue) == true? "?" : hashByInteger(maxIndex);
		
	}
	
	private static boolean isOverlap(int maxIndex, int maxValue) {
		boolean isOverlap = false;
		
		for(int i = 0; i < 26; i++) {
			if(maxValue == strHash[i] && i != maxIndex) {
				isOverlap = true;
				break;
			}
		}
		
		return isOverlap;
	}

	private static void findMaximumAlphabet() {
		for(int i = 0; i < str.length(); i++) {
			hashByAlphabet(str.charAt(i));
		}
	}

	private static void hashByAlphabet(char c) {
		// A = (int)c - 65
		// a = (int)c - 97
		int index = (int)c >= 97? (int)c - 97 : (int)c - 65;
		strHash[index]++;
	}

	private static String hashByInteger(int code) {
		code = code + 65;
		char c = (char)code;
		return String.valueOf(c);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		str = input.next();
		strHash = new int[26];
	}
}
