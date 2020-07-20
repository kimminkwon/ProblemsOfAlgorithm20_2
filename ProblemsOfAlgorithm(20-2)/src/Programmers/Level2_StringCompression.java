package Programmers;
import java.util.*;

public class Level2_StringCompression {
	public static void main(String[] args) {
		// input examples
		String s = "abcabcabcabcdededededede";
		int answer = s.length();
		
		for(int i = 0; i < s.length() / 2; i++) {
			System.out.println(i + "개로 나눠보기");
			
		}
		
        System.out.println("정답은 " + answer);
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++) {
        	System.out.print(c[i] + " ");
        }
        String sc = String.valueOf(c);
        System.out.println(sc);
		
	}
}
