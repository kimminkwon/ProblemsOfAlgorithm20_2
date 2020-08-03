package BaekJoon;

import java.io.*;
import java.util.*;

public class 단어정렬_1181 {
	static int n;
	static String[] stringArr;
	
	public static void main(String[] args) {
		makeInput();
		System.out.println(Arrays.toString(stringArr));
		wordSort();
		System.out.println(Arrays.toString(stringArr));
		printAnswer();
	}
	
	private static void wordSort() {
		Comparator<String> strComparator = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(s1.length() != s2.length()) {
					return s1.length() - s2.length();
				}
				else {
					return s1.compareTo(s2);
				}
			}
		};
		Arrays.sort(stringArr, strComparator);
	}
	
	private static void printAnswer() {
		for(int i = 0; i < stringArr.length; i++) {
			if(i > 0 && stringArr[i].equals(stringArr[i - 1]))
				continue;
			else
				System.out.println(stringArr[i]);
		}
	}
	
	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		stringArr = new String[n];
		for(int i = 0; i < n; i++) {
			stringArr[i] = input.next();
		}
	}
}
