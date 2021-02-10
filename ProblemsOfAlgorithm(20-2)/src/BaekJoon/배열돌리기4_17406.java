package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 배열돌리기4_17406 {
	
	private static int N, M, K, result;
	private static String[][] arr;
	private static List<int[]> perm = new ArrayList<>();
	private static int[][] cycles;
	private static int[] dOne = {0, 1, 0, -1};
	private static int[] dTwo = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new String[N][M];
		cycles = new int[K][3];
		perm = new ArrayList<>();
		result = Integer.MAX_VALUE;
		
		permutation(0, new int[K], new boolean[K]);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) arr[i][j] = st.nextToken();
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			cycles[i][0] = Integer.parseInt(st.nextToken());
			cycles[i][1] = Integer.parseInt(st.nextToken());
			cycles[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for(int[] p : perm) {
			String[][] thisArr = getArr();
			
			for(int i = 0; i < K; i++) {
				int r = cycles[p[i]][0]; int c = cycles[p[i]][1]; int s = cycles[p[i]][2];
				int N0 = r-s - 1; int N = r+s;
				int M0 = c-s - 1; int M = c+s;
				
				for(int j = 0; j < s; j++) {
					doCycle(N0, N, M0, M, 1, thisArr);
					N0++; M0++; N--; M--;
				}
			}
			result = Math.min(result, calculateResult(thisArr));
		}
		System.out.println(result);
	}
	
	private static String[][] getArr() {
		String[][] thisArr = new String[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) 
				thisArr[i][j] = arr[i][j];
		}
		return thisArr;
	}

	private static int calculateResult(String[][] thisArr) {
		int sum = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int subSum = 0;
			for(int j = 0; j < M; j++) 
				subSum += Integer.parseInt(thisArr[i][j]);
			sum = Math.min(subSum, sum);
		}
		return sum;
	}

	private static void doCycle(int N0, int N, int M0, int M, int R, String[][] arr) {
		boolean[][] isVisited = new boolean[N][M];
		
		Deque<String> q = new ArrayDeque<>();
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
		q.offerFirst(q.pollLast());
		
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

	private static void permutation(int cnt, int[] permArr, boolean[] selected) {
		if(cnt == K) {
			perm.add(permArr.clone());
			return;
		}
		for(int i = 0; i < K; i++) {
			if(!selected[i]) {
				permArr[cnt] = i;
				selected[i] = true;
				permutation(cnt + 1, permArr, selected);
				selected[i] = false;
			}
		}
	}
}
