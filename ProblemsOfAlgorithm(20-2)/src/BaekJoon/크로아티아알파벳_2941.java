package BaekJoon;

import java.util.*;
import java.io.*;

public class 크로아티아알파벳_2941 {
	static String word;
	
	public static void main(String[] args) {
		makeInput();
		makeKroatia();
		printResult();
	}
	
	private static void printResult() {
		System.out.println(word.length());
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		word = input.next();
	}
	
	private static void makeKroatia() {
		String[] kroatiaHash = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		for(int i = 0; i < kroatiaHash.length; i++) {
			if(word.contains(kroatiaHash[i])) {
				word = word.replace(kroatiaHash[i], "#");
			}
		}
	}

}
