package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_방향전환_D4_8382 {

	private static final int MAX = 210;
	private static class State {
		int x, y, preDir, move;

		public State(int x, int y, int preDir, int move) {
			this.x = x;
			this.y = y;
			this.preDir = preDir;
			this.move = move;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_방향전환.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		int[][] pathDp = makePathArr();
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(findMinimumPath(x1, y1, x2, y2, pathDp)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int findMinimumPath(int x1, int y1, int x2, int y2, int[][] pathDp) {
		int xGap = Math.abs(x1 - x2);
		int yGap = Math.abs(y1 - y2);

		return pathDp[xGap][yGap];
	}

	private static int[][] makePathArr() {
		int[][] pathArr = new int[MAX][MAX];
		boolean[][][] visited = new boolean[MAX][MAX][2];
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		visited[0][1][0] = true; // 0: 가로
		visited[1][0][1] = true; // 1: 세로
		pathArr[0][1] = 1; pathArr[1][0] = 1;

		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(0, 1, 0, 1));
		q.offer(new State(1, 0, 1, 1));

		while(!q.isEmpty()) {
			State s = q.poll();
			if(s.preDir == 0) { // 세로로 이동해야한다.
				int nx = s.x + 1, ny = s.y;
				if(!isOut(nx, ny) && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					q.offer(new State(nx, ny, 1, s.move + 1));
					if(pathArr[nx][ny] == 0) pathArr[nx][ny] = s.move + 1;
				}
				nx = s.x - 1; ny = s.y;
				if(!isOut(nx, ny) && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					q.offer(new State(nx, ny, 1, s.move + 1));
					if(pathArr[nx][ny] == 0) pathArr[nx][ny] = s.move + 1;
				}
			} else {
				int nx = s.x, ny = s.y + 1;
				if(!isOut(nx, ny) && !visited[nx][ny][0]) {
					visited[nx][ny][0] = true;
					q.offer(new State(nx, ny, 0, s.move + 1));
					if(pathArr[nx][ny] == 0) pathArr[nx][ny] = s.move + 1;
				}
				nx = s.x; ny = s.y - 1;
				if(!isOut(nx, ny) && !visited[nx][ny][0]) {
					visited[nx][ny][0] = true;
					q.offer(new State(nx, ny, 0, s.move + 1));
					if(pathArr[nx][ny] == 0) pathArr[nx][ny] = s.move + 1;
				}
			}
		}

		return pathArr;
	}

	private static boolean isOut(int x, int y) {
		return x >= MAX || y >= MAX || x < 0 || y < 0;
	}

	private static void print(int[][] pathDp) {
		for(int i = 0; i < pathDp.length; i++) {
			for(int j = 0; j < pathDp[i].length; j++) {
				System.out.print(pathDp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
