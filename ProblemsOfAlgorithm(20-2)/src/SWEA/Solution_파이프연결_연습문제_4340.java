package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_파이프연결_연습문제_4340 {

	private static int result;
	private static String[] pipeState;
	private static Map<Integer, int[]> pipeCycleState;
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	private static class State {
		int x, y, pState, move, exmove;
		public State(int x, int y, int pState, int move, int exmove) {
			this.x = x;
			this.y = y;
			this.pState = pState;
			this.move = move;
			this.exmove = exmove;
		}

		@Override
		public String toString() {
			return "[(" + x +
					", " + y +
					"), pState=" + pState +
					", m=" + move +
					']';
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_파이프연결.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		makePipeState();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			result = Integer.MAX_VALUE;
			findMinimumPathForPipes(N, map);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static void findMinimumPathForPipes(int N, int[][] map) {
		int[][][] visited = new int[N][N][7];
		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(N - 1, N - 1, map[N - 1][N - 1], 1, 1));
		visited[N - 1][N - 1][map[N - 1][N - 1]] = 1;

		while(!q.isEmpty()) {
			System.out.println(q);
			State s = q.poll();
			if(s.x == 0 && s.y == 0 && s.pState == map[0][0]) { // 도착지점에 도달하였고, 도착지점의 파이프도 회전시키지 않았다면 멈춘다.
				result = s.move;
				return;
			}
			for(int d = 0; d < 4; d++) {
				int nx = s.x + dx[d], ny = s.y + dy[d];
				if(d == s.exmove) continue;
				if(isOut(nx, ny, N) || map[nx][ny] == 0) continue; // 0. 범위를 벗어났거나 파이프가 없다면 고려하지 않는다.
				if(!pipeState[s.pState].contains(String.valueOf(d))) continue; // 1. 현재 파이프에서 나갈 수 없는 길이라면 고려하지 않는다.
				int nextPipe = map[nx][ny]; // 2. 다음 Pipe의 상태번호를 가져온다.
				for(int cyclePipe : pipeCycleState.get(nextPipe)) { // 3. 다음 차례 파이프를 회전 시켜보며 갈 수 있는 방법을 찾는다.
					char nextT = getNextTerminal(d); // 현재 가고자 하는 길과 대응되는 길
					if(pipeState[cyclePipe].contains(String.valueOf(nextT))) { // 대응되는 길이 다음 예정 파이프에 포함되어있다면 갈 수 있는 길이다.
						// 방문이 되었던 곳이고, 그 방문이 현재 나보다 더 짧은 길로 왔다면 굳이 가 볼 필요가 없다.
						if(visited[nx][ny][cyclePipe] > 0 && visited[nx][ny][cyclePipe] <= s.move + 1) continue;
						// 방문 한적 없거나(visited가 0이거나), 방문 했지만 나보다 더 긴 길로 왔다면 새로운 경우의 수를 Q에 추가하고 방문처리를 한다.
						q.offer(new State(nx, ny, cyclePipe, s.move + 1, getNextTerminal(d)));
						visited[nx][ny][cyclePipe] = s.move + 1;
					}
				}


			}
		}

	}

	private static boolean isOut(int x, int y, int N) {
		return x >= N || y >= N || x < 0 || y < 0;
	}

	private static void makePipeState() {
		pipeState = new String[7];
		pipeState[0] = ""; pipeState[1] = "13"; pipeState[2] = "02";
		pipeState[3] = "12"; pipeState[4] = "23"; pipeState[5] = "03"; pipeState[6] = "01";

		pipeCycleState = new HashMap<>();
		pipeCycleState.put(0, new int[]{}); pipeCycleState.put(1, new int[]{1, 2}); pipeCycleState.put(2, new int[]{1, 2});
		pipeCycleState.put(3, new int[]{3, 4, 5, 6}); pipeCycleState.put(4, new int[]{3, 4, 5, 6});
		pipeCycleState.put(5, new int[]{3, 4, 5, 6}); pipeCycleState.put(6, new int[]{3, 4, 5, 6});
	}

	private static char getNextTerminal(int dir) {
		switch (dir) {
			case 0: return '2'; case 1: return '3';
			case 2: return '0'; case 3: return '1';
		}
		return 'X';
	}
}