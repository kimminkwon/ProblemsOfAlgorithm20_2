package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_농작물수확하기_D3_2805 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_농작물수확하기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[][] farm = new int[N][N];
			for(int i = 0; i < N; i++) {
				String box = br.readLine();
				for(int j = 0; j < N; j++)
					farm[i][j] = Character.getNumericValue(box.charAt(j));
			}
			sb.append("#").append(tc).append(" ").append(calculateProfitInFarm(N, farm)).append("\n");
        }
		System.out.print(sb.toString());
        br.close();
	}

	private static int calculateProfitInFarm(int N, int[][] farm) {
		int profit = 0, left = N / 2, right = N / 2;
		for(int i = 0; i < N; i++) {
			for(int j = left; j <= right; j++) profit += farm[i][j];
			if(i >= N / 2) { left++; right--; }
			else { left--; right++; }
		}
		return profit;
	}
}
