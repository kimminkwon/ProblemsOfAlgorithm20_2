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

public class 설탕배달_2839_2 {

	private static int N;
	private static final int MAX_NUM = 6000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		System.out.println(findMinimumBag());
	}

	private static int findMinimumBag() {
		int[] numOfBagDP = new int[N + 1];
		numOfBagDP[0] = MAX_NUM;
		numOfBagDP[1] = MAX_NUM;
		numOfBagDP[2] = MAX_NUM;
		numOfBagDP[3] = 1;
		
		if(N >= 4) numOfBagDP[4] = MAX_NUM;
		if(N >= 5) numOfBagDP[5] = 1;
		
		for(int i = 6; i <= N; i++) {
			int addThreeCase = numOfBagDP[i - 3] + 1;
			int addFiveCase = numOfBagDP[i - 5] + 1;
			numOfBagDP[i] = Math.min(addFiveCase, addThreeCase);
		}
		
		return numOfBagDP[N] >= MAX_NUM ? -1 : numOfBagDP[N];
	}
}
