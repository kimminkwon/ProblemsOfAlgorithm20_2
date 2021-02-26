package SWEA;

import java.io.*;
import java.util.*;

public class Solution_프로세서연결하기_SWTest샘플문제_1767 {

	private static class Coor {
		int x, y;

		public Coor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int result, numOfConnect;
	private static int[] dX = {-1, 1, 0, 0}, dY = {0, 0, -1, 1};
	private static List<Coor> cores;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_프로세서연결하기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[][] maxinus = new int[N][N];
        	boolean[][] isBlock = new boolean[N][N];
        	result = Integer.MAX_VALUE;
        	numOfConnect = 0;
        	cores = new ArrayList<>();
        	
        	for(int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
        		for(int j = 0; j < N; j++) {
        			maxinus[i][j] = Integer.parseInt(st.nextToken());
        			if(maxinus[i][j] == 1) {
        				if(i != 0 && j != 0) cores.add(new Coor(i, j));
        				isBlock[i][j] = true;
        			}
        		}	
        	}
        	
        	findMaximumWireLength(N, 0, 0, 0, cores, cores.size(), isBlock);
        	sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
	}


	private static void findMaximumWireLength(int N, int cnt, int length, int currNumOfConnect, List<Coor> cores, int numOfCore, boolean[][] isBlock) {
		if(currNumOfConnect + (numOfCore - cnt) < numOfConnect) return;
		
		if(cnt == numOfCore) {
			if(numOfConnect < currNumOfConnect) {
				numOfConnect = currNumOfConnect;
				result = length;
			} else if(currNumOfConnect != 0 && numOfConnect == currNumOfConnect) result = Math.min(result, length);
			return;
		}
		for(int d = 0; d < 4; d++) {
			int currLength = isConnectable(N, d, cores.get(cnt).x, cores.get(cnt).y, isBlock);
			if(currLength != -1) {
				boolean[][] newIsBlock = fillBlock(N, d, cores.get(cnt).x, cores.get(cnt).y, isBlock);
				findMaximumWireLength(N, cnt + 1, length + currLength, currNumOfConnect + 1, cores, numOfCore, newIsBlock);
			}
		}
		findMaximumWireLength(N, cnt + 1, length, currNumOfConnect, cores, numOfCore, isBlock);
	}


	private static boolean[][] fillBlock(int N, int dir, int x, int y, boolean[][] isBlock) {
		boolean[][] newIsBlock = new boolean[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				newIsBlock[i][j] = isBlock[i][j];
		
		while(true) {
			int nX = x + dX[dir], nY = y + dY[dir];
			if(isOut(N, nX, nY)) break;
			newIsBlock[nX][nY] = true;
			x = nX; y = nY;
		}
		
		return newIsBlock;
	}


	private static int isConnectable(int N, int dir, int x, int y, boolean[][] isBlock) {
		int length = 0;
		while(true) {
			int nX = x + dX[dir], nY = y + dY[dir];
			if(isOut(N, nX, nY)) break;
			if(isBlock[nX][nY]) return -1;
			x = nX; y = nY;
			length++;
		}
		return length;
	}
	
	private static boolean isOut(int N, int x, int y) {
		return x >= N || y >= N || x < 0 || y < 0;
	}
	
	static void print(boolean[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				int num = arr[i][j] ? 1 : 0;
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}
