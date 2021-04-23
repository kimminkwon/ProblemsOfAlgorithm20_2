package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_하강모의실험_모의A형테스트 {

	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_하강모의실험.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];

			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			sb.append("#").append(tc).append(" ").append(doDownExperiment(N, map)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static String doDownExperiment(int N, int[][] map) {
		for(int i = 0; i < N; i++) {
			if(map[0][i] == 0) continue;
			int[] lengthAndEnd = doDown(N, i, map);
			int length = lengthAndEnd[0], end = lengthAndEnd[1];
			for(int j = 0; j <= end; j++) map[j][i] = 0;
			for(int j = end; j > end - length; j--) map[j][i] = 1;
		}

		int[][] newMap = rotation(N, map);
		for(int i = 0; i < N; i++) {
			if(newMap[0][i] == 0) continue;
			int[] lengthAndEnd = doDown(N, i, newMap);
			int length = lengthAndEnd[0], end = lengthAndEnd[1];
			for(int j = 0; j <= end; j++) newMap[j][i] = 0;
			for(int j = end; j > end - length; j--) newMap[j][i] = 1;
		}
		int leftCount = 0, downCount = 0;
		for(int i = 0; i < N; i++) if(newMap[i][0] == 1) leftCount++;
		for(int i = 0; i < N; i++) if(newMap[N - 1][i] == 1) downCount++;

		return String.valueOf(leftCount) + " " + String.valueOf(downCount);
	}

	private static int[] doDown(int N, int y, int[][] map) {
		int length = 1;
		int end;
		double power = 1.0;
		for(end = 0; end < N - 1; end++) { // map[end][y]
			if(map[end + 1][y] == 1) { // 충돌하는 경우!
				int blockSize = getSize(end + 1, y, N, map);
				length += blockSize;
				end += blockSize;
				if(blockSize >= power) break;
				else power += blockSize;
			}
			power = power * 1.9d;
		}
		if(end >= N) end--;
		return new int[]{length, end};
	}

	private static int[][] rotation(int N, int[][] map) {
		int[][] newMap = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				newMap[i][j] = map[N - 1 - j][i];
		return newMap;
	}

	private static int getSize(int x, int y, int N, int[][] map) {
		int size = 1;
		for(int i = x; i < N - 1; i++) {
			if(map[i + 1][y] == 1) size += 1;
			else break;
		}
		return size;
	}
}

