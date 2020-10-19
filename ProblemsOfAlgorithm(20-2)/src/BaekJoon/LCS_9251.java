package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class LCS_9251 {

	private static String string1;
	private static String string2;
	private static int result;
	
	public static void main(String[] args) {
		makeInput();
		lcs();
		printResult();
	}

	private static void lcs() {
		int[][] dpLcs = new int[string1.length() + 1][string2.length() + 1];
		
		for(int i = 0; i < string1.length()+1; i++)
			dpLcs[i][0] = 0;
		for(int i = 0; i < string2.length()+1; i++)
			dpLcs[0][i] = 0;
		
		for(int i = 1; i < string1.length()+1; i++) {
			for(int j = 1; j < string2.length()+1; j++) {
				if(string1.charAt(i-1) == string2.charAt(j-1)) {
					dpLcs[i][j] = dpLcs[i-1][j-1] + 1;
				} else {
					dpLcs[i][j] = Math.max(dpLcs[i][j-1], dpLcs[i-1][j]);
				}
			}
		}
		result = dpLcs[string1.length()][string2.length()];
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		string1 = input.next();
		string2 = input.next();
	}

}
