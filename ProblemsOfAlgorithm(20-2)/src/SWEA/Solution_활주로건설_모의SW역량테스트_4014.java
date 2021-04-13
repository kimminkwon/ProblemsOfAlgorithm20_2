package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_활주로건설_모의SW역량테스트_4014 {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_활주로건설.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			sb.append("#").append(tc).append(" ").append(runwayConstruction(N, X, map)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int runwayConstruction(int N, int X, int[][] map) {
		int colRunway = 0, rowRunway = 0;
		for(int i = 0; i < N; i++)
			if(colAndRowCheck(i, N, X, map)) colRunway++;

		int[][] turnMap = turningMap(N, map);

		for(int i = 0; i < N; i++)
			if(colAndRowCheck(i, N, X, turnMap)) rowRunway++;

		return colRunway + rowRunway;
	}

	private static boolean colAndRowCheck(int colNum, int N, int X, int[][] map) {
		boolean[][] isConstruction = new boolean[N][N];

		for(int i = 1; i < N; i++) {
			if(map[i - 1][colNum] > map[i][colNum]) { // 이전의 높이가 내 높이보다 높아졌다면
				for(int j = 0; j < X; j++) {
					if(isOut(i + j, colNum, N)) return false; // 1. 설치할 수 있는 범위 내여야 한다.
					if(map[i - 1][colNum] - 1 != map[i + j][colNum]) return false; // 2. 높이 차이는 1이여야한다.
					if(isConstruction[i + j][colNum]) return false; // 3. 설치된 곳에 또 설치할 수 없다.
				}
				for(int j = 0; j < X; j++) isConstruction[i + j][colNum] = true; // 설치됨을 체크
			} else if(map[i - 1][colNum] < map[i][colNum]) { // 이전의 높이가 내 높이보다 낮아졌다면
				for (int j = 0; j < X; j++) {
					if (isOut(i - 1 - j, colNum, N)) return false; // 1. 설치할 수 있는 범위 내여야 한다.
					if (map[i - 1 - j][colNum] != map[i][colNum] - 1) return false; // 2. 높이 차이는 1이여야한다.
					if (isConstruction[i - 1 - j][colNum]) return false; // 3. 설치된 곳에 또 설치할 수 없다.
				}
				for (int j = 0; j < X; j++) isConstruction[i - 1 - j][colNum] = true; // 설치됨을 체크
			}
		}
		return true;
	}

	private static int[][] turningMap(int N, int[][] map) {
		int[][] turnMap = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				turnMap[i][j] = map[N - j - 1][i];
		return turnMap;
	}

	private static boolean isOut(int x, int y, int N) {
		return x >= N || y >= N || x < 0 || y < 0;
	}
}
