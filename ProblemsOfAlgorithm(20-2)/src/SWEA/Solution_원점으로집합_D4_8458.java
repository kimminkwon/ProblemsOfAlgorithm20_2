package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_원점으로집합_D4_8458 {

	private static final int MAX = 63246;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_원점으로집합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] coors = new int[N][2];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				coors[i][0] = Integer.parseInt(st.nextToken());
				coors[i][1] = Integer.parseInt(st.nextToken());
			}
			sb.append("#").append(tc).append(" ").append(findMinimumTryToZero(N, coors)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static int findMinimumTryToZero(int N, int[][] coors) {
		int[] vertexDist = new int[N];
		boolean[] isEven = new boolean[N];
		for(int i = 0; i < N; i++) {
			vertexDist[i] = getManhattanDist(coors[i][0], coors[i][1]);
			if(vertexDist[i] % 2 == 0) isEven[i] = true;
		}

		int maxDist = getMaxDist(vertexDist);
		if(maxDist == 0) return 0;
		if(!isAllEvenOrOdd(isEven)) return -1;

		if(isEven[0]) {
			long moveNum = 0;
			for(int move = 1; move < MAX; move++) {
				moveNum += move;
				long value = getMaxN(move);
				if(moveNum % 2 == 0 && value >= maxDist) return move;
			}
		} else {
			long moveNum = 0;
			for(int move = 1; move < MAX; move++) {
				moveNum += move;
				long value = getMaxN(move);
				if(moveNum % 2 == 1 && value >= maxDist) return move;
			}
		}
		return 0;
	}

	private static int getMaxDist(int[] vertexDist) {
		int max = -1;
		for(int i = 0; i < vertexDist.length; i++)
			max = Math.max(vertexDist[i], max);
		return max;
	}

	private static boolean isAllEvenOrOdd(boolean[] isEven) {
		for(int i = 1; i < isEven.length; i++)
			if(isEven[i - 1] != isEven[i]) return false;
		return true;
	}

	private static long getMaxN(int N) {
		return 0L + (N * (N + 1L)) / 2L;
	}
	private static int getManhattanDist(int x, int y) {
		return Math.abs(x) + Math.abs(y);
	}
}
