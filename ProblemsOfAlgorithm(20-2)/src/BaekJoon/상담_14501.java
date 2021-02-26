package BaekJoon;

import java.io.*;
import java.util.*;

public class 상담_14501 {
	
	private static int N, profit;
	private static int[][] days;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		days = new int[N][2];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			days[i][0] = Integer.parseInt(st.nextToken());
			days[i][1] = Integer.parseInt(st.nextToken());
		}
		
		findMaximumProfit(0, 0, 0);
		System.out.println(profit);
	}

	private static void findMaximumProfit(int cnt, int currProfit, int passable) {
		if(cnt == N) {
			profit = Math.max(currProfit, profit);
			return;
		}
		if(passable > 0 || cnt + days[cnt][0] > N) findMaximumProfit(cnt + 1, currProfit, passable - 1);
		else {
			findMaximumProfit(cnt + 1, currProfit, passable);
			findMaximumProfit(cnt + 1, currProfit + days[cnt][1], days[cnt][0] - 1);
		}
	}
}
