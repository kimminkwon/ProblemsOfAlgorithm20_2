package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_벌꿀채취_모의SW역량테스트_2115 {

	private static int[][] honey;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_벌꿀채취.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 벌통의 크기
			int M = Integer.parseInt(st.nextToken()); // 채취가능한 벌통개수
			int C = Integer.parseInt(st.nextToken()); // 채취할 수 있는 꿀의 최대양
			honey = new int[N][N];
			List<int[]> honeyList = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
					honeyList.add(new int[]{i, j});
				}
			}
			int result = findMaximumProfitForHoney(0, 0, new int[2], N, M, C, honeyList);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static int findMaximumProfitForHoney(int index, int depth, int[] select, int N, int M, int C, List<int[]> honeyList) {
		if(depth == 2) {
			if(isOverlap(honeyList.get(select[0]), honeyList.get(select[1]), N, M)) return 0;
			return calculateProfit(honeyList.get(select[0]), honeyList.get(select[1]), N, M, C);
		}
		int currRes = 0;
		for(int i = index; i < honeyList.size(); i++) {
			select[depth] = i;
			currRes = Math.max(currRes, findMaximumProfitForHoney(i + 1, depth + 1, select, N, M, C, honeyList));
		}
		return currRes;
	}

	private static int calculateProfit(int[] coor1, int[] coor2, int N, int M, int C) {
		// 1. 일꾼1의 가능한 범위 저장
		int x1 = coor1[0], y1 = coor1[1];
		List<int[]> honeyLoc1 = new ArrayList<>();
		for(int ny = y1; ny < y1 + M; ny++) {
			if(isOut(x1, ny, N)) continue; // 좌표계를 벗어나는 경우
			honeyLoc1.add(new int[]{x1, ny}); // 벗어나지 않았다면 좌표를 넣어준다.
		}
		// 2. 넣어준 좌표에 대해서 최대 가능한 채취를 계산한다. ==> 부분집합
		int profit1 = getMaximumProfit(0, honeyLoc1.size(), new boolean[honeyLoc1.size()], honeyLoc1, C);

		// 3. 일꾼2의 가능한 범위 저장
		int x2 = coor2[0], y2 = coor2[1];
		List<int[]> honeyLoc2 = new ArrayList<>();
		for(int ny = y2; ny < y2 + M; ny++) {
			if(isOut(x2, ny, N)) continue; // 좌표계를 벗어나는 경우
			honeyLoc2.add(new int[]{x2, ny});
		}
		// 4. 넣어준 좌표에 대해서 최대 가능한 채취를 계산한다. ==> 부분집합
		int profit2 = getMaximumProfit(0, honeyLoc2.size(), new boolean[honeyLoc2.size()], honeyLoc2, C);

		return profit1 + profit2;
	}

	private static int getMaximumProfit(int index, int size, boolean[] select, List<int[]> honeyLoc, int C) {
		if(index == size) return getProfit(select, honeyLoc, size, C);

		int profit = 0;
		select[index] = true;
		profit = Math.max(profit, getMaximumProfit(index + 1, size, select, honeyLoc, C));
		select[index] = false;
		profit = Math.max(profit, getMaximumProfit(index + 1, size, select, honeyLoc, C));

		return profit;
	}

	private static int getProfit(boolean[] select, List<int[]> honeyLoc, int size, int C) {
		int honeyValue = 0, profit = 0;
		for(int i = 0; i < size; i++) {
			if(!select[i]) continue;
			int x = honeyLoc.get(i)[0], y = honeyLoc.get(i)[1];
			if(honeyValue + honey[x][y] > C) return 0;
			honeyValue += honey[x][y];
			profit += (honey[x][y] * honey[x][y]);
		}
		return profit;
	}

	private static boolean isOverlap(int[] coor1, int[] coor2, int N, int M) {
		boolean[][] visited = new boolean[N][N];
		// 1. coor1의 위치 체크 ==> y좌표가 M만큼 증가함
		for(int y = coor1[1]; y < coor1[1] + M; y++)
			if(!isOut(coor1[0], y, N)) visited[coor1[0]][y] = true;
		// 2. coor2의 위치가 겹치는가?
		for(int y = coor2[1]; y < coor2[1] + M; y++)
			if(!isOut(coor2[0], y, N)) if(visited[coor2[0]][y]) return true;
		return false;
	}

	private static boolean isOut(int x, int y, int N) {
		return x >= N || y >= N || x < 0 || y < 0;
	}
}

