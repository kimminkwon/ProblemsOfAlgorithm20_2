package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_사람네트워크2_D6_SW문제해결응용 {
	
	private static final int INF = 10000000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_사람네트워크2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] adjArr  = new int[N][N];
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					adjArr[i][j] = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(tc).append(" ").append(findMinimumCC(N, adjArr)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int findMinimumCC(int N, int[][] adjArr) {
		int[][] minPath = makeInitPath(N, adjArr);
		
		for(int k = 0; k < N; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++) 
					if(i != j && j != k) minPath[i][j] = Math.min(minPath[i][j], minPath[i][k] + minPath[k][j]);

		int[] cc = new int[N];
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) 
				sum += minPath[i][j] >= INF ? 0 : minPath[i][j];
			cc[i] = sum;
		}
		
		int min = INF;
		for(int i = 0; i < N; i++) if(min > cc[i]) min = cc[i];
		
		return min;
	}

	private static void printArr(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) 
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
			
	}

	private static int[][] makeInitPath(int N, int[][] adjArr) {
		int[][] minPath = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				minPath[i][j] = adjArr[i][j] == 0 ? INF : adjArr[i][j];
				
		return minPath;
	}

}
