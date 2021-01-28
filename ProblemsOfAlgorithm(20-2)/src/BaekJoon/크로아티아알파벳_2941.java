package BaekJoon;

import java.util.Scanner;

public class 크로아티아알파벳_2941 {
	public static void main(String[] args) throws Exception {
		printResult(getLengthOfKroatiaWord(makeInput()));
	}

	private static int getLengthOfKroatiaWord(String word) {
		String[] kroatiaHash = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		for(String s : kroatiaHash) word = word.replaceAll(s, "@");
		return word.length();
	}

	private static String makeInput() { return new Scanner(System.in).next();}
	private static void printResult(int lengthOfKroatiaWord) { System.out.println(lengthOfKroatiaWord); }
}
