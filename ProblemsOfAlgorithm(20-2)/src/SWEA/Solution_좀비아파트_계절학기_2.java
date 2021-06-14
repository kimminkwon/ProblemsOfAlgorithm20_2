package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_좀비아파트_계절학기_2 {

	private static int[] dx = {-1, 0, 1, 0, 0, 0}, dy = {0, 1, 0, -1, 0, 0}, dh = {0, 0, 0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_좀비아파트.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int[][][] apt = new int[N][M][H];

			boolean isAllHuman = true;
			for(int k = 0; k < H; k++) {
				for(int i = 0; i < N; i++) {
					st = new StringTokenizer(br.readLine());
					for(int j = 0; j < M; j++) {
						apt[i][j][k] = Integer.parseInt(st.nextToken());
						if(apt[i][j][k] == -1) isAllHuman = false;
					}
				}
			}
			String result = isAllHuman ? "ALL HUMANS" : calculateDaysForZombieApt(N, M, H, apt);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static String calculateDaysForZombieApt(int N, int M, int H, int[][][] apt) {
		Deque<int[]> q = makeInitDeque(N, M, H, apt);
		int day = 0;
		while(!q.isEmpty()) {
			int[] currS = q.poll();
			day = Math.max(currS[3], day);
			for(int d = 0; d < 6; d++) {
				int nx = currS[0] + dx[d], ny = currS[1] + dy[d], nh = currS[2] + dh[d];
				if(isOut(nx, ny, nh, N, M, H) || apt[nx][ny][nh] != -1) continue;
				q.offer(new int[]{nx, ny, nh, currS[3] + 1});
				apt[nx][ny][nh] = 1;
			}
		}
		return isStillZombie(day, N, M, H, apt);
	}

	private static String isStillZombie(int day, int N, int M, int H, int[][][] apt) {
		for(int k = 0; k < H; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < M; j++)
					if(apt[i][j][k] == -1) return "STILL ZOMBIES";
		return String.valueOf(day);
	}

	private static Deque<int[]> makeInitDeque(int N, int M, int H, int[][][] apt) {
		Deque<int[]> q = new ArrayDeque<>();
		for(int k = 0; k < H; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < M; j++)
					if(apt[i][j][k] == 1) q.offer(new int[]{i, j, k, 0});
		return q;
	}


	private static boolean isOut(int x, int y, int h, int N, int M, int H) {
		return x >= N || y >= M || h >= H || x < 0 || y < 0 || h < 0;
	}
}