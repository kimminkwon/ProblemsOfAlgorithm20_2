package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_보급로_SW문제해결응용4일차 {

	private static class State {
		int x, y, time;

		public State(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public String toString() {
			return "[" +
					"(" + x +
					", " + y +
					") t=" + time +
					']';
		}
	}

	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_보급로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				String box = br.readLine();
				for(int j = 0; j < N; j++)
					map[i][j] = Character.getNumericValue(box.charAt(j));
			}

			sb.append("#").append(tc).append(" ").append(findMinimumTimePath(N, map)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int findMinimumTimePath(int N, int[][] map) {
		int minPath = Integer.MAX_VALUE;
		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(0, 0, 0));
		int[][] visited = new int[N][N];

		for(int i = 0; i < N; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);

		while(!q.isEmpty()) {
			State s = q.poll();
			if(s.x == N - 1 && s.y == N - 1) minPath = Math.min(s.time, minPath);

			for(int d = 0; d < 4; d++) {
				int nx = s.x + dx[d], ny = s.y + dy[d];
				if(isOut(nx, ny, N)) continue;
				int nTime = s.time + map[nx][ny];
				if(visited[nx][ny] <= nTime) continue;
				visited[nx][ny] = nTime;
				q.offer(new State(nx, ny, nTime));
			}
		}
		return minPath;
	}

	private static boolean isOut(int x, int y, int N) {
		return x >= N || y >= N || x < 0 || y < 0;
	}

}
