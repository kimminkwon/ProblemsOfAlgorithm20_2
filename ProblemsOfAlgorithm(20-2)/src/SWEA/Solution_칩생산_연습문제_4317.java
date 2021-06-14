package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_칩생산_연습문제_4317 {

	private static int result;
	private static Set<String> hs = new HashSet<>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_칩생산.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			int[][] wayper = new int[H][W];
			int[][] memo = new int[1 << H][W - 1];
			for(int i = 0; i < (1 << H); i++) Arrays.fill(memo[i], -1);

			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++)
					wayper[i][j] = Integer.parseInt(st.nextToken());
			}
			result = 0;
			findMaximumSetChips(H, W, 0, 0, wayper, memo, 0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static void findMaximumSetChips(int H, int W, int x, int y, int[][] wayper, int[][] memo, int numOfChip) {
		if(x >= H - 1) { x = 0; y++; }
		if(y >= W - 1) {
			result = Math.max(result, numOfChip);
			return;
		}
		if(x == 0) {
			int visited = 0;
			for(int i = 0; i < H; i++)
				if(wayper[i][y] == 2) visited = visited | (1 << i);
			if(memo[visited][y] >= numOfChip) return;
			memo[visited][y] = numOfChip;
		}
		if(x < H - 1 && y < W - 1 && isCanSetChip(x, y, wayper)) {
			setChip(x, y, wayper);
			findMaximumSetChips(H, W, x + 2, y, wayper, memo, numOfChip + 1);
			removeChip(x, y, wayper);
		}
		findMaximumSetChips(H, W, x + 1, y, wayper, memo, numOfChip);
	}

	private static boolean isCanSetChip(int x, int y, int[][] wayper) {
		for(int i = x; i < x + 2; i++)
			for(int j = y; j < y + 2; j++)
				if(wayper[i][j] != 0) return false;
		return true;
	}

	private static void setChip(int x, int y, int[][] wayper) {
		for(int i = x; i < x + 2; i++)
			for(int j = y; j < y + 2; j++)
				wayper[i][j] = 2;
	}

	private static void removeChip(int x, int y, int[][] wayper) {
		for(int i = x; i < x + 2; i++)
			for(int j = y; j < y + 2; j++)
				wayper[i][j] = 0;
	}

}