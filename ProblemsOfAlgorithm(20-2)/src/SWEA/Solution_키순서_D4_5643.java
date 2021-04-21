package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_키순서_D4_5643 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_키순서.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			boolean[][] adjArr = new boolean[N][N];

			for(int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				adjArr[from][to] = true; // from -> to: from 학생이 to 학생보다 키가 작다.
			}

			sb.append("#").append(tc).append(" ").append(calculateNumOfAbsoluteSeq(N, adjArr)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static int calculateNumOfAbsoluteSeq(int N, boolean[][] adjArr) {
		for(int k = 0; k < N; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					if(i != j && k != i && k != j) if(adjArr[i][k] && adjArr[k][j]) adjArr[i][j] = true;

		int cnt = 0;
		for(int i = 0; i < N; i++) {
			boolean isPossible = true;
			for(int j = 0; j < N; j++) {
				// i에서 j로 가는 경로가 있는가?
				if(i != j && (!adjArr[i][j] && !adjArr[j][i])) isPossible = false;
			}
			if(isPossible) cnt++;
		}
		return cnt;
	}


}

