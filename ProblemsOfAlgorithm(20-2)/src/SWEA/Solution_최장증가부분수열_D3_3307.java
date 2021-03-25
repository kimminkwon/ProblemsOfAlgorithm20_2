package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_최장증가부분수열_D3_3307 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_최장증가부분수열.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] progression  = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) progression[i] = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(findLIS(N, progression)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int findLIS(int n, int[] progression) {
		int[] lisDp = new int[n];
		Arrays.fill(lisDp, 1);
		for(int i = 1; i < n; i++)
			for(int j = 0; j < i; j++)
				if(progression[i] > progression[j] && lisDp[i] < lisDp[j] + 1) lisDp[i] = lisDp[j] + 1;
		int maxLis = 0;
		for(int i = 0; i < n; i++) maxLis = Math.max(maxLis, lisDp[i]);
		return maxLis;
	}

}
