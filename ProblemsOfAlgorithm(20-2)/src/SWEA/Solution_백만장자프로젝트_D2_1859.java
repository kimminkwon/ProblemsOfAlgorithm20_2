package SWEA;

import java.io.*;
import java.util.*;

public class Solution_백만장자프로젝트_D2_1859 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_백만장자프로젝트.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[] prices = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				prices[i] = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(maximumProfitForStuff(N, prices)).append("\n");
        }
		System.out.print(sb.toString());
        br.close();
	}

	private static long maximumProfitForStuff(int N, int[] prices) {
		long profit = 0, currPrice = prices[N - 1];
		for(int i = N - 2; i >= 0; i--) {
			if(currPrice >= prices[i]) profit += (currPrice - prices[i]);
			else currPrice = prices[i];
		}
		return profit;
	}
}
