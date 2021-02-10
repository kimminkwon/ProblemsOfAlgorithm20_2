package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 배열돌리기2_16927 {
	
	private static int N, M, R;
	private static String[][] arr;
	private static int[] dOne = {0, 1, 0, -1};
	private static int[] dTwo = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new String[N][M];
		for(int i = 0; i < N; i++) 
			arr[i] = br.readLine().split(" ");
		int N0 = 0; int M0 = 0;
		int n = N; int m = M; int r = R;
		for(int i = 0; i < Math.min(M, N) / 2; i++) {
			doCycle(N0, n, M0, m, r);
			N0 = N0 + 1; M0 = M0 + 1; n = n - 1; m = m - 1;
		}
		printResult();
	}

	private static void doCycle(int N0, int N, int M0, int M, int R) {
		boolean[][] isVisited = new boolean[N][M];
		
		Queue<String> q = new LinkedList<>();
		int one = N0; int two = M0;
		
		isVisited[one][two] = true;
		q.add(arr[one][two]);
		
		for(int d = 0; d < 4; d++) {
			while(true) {
				if(isOut(one + dOne[d], two + dTwo[d], N0, N, M0, M) 
					|| isVisited[one + dOne[d]][two + dTwo[d]]) break;
				one += dOne[d]; two += dTwo[d];
				isVisited[one][two] = true;
				q.add(arr[one][two]);
			}
		}
		R = R % q.size();
		for(int i = 0; i < R; i++) q.offer(q.poll());
		
		isVisited = new boolean[N][M];
		one = N0; two = M0;
		isVisited[one][two] = true;
		arr[one][two] = q.poll();
		
		for(int d = 0; d < 4; d++) {
			while(true) {
				if(isOut(one + dOne[d], two + dTwo[d], N0, N, M0, M) 
						|| isVisited[one + dOne[d]][two + dTwo[d]]) break;
				one += dOne[d]; two += dTwo[d];
				isVisited[one][two] = true;
				arr[one][two] = q.poll();
			}
		}
		
	}
	
	private static boolean isOut(int one, int two, int N0, int N, int M0, int M) {
		return one >= N || one < N0 || two >= M || two < M0;
	}


	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) 
				sb.append(arr[i][j]).append(" ");
			sb.append("\n");
		}
			
		System.out.println(sb.toString());
	}

	
}
