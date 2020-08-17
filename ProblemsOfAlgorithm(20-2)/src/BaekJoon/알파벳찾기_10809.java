package BaekJoon;

import java.util.*;
import java.io.*;

public class 알파벳찾기_10809 {
	static String word;
	static char[] wordCharArr;
	static int[] alphabetArr;

	public static void main(String[] args) {
		makeInput();
		fillAlphabetArr();
		printResult();
	}
	
	private static void printResult() {
		for(int i = 0; i < alphabetArr.length; i++) {
			if(i == alphabetArr.length - 1) {
				System.out.println(alphabetArr[i]);
			}
			else 
				System.out.printf("%d ", alphabetArr[i]);
		}
	}

	private static void fillAlphabetArr() {
		for (int i = 0; i < alphabetArr.length; i++) {
			char thisAlphabet = (char)(97 + i);
			alphabetArr[i] = findAlphabet(thisAlphabet);
		}
	}
	
	private static int findAlphabet(char thisAlphabet) {
		int flag = -1;
		
		for(int i = 0; i < wordCharArr.length; i++) {
			if(wordCharArr[i] == thisAlphabet) {
				flag = i;
				break;
			}
		}
		
		return flag;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		word = input.next();
		initArr();
	}
	
	private static void initArr() {
		alphabetArr = new int[26];
		wordCharArr = new char[word.length()];
		Arrays.fill(alphabetArr, -1);
		wordCharArr = word.toCharArray();	
	}
	

}
