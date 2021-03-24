package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_하나로_D4_SW문제해결응용 {

	private static final double INF = Double.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_하나로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] landsCoor = new int[N][2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) landsCoor[i][0] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) landsCoor[i][1] = Integer.parseInt(st.nextToken());

			double E = Double.parseDouble(br.readLine());
			sb.append("#").append(tc).append(" ").append(findMinimumCostForTernal(N, landsCoor, E)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static long findMinimumCostForTernal(int N, int[][] landsCoor, double E) {
		double[][] adjArr = makeAdjArr(N, landsCoor, E);
		boolean[] visited = new boolean[N];
		double[] minDist = new double[N];
		Arrays.fill(minDist, INF);
		double result = 0.0;
		minDist[0] = 0;

		for(int V = 0; V < N; V++) {
			// 1. 가장 가까운 정점 선택
			double min = INF; int minIndex = -1;
			for(int i = 0; i < N; i++) {
				if(min > minDist[i] && !visited[i]) {
					min = minDist[i];
					minIndex = i;
				}
			}
			visited[minIndex] = true;
			result += min;

			// 2. 가까운 정점을 출발점으로 최소거리배열 업데이트
			for(int i = 0; i < N; i++) {
				if(minDist[i] > adjArr[minIndex][i] && !visited[i])
					minDist[i] = adjArr[minIndex][i];
			}
		}
		return Math.round(result);
	}

	private static double[][] makeAdjArr(int N, int[][] landsCoor, double E) {
		double[][] adjArr = new double[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) adjArr[i][j] = INF;
				adjArr[i][j] = getCost(landsCoor[i][0], landsCoor[i][1], landsCoor[j][0], landsCoor[j][1], E);
			}
		}
		return adjArr;
	}

	private static double getCost(int x1, int y1, int x2, int y2, double E) {
		return E * (Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}

}
