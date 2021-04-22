package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_파핑파핑지뢰찾기_D4_1868 {

	private static class State {
		int x, y;
		public State(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_파핑파핑지뢰찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 표의 크기
			char[][] map = new char[N][N];

			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			int result = findMinimumClickForFillMap(N, map);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static int findMinimumClickForFillMap(int N, char[][] map) {
		int count = 0;
		Deque<State> clickList = findInitState(N, map);
		while(true) {
			Deque<State> q = new ArrayDeque<>();
			if(clickList.isEmpty()) break;
			State init = clickList.poll();
			if(map[init.x][init.y] == '#') continue;
			q.offer(init);
			map[init.x][init.y] = '#';
			while(!q.isEmpty()) {
				State s = q.poll();
				for(int d = 0; d < 8; d++) {
					int nx = s.x + dx[d], ny = s.y + dy[d];
					if(isOut(nx, ny, N) || map[nx][ny] != '.') continue;
					map[nx][ny] = '#';
					if(isNotBomb(nx, ny, N, map)) q.offer(new State(nx, ny));
				}
			}
			count++;
		}
		return count + countNotClickArea(N, map);
	}

	private static int countNotClickArea(int N, char[][] map) {
		int count = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(map[i][j] == '.') count++;
		return count;
	}

	// 폭탄이 주변에 있어서 방문은 되지만 큐에 들어가면 안되는 케이스!
	private static boolean isNotBomb(int x, int y, int N, char[][] map) {
		for(int d = 0; d < 8; d++) {
			int nx = x + dx[d], ny = y + dy[d];
			if(isOut(nx, ny, N)) continue;
			if(map[nx][ny] == '*') return false;
		}
		return true;
	}

	private static Deque<State> findInitState(int N, char[][] map) {
		Deque<State> clickList = new ArrayDeque<>();
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(map[i][j] == '.' && isNotBomb(i, j, N, map)) clickList.offer(new State(i, j));
		return clickList;
	}

	private static boolean isOut(int x, int y, int N) {
		return x >= N || y >= N || x < 0 || y < 0;
	}
}