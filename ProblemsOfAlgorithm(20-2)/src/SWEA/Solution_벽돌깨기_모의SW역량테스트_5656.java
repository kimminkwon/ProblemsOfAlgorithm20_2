package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_벽돌깨기_모의SW역량테스트_5656 {

	private static class State {
		int x, y, size;

		public State(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	private static int result = 0;
	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_벽돌깨기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());

			int[][] map = new int[H][W];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			result = Integer.MAX_VALUE;
			makePermForbrokeBlocks(N, H, W, map, new int[N], 0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void makePermForbrokeBlocks(int N, int H, int W, int[][] map, int[] perm, int index) {
		if(index == N) {
			result = Math.min(result, brokeBlocks(N, H, W, copyMap(H, W, map), perm));
			return;
		}
		for(int i = 0; i < W; i++) {
			perm[index] = i;
			makePermForbrokeBlocks(N, H, W, map, perm, index + 1);
		}
	}

	private static int brokeBlocks(int N, int H, int W, int[][] copyMap, int[] perm) {
		int minCount = Integer.MAX_VALUE;
		for(int i = 0; i < perm.length; i++) {
			dropBead(N, H, W, copyMap, perm[i]);
			sortBlock(H, W, copyMap);
			minCount = Math.min(minCount, countRemainBlock(H, W, copyMap));
		}
		return minCount;
	}

	private static int countRemainBlock(int H, int W, int[][] copyMap) {
		int count = 0;
		for(int i = 0; i < H; i++)
			for(int j = 0; j < W; j++)
				if(copyMap[i][j] != 0) count++;
		return count;
	}

	private static void sortBlock(int H, int W, int[][] copyMap) {
		for(int i = H - 2; i >= 0; i--)
			for(int j = W - 1; j >= 0; j--)
				if(copyMap[i][j] != 0 && copyMap[i + 1][j] == 0)
					doDown(H, i, j, copyMap);
	}

	private static void doDown(int H, int x, int y, int[][] copyMap) {
		while(true) {
			int nx = x + 1;
			if(nx >= H || copyMap[nx][y] != 0) break;
			copyMap[nx][y] = copyMap[x][y];
			copyMap[x][y] = 0;
			x = nx;
		}
	}

	private static void dropBead(int N, int H, int W, int[][] copyMap, int dropLoc) {
		// 1. 현재 위치에서 깨질 블록을 찾는다.
		int sx = -1;
		for(int i = 0; i < H; i++)
			if(copyMap[i][dropLoc] > 0) {
				sx = i;
				break;
			}

		if(sx == -1) return;

		// 2. 블록을 깨트리기 시작한다.
		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(sx, dropLoc, copyMap[sx][dropLoc]));

		while(!q.isEmpty()) {
			State s = q.poll();
			// 3-1. 자신부터 깨짐
			copyMap[s.x][s.y] = 0;
			// 3-2. 연쇄적인 깨짐
			for(int gap = 1; gap < s.size; gap++) {
				for(int d = 0; d < 4; d++) {
					int nx = s.x + (dx[d] * gap), ny = s.y + (dy[d] * gap);
					if(isOut(nx, ny, H, W) || copyMap[nx][ny] == 0) continue;
					q.offer(new State(nx, ny, copyMap[nx][ny]));
					copyMap[nx][ny] = 0;
				}
			}
		}
	}

	private static int[][] copyMap(int H, int W, int[][] map) {
		int[][] newMap = new int[H][W];
		for(int i = 0; i < H; i++)
			for(int j = 0; j < W; j++)
				newMap[i][j] = map[i][j];
		return newMap;
	}

	private static boolean isOut(int x, int y, int H, int W) {
		return x >= H || y >= W || x < 0 || y < 0;
	}

	private static void printArr(int[][] copyMap) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < copyMap.length; i++){
			for(int j = 0; j < copyMap[i].length; j++) {
				sb.append(String.format("%3d", copyMap[i][j]));
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
