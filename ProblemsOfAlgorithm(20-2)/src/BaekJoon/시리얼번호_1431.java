package BaekJoon;

import java.util.*;
import java.io.*;

public class 시리얼번호_1431 {
	static int n;
	static String[] strArr;

	public static void main(String[] args) {
		makeInput();
		serialSort();
		printResult();
	}
	
	private static void serialSort() {
		Comparator<String> serialComparator = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				int sumInteger1 = getIntegerSum(s1);
				int sumInteger2 = getIntegerSum(s2);
				
				if(s1.length() != s2.length()) { // 조건 1
					return s1.length() - s2.length();
				}
				else { // 길이가 같다면
					if(sumInteger1 != sumInteger2) { // 조건 2
						return sumInteger1 - sumInteger2;
					}
					else // 조건 3
						return s1.compareTo(s2);
				}
			}
		};
		
		Arrays.sort(strArr, serialComparator);
	}
	
	private static int getIntegerSum(String s) {
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				sum = sum + Character.getNumericValue(s.charAt(i));
			}
		}
		return sum;
	}
	
	private static void makeInput() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		strArr = new String[n];
		
		for(int i = 0; i < n; i++) {
			strArr[i] = sc.next();
		}
	}
	
	private static void printResult() {
		for(int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}
	}
}
