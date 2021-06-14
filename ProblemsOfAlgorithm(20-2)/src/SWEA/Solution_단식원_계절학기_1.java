package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_단식원_계절학기_1 {

	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	private static int result;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_단식원.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];

			int cnt = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 0) cnt++;
				}
			}
			int[][] mapCoor = new int[cnt][2]; // [x번째 칸][좌표]
			cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0) {
						mapCoor[cnt][0] = i;
						mapCoor[cnt++][1] = j;
					}
				}
			}
			result = 0;
			findMaximumSlimZone(N, M, map, mapCoor, mapCoor.length, new int[3], 0, 0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static void findMaximumSlimZone(int N, int M, int[][] map, int[][] mapCoor, int zeroLength, int[] select, int numOfSelect, int preSelect) {
		if(numOfSelect == 3) {
			int currSlimArea = doDiffusionAndCheckSlimZone(N, M, copyMap(N, M, map), mapCoor, select);
			result = Math.max(currSlimArea, result);
			return;
		}
		for(int i = preSelect; i < zeroLength; i++) {
			select[numOfSelect] = i;
			findMaximumSlimZone(N, M, map, mapCoor, zeroLength, select, numOfSelect + 1, i + 1);
		}
	}

	private static int doDiffusionAndCheckSlimZone(int N, int M, int[][] map, int[][] mapCoor, int[] select) {
		for(int i = 0; i < 3; i++) map[mapCoor[select[i]][0]][mapCoor[select[i]][1]] = 1;
		Deque<int[]> q = makeInitDeque(N, M, map);

		while(!q.isEmpty()) {
			int[] currC = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = currC[0] + dx[d], ny = currC[1] + dy[d];
				if(isOut(nx, ny, N, M) || map[nx][ny] != 0) continue;
				map[nx][ny] = 2;
				q.offer(new int[]{nx, ny});
			}
		}
		return countSlimArea(N, M, map);
	}

	private static int countSlimArea(int N, int M, int[][] map) {
		int cnt = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(map[i][j] == 0) cnt++;
		return cnt;
	}

	private static boolean isOut(int x, int y, int N, int M) {
		return x >= N || y >= M || x < 0 || y < 0;
	}

	private static Deque<int[]> makeInitDeque(int N, int M, int[][] map) {
		Deque<int[]> q = new ArrayDeque<>();
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(map[i][j] == 2) q.offer(new int[]{i, j});
		return q;
	}

	private static int[][] copyMap(int N, int M, int[][] map) {
		int[][] newMap = new int[N][M];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				newMap[i][j] = map[i][j];
		return newMap;
	}
}