package BaekJoon;

import java.io.*;
import java.util.*;

public class 알파벳_1987 {

	private static int R, C, result;
	private static int[][] board;
	private static boolean[][] visitedMap;
	private static boolean[] visitedChar;
	
	private static int[] dX = {-1, 1, 0, 0};
	private static int[] dY = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		visitedMap = new boolean[R][C];
		visitedChar = new boolean[26];
		
		for(int i = 0; i < R; i++) {
			String box = br.readLine();
			for(int j = 0; j < C; j++) 
				board[i][j] = box.charAt(j) - 'A';
		}
		visitedMap[0][0] = true;
		visitedChar[board[0][0]] = true;
		findMaximumPath(0, 0, 1);
		System.out.println(result);
	}
	
	private static void findMaximumPath(int x, int y, int length) {
		result = Math.max(length, result);
		for(int d = 0; d < 4; d++) {
			int nX = x + dX[d], nY = y + dY[d];
			if(!isOut(nX, nY) && !visitedMap[nX][nY] && !visitedChar[board[nX][nY]]) {
				visitedMap[nX][nY] = true; visitedChar[board[nX][nY]] = true;
				findMaximumPath(nX, nY, length + 1);
				visitedMap[nX][nY] = false; visitedChar[board[nX][nY]] = false;
			}
		}
	}
	
	private static boolean isOut(int x, int y) {
		return x >= R || y >= C || x < 0 | y < 0;
	}
}
