package BaekJoon;

import java.io.*;
import java.util.*;

public class 비밀이메일_2999 {
	
	private static String msg;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		msg = br.readLine();
		findMsg();
		System.out.println(sb.toString());
	}

	private static void findMsg() {
		int length = msg.length();
		int R = 0, C = 0;
		for(int i = 1; i <= Math.sqrt(length); i++) 
			for(int j = i; j <= length; j++) 
				if(i * j == msg.length()) { R = i; C = j; }
	
		int cnt = 0;
		char[][] arr = new char[C][R];
		for(int i = 0; i < C; i++)
			for(int j = 0; j < R; j++)
				arr[i][j] = msg.charAt(cnt++);
	
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++)
				sb.append(arr[j][i]);
		}
	}
}
