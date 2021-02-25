package BaekJoon;

import java.io.*;
import java.util.*;

public class 주사위쌓기_2116 {
	
	private static int N, result;
	private static int[][] dices;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dices[i][0] = Integer.parseInt(st.nextToken());
			dices[i][1] = Integer.parseInt(st.nextToken());
			dices[i][2] = Integer.parseInt(st.nextToken());
			dices[i][3] = Integer.parseInt(st.nextToken());
			dices[i][4] = Integer.parseInt(st.nextToken());
			dices[i][5] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < 6; i++)
			findMaximumSumOfDice(0, 0, dices[0][i]);
		System.out.println(result);
	}
	
	private static void findMaximumSumOfDice(int cnt, int sum, int preNum) {
		if(cnt == N) {
			result = Math.max(result, sum);
			return;
		}
		int index = -1;
		for(int i = 0; i < 6; i++) 
			if(dices[cnt][i] == preNum) index = i;
		
		int reverseIndex = reverseIndex(index);
		int max = 0;
		for(int i = 0; i < 6; i++)
			if(i != reverseIndex && i != index) max = Math.max(max, dices[cnt][i]);
		
		findMaximumSumOfDice(cnt + 1, sum + max, dices[cnt][reverseIndex(index)]);
	}
	
	private static int reverseIndex(int index) {
		switch (index) {
		case 0:
			return 5;
		case 1:
			return 3;
		case 2:
			return 4;
		case 3:
			return 1;
		case 4:
			return 2;
		case 5:
			return 0;
		}
		return -1;
	}
}
	
