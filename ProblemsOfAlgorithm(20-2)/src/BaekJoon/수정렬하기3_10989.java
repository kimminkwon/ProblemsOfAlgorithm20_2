package BaekJoon;

import java.util.*;
import java.io.*;

public class 수정렬하기3_10989 {
	static int n;
	static int[] cnt;
	
	public static void main(String[] args) throws IOException {
		makeInput();
		printResult();
	}
	
	private static void makeInput() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());
		int flag;
		cnt = new int[10010];
		for(int i = 0; i < n; i++) {
			flag = Integer.parseInt(input.readLine());
			cnt[flag]++;
		}
	}
	
	private static void printResult() {
		for(int i = 0; i < cnt.length; i++) {
			if(cnt[i] != 0) {
				for(int c = 0; c < cnt[i]; c++) {
					System.out.println(i);
				}
			}
		}
	}
}
