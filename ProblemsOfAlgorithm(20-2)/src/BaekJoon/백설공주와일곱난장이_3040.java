package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 백설공주와일곱난장이_3040 {
	
	private static int[] numOfHat = new int[9];
	private static int[] dwarfs = new int[7];
	private static int[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numOfHat = new int[9];
		
		for(int i = 0; i < 9; i++) 
			numOfHat[i] = Integer.parseInt(br.readLine());
		
		findSevenDwarfs(0, 0);
		System.out.print(resultToString());
	}

	private static String resultToString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 7; i++)
			sb.append(result[i]).append("\n");
		return sb.toString();
	}

	private static void findSevenDwarfs(int start, int cnt) {
		if(cnt == 7) {
			if(isCurrectSevenDwarfs()) result = dwarfs.clone();
			return;
		} else {
			for(int i = start; i < 9; i++) {
				dwarfs[cnt] = numOfHat[i];
				findSevenDwarfs(i + 1, cnt + 1);
			}
		}
	}

	private static boolean isCurrectSevenDwarfs() {
		int sum = 0;
		for(int i = 0; i < 7; i++) 
			sum += dwarfs[i];
		return sum == 100;
	}
}
