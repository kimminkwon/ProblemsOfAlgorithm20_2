package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_탈주범검거_모의SW역량테스트_1953 {

	private static class State {
		int x, y;
		boolean[] tunnel;

		public State(int x, int y, int tNum) {
			this.x = x;
			this.y = y;
			setTunnel(tNum);
		}

		private void setTunnel(int tNum) {
			tunnel = new boolean[4];
			switch (tNum) {
				case 1:
					Arrays.fill(tunnel, true);
					break;
				case 2:
					tunnel[0] = true; tunnel[2] = true;
					break;
				case 3:
					tunnel[1] = true; tunnel[3] = true;
					break;
				case 4:
					tunnel[0] = true; tunnel[1] = true;
					break;
				case 5:
					tunnel[1] = true; tunnel[2] = true;
					break;
				case 6:
					tunnel[2] = true; tunnel[3] = true;
					break;
				case 7:
					tunnel[3] = true; tunnel[0] = true;
					break;
			}
		}
	}
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_탈주범검거.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			sb.append("#").append(tc).append(" ").append(findCriminalLocation(N, M, R, C, L, map)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int findCriminalLocation(int N, int M, int R, int C, int L, int[][] map) {
		int t = 1;
		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(R, C, map[R][C]));
		boolean[][] visited = new boolean[N][M];
		visited[R][C] = true;

		while(!q.isEmpty() && t < L) {
			int size = q.size();
			while(--size >= 0) {
				State s = q.poll();
				for(int d = 0; d < 4; d++) {
					int nx = s.x + dx[d], ny = s.y + dy[d];
					if(isOut(nx, ny, N, M) || visited[nx][ny]) continue; // 범위를 벗어났거나 이미 방문했을 경우
					State ns = new State(nx, ny, map[nx][ny]);
					if(!s.tunnel[d] || !ns.tunnel[nextLoc(d)]) continue; // 나갈수 있는 길이 없거나 들어갈 수 있는 길이 없는 경우
					q.offer(ns);
					visited[nx][ny] = true;
				}
			}
			t++;
		}
		return countVisited(N, M, visited);
	}

	private static int countVisited(int N, int M, boolean[][] visited) {
		int count = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(visited[i][j]) count++;
		return count;
	}

	private static int nextLoc(int loc) {
		switch (loc) {
			case 0: return 2;
			case 1: return 3;
			case 2: return 0;
			case 3: return 1;
		}
		return -1;
	}

	private static boolean isOut(int x, int y, int N, int M) {
		return x >= N || y >= M || x < 0 || y < 0;
	}

	private static void printArr(boolean[][] copyMap) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < copyMap.length; i++){
			for(int j = 0; j < copyMap[i].length; j++) {
				sb.append(String.format("%3d", copyMap[i][j] ? 1 : 0));
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
