package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 체스판다시칠하기_1018 {

	private static int N, M, result;
	private static char[][] board;
	private static char[][] trueBoardOne = {{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}};
	private static char[][] trueBoardTwo = {{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
											{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
											{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}};
	
	public static void main(String[] args) throws IOException {
		makeInput();
		findMinimumDiffToCurrBoard();
		printResult();
	}

	private static void findMinimumDiffToCurrBoard() {
		for(int i = 0; i <= N-8; i++)
			for(int j = 0; j <= M-8; j++)
				result = Math.min(result, calculateDiffFromStart(i, j));
	}

	private static int calculateDiffFromStart(int iStart, int jStart) {
		int diffToBoardOne = 0, diffToBoardTwo = 0;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(trueBoardOne[i][j] != board[i + iStart][j + jStart]) diffToBoardOne++;
				if(trueBoardTwo[i][j] != board[i + iStart][j + jStart]) diffToBoardTwo++;
			}
		return Math.min(diffToBoardOne, diffToBoardTwo);
	}

	private static void makeInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); result = Integer.MAX_VALUE;
		board = new char[N][M];
		for(int i = 0; i < N; i++) board[i] = br.readLine().toCharArray();
	}

	private static void printResult() { System.out.println(result); }

}
