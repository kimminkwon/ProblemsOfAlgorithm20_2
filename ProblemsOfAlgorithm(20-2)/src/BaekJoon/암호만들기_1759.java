package BaekJoon;

import java.io.*;
import java.util.*;

public class 암호만들기_1759 {
	
	private static int L, C;
	private static char[] chars;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		chars = br.readLine().replace(" ", "").toCharArray();
		Arrays.sort(chars);
		
		findPossableKey(0, "", 0, 0, 0);
		
		System.out.print(sb.toString());
	}

	private static void findPossableKey(int v, String box, int length, int vowel, int consonant) {
		if(length == L) {
			if(vowel > 0 && consonant > 1) sb.append(box).append("\n");
			return;
		}
		if(v == C) return;
		
		if(isVowel(chars[v])) findPossableKey(v + 1, box + chars[v], length + 1, vowel + 1, consonant);
		else findPossableKey(v + 1, box + chars[v], length + 1, vowel, consonant + 1);
		findPossableKey(v + 1, box, length, vowel, consonant);
	}
	
	private static boolean isVowel(char c) {
		String check = "aeiou";
		return check.contains(String.valueOf(c));
	}
}
	
