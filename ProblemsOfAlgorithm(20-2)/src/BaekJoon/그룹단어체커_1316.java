package BaekJoon;

import java.util.*;
import java.io.*;

public class 그룹단어체커_1316 {
	static int n;
	static String[] words;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		wordsCheck();
		printResult();
	}
	
	private static void printResult() {
		System.out.println(result);
	}
	
	private static void wordsCheck() {
		for(int i = 0; i < n; i++) {
			if(wordCheck(words[i]) == true) {
				result++;
			}
		}
	}
	
	
	private static boolean wordCheck(String word) {
		boolean isGroup = true;
		String zipWord = zipWord(word);
		HashSet<Character> hashSet = new HashSet<Character>();
		
		for(int i = 0; i < zipWord.length(); i++) {
			if(hashSet.add(zipWord.charAt(i)) == false) {
				isGroup = false;
				break;	
			}
		}
		
		return isGroup;
	}
	
	private static String zipWord(String word) {
		int cnt = 0;
		String zipString = "";
		zipString = zipString + (String.valueOf(word.charAt(0)));
		for(int i = 1; i < word.length(); i++) {
			if(zipString.charAt(cnt) != word.charAt(i)) {
				zipString = zipString + (String.valueOf(word.charAt(i)));
				cnt++;
			}
		}
		return zipString;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		
		n = input.nextInt();
		words = new String[n];
		
		for(int i = 0; i < n; i++) {
			words[i] = input.next();
		}
		
		result = 0;
	}

}
